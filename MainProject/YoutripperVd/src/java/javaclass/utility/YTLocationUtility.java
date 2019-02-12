/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaclass.utility;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.model.CountryResponse;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javaclass.common.YTData;
import javax.servlet.http.HttpServletRequest;
import yt.javaclass.config.YTDataConfiguration;

/**
 *
 * @author nickn
 */
public class YTLocationUtility {

    public static String getipAddress(HttpServletRequest request) {
        String remoteAddr = null;
        try {
            if (request != null) {
                remoteAddr = request.getRemoteAddr();
                if (remoteAddr == null) {
                    remoteAddr = request.getHeader("X-FORWARDED-FOR");
                }

                if (remoteAddr != null) {
                    if (remoteAddr.equalsIgnoreCase("0:0:0:0:0:0:0:1")) {
                        InetAddress inetAddress = InetAddress.getLocalHost();
                        remoteAddr = inetAddress.getHostAddress();
                    }
                }
            }
        } catch (Exception e) {
        }
        return remoteAddr;
    }

    public static String getClientLocationCode(HttpServletRequest request) {
        String rqCode = YTData.LOCALE_ENGLISH_CODE;
        try {
            String ip = getipAddress(request);
            DatabaseReader db = YTData.MAXMIND_COUNTRY_DB;
            if (ip != null && db != null) {
                InetAddress ipAddress = InetAddress.getByName(ip);
                CountryResponse countryResponse = db.country(ipAddress);

                String returnCode = countryResponse.getCountry().getIsoCode();
                if (returnCode.equals("TH")) {
                    rqCode = YTData.LOCALE_THAI_CODE;
                }
            }
        } catch (Exception e) {
        }
        return rqCode;
    }

    public static void generateLocationAlternate(HttpServletRequest request, String url) {
        List<String[]> alts = new ArrayList<>();
        String dm = YTDataConfiguration.getYTDomain();
        String enCode = YTData.LOCALE_ENGLISH_ALTERNATE_CODE;
        String enURL = dm + YTUrlUtility.getLocalePath(YTData.LOCALE_ENGLISH_CODE) + url;
        String[] en = {enCode, enURL};
        alts.add(en);

        String thCode = YTData.LOCALE_THAI_ALTERNATE_CODE;
        String thURL = dm + YTUrlUtility.getLocalePath(YTData.LOCALE_THAI_CODE) + url;
        String[] th = {thCode, thURL};
        alts.add(th);
        request.setAttribute("alternates", alts);
    }

    public static String getLocale(Locale locale) {
        return locale.toString();
    }
}
