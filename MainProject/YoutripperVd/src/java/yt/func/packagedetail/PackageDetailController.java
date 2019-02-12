/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.func.packagedetail;

import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import yt.func.packagedetail.service.PackageDetailService;

/**
 *
 * @author nickn
 */
@Controller
@RequestMapping(value = {"", "/{locale}"})
public class PackageDetailController {
    
    @Autowired
    private PackageDetailService packageDetailService;

    @RequestMapping(value = PackageDetailViewMapping.PACKAGE_DETAIL_URL + "/{siteURL}/{packageID}", method = RequestMethod.GET)
    public String goToPackageDetail(@PathVariable String packageID, HttpServletRequest request, HttpSession session, Locale locale) {
        packageDetailService.getPackageDetailData(packageID, request, locale, session);
        return "common/PackageDetail";
    }
}
