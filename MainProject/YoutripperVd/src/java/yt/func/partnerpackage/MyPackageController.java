/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.func.partnerpackage;

import java.io.IOException;
import javaclass.common.YTAttr;
import javaclass.common.YTData;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import yt.func.partnerpackage.javaclass.MyPackageFilter;
import yt.func.partnerpackage.service.MyPackageService;

/**
 *
 * @author nickn
 */
@Controller
@RequestMapping(value = {YTData.PARTNER_PATH, "/{locale}" + YTData.PARTNER_PATH})
public class MyPackageController {

    @Autowired
    private MyPackageService myPackageService;

    @RequestMapping(value = MyPackageViewMapping.MY_REGULAR_PACKAGE_URL, method = RequestMethod.GET)
    public String goToRegularPackages(HttpServletRequest request) {
        request.setAttribute("servingType", MyPackageFilter.PACKAGE_SERVING_TYPE_REGULAR);
        return "partner/MyPackages";
    }

    @RequestMapping(value = MyPackageViewMapping.MY_OPEN_TIMED_PACKAGE_URL, method = RequestMethod.GET)
    public String goToOpenTimedPackages(HttpServletRequest request) {
        request.setAttribute("servingType", MyPackageFilter.PACKAGE_SERVING_TYPE_OPEN_TIMED);
        return "partner/MyPackages";
    }

    @RequestMapping(value = MyPackageViewMapping.MY_REGULAR_PACKAGE_FILTERING_URL, method = RequestMethod.POST)
    @ResponseBody
    public String filterMyPackage(HttpServletRequest request, HttpSession session) throws IOException {
        String data = (String) request.getAttribute(YTAttr.AJAX_DATA_ATTR);
        return myPackageService.getPartnerPackages(session, data);
    }

}
