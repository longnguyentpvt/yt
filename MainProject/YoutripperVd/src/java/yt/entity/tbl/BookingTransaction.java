/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.entity.tbl;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "BookingTransaction", schema = "dbo")
public class BookingTransaction implements Serializable {

    @Id
    @Column(name = "TransactionID", unique = true, nullable = false)
    private String transactionID;

    @Column(name = "TransactionTime")
    private long transactionTime;

    @Column(name = "TransactionDate")
    private long transactionDate;

    @Column(name = "PackageID")
    private String packageID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PackageID", insertable = false, updatable = false)
    private YTPackage ytPackage;

    @Column(name = "ServingDate")
    private Long servingDate;

    @Column(name = "ServingTime")
    private Integer servingTime;

    @Column(name = "CurrencyCode")
    private String currencyCode;

    @Column(name = "NoPackages")
    private int noPackages;

    @Column(name = "ServingType")
    private String servingType;

    @Column(name = "DurationType")
    private String durationType;

    @Column(name = "BusinessDuration")
    private int businessDuration;

    @Column(name = "ExpirationDate")
    private Long expirationDate;

    @Column(name = "OPServingQTY")
    private Integer opServingQTY;

    @Column(name = "PackagePrice", precision = 10, scale = 2)
    private BigDecimal packagePrice;

    @Column(name = "PaymentPrice", precision = 10, scale = 2)
    private BigDecimal paymentPrice;

    @Column(name = "OnPromotional")
    private boolean onPromotional;

    public BookingTransaction() {
    }

    public String getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }

    public long getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(long transactionTime) {
        this.transactionTime = transactionTime;
    }

    public long getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(long transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getPackageID() {
        return packageID;
    }

    public void setPackageID(String packageID) {
        this.packageID = packageID;
    }

    public YTPackage getYtPackage() {
        return ytPackage;
    }

    public void setYtPackage(YTPackage ytPackage) {
        this.ytPackage = ytPackage;
    }

    public Long getServingDate() {
        return servingDate;
    }

    public void setServingDate(Long servingDate) {
        this.servingDate = servingDate;
    }

    public Integer getServingTime() {
        return servingTime;
    }

    public void setServingTime(Integer servingTime) {
        this.servingTime = servingTime;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public int getNoPackages() {
        return noPackages;
    }

    public void setNoPackages(int noPackages) {
        this.noPackages = noPackages;
    }

    public String getServingType() {
        return servingType;
    }

    public void setServingType(String servingType) {
        this.servingType = servingType;
    }

    public String getDurationType() {
        return durationType;
    }

    public void setDurationType(String durationType) {
        this.durationType = durationType;
    }

    public int getBusinessDuration() {
        return businessDuration;
    }

    public void setBusinessDuration(int businessDuration) {
        this.businessDuration = businessDuration;
    }

    public Long getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Long expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Integer getOpServingQTY() {
        return opServingQTY;
    }

    public void setOpServingQTY(Integer opServingQTY) {
        this.opServingQTY = opServingQTY;
    }

    public BigDecimal getPackagePrice() {
        return packagePrice;
    }

    public void setPackagePrice(BigDecimal packagePrice) {
        this.packagePrice = packagePrice;
    }

    public BigDecimal getPaymentPrice() {
        return paymentPrice;
    }

    public void setPaymentPrice(BigDecimal paymentPrice) {
        this.paymentPrice = paymentPrice;
    }

    public boolean isOnPromotional() {
        return onPromotional;
    }

    public void setOnPromotional(boolean onPromotional) {
        this.onPromotional = onPromotional;
    }

}
