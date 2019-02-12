/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.javaclass.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 *
 * @author nickn
 */
@Component
public class YTDataConfiguration {

    private static String RESOURCE_CSS_URL;
    private static String RESOURCE_JS_URL;
    private static String COMMON_IMAGE_URL;

    private static String YT_DOMAIN;

    private static String REGISTERED_GOOGLE_ID;
    private static String REGISTERED_FACEBOOK_ID;

    private static String CDN_URL_LINK;
    private static String CDN_FOLDER;

    private static String MERCHANT_2C2P;
    private static String SECRET_2C2P;
    private static String JS_2C2P;
    private static String AUTH_2C2P;

    private static String PAYPAL_MODE;
    private static String PAYPAL_CLIENT_ID;
    private static String PAYPAL_CLIENT_SECRET;

    private static String LINE_PAYMENT_URL;
    private static String LINE_CHANNEL_ID;
    private static String LINE_SECRET;

    private static String LINE_LOGIN_ACCESS;
    private static String LINE_LOGIN_API;
    private static String LINE_LOGIN_ID;
    private static String LINE_LOGIN_SECRET;

    @Value("${yt.config.css.url}")
    private void setResource_css_url(String cssURL) {
        this.RESOURCE_CSS_URL = cssURL;
    }

    public static String getCssURL() {
        return RESOURCE_CSS_URL;
    }

    @Value("${yt.config.js.url}")
    private void setResource_js_url(String jsURL) {
        this.RESOURCE_JS_URL = jsURL;
    }

    public static String getJsURL() {
        return RESOURCE_JS_URL;
    }

    @Value("${yt.config.images.url}")
    private void setCommon_image_url(String imageURL) {
        YTDataConfiguration.COMMON_IMAGE_URL = imageURL;
    }

    public static String getCommonImageURL() {
        return COMMON_IMAGE_URL;
    }

    @Value("${yt.config.domain}")
    private void setYt_domain(String ytDomain) {
        this.YT_DOMAIN = ytDomain;
    }

    public static String getYTDomain() {
        return YT_DOMAIN;
    }

    @Value("${yt.social.gg.id}")
    private void setRegistered_google_id(String id) {
        this.REGISTERED_GOOGLE_ID = id;
    }

    public static String getRegisteredGoogleID() {
        return REGISTERED_GOOGLE_ID;
    }

    @Value("${yt.social.fb.id}")
    private void setRegistered_facebook_id(String id) {
        this.REGISTERED_FACEBOOK_ID = id;
    }

    public static String getRegisteredFacebookID() {
        return REGISTERED_FACEBOOK_ID;
    }

    @Value("${yt.cdn.url}")
    private void setCDN_URL_LINK(String CDN_URL_LINK) {
        this.CDN_URL_LINK = CDN_URL_LINK;
    }

    public static String getCDN_URL_LINK() {
        return CDN_URL_LINK;
    }

    @Value("${yt.cdn.bucket.name}")
    private void setCDN_FOLDER(String CDN_FOLDER) {
        this.CDN_FOLDER = CDN_FOLDER;
    }

    public static String getCDN_FOLDER() {
        return CDN_FOLDER;
    }

    public static String getMERCHANT_2C2P() {
        return MERCHANT_2C2P;
    }

    @Value("${yt.payment.2c2p.merchant}")
    private void setMERCHANT_2C2P(String MERCHANT_2C2P) {
        YTDataConfiguration.MERCHANT_2C2P = MERCHANT_2C2P;
    }

    public static String getSECRET_2C2P() {
        return SECRET_2C2P;
    }

    @Value("${yt.payment.2c2p.secret}")
    private void setSECRET_2C2P(String SECRET_2C2P) {
        YTDataConfiguration.SECRET_2C2P = SECRET_2C2P;
    }

    public static String getJS_2C2P() {
        return JS_2C2P;
    }

    @Value("${yt.payment.2c2p.jslink}")
    private void setJS_2C2P(String JS_2C2P) {
        YTDataConfiguration.JS_2C2P = JS_2C2P;
    }

    public static String getAUTH_2C2P() {
        return AUTH_2C2P;
    }

    @Value("${yt.payment.2c2p.authlink}")
    private void setAUTH_2C2P(String AUTH_2C2P) {
        YTDataConfiguration.AUTH_2C2P = AUTH_2C2P;
    }

    public static String getPAYPAL_MODE() {
        return PAYPAL_MODE;
    }

    @Value("${yt.payment.paypal.mode}")
    private void setPAYPAL_MODE(String PAYPAL_MODE) {
        YTDataConfiguration.PAYPAL_MODE = PAYPAL_MODE;
    }

    public static String getPAYPAL_CLIENT_ID() {
        return PAYPAL_CLIENT_ID;
    }

    @Value("${yt.payment.paypal.clientid}")
    private void setPAYPAL_CLIENT_ID(String PAYPAL_CLIENT_ID) {
        YTDataConfiguration.PAYPAL_CLIENT_ID = PAYPAL_CLIENT_ID;
    }

    public static String getPAYPAL_CLIENT_SECRET() {
        return PAYPAL_CLIENT_SECRET;
    }

    @Value("${yt.payment.paypal.clientsecret}")
    private void setPAYPAL_CLIENT_SECRET(String PAYPAL_CLIENT_SECRET) {
        YTDataConfiguration.PAYPAL_CLIENT_SECRET = PAYPAL_CLIENT_SECRET;
    }

    public static String getLINE_PAYMENT_URL() {
        return LINE_PAYMENT_URL;
    }

    @Value("${yt.payment.line.payment}")
    private void setLINE_PAYMENT_URL(String LINE_PAYMENT_URL) {
        YTDataConfiguration.LINE_PAYMENT_URL = LINE_PAYMENT_URL;
    }

    public static String getLINE_CHANNEL_ID() {
        return LINE_CHANNEL_ID;
    }

    @Value("${yt.payment.line.cnID}")
    private void setLINE_CHANNEL_ID(String LINE_CHANNEL_ID) {
        YTDataConfiguration.LINE_CHANNEL_ID = LINE_CHANNEL_ID;
    }

    public static String getLINE_SECRET() {
        return LINE_SECRET;
    }

    @Value("${yt.payment.line.secret}")
    private void setLINE_SECRET(String LINE_SECRET) {
        YTDataConfiguration.LINE_SECRET = LINE_SECRET;
    }

    public static String getLINE_LOGIN_ACCESS() {
        return LINE_LOGIN_ACCESS;
    }

    @Value("${yt.login.line.access}")
    private void setLINE_LOGIN_ACCESS(String LINE_LOGIN_ACCESS) {
        YTDataConfiguration.LINE_LOGIN_ACCESS = LINE_LOGIN_ACCESS;
    }

    public static String getLINE_LOGIN_API() {
        return LINE_LOGIN_API;
    }

    @Value("${yt.login.line.api}")
    private void setLINE_LOGIN_API(String LINE_LOGIN_API) {
        YTDataConfiguration.LINE_LOGIN_API = LINE_LOGIN_API;
    }

    public static String getLINE_LOGIN_ID() {
        return LINE_LOGIN_ID;
    }

    @Value("${yt.login.line.cnID}")
    private void setLINE_LOGIN_ID(String LINE_LOGIN_ID) {
        YTDataConfiguration.LINE_LOGIN_ID = LINE_LOGIN_ID;
    }

    public static String getLINE_LOGIN_SECRET() {
        return LINE_LOGIN_SECRET;
    }

    @Value("${yt.login.line.secret}")
    private void setLINE_LOGIN_SECRET(String LINE_LOGIN_SECRET) {
        YTDataConfiguration.LINE_LOGIN_SECRET = LINE_LOGIN_SECRET;
    }

}
