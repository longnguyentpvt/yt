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
import yt.entity.tbl.Partner;

/**
 *
 * @author nickn
 */
public class PartnerDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }

    public Partner getParterLoginInfoByEmail(String email, String password) {
        Session session = sessionFactory.getCurrentSession();
        Criteria cri = session.createCriteria(Partner.class);

        cri.add(Restrictions.eq("email", email)).add(Restrictions.eq("password", password));

        ProjectionList projectionList = Projections.projectionList()
                .add(Projections.property("partnerID")).add(Projections.property("smallAvatar"))
                .add(Projections.property("displayName")).add(Projections.property("accountStatus"));

        cri.setProjection(projectionList);

        Object[] row = (Object[]) cri.uniqueResult();
        if (row != null) {
            String partnerID = (String) row[0];
            String smallAvatar = (String) row[1];
            String displayName = (String) row[2];
            String accountStatus = (String) row[3];

            Partner partner = new Partner();
            partner.setPartnerID(partnerID);
            partner.setSmallAvatar(smallAvatar);
            partner.setDisplayName(displayName);
            partner.setAccountStatus(accountStatus);

            return partner;
        }
        return null;
    }

}
