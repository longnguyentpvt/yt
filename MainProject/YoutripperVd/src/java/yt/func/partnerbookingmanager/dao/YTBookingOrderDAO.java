/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.func.partnerbookingmanager.dao;

import java.util.ArrayList;
import java.util.List;
import javaclass.common.ytpackage.YTPackageData;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.beans.factory.annotation.Autowired;
import yt.entity.tbl.YTBookingOrder;
import yt.func.partnerbookingmanager.javaclass.AdOpenBookingOrder;
import yt.func.partnerbookingmanager.javaclass.AdRegularBookingOrder;

/**
 *
 * @author Hiep
 */
public class YTBookingOrderDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }

    public List<AdRegularBookingOrder> loadDataForBookingManagementRegular(String partnerID, Boolean channel, String packageID, String text_search, Long startDate,
            Long endDate, int skipRows, int limit) {
        //get session
        Session session = sessionFactory.getCurrentSession();
        //get total number of result
        Criteria cri = session.createCriteria(YTBookingOrder.class, "bookingOrder");
        cri.createAlias("bookingOrder.ytPackage", "ytp", JoinType.INNER_JOIN);
        cri.createAlias("ytp.contents", "ytpContent", JoinType.INNER_JOIN);
        //get default name
        cri.add(Restrictions.eq("ytpContent.defaultContent", true));
        // Regular Served
        cri.add(Restrictions.eq("bookingOrder.rpServed", false));
        //PARTNER ID
        cri.add(Restrictions.eq("ytp.partnerID", partnerID));
        // serving type
        cri.add(Restrictions.or(Restrictions.eq("ytp.servingType", YTPackageData.SERVING_TYPE_PUBLIC),
                Restrictions.or(Restrictions.eq("ytp.servingType", YTPackageData.SERVING_TYPE_GROUP),
                        Restrictions.eq("ytp.servingType", YTPackageData.SERVING_TYPE_PRIVATE))));

        //CHANNEL CONDITION
        if (channel != null) {
            cri.add(Restrictions.eq("bookingOrder.offlineBooking", channel));
        }
        // PACKAGE ID CONDITION
        if (!packageID.equals("all")) {
            cri.add(Restrictions.eq("ytp.packageID", packageID));
        }
        // SEARCH TEXT CONDITION
        if (text_search != null) {
            cri.add(Restrictions.eq("bookingOrder.orderNo", text_search));
        }
        // DATE FILTER
        if (startDate != null) {
            cri.add(Restrictions.ge("bookingOrder.tripDate", startDate));
        }

        if (endDate != null) {
            cri.add(Restrictions.le("bookingOrder.tripDate", endDate));
        }

        //get total rsults
        cri.setProjection(Projections.rowCount());
        Long count = (Long) cri.uniqueResult();
        // select clause
        cri.setProjection(Projections.projectionList()
                .add(Projections.property("bookingOrder.orderNo")).add(Projections.property("bookingOrder.tripDate"))
                .add(Projections.property("bookingOrder.tripTime")).add(Projections.property("bookingOrder.durationType"))
                .add(Projections.property("bookingOrder.businessDuration")).add(Projections.property("bookingOrder.billingFirstName"))
                .add(Projections.property("bookingOrder.billingLastName")).add(Projections.property("ytpContent.packageName"))
                .add(Projections.property("bookingOrder.usedQuota")).add(Projections.property("bookingOrder.offlineBooking"))
                .add(Projections.property("ytp.servingType")).add(Projections.property("bookingOrder.tripperAbsence"))
        );
        cri.addOrder(Order.desc("bookingOrder.tripDate"));
        cri.addOrder(Order.desc("bookingOrder.tripTime"));
        cri.addOrder(Order.desc("bookingOrder.orderNo"));
        cri.addOrder(Order.desc("ytp.packageID"));
        cri.addOrder(Order.desc("bookingOrder.bookingDateTime"));
        cri.setFirstResult(skipRows).setMaxResults(limit);
        List<Object[]> rows = cri.list();
        List<AdRegularBookingOrder> results = new ArrayList<>();
        for (Object[] row : rows) {
            String bookingNo = (String) row[0];
            Long tripDate = (Long) row[1];
            Integer startTime = (Integer) row[2];
            String durationType = (String) row[3];
            Integer duration = (Integer) row[4];
            String billingFirstName = (String) row[5];
            String billingLastName = (String) row[6];
            String packageName = (String) row[7];
            Integer bookedQty = (Integer) row[8];
            Boolean offlineBooking = (Boolean) row[9];
            String servingTypeR = (String) row[10];
            Boolean tripperAbsence = (Boolean) row[11];

            AdRegularBookingOrder upcomingBooking = new AdRegularBookingOrder();
            upcomingBooking.setBookingNo(bookingNo);
            upcomingBooking.setTripDate(tripDate);
            upcomingBooking.setStartTime(startTime);
            upcomingBooking.setDurationType(durationType);
            upcomingBooking.setDuration(duration);
            upcomingBooking.setBillingFirstName(billingFirstName);
            upcomingBooking.setBillingLastName(billingLastName);
            upcomingBooking.setPackageName(packageName);
            upcomingBooking.setBookedQty(bookedQty);
            upcomingBooking.setOfflineBooking(offlineBooking);
            upcomingBooking.setTotalResults(count);
            upcomingBooking.setServingType(servingTypeR);
            upcomingBooking.setTripperAbsence(tripperAbsence);
            results.add(upcomingBooking);
        }
        return results;
    }

    public List<AdOpenBookingOrder> loadDataForBookingManagementOpened(String partnerID, Boolean channel, String packageID, String text_search,
            Long startDate, Long endDate, int skipRows, int limit, boolean isNoExpire) {
        //get session
        Session session = sessionFactory.getCurrentSession();

        //get total number of result
        Criteria cri = session.createCriteria(YTBookingOrder.class, "bookingOrder");
        cri.createAlias("bookingOrder.ytPackage", "ytp", JoinType.INNER_JOIN);
        cri.createAlias("ytp.contents", "ytpContent", JoinType.INNER_JOIN);

        //get default name
        cri.add(Restrictions.eq("ytpContent.defaultContent", true));
        cri.add(Restrictions.eq("ytp.partnerID", partnerID));
        // serving type
        cri.add(Restrictions.eq("ytp.servingType", YTPackageData.SERVING_TYPE_OPEN_TIMED));

        cri.add(Restrictions.eq("bookingOrder.otExpired", false));
        if (channel != null) {
            cri.add(Restrictions.eq("bookingOrder.offlineBooking", channel));
        }

        if (!packageID.equals("all")) {
            cri.add(Restrictions.eq("ytp.packageID", packageID));
        }

        if (text_search != null) {
            cri.add(Restrictions.eq("bookingOrder.orderNo", text_search));
        }

        if (startDate != null) {
            cri.add(Restrictions.ge("bookingOrder.expirationDate", startDate));
        }
        if (endDate != null) {
            cri.add(Restrictions.le("bookingOrder.expirationDate", endDate));
        }
        
        // no expire date
        if (isNoExpire) {
            cri.add(Restrictions.eq("bookingOrder.expirationDate", YTPackageData.UNLIMIT_EXPIRE_DATE));
        }

        //get total rsults
        cri.setProjection(Projections.rowCount());
        Long count = (Long) cri.uniqueResult();
        // select clause
        cri.setProjection(Projections.projectionList()
                .add(Projections.property("bookingOrder.orderNo")).add(Projections.property("bookingOrder.expirationDate"))
                .add(Projections.property("bookingOrder.tripTime")).add(Projections.property("bookingOrder.durationType"))
                .add(Projections.property("bookingOrder.businessDuration")).add(Projections.property("bookingOrder.billingFirstName"))
                .add(Projections.property("bookingOrder.billingLastName")).add(Projections.property("ytpContent.packageName"))
                .add(Projections.property("bookingOrder.usedQuota")).add(Projections.property("bookingOrder.offlineBooking"))
                .add(Projections.property("ytp.servingType")).add(Projections.property("bookingOrder.otServingQTY"))
                .add(Projections.property("bookingOrder.otServedQTY"))
        );
        cri.addOrder(Order.desc("bookingOrder.tripDate"));
        cri.addOrder(Order.desc("bookingOrder.tripTime"));
        cri.addOrder(Order.desc("bookingOrder.orderNo"));
        cri.addOrder(Order.desc("ytp.packageID"));
        cri.addOrder(Order.desc("bookingOrder.bookingDateTime"));

        cri.setFirstResult(skipRows).setMaxResults(limit);
        List<Object[]> rows = cri.list();
        List<AdOpenBookingOrder> results = new ArrayList<>();
        for (Object[] row : rows) {
            String bookingNo = (String) row[0];
            Long expirationDate = (Long) row[1];
            Integer startTime = (Integer) row[2];
            String durationType = (String) row[3];
            Integer duration = (Integer) row[4];
            String billingFirstName = (String) row[5];
            String billingLastName = (String) row[6];
            String packageName = (String) row[7];
            Integer bookedQty = (Integer) row[8];
            
            boolean offlineBooking = (boolean) row[9];
            String servingTypeR = (String) row[10];
            Integer otServingQTY = (Integer) row[11];
            Integer otServedQTY = (Integer) row[12];

            AdOpenBookingOrder upcomingBooking = new AdOpenBookingOrder();
            upcomingBooking.setBookingNo(bookingNo);
            upcomingBooking.setExpirationDate(expirationDate);
            upcomingBooking.setStartTime(startTime);
            upcomingBooking.setDurationType(durationType);
            upcomingBooking.setDuration(duration);
            upcomingBooking.setBillingFirstName(billingFirstName);
            upcomingBooking.setBillingLastName(billingLastName);
            upcomingBooking.setPackageName(packageName);
            upcomingBooking.setBookedQty(bookedQty);
            upcomingBooking.setOfflineBooking(offlineBooking);
            upcomingBooking.setTotalResults(count);
            upcomingBooking.setServingType(servingTypeR);
            upcomingBooking.setOtServingQTY(otServingQTY);
            upcomingBooking.setOtServedQTY(otServedQTY);
            results.add(upcomingBooking);
        }
        return results;
    }
}
