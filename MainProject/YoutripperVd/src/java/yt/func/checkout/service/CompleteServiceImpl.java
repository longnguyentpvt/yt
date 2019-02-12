/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.func.checkout.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javaclass.common.EmailAttachment;
import javaclass.common.YTData;
import javaclass.common.YTPackageInvoiceInfo;
import javaclass.common.ytbooking.YTBookingData;
import javaclass.utility.EmailUtility;
import javaclass.utility.PDFUtility;
import javaclass.utility.YTFileUtility;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.transaction.annotation.Transactional;
import yt.entity.tbl.Partner;
import yt.entity.tbl.YTBookingOrder;
import yt.func.checkout.dao.InvoiceGenerationDAO;
import yt.func.checkout.dao.YTBookingOrderDAO;
import yt.globalexception.GlobalExceptionHandler;

/**
 *
 * @author nickn
 */
public class CompleteServiceImpl implements CompleteService {

    @Autowired
    private YTBookingOrderDAO ytCheckoutBookingOrderDAO;

    @Autowired
    private InvoiceGenerationDAO ytCheckoutInvoiceGenerationDAO;

    @Override
    @Async
    @Transactional
    public void completeSuccessPayment(String orderNo) {
        // get order info
        YTBookingOrder order = ytCheckoutBookingOrderDAO.getInfoToCompletePayment(orderNo);
        if (order != null) {
            String pms = order.getPaymentStatus();
            if (pms.equals(YTBookingData.PAYMENT_STATUS_SUCCESS)) {
                // generate invoice
                String invoiceContent = order.getInvoiceContent();
                if (invoiceContent != null) {
                    try {
                        ObjectMapper mapper = new ObjectMapper();
                        YTPackageInvoiceInfo invoice = mapper.readValue(invoiceContent, YTPackageInvoiceInfo.class);

                        String paypalID = order.getPaypalID();
                        String linepayID = order.getLinepayID();
                        String visaMasterCardNo = order.getVisaMasterCardNo();

                        invoice.setPaypalID(paypalID);
                        invoice.setLinepayID(linepayID);
                        invoice.setCartNo(visaMasterCardNo);

                        DateTime dt = new DateTime(invoice.getInvoiceDate());
                        int month = dt.getMonthOfYear();
                        String invoiceID = ytCheckoutInvoiceGenerationDAO.getInvoiceID(month, dt);
                        invoice.setInvoiceNo(invoiceID);
                        String invoiceFile = YTFileUtility.generateInvoiceFileName(invoiceID);

                        // upload file
                        byte[] iBytes = PDFUtility.generateInvoicePDF(invoice);
                        String ctType = YTFileUtility.PDF_CONTENT_TYPE;
                        YTFileUtility.uploadFileToCDN(iBytes, invoiceFile, ctType, false, YTData.CDN_INVOICE_FOLDER);
                        // then send email
                        EmailAttachment atm = new EmailAttachment(iBytes, invoiceID + "." + YTFileUtility.PDF_EXTENSION, ctType);
                        List<EmailAttachment> atts = new ArrayList<>();
                        atts.add(atm);

                        // email content
                        String emailTitle = "Confirmation for Booking No. " + orderNo;
                        String contentTitle = "Booking No. " + orderNo;
                        String pkgName = order.getPackageName();
                        String emailT = "<table width='100%' border='0' cellspacing='0' cellpadding='0' align='center'>"
                                + "<tr>"
                                + "<td>"
                                + pkgName
                                + "</td>"
                                + "</tr>"
                                + "<tr>"
                                + "<td>"
                                + "<br/>"
                                + "Please find attached PDF copies of your tax invoice / receipt for this booking."
                                + "</td>"
                                + "</tr>"
                                + "</table>";
                        String tpName = order.getBillingFirstName() + " " + order.getBillingLastName();
                        String tpEmail = order.getTripperEmail();

                        Partner pn = order.getYtPackage().getPartner();
                        String pnName = pn.getDisplayName();
                        String pnEmail = pn.getEmail();

                        EmailUtility.sendEmailByRegistrationEmail(tpEmail, emailTitle, "Youtripper Booking", emailT, contentTitle, null, null, atts);
                        EmailUtility.sendEmailByRegistrationEmail(pnEmail, emailTitle, "Youtripper Booking", emailT, contentTitle, null, null, atts);
                        ytCheckoutBookingOrderDAO.completeOrder(orderNo, invoiceID, invoiceFile);
                    } catch (Exception ex) {
                        GlobalExceptionHandler.logCompleteCheckout(ex, orderNo);
                    }
                    return;
                }
                GlobalExceptionHandler.logCompleteCheckout(new IllegalArgumentException("Invoice Fail"), orderNo);
                return;
            }
            GlobalExceptionHandler.logCompleteCheckout(new IllegalArgumentException("Wrong Status"), orderNo);
            return;
        }
        GlobalExceptionHandler.logCompleteCheckout(new IllegalArgumentException("Wrong Order"), orderNo);
    }

}
