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
@Table(name = "TargetLocationContent", schema = "dbo")
public class TargetLocationContent implements Serializable {

    @Id
    @Column(name = "TargetLocationID")
    private int targetLocationID;

    @Id
    @Column(name = "LanguageCode")
    private String languageCode;

    @Column(name = "LocationName")
    private String locationName;

    public TargetLocationContent() {
    }

    public int getTargetLocationID() {
        return targetLocationID;
    }

    public void setTargetLocationID(int targetLocationID) {
        this.targetLocationID = targetLocationID;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

}
