/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.func.signup.dao;

import javaclass.common.YTData;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import yt.entity.tbl.YTEmail;

/**
 *
 * @author nickn
 */
public class YTEmailDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }

    public void logTripperVerficationEmail(String receiverEmail, String emailTitle, String emailContent,
            boolean emailSent, long executionDateTime, int responseCode) {
        Session session = sessionFactory.getCurrentSession();

        YTEmail ytEmail = new YTEmail(YTData.TRIPPER_VERIFICATION_EMAIL_FUNCTION_CODE, receiverEmail, emailTitle, emailContent, emailSent, executionDateTime, responseCode);

        session.persist(ytEmail);
        session.flush();
    }

    public void logPartnerVerficationEmail(String receiverEmail, String emailTitle, String emailContent,
            boolean emailSent, long executionDateTime, int responseCode) {
        Session session = sessionFactory.getCurrentSession();

        YTEmail ytEmail = new YTEmail(YTData.PARTNER_VERIFICATION_EMAIL_FUNCTION_CODE, receiverEmail, emailTitle, emailContent, emailSent, executionDateTime, responseCode);

        session.persist(ytEmail);
        session.flush();
    }
}
