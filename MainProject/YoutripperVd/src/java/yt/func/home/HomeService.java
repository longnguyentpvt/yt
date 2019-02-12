/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.func.home;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import javaclass.common.YTData;
import javaclass.common.YTSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

/**
 *
 * @author nickn
 */
public class HomeService {

    @Autowired
    private SessionLocaleResolver sessionLocaleResolver;

    public String currencySetting(String data, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode dataObject = mapper.readTree(data);

        JsonNode currencyNode = dataObject.get("currencyCode");
        String currencyCode = null;
        if (!currencyNode.isNull()) {
            currencyCode = currencyNode.asText();
        }

        if (currencyCode == null) {
            currencyCode = YTData.DEFAULT_CURRENCY_CODE;
        }

        YTSession.setCurrencyCode(session, currencyCode);

        JsonNode localeNode = dataObject.get("localeCode");
        String localeCode = null;
        if (!localeNode.isNull()) {
            localeCode = localeNode.asText();
        }
        if (localeCode == null) {
            localeCode = YTData.LOCALE_ENGLISH_CODE;
        }
        sessionLocaleResolver.setLocale(request, response, StringUtils.parseLocaleString(localeCode));

        return "{\"success\" : true}";
    }

}
