/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.func.loginout.service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author nickn
 */
public interface LogInOutService {

    public void addRememberToken(HttpServletResponse response, String id, boolean partner);

    public void logout(HttpServletRequest request, HttpServletResponse response, HttpSession session, boolean remember);

    public void loginAsPartner(HttpServletRequest request, HttpServletResponse response, HttpSession session,
            String partnerID, String avatar, String displayName, boolean remember, boolean isLoginedToken);

    public void loginAsTripper(HttpServletRequest request, HttpServletResponse response, HttpSession session,
            String tripperID, String avatar, String displayName, boolean remember, boolean isLoginedToken);

    public String loginByYTAccount(HttpServletRequest request, HttpServletResponse response, HttpSession session, String data) throws IOException;

    public void loginByRememberToken(HttpServletRequest request, HttpServletResponse response, HttpSession session);

    public String loginByGoogle(HttpServletRequest request, HttpServletResponse response, HttpSession session, String data) throws IOException,
            GeneralSecurityException;

    public String loginByFacebook(HttpServletRequest request, HttpServletResponse response, HttpSession session, String data) throws IOException;

    public String generateLineLoginRequest(HttpServletRequest request) throws IOException;

    public String loginByLineChat(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException;
}
