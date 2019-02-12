/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.func.signup.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import yt.entity.tbl.TripperRegistrationNumber;

/**
 *
 * @author nickn
 */
public class TripperRegistrationNumberDAO {
    
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }
    
    public long registerTripperNumber(long registraitonDateTime) {
         //get session
        Session session = sessionFactory.getCurrentSession();
        TripperRegistrationNumber tripper = new TripperRegistrationNumber();
        tripper.setRegistrationDateTime(registraitonDateTime);
        
        session.persist(tripper);
        return tripper.getTripperNo();
    }
}
