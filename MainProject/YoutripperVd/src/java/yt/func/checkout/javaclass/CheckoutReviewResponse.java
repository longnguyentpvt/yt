/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.func.checkout.javaclass;

import java.math.BigDecimal;

/**
 *
 * @author nickn
 */
public class CheckoutReviewResponse {

    private String pkgID;
    private BigDecimal pkgPrice;
    private int qty;
    private BigDecimal subTotal;
    private BigDecimal total;
    private String currencyCode;
    private DiscountCodeResponse dc;

    public CheckoutReviewResponse() {
    }

    public CheckoutReviewResponse(String pkgID, BigDecimal pkgPrice, int qty, BigDecimal subTotal, 
            BigDecimal total, String currencyCode, DiscountCodeResponse dc) {
        this.pkgID = pkgID;
        this.pkgPrice = pkgPrice;
        this.qty = qty;
        this.subTotal = subTotal;
        this.total = total;
        this.currencyCode = currencyCode;
        this.dc = dc;
    }

    public String getPkgID() {
        return pkgID;
    }

    public void setPkgID(String pkgID) {
        this.pkgID = pkgID;
    }

    public BigDecimal getPkgPrice() {
        return pkgPrice;
    }

    public void setPkgPrice(BigDecimal pkgPrice) {
        this.pkgPrice = pkgPrice;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public DiscountCodeResponse getDc() {
        return dc;
    }

    public void setDc(DiscountCodeResponse dc) {
        this.dc = dc;
    }

}
