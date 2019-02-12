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
@Table(name = "TargetLocation", schema = "dbo")
public class TargetLocation implements Serializable {

    @Id
    @Column(name = "TargetLocationID")
    private int targetLocationID;

    @Column(name = "Location")
    private String location;

    public TargetLocation() {
    }

    public int getTargetLocationID() {
        return targetLocationID;
    }

    public void setTargetLocationID(int targetLocationID) {
        this.targetLocationID = targetLocationID;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
    
    
}
