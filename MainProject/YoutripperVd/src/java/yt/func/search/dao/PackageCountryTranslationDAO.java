/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.func.search.dao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import yt.entity.tbl.PackageCountryTranslation;

/**
 *
 * @author Hiep
 */
public class PackageCountryTranslationDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }

    public List<PackageCountryTranslation> searchCountryByName(String keySearch) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(PackageCountryTranslation.class);
        keySearch = keySearch + "%";
        criteria.add(Restrictions.like("countryName", keySearch));

        ProjectionList pl = Projections.projectionList()
                .add(Projections.property("countryID")).add(Projections.property("countryName"));
        criteria.setProjection(pl);
        //
        List<Object[]> rows = criteria.list();
        List<PackageCountryTranslation> results = new ArrayList<>();
        for (Object[] row : rows) {
            String countryID = (String) row[0];
            String countryName = (String) row[1];
            // hottest deal
            PackageCountryTranslation aPc = new PackageCountryTranslation();
            aPc.setCountryID(countryID);
            aPc.setCountryName(countryName);
            results.add(aPc);
        }
        return results;
    }
}
