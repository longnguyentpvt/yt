/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.func.javacriptLog;

import yt.func.home.*;
import yt.func.home.service.HomePackageService;
import java.io.IOException;
import javaclass.common.YTAttr;
import javaclass.utility.YTLocationUtility;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.Device;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import yt.func.javacriptLog.service.JavascriptLogService;

/**
 *
 * @author nickn
 */
@Controller
@RequestMapping(value = {"", "/{locale}"})
public class JavascriptLogController {

    @Autowired
    private JavascriptLogService javascriptLogService;

    @RequestMapping(value = {JavacriptLogViewMapping.JAVASCRIPT_LOG_URL}, method = RequestMethod.POST)
    @ResponseBody
    public String javaScriptLogException(HttpSession session, HttpServletRequest request, HttpServletResponse response,
            Device device)
            throws IOException {
        String data = (String) request.getAttribute(YTAttr.AJAX_DATA_ATTR);
        return javascriptLogService.saveJavascriptLog(data, device);
    }

}
