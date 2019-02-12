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
@Table(name = "TripperExistEmail (NOEXPAND)", schema = "dbo")
public class TripperExistEmail implements Serializable {

    @Id
    @Column(name = "Email")
    private String email;

    @Column(name = "TripperID")
    private String tripperID;

    public TripperExistEmail() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTripperID() {
        return tripperID;
    }

    public void setTripperID(String tripperID) {
        this.tripperID = tripperID;
    }

}
