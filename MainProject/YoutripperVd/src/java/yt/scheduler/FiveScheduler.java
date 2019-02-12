/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import yt.func.checkout.service.CheckoutService;
import yt.globalexception.GlobalExceptionHandler;

/**
 *
 * @author nickn
 */
public class FiveScheduler {

    @Autowired
    private CheckoutService checkoutService;

    public void expireBooking() {
        try {
            System.out.println("run");
            checkoutService.expireBooking();
        } catch (Exception e) {
            GlobalExceptionHandler.logExpireScheduler(e);
        }
    }
}
