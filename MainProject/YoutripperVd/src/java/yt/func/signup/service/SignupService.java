/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.func.signup.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author nickn
 */
public interface SignupService {

    public String getPartnerSignupData() throws JsonProcessingException;

    public String getPartnerStatesOfCountry(String data) throws JsonProcessingException, IOException;

    public String isValidSignupEmail(String data) throws JsonProcessingException, IOException;

    public String registerPartner(HttpServletRequest request);

    public boolean resendPartnerActivationEmail(String emailToken);

    public boolean activatePartnerAccount(HttpServletRequest request, HttpServletResponse response,
            HttpSession session, String registrationToken);

    public String registrationPartnerContact(HttpSession session, String data) throws IOException;

    public String closePartnerContact(HttpSession session);

    public String registerTripperAsYTAccount(String data, HttpServletRequest request, HttpServletResponse response,
            HttpSession session) throws IOException;

    public String registerTripperGoogleAccount(String email, String googleID);

    public String registerTripperFacebookAccount(String email, String facebookID);
    
    public String registerTripperLinePayAccount(String email, String linepayID);
}
