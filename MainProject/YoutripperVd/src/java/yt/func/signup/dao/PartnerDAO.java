/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.func.signup.dao;

import javaclass.common.YTPartnerData;
import javaclass.utility.YoutripperIDUtility;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import yt.entity.tbl.Partner;
import yt.entity.view.AbleResendPartnerActivation;
import yt.entity.view.PartnerActivation;
import yt.entity.view.PartnerExistEmail;

/**
 *
 * @author nickn
 */
public class PartnerDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }

    public boolean checkEmailExists(String email) {
        //get session
        Session session = sessionFactory.getCurrentSession();
        Criteria cri = session.createCriteria(PartnerExistEmail.class);

        cri.add(Restrictions.eq("email", email));
        cri.setProjection(Projections.property("partnerID"));

        String partnerID = (String) cri.uniqueResult();

        return partnerID != null;
    }

    public synchronized boolean registerNewPartner(String partnerID, String businessType, String businessName, String countryID, long state,
            String address, String phoneCode, String phone, String postCode, String firstName, String lastName, String displayName,
            String jobPosition, String email, String password, String signupToken, long signupTime,
            String city) {
        Session session = sessionFactory.getCurrentSession();

        if (partnerID != null) {
            //Create new partner object
            Partner partner = new Partner();
            partner.setPartnerID(partnerID);
            partner.setBusinessType(businessType);
            partner.setBusinessName(businessName);
            partner.setJobPosition(jobPosition);
            partner.setDisplayName(displayName);
            partner.setFirstName(firstName);
            partner.setLastName(lastName);
            partner.setCountryID(countryID);
            partner.setStateID(state);
            partner.setBusinessCity(city);
            partner.setPostalCode(postCode);
            partner.setBusinessAddress(address);
            partner.setPhoneCode(phoneCode);
            partner.setPhoneNumber(phone);
            partner.setEmail(email);
            partner.setPassword(password);
            partner.setRegistrationDateTime(signupTime);
            partner.setRegistrationToken(signupToken);
            partner.setAccountType(YTPartnerData.ACCOUNT_TYPE_YT);
            partner.setAccountStatus(YTPartnerData.ACCOUNT_STATUS_NON_ACTIVED);
            partner.setVerficationStatus(YTPartnerData.ACCOUNT_VERIFICATION_STATUS_NOT_UPLOADED);
            partner.setBankAccountStatus(YTPartnerData.ACCOUNT_BANK_STATUS_NOT_UPLOADED);

            session.persist(partner);
            session.flush();

            return true;
        }
        return false;
    }

    public AbleResendPartnerActivation getPartnerResendActivationInfo(String registrationToken) {
        Session session = sessionFactory.getCurrentSession();
        Criteria cri = session.createCriteria(AbleResendPartnerActivation.class);

        cri.add(Restrictions.eq("registrationToken", registrationToken));

        return (AbleResendPartnerActivation) cri.uniqueResult();
    }

    public PartnerActivation getPartnerActivationInfoByToken(String registrationToken) {
        Session session = sessionFactory.getCurrentSession();
        Criteria cri = session.createCriteria(PartnerActivation.class);

        cri.add(Restrictions.eq("registrationToken", registrationToken));

        return (PartnerActivation) cri.uniqueResult();
    }

    public void activatePartnerAccount(String partnerID) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "UPDATE Partner "
                + "SET accountStatus = :accountStatus "
                + "WHERE partnerID = :partnerID";

        Query query = session.createQuery(hql);
        query.setParameter("partnerID", partnerID);
        query.setParameter("accountStatus", YTPartnerData.ACCOUNT_STATUS_ACTIVED);

        query.executeUpdate();
    }
}
