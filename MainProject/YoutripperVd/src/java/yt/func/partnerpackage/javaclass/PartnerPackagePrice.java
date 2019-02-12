/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.func.partnerpackage.javaclass;

import java.math.BigDecimal;

/**
 *
 * @author nickn
 */
public class PartnerPackagePrice {

    private int promotionPercent;
    private String currencyCode;
    private BigDecimal pkgPrice;
    private BigDecimal paidPrice;

    public PartnerPackagePrice() {
    }

    public PartnerPackagePrice(int promotionPercent, String currencyCode, BigDecimal pkgPrice, BigDecimal paidPrice) {
        this.promotionPercent = promotionPercent;
        this.currencyCode = currencyCode;
        this.pkgPrice = pkgPrice;
        this.paidPrice = paidPrice;
    }

    public int getPromotionPercent() {
        return promotionPercent;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public BigDecimal getPkgPrice() {
        return pkgPrice;
    }

    public BigDecimal getPaidPrice() {
        return paidPrice;
    }

}
