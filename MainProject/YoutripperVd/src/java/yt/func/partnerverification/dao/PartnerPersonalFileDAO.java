/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.func.partnerverification.dao;

import java.util.ArrayList;
import java.util.List;
import javaclass.common.YTPartnerData;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import yt.entity.tbl.PartnerPersonalFile;
import yt.func.partnerverification.javaclass.AdPersonalFile;
import yt.func.partnerverification.javaclass.PartnerVerificationData;

/**
 *
 * @author nickn
 */
public class PartnerPersonalFileDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }

    public void insertPersonalFile(String partnerID, String fileName, String imgFile, String imgStatus) {
        Session session = sessionFactory.getCurrentSession();
        PartnerPersonalFile file = new PartnerPersonalFile();
        file.setFileName(fileName);
        file.setImgFile(imgFile);
        file.setImgStatus(imgStatus);
        file.setPartnerID(partnerID);
        session.persist(file);
    }

    public List<AdPersonalFile> getPersonalFiles(String partnerID) {
        Session session = sessionFactory.getCurrentSession();
        Criteria cri = session.createCriteria(PartnerPersonalFile.class);
        //condition
        cri.add(Restrictions.eq("partnerID", partnerID));
        cri.add(Restrictions.eq("imgStatus", YTPartnerData.PARTNER_FILE_UPLOADED));
        //select
        ProjectionList projectionList = Projections.projectionList().add(Projections.property("fileID"))
                .add(Projections.property("fileName")).add(Projections.property("imgFile"));
        cri.setProjection(projectionList);
        // get list
        List<Object[]> rows = cri.list();
        //
        List<AdPersonalFile> list = new ArrayList<>();
        for (Object[] row : rows) {
            AdPersonalFile aP = new AdPersonalFile();
            Long fileID = (Long) row[0];
            String fileName = (String) row[1];
            String imgFile = (String) row[2];
            aP.setFileID(fileID);
            aP.setFileName(fileName);
            aP.setImgFile(imgFile);
            list.add(aP);
        }
        return list;
    }

    public boolean deleteImg(Long fileID, String partnerID) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "UPDATE PartnerPersonalFile "
                + "SET imgStatus = :imgStatus "
                + "WHERE fileID = :fileID AND partnerID =:partnerID";
        Query query = session.createQuery(hql);
        query.setParameter("imgStatus", YTPartnerData.PARTNER_FILE_DELETED);
        query.setParameter("fileID", fileID);
        query.setParameter("partnerID", partnerID);
        int noR = query.executeUpdate();
        return noR > 0;
    }
}
