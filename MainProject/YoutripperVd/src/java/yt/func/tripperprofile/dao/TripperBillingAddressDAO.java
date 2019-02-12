/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.func.tripperprofile.dao;

import java.util.ArrayList;
import java.util.List;
import javaclass.common.YTTripperData;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import yt.entity.tbl.TripperBillingAddress;

/**
 *
 * @author Hiep
 */
public class TripperBillingAddressDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }

    public List<TripperBillingAddress> listBillings(String tripperID) {
        Session session = sessionFactory.getCurrentSession();
        Criteria cri = session.createCriteria(TripperBillingAddress.class);
        //condition
        cri.add(Restrictions.eq("tripperID", tripperID));
        cri.add(Restrictions.eq("status", YTTripperData.BILLING_ADDRESS_STATUS_ACTIVE));
        //select
        ProjectionList projectionList = Projections.projectionList().add(Projections.property("billingID"))
                .add(Projections.property("billingName")).add(Projections.property("firstName"))
                .add(Projections.property("lastName")).add(Projections.property("address"));
        cri.setProjection(projectionList);
        //
        List<Object[]> rows = cri.list();
        List<TripperBillingAddress> billingAddresses = new ArrayList<>();
        for (Object[] row : rows) {
            TripperBillingAddress aP = new TripperBillingAddress();
            Long billingID = (Long) row[0];
            String billingName = (String) row[1];
            String firstName = (String) row[2];
            String lastName = (String) row[3];
            String address = (String) row[4];
            //
            aP.setBillingID(billingID);
            aP.setBillingName(billingName);
            aP.setFirstName(firstName);
            aP.setLastName(lastName);
            aP.setAddress(address);

            billingAddresses.add(aP);
        }
        return billingAddresses;
    }

    public boolean removeBilling(String tripperID, Long billingID) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "UPDATE TripperBillingAddress "
                + "SET status = :status "
                + "WHERE tripperID = :tripperID AND billingID = :billingID ";
        Query query = session.createQuery(hql);
        query.setParameter("status", YTTripperData.BILLING_ADDRESS_STATUS_DELETE);
        query.setParameter("billingID", billingID);
        query.setParameter("tripperID", tripperID);
        int noR = query.executeUpdate();
        return noR > 0;
    }

    public TripperBillingAddress getBillingDetail(Long billingID, String tripperID) {
        Session session = sessionFactory.getCurrentSession();
        Criteria cri = session.createCriteria(TripperBillingAddress.class);
        //condition
        cri.add(Restrictions.eq("tripperID", tripperID));
        cri.add(Restrictions.eq("billingID", billingID));
        cri.add(Restrictions.eq("status", YTTripperData.BILLING_ADDRESS_STATUS_ACTIVE));
        ProjectionList projectionList = Projections.projectionList()
                .add(Projections.property("billingName")).add(Projections.property("firstName"))
                .add(Projections.property("lastName")).add(Projections.property("branch"))
                .add(Projections.property("taxID")).add(Projections.property("address"));
        cri.setProjection(projectionList);

        TripperBillingAddress tripperBillingAddress = new TripperBillingAddress();
        Object[] row = (Object[]) cri.uniqueResult();
        String billingName = (String) row[0];
        String firstName = (String) row[1];
        String lastName = (String) row[2];
        String branch = (String) row[3];
        String taxID = (String) row[4];
        String address = (String) row[5];
        tripperBillingAddress.setBillingName(billingName);
        tripperBillingAddress.setFirstName(firstName);
        tripperBillingAddress.setLastName(lastName);
        tripperBillingAddress.setBranch(branch);
        tripperBillingAddress.setTaxID(taxID);
        tripperBillingAddress.setAddress(address);

        return tripperBillingAddress;
    }
}
