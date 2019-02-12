/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaclass.utility;

import com.mailjet.client.ClientOptions;
import com.mailjet.client.MailjetClient;
import com.mailjet.client.MailjetRequest;
import com.mailjet.client.MailjetResponse;
import com.mailjet.client.resource.Emailv31;

import java.util.List;
import javaclass.common.EmailAttachment;
import org.apache.commons.codec.binary.Base64;
import org.json.JSONArray;
import org.json.JSONObject;
import yt.globalexception.GlobalExceptionHandler;
import yt.javaclass.config.YTDataConfiguration;

/**
 *
 * @author nickn
 */
public class EmailUtility {
    // registration email

    private static final String REGISTRATION_EMAIL = "registration@youtripper.com";
    private static final String REGISTRATION_PASSWORD = "Tripregister190515";

    // noreply email
    private static final String NOREPLY_EMAIL = "noreply@youtripper.com";
    private static final String NOREPLY_PASSWORD = "Tripper190515";

    //booking eamail
    private static final String BOOKING_EMAIL = "booking@youtripper.com";
    private static final String BOOKING_PASSWORD = "Tripbook190515";

    //package email
    private static final String ADMIN_EMAIL = "registration@youtripper.com";
    private static final String ADMIN_PASSWORD = "Tripregister190515";

    public static int sendEmailByRegistrationEmail(String receiverEmail,
            String title, String nickname, String content, String contentTitle, String thankName, String contactEmail,
            List<EmailAttachment> attachments) {
        return sendEmailWithMailjet(REGISTRATION_EMAIL, REGISTRATION_PASSWORD, receiverEmail,
                title, nickname, content, contentTitle, thankName, contactEmail, attachments);
    }

    public static int sendEmailByNoReplayEmail(String receiverEmail,
            String title, String nickname, String content, String contentTitle, String thankName, String contactEmail,
            List<EmailAttachment> attachments) {
        return sendEmailWithMailjet(NOREPLY_EMAIL, NOREPLY_PASSWORD, receiverEmail,
                title, nickname, content, contentTitle, thankName, contactEmail, attachments);
    }

    private static int sendEmailWithMailjet(String senderEmail, String senderPassword, String receiverEmail,
            String title, String nickname, String content, String contentTitle, String thankName, String contactEmail,
            List<EmailAttachment> attachments) {
        int responseCode = 0;
        try {
            if (thankName == null || thankName.isEmpty()) {
                thankName = "Youtripper Team";
            }

            if (contactEmail == null || contactEmail.isEmpty()) {
                contactEmail = "help@youtripper.com";
            }
            String thankStr = "<br/>"
                    + "Thanks,"
                    + "<br/>"
                    + "<span style='text-decoration: none; color: #4DBDC9;'>" + thankName + "</span>";

            String cdn = YTDataConfiguration.getCommonImageURL();
            String emailContent = "<table width='100%' border='0' cellspacing='0' cellpadding='0'>"
                    + "<tr>"
                    + "<td align='center'>"
                    + "<center style='width:100%; min-width:300px; margin:0 auto'>"
                    + "<table style='color: #555555; width: 650px; line-height: 21px; margin: 0 auto; background-color: #F8F7F9'>"
                    + "<tr>"
                    + "<td  style='padding: 30px 0 25px; text-align: center; color: #fff; background-color: #4DBDC9'>"
                    + "<img width='80' height='80' src='" + cdn + "email-youtripper-icon.png' style='margin-bottom: 15px;'>"
                    + "<br/>"
                    + "<h1 style=' color: #fff; font-size: 36px;"
                    + " margin: 0; line-height: 44px;'>"
                    + contentTitle
                    + "</h1>"
                    + "</td>"
                    + "</tr>"
                    + "<tr>"
                    + "<td>"
                    + "<table width='100%' border='0' cellspacing='0' cellpadding='0'>"
                    + "<tr>"
                    + "<td style='padding-left: 50px; padding-right: 50px'>"
                    + "<table style='color: #555555; width: 100%; line-height: 21px;  background-color: #F8F7F9; font-size: 14px'>"
                    + "<tr>"
                    + "<td>"
                    + "<br/>"
                    + "</td>"
                    + "</tr>"
                    + content
                    + "<tr>"
                    + "<td>"
                    + thankStr
                    + "</td>"
                    + "</tr>"
                    + "<tr>"
                    + "<td>"
                    + "<table width='100%' border='0' cellspacing='0' cellpadding='0'>"
                    + "<tr>"
                    + "<td style='padding-left: 90px; padding-right: 90px'>"
                    + "<table style='width: 100%;  color: #555555;"
                    + "line-height: 21px; font-size: 12px;  border-bottom: solid 1px #9C9B9C'>"
                    + "<tr>"
                    + "<td style='text-align: center; font-size: 12px;'>"
                    + "<br/>"
                    + "Need help? Please contact <a href='mailto:"
                    + contactEmail
                    + "' style='text-decoration: none; text-decoration: none; color: #4DBDC9;'>"
                    + contactEmail
                    + "</a>"
                    + "</td>"
                    + "</tr>"
                    + "</table>"
                    + "</td>"
                    + "</tr>"
                    + "</table>"
                    + "</td>"
                    + "</tr>"
                    + "<tr>"
                    + "<td>"
                    + "<table width='100%' border='0' cellspacing='0' cellpadding='0'>"
                    + "<tr>"
                    + "<td style='padding-left: 50px; padding-right: 50px'>"
                    + "<table style='width: 100%; color: #555555; line-height: 21px; font-size: 12px'>"
                    + "<tr>"
                    + "<td style='text-align: center;'>"
                    + "Sent from <a href='https://youtripper.com' style='text-decoration: none; color: #4DBDC9;'>youtripper.com</a>"
                    + "<br/>"
                    + "<a href='' style='text-decoration: none; cursor: default; color: #555555;'>56 Soi Seri</a> Villar, Srinakarin Rd., Nongbon, Prawet, Bangkok, Thailand 10250"
                    + "<br/>"
                    + "Tel: +66-2101-3069, Email: <a href='' style='text-decoration: none; cursor: default; color: #555555;'>help@youtripper.com</a>"
                    + "<br/>"
                    + "<br/>"
                    + "</td>"
                    + "</tr>"
                    + "</table>"
                    + "</td>"
                    + "</tr>"
                    + "</table>"
                    + "</td>"
                    + "</tr>"
                    + "</table>"
                    + "</td>"
                    + "</tr>"
                    + "</table>"
                    + "</td>"
                    + "</tr>"
                    + "</table>"
                    + "</center>"
                    + "</td>"
                    + "</tr>"
                    + "</table>";

            JSONArray attachmentList = new JSONArray();

            if (attachments != null) {
                for (EmailAttachment attachment : attachments) {
                    Base64 x = new Base64();
                    byte[] bytes = attachment.getFile();
                    String encodedStringPDF = x.encodeAsString(bytes);
                    
                    String contentType = attachment.getContentType();
                    String attachmentName = attachment.getName();

                    attachmentList.put(new JSONObject()
                            .put("ContentType", contentType)
                            .put("Filename", attachmentName)
                            .put("Base64Content", encodedStringPDF));
                }
            }


            MailjetClient client = new MailjetClient("3d484a2b73e31e3de73b0e7de5da1e02", "4dbc16c53419f839b869e0f3d38f7250", new ClientOptions("v3.1"));
            MailjetRequest request = new MailjetRequest(Emailv31.resource)
                    .property(Emailv31.MESSAGES, new JSONArray()
                            .put(new JSONObject()
                                    .put(Emailv31.Message.FROM, new JSONObject()
                                            .put("Email", senderEmail)
                                            .put("Name", nickname))
                                    .put(Emailv31.Message.TO, new JSONArray()
                                            .put(new JSONObject()
                                                    .put("Email", receiverEmail)))
                                    .put(Emailv31.Message.SUBJECT, title)
                                    .put(Emailv31.Message.HTMLPART, emailContent)
                                    .put(Emailv31.Message.ATTACHMENTS, attachmentList)));
            MailjetResponse response = client.post(request);

            responseCode = response.getStatus();
        } catch (Exception e) {
            GlobalExceptionHandler.logEmailException(senderEmail, senderPassword, receiverEmail,
                    title, nickname, content, contentTitle, thankName, contactEmail, e);
        }
        return responseCode;
    }

}
