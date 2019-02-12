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
import yt.entity.tbl.TripperCreditCard;

/**
 *
 * @author Hiep
 */
public class TripperCreditCardDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }

    public List<TripperCreditCard> listCards(String tripperID) {
        Session session = sessionFactory.getCurrentSession();
        Criteria cri = session.createCriteria(TripperCreditCard.class);
        //condition
        cri.add(Restrictions.eq("tripperID", tripperID));
        cri.add(Restrictions.eq("status", YTTripperData.CREDIT_CARD_STATUS_ACTIVE));
        //
        ProjectionList projectionList = Projections.projectionList()
                .add(Projections.property("holderName")).add(Projections.property("shownNumber"))
                .add(Projections.property("expirationDate")).add(Projections.property("cardID"));
        cri.setProjection(projectionList);

        List<Object[]> rows = cri.list();
        List<TripperCreditCard> reCreditCards = new ArrayList<>();
        for (Object[] row : rows) {
            TripperCreditCard tripperBillingAddress = new TripperCreditCard();
            String holderName = (String) row[0];
            String shownNumber = (String) row[1];
            Long expirationDate = (Long) row[2];
            Long cardID = (Long) row[3];
            tripperBillingAddress.setHolderName(holderName);
            tripperBillingAddress.setShownNumber(shownNumber);
            tripperBillingAddress.setExpirationDate(expirationDate);
            tripperBillingAddress.setCardID(cardID);
            reCreditCards.add(tripperBillingAddress);
        }
        return reCreditCards;
    }

    public boolean removeCard(String tripperID, Long cardID) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "UPDATE TripperCreditCard "
                + "SET status = :status "
                + "WHERE tripperID = :tripperID AND cardID = :cardID ";
        Query query = session.createQuery(hql);
        query.setParameter("status", YTTripperData.CREDIT_CARD_STATUS_DELETE);
        query.setParameter("cardID", cardID);
        query.setParameter("tripperID", tripperID);
        int noR = query.executeUpdate();
        return noR > 0;
    }
}
