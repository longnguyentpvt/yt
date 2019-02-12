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
@Table(name = "TripperLinepayLoginInfo (NOEXPAND)", schema = "dbo")
public class TripperLinepayLoginInfo implements Serializable {

    @Id
    @Column(name = "LineID")
    private String lineID;

    @Column(name = "TripperID")
    private String tripperID;

    @Column(name = "DisplayName")
    private String displayName;

    @Column(name = "Avatar")
    private String avatar;

    public TripperLinepayLoginInfo() {
    }

    public String getLineID() {
        return lineID;
    }

    public void setLineID(String lineID) {
        this.lineID = lineID;
    }

    public String getTripperID() {
        return tripperID;
    }

    public void setTripperID(String tripperID) {
        this.tripperID = tripperID;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

}
