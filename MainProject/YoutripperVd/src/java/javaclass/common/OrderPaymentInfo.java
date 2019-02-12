/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaclass.common;

import java.math.BigDecimal;

/**
 *
 * @author nickn
 */
public class OrderPaymentInfo {

    private BigDecimal payemntRate;
    private BigDecimal finalRate;
    private BigDecimal amount;
    private BigDecimal ytCommission;
    private BigDecimal partnerPayable;

    public OrderPaymentInfo() {
    }

    public OrderPaymentInfo(BigDecimal payemntRate, BigDecimal finalRate, BigDecimal amount, BigDecimal ytCommission, BigDecimal partnerPayable) {
        this.payemntRate = payemntRate;
        this.finalRate = finalRate;
        this.amount = amount;
        this.ytCommission = ytCommission;
        this.partnerPayable = partnerPayable;
    }

    public BigDecimal getPayemntRate() {
        return payemntRate;
    }

    public BigDecimal getFinalRate() {
        return finalRate;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public BigDecimal getYtCommission() {
        return ytCommission;
    }

    public BigDecimal getPartnerPayable() {
        return partnerPayable;
    }

}
