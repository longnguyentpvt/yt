/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.func.partnertrippdash.dao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import yt.entity.tbl.YTPackage;

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

    public List<YTPackage> getNumberPackage(String partnerID) {
        Session session = sessionFactory.getCurrentSession();
        Criteria cri = session.createCriteria(YTPackage.class);
        //condition
        cri.add(Restrictions.eq("partnerID", partnerID));
        //
        ProjectionList projectionList = Projections.projectionList().add(Projections.property("servingType"))
                .add(Projections.property("packageStatus"));
        cri.setProjection(projectionList);
        //
        List<Object[]> rows = cri.list();
        List<YTPackage> yTPackages = new ArrayList<>();
        for (Object[] row : rows) {
            YTPackage pkg = new YTPackage();
            String servingType = (String) row[0];
            String packageStatus = (String) row[1];
            pkg.setServingType(servingType);
            pkg.setPackageStatus(packageStatus);
            yTPackages.add(pkg);
        }
        return yTPackages;
    }
}
