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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 *
 * @author Hiep
 */
@Entity
@Table(name = "PackageCountry", schema = "dbo")
public class PackageCountry implements Serializable {

    @Id
    @Column(name = "CountryID", unique = true, nullable = false)
    private String countryID;

    @Column(name = "CountryName")
    private String countryName;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "packageCountry")
    @Cascade(value = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.EXTRA)
    private Set<PackageCountryTranslation> packageCountryTranslations;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "packageCountry")
    @Cascade(value = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.EXTRA)
    private Set<PackageCity> packageCities;

    public PackageCountry() {
    }

    public String getCountryID() {
        return countryID;
    }

    public void setCountryID(String countryID) {
        this.countryID = countryID;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public Set<PackageCountryTranslation> getPackageCountryTranslations() {
        return packageCountryTranslations;
    }

    public void setPackageCountryTranslations(Set<PackageCountryTranslation> packageCountryTranslations) {
        this.packageCountryTranslations = packageCountryTranslations;
    }

    public Set<PackageCity> getPackageCities() {
        return packageCities;
    }

    public void setPackageCities(Set<PackageCity> packageCities) {
        this.packageCities = packageCities;
    }

}
