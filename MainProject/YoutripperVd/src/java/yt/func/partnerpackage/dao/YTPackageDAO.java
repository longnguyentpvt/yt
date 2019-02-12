/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.func.partnerpackage.dao;

import java.util.List;
import javaclass.common.ytpackage.YTPackageData;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import yt.entity.view.PartnerPackage;

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

    public List<PartnerPackage> getAllPackages(String partnerID, boolean isOpenTimed, String packageName, String mainStatus, String tempStatus) {
        Session session = sessionFactory.getCurrentSession();
        Criteria cri = session.createCriteria(PartnerPackage.class);

        cri.add(Restrictions.eq("partnerID", partnerID));
        if (isOpenTimed) {
            cri.add(Restrictions.eq("servingType", YTPackageData.SERVING_TYPE_OPEN_TIMED));
        } else {
            cri.add(Restrictions.ne("servingType", YTPackageData.SERVING_TYPE_OPEN_TIMED));
        }

        if (packageName != null && !packageName.isEmpty()) {
            cri.add(Restrictions.like("packageName", packageName, MatchMode.ANYWHERE));
        }

        if (mainStatus != null && !mainStatus.isEmpty()) {
            cri.add(Restrictions.eq("mainStatus", mainStatus));
        }

        if (tempStatus != null && !tempStatus.isEmpty()) {
            cri.add(Restrictions.eq("tempStatus", tempStatus));
        }

        return cri.list();
    }

}
