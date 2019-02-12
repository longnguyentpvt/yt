/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.func.javacriptLog.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import javaclass.utility.YTDateTimeUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.Device;
import org.springframework.transaction.annotation.Transactional;
import yt.entity.tbl.JavascriptLog;
import yt.func.javacriptLog.dao.JavascriptLogDAO;

/**
 *
 * @author Hiep
 */
public class JavascriptLogServiceImpl implements JavascriptLogService {
    
    @Autowired
    private JavascriptLogDAO javascriptLogDAO;
    
    @Override
    @Transactional
    public String saveJavascriptLog(String data, Device device) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        // get infor from json
        JsonNode dataObject = mapper.readTree(data);
        String pageURL = dataObject.get("pageURL").asText();
        String pageID = dataObject.get("pageID").asText();

        //error date time
        long errorDateTime = YTDateTimeUtility.getCurrentTimeInMilli();

        //device info
        String deviceInfo = "Unknow";
        if (device.isNormal()) {
            deviceInfo = "PC/Laptop";
        }
        if (device.isMobile()) {
            deviceInfo = "Mobile";
        }
        if (device.isTablet()) {
            deviceInfo = "Tablet";
        }

        //is mobile access
        boolean mobileAccess = device.isMobile();

        //Android IOS ...
        String mobileInfo = device.getDevicePlatform().name();

        //browser Chrome, FireFox, ...
        String browserType = dataObject.get("browserName").asText();

        //version Chrome 64, ...
        String browserVersion = "Unknow";
        JsonNode versionNode = dataObject.get("browserVersion");
        if (browserVersion.length() <= 10 && !versionNode.isNull()) {
            browserVersion = versionNode.asText();
        }

        // mobile info Android 5.0.0, ...
        String version = null;
        if (!dataObject.get("mobileVersion").isNull()) {
            version = dataObject.get("mobileVersion").asText();
            if (!version.equals("null")) {
                version = version.replaceAll("_", ".");
                mobileInfo = mobileInfo + " " + version;
            }
        }

        // construct object
        JavascriptLog javascriptLog = new JavascriptLog();
        javascriptLog.setErrorDateTime(errorDateTime);
        javascriptLog.setPageID(pageID);
        javascriptLog.setPageURL(pageURL);
        javascriptLog.setBrowserType(browserType);
        javascriptLog.setBrowserVersion(browserVersion);
        javascriptLog.setDeviceInfo(deviceInfo);
        javascriptLog.setMobileAccesss(mobileAccess);
        javascriptLog.setMobileInfo(mobileInfo);
        javascriptLogDAO.insertToWishlist(javascriptLog);
        return "{\"result\": " + true + "}";
    }
    
}
