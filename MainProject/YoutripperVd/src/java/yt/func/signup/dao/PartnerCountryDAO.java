/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.func.signup.dao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import yt.entity.tbl.PartnerCountry;

/**
 *
 * @author nickn
 */
public class PartnerCountryDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }

    public List<PartnerCountry> getAllCountries() {
        //get session
        Session session = sessionFactory.getCurrentSession();
        Criteria cri = session.createCriteria(PartnerCountry.class);

        ProjectionList projectionList = Projections.projectionList()
                .add(Projections.property("countryID"))
                .add(Projections.property("countryName"))
                .add(Projections.property("phoneCode"));

        cri.setProjection(projectionList);
        List<Object[]> rows = cri.list();
        
        List<PartnerCountry> partnerCountries = new ArrayList<>();
        for (Object[] row : rows) {
            String countryID = (String) row[0];
            String countryName = (String) row[1];
            String phoneCode = (String) row[2];
            
            PartnerCountry aCountry = new PartnerCountry();
            aCountry.setCountryID(countryID);
            aCountry.setCountryName(countryName);
            aCountry.setPhoneCode(phoneCode);
            
            partnerCountries.add(aCountry);
        }

        return partnerCountries;
    }
}
