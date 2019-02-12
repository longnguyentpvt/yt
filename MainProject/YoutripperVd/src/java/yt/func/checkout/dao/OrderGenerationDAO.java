/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.func.checkout.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import yt.entity.tbl.OrderGeneration;

/**
 *
 * @author nickn
 */
public class OrderGenerationDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }

    public long getOrderID(String tsID) {
        Session session = sessionFactory.getCurrentSession();

        OrderGeneration o = new OrderGeneration();
        o.setTransactionID(tsID);
        session.persist(o);
        session.flush();

        return o.getOrderID();
    }
}
