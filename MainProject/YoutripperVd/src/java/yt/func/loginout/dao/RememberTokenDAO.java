/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.func.loginout.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import yt.entity.tbl.RememberLoginToken;
import yt.entity.view.RememberLoginInfo;

/**
 *
 * @author nickn
 */
public class RememberTokenDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }

    public void generateNewToken(String rememberToken, String partnerID, String tripperID, long aliveTime) {
        Session session = sessionFactory.getCurrentSession();

        RememberLoginToken remember = new RememberLoginToken();
        remember.setRememberToken(rememberToken);
        remember.setPartnerID(partnerID);
        remember.setTripperID(tripperID);
        remember.setAliveTime(aliveTime);

        session.persist(remember);
    }

    public RememberLoginInfo getLoginInfo(String rememberToken) {
        Session session = sessionFactory.getCurrentSession();
        Criteria cri = session.createCriteria(RememberLoginInfo.class);
        
        cri.add(Restrictions.eq("rememberToken", rememberToken));
        
        return (RememberLoginInfo) cri.uniqueResult();
    }
}
