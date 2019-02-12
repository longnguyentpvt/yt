/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.func.checkout;

/**
 *
 * @author nickn
 */
public class CheckoutViewMapping {

    public static final String PACKAGE_BOOKING_URL = "booking-process";

    public static final String CHECKOUT_REVIEW_URL = "checkout-review";
    public static final String CHECKOUT_REVIEW_DATA_URL = "checkout-review/loading";
    public static final String VISA_MASTER_REQUEST_URL = "checkout-review/vm-request";
    public static final String PAYPAL_REQUEST_URL = "checkout-review/paypal-request";
    public static final String LINEPAY_REQUEST_URL = "checkout-review/linepay-request";

    public static final String VISA_MASTER_CHECKOUT_URL = "vm-checkout";
    public static final String VISA_MASTER_PAYMENT_URL = "vm-payment-request";
    public static final String VISA_MASTER_RESPONSE_URL = "ytpayment-response";

    public static final String PAYPAY_PAYMENT_RESPONSE_URL = "paypal-response";
    
    public static final String LINEPAY_PAYMENT_RESPONSE_URL = "linepay-response";
}
