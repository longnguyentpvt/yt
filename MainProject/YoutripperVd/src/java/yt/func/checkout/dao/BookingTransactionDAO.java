/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.func.checkout.dao;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.beans.factory.annotation.Autowired;
import yt.entity.tbl.BookingTransaction;
import yt.entity.tbl.Partner;
import yt.entity.tbl.PartnerCountry;
import yt.entity.tbl.PartnerState;
import yt.entity.tbl.YTPackage;
import yt.entity.tbl.YTPackageContent;

/**
 *
 * @author nickn
 */
public class BookingTransactionDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }

    public void createBookingTransaction(String tsID, long transactionTime, long transactionDate, String packageID, Long servingDate,
            Integer servingTime, String currencyCode, int noPackages, String servingType, String durationType,
            int businessDuration, Long expirationDate, Integer opServingQTY, BigDecimal packagePrice, BigDecimal paymentPrice, boolean onPromotional) {
        Session session = sessionFactory.getCurrentSession();
        BookingTransaction ts = new BookingTransaction();
        ts.setTransactionID(tsID);
        ts.setTransactionTime(transactionTime);
        ts.setTransactionDate(transactionDate);
        ts.setPackageID(packageID);
        ts.setServingDate(servingDate);
        ts.setServingTime(servingTime);
        ts.setCurrencyCode(currencyCode);
        ts.setNoPackages(noPackages);
        ts.setServingType(servingType);
        ts.setDurationType(durationType);
        ts.setBusinessDuration(businessDuration);
        ts.setExpirationDate(expirationDate);
        ts.setOpServingQTY(opServingQTY);
        ts.setPackagePrice(packagePrice);
        ts.setPaymentPrice(paymentPrice);
        ts.setOnPromotional(onPromotional);
        session.persist(ts);
    }

    public BookingTransaction loadCheckoutInfo(String tsID) {
        Session session = sessionFactory.getCurrentSession();
        Criteria cri = session.createCriteria(BookingTransaction.class, "b");
        cri.createAlias("b.ytPackage", "p", JoinType.INNER_JOIN);
        cri.add(Restrictions.eq("transactionID", tsID));

        ProjectionList pl = Projections.projectionList().add(Projections.property("b.packageID")).add(Projections.property("b.paymentPrice"))
                .add(Projections.property("b.noPackages")).add(Projections.property("b.currencyCode")).add(Projections.property("p.partnerID")).add(Projections.property("p.packageID"))
                .add(Projections.property("p.categoryID")).add(Projections.property("p.subCategoryID"));

        cri.setProjection(pl);

        Object[] row = (Object[]) cri.uniqueResult();
        if (row != null) {
            String pkgID = (String) row[0];
            BigDecimal paymentPrice = (BigDecimal) row[1];
            int noPackages = (int) row[2];
            String currencyCode = (String) row[3];
            String partnerID = (String) row[4];
            String packageID = (String) row[5];
            String categoryID = (String) row[6];
            String subCategoryID = (String) row[7];

            BookingTransaction ts = new BookingTransaction();
            ts.setPackageID(pkgID);
            ts.setPaymentPrice(paymentPrice);
            ts.setNoPackages(noPackages);
            ts.setCurrencyCode(currencyCode);

            YTPackage pkg = new YTPackage();
            pkg.setPackageID(packageID);
            pkg.setPartnerID(partnerID);
            pkg.setCategoryID(categoryID);
            pkg.setSubCategoryID(subCategoryID);
            ts.setYtPackage(pkg);

            return ts;
        }
        return null;
    }

    public BookingTransaction getBookingToCheckout(String tsID) {
        Session session = sessionFactory.getCurrentSession();
        Criteria cri = session.createCriteria(BookingTransaction.class, "b");
        cri.createAlias("b.ytPackage", "p", JoinType.INNER_JOIN);
        cri.createAlias("p.contents", "c", JoinType.INNER_JOIN);
        cri.createAlias("p.partner", "pn", JoinType.INNER_JOIN);
        cri.createAlias("pn.partnerCountry", "pnc", JoinType.INNER_JOIN);
        cri.createAlias("pn.partnerState", "pns", JoinType.INNER_JOIN);

        cri.add(Restrictions.eq("b.transactionID", tsID)).add(Restrictions.eq("c.defaultContent", true));

        ProjectionList pl = Projections.projectionList().add(Projections.property("b.packageID")).add(Projections.property("b.packagePrice"))
                .add(Projections.property("b.noPackages")).add(Projections.property("b.currencyCode"))
                .add(Projections.property("b.servingDate")).add(Projections.property("b.servingTime")).add(Projections.property("b.servingType"))
                .add(Projections.property("b.durationType")).add(Projections.property("b.businessDuration")).add(Projections.property("b.expirationDate"))
                .add(Projections.property("b.opServingQTY")).add(Projections.property("c.packageName"))
                .add(Projections.property("pn.businessName")).add(Projections.property("pn.businessAddress")).add(Projections.property("pnc.countryName"))
                .add(Projections.property("pns.stateName")).add(Projections.property("pn.postalCode"))
                .add(Projections.property("pn.phoneCode")).add(Projections.property("pn.phoneNumber")).add(Projections.property("pn.verificationID"))
                .add(Projections.property("pn.smallAvatar")).add(Projections.property("p.partnerID"))
                .add(Projections.property("p.categoryID")).add(Projections.property("p.subCategoryID"))
                .add(Projections.property("b.paymentPrice")).add(Projections.property("b.onPromotional"));

        cri.setProjection(pl);

        Object[] row = (Object[]) cri.uniqueResult();

        String pkgID = (String) row[0];
        BigDecimal pkgPrice = (BigDecimal) row[1];
        int noPackages = (int) row[2];
        String currencyCode = (String) row[3];
        Long servingDate = (Long) row[4];
        Integer servingTime = (Integer) row[5];
        String servingType = (String) row[6];
        String durationType = (String) row[7];
        int businessDuration = (int) row[8];
        Long expirationDate = (Long) row[9];
        Integer opServingQTY = (Integer) row[10];
        String packageName = (String) row[11];
        String businessName = (String) row[12];
        String businessAddress = (String) row[13];
        String countryName = (String) row[14];
        String stateName = (String) row[15];
        String postalCode = (String) row[16];
        String phoneCode = (String) row[17];
        String phoneNumber = (String) row[18];
        String verificationID = (String) row[19];
        String smallAvatar = (String) row[20];
        String partnerID = (String) row[21];
        String categoryID = (String) row[22];
        String subCategoryID = (String) row[23];
        BigDecimal paymentPrice = (BigDecimal) row[24];
        boolean onPromotional = (boolean) row[25];

        BookingTransaction ts = new BookingTransaction();
        ts.setPackageID(pkgID);
        ts.setPackagePrice(pkgPrice);
        ts.setPaymentPrice(paymentPrice);
        ts.setOnPromotional(onPromotional);
        ts.setNoPackages(noPackages);
        ts.setCurrencyCode(currencyCode);
        ts.setServingDate(servingDate);
        ts.setServingTime(servingTime);
        ts.setServingType(servingType);
        ts.setDurationType(durationType);
        ts.setBusinessDuration(businessDuration);
        ts.setExpirationDate(expirationDate);
        ts.setOpServingQTY(opServingQTY);

        YTPackage ytPackage = new YTPackage();
        ytPackage.setPartnerID(partnerID);
        ytPackage.setCategoryID(categoryID);
        ytPackage.setSubCategoryID(subCategoryID);

        YTPackageContent content = new YTPackageContent();
        content.setPackageName(packageName);
        Set<YTPackageContent> contents = new HashSet<>();
        contents.add(content);
        ytPackage.setContents(contents);

        Partner pn = new Partner();
        pn.setBusinessName(businessName);
        pn.setBusinessAddress(businessAddress);
        PartnerCountry ct = new PartnerCountry();
        ct.setCountryName(countryName);
        pn.setPartnerCountry(ct);
        PartnerState st = new PartnerState();
        st.setStateName(stateName);
        pn.setPartnerState(st);
        pn.setPostalCode(postalCode);
        pn.setPhoneCode(phoneCode);
        pn.setPhoneNumber(phoneNumber);
        pn.setVerificationID(verificationID);
        pn.setSmallAvatar(smallAvatar);
        ytPackage.setPartner(pn);

        ts.setYtPackage(ytPackage);

        return ts;
    }

    public BookingTransaction getPackageInfoForDiscount(String transactionID) {
        Session session = sessionFactory.getCurrentSession();
        Criteria cri = session.createCriteria(BookingTransaction.class, "b");
        cri.createAlias("b.ytPackage", "p", JoinType.INNER_JOIN);

        cri.add(Restrictions.eq("b.transactionID", transactionID));

        ProjectionList pl = Projections.projectionList().add(Projections.property("b.packagePrice")).add(Projections.property("b.currencyCode"))
                .add(Projections.property("p.partnerID")).add(Projections.property("p.packageID"))
                .add(Projections.property("p.categoryID")).add(Projections.property("p.subCategoryID")).add(Projections.property("b.noPackages"));
        cri.setProjection(pl);

        Object[] row = (Object[]) cri.uniqueResult();
        BigDecimal packagePrice = (BigDecimal) row[0];
        String currencyCode = (String) row[1];
        String partnerID = (String) row[2];
        String packageID = (String) row[3];
        String categoryID = (String) row[4];
        String subCategoryID = (String) row[5];
        int noPackage = (int) row[6];

        BookingTransaction bt = new BookingTransaction();
        bt.setPackagePrice(packagePrice);
        bt.setCurrencyCode(currencyCode);
        bt.setNoPackages(noPackage);

        YTPackage pkg = new YTPackage();
        pkg.setPackageID(packageID);
        pkg.setPartnerID(partnerID);
        pkg.setCategoryID(categoryID);
        pkg.setSubCategoryID(subCategoryID);
        bt.setYtPackage(pkg);

        return bt;
    }
}
