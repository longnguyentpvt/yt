/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.func.loginout.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.security.GeneralSecurityException;
import java.util.Collections;
import javaclass.common.YTAttr;
import javaclass.common.YTCookie;
import javaclass.common.YTData;
import javaclass.common.YTPartnerData;
import javaclass.common.YTSession;
import javaclass.common.YTSessionAccount;
import javaclass.common.YTTripperData;
import javaclass.utility.YTDateTimeUtility;
import javaclass.utility.YTFileUtility;
import javaclass.utility.YTTokenUtility;
import javaclass.utility.YoutripperIDUtility;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.codec.binary.Base64;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import yt.entity.tbl.Partner;
import yt.entity.tbl.Tripper;
import yt.entity.view.RememberLoginInfo;
import yt.entity.view.TripperFacebookLoginInfo;
import yt.entity.view.TripperGoogleLoginInfo;
import yt.entity.view.TripperLinepayLoginInfo;
import yt.func.loginout.LogInOutViewMapping;
import yt.func.loginout.dao.LineLoginTransactionDAO;
import yt.func.loginout.dao.PartnerDAO;
import yt.func.loginout.dao.RememberTokenDAO;
import yt.func.loginout.dao.TripperDAO;
import yt.func.loginout.javaclass.SocialLoginResponse;
import yt.func.signup.service.SignupService;
import yt.javaclass.config.YTDataConfiguration;

/**
 *
 * @author nickn
 */
@Service
public class LogInOutServiceImpl implements LogInOutService {

    @Autowired
    private TripperDAO loginoutTripperDAO;

    @Autowired
    private PartnerDAO loginoutPartnerDAO;

    @Autowired
    private RememberTokenDAO loginoutRememberTokenDAO;

    @Autowired
    private LineLoginTransactionDAO loginoutLineTransactionDAO;

    @Autowired
    private SignupService signupService;

    @Override
    @Transactional
    public void addRememberToken(HttpServletResponse response, String id, boolean partner) {
        String rememberToken = YTTokenUtility.generateRememberToken(id);

        // get alive time for this token, 30days
        DateTime now = new DateTime();
        now.plusDays(YTData.ALIVE_DAYS_REMEMBER_TOKEN);
        long aliveTime = now.getMillis();

        Cookie cookie = new Cookie(YTCookie.REMEMBER_TOKEN_NAME, rememberToken);
        cookie.setPath("/");
        cookie.setMaxAge(60 * 60 * 24 * YTData.ALIVE_DAYS_REMEMBER_TOKEN); // 1 month
        response.addCookie(cookie);

        String partnerID = YTPartnerData.PARTNER_DELETED_ACCOUNT_ID;
        String tripperID = YTTripperData.OFFLINE_ACCOUNT_ID;
        if (partner) {
            partnerID = id;
        } else {
            tripperID = id;
        }

        loginoutRememberTokenDAO.generateNewToken(rememberToken, partnerID, tripperID, aliveTime);
    }

    @Override
    @Transactional
    public void logout(HttpServletRequest request, HttpServletResponse response, HttpSession session, boolean remember) {
        if (!remember) {
            Cookie cookie = new Cookie(YTCookie.REMEMBER_TOKEN_NAME, null);
            cookie.setPath("/");
            cookie.setMaxAge(0);
            response.addCookie(cookie);
        }

        YTSession.setAccountSession(session, null);
    }

    @Override
    @Transactional
    public void loginAsPartner(HttpServletRequest request, HttpServletResponse response, HttpSession session,
            String partnerID, String avatar, String displayName, boolean remember, boolean isLoginedToken) {
        if (!isLoginedToken) {
            logout(request, response, session, remember);
        }

        YTSessionAccount account = new YTSessionAccount();

        account.setPartnerID(partnerID);

        if (avatar == null || avatar.isEmpty()) {
            avatar = YTFileUtility.getDefaultSmallAvatar();
        }
        account.setAvatar(avatar);
        account.setName(displayName);
        account.setPartner(true);

        YTSession.setAccountSession(session, account);

        if (remember) {
            addRememberToken(response, partnerID, true);
        }
    }

    @Override
    @Transactional
    public void loginAsTripper(HttpServletRequest request, HttpServletResponse response, HttpSession session,
            String tripperID, String avatar, String displayName, boolean remember, boolean isLoginedToken) {
        if (!isLoginedToken) {
            logout(request, response, session, remember);
        }

        YTSessionAccount account = new YTSessionAccount();

        account.setTripperID(tripperID);

        if (avatar == null || avatar.isEmpty()) {
            avatar = YTFileUtility.getDefaultSmallAvatar();
        }

        if (displayName == null || displayName.isEmpty()) {
            displayName = "Tripper";
        }
        account.setAvatar(avatar);
        account.setName(displayName);
        account.setTripper(true);

        YTSession.setAccountSession(session, account);

        if (remember) {
            addRememberToken(response, tripperID, false);
        }
    }

    @Override
    @Transactional
    public String loginByYTAccount(HttpServletRequest request, HttpServletResponse response, HttpSession session, String data) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode dataObject = mapper.readTree(data);
        String email = dataObject.get("email").asText().toLowerCase();
        String password = dataObject.get("password").asText();
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        boolean remember = dataObject.get("remember").asBoolean();

        boolean success = false;
        Tripper tripper = loginoutTripperDAO.getTripperLoginInfoByEmail(email, password);
        if (tripper != null) {
            String accountStatus = tripper.getAccountStatus();
            if (accountStatus.equals(YTTripperData.ACCOUNT_STATUS_ACTIVED)) {
                String tripperID = tripper.getTripperID();
                String displayName = tripper.getDisplayName();
                String avatar = tripper.getSmallAvatar();

                loginAsTripper(request, response, session, tripperID, avatar, displayName, remember, false);
                success = true;
            }
        } else {
            Partner partner = loginoutPartnerDAO.getParterLoginInfoByEmail(email, password);
            if (partner != null) {
                String accountStatus = partner.getAccountStatus();
                if (accountStatus.equals(YTPartnerData.ACCOUNT_STATUS_ACTIVED)) {
                    String partnerID = partner.getPartnerID();
                    String displayName = partner.getDisplayName();
                    String avatar = partner.getSmallAvatar();

                    loginAsPartner(request, response, session, partnerID, avatar, displayName, remember, false);
                    success = true;
                }
            }
        }

        return "{\"success\": " + success + "}";
    }

    @Override
    @Transactional
    public void loginByRememberToken(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        YTSessionAccount account = YTSession.getAccountSession(session);
        if (account == null) {
            Cookie[] cookie = request.getCookies();
            if (cookie != null) {
                for (Cookie obj : cookie) {
                    if (obj.getName().equals(YTCookie.REMEMBER_TOKEN_NAME)) {
                        String token = obj.getValue();
                        if (token != null && !token.isEmpty()) {
                            RememberLoginInfo info = loginoutRememberTokenDAO.getLoginInfo(token);
                            if (info != null) {
                                String partnerStatus = info.getPartnerStatus();
                                if (partnerStatus.equals(YTPartnerData.ACCOUNT_STATUS_ACTIVED)) {
                                    String partnerID = info.getPartnerID();
                                    String name = info.getPartnerName();
                                    String avatar = info.getPartnerAvatar();

                                    loginAsPartner(request, response, session, partnerID, avatar, name, false, true);
                                } else {
                                    String tripperStatus = info.getTripperStatus();
                                    if (tripperStatus.equals(YTTripperData.ACCOUNT_STATUS_ACTIVED)) {
                                        String tripperID = info.getTripperID();
                                        String name = info.getTripperName();
                                        String avatar = info.getTripperAvatar();

                                        loginAsTripper(request, response, session, tripperID, avatar, name, false, true);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    @Transactional
    public String loginByGoogle(HttpServletRequest request, HttpServletResponse response,
            HttpSession session, String data) throws IOException,
            GeneralSecurityException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode actualObj = mapper.readTree(data);

        String idTokenStr = actualObj.get("idToken").asText();
        if (idTokenStr != null) {
            GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), new JacksonFactory())
                    .setAudience(Collections.singletonList(YTDataConfiguration.getRegisteredGoogleID()))
                    .build();
            GoogleIdToken idToken = verifier.verify(idTokenStr);
            if (idToken != null) {
                GoogleIdToken.Payload payload = idToken.getPayload();
                String email = payload.getEmail();
                String googleID = payload.getSubject();

                String id = null, displayName = null, avatar = null;
                boolean registered = false;
                TripperGoogleLoginInfo info = loginoutTripperDAO.getGoogleLoginInfo(email);
                if (info != null) {
                    id = info.getTripperID();
                    displayName = info.getDisplayName();
                    avatar = info.getAvatar();
                    registered = true;
                } else {
                    String tripperID = signupService.registerTripperGoogleAccount(email, googleID);
                    if (tripperID != null) {
                        id = tripperID;
                        registered = true;
                    }
                }

                SocialLoginResponse result = new SocialLoginResponse();
                if (registered) {
                    loginAsTripper(request, response, session, id, avatar, displayName, false, false);
                }
                result.setRegistered(registered);

                return mapper.writeValueAsString(result);
            }
        }

        throw new IllegalArgumentException("Google Token Is Wrong");
    }

    @Override
    @Transactional
    public String loginByFacebook(HttpServletRequest request, HttpServletResponse response, HttpSession session, String data) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode actualObj = mapper.readTree(data);
        String idTokenStr = actualObj.get("idToken").asText();
        if (idTokenStr != null) {
            String g = "https://graph.facebook.com/v2.11/me?access_token=" + idTokenStr + "&fields=id,first_name,last_name,email";
            URL u = new URL(g);
            URLConnection c = u.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    c.getInputStream()));
            String inputLine;
            StringBuffer b = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                b.append(inputLine).append("\n");
            }
            in.close();
            String graph = b.toString();
            JsonNode returnProfileObj = mapper.readTree(graph);
            JsonNode emailNode = returnProfileObj.get("email");
            JsonNode idNode = returnProfileObj.get("id");
            String email = null, facebookID = null;
            if (emailNode != null && !emailNode.isNull()) {
                email = emailNode.asText();
            }

            if (idNode != null && !idNode.isNull()) {
                facebookID = idNode.asText();
            }

            boolean registered = false;
            String tripperID = null, displayName = null, avatar = null;
            if (facebookID != null) {
                TripperFacebookLoginInfo info = loginoutTripperDAO.getFacebookLoginInfo(facebookID);

                if (info != null) {
                    tripperID = info.getTripperID();
                    displayName = info.getDisplayName();
                    avatar = info.getAvatar();
                    registered = true;
                } else {
                    tripperID = signupService.registerTripperFacebookAccount(email, facebookID);
                    if (tripperID != null) {
                        registered = true;
                    }
                }

                if (registered) {
                    loginAsTripper(request, response, session, tripperID, avatar, displayName, false, false);
                }

                SocialLoginResponse result = new SocialLoginResponse();
                result.setRegistered(registered);
                return mapper.writeValueAsString(result);
            }

            throw new IllegalArgumentException("Facebook ID Is Wrong");
        }
        throw new IllegalArgumentException("Facebook Token Is Wrong");
    }

    @Override
    @Transactional
    public String generateLineLoginRequest(HttpServletRequest request) throws IOException {
        String data = (String) request.getAttribute(YTAttr.AJAX_DATA_ATTR);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode actualObj = mapper.readTree(data);
        String rurl = actualObj.get("rurl").asText();

        long tsTime = YTDateTimeUtility.getCurrentTimeInMilli();
        String tid = YoutripperIDUtility.generateLineTransactionID();
        if (tid != null) {
            String access = YTDataConfiguration.getLINE_LOGIN_ACCESS();
            String rpType = "response_type=code";
            String cID = "client_id=" + YTDataConfiguration.getLINE_LOGIN_ID();
            String rpURL = "redirect_uri=" + YTDataConfiguration.getYTDomain() + LogInOutViewMapping.LOGIN_LINEPAY_URL;
            String tsID = "state=" + tid;
            String scope = "scope=openid%20profile%20email";
            String rdURL = access + "?" + rpType + "&" + cID + "&" + rpURL + "&" + tsID + "&" + scope;

            loginoutLineTransactionDAO.generateLoginTransaction(tid, tsTime, false, rurl);

            return "{\"rs\":\"" + rdURL + "\"}";
        }
        throw new IllegalArgumentException("Transaction ID generated fail!");
    }

    @Override
    @Transactional
    public String loginByLineChat(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
        //try to get Authorization code
        String code = request.getParameter("code");
        String tID = request.getParameter("state");

        if (code != null) { // if user login success
            // verifiy tid
            String directURL = loginoutLineTransactionDAO.completeTransaction(tID);
            if (directURL != null) {
                //construct request to get id_token from Authorization code
                String pmURL = YTDataConfiguration.getLINE_LOGIN_API();
                URL url = new URL(pmURL);
                HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
                httpCon.setDoOutput(true);
                httpCon.setRequestMethod("POST");
                httpCon.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

                String gType = "grant_type=authorization_code";
                String cParam = "code=" + code;
                String rpURL = "redirect_uri=" + YTDataConfiguration.getYTDomain() + LogInOutViewMapping.LOGIN_LINEPAY_URL;
                String cID = "client_id=" + YTDataConfiguration.getLINE_LOGIN_ID();
                String cSecret = "client_secret=" + YTDataConfiguration.getLINE_LOGIN_SECRET();

                String rawData = gType + "&" + cParam + "&" + rpURL + "&" + cID
                        + "&" + cSecret;

                OutputStream os = httpCon.getOutputStream();
                os.write(rawData.getBytes());
                os.flush();
                OutputStreamWriter out = new OutputStreamWriter(
                        httpCon.getOutputStream());

                StringBuffer text = new StringBuffer();
                InputStreamReader in = new InputStreamReader((InputStream) httpCon.getContent());
                BufferedReader buff = new BufferedReader(in);
                String line;
                do {
                    line = buff.readLine();
                    if (line != null) {
                        text.append(line);
                    }
                } while (line != null);

                //decode response
                String rpStr = text.toString();
                ObjectMapper mapper = new ObjectMapper();
                JsonNode rpJSON = mapper.readTree(rpStr);
                String idToken = null;
                JsonNode idTokenNode = rpJSON.get("id_token");

                if (!idTokenNode.isNull()) {
                    idToken = idTokenNode.asText();
                }

                if (idToken != null) {
                    String[] idTokenArray = idToken.split("\\.");
                    String base64EncodedBody = idTokenArray[1];
                    Base64 base64Url = new Base64(true);

                    //decode id_token
                    String idTokenDecoded = new String(base64Url.decode(base64EncodedBody));
                    JsonNode rsNode = mapper.readTree(idTokenDecoded);

                    String userID = null, email = null;
                    JsonNode idNode = rsNode.get("sub");
                    if (!idNode.isNull()) {
                        userID = idNode.asText();
                    }
                    JsonNode emNode = rsNode.get("email");
                    if (emNode != null && !emNode.isNull()) {
                        email = emNode.asText();
                        if (email.isEmpty()) {
                            email = null;
                        }
                    }
                    out.close();

                    if (userID != null) {
                        TripperLinepayLoginInfo info = loginoutTripperDAO.getLinepayLoginInfo(userID);
                        String tripperID = null, displayName = null, avatar = null;
                        if (info != null) {
                            tripperID = info.getTripperID();
                            displayName = info.getDisplayName();
                            avatar = info.getAvatar();
                        } else {
                            tripperID = signupService.registerTripperLinePayAccount(email, userID);
                        }

                        loginAsTripper(request, response, session, tripperID, avatar, displayName, false, false);
                        return directURL;
                    }
                    throw new IllegalArgumentException("Wrong Decoded Response");
                }
                throw new IllegalArgumentException("Wrong Transaction ID");
            }
            throw new IllegalArgumentException("Wrong Response From Line");
        }
        // catch error when user reject permission
        String errorMessage = request.getParameter("error_description");
        throw new IllegalArgumentException(errorMessage.replaceAll("+", " "));
    }
}
