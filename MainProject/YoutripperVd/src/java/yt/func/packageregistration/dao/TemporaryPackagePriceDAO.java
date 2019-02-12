/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.func.packageregistration.dao;

import javaclass.common.YTData;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import yt.entity.tbl.TemporaryPackagePrice;

/**
 *
 * @author nickn
 */
public class TemporaryPackagePriceDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }

    public void registerDefaultPrice(long packageID) {
        Session session = sessionFactory.getCurrentSession();

        TemporaryPackagePrice tempPrice = new TemporaryPackagePrice();
        tempPrice.setPackageID(packageID);
        tempPrice.setCurrencyCode(YTData.DEFAULT_CURRENCY_CODE);

        session.persist(tempPrice);
    }
}
