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
 * @author Hiep
 */
@Entity
@Table(name = "PackageCityTranslation", schema = "dbo")
public class PackageCityTranslation implements Serializable {

    @Id
    @Column(name = "CityID")
    private String cityID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CityID", insertable = false, updatable = false)
    private PackageCity packageCity;

    @Column(name = "LanguageCode")
    private String languageCode;

    @Column(name = "CityName")
    private String cityName;

    public PackageCityTranslation() {
    }

    public String getCityID() {
        return cityID;
    }

    public void setCityID(String cityID) {
        this.cityID = cityID;
    }

    public PackageCity getPackageCity() {
        return packageCity;
    }

    public void setPackageCity(PackageCity packageCity) {
        this.packageCity = packageCity;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

}
