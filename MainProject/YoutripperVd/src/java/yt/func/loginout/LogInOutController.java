/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.func.loginout;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Locale;
import javaclass.common.YTAttr;
import javaclass.utility.YTUrlUtility;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import yt.func.home.HomeViewMapping;
import yt.func.loginout.service.LogInOutService;

/**
 *
 * @author nickn
 */
@Controller
@RequestMapping(value = {"", "/{locale}"})
public class LogInOutController {

    @Autowired
    private LogInOutService logInOutService;

    @RequestMapping(value = LogInOutViewMapping.LOGOUT_URL, method = RequestMethod.GET)
    public String goToPartnerSignupPage(HttpServletRequest request, HttpServletResponse response, HttpSession session, Locale locale) {
        logInOutService.logout(request, response, session, false);
        return YTUrlUtility.getDirectURL(locale, HomeViewMapping.HOME_PAGE_URL);
    }

    @RequestMapping(value = LogInOutViewMapping.LOGIN_URL, method = RequestMethod.POST)
    @ResponseBody
    public String loginToYT(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
        String data = (String) request.getAttribute(YTAttr.AJAX_DATA_ATTR);
        return logInOutService.loginByYTAccount(request, response, session, data);
    }

    @RequestMapping(value = LogInOutViewMapping.LOGIN_GOOGLE_URL, method = RequestMethod.POST)
    @ResponseBody
    public String loginToYTByGoogle(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException, GeneralSecurityException {
        String data = (String) request.getAttribute(YTAttr.AJAX_DATA_ATTR);
        return logInOutService.loginByGoogle(request, response, session, data);
    }

    @RequestMapping(value = LogInOutViewMapping.LOGIN_FB_URL, method = RequestMethod.POST)
    @ResponseBody
    public String loginToYTByFacebook(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
        String data = (String) request.getAttribute(YTAttr.AJAX_DATA_ATTR);
        return logInOutService.loginByFacebook(request, response, session, data);
    }

    @RequestMapping(value = {"/line-wechat-login"}, method = RequestMethod.GET)
    public String gotoLinePayWeChatLogin(HttpSession session, HttpServletRequest request) {
        return "common/LineWePay";
    }

    @RequestMapping(value = LogInOutViewMapping.LOGIN_LINEPAY_REQUEST_URL, method = RequestMethod.POST)
    @ResponseBody
    public String getLinepayRequest(HttpServletRequest request) throws IOException {
        return logInOutService.generateLineLoginRequest(request);
    }

    @RequestMapping(value = {LogInOutViewMapping.LOGIN_LINEPAY_URL}, method = RequestMethod.GET)
    public String getLineResponse(HttpSession session, HttpServletRequest request, HttpServletResponse response, Locale locale) throws IOException {
        return "redirect:" + logInOutService.loginByLineChat(request, response, session);
    }
}
