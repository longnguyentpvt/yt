/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.func.partnertrippdash.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javaclass.common.ytbooking.YTBookingData;
import javaclass.utility.YTDateTimeUtility;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.beans.factory.annotation.Autowired;
import yt.entity.tbl.YTBookingOrder;
import yt.entity.tbl.YTPackage;
import yt.func.partnertrippdash.javaclass.PackageSelling;
import yt.func.partnertrippdash.javaclass.PackageWithTotal;
import yt.func.partnertrippdash.javaclass.UpcomingBooking;

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

    public List<PackageWithTotal> getPackagesWithTotalAmount(String partnerID, long beginOfThisMonth, long endOfThisMonth) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteriaYTP = session.createCriteria(YTBookingOrder.class, "bookingOrder");
        criteriaYTP.createAlias("bookingOrder.ytPackage", "ytp", JoinType.INNER_JOIN);
        criteriaYTP.createAlias("ytp.contents", "ytpContent", JoinType.INNER_JOIN);
        criteriaYTP.add(Restrictions.eq("ytp.partnerID", partnerID));
        //get default name
        criteriaYTP.add(Restrictions.eq("ytpContent.defaultContent", true));
        criteriaYTP.add(Restrictions.eq("bookingOrder.offlineBooking", false));

        // payment status
        criteriaYTP.add(Restrictions.eq("bookingOrder.paymentStatus", YTBookingData.PAYMENT_STATUS_SUCCESS));
        
        Criterion criterion = Restrictions.and(Restrictions.ge("bookingOrder.bookingDate", beginOfThisMonth),
                        Restrictions.le("bookingOrder.bookingDate", endOfThisMonth));
        criteriaYTP.add(criterion);

        // select clause
        ProjectionList selectClause = Projections.projectionList()
                .add(Projections.groupProperty("bookingOrder.packageID"))
                .add(Projections.groupProperty("ytpContent.packageName"))
                .add(Projections.sum("bookingOrder.amount"));

        // set clause
        criteriaYTP.setProjection(selectClause);
        //
        List<Object[]> rows = criteriaYTP.list();
        List<PackageWithTotal> list = new ArrayList<>();

        for (Object[] row : rows) {
            PackageWithTotal ytp = new PackageWithTotal();
            // packageID
            String packageID = (String) row[0];
            ytp.setPackageID(packageID);
            // packageName
            String packageName = (String) row[1];
            // init set PackageContent
            ytp.setPackageName(packageName);

            // price
            BigDecimal total = (BigDecimal) row[2];
            ytp.setTotalPay(total);
            list.add(ytp);
        }
        return list;
    }

    public List<PackageWithTotal> getPackageTotalSale(String partnerID) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteriaYTP = session.createCriteria(YTBookingOrder.class, "bookingOrder");
        criteriaYTP.createAlias("bookingOrder.ytPackage", "ytp", JoinType.INNER_JOIN);
        criteriaYTP.createAlias("ytp.contents", "ytpContent", JoinType.INNER_JOIN);
        criteriaYTP.add(Restrictions.eq("ytp.partnerID", partnerID));
        //get default name
        criteriaYTP.add(Restrictions.eq("ytpContent.defaultContent", Boolean.TRUE));
        criteriaYTP.add(Restrictions.eq("bookingOrder.offlineBooking", Boolean.FALSE));
        // payment status
        criteriaYTP.add(Restrictions.eq("bookingOrder.paymentStatus", YTBookingData.PAYMENT_STATUS_SUCCESS));
        // select clause
        ProjectionList selectClause = Projections.projectionList()
                .add(Projections.groupProperty("bookingOrder.packageID"))
                .add(Projections.groupProperty("ytpContent.packageName"))
                .add(Projections.sum("bookingOrder.amount"));

        // set clause
        criteriaYTP.setProjection(selectClause);
        //
        List<Object[]> rows = criteriaYTP.list();
        List<PackageWithTotal> list = new ArrayList<>();

        for (Object[] row : rows) {

            PackageWithTotal ytp = new PackageWithTotal();
            // packageID
            String packageID = (String) row[0];
            ytp.setPackageID(packageID);
            // packageName
            String packageName = (String) row[1];
            // init set PackageContent
            ytp.setPackageName(packageName);

            // price
            BigDecimal total = (BigDecimal) row[2];
            ytp.setTotalPay(total);
            list.add(ytp);
        }
        return list;
    }

    public List<UpcomingBooking> getUpcomingToday(String partnerID, int page, int limit) {
        //get session
        Session session = sessionFactory.getCurrentSession();
        //get total number of result
        Criteria cri = session.createCriteria(YTBookingOrder.class, "bookingOrder");
        cri.createAlias("bookingOrder.ytPackage", "ytp", JoinType.INNER_JOIN);
        cri.add(Restrictions.eq("ytp.partnerID", partnerID));
        // payment status
        cri.add(Restrictions.eq("bookingOrder.paymentStatus", YTBookingData.PAYMENT_STATUS_SUCCESS));
        cri.add(Restrictions.eq("bookingOrder.tripDate", YTDateTimeUtility.getCurrentDateInMilli()));

        //get total rsults
        cri.setProjection(Projections.rowCount());
        Long count = (Long) cri.uniqueResult();
        // select clause
        cri.setProjection(Projections.projectionList()
                .add(Projections.property("bookingOrder.orderNo")).add(Projections.property("bookingOrder.bookingDate"))
                .add(Projections.property("bookingOrder.tripTime")).add(Projections.property("bookingOrder.durationType"))
                .add(Projections.property("bookingOrder.businessDuration")).add(Projections.property("bookingOrder.billingFirstName"))
                .add(Projections.property("bookingOrder.billingLastName")).add(Projections.property("bookingOrder.packageName"))
                .add(Projections.property("bookingOrder.usedQuota")).add(Projections.property("bookingOrder.offlineBooking"))
        ).addOrder(Order.desc("bookingOrder.bookingDateTime"))
                .setFirstResult((limit * page) - limit).setMaxResults(limit);
        List<Object[]> rows = cri.list();
        List<UpcomingBooking> results = new ArrayList<>();
        for (Object[] row : rows) {
            String bookingNo = (String) row[0];
            Long bookingDate = (Long) row[1];
            Integer startTime = (Integer) row[2];
            String durationType = (String) row[3];
            Integer duration = (Integer) row[4];
            String billingFirstName = (String) row[5];
            String billingLastName = (String) row[6];
            String packageName = (String) row[7];
            Integer bookedQty = (Integer) row[8];
            Boolean offlineBooking = (Boolean) row[9];

            UpcomingBooking upcomingBooking = new UpcomingBooking();
            upcomingBooking.setBookingNo(bookingNo);
            upcomingBooking.setBookingDate(bookingDate);
            upcomingBooking.setStartTime(startTime);
            upcomingBooking.setDurationType(durationType);
            upcomingBooking.setDuration(duration);
            upcomingBooking.setBillingFirstName(billingFirstName);
            upcomingBooking.setBillingLastName(billingLastName);
            upcomingBooking.setPackageName(packageName);
            upcomingBooking.setBookedQty(bookedQty);
            upcomingBooking.setOfflineBooking(offlineBooking);
            upcomingBooking.setTotalResults(count);
            results.add(upcomingBooking);

        }
        return results;
    }

    public List<YTPackage> getPackageSellingData(String partnerID) {
        //get session
        Session session = sessionFactory.getCurrentSession();
        Criteria criteriaYTP = session.createCriteria(YTPackage.class, "ytp");
        criteriaYTP.add(Restrictions.eq("ytp.partnerID", partnerID));
        // select clause
        criteriaYTP.setProjection(Projections.projectionList()
                .add(Projections.property("ytp.packageID"))
                .add(Projections.property("ytp.dateSlots"))
                .add(Projections.property("ytp.quotaType"))
                .add(Projections.property("ytp.slotQuota"))
                .add(Projections.property("ytp.dateSchedule"))
                .add(Projections.property("ytp.slotInterval"))
                .add(Projections.property("ytp.timeSlots"))
                .add(Projections.property("ytp.servingType")));
        List<Object[]> rows = criteriaYTP.list();
        List<YTPackage> results = new ArrayList<>();
        for (Object[] row : rows) {
            String packageID = (String) row[0];
            String dateSlots = (String) row[1];
            String quotaType = (String) row[2];
            Integer slotQuota = (Integer) row[3];
            String dateSchedule = (String) row[4];
            Integer slotInterval = (Integer) row[5];
            String timeSlots = (String) row[6];
            String servingType = (String) row[7];

            YTPackage aP = new YTPackage();
            aP.setPackageID(packageID);
            aP.setDateSlots(dateSlots);
            aP.setQuotaType(quotaType);
            aP.setSlotQuota(slotQuota);
            aP.setDateSchedule(dateSchedule);
            aP.setSlotInterval(slotInterval);
            aP.setTimeSlots(timeSlots);
            aP.setServingType(servingType);
            results.add(aP);
        }
        return results;
    }

    public List<YTBookingOrder> getUsedQuotaWithInCurrentMonth(String partnerID, long beginOfCurrenMontth, long endOfCurrentMonth) {
        //get session
        Session session = sessionFactory.getCurrentSession();
        Criteria criteriaYTP = session.createCriteria(YTBookingOrder.class, "bookingOrder");
        criteriaYTP.createAlias("bookingOrder.ytPackage", "ytp", JoinType.INNER_JOIN);
        criteriaYTP.createAlias("ytp.contents", "ytpContent", JoinType.INNER_JOIN);
        
        // where clause
        criteriaYTP.add(Restrictions.eq("ytp.partnerID", partnerID));
        criteriaYTP.add(Restrictions.eq("bookingOrder.offlineBooking", false));
        criteriaYTP.add(Restrictions.ge("bookingOrder.bookingDate", beginOfCurrenMontth));
        criteriaYTP.add(Restrictions.le("bookingOrder.bookingDate", endOfCurrentMonth));
        criteriaYTP.add(Restrictions.eq("bookingOrder.paymentStatus", YTBookingData.PAYMENT_STATUS_SUCCESS));
       
        // select clause
        criteriaYTP.setProjection(Projections.projectionList()
                .add(Projections.property("bookingOrder.packageID"))
                .add(Projections.property("ytpContent.packageName"))
                .add(Projections.property("bookingOrder.usedQuota")));
        
        List<Object[]> rows = criteriaYTP.list();
        List<YTBookingOrder> results = new ArrayList<>();
        for (Object[] row : rows) {
            String packageID = (String) row[0];
            String packageName = (String) row[1];
            Integer usedQuota = (Integer) row[2];
            //
            YTBookingOrder bookingOrder = new YTBookingOrder();
            bookingOrder.setPackageID(packageID);
            bookingOrder.setPackageName(packageName);
            bookingOrder.setUsedQuota(usedQuota);
            results.add(bookingOrder);
        }
        return results;
    }

}
