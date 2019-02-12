/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.func.partnerverification.dao;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import yt.entity.tbl.Partner;
import yt.func.partnerverification.javaclass.BankCommonData;
import yt.func.partnerverification.javaclass.VerificationCommonData;

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

    public VerificationCommonData getVerificationData(String partnerID) {
        Session session = sessionFactory.getCurrentSession();
        Criteria cri = session.createCriteria(Partner.class);
        //condition
        cri.add(Restrictions.eq("partnerID", partnerID));
        //select
        ProjectionList projectionList = Projections.projectionList().add(Projections.property("verificationID"))
                .add(Projections.property("verficationStatus"));
        cri.setProjection(projectionList);
        //
        Object[] row = (Object[]) cri.uniqueResult();
        VerificationCommonData aP = new VerificationCommonData();
        String verificationID = (String) row[0];
        String verficationStatus = (String) row[1];
        aP.setVerificationID(verificationID);
        aP.setVerificationStatus(verficationStatus);
        return aP;
    }

    public BankCommonData getBankData(String partnerID) {
        Session session = sessionFactory.getCurrentSession();
        Criteria cri = session.createCriteria(Partner.class);
        //condition
        cri.add(Restrictions.eq("partnerID", partnerID));
        //select
        ProjectionList projectionList = Projections.projectionList().add(Projections.property("bankID"))
                .add(Projections.property("bankAccountName")).add(Projections.property("bankAccountNumber"))
                .add(Projections.property("bankAccountStatus"));
        cri.setProjection(projectionList);
        //
        Object[] row = (Object[]) cri.uniqueResult();
        BankCommonData data = new BankCommonData();
        String bankID = (String) row[0];
        String bankAccountName = (String) row[1];
        String bankAccountNumber = (String) row[2];
        String bankAccountStatus = (String) row[3];
        data.setBankID(bankID);
        data.setBankAccountName(bankAccountName);
        data.setBankAccountNumber(bankAccountNumber);
        data.setBankAccountStatus(bankAccountStatus);
        return data;
    }

    public String getVerificationStatus(String partnerID) {
        Session session = sessionFactory.getCurrentSession();
        Criteria cri = session.createCriteria(Partner.class);
        //condition
        cri.add(Restrictions.eq("partnerID", partnerID));
        //select
        ProjectionList projectionList = Projections.projectionList()
                .add(Projections.property("verficationStatus"));
        cri.setProjection(projectionList);
        return (String) cri.uniqueResult();
    }

    public String getBankStatus(String partnerID) {
        Session session = sessionFactory.getCurrentSession();
        Criteria cri = session.createCriteria(Partner.class);
        //condition
        cri.add(Restrictions.eq("partnerID", partnerID));
        //select
        ProjectionList projectionList = Projections.projectionList()
                .add(Projections.property("bankAccountStatus"));
        cri.setProjection(projectionList);
        return (String) cri.uniqueResult();
    }

    public boolean updatePersonalVerification(String partnerID, String taxID, String status) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "UPDATE Partner "
                + "SET verificationID = :verificationID, "
                + "verficationStatus = :verficationStatus "
                + "WHERE partnerID = :partnerID";
        Query query = session.createQuery(hql);
        query.setParameter("verificationID", taxID);
        query.setParameter("verficationStatus", status);
        query.setParameter("partnerID", partnerID);
        int noR = query.executeUpdate();
        return noR > 0;
    }

    public boolean updateBankVerification(String partnerID, String bankID, String bankAccountName,
            String bankAccountNumber, String status) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "UPDATE Partner "
                + "SET bankID = :bankID, "
                + "bankAccountName = :bankAccountName, "
                + "bankAccountNumber = :bankAccountNumber, "
                + "bankAccountStatus = :bankAccountStatus "
                + "WHERE partnerID = :partnerID";
        Query query = session.createQuery(hql);
        query.setParameter("bankID", bankID);
        query.setParameter("bankAccountName", bankAccountName);
        query.setParameter("bankAccountNumber", bankAccountNumber);
        query.setParameter("bankAccountStatus", status);
        query.setParameter("partnerID", partnerID);
        int noR = query.executeUpdate();
        return noR > 0;
    }

}
