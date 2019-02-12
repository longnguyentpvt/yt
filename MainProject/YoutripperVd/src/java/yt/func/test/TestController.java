/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.func.test;

import java.math.BigDecimal;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javaclass.utility.YTDateTimeUtility;
import javaclass.utility.YTPaymentUtility;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import yt.func.test.service.TestService;

/**
 *
 * @author KyLong
 */
@Controller
@RequestMapping(value = {"", "/{locale}"})
public class TestController {
    
    @Autowired
    private TestService testService;

    @RequestMapping(value = "/testcard", method = RequestMethod.GET)
    public String goToTestCard() {
        return "test/TestCard";
    }

    @RequestMapping(value = "/ytpayment", method = RequestMethod.POST)
    public String getPaymentRequest(HttpServletRequest request) throws NoSuchAlgorithmException, InvalidKeyException {
        String expirationMonth = request.getParameter("expMonthCardInfo");
        String expirationYear = request.getParameter("expYearCardInfo");
        String encryptedCardInfo = request.getParameter("encryptedCardInfo");
        String totalStr = request.getParameter("total");
        BigDecimal total = new BigDecimal(totalStr);
        String holderName = "Long Nguyen";
        String orderNo = YTDateTimeUtility.getCurrentTimeInMilli() + "";
        String paymentRequest = YTPaymentUtility.generate2c2pRequest(encryptedCardInfo, holderName, total, "THB", orderNo);
        request.setAttribute("paymentrequest", paymentRequest);
        return "test/PaymentAuthentication";
    }

 
}
