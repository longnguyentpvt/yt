/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.entity.tbl;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author nickn
 */
@Entity
@Table(name = "PartnerRegistrationNumber", schema = "dbo")
public class PartnerRegistrationNumber implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PartnerNo")
    private long partnerNo;

    @Column(name = "RegistrationDateTime")
    private long registrationDateTime;

    public PartnerRegistrationNumber() {
    }

    public PartnerRegistrationNumber(long partnerNo, Long registrationDateTime) {
        this.partnerNo = partnerNo;
        this.registrationDateTime = registrationDateTime;
    }

    public long getPartnerNo() {
        return partnerNo;
    }

    public void setPartnerNo(long partnerNo) {
        this.partnerNo = partnerNo;
    }

    public long getRegistrationDateTime() {
        return registrationDateTime;
    }

    public void setRegistrationDateTime(long registrationDateTime) {
        this.registrationDateTime = registrationDateTime;
    }

}
