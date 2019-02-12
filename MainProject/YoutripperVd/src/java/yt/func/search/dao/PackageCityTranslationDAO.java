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
import yt.entity.tbl.PackageCityTranslation;
import yt.entity.tbl.PackageCountryTranslation;

/**
 *
 * @author Hiep
 */
public class PackageCityTranslationDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }

    public List<PackageCityTranslation> searchCityByName(String keySearch) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(PackageCityTranslation.class);
        keySearch = keySearch + "%";
        criteria.add(Restrictions.like("cityName", keySearch));

        ProjectionList pl = Projections.projectionList()
                .add(Projections.property("cityID")).add(Projections.property("cityName"));
        criteria.setProjection(pl);
        //
        List<Object[]> rows = criteria.list();
        List<PackageCityTranslation> results = new ArrayList<>();
        for (Object[] row : rows) {
            String cityID = (String) row[0];
            String cityName = (String) row[1];
            // hottest deal
            PackageCityTranslation aPc = new PackageCityTranslation();
            aPc.setCityID(cityID);
            aPc.setCityName(cityName);
            results.add(aPc);
        }
        return results;
    }

}
