/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.func.tripperbooking.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javaclass.common.ytbooking.YTBookingData;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.beans.factory.annotation.Autowired;
import yt.entity.tbl.YTBookingOrder;
import yt.func.tripperbooking.javaclass.AdMyBooking;

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

    public List<AdMyBooking> getMyBookingList(String tripperID, int skipRows, int limit, String bookingCode) {
        Session session = sessionFactory.getCurrentSession();
        Criteria cri = session.createCriteria(YTBookingOrder.class, "bookingOrder");
        cri.createAlias("bookingOrder.ytPackage", "ytp", JoinType.INNER_JOIN);
        cri.createAlias("ytp.contents", "ytpContent", JoinType.INNER_JOIN);
        //get default name
        cri.add(Restrictions.eq("ytpContent.defaultContent", true));
        //booking no
        if (bookingCode != null) {
            cri.add(Restrictions.eq("bookingOrder.orderNo", bookingCode));
        }
        //tripperID
        cri.add(Restrictions.eq("bookingOrder.tripperID", tripperID));
        // get total rsults
        cri.setProjection(Projections.rowCount());
        Long count = (Long) cri.uniqueResult();
        // select clause
        cri.setProjection(Projections.projectionList()
                .add(Projections.property("bookingOrder.orderNo")).add(Projections.property("bookingOrder.bookingDate"))
                .add(Projections.property("bookingOrder.amount")).add(Projections.property("ytpContent.packageName"))
                .add(Projections.property("bookingOrder.invoiceFile")).add(Projections.property("bookingOrder.completedPayment"))
        ).setFirstResult(skipRows).setMaxResults(limit);
        List<Object[]> rows = cri.list();
        List<AdMyBooking> results = new ArrayList<>();
        for (Object[] row : rows) {
            String bookingNo = (String) row[0];
            long bookingDate = (Long) row[1];
            BigDecimal amount = (BigDecimal) row[2];
            String packageName = (String) row[3];
            String invoiceFile = (String) row[4];
            boolean completedPayment = (boolean) row[5];
            //
            AdMyBooking mBooking = new AdMyBooking();
            mBooking.setBookingCode(bookingNo);
            mBooking.setBookingDateTime(bookingDate);
            mBooking.setAmount(amount);
            mBooking.setPackageName(packageName);
            mBooking.setInvoiceFile(invoiceFile);
            mBooking.setCompletedPayment(completedPayment);
            mBooking.setTotalResults(count);
            results.add(mBooking);
        }
        return results;
    }
}
