/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.func.signup.dao;

import javaclass.common.YTTripperData;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import yt.entity.tbl.Tripper;
import yt.entity.view.TripperActivation;
import yt.entity.view.TripperExistEmail;

/**
 *
 * @author nickn
 */
public class TripperDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }

    public boolean checkEmailExists(String email) {
        //get session
        Session session = sessionFactory.getCurrentSession();
        Criteria cri = session.createCriteria(TripperExistEmail.class);

        cri.add(Restrictions.eq("email", email));
        cri.setProjection(Projections.property("tripperID"));

        String tripperID = (String) cri.uniqueResult();

        return tripperID != null;
    }

    public String registerNewTripper(String tripperID, String email, String password, long registrationDateTime,
            String accountType, String accountStatus, String facebookID, String googleID, String linepayID) {
        Session session = sessionFactory.getCurrentSession();
        Criteria cri = session.createCriteria(Tripper.class);
        cri.setProjection(Projections.rowCount());

        if (tripperID != null) {
            Tripper tripper = new Tripper();
            tripper.setTripperID(tripperID);
            tripper.setEmail(email);
            tripper.setPassword(password);
            tripper.setRegistrationDateTime(registrationDateTime);
            tripper.setAccountType(accountType);
            tripper.setAccountStatus(accountStatus);
            tripper.setFacebookID(facebookID);
            tripper.setGoogleID(googleID);
            tripper.setLineID(linepayID);

            session.persist(tripper);
            session.flush();

            return tripperID;
        }
        return tripperID;
    }

    public TripperActivation getTripperActivationInfoByToken(String registrationToken) {
        Session session = sessionFactory.getCurrentSession();
        Criteria cri = session.createCriteria(TripperActivation.class);

        cri.add(Restrictions.eq("registrationToken", registrationToken));

        return (TripperActivation) cri.uniqueResult();
    }

    public void activateTripperAccount(String tripperID) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "UPDATE Tripper "
                + "SET accountStatus = :accountStatus "
                + "WHERE tripperID = :tripperID";

        Query query = session.createQuery(hql);
        query.setParameter("tripperID", tripperID);
        query.setParameter("accountStatus", YTTripperData.ACCOUNT_STATUS_ACTIVED);

        query.executeUpdate();
    }
}
