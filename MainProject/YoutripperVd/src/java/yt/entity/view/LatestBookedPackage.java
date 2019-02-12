/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.entity.view;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.Immutable;

/**
 *
 * @author Hiep
 */
@Entity
@Immutable
@Table(name = "LatestBookedPackage (NOEXPAND)", schema = "dbo")
public class LatestBookedPackage implements Serializable {

    @Id
    @Column(name = "PackageID", nullable = false)
    private String packageID;

    @Id
    @Column(name = "LanguageCode")
    private String languageCode;

    @Id
    @Column(name = "CurrencyCode")
    private String currencyCode;

    @Column(name = "PackageName")
    private String packageName;

    @Column(name = "SiteURL")
    private String siteURL;

    @Column(name = "CityName")
    private String cityName;

    @Column(name = "CountryName")
    private String countryName;

    @Column(name = "Price")
    private BigDecimal price;

    @Column(name = "PromotionPrice")
    private BigDecimal promotionPrice;

    @Column(name = "NoSolds")
    private Integer noSolds;

    @Column(name = "NoComments")
    private Integer noComments;

    @Column(name = "Rate")
    private Integer rate;

    @Column(name = "PortraitPhoto")
    private String portraitPhoto;

    @Column(name = "PromotionPortrait")
    private String promotionPortrait;

    @Column(name = "OnPromotional")
    private boolean onPromotional;

    @Column(name = "LatestBookedTime")
    private Long latestBookedTime;

    public LatestBookedPackage() {

    }

    public String getPackageID() {
        return packageID;
    }

    public void setPackageID(String packageID) {
        this.packageID = packageID;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getSiteURL() {
        return siteURL;
    }

    public void setSiteURL(String siteURL) {
        this.siteURL = siteURL;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPromotionPrice() {
        return promotionPrice;
    }

    public void setPromotionPrice(BigDecimal promotionPrice) {
        this.promotionPrice = promotionPrice;
    }

    public Integer getNoSolds() {
        return noSolds;
    }

    public void setNoSolds(Integer noSolds) {
        this.noSolds = noSolds;
    }

    public Integer getNoComments() {
        return noComments;
    }

    public void setNoComments(Integer noComments) {
        this.noComments = noComments;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public String getPortraitPhoto() {
        return portraitPhoto;
    }

    public void setPortraitPhoto(String portraitPhoto) {
        this.portraitPhoto = portraitPhoto;
    }

    public String getPromotionPortrait() {
        return promotionPortrait;
    }

    public void setPromotionPortrait(String promotionPortrait) {
        this.promotionPortrait = promotionPortrait;
    }

    public boolean isOnPromotional() {
        return onPromotional;
    }

    public void setOnPromotional(boolean onPromotional) {
        this.onPromotional = onPromotional;
    }

    public Long getLatestBookedTime() {
        return latestBookedTime;
    }

    public void setLatestBookedTime(Long latestBookedTime) {
        this.latestBookedTime = latestBookedTime;
    }

}
