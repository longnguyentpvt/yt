/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.func.signup;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.io.IOException;
import java.util.Locale;
import javaclass.common.YTAttr;
import javaclass.utility.YTUrlUtility;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import yt.func.home.HomeViewMapping;
import yt.func.signup.service.SignupService;

/**
 *
 * @author nickn
 */
@Controller
@RequestMapping(value = {"", "/{locale}"})
public class SignupController {

    @Autowired
    private SignupService signupService;

    @RequestMapping(value = SignupViewMapping.PARTNER_SIGNUP_URL, method = RequestMethod.GET)
    public String goToPartnerSignupPage() {
        return "common/BecomePartner";
    }

    @RequestMapping(value = SignupViewMapping.PARTNER_SIGNUP_FORM_LOADING_URL, method = RequestMethod.POST)
    @ResponseBody
    public String getPartnerSignupData(HttpServletRequest request) throws JsonProcessingException {
        return signupService.getPartnerSignupData();
    }

    @RequestMapping(value = SignupViewMapping.PARTNER_SIGNUP_STATE_LOADING_URL, method = RequestMethod.POST)
    @ResponseBody
    public String getState(HttpServletRequest request) throws JsonProcessingException, IOException {
        String data = (String) request.getAttribute(YTAttr.AJAX_DATA_ATTR);
        return signupService.getPartnerStatesOfCountry(data);
    }

    @RequestMapping(value = SignupViewMapping.PARTNER_SIGNUP_EMAIL_VALID_URL, method = RequestMethod.POST)
    @ResponseBody
    public String checkPartnerValidEmail(HttpServletRequest request) throws JsonProcessingException, IOException {
        String data = (String) request.getAttribute(YTAttr.AJAX_DATA_ATTR);
        return signupService.isValidSignupEmail(data);
    }

    @RequestMapping(value = SignupViewMapping.PARTNER_SIGNUP_REGISTRATION_URL, method = RequestMethod.POST)
    public String signupNewPartner(HttpSession session, HttpServletRequest request, Locale locale) {
        String emailToken = signupService.registerPartner(request);
        if (emailToken != null) {
            session.setAttribute("resend", emailToken);
            return YTUrlUtility.getDirectURL(locale, SignupViewMapping.PARTNER_SUCCESS_REGISTRATION_URL);
        }
        throw new IllegalArgumentException("Email Token Is Null");
    }

    @RequestMapping(value = SignupViewMapping.PARTNER_ACTIVATION_RESEND_URL, method = RequestMethod.POST)
    public String resendPartnerActivationEmail(HttpServletRequest request, HttpSession session, Locale locale) {
//        session
        String emailToken = (String) session.getAttribute("resend");
        boolean success = signupService.resendPartnerActivationEmail(emailToken);
        if (success) {
            session.setAttribute("resend", null);
            return YTUrlUtility.getDirectURL(locale, SignupViewMapping.PARTNER_SUCCESS_REGISTRATION_URL);
        }
        throw new IllegalArgumentException("Email Token does not exists");
    }

    @RequestMapping(value = SignupViewMapping.PARTNER_SUCCESS_REGISTRATION_URL, method = RequestMethod.GET)
    public String goToSuccessRegistrationPage(HttpSession session, HttpServletRequest request) {
        return "common/SuccessPartnerRegistration";
    }

    @RequestMapping(value = SignupViewMapping.PARTNER_EMAIL_ACTIVATION_URL + "/{token}", method = RequestMethod.GET)
    public String validatePartnerAccount(HttpSession session, HttpServletRequest request,
            HttpServletResponse response, @PathVariable String token, Locale locale) {
        boolean success = signupService.activatePartnerAccount(request, response, session, token);
        if (success) {
            return YTUrlUtility.getDirectURL(locale, HomeViewMapping.HOME_PAGE_URL);
        }
        return "common/FailActivation";
    }

    @RequestMapping(value = SignupViewMapping.TRIPPER_SIGNUP_EMAIL_VALID_URL, method = RequestMethod.POST)
    @ResponseBody
    public String checkTripperValidEmail(HttpServletRequest request) throws JsonProcessingException, IOException {
        String data = (String) request.getAttribute(YTAttr.AJAX_DATA_ATTR);
        return signupService.isValidSignupEmail(data);
    }

    @RequestMapping(value = SignupViewMapping.TRIPPER_SIGNUP_REGISTRATION_URL, method = RequestMethod.POST)
    @ResponseBody
    public String signupNewTripper(HttpSession session, HttpServletRequest request, HttpServletResponse response, Locale locale) throws IOException {
        String data = (String) request.getAttribute(YTAttr.AJAX_DATA_ATTR);
        return signupService.registerTripperAsYTAccount(data, request, response, session);
    }
    
       @RequestMapping(value = SignupViewMapping.PARTNER_CONTACT_CLOSE_URL, method = RequestMethod.POST)
    @ResponseBody
    public String closePartnerContact(HttpSession session, HttpServletRequest request, HttpServletResponse response, Locale locale) throws IOException {       
        return signupService.closePartnerContact(session);
    }
    
     @RequestMapping(value = SignupViewMapping.PARTNER_CONTACT_REGISTRATION_URL, method = RequestMethod.POST)
    @ResponseBody
    public String PartnerContactSignup(HttpSession session, HttpServletRequest request, HttpServletResponse response, Locale locale) throws IOException {
        String data = (String) request.getAttribute(YTAttr.AJAX_DATA_ATTR);        
        return signupService.registrationPartnerContact(session,data);
    }
    
}
