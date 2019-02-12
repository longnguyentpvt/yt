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
 * @author macnew
 */
@Entity
@Immutable
@Table(name = "PartnerPackage (NOEXPAND)", schema = "dbo")
public class PartnerPackage implements Serializable {

    @Id
    @Column(name = "TempPackageID")
    private long tempPackageID;

    @Id
    @Column(name = "CurrencyCode")
    private String currencyCode;

    @Column(name = "MainPackageID")
    private String mainPackageID;

    @Column(name = "PartnerID")
    private String partnerID;

    @Column(name = "PackageName")
    private String packageName;

    @Column(name = "ServingType")
    private String servingType;

    @Column(name = "NoSolds")
    private Integer noSolds;

    @Column(name = "Rate")
    private Integer rate;

    @Column(name = "NoComments")
    private Integer noComments;

    @Column(name = "PortraitPhoto")
    private String portraitPhoto;

    @Column(name = "PromotionPortrait")
    private String promotionPortrait;

    @Column(name = "Price", precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "Rounded")
    private boolean rounded;

    @Column(name = "PromotionPrice", precision = 10, scale = 2)
    private BigDecimal promotionPrice;

    @Column(name = "OnPromotional")
    private boolean onPromotional;

    @Column(name = "MainStatus")
    private String mainStatus;

    @Column(name = "TempStatus")
    private String tempStatus;

    public PartnerPackage() {
    }

    public String getMainPackageID() {
        return mainPackageID;
    }

    public void setMainPackageID(String mainPackageID) {
        this.mainPackageID = mainPackageID;
    }

    public long getTempPackageID() {
        return tempPackageID;
    }

    public void setTempPackageID(long tempPackageID) {
        this.tempPackageID = tempPackageID;
    }

    public String getPartnerID() {
        return partnerID;
    }

    public void setPartnerID(String partnerID) {
        this.partnerID = partnerID;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getServingType() {
        return servingType;
    }

    public void setServingType(String servingType) {
        this.servingType = servingType;
    }

    public Integer getNoSolds() {
        return noSolds;
    }

    public void setNoSolds(Integer noSolds) {
        this.noSolds = noSolds;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public Integer getNoComments() {
        return noComments;
    }

    public void setNoComments(Integer noComments) {
        this.noComments = noComments;
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

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public boolean isRounded() {
        return rounded;
    }

    public void setRounded(boolean rounded) {
        this.rounded = rounded;
    }

    public BigDecimal getPromotionPrice() {
        return promotionPrice;
    }

    public void setPromotionPrice(BigDecimal promotionPrice) {
        this.promotionPrice = promotionPrice;
    }

    public boolean isOnPromotional() {
        return onPromotional;
    }

    public void setOnPromotional(boolean onPromotional) {
        this.onPromotional = onPromotional;
    }

    public String getMainStatus() {
        return mainStatus;
    }

    public void setMainStatus(String mainStatus) {
        this.mainStatus = mainStatus;
    }

    public String getTempStatus() {
        return tempStatus;
    }

    public void setTempStatus(String tempStatus) {
        this.tempStatus = tempStatus;
    }

}
