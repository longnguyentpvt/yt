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
import yt.entity.view.KeywordSearchSuggestion;

/**
 *
 * @author Hiep
 */
public class YTPackageKeywordDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }

    public List<KeywordSearchSuggestion> getKeywordSuggest(String keySearch, String languageCode) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(KeywordSearchSuggestion.class);
        keySearch = keySearch + "%";
        criteria.add(Restrictions.like("keyword", keySearch));
        criteria.add(Restrictions.like("languageCode", languageCode));

        ProjectionList pl = Projections.projectionList()
                .add(Projections.property("keyword")).add(Projections.property("categoryID"))
                .add(Projections.property("categoryName")).add(Projections.property("noFound"));
        criteria.setProjection(pl);
        //
        List<Object[]> rows = criteria.list();
        List<KeywordSearchSuggestion> results = new ArrayList<>();
        for (Object[] row : rows) {
            String keyword = (String) row[0];
            String categoryID = (String) row[1];
            String categoryName = (String) row[2];
            Long noFound = (Long) row[3];
            // hottest deal
            KeywordSearchSuggestion aPc = new KeywordSearchSuggestion();
            aPc.setKeyword(keyword);
            aPc.setCategoryID(categoryID);
            aPc.setCategoryName(categoryName);
            aPc.setNoFound(noFound);
            results.add(aPc);
        }
        return results;
    }
}
