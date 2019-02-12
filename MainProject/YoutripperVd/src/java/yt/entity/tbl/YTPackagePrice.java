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
@Table(name = "YTPackagePrice", schema = "dbo")
public class YTPackagePrice implements Serializable {

    @Id
    @Column(name = "PackageID")
    private String packageID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PackageID", insertable = false, updatable = false)
    private YTPackage ytPackage;

    @Id
    @Column(name = "CurrencyCode")
    private String currencyCode;

    @Column(name = "Price", precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "PromotionPrice", precision = 10, scale = 2)
    private BigDecimal promotionPrice;

    public YTPackagePrice() {
    }

    public String getPackageID() {
        return packageID;
    }

    public void setPackageID(String packageID) {
        this.packageID = packageID;
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

    public BigDecimal getPromotionPrice() {
        return promotionPrice;
    }

    public void setPromotionPrice(BigDecimal promotionPrice) {
        this.promotionPrice = promotionPrice;
    }

    public YTPackage getYtPackage() {
        return ytPackage;
    }

    public void setYtPackage(YTPackage ytPackage) {
        this.ytPackage = ytPackage;
    }

}
