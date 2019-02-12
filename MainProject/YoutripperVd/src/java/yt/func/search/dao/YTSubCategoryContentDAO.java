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
import org.hibernate.sql.JoinType;
import org.springframework.beans.factory.annotation.Autowired;
import yt.entity.tbl.YTSubCategoryContent;
import yt.func.search.javaclass.SubCategorySuggestionDTO;

/**
 *
 * @author Hiep
 */
public class YTSubCategoryContentDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }

    public List<SubCategorySuggestionDTO> searchSubCategoryByName(String keySearch, String languageCode) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(YTSubCategoryContent.class, "subContent");
        criteria.createAlias("subContent.ytSubCategory", "ytSubCategory", JoinType.INNER_JOIN);
        criteria.createAlias("ytSubCategory.ytCategory", "ytCategory", JoinType.INNER_JOIN);
        criteria.createAlias("ytCategory.contents", "content", JoinType.INNER_JOIN);
        keySearch = keySearch + "%";
        criteria.add(Restrictions.like("subContent.subCategoryName", keySearch));
        criteria.add(Restrictions.like("content.languageCode", languageCode));

        ProjectionList pl = Projections.projectionList()
                .add(Projections.property("subContent.subCategoryID")).add(Projections.property("subContent.subCategoryName"))
                .add(Projections.property("content.categoryName"));
        criteria.setProjection(pl);
        //
        List<Object[]> rows = criteria.list();
        List<SubCategorySuggestionDTO> results = new ArrayList<>();
        for (Object[] row : rows) {
            String subCategoryID = (String) row[0];
            String subCategoryName = (String) row[1];
            String categoryName = (String) row[2];
            // hottest deal
            SubCategorySuggestionDTO aSc = new SubCategorySuggestionDTO();
            aSc.setSubCategoryID(subCategoryID);
            aSc.setSubCategoryName(subCategoryName);
            aSc.setCategoryName(categoryName);
            results.add(aSc);
        }
        return results;
    }
}
