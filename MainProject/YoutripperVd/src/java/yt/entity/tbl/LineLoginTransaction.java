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
@Table(name = "LineLoginTransaction", schema = "dbo")
public class LineLoginTransaction implements Serializable {

    @Id
    @Column(name = "TransactionID", unique = true, nullable = false)
    private String transactionID;

    @Column(name = "TransactionDateTime")
    private long transactionDateTime;

    @Column(name = "Completed")
    private boolean completed;

    @Column(name = "DirectURL")
    private String directURL;

    public LineLoginTransaction() {
    }

    public String getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }

    public long getTransactionDateTime() {
        return transactionDateTime;
    }

    public void setTransactionDateTime(long transactionDateTime) {
        this.transactionDateTime = transactionDateTime;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public String getDirectURL() {
        return directURL;
    }

    public void setDirectURL(String directURL) {
        this.directURL = directURL;
    }

}
