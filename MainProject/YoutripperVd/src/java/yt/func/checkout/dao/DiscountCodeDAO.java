/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.func.checkout.dao;

import java.math.BigDecimal;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.beans.factory.annotation.Autowired;
import yt.entity.tbl.DiscountCode;
import yt.entity.tbl.DiscountCurrency;

/**
 *
 * @author nickn
 */
public class DiscountCodeDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }

    public DiscountCurrency getDiscountCodeInfo(String code, String currencyCode) {
        Session session = sessionFactory.getCurrentSession();
        Criteria cri = session.createCriteria(DiscountCurrency.class, "c");
        cri.createAlias("c.discountCode", "d", JoinType.INNER_JOIN);

        cri.add(Restrictions.eq("c.code", code)).add(Restrictions.eq("c.currencyCode", currencyCode));

        ProjectionList pl = Projections.projectionList().add(Projections.property("c.discountPrice")).add(Projections.property("c.percentageDiscount"))
                .add(Projections.property("c.minimumPayment")).add(Projections.property("c.maximumDiscount")).add(Projections.property("d.quantity"))
                .add(Projections.property("d.expiredCode")).add(Projections.property("d.multipleUse")).add(Projections.property("d.partnerCode"))
                .add(Projections.property("d.codeType")).add(Projections.property("d.partnerList")).add(Projections.property("d.packageList"))
                .add(Projections.property("d.categoryList")).add(Projections.property("d.subCategoryList")).add(Projections.property("d.specificDiscount"));
        cri.setProjection(pl);

        Object[] row = (Object[]) cri.uniqueResult();
        DiscountCurrency dc = null;
        if (row != null) {
            BigDecimal discountPrice = (BigDecimal) row[0];
            Integer percentageDiscount = (Integer) row[1];
            BigDecimal minimumPayment = (BigDecimal) row[2];
            BigDecimal maximumDiscount = (BigDecimal) row[3];
            int quantity = (int) row[4];
            boolean expiredCode = (boolean) row[5];
            boolean multipleUse = (boolean) row[6];
            boolean partnerCode = (boolean) row[7];
            String codeType = (String) row[8];
            String partnerList = (String) row[9];
            String packageList = (String) row[10];
            String categoryList = (String) row[11];
            String subCategoryList = (String) row[12];
            boolean specificDiscount = (boolean) row[13];
            
            dc = new DiscountCurrency();
            dc.setDiscountPrice(discountPrice);
            dc.setPercentageDiscount(percentageDiscount);
            dc.setMinimumPayment(minimumPayment);
            dc.setMaximumDiscount(maximumDiscount);
            
            DiscountCode discountCode = new DiscountCode();
            discountCode.setQuantity(quantity);
            discountCode.setExpiredCode(expiredCode);
            discountCode.setMultipleUse(multipleUse);
            discountCode.setPartnerCode(partnerCode);
            discountCode.setSpecificDiscount(specificDiscount);
            discountCode.setCodeType(codeType);
            discountCode.setPartnerList(partnerList);
            discountCode.setPackageList(packageList);
            discountCode.setCategoryList(categoryList);
            discountCode.setSubCategoryList(subCategoryList);
            dc.setDiscountCode(discountCode);
        }
        return dc;
    }
}
