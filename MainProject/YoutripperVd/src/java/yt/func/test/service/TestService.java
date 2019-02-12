/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.func.test.service;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author nickn
 */
public interface TestService {

    public void processPaymentResponse(HttpServletRequest request) throws Exception;
}
