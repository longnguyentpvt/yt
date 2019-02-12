/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.func.loginout.dao;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import yt.entity.tbl.LineLoginTransaction;

/**
 *
 * @author nickn
 */
public class LineLoginTransactionDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }

    public void generateLoginTransaction(String tid, long time, boolean completed, String rurl) {
        Session session = sessionFactory.getCurrentSession();
        LineLoginTransaction ts = new LineLoginTransaction();
        ts.setTransactionID(tid);
        ts.setTransactionDateTime(time);
        ts.setCompleted(completed);
        ts.setDirectURL(rurl);
        session.persist(ts);
    }

    public String completeTransaction(String tid) {
        Session session = sessionFactory.getCurrentSession();
        Criteria cri = session.createCriteria(LineLoginTransaction.class);
        cri.add(Restrictions.eq("transactionID", tid));

        LineLoginTransaction ts = (LineLoginTransaction) cri.uniqueResult();
        String directURL = ts.getDirectURL();
        ts.setCompleted(true);

        return directURL;
    }
}
