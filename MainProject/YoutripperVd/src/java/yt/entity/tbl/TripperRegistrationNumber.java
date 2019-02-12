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
@Table(name = "TripperRegistrationNumber", schema = "dbo")
public class TripperRegistrationNumber implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TripperNo")
    private long tripperNo;

    @Column(name = "RegistrationDateTime")
    private long registrationDateTime;

    public TripperRegistrationNumber() {
    }

    public long getTripperNo() {
        return tripperNo;
    }

    public void setTripperNo(long tripperNo) {
        this.tripperNo = tripperNo;
    }

    public long getRegistrationDateTime() {
        return registrationDateTime;
    }

    public void setRegistrationDateTime(long registrationDateTime) {
        this.registrationDateTime = registrationDateTime;
    }

}
