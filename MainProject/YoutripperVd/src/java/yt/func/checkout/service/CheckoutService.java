/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.func.checkout.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.paypal.base.rest.PayPalRESTException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author nickn
 */
public interface CheckoutService {

    public String processBooking(HttpServletRequest rq, HttpSession session) throws IOException, InterruptedException;

    public String loadCheckoutReview(HttpServletRequest rq, HttpSession session) throws IOException;

    public String processVisaMasterCheckout(HttpServletRequest rq, HttpSession session) throws IOException;

    public void getVisaMasterCheckoutInfo(HttpServletRequest rq) throws JsonProcessingException;

    public void visaMasterPayment(HttpServletRequest rq) throws NoSuchAlgorithmException,
            InvalidKeyException;

    public void processVisaMasterPaymentResponse(HttpServletRequest rq) throws Exception;

    public String processPaypalPayment(HttpServletRequest rq, HttpSession session) throws IOException, PayPalRESTException;

    public void processPaypalResponse(HttpServletRequest rq) throws PayPalRESTException;

    public String processLinepayPayment(HttpServletRequest rq, HttpSession session) throws IOException;

    public void processLinepayResponse(HttpServletRequest rq) throws IOException;

    public void expireBooking();
}
