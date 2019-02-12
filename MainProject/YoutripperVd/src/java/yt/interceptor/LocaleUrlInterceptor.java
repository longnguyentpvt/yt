/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.interceptor;

import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import javaclass.common.YTAttr;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.support.RequestContext;
import javaclass.common.YTData;
import javaclass.common.YTSession;
import javaclass.utility.YTLocationUtility;
import javaclass.utility.YTUrlUtility;
import javax.servlet.http.HttpSession;
import org.apache.commons.io.IOUtils;
import yt.func.loginout.service.LogInOutService;

/**
 *
 * @author nickn
 */
public class LocaleUrlInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private SessionLocaleResolver sessionLocaleResolver;

    @Autowired
    private LogInOutService logInOutService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        if (request.getMethod().equals("GET")) {
            RequestContext requestContext = new RequestContext(request);
            HttpSession session = request.getSession(true);
            Boolean ftChecked = YTSession.getFirstTimeChecked(session);
            if (ftChecked == null) {
                ftChecked = false;
                YTSession.setFirstTimeChecked(session, true);
            }

            Locale locale = requestContext.getLocale();
            String currentLocale = locale.getLanguage();
            String requestURL = request.getServletPath();
            String requestLocale = YTData.LOCALE_ENGLISH_CODE;
            boolean definedLocale = false;
            if (StringUtils.startsWithIgnoreCase(requestURL, (YTData.LOCALE_THAI_PATH + "/"))) {
                requestLocale = YTData.LOCALE_THAI_CODE;
                definedLocale = true;
            }

            if (!ftChecked) {
                logInOutService.loginByRememberToken(request, response, session);

                String currencyCode = YTSession.getCurrencyCode(session);
                if (currencyCode == null) {
                    YTSession.setCurrencyCode(session, YTData.DEFAULT_CURRENCY_CODE);
                }
            }

            if (!definedLocale) {
                if (!ftChecked) {
                    currentLocale = YTLocationUtility.getClientLocationCode(request);
                    sessionLocaleResolver.setLocale(request, response, StringUtils.parseLocaleString(currentLocale));
                    locale = requestContext.getLocale();
                }
                if (!currentLocale.equalsIgnoreCase(YTData.LOCALE_ENGLISH_CODE)) {
                    String queryStr = request.getQueryString();
                    String realUrl = YTUrlUtility.getLocalePath(locale) + new URL(request.getRequestURL().toString()).getPath();
                    if (queryStr != null && !queryStr.isEmpty()) {
                        realUrl += "?" + queryStr;
                    }
                    response.sendRedirect(realUrl);
                    return false;
                }
            } else {
                if (!currentLocale.equalsIgnoreCase(requestLocale)) {
                    locale = StringUtils.parseLocaleString(requestLocale);
                    sessionLocaleResolver.setLocale(request, response, locale);
                    YTSession.setHrefLocale(session, YTUrlUtility.getLocalePath(locale));
                }
            }

        } else {
            String contentType = request.getHeader("Content-Type");
            String requestedWith = request.getHeader("X-Requested-With");
            boolean isAjax = false;
            if ((contentType != null && contentType.contains("application/json"))
                    || (requestedWith != null && "XMLHttpRequest".equals(requestedWith))) {
                isAjax = true;
            }
            if (isAjax) {
                String requestBody = IOUtils.toString(request.getInputStream(), StandardCharsets.UTF_8);
                request.setAttribute(YTAttr.AJAX_DATA_ATTR, requestBody);
            }
        }
        return true;
    }

}
