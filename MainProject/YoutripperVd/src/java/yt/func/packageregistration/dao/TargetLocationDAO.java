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
import yt.entity.tbl.TargetLocationContent;

/**
 *
 * @author nickn
 */
public class TargetLocationDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }

    public List<TargetLocationContent> getList(String languageCode) {
        Session session = sessionFactory.getCurrentSession();
        Criteria cri = session.createCriteria(TargetLocationContent.class);

        cri.add(Restrictions.eq("languageCode", languageCode));

        ProjectionList projectionList = Projections.projectionList().add(Projections.property("targetLocationID"))
                .add(Projections.property("locationName"));
        cri.setProjection(projectionList);

        List<Object[]> rows = cri.list();
        List<TargetLocationContent> locations = new ArrayList<>();
        for (Object[] row : rows) {
            TargetLocationContent aL = new TargetLocationContent();
            int lID = (int) row[0];
            String lName = (String) row[1];
            aL.setTargetLocationID(lID);
            aL.setLocationName(lName);
            
            locations.add(aL);
        }
        
        return locations;
    }
}
