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
public class NewCheckoutResponse {

    private String orderNo;
    private String currencyCode;
    private BigDecimal total;

    public NewCheckoutResponse() {
    }

    public NewCheckoutResponse(String orderNo, String currencyCode, BigDecimal total) {
        this.orderNo = orderNo;
        this.currencyCode = currencyCode;
        this.total = total;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

}
