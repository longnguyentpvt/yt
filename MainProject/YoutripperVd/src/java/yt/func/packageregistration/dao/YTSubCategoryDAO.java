/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.func.packageregistration.dao;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import yt.entity.tbl.YTSubCategory;

/**
 *
 * @author nickn
 */
public class YTSubCategoryDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }

    public List<String[]> getSubCategoryByCategory(String categoryID, String languageCode) {
        Session session = sessionFactory.getCurrentSession();
        Criteria cri = session.createCriteria(YTSubCategory.class, "s");
        cri.createAlias("s.contents", "c");

        cri.add(Restrictions.eq("s.categoryID", categoryID)).add(Restrictions.eq("c.languageCode", languageCode));

        ProjectionList projectionList = Projections.projectionList().add(Projections.property("s.subCategoryID"))
                .add(Projections.property("c.subCategoryName"));

        cri.setProjection(projectionList);

        List<Object[]> rows = cri.list();

        List<String[]> subCategories = new ArrayList<>();
        for (Object[] row : rows) {
            String subCategoryID = (String) row[0];
            String subCategoryName = (String) row[1];

            String[] aSub = {subCategoryID, subCategoryName};
            subCategories.add(aSub);
        }
        return subCategories;
    }
}
