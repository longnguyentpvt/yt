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
import yt.entity.tbl.YTPackage;
import yt.entity.tbl.YTPackagePrice;

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

    public YTPackage getBookingInfo(String packageID, String currencyCode) {
        Session session = sessionFactory.getCurrentSession();

        Criteria cri = session.createCriteria(YTPackage.class, "pkg");
        cri.createAlias("pkg.prices", "pr", JoinType.INNER_JOIN);

        cri.add(Restrictions.eq("pkg.packageID", packageID))
                .add(Restrictions.eq("pr.currencyCode", currencyCode));

        ProjectionList pl = Projections.projectionList().add(Projections.property("pkg.packageStatus")).add(Projections.property("pkg.servingType"))
                .add(Projections.property("pkg.durationType")).add(Projections.property("pkg.businessDuration"))
                .add(Projections.property("pkg.dateSchedule")).add(Projections.property("pkg.validationWeeks"))
                .add(Projections.property("pkg.validationStartDate")).add(Projections.property("pkg.validationEndDate"))
                .add(Projections.property("pkg.servingTimes")).add(Projections.property("pr.price")).add(Projections.property("pr.promotionPrice"))
                .add(Projections.property("pkg.promotionSchedule")).add(Projections.property("pkg.promotionPeriods"));
        cri.setProjection(pl);

        Object[] row = (Object[]) cri.uniqueResult();
        if (row != null) {
            YTPackage ytPackage = new YTPackage();
            
            String pkgStt = (String) row[0];
            String servingType = (String) row[1];
            String durationType = (String) row[2];
            int businessDuration = (int) row[3];
            String dateSchedule = (String) row[4];
            Integer validateWeeks = (Integer) row[5];
            Long validationStartDate = (Long) row[6];
            Long validationEndDate = (Long) row[7];
            Integer servingTimes = (Integer) row[8];
            BigDecimal price = (BigDecimal) row[9];
            BigDecimal promotionPrice = (BigDecimal) row[10];
            boolean promotionSchedule = (boolean) row[11];
            String promotionPeriods = (String) row[12];
            
            YTPackagePrice aP = new YTPackagePrice();
            aP.setPrice(price);
            aP.setPromotionPrice(promotionPrice);
            Set<YTPackagePrice> prices = new HashSet<>();
            prices.add(aP);
            
            ytPackage.setPackageStatus(pkgStt);
            ytPackage.setServingType(servingType);
            ytPackage.setDurationType(durationType);
            ytPackage.setBusinessDuration(businessDuration);
            ytPackage.setDateSchedule(dateSchedule);
            ytPackage.setValidationWeeks(validateWeeks);
            ytPackage.setValidationStartDate(validationStartDate);
            ytPackage.setValidationEndDate(validationEndDate);
            ytPackage.setServingTimes(servingTimes);
            ytPackage.setPrices(prices);
            ytPackage.setPromotionSchedule(promotionSchedule);
            ytPackage.setPromotionPeriods(promotionPeriods);

            return ytPackage;
        }
        return null;
    }

}
