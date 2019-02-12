/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.entity.tbl;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import yt.func.search.dao.PackageCityTranslationDAO;

/**
 *
 * @author Hiep
 */
@Entity
@Table(name = "PackageCity", schema = "dbo")
public class PackageCity implements Serializable {

    @Id
    @Column(name = "CityID", unique = true, nullable = false)
    private String cityID;

    @Column(name = "CityName")
    private String cityName;

    @Column(name = "CountryID", insertable = false, updatable = false)
    private String countryID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CountryID")
    private PackageCountry packageCountry;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "packageCity")
    @Cascade(value = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.EXTRA)
    private Set<PackageCityTranslation> packageCityTranslations;

    public PackageCity() {
    }

    public String getCityID() {
        return cityID;
    }

    public void setCityID(String cityID) {
        this.cityID = cityID;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
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

    public Set<PackageCityTranslation> getPackageCityTranslations() {
        return packageCityTranslations;
    }

    public void setPackageCityTranslations(Set<PackageCityTranslation> packageCityTranslations) {
        this.packageCityTranslations = packageCityTranslations;
    }

    

}
