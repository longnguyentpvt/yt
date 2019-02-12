/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.func.javacriptLog.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import yt.entity.tbl.JavascriptLog;

/**
 *
 * @author Hiep
 */
public class JavascriptLogDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }

    public void insertToWishlist(JavascriptLog javascriptLog) {
        Session session = sessionFactory.getCurrentSession();
        session.save(javascriptLog);
    }
}
