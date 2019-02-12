/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.func.packageregistration.dao;

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
import yt.entity.tbl.TemporaryPackageContent;

/**
 *
 * @author nickn
 */
public class TemporaryPackageContentDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }

    public void registerDefaultContent(long packageID, String languageCode, boolean registeredContent) {
        Session session = sessionFactory.getCurrentSession();

        TemporaryPackageContent content = new TemporaryPackageContent();
        content.setPackageID(packageID);
        content.setLanguageCode(languageCode);
        content.setRegisteredContent(registeredContent);

        session.persist(content);
    }

    public List<TemporaryPackageContent> getRegistrationContent(long packageID) {
        Session session = sessionFactory.getCurrentSession();
        Criteria cri = session.createCriteria(TemporaryPackageContent.class);

        cri.add(Restrictions.eq("packageID", packageID));

        ProjectionList projectionList = Projections.projectionList().add(Projections.property("languageCode"))
                .add(Projections.property("packageName")).add(Projections.property("googleDescription")).add(Projections.property("multiDescription"))
                .add(Projections.property("activityLocations")).add(Projections.property("departureLocations")).add(Projections.property("pickupLocations"))
                .add(Projections.property("keywords")).add(Projections.property("registeredContent"));
        cri.setProjection(projectionList);

        List<Object[]> rows = cri.list();

        List<TemporaryPackageContent> contents = new ArrayList<>();
        for (Object[] row : rows) {
            String languageCode = (String) row[0];
            String packageName = (String) row[1];
            String googleDescription = (String) row[2];
            String multiDescription = (String) row[3];
            String activityLocations = (String) row[4];
            String departureLocations = (String) row[5];
            String pickupLocations = (String) row[6];
            String kws = (String) row[7];
            boolean registeredContent = (boolean) row[8];

            TemporaryPackageContent aC = new TemporaryPackageContent();
            aC.setLanguageCode(languageCode);
            aC.setPackageName(packageName);
            aC.setGoogleDescription(googleDescription);
            aC.setMultiDescription(multiDescription);
            aC.setActivityLocations(activityLocations);
            aC.setDepartureLocations(departureLocations);
            aC.setPickupLocations(pickupLocations);
            aC.setKeywords(kws);
            aC.setRegisteredContent(registeredContent);
            contents.add(aC);
        }
        return contents;
    }

    public boolean updatePackageName(long packageID, String packageName, String languageCode) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "UPDATE TemporaryPackageContent "
                + "SET packageName = :packageName "
                + "WHERE packageID = :packageID AND languageCode = :languageCode ";
        Query query = session.createQuery(hql);
        query.setParameter("packageName", packageName);
        query.setParameter("packageID", packageID);
        query.setParameter("languageCode", languageCode);

        int noR = query.executeUpdate();
        return noR > 0;
    }

    public boolean updateMultiDescription(long packageID, String multiDescription, String languageCode) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "UPDATE TemporaryPackageContent "
                + "SET multiDescription = :multiDescription "
                + "WHERE packageID = :packageID AND languageCode = :languageCode ";
        Query query = session.createQuery(hql);
        query.setParameter("multiDescription", multiDescription);
        query.setParameter("packageID", packageID);
        query.setParameter("languageCode", languageCode);

        int noR = query.executeUpdate();
        return noR > 0;
    }

    public boolean updateActivityLocation(long packageID, String activityLocations, String languageCode) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "UPDATE TemporaryPackageContent "
                + "SET activityLocations = :activityLocations "
                + "WHERE packageID = :packageID AND languageCode = :languageCode";
        Query query = session.createQuery(hql);
        query.setParameter("activityLocations", activityLocations);
        query.setParameter("packageID", packageID);
        query.setParameter("languageCode", languageCode);

        int noR = query.executeUpdate();
        return noR > 0;
    }

    public boolean updateDepartureLocation(long packageID, String departureLocations, String languageCode) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "UPDATE TemporaryPackageContent "
                + "SET departureLocations = :departureLocations "
                + "WHERE packageID = :packageID AND languageCode = :languageCode";
        Query query = session.createQuery(hql);
        query.setParameter("departureLocations", departureLocations);
        query.setParameter("packageID", packageID);
        query.setParameter("languageCode", languageCode);

        int noR = query.executeUpdate();
        return noR > 0;
    }

    public boolean updatePickupLocation(long packageID, String pickupLocations, String languageCode) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "UPDATE TemporaryPackageContent "
                + "SET pickupLocations = :pickupLocations "
                + "WHERE packageID = :packageID AND languageCode = :languageCode";
        Query query = session.createQuery(hql);
        query.setParameter("pickupLocations", pickupLocations);
        query.setParameter("packageID", packageID);
        query.setParameter("languageCode", languageCode);

        int noR = query.executeUpdate();
        return noR > 0;
    }

    public boolean updateKeywords(long packageID, String keywords, String languageCode) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "UPDATE TemporaryPackageContent "
                + "SET keywords = :keywords "
                + "WHERE packageID = :packageID AND languageCode = :languageCode";
        Query query = session.createQuery(hql);
        query.setParameter("keywords", keywords);
        query.setParameter("packageID", packageID);
        query.setParameter("languageCode", languageCode);

        int noR = query.executeUpdate();
        return noR > 0;
    }
    
     public boolean updateSEODescription(long packageID, String googleDescription, String languageCode) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "UPDATE TemporaryPackageContent "
                + "SET googleDescription = :googleDescription "
                + "WHERE packageID = :packageID AND languageCode = :languageCode";
        Query query = session.createQuery(hql);
        query.setParameter("googleDescription", googleDescription);
        query.setParameter("packageID", packageID);
        query.setParameter("languageCode", languageCode);

        int noR = query.executeUpdate();
        return noR > 0;
    }

    public boolean activeLanguage(long packageID, String languageCode) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "UPDATE TemporaryPackageContent "
                + "SET registeredContent = :registeredContent "
                + "WHERE packageID = :packageID AND languageCode = :languageCode ";
        Query query = session.createQuery(hql);
        query.setParameter("registeredContent", true);
        query.setParameter("packageID", packageID);
        query.setParameter("languageCode", languageCode);

        int noR = query.executeUpdate();
        return noR > 0;
    }

    public boolean unactiveLanguage(long packageID, String languageCode) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "UPDATE TemporaryPackageContent "
                + "SET registeredContent = :registeredContent "
                + "WHERE packageID = :packageID AND languageCode = :languageCode ";
        Query query = session.createQuery(hql);
        query.setParameter("registeredContent", false);
        query.setParameter("packageID", packageID);
        query.setParameter("languageCode", languageCode);

        int noR = query.executeUpdate();
        return noR > 0;
    }

}
