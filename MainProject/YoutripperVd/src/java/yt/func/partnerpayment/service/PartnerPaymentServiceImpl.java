/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.func.partnerpayment.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javaclass.common.YTPartnerPaymentInfo;
import javaclass.common.YTSession;
import javaclass.common.YTSessionAccount;
import javaclass.utility.ContentUtility;
import javaclass.utility.YTDateTimeUtility;
import javax.servlet.http.HttpSession;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yt.entity.tbl.YTBookingOrder;
import yt.func.partnerpayment.dao.YTBookingOrderDAO;
import yt.func.partnerpayment.javaclass.PartnerPaymentOrder;
import yt.func.partnerpayment.javaclass.PaymentFilterResult;

/**
 *
 * @author nickn
 */
@Service
public class PartnerPaymentServiceImpl implements PartnerPaymentService {

    @Autowired
    private YTBookingOrderDAO partnerPaymentOrderDAO;

    @Override
    @Transactional
    public String getPaymentList(HttpSession session, String data) throws IOException {
        //get partnerID
        YTSessionAccount account = YTSession.getAccountSession(session);
        String partnerID = account.getPartnerID();

        //get data
        ObjectMapper mapper = new ObjectMapper();
        JsonNode dataObject = mapper.readTree(data);

        int year = dataObject.get("sly").asInt();

        JsonNode monthNode = dataObject.get("slm");
        Integer month = null;
        if (!monthNode.isNull()) {
            month = monthNode.asInt();
        }

        DateTime dateO = new DateTime().withYear(year);
        long startBookingDate, endBookingDate;
        if (month != null) {
            DateTime startDayOfMonth = dateO.withMonthOfYear(month).withDayOfMonth(1).withTime(0, 0, 0, 0);
            startBookingDate = startDayOfMonth.getMillis();
            DateTime endDayOfMonth = startDayOfMonth.plusMonths(1).minusSeconds(1);
            endBookingDate = endDayOfMonth.getMillis();
        } else {
            DateTime startDayOfYear = dateO.withMonthOfYear(1).withDayOfMonth(1).withTime(0, 0, 0, 0);
            startBookingDate = startDayOfYear.getMillis();
            DateTime endDayOfYear = startDayOfYear.plusYears(1).minusSeconds(1);
            endBookingDate = endDayOfYear.getMillis();
        }

        int limit = dataObject.get("limit").asInt();
        int currentPage = dataObject.get("currentPage").asInt();

        JsonNode statusNode = dataObject.get("status");
        String status = null;
        if (!statusNode.isNull()) {
            status = statusNode.asText();
        }
        //get list partner payment order
        Map.Entry<Long, List<YTBookingOrder>> filterResult = partnerPaymentOrderDAO.getPartnerPaymentOrders(partnerID, startBookingDate,
                endBookingDate, status, currentPage, limit, false);

        long noOrders = filterResult.getKey();
        List<YTBookingOrder> paymentOrders = filterResult.getValue();

        List<PartnerPaymentOrder> orders = new ArrayList<>();
        for (YTBookingOrder paymentOrder : paymentOrders) {
            long bookingDateTime = paymentOrder.getBookingDate();
            String bookingDate = YTDateTimeUtility.convertMilliToddMMyyyy(bookingDateTime);
            String bookingCode = paymentOrder.getOrderNo();
            String currencyCode = paymentOrder.getCurrencyCode();
            String packageID = paymentOrder.getPackageID();
            String packageName = paymentOrder.getPackageName();

            BigDecimal aTotalWoDc = paymentOrder.getTotal();
            if (aTotalWoDc == null) {
                aTotalWoDc = new BigDecimal(0);
            }
            BigDecimal aDiscount = null;
            if (aDiscount == null) {
                aDiscount = new BigDecimal(0);
            }
            BigDecimal aPayback = null;
            if (aPayback == null) {
                aPayback = new BigDecimal(0);
            }
            BigDecimal aTotal = paymentOrder.getAmount();
            if (aTotal == null) {
                aTotal = new BigDecimal(0);
            }
            BigDecimal anExRate = paymentOrder.getFinalRate();
            if (anExRate == null) {
                anExRate = new BigDecimal(0);
            }
            BigDecimal aCommission = paymentOrder.getYtCommission();
            if (aCommission == null) {
                aCommission = new BigDecimal(0);
            }
            BigDecimal aPartnerTotal = paymentOrder.getPartnerPayable();
            if (aPartnerTotal == null) {
                aPartnerTotal = new BigDecimal(0);
            }
            //
            String doneDate = null;
            Long doneDateL = paymentOrder.getPayableBillDate();
            if (doneDateL != null) {
                doneDate = YTDateTimeUtility.convertMilliToddMMyyyy(doneDateL);
            }
            //
            boolean done = paymentOrder.isPayableBill();
            String returnStatus = paymentOrder.getWithdrawStatus();
            String orderStatus;
            int pendingDays = 0;
            if (returnStatus == null) {
                if (done) {
                    orderStatus = YTPartnerPaymentInfo.PAYMENT_STATUS_FILTER_PAYABLE;

                } else {
                    orderStatus = YTPartnerPaymentInfo.PAYMENT_STATUS_FILTER_PENDING;
                    pendingDays = YTDateTimeUtility.calculateDifferenceFromToday(doneDateL);
                }
            } else {
                orderStatus = returnStatus;
            }

            PartnerPaymentOrder anOrder = new PartnerPaymentOrder(bookingDateTime,
                    bookingDate, bookingCode, currencyCode, packageID,
                    packageName, aTotalWoDc, aDiscount, aPayback, aTotal,
                    anExRate, aCommission, aPartnerTotal, done, doneDate, pendingDays, orderStatus);
            orders.add(anOrder);
        }

        PaymentFilterResult result = new PaymentFilterResult(noOrders, orders);
        String json = mapper.writeValueAsString(result);
        json = ContentUtility.refineContentAsJsonInJavaSript(json);
        return json;
    }

}
