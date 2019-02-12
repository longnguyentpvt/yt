/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.func.signup.service;

/**
 *
 * @author nickn
 */
public interface EmailService {

    public void sendPartnerAccountVerificationEmail(String displayName, String email, String token);
}
