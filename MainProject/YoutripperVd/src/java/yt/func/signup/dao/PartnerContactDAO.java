/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.func.signup.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import yt.entity.tbl.PartnerContact;

/**
 *
 * @author IDD_LENOVO
 */
public class PartnerContactDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }

    public boolean checkPartnerContactExists(String email) {
        //get session
        Session session = sessionFactory.getCurrentSession();
        Criteria cri = session.createCriteria(PartnerContact.class);

        cri.add(Restrictions.eq("email", email));
        cri.setProjection(Projections.property("partnerID"));

        String partnerID = (String) cri.uniqueResult();

        return partnerID != null;
    }

    public void addNewPartnerContact(String email, long currentTime) {
        Session session = sessionFactory.getCurrentSession();
        PartnerContact newContact = new PartnerContact();
        newContact.setEmail(email);
        newContact.setSignupTime(currentTime);
        session.persist(newContact);
    }
}
