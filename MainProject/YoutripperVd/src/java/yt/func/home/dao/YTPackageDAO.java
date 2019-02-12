/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.func.home.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javaclass.common.ytpackage.YTPackageModule;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import yt.entity.view.HottestDeal;
import yt.entity.view.LatestBookedPackage;

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

    public List<HottestDeal> loadHottestDealPackage(String languageCode, String currencyCode) {
        Session session = sessionFactory.getCurrentSession();

        Criteria criteria = session.createCriteria(HottestDeal.class);
        criteria.add(Restrictions.eq("languageCode", languageCode))
                .add(Restrictions.eq("currencyCode", currencyCode));

        ProjectionList pl = Projections.projectionList()
                .add(Projections.property("packageID")).add(Projections.property("packageName"))
                .add(Projections.property("siteURL")).add(Projections.property("cityName"))
                .add(Projections.property("countryName")).add(Projections.property("price"))
                .add(Projections.property("promotionPrice")).add(Projections.property("noSolds"))
                .add(Projections.property("noComments")).add(Projections.property("rate"))
                .add(Projections.property("portraitPhoto")).add(Projections.property("promotionPortrait"))
                .add(Projections.property("onPromotional"));
        criteria.setProjection(pl);
        criteria.addOrder(Order.desc("promotionPercent"));
        criteria.addOrder(Order.asc("promotionPrice"));
        criteria.addOrder(Order.desc("approvedDateTime"));
        //SELECT TOP 6
        criteria.setMaxResults(6);
        //
        List<Object[]> rows = criteria.list();
        List<HottestDeal> results = new ArrayList<>();
        for (Object[] row : rows) {
            String packageID = (String) row[0];
            String packageName = (String) row[1];
            String siteURL = (String) row[2];
            String cityName = (String) row[3];
            String countryName = (String) row[4];
            BigDecimal price = (BigDecimal) row[5];
            BigDecimal promotionPrice = (BigDecimal) row[6];
            Integer noSolds = (Integer) row[7];
            Integer noComments = (Integer) row[8];
            Integer rate = (Integer) row[9];
            String portraitPhoto = (String) row[10];
            String promotionPortrait = (String) row[11];
            boolean onPromotional = (boolean) row[12];
            // hottest deal
            HottestDeal aP = new HottestDeal();
            aP.setPackageID(packageID);
            aP.setPackageName(packageName);
            aP.setSiteURL(siteURL);
            aP.setCityName(cityName);
            aP.setCountryName(countryName);
            aP.setPrice(price);
            aP.setPromotionPrice(promotionPrice);
            aP.setNoSolds(noSolds);
            aP.setNoComments(noComments);
            aP.setRate(rate);
            aP.setPortraitPhoto(portraitPhoto);
            aP.setPromotionPortrait(promotionPortrait);
            aP.setOnPromotional(onPromotional);
            results.add(aP);
        }
        return results;
    }

    public List<LatestBookedPackage> loadJustBookedPackage(String languageCode, String currencyCode) {
        Session session = sessionFactory.getCurrentSession();

        Criteria criteria = session.createCriteria(LatestBookedPackage.class);
        criteria.add(Restrictions.eq("languageCode", languageCode))
                .add(Restrictions.eq("currencyCode", currencyCode));

        ProjectionList pl = Projections.projectionList()
                .add(Projections.property("packageID")).add(Projections.property("packageName"))
                .add(Projections.property("siteURL")).add(Projections.property("cityName"))
                .add(Projections.property("countryName")).add(Projections.property("price"))
                .add(Projections.property("promotionPrice")).add(Projections.property("noSolds"))
                .add(Projections.property("noComments")).add(Projections.property("rate"))
                .add(Projections.property("portraitPhoto")).add(Projections.property("promotionPortrait"))
                .add(Projections.property("onPromotional"));
        criteria.setProjection(pl);
        criteria.addOrder(Order.desc("latestBookedTime"));
        //SELECT TOP 6
        criteria.setMaxResults(6);
        //
        List<Object[]> rows = criteria.list();
        List<LatestBookedPackage> results = new ArrayList<>();
        for (Object[] row : rows) {
            String packageID = (String) row[0];
            String packageName = (String) row[1];
            String siteURL = (String) row[2];
            String cityName = (String) row[3];
            String countryName = (String) row[4];
            BigDecimal price = (BigDecimal) row[5];
            BigDecimal promotionPrice = (BigDecimal) row[6];
            Integer noSolds = (Integer) row[7];
            Integer noComments = (Integer) row[8];
            Integer rate = (Integer) row[9];
            String portraitPhoto = (String) row[10];
            String promotionPortrait = (String) row[11];
            boolean onPromotional = (boolean) row[12];
            //just booked
            LatestBookedPackage aP = new LatestBookedPackage();
            aP.setPackageID(packageID);
            aP.setPackageName(packageName);
            aP.setSiteURL(siteURL);
            aP.setCityName(cityName);
            aP.setCountryName(countryName);
            aP.setPrice(price);
            aP.setPromotionPrice(promotionPrice);
            aP.setNoSolds(noSolds);
            aP.setNoComments(noComments);
            aP.setRate(rate);
            aP.setPortraitPhoto(portraitPhoto);
            aP.setPromotionPortrait(promotionPortrait);
            aP.setOnPromotional(onPromotional);
            results.add(aP);
        }
        return results;
    }
}
