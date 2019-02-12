/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.entity.tbl;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author nickn
 */
@Entity
@Table(name = "TemporaryPackageContent", schema = "dbo")
public class TemporaryPackageContent implements Serializable {

    @Id
    @Column(name = "PackageID")
    private long packageID;

    @Id
    @Column(name = "LanguageCode")
    private String languageCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PackageID", insertable = false, updatable = false)
    private TemporaryPackage temporaryPackage;

    @Column(name = "PackageName")
    private String packageName;

    @Column(name = "GoogleDescription")
    private String googleDescription;

    @Column(name = "MultiDescription")
    private String multiDescription;

    @Column(name = "ActivityLocations")
    private String activityLocations;

    @Column(name = "DepartureLocations")
    private String departureLocations;

    @Column(name = "PickupLocations")
    private String pickupLocations;

    @Column(name = "Keywords")
    private String keywords;

    @Column(name = "RegisteredContent")
    private boolean registeredContent;

    public TemporaryPackageContent() {
    }

    public long getPackageID() {
        return packageID;
    }

    public void setPackageID(long packageID) {
        this.packageID = packageID;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }

    public TemporaryPackage getTemporaryPackage() {
        return temporaryPackage;
    }

    public void setTemporaryPackage(TemporaryPackage temporaryPackage) {
        this.temporaryPackage = temporaryPackage;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getGoogleDescription() {
        return googleDescription;
    }

    public void setGoogleDescription(String googleDescription) {
        this.googleDescription = googleDescription;
    }

    public String getMultiDescription() {
        return multiDescription;
    }

    public void setMultiDescription(String multiDescription) {
        this.multiDescription = multiDescription;
    }

    public String getActivityLocations() {
        return activityLocations;
    }

    public void setActivityLocations(String activityLocations) {
        this.activityLocations = activityLocations;
    }

    public String getDepartureLocations() {
        return departureLocations;
    }

    public void setDepartureLocations(String departureLocations) {
        this.departureLocations = departureLocations;
    }

    public String getPickupLocations() {
        return pickupLocations;
    }

    public void setPickupLocations(String pickupLocations) {
        this.pickupLocations = pickupLocations;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public boolean isRegisteredContent() {
        return registeredContent;
    }

    public void setRegisteredContent(boolean registeredContent) {
        this.registeredContent = registeredContent;
    }

}
