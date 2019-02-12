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
import yt.entity.tbl.YTCategoryContent;
import yt.entity.view.KeywordSearchSuggestion;

/**
 *
 * @author Hiep
 */
public class YTCategoryDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }

    public List<YTCategoryContent> getCategoryByName(String keySearch) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(YTCategoryContent.class);
        keySearch = keySearch + "%";
        criteria.add(Restrictions.like("categoryName", keySearch));

        ProjectionList pl = Projections.projectionList()
                .add(Projections.property("categoryID")).add(Projections.property("categoryName"));
        criteria.setProjection(pl);
        //
        List<Object[]> rows = criteria.list();
        List<YTCategoryContent> results = new ArrayList<>();
        for (Object[] row : rows) {
            String categoryID = (String) row[0];
            String categoryName = (String) row[1];
            // hottest deal
            YTCategoryContent aPc = new YTCategoryContent();
            aPc.setCategoryID(categoryID);
            aPc.setCategoryName(categoryName);
            results.add(aPc);
        }
        return results;
    }

}
