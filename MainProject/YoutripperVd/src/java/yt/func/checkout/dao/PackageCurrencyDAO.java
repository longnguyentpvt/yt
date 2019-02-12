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
import org.springframework.beans.factory.annotation.Autowired;
import yt.entity.tbl.PackageCurrency;

/**
 *
 * @author nickn
 */
public class PackageCurrencyDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }

    public boolean isRounded(String currencyCode) {
        Session session = sessionFactory.getCurrentSession();
        Criteria cri = session.createCriteria(PackageCurrency.class);
        cri.add(Restrictions.eq("currencyCode", currencyCode));

        cri.setProjection(Projections.property("rounded"));
        
        return (boolean) cri.uniqueResult();
    }

    public PackageCurrency getCheckoutInfo(String currencyCode) {
        Session session = sessionFactory.getCurrentSession();
        Criteria cri = session.createCriteria(PackageCurrency.class);

        cri.add(Restrictions.eq("currencyCode", currencyCode));

        ProjectionList pl = Projections.projectionList().add(Projections.property("rate")).add(Projections.property("rounded"));
        cri.setProjection(pl);

        Object[] row = (Object[]) cri.uniqueResult();
        BigDecimal rate = (BigDecimal) row[0];
        boolean rounded = (boolean) row[1];
        PackageCurrency pkgCurrency = new PackageCurrency();
        pkgCurrency.setRate(rate);
        pkgCurrency.setRounded(rounded);
        return pkgCurrency;
    }
}
