/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.func.signup.service;

import javaclass.utility.EmailUtility;
import javaclass.utility.YTDateTimeUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yt.func.signup.SignupViewMapping;
import yt.func.signup.dao.YTEmailDAO;
import yt.globalexception.GlobalExceptionHandler;
import yt.javaclass.config.YTDataConfiguration;

/**
 *
 * @author nickn
 */
@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private YTEmailDAO signupYTEmailDAO;

    @Override
    @Transactional
    @Async
    public void sendPartnerAccountVerificationEmail(String displayName, String email, String token) {
        String nickname = "Youtripper Registration";
        String title = "Please verify your email";
        String contentTitle = "Thanks for Registration";
        String content = null;
        int responseCode = 0;
        boolean success = true;
        try {
            // build content
            content = "<tr>"
                    + "<td>"
                    + "Hi " + displayName + ","
                    + "<br/><br/>"
                    + "In order to get started, you need to verify your email address by clicking "
                    + "the following button. "
                    + "<br/><br/>"
                    + "</td>"
                    + "</tr>"
                    + "<tr>"
                    + "<td>"
                    + "<table width='100%' border='0' cellspacing='0' cellpadding='0' align='center'>"
                    + "<tr>"
                    + "<td align='center'>"
                    + "<table align='center' class='button'>"
                    + "<tr>"
                    + "<td align='center' bgColor='#E94848'>"
                    + "<a href='" + YTDataConfiguration.getYTDomain()
                    + SignupViewMapping.PARTNER_EMAIL_ACTIVATION_URL
                    + token
                    + "' style='FONT-SIZE: 14px; TEXT-DECORATION: none; BORDER-TOP: #E94848 1px solid;"
                    + "FONT-FAMILY: Helvetica, Arial, sans-serif; BORDER-RIGHT: #E94848 1px solid; BORDER-LEFT: #E94848 1px solid;"
                    + "BORDER-BOTTOM: #E94848 1px solid; FONT-WEIGHT: bold; COLOR: #ffffff; PADDING-BOTTOM: 10px;"
                    + "PADDING-TOP: 10px; PADDING-LEFT: 100px; DISPLAY: inline-block;"
                    + "LINE-HEIGHT: 20px; PADDING-RIGHT: 100px; -webkit-border-radius: 0px; -moz-border-radius: 0px; border-radius: 0px' target='_blank'>"
                    + "Verify"
                    + "</a>"
                    + "</td>"
                    + "</tr>"
                    + "</table>"
                    + "</td>"
                    + "</tr>"
                    + "</table>"
                    + "</td>"
                    + "</tr>";

            responseCode = EmailUtility.sendEmailByRegistrationEmail(email, title, nickname, content, contentTitle, null, null, null);
        } catch (Exception e) {
            GlobalExceptionHandler.logVerificationEmail(e, displayName, email, token);
            success = false;
        }
        signupYTEmailDAO.logPartnerVerficationEmail(email, title, content, success, YTDateTimeUtility.getCurrentTimeInMilli(), responseCode);
    }

}
