/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.func.signup.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import yt.entity.tbl.PartnerRegistrationNumber;

/**
 *
 * @author nickn
 */
public class PartnerRegistrationNumberDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }
    
    public long registerPartnerNumber(long registraitonDateTime) {
         //get session
        Session session = sessionFactory.getCurrentSession();
        PartnerRegistrationNumber partner = new PartnerRegistrationNumber();
        partner.setRegistrationDateTime(registraitonDateTime);
        
        session.persist(partner);
        return partner.getPartnerNo();
    }
}
