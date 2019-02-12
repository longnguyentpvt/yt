/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.func.packagedetail.javaclass;

import java.math.BigDecimal;

/**
 *
 * @author nickn
 */
public class YTPackageDetailData {

    private String packageID;
    private String packageName;
    private BigDecimal price;
    private String currencyCode;
    private boolean openedPkg;

    public YTPackageDetailData() {
    }

    public YTPackageDetailData(String packageID, String packageName, BigDecimal price, String currencyCode, boolean openedPkg) {
        this.packageID = packageID;
        this.packageName = packageName;
        this.price = price;
        this.currencyCode = currencyCode;
        this.openedPkg = openedPkg;
    }

    public String getPackageID() {
        return packageID;
    }

    public void setPackageID(String packageID) {
        this.packageID = packageID;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public boolean isOpenedPkg() {
        return openedPkg;
    }

    public void setOpenedPkg(boolean openedPkg) {
        this.openedPkg = openedPkg;
    }

}
