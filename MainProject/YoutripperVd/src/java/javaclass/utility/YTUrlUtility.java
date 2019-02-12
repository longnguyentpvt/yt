/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaclass.utility;

import java.util.Locale;
import javaclass.common.YTData;

/**
 *
 * @author nickn
 */
public class YTUrlUtility {

    public static String getLocalePath(Locale locale) {
        String currentLocale = locale.toString();
        
        String path = "";
        if (currentLocale.equals(YTData.LOCALE_THAI_CODE)) {
            path = YTData.LOCALE_THAI_PATH;
        }
        return path;
    }

    public static String getLocalePath(String languageCode) {
        String path = "";
        if (languageCode.equals(YTData.LOCALE_THAI_CODE)) {
            path = YTData.LOCALE_THAI_PATH;
        }
        return path;
    }

    public static String getDirectURL(Locale locale, String url) {
        return "redirect:" + getLocalePath(locale) + url;
    }
}
