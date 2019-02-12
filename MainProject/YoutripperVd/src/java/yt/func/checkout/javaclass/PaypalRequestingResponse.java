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
public class PaypalRequestingResponse {

    private String redirectLink;
    private String paymentID;

    public PaypalRequestingResponse() {
    }

    public PaypalRequestingResponse(String redirectLink, String paymentID) {
        this.redirectLink = redirectLink;
        this.paymentID = paymentID;
    }

    public String getRedirectLink() {
        return redirectLink;
    }

    public String getPaymentID() {
        return paymentID;
    }

}
