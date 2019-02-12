/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.entity.view;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.Immutable;

/**
 *
 * @author macnew
 */
@Entity
@Immutable
@Table(name = "RememberLoginInfo (NOEXPAND)", schema = "dbo")
public class RememberLoginInfo implements Serializable {

    @Id
    @Column(name = "RememberToken")
    private String rememberToken;

    @Column(name = "PartnerID")
    private String partnerID;

    @Column(name = "PartnerName")
    private String partnerName;

    @Column(name = "PartnerAvatar")
    private String partnerAvatar;

    @Column(name = "PartnerStatus")
    private String partnerStatus;

    @Column(name = "TripperID")
    private String tripperID;

    @Column(name = "TripperName")
    private String tripperName;

    @Column(name = "TripperAvatar")
    private String tripperAvatar;

    @Column(name = "TripperStatus")
    private String tripperStatus;

    @Column(name = "AliveTime")
    private Long aliveTime;

    public RememberLoginInfo() {
    }

    public String getRememberToken() {
        return rememberToken;
    }

    public void setRememberToken(String rememberToken) {
        this.rememberToken = rememberToken;
    }

    public String getPartnerID() {
        return partnerID;
    }

    public void setPartnerID(String partnerID) {
        this.partnerID = partnerID;
    }

    public String getPartnerName() {
        return partnerName;
    }

    public void setPartnerName(String partnerName) {
        this.partnerName = partnerName;
    }

    public String getPartnerAvatar() {
        return partnerAvatar;
    }

    public void setPartnerAvatar(String partnerAvatar) {
        this.partnerAvatar = partnerAvatar;
    }

    public String getPartnerStatus() {
        return partnerStatus;
    }

    public void setPartnerStatus(String partnerStatus) {
        this.partnerStatus = partnerStatus;
    }

    public String getTripperID() {
        return tripperID;
    }

    public void setTripperID(String tripperID) {
        this.tripperID = tripperID;
    }

    public String getTripperName() {
        return tripperName;
    }

    public void setTripperName(String tripperName) {
        this.tripperName = tripperName;
    }

    public String getTripperAvatar() {
        return tripperAvatar;
    }

    public void setTripperAvatar(String tripperAvatar) {
        this.tripperAvatar = tripperAvatar;
    }

    public String getTripperStatus() {
        return tripperStatus;
    }

    public void setTripperStatus(String tripperStatus) {
        this.tripperStatus = tripperStatus;
    }

    public Long getAliveTime() {
        return aliveTime;
    }

    public void setAliveTime(Long aliveTime) {
        this.aliveTime = aliveTime;
    }

}
