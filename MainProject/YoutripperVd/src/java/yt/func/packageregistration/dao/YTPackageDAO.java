/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.func.packageregistration.dao;

import javaclass.common.ytpackage.YTPackageData;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import yt.entity.tbl.YTPackage;

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

    public void registerMainPackage(String mainPackageID, long tempPackageID, String partnerID) {
        Session session = sessionFactory.getCurrentSession();

        YTPackage ytPackage = new YTPackage();
        ytPackage.setPackageID(mainPackageID);
        ytPackage.setTempPackageID(tempPackageID);
        ytPackage.setPackageStatus(YTPackageData.YT_PACKAGE_STATUS_CREATING);
        ytPackage.setPartnerID(partnerID);
        
        session.persist(ytPackage);
    }
}
