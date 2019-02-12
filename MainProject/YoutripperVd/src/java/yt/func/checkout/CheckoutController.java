/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.func.checkout;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.paypal.base.rest.PayPalRESTException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import yt.func.checkout.service.CheckoutService;

/**
 *
 * @author nickn
 */
@Controller
@RequestMapping(value = {"", "/{locale}"})
public class CheckoutController {

    @Autowired
    private CheckoutService checkoutService;

    @RequestMapping(value = CheckoutViewMapping.CHECKOUT_REVIEW_URL, method = RequestMethod.POST)
    public String checkoutReview() {
        return "common/CheckoutReview";
    }

    @RequestMapping(value = CheckoutViewMapping.CHECKOUT_REVIEW_DATA_URL, method = RequestMethod.POST)
    @ResponseBody
    public String loadCheckoutReview(HttpServletRequest request, HttpSession session) throws IOException {
        return checkoutService.loadCheckoutReview(request, session);
    }

    @RequestMapping(value = CheckoutViewMapping.PACKAGE_BOOKING_URL, method = RequestMethod.POST)
    @ResponseBody
    public String updateCategoryInfo(HttpServletRequest request, HttpSession session) throws IOException, InterruptedException {
        return checkoutService.processBooking(request, session);
    }

    @RequestMapping(value = CheckoutViewMapping.VISA_MASTER_REQUEST_URL, method = RequestMethod.POST)
    @ResponseBody
    public String requestVisaMasterPayment(HttpServletRequest request, HttpSession session) throws IOException {
        return checkoutService.processVisaMasterCheckout(request, session);
    }

    @RequestMapping(value = CheckoutViewMapping.PAYPAL_REQUEST_URL, method = RequestMethod.POST)
    @ResponseBody
    public String requestPaypalPayment(HttpServletRequest request, HttpSession session) throws PayPalRESTException, IOException {
        return checkoutService.processPaypalPayment(request, session);
    }

    @RequestMapping(value = CheckoutViewMapping.LINEPAY_REQUEST_URL, method = RequestMethod.POST)
    @ResponseBody
    public String requestLinepayPayment(HttpServletRequest request, HttpSession session) throws IOException {
        return checkoutService.processLinepayPayment(request, session);
    }

    @RequestMapping(value = CheckoutViewMapping.VISA_MASTER_CHECKOUT_URL, method = RequestMethod.POST)
    public String goToVisaMasterCheckoutPage(HttpServletRequest rq) throws JsonProcessingException {
        checkoutService.getVisaMasterCheckoutInfo(rq);
        return "common/VisaMasterCheckout";
    }

    @RequestMapping(value = CheckoutViewMapping.VISA_MASTER_PAYMENT_URL, method = RequestMethod.POST)
    public String getPaymentRequest(HttpServletRequest request) throws NoSuchAlgorithmException, InvalidKeyException {
        checkoutService.visaMasterPayment(request);
        return "common/PaymentAuthentication";
    }

    @RequestMapping(value = CheckoutViewMapping.VISA_MASTER_RESPONSE_URL, method = RequestMethod.POST)
    public String processPaymentResponse(HttpServletRequest request) throws Exception {
        checkoutService.processVisaMasterPaymentResponse(request);
        return "common/PaymentResult";
    }

    @RequestMapping(value = CheckoutViewMapping.PAYPAY_PAYMENT_RESPONSE_URL, method = RequestMethod.GET)
    public String processPaypalResponse(HttpServletRequest request) throws PayPalRESTException {
        checkoutService.processPaypalResponse(request);
        return "common/PaymentResult";
    }

    @RequestMapping(value = CheckoutViewMapping.LINEPAY_PAYMENT_RESPONSE_URL, method = RequestMethod.GET)
    public String processLinepayResponse(HttpServletRequest request) throws IOException {
        checkoutService.processLinepayResponse(request);
        return "common/PaymentResult";
    }
}
