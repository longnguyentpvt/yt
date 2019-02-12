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
public class PackageDetailDAORespone {

    public String servingType;
    private String packageName;
    private BigDecimal price;
    private String pkgStt;

    public PackageDetailDAORespone() {
    }

    public PackageDetailDAORespone(String servingType, String packageName, BigDecimal price, String pkgStt) {
        this.servingType = servingType;
        this.packageName = packageName;
        this.price = price;
        this.pkgStt = pkgStt;
    }

    public String getServingType() {
        return servingType;
    }

    public void setServingType(String servingType) {
        this.servingType = servingType;
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

    public String getPkgStt() {
        return pkgStt;
    }

    public void setPkgStt(String pkgStt) {
        this.pkgStt = pkgStt;
    }

}
