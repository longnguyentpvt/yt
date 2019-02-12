/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.func.partnerpayment.dao;

import java.math.BigDecimal;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javaclass.common.YTPartnerPaymentInfo;
import javaclass.common.ytbooking.YTBookingData;
import javaclass.common.ytpackage.YTPackageData;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.beans.factory.annotation.Autowired;
import yt.entity.tbl.YTBookingOrder;

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

    public Map.Entry<Long, List<YTBookingOrder>> getPartnerPaymentOrders(String partnerID, long startBookingDate,
            long endBookingDate, String paymentStatus, int currentPage, int limit, boolean b) {
        Session session = sessionFactory.getCurrentSession();
        Criteria cri = session.createCriteria(YTBookingOrder.class, "bookingOrder");
        cri.createAlias("bookingOrder.ytPackage", "ytp", JoinType.INNER_JOIN);
        cri.createAlias("ytp.contents", "ytpContent", JoinType.LEFT_OUTER_JOIN);
        //get default name
        cri.add(Restrictions.eq("ytpContent.defaultContent", Boolean.TRUE));
        //PARTNER ID
        cri.add(Restrictions.eq("ytp.partnerID", partnerID));
        //
        cri.add(Restrictions.ge("bookingOrder.bookingDateTime", startBookingDate));
        cri.add(Restrictions.le("bookingOrder.bookingDateTime", endBookingDate));
        //
        if (paymentStatus != null) {
            if (paymentStatus.equals(YTPartnerPaymentInfo.ORDER_PAYMENT_STATUS_PAID)) {
                cri.add(Restrictions.eq("bookingOrder.withdrawStatus", YTPartnerPaymentInfo.ORDER_PAYMENT_STATUS_PAID));
            } else if (paymentStatus.equals(YTPartnerPaymentInfo.PAYMENT_STATUS_FILTER_PROCESSING)) {
                cri.add(Restrictions.eq("bookingOrder.withdrawStatus", YTPartnerPaymentInfo.ORDER_PAYMENT_STATUS_PROCESSING));
            } else if (paymentStatus.equals(YTPartnerPaymentInfo.PAYMENT_STATUS_FILTER_PAYABLE)) {
                cri.add(Restrictions.isNull("bookingOrder.withdrawStatus"));
            } else if (paymentStatus.equals(YTPartnerPaymentInfo.PAYMENT_STATUS_FILTER_PENDING)) {
                cri.add(Restrictions.isNull("bookingOrder.withdrawStatus"));
            }
        }
        //
        cri.add(Restrictions.eq("bookingOrder.paymentStatus", YTBookingData.PAYMENT_STATUS_SUCCESS));
        //
        cri.setProjection(Projections.rowCount());
        long totalRecords = (long) cri.uniqueResult();

        cri.setProjection(Projections.projectionList()
                .add(Projections.property("bookingOrder.bookingDate")).add(Projections.property("bookingOrder.orderNo"))
                .add(Projections.property("ytp.packageID")).add(Projections.property("ytpContent.packageName"))
                .add(Projections.property("bookingOrder.total")).add(Projections.property("bookingOrder.amount"))
                .add(Projections.property("bookingOrder.finalRate")).add(Projections.property("bookingOrder.ytCommission"))
                .add(Projections.property("bookingOrder.partnerPayable")).add(Projections.property("bookingOrder.payableBill"))
                .add(Projections.property("bookingOrder.payableBillDate")).add(Projections.property("bookingOrder.withdrawStatus"))
                .add(Projections.property("bookingOrder.withdrawDate")).add(Projections.property("bookingOrder.currencyCode"))
        );
        cri.setFirstResult((limit * currentPage) - limit).setMaxResults(limit);
        List<Object[]> rows = cri.list();
        List<YTBookingOrder> orders = new ArrayList<>();
        for (Object[] row : rows) {
            Long bookingDate = (Long) row[0];
            String orderNo = (String) row[1];
            String packageID = (String) row[2];
            String packageName = (String) row[3];
            BigDecimal total = (BigDecimal) row[4];
            BigDecimal ammount = (BigDecimal) row[5];
            BigDecimal finalRate = (BigDecimal) row[6];
            BigDecimal ytCommission = (BigDecimal) row[7];
            BigDecimal partnerPayable = (BigDecimal) row[8];
            boolean payableBill = (boolean) row[9];
            Long payableBillDate = (Long) row[10];
            String withdrawStatus = (String) row[11];
            Long withdrawDate = (Long) row[12];
            String currencyCode = (String) row[13];
            //
            YTBookingOrder order = new YTBookingOrder();
            order.setBookingDate(bookingDate);
            order.setOrderNo(orderNo);
            order.setPackageID(packageID);
            order.setPackageName(packageName);
            order.setTotal(total);
            order.setAmount(ammount);
            order.setFinalRate(finalRate);
            order.setYtCommission(ytCommission);
            order.setPartnerPayable(partnerPayable);
            order.setPayableBill(payableBill);
            order.setPayableBillDate(payableBillDate);
            order.setWithdrawStatus(withdrawStatus);
            order.setWithdrawDate(withdrawDate);
            order.setCurrencyCode(currencyCode);
            orders.add(order);
        }

        return new AbstractMap.SimpleEntry<>(totalRecords, orders);
    }

}
