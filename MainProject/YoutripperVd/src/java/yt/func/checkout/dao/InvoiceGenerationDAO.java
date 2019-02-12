/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.func.checkout.dao;

import javaclass.utility.YoutripperIDUtility;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import yt.entity.tbl.InvoiceGeneration;

/**
 *
 * @author nickn
 */
public class InvoiceGenerationDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }

    public synchronized String getInvoiceID(int month, DateTime generatedDateTime) {
        Session session = sessionFactory.getCurrentSession();
        Criteria cri = session.createCriteria(InvoiceGeneration.class);
        cri.add(Restrictions.eq("invoiceMonth", month));

        cri.setProjection(Projections.count("invoiceID"));

        long total = (long) cri.uniqueResult();
        total++;
        
        long gnDT = generatedDateTime.getMillis();
        
        String invoiceID = YoutripperIDUtility.generateInvoiceID(total, generatedDateTime);
        InvoiceGeneration in = new InvoiceGeneration();
        in.setInvoiceID(invoiceID);
        in.setInvoiceMonth(month);
        in.setGeneratedDate(gnDT);
        
        session.persist(in);
        session.flush();
        
        return invoiceID;
    }
}
