/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.func.test.service;

import javaclass.utility.YTPaymentUtility;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author nickn
 */
@Service
public class TestServiceImpl implements TestService {

    @Override
    @Transactional
    public void processPaymentResponse(HttpServletRequest request) throws Exception {
        String paymentResponse = request.getParameter("paymentResponse");
        YTPaymentUtility.verifyPaymentResponse(paymentResponse);
    }

}
