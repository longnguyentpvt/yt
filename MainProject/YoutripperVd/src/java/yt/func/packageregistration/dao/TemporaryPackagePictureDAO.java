/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.func.packageregistration.dao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import yt.entity.tbl.TemporaryPackagePicture;

/**
 *
 * @author nickn
 */
public class TemporaryPackagePictureDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }

    public List<TemporaryPackagePicture> getPackagePictures(long packageID) {
        Session session = sessionFactory.getCurrentSession();
        Criteria cri = session.createCriteria(TemporaryPackagePicture.class);

        cri.add(Restrictions.eq("packageID", packageID));

        ProjectionList projectionList = Projections.projectionList().add(Projections.property("pictureID")).add(Projections.property("pictureName"));
        cri.setProjection(projectionList);

        List<Object[]> rows = cri.list();

        List<TemporaryPackagePicture> pictures = new ArrayList<>();
        for (Object[] row : rows) {
            long pictureID = (long) row[0];
            String pictureName = (String) row[1];
            
            TemporaryPackagePicture aP = new TemporaryPackagePicture();
            aP.setPictureID(pictureID);
            aP.setPictureName(pictureName);
            pictures.add(aP);
        }
        return pictures;
    }

}
