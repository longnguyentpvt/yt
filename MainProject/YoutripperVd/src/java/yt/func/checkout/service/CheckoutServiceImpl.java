/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.func.checkout.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.paypal.base.rest.PayPalRESTException;
import java.io.IOException;
import java.math.BigDecimal;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import javaclass.common.OrderPaymentInfo;
import javaclass.common.YTAttr;
import javaclass.common.YTPackageInvoiceInfo;
import javaclass.common.YTPackageInvoiceOrder;
import javaclass.common.YTSession;
import javaclass.common.YTSessionAccount;
import javaclass.common.ytbooking.YTBookingData;
import javaclass.common.ytpackage.YTPackageData;
import javaclass.utility.YTPaymentUtility;
import javaclass.utility.YTPriceUtility;
import javaclass.utility.YoutripperIDUtility;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yt.entity.tbl.BookingTransaction;
import yt.entity.tbl.DiscountCode;
import yt.entity.tbl.DiscountCurrency;
import yt.entity.tbl.PackageCurrency;
import yt.entity.tbl.Partner;
import yt.entity.tbl.YTBookingOrder;
import yt.entity.tbl.YTPackage;
import yt.entity.tbl.YTPackageContent;
import yt.entity.tbl.YTPackagePrice;
import yt.func.checkout.dao.BookingTransactionDAO;
import yt.func.checkout.dao.DiscountCodeDAO;
import yt.func.checkout.dao.OrderGenerationDAO;
import yt.func.checkout.dao.PackageCurrencyDAO;
import yt.func.checkout.dao.YTBookingOrderDAO;
import yt.func.checkout.dao.YTPackageDAO;
import yt.func.checkout.javaclass.CheckoutReviewResponse;
import yt.func.checkout.javaclass.DiscountCodeResponse;
import yt.func.checkout.javaclass.LinepayPaymentResponseResult;
import yt.func.checkout.javaclass.LinepayRequestingResponse;
import yt.func.checkout.javaclass.NewCheckoutResponse;
import yt.func.checkout.javaclass.PaypalPaymentResponseResult;
import yt.func.checkout.javaclass.PaypalRequestingResponse;
import yt.func.checkout.javaclass.VisaMasterPaymentData;
import yt.func.checkout.javaclass.VisaMasterResponseResult;

/**
 *
 * @author nickn
 */
@Service
public class CheckoutServiceImpl implements CheckoutService {

    @Autowired
    private YTPackageDAO ytPackageCheckoutDAO;

    @Autowired
    private BookingTransactionDAO bookingCheckoutTransactionDAO;

    @Autowired
    private OrderGenerationDAO orderCheckoutGenerationDAO;

    @Autowired
    private YTBookingOrderDAO ytCheckoutBookingOrderDAO;

    @Autowired
    private PackageCurrencyDAO packageCheckoutCurrencyDAO;

    @Autowired
    private DiscountCodeDAO packageCheckoutDiscountCodeDAO;

    @Autowired
    private CompleteService ytCheckoutCompleteService;

    @Override
    @Transactional
    public String processBooking(HttpServletRequest rq, HttpSession session) throws IOException, InterruptedException {
        String data = (String) rq.getAttribute(YTAttr.AJAX_DATA_ATTR);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode dataObject = mapper.readTree(data);

        String pkgID = dataObject.get("pkgID").asText();
        String currencyCode = dataObject.get("cc").asText();

        YTPackage ytPackage = ytPackageCheckoutDAO.getBookingInfo(pkgID, currencyCode);
        if (ytPackage != null) {
            String stt = ytPackage.getPackageStatus();
            if (stt.equals(YTPackageData.YT_PACKAGE_STATUS_APPROVED)) {

                Long servingDate = null, expirationDate = null;

                Integer servingTime = null, opServingQTY = null;

                int nPkgs = dataObject.get("np").asInt();

                DateTime transactionDT = new DateTime();
                long transactionTime = transactionDT.getMillis();
                long transactionDate = transactionDT.withTime(0, 0, 0, 0).getMillis();

                String durationType = ytPackage.getDurationType();
                int duration = ytPackage.getBusinessDuration();

                YTPackagePrice ytPrice = ytPackage.getPrices().iterator().next();
                BigDecimal pkgPrice = ytPrice.getPrice();

                String servingType = ytPackage.getServingType(), promotionPeriodsStr = ytPackage.getPromotionPeriods();
                boolean promotional = false, promotionSchedule = ytPackage.isPromotionSchedule();

                long pmDate;
                if (servingType.equals(YTPackageData.SERVING_TYPE_OPEN_TIMED)) {
                    opServingQTY = (int) ytPackage.getServingTimes();

                    String dateSchedule = ytPackage.getDateSchedule();
                    if (dateSchedule.equals(YTPackageData.DATE_SCHEDULE_OPENED_LIFETIME)) {
                        expirationDate = YTPackageData.UNLIMIT_EXPIRE_DATE;
                    } else if (dateSchedule.equals(YTPackageData.DATE_SCHEDULE_OPENED_LIMIT) || dateSchedule.equals(YTPackageData.DATE_SCHEDULE_OPENED_RANGE)) {
                        expirationDate = (long) ytPackage.getValidationEndDate();
                    } else if (dateSchedule.equals(YTPackageData.DATE_SCHEDULE_OPENED_TIME_SLOT)) {
                        int noW = ytPackage.getValidationWeeks();
                        expirationDate = transactionDT.plusWeeks(noW).getMillis();
                    } else {
                        throw new IllegalArgumentException("Package Detail wrong");
                    }

                    pmDate = transactionTime;
                } else {
                    JsonNode sdNode = dataObject.get("sd");
                    servingDate = sdNode.asLong();
                    pmDate = servingDate;
                }

                if (promotionSchedule) {
                    if (promotionPeriodsStr != null) {
                        TypeFactory typeFactory = mapper.getTypeFactory();
                        List<long[]> pPeriods = mapper.readValue(promotionPeriodsStr, typeFactory.constructCollectionType(List.class, long[].class));

                        for (int i = 0, max = pPeriods.size(); i < max; i++) {
                            long[] pPeriod = pPeriods.get(i);
                            long sd = pPeriod[0];
                            long ed = pPeriod[1];

                            if (pmDate >= sd && pmDate <= ed) {
                                promotional = true;
                                break;
                            }
                        }
                    } else {
                        promotional = true;
                    }
                }

                BigDecimal paymentPrice = null;
                if (!promotional) {
                    paymentPrice = pkgPrice.add(BigDecimal.ZERO);
                } else {
                    paymentPrice = ytPrice.getPromotionPrice();
                }

                String tsID = YoutripperIDUtility.generatBookingTransactionID(transactionTime);
                bookingCheckoutTransactionDAO.createBookingTransaction(tsID, transactionTime, transactionDate, pkgID, servingDate,
                        servingTime, currencyCode, nPkgs, servingType, durationType, duration, expirationDate, opServingQTY, pkgPrice, paymentPrice, promotional);

                return "{\"rs\" : \"" + tsID + "\"}";
            }

            throw new IllegalArgumentException("Package is not approved");
        }

        throw new IllegalArgumentException("Package does not exists");
    }

    @Override
    @Transactional
    public String loadCheckoutReview(HttpServletRequest rq, HttpSession session) throws IOException {
        String data = (String) rq.getAttribute(YTAttr.AJAX_DATA_ATTR);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode dataObject = mapper.readTree(data);

        String transactionID = dataObject.get("tid").asText();
        String discountCode = null;
        JsonNode discountCodeNode = dataObject.get("dc");
        if (!discountCodeNode.isNull()) {
            discountCode = discountCodeNode.asText();
        }

        BookingTransaction bt = bookingCheckoutTransactionDAO.loadCheckoutInfo(transactionID);

        String rp = null;
        if (bt != null) {
            String pkgID = bt.getPackageID();
            BigDecimal pmPrice = bt.getPaymentPrice();
            int qty = bt.getNoPackages();
            BigDecimal subTotal = pmPrice.multiply(new BigDecimal(qty)).setScale(2, BigDecimal.ROUND_HALF_UP);
            BigDecimal ttDiscount = BigDecimal.ZERO;
            String currencyCode = bt.getCurrencyCode();
            DiscountCodeResponse dcRP = null;
            if (discountCode != null) {
                DiscountCurrency dc = packageCheckoutDiscountCodeDAO.getDiscountCodeInfo(discountCode, currencyCode);

                YTPackage ytPackage = bt.getYtPackage();
                String partnerID = ytPackage.getPartnerID();
                String packageID = ytPackage.getPackageID();
                String categoryID = ytPackage.getCategoryID();
                String subCategoryID = ytPackage.getSubCategoryID();
                int noPackage = bt.getNoPackages();
                YTSessionAccount account = YTSession.getAccountSession(session);
                String tripperID = account.getTripperID();
                if (tripperID != null) {
                    boolean rounded = packageCheckoutCurrencyDAO.isRounded(currencyCode);
                    dcRP = validateDiscountCode(dc, partnerID, categoryID, subCategoryID, packageID, tripperID, discountCode, pmPrice, noPackage, rounded);
                    boolean validDC = dcRP.isValid();
                    if (validDC) {
                        ttDiscount = dcRP.getTotalDC();
                    }
                }
            }

            BigDecimal total = subTotal.subtract(ttDiscount).setScale(2, BigDecimal.ROUND_HALF_UP);

            CheckoutReviewResponse cR = new CheckoutReviewResponse(pkgID, pmPrice, qty, subTotal, total, currencyCode, dcRP);
            rp = mapper.writeValueAsString(cR);
        }
        return "{\"info\" : " + rp + "}";
    }

    @Transactional
    public DiscountCodeResponse validateDiscountCode(DiscountCurrency dc, String partnerID, String categoryID,
            String subCtegoryID, String packageID, String tripperID, String code, BigDecimal packagePrice, int noPackages, boolean rounded) throws IOException {
        DiscountCodeResponse response = new DiscountCodeResponse();
        boolean valid = false;
        String error = DiscountCodeResponse.WRONG_CODE_ERROR;
        BigDecimal ttDC = null;
        if (dc != null) {
            DiscountCode codeE = dc.getDiscountCode();
            String codeType = codeE.getCodeType();
            ObjectMapper mapper = new ObjectMapper();

            if (codeType.equals(YTBookingData.DISCOUNT_CODE_TYPE_EVERY_PACKAGE)) {
                valid = true;
            } else if (codeType.equals(YTBookingData.DISCOUNT_CODE_TYPE_SPECIFIC_PACKAGE)) {
                String packageList = codeE.getPackageList();
                if (packageList != null) {
                    String[] pkgs = mapper.readValue(packageList, String[].class);
                    for (String pkg : pkgs) {
                        if (packageID.equals(pkg)) {
                            valid = true;
                            break;
                        }
                    }
                }
            } else if (codeType.equals(YTBookingData.DISCOUNT_CODE_TYPE_SPECIFIC_PARTNER)) {
                String partnerList = codeE.getPartnerList();
                if (partnerList != null) {
                    String[] pns = mapper.readValue(partnerList, String[].class);
                    for (String pn : pns) {
                        if (partnerID.equals(pn)) {
                            valid = true;
                            break;
                        }
                    }
                }
            } else if (codeType.equals(YTBookingData.DISCOUNT_CODE_TYPE_SPECIFIC_CATEGORY)) {
                String categoryList = codeE.getCategoryList();
                if (categoryList != null) {
                    String[] cats = mapper.readValue(categoryList, String[].class);
                    for (String cat : cats) {
                        if (categoryID.equals(cat)) {
                            valid = true;
                            break;
                        }
                    }
                }
            } else if (codeType.equals(YTBookingData.DISCOUNT_CODE_TYPE_SPECIFIC_SUB_CATEGORY)) {
                String subList = codeE.getSubCategoryList();
                if (subList != null) {
                    String[] subs = mapper.readValue(subList, String[].class);
                    for (String sub : subs) {
                        if (subCtegoryID.equals(sub)) {
                            valid = true;
                            break;
                        }
                    }
                }
            }

            if (valid) {
                BigDecimal tt = packagePrice.multiply(new BigDecimal(noPackages)).setScale(2, BigDecimal.ROUND_HALF_UP);
                BigDecimal minimum = dc.getMinimumPayment();
                if (minimum != null) {
                    valid = tt.compareTo(minimum) > -1;
                }

                if (valid) {
                    boolean multipleUse = codeE.isMultipleUse();
                    boolean used = false;
                    if (!multipleUse) {
                        used = ytCheckoutBookingOrderDAO.checkUsedDiscountCode(code, tripperID);
                    }

                    valid = !used;
                    error = DiscountCodeResponse.EXPIRED_CODE_ERROR;
                    if (valid) {
                        int noUsed = (int) ytCheckoutBookingOrderDAO.getNoUsedOfDiscountCode(code);
                        int qtt = codeE.getQuantity();
                        valid = noUsed < qtt;

                        if (valid) {
                            error = null;
                            // get discount price
                            boolean specificDiscount = codeE.isSpecificDiscount();
                            if (specificDiscount) {
                                ttDC = dc.getDiscountPrice();
                            } else {
                                int pt = dc.getPercentageDiscount();
                                BigDecimal dcPrice = YTPriceUtility.getDiscountPriceByPercent(packagePrice, pt, rounded);

                                ttDC = dcPrice.multiply(new BigDecimal(noPackages));

                                BigDecimal maximumDC = dc.getMaximumDiscount();
                                if (maximumDC != null) {
                                    if (ttDC.compareTo(maximumDC) > 0) {
                                        ttDC = maximumDC.add(BigDecimal.ZERO);
                                    }
                                }
                            }
                        }
                    }
                }

            }
        }
        response.setValid(valid);
        response.setError(error);
        response.setTotalDC(ttDC);

        return response;
    }

    @Transactional
    private NewCheckoutResponse processNewCheckout(String transactionID, String discountCode, String tripperEmail, String billingFirstName,
            String billingLastName, String paymentMethod, HttpSession session) throws JsonProcessingException, IOException {
        BookingTransaction bt = bookingCheckoutTransactionDAO.getBookingToCheckout(transactionID);

        String packageID = bt.getPackageID();
        String servingType = bt.getServingType();
        String currencyCode = bt.getCurrencyCode();
        BigDecimal packagePrice = bt.getPackagePrice();
        BigDecimal paymentPrice = bt.getPaymentPrice();
        boolean promotional = bt.isOnPromotional();
        int usedQuota = bt.getNoPackages();
        YTPackage ytPackage = bt.getYtPackage();
        String partnerID = ytPackage.getPartnerID();
        String categoryID = ytPackage.getCategoryID();
        String subCategoryID = ytPackage.getSubCategoryID();

        PackageCurrency pkgCurrency = packageCheckoutCurrencyDAO.getCheckoutInfo(currencyCode);
        BigDecimal exRate = pkgCurrency.getRate();
        BigDecimal commissionRate = YTBookingData.getYT_COMMISSION_RATE();
        boolean rounded = pkgCurrency.isRounded();

        BigDecimal subTotal = paymentPrice.multiply(new BigDecimal(usedQuota)).setScale(2, BigDecimal.ROUND_HALF_UP);
        BigDecimal totalDiscount = BigDecimal.ZERO;
        if (discountCode.isEmpty()) {
            discountCode = null;
        }
        if (discountCode != null) {
            YTSessionAccount account = YTSession.getAccountSession(session);
            String tripperID = account.getTripperID();
            if (tripperID != null) {
                DiscountCurrency dc = packageCheckoutDiscountCodeDAO.getDiscountCodeInfo(discountCode, currencyCode);
                DiscountCodeResponse dcRP = validateDiscountCode(dc, partnerID, categoryID, subCategoryID, packageID,
                        tripperID, discountCode, paymentPrice, usedQuota, rounded);
                boolean vldc = dcRP.isValid();
                if (vldc) {
                    totalDiscount = dcRP.getTotalDC();
                }
            }
        }
        BigDecimal total = subTotal.subtract(totalDiscount);

        OrderPaymentInfo pmI = YTPriceUtility.getPaymentInfo(total, exRate, paymentMethod, commissionRate);
        BigDecimal amount = pmI.getAmount();
        BigDecimal paymentRate = pmI.getPayemntRate();
        BigDecimal finalRate = pmI.getFinalRate();
        BigDecimal ytCommission = pmI.getYtCommission();
        BigDecimal partnerPayalbe = pmI.getPartnerPayable();

        YTPackageContent content = ytPackage.getContents().iterator().next();
        String packageName = content.getPackageName();

        Long servingDate = bt.getServingDate();
        Integer servingTime = bt.getServingTime();
        String durationType = bt.getDurationType();
        int businessDuration = bt.getBusinessDuration();
        Long expirationDate = bt.getExpirationDate();
        Integer opServingQTY = bt.getOpServingQTY();

        DateTime cd = new DateTime();
        long bookingDateTime = cd.getMillis();
        long bookingDate = cd.withTime(0, 0, 0, 0).getMillis();

        long orderID = orderCheckoutGenerationDAO.getOrderID(transactionID);
        String orderNo = YoutripperIDUtility.generateBookingOrderNo(orderID, cd);

        Partner pn = ytPackage.getPartner();
        String pnLogo = pn.getSmallAvatar();
        String businessName = pn.getBusinessName();
        String bsnAdd = pn.getBusinessAddress();
        String state = pn.getPartnerState().getStateName();
        String pncountry = pn.getPartnerCountry().getCountryName();
        String pnPostalCode = pn.getPostalCode();
        String pnPhoneCode = pn.getPhoneCode();
        String pnPhoneNo = pn.getPhoneNumber();
        String pnVerficiationID = pn.getVerificationID();

        BigDecimal[] vatInfo = YTPriceUtility.getVATInfo(YTBookingData.VAT_PERCENT, amount);
        BigDecimal vat = vatInfo[1];
        BigDecimal amountExVAT = vatInfo[0];

        YTPackageInvoiceOrder order = new YTPackageInvoiceOrder(packageID, packageName, "(Option Name)",
                currencyCode, paymentPrice, usedQuota, subTotal);
        List<YTPackageInvoiceOrder> orders = new ArrayList<>();
        orders.add(order);

        YTPackageInvoiceInfo invoiceInfo = new YTPackageInvoiceInfo(pnLogo, businessName,
                bsnAdd, state, pncountry, pnPostalCode, pnPhoneNo, pnPhoneCode, pnVerficiationID, null, billingFirstName + " " + billingLastName,
                null, null, null, null, bookingDateTime, orderNo, currencyCode, subTotal, totalDiscount,
                discountCode, total, finalRate, amount, vat, amountExVAT, paymentMethod, null, null, null, orders);

        ObjectMapper mapper = new ObjectMapper();
        String invoiceContent = mapper.writeValueAsString(invoiceInfo);

        ytCheckoutBookingOrderDAO.insertNewOrder(orderNo, transactionID, null, tripperEmail, billingFirstName, billingLastName,
                packageID, servingType, bookingDate, bookingDateTime, currencyCode, subTotal, discountCode, totalDiscount, total, exRate, paymentRate, finalRate, amount,
                commissionRate, ytCommission, partnerPayalbe,
                packageName, packagePrice, paymentPrice, promotional, servingDate, servingTime, durationType, businessDuration,
                expirationDate, opServingQTY, usedQuota, paymentMethod, invoiceContent);

        NewCheckoutResponse rp = new NewCheckoutResponse(orderNo, currencyCode, total);
        return rp;
    }

    @Transactional
    private void processPayment(HttpServletRequest rq, boolean success, String orderNo, String pmStt) {
        if (success) {
            if (pmStt.equals(YTBookingData.PAYMENT_STATUS_RESERVED)) {
                ytCheckoutBookingOrderDAO.succeedBooking(orderNo);
                ytCheckoutCompleteService.completeSuccessPayment(orderNo);
            }
        } else {
            // check expired
            if (!pmStt.equals(YTBookingData.PAYMENT_STATUS_EXPIRED)) {
                // fail
                if (!pmStt.equals(YTBookingData.PAYMENT_STATUS_FAIL)) {
                    ytCheckoutBookingOrderDAO.failBooking(orderNo);
                }
            }
        }
        rq.setAttribute("sc", success);
        rq.setAttribute("on", orderNo);
    }

    @Override
    @Transactional
    public String processVisaMasterCheckout(HttpServletRequest rq, HttpSession session) throws IOException {
        String data = (String) rq.getAttribute(YTAttr.AJAX_DATA_ATTR);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode dataObject = mapper.readTree(data);

        String transactionID = dataObject.get("tid").asText();
        String discountCode = dataObject.get("dc").asText();
        String tripperEmail = dataObject.get("tem").asText();
        String billingFirstName = dataObject.get("bfn").asText();
        String billingLastName = dataObject.get("bln").asText();
        String paymentMethod = YTBookingData.VISA_MASTER_METHOD;

        NewCheckoutResponse rp = processNewCheckout(transactionID, discountCode, tripperEmail, billingFirstName, billingLastName, paymentMethod, session);
        String orderNo = rp.getOrderNo();

        return "{\"rp\" : \"" + orderNo + "\"}";
    }

    @Override
    @Transactional
    public void getVisaMasterCheckoutInfo(HttpServletRequest rq) throws JsonProcessingException {
        String orderNo = rq.getParameter("orderNo");
        if (orderNo != null && !orderNo.isEmpty()) {
            YTBookingOrder order = ytCheckoutBookingOrderDAO.getTotalCheckout(orderNo);
            String currencyCode = order.getCurrencyCode();
            BigDecimal total = order.getTotal();

            VisaMasterPaymentData data = new VisaMasterPaymentData(orderNo, total, currencyCode);
            ObjectMapper mapper = new ObjectMapper();
            String rp = mapper.writeValueAsString(data);
            rq.setAttribute("rp", rp);
            return;
        }
        throw new IllegalArgumentException("Wrong Order No");
    }

    @Override
    @Transactional
    public void visaMasterPayment(HttpServletRequest rq) throws NoSuchAlgorithmException,
            InvalidKeyException {
        String expirationMonth = rq.getParameter("expMonthCardInfo");
        String expirationYear = rq.getParameter("expYearCardInfo");
        String encryptedCardInfo = rq.getParameter("encryptedCardInfo");
        String maskedCardInfo = rq.getParameter("maskedCardInfo");
        String cardHolderName = rq.getParameter("cardHolderName");
        String orderNo = rq.getParameter("on");

        YTBookingOrder order = ytCheckoutBookingOrderDAO.getOrderInfoForVisaMasterPayment(orderNo);

        BigDecimal tt = order.getTotal();
        String currencyCode = order.getCurrencyCode();

        String paymentRequest = YTPaymentUtility.generate2c2pRequest(encryptedCardInfo, cardHolderName, tt, currencyCode, orderNo);
        rq.setAttribute("paymentrequest", paymentRequest);
    }

    @Override
    @Transactional
    public void processVisaMasterPaymentResponse(HttpServletRequest rq) throws Exception {
        String paymentResponse = rq.getParameter("paymentResponse");
        VisaMasterResponseResult result = YTPaymentUtility.verifyPaymentResponse(paymentResponse);
        String orderNo = result.getOrderNo();
        boolean success = result.isSuccess();
        String reponseXML = result.getResponse();

        YTBookingOrder info = ytCheckoutBookingOrderDAO.getVisaMasterOrderInfoForResult(orderNo);
        String pmStt = info.getPaymentStatus();
        processPayment(rq, success, orderNo, pmStt);
    }

    @Override
    @Transactional
    public String processPaypalPayment(HttpServletRequest rq, HttpSession session) throws IOException, PayPalRESTException {
        String data = (String) rq.getAttribute(YTAttr.AJAX_DATA_ATTR);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode dataObject = mapper.readTree(data);

        String transactionID = dataObject.get("tid").asText();
        String discountCode = dataObject.get("dc").asText();
        String tripperEmail = dataObject.get("tem").asText();
        String billingFirstName = dataObject.get("bfn").asText();
        String billingLastName = dataObject.get("bln").asText();
        String paymentMethod = YTBookingData.PAYPAL_METHOD;

        NewCheckoutResponse rp = processNewCheckout(transactionID, discountCode, tripperEmail, billingFirstName, billingLastName, paymentMethod, session);
        String orderNo = rp.getOrderNo();
        String currencyCode = rp.getCurrencyCode();
        BigDecimal total = rp.getTotal();

        PaypalRequestingResponse requestingRp = YTPaymentUtility.generatePaypalRequest(orderNo, currencyCode, total);
        String rpLink = requestingRp.getRedirectLink();
        String paymentID = requestingRp.getPaymentID();
        ytCheckoutBookingOrderDAO.updatePaypalPaymentID(orderNo, paymentID);

        return "{\"rp\" : \"" + rpLink + "\"}";
    }

    @Override
    @Transactional
    public void processPaypalResponse(HttpServletRequest rq) throws PayPalRESTException {
        boolean stt = Integer.parseInt(rq.getParameter(YTPaymentUtility.PAYPAL_RESPONSE_PARAM_STT)) == 1;
        String orderNo = rq.getParameter(YTPaymentUtility.PAYPAL_RESPONSE_PARAM_ORDER_NO);
        YTBookingOrder info = ytCheckoutBookingOrderDAO.getPaypalOrderInfoForResult(orderNo);
        String paymentStt = info.getPaymentStatus();
        if (stt) {
            stt = false;
            if (!paymentStt.equals(YTBookingData.PAYMENT_STATUS_EXPIRED)) {
                String storedPaypalID = info.getPaypalID();
                String paymentID = rq.getParameter("paymentId");
                if (storedPaypalID.equals(paymentID)) {
                    String payerID = rq.getParameter("PayerID");
                    PaypalPaymentResponseResult rs = YTPaymentUtility.processPaypalResponse(paymentID, payerID, orderNo);
                    stt = rs.isSuccess();
                    String paypalStt = rs.getPaymentStatus();
                }
            }
        }
        processPayment(rq, stt, orderNo, paymentStt);
    }

    @Override
    @Transactional
    public String processLinepayPayment(HttpServletRequest rq, HttpSession session) throws IOException {
        String data = (String) rq.getAttribute(YTAttr.AJAX_DATA_ATTR);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode dataObject = mapper.readTree(data);

        String transactionID = dataObject.get("tid").asText();
        String discountCode = dataObject.get("dc").asText();
        String tripperEmail = dataObject.get("tem").asText();
        String billingFirstName = dataObject.get("bfn").asText();
        String billingLastName = dataObject.get("bln").asText();
        String paymentMethod = YTBookingData.PAYPAL_METHOD;

        NewCheckoutResponse rp = processNewCheckout(transactionID, discountCode, tripperEmail, billingFirstName, billingLastName, paymentMethod, session);
        String orderNo = rp.getOrderNo();
        String currencyCode = rp.getCurrencyCode();
        BigDecimal total = rp.getTotal();

        LinepayRequestingResponse paymentRQ = YTPaymentUtility.generateLinepayRequest(orderNo, total, currencyCode);
        String rpLink = paymentRQ.getRedirectLink();
        String linepayID = paymentRQ.getLinepayID();
        ytCheckoutBookingOrderDAO.updateLinepayPaymentID(orderNo, linepayID);

        return "{\"rp\" : \"" + rpLink + "\"}";
    }

    @Override
    @Transactional
    public void processLinepayResponse(HttpServletRequest rq) throws IOException {
        String transactionID = rq.getParameter("transactionId");
        String orderNo = rq.getParameter(YTPaymentUtility.PAYPAL_RESPONSE_PARAM_ORDER_NO);

        YTBookingOrder info = ytCheckoutBookingOrderDAO.getLinepayOrderInfoForResult(orderNo);
        String storedLinepayID = info.getLinepayID();
        String paymentStt = info.getPaymentStatus();

        boolean stt = false;
        if (!paymentStt.equals(YTBookingData.PAYMENT_STATUS_EXPIRED)) {
            if (storedLinepayID.equals(transactionID)) {
                BigDecimal total = info.getTotal();
                String currencyCode = info.getCurrencyCode();

                LinepayPaymentResponseResult result = YTPaymentUtility.processLinepayResponse(transactionID, total, currencyCode);
                stt = result.isSuccess();
            }
        }
        processPayment(rq, stt, orderNo, paymentStt);
    }

    @Override
    @Transactional
    public void expireBooking() {
        DateTime dt = new DateTime();
        dt = dt.minusMinutes(YTBookingData.RESERVED_INTERVAL);
        long limitTime = dt.getMillis();
        ytCheckoutBookingOrderDAO.updateExpireBooking(limitTime);
    }

}
