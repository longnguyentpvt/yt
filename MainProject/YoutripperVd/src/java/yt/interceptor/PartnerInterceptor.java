/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.interceptor;

import javaclass.common.YTInterceptorException;
import javaclass.common.YTSession;
import javaclass.common.YTSessionAccount;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 *
 * @author nickn
 */
public class PartnerInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(true);
        YTSessionAccount account = YTSession.getAccountSession(session);
        if (account != null) {
            if (account.isPartner()) {
                return true;
            }
        }
        throw new YTInterceptorException("Partner Interceptor");
    }

}
