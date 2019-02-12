/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.func.packageregistration;

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
import yt.func.packageregistration.service.CategoryRegistrationService;

/**
 *
 * @author nickn
 */
@Controller
@RequestMapping(value = {YTData.PARTNER_PATH, "/{locale}" + YTData.PARTNER_PATH})
public class CategoryRegistrationController {

    @Autowired
    private CategoryRegistrationService categoryRegistrationService;

    @RequestMapping(value = PackageRegistrationViewMapping.CATEGORY_DATA_LOADING_URL, method = RequestMethod.POST)
    @ResponseBody
    public String getCategoryData(HttpServletRequest request, HttpSession session) throws IOException {
        String data = (String) request.getAttribute(YTAttr.AJAX_DATA_ATTR);
        return categoryRegistrationService.getCategoryData(session, data);
    }

    @RequestMapping(value = PackageRegistrationViewMapping.CATEGORY_DATA_SUB_CATEGORY_LOADING_URL, method = RequestMethod.POST)
    @ResponseBody
    public String getSubCategory(HttpServletRequest request, HttpSession session) throws IOException {
        String data = (String) request.getAttribute(YTAttr.AJAX_DATA_ATTR);
        return categoryRegistrationService.getSubCategoryByCategory(data);
    }

    @RequestMapping(value = PackageRegistrationViewMapping.CATEGORY_INFO_UPDATING_URL, method = RequestMethod.POST)
    @ResponseBody
    public String updateCategoryInfo(HttpServletRequest request, HttpSession session) throws IOException {
        String data = (String) request.getAttribute(YTAttr.AJAX_DATA_ATTR);
        return categoryRegistrationService.updateCategoryAndSubcategory(session, data);
    }

    @RequestMapping(value = PackageRegistrationViewMapping.SUITABILITY_INFO_UPDATING_URL, method = RequestMethod.POST)
    @ResponseBody
    public String updateSuitability(HttpServletRequest request, HttpSession session) throws IOException {
        String data = (String) request.getAttribute(YTAttr.AJAX_DATA_ATTR);
        return categoryRegistrationService.updateSuitability(session, data);
    }

    @RequestMapping(value = PackageRegistrationViewMapping.TARGET_LOCATION_INFO_UPDATING_URL, method = RequestMethod.POST)
    @ResponseBody
    public String updateTargetLocation(HttpServletRequest request, HttpSession session) throws IOException {
        String data = (String) request.getAttribute(YTAttr.AJAX_DATA_ATTR);
        return categoryRegistrationService.updateTargetLocation(session, data);
    }

    @RequestMapping(value = PackageRegistrationViewMapping.COLOR_INFO_UPDATING_URL, method = RequestMethod.POST)
    @ResponseBody
    public String updatePackageColor(HttpServletRequest request, HttpSession session) throws IOException {
        String data = (String) request.getAttribute(YTAttr.AJAX_DATA_ATTR);
        return categoryRegistrationService.updatePackageColor(session, data);
    }
}
