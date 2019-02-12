/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.func.signup.dao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import yt.entity.tbl.PartnerState;

/**
 *
 * @author nickn
 */
public class PartnerStateDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }

    public List<PartnerState> getAllStatesFromACountry(String countryID) {
        //get session
        Session session = sessionFactory.getCurrentSession();
        Criteria cri = session.createCriteria(PartnerState.class);

        ProjectionList projectionList = Projections.projectionList()
                .add(Projections.property("stateID"))
                .add(Projections.property("stateName"));

        cri.add(Restrictions.eq("countryID", countryID));

        cri.setProjection(projectionList);
        List<Object[]> rows = cri.list();
        List<PartnerState> states = new ArrayList<>();
        rows.stream().map((row) -> {
            long stateID = (long) row[0];
            String stateName = (String) row[1];
            PartnerState aState = new PartnerState();
            aState.setStateID(stateID);
            aState.setStateName(stateName);
            return aState;
        }).forEachOrdered((aState) -> {
            states.add(aState);
        });

        return states;
    }
}
