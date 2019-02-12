/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.entity.tbl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author IDD_LENOVO
 */
@Entity
@Table(name = "PartnerContact", schema = "dbo")
public class PartnerContact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PartnerID")
    private String partnerID;

    @Column(name = "Email")
    private String email;

    @Column(name = "SignupTime")
    private long signupTime;

    public PartnerContact() {
    }

    public PartnerContact(String partnerID, String email, long signupTime) {
        this.partnerID = partnerID;
        this.email = email;
        this.signupTime = signupTime;
    }
    
    public String getPartnerID() {
        return partnerID;
    }

    public void setPartnerID(String partnerID) {
        this.partnerID = partnerID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getSignupTime() {
        return signupTime;
    }

    public void setSignupTime(long signupTime) {
        this.signupTime = signupTime;
    }
    
    
}
