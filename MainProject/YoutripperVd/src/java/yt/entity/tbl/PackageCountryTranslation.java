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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Hiep
 */
@Entity
@Table(name = "PackageCountryTranslation", schema = "dbo")
public class PackageCountryTranslation implements Serializable {

    @Id
    @Column(name = "CountryID")
    private String countryID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CountryID", insertable = false, updatable = false)
    private PackageCountry packageCountry;

    @Column(name = "LanguageCode")
    private String languageCode;

    @Column(name = "CountryName")
    private String countryName;

    public PackageCountryTranslation() {
    }

    public String getCountryID() {
        return countryID;
    }

    public void setCountryID(String countryID) {
        this.countryID = countryID;
    }

    public PackageCountry getPackageCountry() {
        return packageCountry;
    }

    public void setPackageCountry(PackageCountry packageCountry) {
        this.packageCountry = packageCountry;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

}
