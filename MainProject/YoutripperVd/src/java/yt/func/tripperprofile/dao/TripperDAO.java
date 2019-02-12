/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.func.tripperprofile.dao;

import javaclass.common.YTTripperData;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.beans.factory.annotation.Autowired;
import yt.entity.tbl.Country;
import yt.entity.tbl.State;
import yt.entity.tbl.Tripper;

/**
 *
 * @author Hiep
 */
public class TripperDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }

    public Tripper getPersonal(String tripperID) {
        Session session = sessionFactory.getCurrentSession();
        Criteria cri = session.createCriteria(Tripper.class, "tr");
        cri.createAlias("tr.country", "co", JoinType.LEFT_OUTER_JOIN);
        cri.createAlias("tr.state", "st", JoinType.LEFT_OUTER_JOIN);
        //condition
        cri.add(Restrictions.eq("tripperID", tripperID));
        //select
        ProjectionList projectionList = Projections.projectionList()
                .add(Projections.property("tr.firstName")).add(Projections.property("tr.lastName"))
                .add(Projections.property("tr.countryID")).add(Projections.property("tr.stateID"))
                .add(Projections.property("tr.personalAddress")).add(Projections.property("tr.personalCity"))
                .add(Projections.property("tr.postalCode")).add(Projections.property("tr.phoneCode"))
                .add(Projections.property("tr.phoneNumber")).add(Projections.property("tr.company"))
                .add(Projections.property("tr.taxNumber")).add(Projections.property("tr.gender"))
                .add(Projections.property("tr.email")).add(Projections.property("tr.accountType"))
                .add(Projections.property("co.countryName")).add(Projections.property("st.stateName"))
                .add(Projections.property("tr.displayName"));
        cri.setProjection(projectionList);
        //
        Object[] row = (Object[]) cri.uniqueResult();
        //get infor from database
        String firstName = (String) row[0];
        String lastName = (String) row[1];
        String countryID = (String) row[2];
        Long stateID = (Long) row[3];
        String personalAddress = (String) row[4];
        String personalCity = (String) row[5];
        String postalCode = (String) row[6];
        String phoneCode = (String) row[7];
        String phoneNumber = (String) row[8];
        String company = (String) row[9];
        String taxNumber = (String) row[10];
        Boolean gender = (Boolean) row[11];
        String email = (String) row[12];
        String accountType = (String) row[13];
        String countryName = (String) row[14];
        String stateName = (String) row[15];
        String displayName = (String) row[16];

        Country country = new Country();
        country.setCountryName(countryName);
        State state = new State();
        state.setStateName(stateName);

        Tripper tripper = new Tripper();
        tripper.setFirstName(firstName);
        tripper.setLastName(lastName);
        tripper.setGender(gender);
        tripper.setCountryID(countryID);
        tripper.setCountry(country);
        tripper.setStateID(stateID);
        tripper.setState(state);
        tripper.setPersonalCity(personalCity);
        tripper.setPersonalAddress(personalAddress);
        tripper.setPostalCode(postalCode);
        tripper.setPhoneCode(phoneCode);
        tripper.setPhoneNumber(phoneNumber);
        tripper.setCompany(company);
        tripper.setTaxNumber(taxNumber);
        tripper.setEmail(email);
        tripper.setAccountType(accountType);
        tripper.setDisplayName(displayName);

        return tripper;
    }

    public boolean updatePersonalInfor(String tripperID, String firstName, String lastName,
            Boolean gender, String countryID, Long stateID, String personalAddress, String personalCity,
            String postalCode, String phoneCode, String phoneNumber, String companyName, String taxNumber, String displayName) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "UPDATE Tripper "
                + "SET firstName = :firstName, "
                + "lastName = :lastName, "
                + "countryID = :countryID, "
                + "stateID = :stateID, "
                + "personalAddress = :personalAddress, "
                + "personalCity = :personalCity, "
                + "postalCode = :postalCode, "
                + "phoneCode = :phoneCode, "
                + "phoneNumber = :phoneNumber, "
                + "company = :companyName, "
                + "taxNumber = :taxNumber, "
                + "gender = :gender, "
                + "displayName = :displayName "
                + "WHERE tripperID = :tripperID";
        Query query = session.createQuery(hql);
        query.setParameter("firstName", firstName);
        query.setParameter("lastName", lastName);
        query.setParameter("gender", gender);
        query.setParameter("countryID", countryID);
        query.setParameter("stateID", stateID);
        query.setParameter("personalAddress", personalAddress);
        query.setParameter("personalCity", personalCity);
        query.setParameter("postalCode", postalCode);
        query.setParameter("phoneCode", phoneCode);
        query.setParameter("phoneNumber", phoneNumber);
        query.setParameter("companyName", companyName);
        query.setParameter("taxNumber", taxNumber);
        query.setParameter("displayName", displayName);
        query.setParameter("tripperID", tripperID);
        int noR = query.executeUpdate();
        return noR > 0;
    }

    public String getCurrentPassword(String tripperID) {
        Session session = sessionFactory.getCurrentSession();
        Criteria cri = session.createCriteria(Tripper.class);
        cri.add(Restrictions.eq("tripperID", tripperID));
        //select
        ProjectionList projectionList = Projections.projectionList()
                .add(Projections.property("password"));
        cri.setProjection(projectionList);
        //
        String opass = (String) cri.uniqueResult();
        return opass;
    }

    public boolean updatePassword(String tripperID, String newPassword) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "UPDATE Tripper "
                + "SET password = :password "
                + "WHERE tripperID = :tripperID";
        Query query = session.createQuery(hql);
        query.setParameter("password", newPassword);
        query.setParameter("tripperID", tripperID);
        int noR = query.executeUpdate();
        return noR > 0;
    }

    public Tripper getOptionalInformation(String tripperID) {
        Session session = sessionFactory.getCurrentSession();
        Criteria cri = session.createCriteria(Tripper.class);
        //condition
        cri.add(Restrictions.eq("tripperID", tripperID));
        //select clause
        ProjectionList projectionList = Projections.projectionList()
                .add(Projections.property("background")).add(Projections.property("preferedLanguageID"));
        cri.setProjection(projectionList);
        //get result
        Object[] row = (Object[]) cri.uniqueResult();
        //get infor from database
        String backGround = (String) row[0];
        String preferedLanguageID = (String) row[1];
        //set
        Tripper aP = new Tripper();
        aP.setBackground(backGround);
        aP.setPreferedLanguageID(preferedLanguageID);
        return aP;
    }

    public boolean updateOptional(String tripperID, String background, String preferedLanguageID) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "UPDATE Tripper "
                + "SET background = :background, preferedLanguageID = :preferedLanguageID "
                + "WHERE tripperID = :tripperID";
        Query query = session.createQuery(hql);
        query.setParameter("tripperID", tripperID);
        query.setParameter("background", background);
        query.setParameter("preferedLanguageID", preferedLanguageID);
        int noR = query.executeUpdate();
        return noR > 0;
    }

    public Boolean updateEmail(String tripperID, String newEmail) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "UPDATE Tripper "
                + "SET email = :email "
                + "WHERE tripperID = :tripperID AND (accountType =:fb OR accountType =:line) ";
        Query query = session.createQuery(hql);
        query.setParameter("email", newEmail);
        query.setParameter("tripperID", tripperID);
        query.setParameter("fb", YTTripperData.ACCOUNT_TYPE_FACEBOOK);
        query.setParameter("line", YTTripperData.ACCOUNT_TYPE_LINEPAY);
        int noR = query.executeUpdate();
        return noR > 0;
    }

}
