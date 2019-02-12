/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.func.packagedetail.dao;

import java.math.BigDecimal;
import javaclass.common.ytpackage.YTPackageData;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.beans.factory.annotation.Autowired;
import yt.entity.tbl.YTPackage;
import yt.func.packagedetail.javaclass.PackageDetailDAORespone;

/**
 *
 * @author nickn
 */
public class YTPackageDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }

    public PackageDetailDAORespone getPackageDetail(String packageID, String locale, String currencyCode) {
        Session session = sessionFactory.getCurrentSession();

        Criteria cri = session.createCriteria(YTPackage.class, "pkg");
        cri.createAlias("pkg.prices", "pr", JoinType.INNER_JOIN);
        cri.createAlias("pkg.contents", "ct", JoinType.INNER_JOIN);

        cri.add(Restrictions.eq("pkg.packageID", packageID)).add(Restrictions.eq("ct.languageCode", locale))
                .add(Restrictions.eq("pr.currencyCode", currencyCode));

        ProjectionList pl = Projections.projectionList().add(Projections.property("pkg.servingType"))
                .add(Projections.property("ct.packageName")).add(Projections.property("pr.price")).add(Projections.property("pkg.packageStatus"));
        cri.setProjection(pl);

        Object[] row = (Object[]) cri.uniqueResult();
        PackageDetailDAORespone ytPackage = null;
        if (row != null) {
            String servingType = (String) row[0];
            String packageName = (String) row[1];
            BigDecimal price = (BigDecimal) row[2];
            String pkgStt = (String) row[3];
            
            ytPackage = new PackageDetailDAORespone(servingType, packageName, price, pkgStt);
        }
        return ytPackage;
    }
}
