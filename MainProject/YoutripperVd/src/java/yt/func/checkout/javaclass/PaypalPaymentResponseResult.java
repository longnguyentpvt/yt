/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.func.checkout.javaclass;

/**
 *
 * @author nickn
 */
public class PaypalPaymentResponseResult {

    private boolean success;
    private String paymentStatus;

    public PaypalPaymentResponseResult() {
    }

    public PaypalPaymentResponseResult(boolean success, String paymentStatus) {
        this.success = success;
        this.paymentStatus = paymentStatus;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

}
