/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.func.tripperprofile.dao;

import java.util.ArrayList;
import java.util.List;
import javaclass.common.YTData;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import yt.entity.tbl.Country;
import yt.entity.tbl.State;

/**
 *
 * @author Hiep
 */
public class CountryDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }

    public List<Country> getAllCountries() {
        //get session
        Session session = sessionFactory.getCurrentSession();
        Criteria cri = session.createCriteria(Country.class);
        cri.add(Restrictions.ne("countryID", YTData.DELETED_COUNTRY_ID));

        ProjectionList projectionList = Projections.projectionList()
                .add(Projections.property("countryID"))
                .add(Projections.property("countryName"))
                .add(Projections.property("phoneCode"));

        cri.setProjection(projectionList);
        List<Object[]> rows = cri.list();

        List<Country> countries = new ArrayList<>();
        for (Object[] row : rows) {
            String countryID = (String) row[0];
            String countryName = (String) row[1];
            String phoneCode = (String) row[2];
            Country aCountry = new Country();
            aCountry.setCountryID(countryID);
            aCountry.setCountryName(countryName);
            aCountry.setPhoneCode(phoneCode);
            countries.add(aCountry);
        }
        return countries;
    }

    public List<State> getAllStatesFromACountry(String countryID) {
        //get session
        Session session = sessionFactory.getCurrentSession();
        Criteria cri = session.createCriteria(State.class);

        ProjectionList projectionList = Projections.projectionList()
                .add(Projections.property("stateID"))
                .add(Projections.property("stateName"));

        cri.add(Restrictions.eq("countryID", countryID));

        cri.setProjection(projectionList);
        List<Object[]> rows = cri.list();
        List<State> states = new ArrayList<>();
        rows.stream().map((row) -> {
            long stateID = (long) row[0];
            String stateName = (String) row[1];
            State aState = new State();
            aState.setStateID(stateID);
            aState.setStateName(stateName);
            return aState;
        }).forEachOrdered((aState) -> {
            states.add(aState);
        });

        return states;
    }
}
