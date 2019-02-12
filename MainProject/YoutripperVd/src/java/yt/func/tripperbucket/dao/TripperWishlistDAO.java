/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.func.tripperbucket.dao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.beans.factory.annotation.Autowired;
import yt.entity.tbl.TripperWishlist;
import yt.func.tripperbucket.javaclass.AdBucket;

/**
 *
 * @author Hiep
 */
public class TripperWishlistDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }

    public void insertToWishlist(TripperWishlist tripperWishlist) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(tripperWishlist);
    }

    public List<AdBucket> getBucketList(String tripperID) {
        //get session
        Session session = sessionFactory.getCurrentSession();
        //get total number of result
        Criteria cri = session.createCriteria(TripperWishlist.class, "bucket");
        cri.createAlias("bucket.ytPackage", "ytp", JoinType.INNER_JOIN);
        cri.createAlias("ytp.contents", "ytpContent", JoinType.INNER_JOIN);

        //get default name
        cri.add(Restrictions.eq("ytpContent.defaultContent", true));
        //PARTNER ID
        cri.add(Restrictions.eq("bucket.tripperID", tripperID));
        // select clause
        cri.setProjection(Projections.projectionList()
                .add(Projections.property("bucket.packageID"))
                .add(Projections.property("ytpContent.packageName"))
                .add(Projections.property("ytp.packageStatus")));
        cri.addOrder(Order.desc("bucket.wishlistDateTime"));

        List<Object[]> rows = cri.list();
        List<AdBucket> results = new ArrayList<>();
        for (Object[] row : rows) {
            String packageID = (String) row[0];
            String packageName = (String) row[1];
            String status = (String) row[2];
            //
            AdBucket aB = new AdBucket();
            aB.setPackageID(packageID);
            aB.setPackageName(packageName);
            aB.setStatus(status);
            results.add(aB);
        }
        return results;
    }

    public boolean removeBucketByID(String packageID, String tripperID) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "DELETE TripperWishlist "
                + "WHERE packageID = :packageID AND tripperID =:tripperID";
        Query query = session.createQuery(hql);
        query.setParameter("packageID", packageID);
        query.setParameter("tripperID", tripperID);
        int noR = query.executeUpdate();
        return noR > 0;
    }

    public boolean checkExist(String tripperID, String packageID) {
        //get session
        Session session = sessionFactory.getCurrentSession();
        Criteria cri = session.createCriteria(TripperWishlist.class, "bucket");
        //PARTNER ID PACKAGE ID
        cri.add(Restrictions.eq("bucket.tripperID", tripperID));
        cri.add(Restrictions.eq("bucket.packageID", packageID));
        cri.setProjection(Projections.projectionList().add(Projections.property("bucket.packageID")));
        //
        String rtPkgID = (String) cri.uniqueResult();
        return rtPkgID != null;
    }
}
