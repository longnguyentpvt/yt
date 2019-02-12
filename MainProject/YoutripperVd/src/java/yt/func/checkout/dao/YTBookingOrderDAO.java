/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.func.checkout.dao;

import java.math.BigDecimal;
import java.util.List;
import javaclass.common.ytbooking.YTBookingData;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.beans.factory.annotation.Autowired;
import yt.entity.tbl.Partner;
import yt.entity.tbl.YTBookingOrder;
import yt.entity.tbl.YTPackage;

/**
 *
 * @author nickn
 */
public class YTBookingOrderDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }

    public void insertNewOrder(String orderNo, String transactionID, String tripperID, String tripperEmail, String billingFirstName, String billingLastName,
            String packageID, String servingType, long bookingDate, long bookingDateTime, String currencyCode, BigDecimal subTotal, String discountCode,
            BigDecimal totalDiscount, BigDecimal total, BigDecimal exchangeRate, BigDecimal paymentRate, BigDecimal finalRate, BigDecimal amount,
            BigDecimal commissionRate, BigDecimal ytCommission, BigDecimal partnerPayable,
            String packageName, BigDecimal packagePrice, BigDecimal paymentPrice, boolean onPromotional, Long tripDate, Integer tripTime, String durationType,
            int businessDuration, Long expirateDate, Integer opServingQTY, int usedQuota, String paymentMethod, String invoiceContent) {
        Session session = sessionFactory.getCurrentSession();

        YTBookingOrder order = new YTBookingOrder();
        order.setOrderNo(orderNo);
        order.setTransactionID(transactionID);
        order.setTripperID(tripperID);
        order.setTripperEmail(tripperEmail);
        order.setBillingFirstName(billingFirstName);
        order.setBillingLastName(billingLastName);
        order.setPackageID(packageID);
        order.setServingType(servingType);
        order.setBookingDate(bookingDate);
        order.setBookingDateTime(bookingDateTime);
        order.setCurrencyCode(currencyCode);
        order.setTotal(subTotal);
        order.setDiscountCode(discountCode);
        order.setTotalDiscount(totalDiscount);
        order.setFinalTotal(total);
        order.setExchangeRate(exchangeRate);
        order.setPaymentRate(paymentRate);
        order.setFinalRate(finalRate);
        order.setAmount(amount);
        order.setCommissionRate(commissionRate);
        order.setYtCommission(ytCommission);
        order.setPartnerPayable(partnerPayable);
        order.setPackageName(packageName);
        order.setPackagePrice(packagePrice);
        order.setPaymentPrice(paymentPrice);
        order.setOnPromotional(onPromotional);
        order.setTripDate(tripDate);
        order.setTripTime(tripTime);
        order.setDurationType(durationType);
        order.setBusinessDuration(businessDuration);
        order.setUsedQuota(usedQuota);
        order.setPaymentMethod(paymentMethod);
        order.setInvoiceContent(invoiceContent);
        order.setPaymentStatus(YTBookingData.PAYMENT_STATUS_RESERVED);
        session.persist(order);
    }

    public YTBookingOrder getTotalCheckout(String orderNo) {
        Session session = sessionFactory.getCurrentSession();
        Criteria cri = session.createCriteria(YTBookingOrder.class);
        cri.add(Restrictions.eq("orderNo", orderNo));

        ProjectionList pl = Projections.projectionList().add(Projections.property("total"))
                .add(Projections.property("currencyCode"));
        cri.setProjection(pl);

        Object[] row = (Object[]) cri.uniqueResult();
        BigDecimal tt = (BigDecimal) row[0];
        String cc = (String) row[1];

        YTBookingOrder order = new YTBookingOrder();
        order.setTotal(tt);
        order.setCurrencyCode(cc);

        return order;
    }

    public YTBookingOrder getOrderInfoForVisaMasterPayment(String orderNo) {
        Session session = sessionFactory.getCurrentSession();
        Criteria cri = session.createCriteria(YTBookingOrder.class);
        cri.add(Restrictions.eq("orderNo", orderNo));

        ProjectionList pl = Projections.projectionList().add(Projections.property("total"))
                .add(Projections.property("currencyCode"));
        cri.setProjection(pl);

        Object[] row = (Object[]) cri.uniqueResult();
        BigDecimal tt = (BigDecimal) row[0];
        String cc = (String) row[1];

        YTBookingOrder order = new YTBookingOrder();
        order.setTotal(tt);
        order.setCurrencyCode(cc);

        return order;
    }

    public YTBookingOrder getVisaMasterOrderInfoForResult(String orderNo) {
        Session session = sessionFactory.getCurrentSession();
        Criteria cri = session.createCriteria(YTBookingOrder.class);
        cri.add(Restrictions.eq("orderNo", orderNo));

        ProjectionList pl = Projections.projectionList().add(Projections.property("paymentStatus"));
        cri.setProjection(pl);

        String row = (String) cri.uniqueResult();
//        String paymentStatus = (String) row[0];
        YTBookingOrder order = new YTBookingOrder();
        order.setPaymentStatus(row);

        return order;
    }

    public YTBookingOrder getPaypalOrderInfoForResult(String orderNo) {
        Session session = sessionFactory.getCurrentSession();
        Criteria cri = session.createCriteria(YTBookingOrder.class);
        cri.add(Restrictions.eq("orderNo", orderNo));

        ProjectionList pl = Projections.projectionList().add(Projections.property("paymentStatus"))
                .add(Projections.property("paypalID"));
        cri.setProjection(pl);

        Object[] row = (Object[]) cri.uniqueResult();

        String paymentStatus = (String) row[0];
        String paypalID = (String) row[1];

        YTBookingOrder order = new YTBookingOrder();
        order.setPaymentStatus(paymentStatus);
        order.setPaypalID(paypalID);

        return order;
    }

    public YTBookingOrder getLinepayOrderInfoForResult(String orderNo) {
        Session session = sessionFactory.getCurrentSession();
        Criteria cri = session.createCriteria(YTBookingOrder.class);
        cri.add(Restrictions.eq("orderNo", orderNo));

        ProjectionList pl = Projections.projectionList().add(Projections.property("paymentStatus"))
                .add(Projections.property("linepayID")).add(Projections.property("total")).add(Projections.property("currencyCode"));
        cri.setProjection(pl);

        Object[] row = (Object[]) cri.uniqueResult();

        String paymentStatus = (String) row[0];
        String linepayID = (String) row[1];
        BigDecimal total = (BigDecimal) row[2];
        String currencyCode = (String) row[3];

        YTBookingOrder order = new YTBookingOrder();
        order.setPaymentStatus(paymentStatus);
        order.setLinepayID(linepayID);
        order.setTotal(total);
        order.setCurrencyCode(currencyCode);

        return order;
    }

    public boolean updatePaypalPaymentID(String orderNo, String paypalID) {
        Session session = sessionFactory.getCurrentSession();
        String sql = "UPDATE YTBookingOrder "
                + "SET paypalID = :paypalID "
                + "WHERE orderNo = :orderNo";
        Query query = session.createQuery(sql);

        query.setParameter("paypalID", paypalID);
        query.setParameter("orderNo", orderNo);

        return query.executeUpdate() > 0;
    }

    public boolean updateLinepayPaymentID(String orderNo, String linepayID) {
        Session session = sessionFactory.getCurrentSession();
        String sql = "UPDATE YTBookingOrder "
                + "SET linepayID = :linepayID "
                + "WHERE orderNo = :orderNo";
        Query query = session.createQuery(sql);

        query.setParameter("linepayID", linepayID);
        query.setParameter("orderNo", orderNo);

        return query.executeUpdate() > 0;
    }

    public boolean succeedBooking(String orderNo) {
        Session session = sessionFactory.getCurrentSession();
        String sql = "UPDATE YTBookingOrder "
                + "SET paymentStatus = :stt "
                + "WHERE orderNo = :orderNo";
        Query query = session.createQuery(sql);

        query.setParameter("stt", YTBookingData.PAYMENT_STATUS_SUCCESS);
        query.setParameter("orderNo", orderNo);

        boolean sc = query.executeUpdate() > 0;
        session.flush();
        return sc;
    }

    public boolean failBooking(String orderNo) {
        Session session = sessionFactory.getCurrentSession();
        String sql = "UPDATE YTBookingOrder "
                + "SET paymentStatus = :stt "
                + "WHERE orderNo = :orderNo";
        Query query = session.createQuery(sql);

        query.setParameter("stt", YTBookingData.PAYMENT_STATUS_FAIL);
        query.setParameter("orderNo", orderNo);

        boolean sc = query.executeUpdate() > 0;
        session.flush();
        return sc;
    }

    public YTBookingOrder getInfoToCompletePayment(String orderNo) {
        Session session = sessionFactory.getCurrentSession();
        Criteria cri = session.createCriteria(YTBookingOrder.class, "o");
        cri.createAlias("o.ytPackage", "pkg", JoinType.INNER_JOIN);
        cri.createAlias("pkg.partner", "pn", JoinType.INNER_JOIN);
        cri.add(Restrictions.eq("orderNo", orderNo));

        ProjectionList pl = Projections.projectionList().add(Projections.property("o.packageName")).add(Projections.property("o.paymentStatus"))
                .add(Projections.property("o.invoiceContent")).add(Projections.property("pn.email")).add(Projections.property("pn.displayName"))
                .add(Projections.property("o.billingFirstName")).add(Projections.property("o.billingLastName")).add(Projections.property("o.tripperEmail"))
                .add(Projections.property("o.paypalID")).add(Projections.property("o.linepayID")).add(Projections.property("o.visaMasterCardNo"));
        cri.setProjection(pl);

        YTBookingOrder order = null;
        Object[] row = (Object[]) cri.uniqueResult();
        if (row != null) {
            order = new YTBookingOrder();
            String packageName = (String) row[0];
            String paymentStatus = (String) row[1];
            String invoiceContent = (String) row[2];
            String pnEmail = (String) row[3];
            String pnName = (String) row[4];
            String billingFirstName = (String) row[5];
            String billingLastName = (String) row[6];
            String tpEmail = (String) row[7];
            String paypalID = (String) row[7];
            String linepayID = (String) row[7];
            String visaMasterCardNo = (String) row[7];

            order.setPackageName(packageName);
            order.setPaymentStatus(paymentStatus);
            order.setInvoiceContent(invoiceContent);
            order.setBillingFirstName(billingFirstName);
            order.setBillingLastName(billingLastName);
            order.setTripperEmail(tpEmail);
            order.setPaypalID(paypalID);
            order.setLinepayID(linepayID);
            order.setVisaMasterCardNo(visaMasterCardNo);

            Partner pn = new Partner();
            pn.setDisplayName(pnName);
            pn.setEmail(pnEmail);
            YTPackage pkg = new YTPackage();
            pkg.setPartner(pn);
            order.setYtPackage(pkg);
        }
        return order;
    }

    public void completeOrder(String orderNo, String invoiceName, String invoiceFile) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "UPDATE YTBookingOrder "
                + "SET invoiceName = :invoiceName, invoiceFile = :invoiceFile, completedPayment = :completedPayment "
                + "WHERE orderNo = :orderNo";

        Query query = session.createQuery(hql);
        query.setParameter("invoiceName", invoiceName);
        query.setParameter("invoiceFile", invoiceFile);
        query.setParameter("completedPayment", true);
        query.setParameter("orderNo", orderNo);

        query.executeUpdate();
    }

    public void updateExpireBooking(long limitTime) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "UPDATE YTBookingOrder "
                + "SET paymentStatus = :expiredStt "
                + "WHERE paymentStatus = :reservedStt AND bookingDateTime < :limitTime";

        Query query = session.createQuery(hql);
        query.setParameter("expiredStt", YTBookingData.PAYMENT_STATUS_EXPIRED);
        query.setParameter("reservedStt", YTBookingData.PAYMENT_STATUS_RESERVED);
        query.setParameter("limitTime", limitTime);

        query.executeUpdate();
    }

    public boolean checkUsedDiscountCode(String discountCode, String tripperID) {
        Session session = sessionFactory.getCurrentSession();
        Criteria cri = session.createCriteria(YTBookingOrder.class);
        cri.add(Restrictions.eq("tripperID", tripperID)).add(Restrictions.eq("discountCode", discountCode))
                .add(Restrictions.or(Restrictions.eq("paymentStatus", YTBookingData.PAYMENT_STATUS_SUCCESS),
                        Restrictions.eq("paymentStatus", YTBookingData.PAYMENT_STATUS_RESERVED)));

        cri.setProjection(Projections.property("orderNo"));

        List<Object[]> rows = cri.list();
        return rows != null && rows.size() > 0;
    }

    public long getNoUsedOfDiscountCode(String discountCode) {
        Session session = sessionFactory.getCurrentSession();
        Criteria cri = session.createCriteria(YTBookingOrder.class);
        cri.add(Restrictions.eq("discountCode", discountCode))
                .add(Restrictions.or(Restrictions.eq("paymentStatus", YTBookingData.PAYMENT_STATUS_SUCCESS),
                        Restrictions.eq("paymentStatus", YTBookingData.PAYMENT_STATUS_RESERVED)));

        cri.setProjection(Projections.count("orderNo"));
        long no = (long) cri.uniqueResult();
        return no;
    }
}
