/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.entity.tbl;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author nickn
 */
@Entity
@Table(name = "InvoiceGeneration", schema = "dbo")
public class InvoiceGeneration implements Serializable {

    @Id
    @Column(name = "InvoiceID", unique = true, nullable = false)
    private String invoiceID;

    @Column(name = "InvoiceMonth")
    private int invoiceMonth;

    @Column(name = "GeneratedDate")
    private long generatedDate;

    public InvoiceGeneration() {
    }

    public String getInvoiceID() {
        return invoiceID;
    }

    public void setInvoiceID(String invoiceID) {
        this.invoiceID = invoiceID;
    }

    public int getInvoiceMonth() {
        return invoiceMonth;
    }

    public void setInvoiceMonth(int invoiceMonth) {
        this.invoiceMonth = invoiceMonth;
    }

    public long getGeneratedDate() {
        return generatedDate;
    }

    public void setGeneratedDate(long generatedDate) {
        this.generatedDate = generatedDate;
    }

}
