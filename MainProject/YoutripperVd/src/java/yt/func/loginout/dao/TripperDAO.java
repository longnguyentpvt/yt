/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.func.loginout.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import yt.entity.tbl.Tripper;
import yt.entity.view.TripperFacebookLoginInfo;
import yt.entity.view.TripperGoogleLoginInfo;
import yt.entity.view.TripperLinepayLoginInfo;

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

    public Tripper getTripperLoginInfoByEmail(String email, String password) {
        Session session = sessionFactory.getCurrentSession();
        Criteria cri = session.createCriteria(Tripper.class);

        cri.add(Restrictions.eq("email", email)).add(Restrictions.eq("password", password));

        ProjectionList projectionList = Projections.projectionList()
                .add(Projections.property("tripperID")).add(Projections.property("smallAvatar"))
                .add(Projections.property("displayName")).add(Projections.property("accountStatus"));

        cri.setProjection(projectionList);

        Object[] row = (Object[]) cri.uniqueResult();
        if (row != null) {
            String tripperID = (String) row[0];
            String smallAvatar = (String) row[1];
            String displayName = (String) row[2];
            String accountStatus = (String) row[3];

            Tripper tripper = new Tripper();
            tripper.setTripperID(tripperID);
            tripper.setSmallAvatar(smallAvatar);
            tripper.setDisplayName(displayName);
            tripper.setAccountStatus(accountStatus);
            return tripper;
        }
        return null;
    }

    public TripperGoogleLoginInfo getGoogleLoginInfo(String googleID) {
        Session session = sessionFactory.getCurrentSession();
        Criteria cri = session.createCriteria(TripperGoogleLoginInfo.class);

        cri.add(Restrictions.eq("googleID", googleID));

        return (TripperGoogleLoginInfo) cri.uniqueResult();
    }

    public TripperFacebookLoginInfo getFacebookLoginInfo(String facebookID) {
        Session session = sessionFactory.getCurrentSession();
        Criteria cri = session.createCriteria(TripperFacebookLoginInfo.class);

        cri.add(Restrictions.eq("facebookID", facebookID));

        return (TripperFacebookLoginInfo) cri.uniqueResult();
    }

    public TripperLinepayLoginInfo getLinepayLoginInfo(String lineID) {
        Session session = sessionFactory.getCurrentSession();
        Criteria cri = session.createCriteria(TripperLinepayLoginInfo.class);

        cri.add(Restrictions.eq("lineID", lineID));

        return (TripperLinepayLoginInfo) cri.uniqueResult();
    }
}
