/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.func.partnerbookinghistory.dao;

import java.math.BigDecimal;
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
import yt.func.partnerbookinghistory.javaclass.AdHistoryOpenBookingOrder;
import yt.func.partnerbookinghistory.javaclass.AdHistoryRegularBookingOrder;

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

    public List<AdHistoryOpenBookingOrder> loadDataForBookingHistoryOpened(String partnerID, Boolean channel, String packageID,
            String text_search, Long startDate, Long endDate, int skipRows, int limit, boolean reviewed) {
        //get session
        Session session = sessionFactory.getCurrentSession();
        //get total number of result
        Criteria cri = session.createCriteria(YTBookingOrder.class, "bookingOrder");
        cri.createAlias("bookingOrder.ytPackage", "ytp", JoinType.INNER_JOIN);
        cri.createAlias("ytp.contents", "ytpContent", JoinType.LEFT_OUTER_JOIN);
        cri.createAlias("bookingOrder.ytPackageReview", "review", JoinType.LEFT_OUTER_JOIN);
        //get default name
        cri.add(Restrictions.eq("ytpContent.defaultContent", Boolean.TRUE));
        //PARTNER ID
        cri.add(Restrictions.eq("ytp.partnerID", partnerID));
        // serving type
        cri.add(Restrictions.eq("ytp.servingType", YTPackageData.SERVING_TYPE_OPEN_TIMED));
        //CHANNEL CONDITION
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

        // REVIEW FILTER
        cri.add(Restrictions.eq("review.reviewed", reviewed));
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
                .add(Projections.property("bookingOrder.otServedQTY")).add(Projections.property("bookingOrder.tripperAbsence"))
                .add(Projections.property("bookingOrder.currencyCode")).add(Projections.property("bookingOrder.total"))
                .add(Projections.property("bookingOrder.reviewToken")).add(Projections.property("review.reviewed"))
                .add(Projections.property("review.packageRate")).add(Projections.property("bookingOrder.otExpiredDate"))
        );
        cri.addOrder(Order.desc("bookingOrder.tripDate"));
        cri.addOrder(Order.desc("bookingOrder.tripTime"));
        cri.addOrder(Order.desc("bookingOrder.orderNo"));
        cri.addOrder(Order.desc("ytp.packageID"));
        cri.addOrder(Order.desc("bookingOrder.bookingDateTime"));
        cri.setFirstResult(skipRows).setMaxResults(limit);
        List<Object[]> rows = cri.list();
        List<AdHistoryOpenBookingOrder> results = new ArrayList<>();
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
            Boolean offlineBooking = (Boolean) row[9];
            String servingTypeR = (String) row[10];
            Integer otServingQTY = (Integer) row[11];
            Integer otServedQTY = (Integer) row[12];
            Boolean tripperAbsence = (Boolean) row[13];
            String currencyCode = (String) row[14];
            BigDecimal total = (BigDecimal) row[15];
            String reviewToken = (String) row[16];
            Boolean reviewedR = (Boolean) row[17];
            Integer packageRate = (Integer) row[18];
            Long otExpiredDate = (Long) row[19];

            AdHistoryOpenBookingOrder histo = new AdHistoryOpenBookingOrder();
            histo.setBookingNo(bookingNo);
            histo.setExpirationDate(expirationDate);
            histo.setStartTime(startTime);
            histo.setDurationType(durationType);
            histo.setDuration(duration);
            histo.setBillingFirstName(billingFirstName);
            histo.setBillingLastName(billingLastName);
            histo.setPackageName(packageName);
            histo.setBookedQty(bookedQty);
            histo.setOfflineBooking(offlineBooking);
            histo.setTotalResults(count);
            histo.setServingType(servingTypeR);
            histo.setOtServingQTY(otServingQTY);
            histo.setOtServedQTY(otServedQTY);
            histo.setTripperAbsence(tripperAbsence);
            histo.setCurrencyCode(currencyCode);
            histo.setTotal(total);
            histo.setReviewToken(reviewToken);
            histo.setReviewed(reviewedR);
            histo.setPackageRate(packageRate);
            histo.setOtExpiredDate(otExpiredDate);
            results.add(histo);
        }
        return results;
    }

    public List<AdHistoryRegularBookingOrder> loadDataForBookingHistoryRegular(String partnerID, Boolean channel,
            String packageID, String text_search, Long startDate, Long endDate, int skipRows, int limit, boolean reviewed) {
        //get session
        Session session = sessionFactory.getCurrentSession();
        //get total number of result
        Criteria cri = session.createCriteria(YTBookingOrder.class, "bookingOrder");
        cri.createAlias("bookingOrder.ytPackage", "ytp", JoinType.INNER_JOIN);
        cri.createAlias("ytp.contents", "ytpContent", JoinType.INNER_JOIN);
        cri.createAlias("bookingOrder.ytPackageReview", "review", JoinType.LEFT_OUTER_JOIN);
        //get default name
        cri.add(Restrictions.eq("ytpContent.defaultContent", Boolean.TRUE));
        // Regular Served
        cri.add(Restrictions.eq("bookingOrder.rpServed", Boolean.TRUE));
        // PARTNER ID
        cri.add(Restrictions.eq("ytp.partnerID", partnerID));
        // serving type
        cri.add(Restrictions.or(Restrictions.eq("ytp.servingType", YTPackageData.SERVING_TYPE_PUBLIC),
                Restrictions.or(Restrictions.eq("ytp.servingType", YTPackageData.SERVING_TYPE_GROUP),
                        Restrictions.eq("ytp.servingType", YTPackageData.SERVING_TYPE_PRIVATE))));

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

        // REVIEW FILTER
        cri.add(Restrictions.eq("review.reviewed", reviewed));

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
                .add(Projections.property("bookingOrder.currencyCode")).add(Projections.property("bookingOrder.total"))
                .add(Projections.property("bookingOrder.reviewToken")).add(Projections.property("review.reviewed"))
                .add(Projections.property("review.packageRate"))
        );
        cri.addOrder(Order.desc("bookingOrder.tripDate"));
        cri.addOrder(Order.desc("bookingOrder.tripTime"));
        cri.addOrder(Order.desc("bookingOrder.orderNo"));
        cri.addOrder(Order.desc("ytp.packageID"));
        cri.addOrder(Order.desc("bookingOrder.bookingDateTime"));
        cri.setFirstResult(skipRows).setMaxResults(limit);
        List<Object[]> rows = cri.list();
        List<AdHistoryRegularBookingOrder> results = new ArrayList<>();
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
            String currencyCode = (String) row[12];
            BigDecimal total = (BigDecimal) row[13];
            String reviewToken = (String) row[14];
            Boolean reviewedR = (Boolean) row[15];
            Integer packageRate = (Integer) row[16];

            AdHistoryRegularBookingOrder histo = new AdHistoryRegularBookingOrder();
            histo.setBookingNo(bookingNo);
            histo.setTripDate(tripDate);
            histo.setStartTime(startTime);
            histo.setDurationType(durationType);
            histo.setDuration(duration);
            histo.setBillingFirstName(billingFirstName);
            histo.setBillingLastName(billingLastName);
            histo.setPackageName(packageName);
            histo.setBookedQty(bookedQty);
            histo.setOfflineBooking(offlineBooking);
            histo.setTotalResults(count);
            histo.setServingType(servingTypeR);
            histo.setTripperAbsence(tripperAbsence);
            histo.setCurrencyCode(currencyCode);
            histo.setTotal(total);
            histo.setReviewToken(reviewToken);
            histo.setReviewed(reviewedR);
            histo.setPackageRate(packageRate);
            results.add(histo);
        }
        return results;
    }

}
