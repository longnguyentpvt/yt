/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.func.partnerbookinghistory.dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.beans.factory.annotation.Autowired;
import yt.entity.tbl.YTPackage;
import yt.entity.tbl.YTPackageContent;

/**
 *
 * @author Hiep
 */
public class YTPackageDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }

    public List<YTPackage> loadAllPackage(String partnerID) {
        //get session
        Session session = sessionFactory.getCurrentSession();
        //get total number of result
        Criteria cri = session.createCriteria(YTPackage.class, "ytp");
        cri.createAlias("ytp.contents", "ytpContent", JoinType.INNER_JOIN);
        cri.add(Restrictions.eq("ytp.partnerID", partnerID));
        //get default name
        cri.add(Restrictions.eq("ytpContent.defaultContent", Boolean.TRUE));
        // select clause
        cri.setProjection(Projections.projectionList()
                .add(Projections.property("ytp.packageID"))
                .add(Projections.property("ytpContent.packageName"))
                .add(Projections.property("ytp.servingType")));

        List<Object[]> rows = cri.list();
        List<YTPackage> results = new ArrayList<>();
        for (Object[] row : rows) {
            YTPackage ytp = new YTPackage();
            String packageID = (String) row[0];
            String packageName = (String) row[1];
            String servingType = (String) row[2];
            ytp.setPackageID(packageID);
            YTPackageContent packageContent = new YTPackageContent();
            packageContent.setPackageName(packageName);
            Set<YTPackageContent> contents = new HashSet<>();
            contents.add(packageContent);
            ytp.setContents(contents);
            ytp.setServingType(servingType);
            results.add(ytp);
        }
        return results;
    }

}
