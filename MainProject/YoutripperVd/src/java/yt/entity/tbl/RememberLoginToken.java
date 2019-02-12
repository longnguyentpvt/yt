package yt.entity.tbl;
// Generated Jan 13, 2017 3:31:54 PM by Hibernate Tools 4.3.1

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * PartnerCountry generated by hbm2java
 */
@Entity
@Table(name = "RememberLoginToken", schema = "dbo")
public class RememberLoginToken implements Serializable {

    @Id
    @Column(name = "RememberToken", unique = true, nullable = false)
    private String rememberToken;

    @Column(name = "PartnerID")
    private String partnerID;

    @Column(name = "TripperID")
    private String tripperID;

    @Column(name = "AliveTime")
    private Long aliveTime;

    public RememberLoginToken() {
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

    public String getTripperID() {
        return tripperID;
    }

    public void setTripperID(String tripperID) {
        this.tripperID = tripperID;
    }

    public Long getAliveTime() {
        return aliveTime;
    }

    public void setAliveTime(Long aliveTime) {
        this.aliveTime = aliveTime;
    }

}