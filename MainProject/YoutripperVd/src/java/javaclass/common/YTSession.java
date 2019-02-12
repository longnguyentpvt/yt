/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaclass.common;

import java.util.List;
import javax.servlet.http.HttpSession;

/**
 *
 * @author nickn
 */
public class YTSession {

    private final static String ACCOUNT_SESSION = "ytAccount";
    private final static String HREF_LOCALE_SESSION = "hrefLocale";
    private final static String CURRENCY_CODE = "ytCurrency";
    private final static String FIRST_TIME_CHECKED = "ftChecked";
    private final static String FILE_META_SESSION = "fileMetas";
    private final static String PARTNER_CONTACT_SESSION = "pnContact";

    public static YTSessionAccount getAccountSession(HttpSession session) {
        return (YTSessionAccount) session.getAttribute(ACCOUNT_SESSION);
    }

    public static void setAccountSession(HttpSession session, YTSessionAccount acc) {
        session.setAttribute(ACCOUNT_SESSION, acc);
    }

    public static String getHrefLocale(HttpSession session) {
        return (String) session.getAttribute(HREF_LOCALE_SESSION);
    }

    public static void setHrefLocale(HttpSession session, String hrefLocale) {
        session.setAttribute(HREF_LOCALE_SESSION, hrefLocale);
    }

    public static String getCurrencyCode(HttpSession session) {
        return (String) session.getAttribute(CURRENCY_CODE);
    }

    public static void setCurrencyCode(HttpSession session, String currencyCode) {
        session.setAttribute(CURRENCY_CODE, currencyCode);
    }

    public static Boolean getFirstTimeChecked(HttpSession session) {
        return (Boolean) session.getAttribute(FIRST_TIME_CHECKED);
    }

    public static void setFirstTimeChecked(HttpSession session, Boolean checked) {
        session.setAttribute(FIRST_TIME_CHECKED, checked);
    }

    public static List<YTFileMeta> getFileMetas(HttpSession session) {
        return (List<YTFileMeta>) session.getAttribute(FILE_META_SESSION);
    }

    public static void setListFileMeta(HttpSession session, List<YTFileMeta> files) {
        session.setAttribute(FILE_META_SESSION, files);
    }

    public static Boolean getPartnerContact(HttpSession session) {
        return (Boolean) session.getAttribute(PARTNER_CONTACT_SESSION);
    }

    public static void setPartnerContact(HttpSession session, Boolean contact) {
        session.setAttribute(PARTNER_CONTACT_SESSION, contact);
    }

}
