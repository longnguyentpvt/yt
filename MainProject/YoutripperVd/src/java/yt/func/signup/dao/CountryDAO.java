/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.func.signup.dao;

import java.util.ArrayList;
import java.util.List;
import javaclass.common.YTData;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import yt.entity.tbl.Country;

/**
 *
 * @author nickn
 */
public class CountryDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }

    public List<Country> getAllCountries() {
        //get session
        Session session = sessionFactory.getCurrentSession();
        Criteria cri = session.createCriteria(Country.class);

        cri.add(Restrictions.ne("countryID", YTData.DELETED_COUNTRY_ID));

        ProjectionList projectionList = Projections.projectionList()
                .add(Projections.property("countryID"))
                .add(Projections.property("countryName"));

        cri.setProjection(projectionList);
        List<Object[]> rows = cri.list();

        List<Country> countries = new ArrayList<>();
        for (Object[] row : rows) {
            String countryID = (String) row[0];
            String countryName = (String) row[1];

            Country aCountry = new Country();
            aCountry.setCountryID(countryID);
            aCountry.setCountryName(countryName);

            countries.add(aCountry);
        }

        return countries;
    }
}
