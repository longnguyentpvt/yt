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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import yt.func.packageregistration.service.PackageRegistrationService;

/**
 *
 * @author nickn
 */
@Controller
@RequestMapping(value = {YTData.PARTNER_PATH, "/{locale}" + YTData.PARTNER_PATH})
public class PackageRegistrationController {

    @Autowired
    private PackageRegistrationService packageRegistrationService;

    @RequestMapping(value = PackageRegistrationViewMapping.PACKAGE_REGISTRATION_URL, method = RequestMethod.POST)
    @ResponseBody
    public String registerNewPackage(HttpServletRequest request, HttpSession session) throws IOException {
        String data = (String) request.getAttribute(YTAttr.AJAX_DATA_ATTR);
        return packageRegistrationService.registerNewPackage(session, data);
    }

    @RequestMapping(value = PackageRegistrationViewMapping.REGULAR_EDITING_URL + "/{packageID}", method = RequestMethod.GET)
    public String packageRegistration(HttpServletRequest request, HttpSession session, @PathVariable String packageID) throws IOException {
        request.setAttribute("packgeID", packageID);
        return "partner/RegularEditing";
    }

    @RequestMapping(value = PackageRegistrationViewMapping.CATEGORY_DATA_CHECKING_URL, method = RequestMethod.POST)
    @ResponseBody
    public String checkCategoryData(HttpServletRequest request, HttpSession session) throws IOException {
        String data = (String) request.getAttribute(YTAttr.AJAX_DATA_ATTR);
        return packageRegistrationService.checkCategoryStep(session, data);
    }

    @RequestMapping(value = PackageRegistrationViewMapping.DESCRIPTION_DATA_CHECKING_URL, method = RequestMethod.POST)
    @ResponseBody
    public String checkDescriptionData(HttpServletRequest request, HttpSession session) throws IOException {
        String data = (String) request.getAttribute(YTAttr.AJAX_DATA_ATTR);
        return packageRegistrationService.checkDescriptionStep(session, data);
    }

    @RequestMapping(value = PackageRegistrationViewMapping.PHOTO_DATA_CHECKING_URL, method = RequestMethod.POST)
    @ResponseBody
    public String checkPhotoData(HttpServletRequest request, HttpSession session) throws IOException {
        String data = (String) request.getAttribute(YTAttr.AJAX_DATA_ATTR);
        return packageRegistrationService.checkPhotoStep(session, data);
    }
}
