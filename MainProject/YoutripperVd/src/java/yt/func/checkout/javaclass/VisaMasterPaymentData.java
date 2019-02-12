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
public class VisaMasterPaymentData {

    private String on;
    private BigDecimal tt;
    private String cc;

    public VisaMasterPaymentData() {
    }

    public VisaMasterPaymentData(String on, BigDecimal tt, String cc) {
        this.on = on;
        this.tt = tt;
        this.cc = cc;
    }

    public String getOn() {
        return on;
    }

    public BigDecimal getTt() {
        return tt;
    }

    public String getCc() {
        return cc;
    }

}
