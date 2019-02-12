/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.func.partnerprofile.dao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import yt.entity.tbl.Country;
import yt.entity.tbl.Partner;
import yt.entity.tbl.PartnerState;
import yt.func.partnerprofile.javaclass.PartnerBusinesslResponse;
import yt.func.partnerprofile.javaclass.PartnerPersonalResponse;

/**
 *
 * @author Hiep
 */
public class PartnerDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }

    public PartnerPersonalResponse getPartnerPersonalInfor(String partnerID) {
        Session session = sessionFactory.getCurrentSession();
        Criteria cri = session.createCriteria(Partner.class);
        //condition
        cri.add(Restrictions.eq("partnerID", partnerID));
        //select
        ProjectionList projectionList = Projections.projectionList().add(Projections.property("firstName"))
                .add(Projections.property("lastName")).add(Projections.property("jobPosition")).add(Projections.property("email"));
        cri.setProjection(projectionList);
        //
        Object[] row = (Object[]) cri.uniqueResult();
        PartnerPersonalResponse aP = new PartnerPersonalResponse();
        String fn = (String) row[0];
        String ln = (String) row[1];
        String jp = (String) row[2];
        String e = (String) row[3];
        aP.setFirstName(fn);
        aP.setLastName(ln);
        aP.setPosition(jp);
        aP.setEmail(e);
        return aP;
    }

    public boolean updatePersonalInfor(String partnerID, String firstName, String lastName, String jobPosition) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "UPDATE Partner "
                + "SET firstName = :firstName, "
                + "lastName = :lastName, "
                + "jobPosition = :jobPosition "
                + "WHERE partnerID = :partnerID";
        Query query = session.createQuery(hql);
        query.setParameter("firstName", firstName);
        query.setParameter("lastName", lastName);
        query.setParameter("jobPosition", jobPosition);
        query.setParameter("partnerID", partnerID);
        int noR = query.executeUpdate();
        return noR > 0;
    }

    public String getCurrentPassword(String partnerID) {
        Session session = sessionFactory.getCurrentSession();
        Criteria cri = session.createCriteria(Partner.class);
        cri.add(Restrictions.eq("partnerID", partnerID));
        //select
        ProjectionList projectionList = Projections.projectionList()
                .add(Projections.property("password"));
        cri.setProjection(projectionList);
        //
        String opass = (String) cri.uniqueResult();
        return opass;
    }

    public boolean updatePassword(String partnerID, String newPassword) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "UPDATE Partner "
                + "SET password = :password "
                + "WHERE partnerID = :partnerID";
        Query query = session.createQuery(hql);
        query.setParameter("password", newPassword);
        query.setParameter("partnerID", partnerID);
        int noR = query.executeUpdate();
        return noR > 0;
    }

    public PartnerBusinesslResponse getPartnerBusinessInfor(String partnerID) {
        Session session = sessionFactory.getCurrentSession();
        Criteria cri = session.createCriteria(Partner.class);
        //condition
        cri.add(Restrictions.eq("partnerID", partnerID));
        //select
        ProjectionList projectionList = Projections.projectionList()
                .add(Projections.property("businessName")).add(Projections.property("businessType"))
                .add(Projections.property("countryID")).add(Projections.property("stateID"))
                .add(Projections.property("businessAddress")).add(Projections.property("businessCity"))
                .add(Projections.property("postalCode")).add(Projections.property("phoneCode"))
                .add(Projections.property("phoneNumber")).add(Projections.property("businessBackground"));
        cri.setProjection(projectionList);
        //
        Object[] row = (Object[]) cri.uniqueResult();
        //get infor from database
        String businessName = (String) row[0];
        String businessType = (String) row[1];
        String countryID = (String) row[2];
        Long stateID = (Long) row[3];
        String businessAddress = (String) row[4];
        String businessCity = (String) row[5];
        String postalCode = (String) row[6];
        String phoneCode = (String) row[7];
        String phoneNumber = (String) row[8];
        String businessBackground = (String) row[9];

        // COUNTRY
        cri = session.createCriteria(Country.class);
        //condition
        cri.add(Restrictions.eq("countryID", countryID));
        Country country = (Country) cri.uniqueResult();
        String countryName = country.getCountryName();

        // PartnerState
        cri = session.createCriteria(PartnerState.class);
        //condition
        cri.add(Restrictions.eq("countryID", countryID));
        cri.add(Restrictions.eq("stateID", stateID));
        PartnerState city = (PartnerState) cri.uniqueResult();
        String stateName = city.getStateName();

        //
        List<PartnerState> cities = new ArrayList<>();
        PartnerBusinesslResponse aP = new PartnerBusinesslResponse(businessName, businessType, countryID,
                stateID, businessAddress, businessCity, postalCode, phoneCode, phoneNumber, businessBackground, countryName,
                stateName, cities);
        return aP;
    }

    public boolean updateBusinessInfor(String partnerID, String countryID, Long stateID, String businessAddress, String businessCity,
            String postalCode, String phoneCode, String phoneNumber, String businessBackGround) {

        Session session = sessionFactory.getCurrentSession();
        String hql = "UPDATE Partner "
                + "SET countryID = :countryID, "
                + "stateID = :stateID, "
                + "businessAddress = :businessAddress, "
                + "businessCity = :businessCity, "
                + "postalCode = :postalCode, "
                + "phoneCode = :phoneCode, "
                + "phoneNumber = :phoneNumber, "
                + "businessBackGround = :businessBackGround "
                + "WHERE partnerID = :partnerID";
        Query query = session.createQuery(hql);
        query.setParameter("countryID", countryID);
        query.setParameter("stateID", stateID);
        query.setParameter("businessAddress", businessAddress);
        query.setParameter("businessCity", businessCity);
        query.setParameter("postalCode", postalCode);
        query.setParameter("phoneCode", phoneCode);
        query.setParameter("phoneNumber", phoneNumber);
        query.setParameter("businessBackGround", businessBackGround);
        query.setParameter("partnerID", partnerID);
        int noR = query.executeUpdate();
        return noR > 0;
    }

}
