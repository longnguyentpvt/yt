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
import yt.func.packageregistration.service.DescriptionRegistrationService;

/**
 *
 * @author nickn
 */
@Controller
@RequestMapping(value = {YTData.PARTNER_PATH, "/{locale}" + YTData.PARTNER_PATH})
public class DescriptionRegistrationController {

    @Autowired
    private DescriptionRegistrationService descriptionRegistrationService;

    @RequestMapping(value = PackageRegistrationViewMapping.DESCRIPTION_DATA_LOADING_URL, method = RequestMethod.POST)
    @ResponseBody
    public String getDescriptionData(HttpServletRequest request, HttpSession session) throws IOException {
        String data = (String) request.getAttribute(YTAttr.AJAX_DATA_ATTR);
        return descriptionRegistrationService.getDescriptionData(session, data);
    }

    @RequestMapping(value = PackageRegistrationViewMapping.DESCRIPTION_DATA_ENABLE_EDITING_URL, method = RequestMethod.POST)
    @ResponseBody
    public String activeDescriptionEditing(HttpServletRequest request, HttpSession session) throws IOException {
        String data = (String) request.getAttribute(YTAttr.AJAX_DATA_ATTR);
        return descriptionRegistrationService.activeEditing(session, data);
    }

    @RequestMapping(value = PackageRegistrationViewMapping.DESCRIPTION_LANGUAGE_ACTIVATION_URL, method = RequestMethod.POST)
    @ResponseBody
    public String activeNewLanguage(HttpServletRequest request, HttpSession session) throws IOException {
        String data = (String) request.getAttribute(YTAttr.AJAX_DATA_ATTR);
        return descriptionRegistrationService.activeContentLanguage(session, data);
    }

    @RequestMapping(value = PackageRegistrationViewMapping.DESCRIPTION_LANGUAGE_UNACTIVATION_URL, method = RequestMethod.POST)
    @ResponseBody
    public String unactiveLanguage(HttpServletRequest request, HttpSession session) throws IOException {
        String data = (String) request.getAttribute(YTAttr.AJAX_DATA_ATTR);
        return descriptionRegistrationService.unActiveContentLanguage(session, data);
    }

    @RequestMapping(value = PackageRegistrationViewMapping.DESCRIPTION_PACKAGE_NAME_UPDATING_URL, method = RequestMethod.POST)
    @ResponseBody
    public String updatePackageName(HttpServletRequest request, HttpSession session) throws IOException {
        String data = (String) request.getAttribute(YTAttr.AJAX_DATA_ATTR);
        return descriptionRegistrationService.updatePackageName(session, data);
    }

    @RequestMapping(value = PackageRegistrationViewMapping.DESCRIPTION_MULTI_DESCRIPTION_UPDATING_URL, method = RequestMethod.POST)
    @ResponseBody
    public String updateMultiDescription(HttpServletRequest request, HttpSession session) throws IOException {
        String data = (String) request.getAttribute(YTAttr.AJAX_DATA_ATTR);
        return descriptionRegistrationService.updateMultiDescription(session, data);
    }

    @RequestMapping(value = PackageRegistrationViewMapping.DESCRIPTION_ACTIVITY_LOCATION_UPDATING_URL, method = RequestMethod.POST)
    @ResponseBody
    public String updateActivityLocation(HttpServletRequest request, HttpSession session) throws IOException {
        String data = (String) request.getAttribute(YTAttr.AJAX_DATA_ATTR);
        return descriptionRegistrationService.updateActivityLocation(session, data);
    }

    @RequestMapping(value = PackageRegistrationViewMapping.DESCRIPTION_DEPARTURE_LOCATION_UPDATING_URL, method = RequestMethod.POST)
    @ResponseBody
    public String updateDepartureLocation(HttpServletRequest request, HttpSession session) throws IOException {
        String data = (String) request.getAttribute(YTAttr.AJAX_DATA_ATTR);
        return descriptionRegistrationService.updateDepartureLocation(session, data);
    }

    @RequestMapping(value = PackageRegistrationViewMapping.DESCRIPTION_PICKUP_LOCATION_UPDATING_URL, method = RequestMethod.POST)
    @ResponseBody
    public String updatePickupLocation(HttpServletRequest request, HttpSession session) throws IOException {
        String data = (String) request.getAttribute(YTAttr.AJAX_DATA_ATTR);
        return descriptionRegistrationService.updatePickupLocation(session, data);
    }
    
    @RequestMapping(value = PackageRegistrationViewMapping.DESCRIPTION_KEYWORDS_UPDATING_URL, method = RequestMethod.POST)
    @ResponseBody
    public String updateKeywords(HttpServletRequest request, HttpSession session) throws IOException {
        String data = (String) request.getAttribute(YTAttr.AJAX_DATA_ATTR);
        return descriptionRegistrationService.updateKeywords(session, data);
    }
    
    @RequestMapping(value = PackageRegistrationViewMapping.DESCRIPTION_GOOGLE_DESC_UPDATING_URL, method = RequestMethod.POST)
    @ResponseBody
    public String updateGoogleDescription(HttpServletRequest request, HttpSession session) throws IOException {
        String data = (String) request.getAttribute(YTAttr.AJAX_DATA_ATTR);
        return descriptionRegistrationService.updateGoogleDescription(session, data);
    }
    
    @RequestMapping(value = PackageRegistrationViewMapping.DESCRIPTION_SERVING_LANGUAGE_UPDATING_URL, method = RequestMethod.POST)
    @ResponseBody
    public String updateServingLanguage(HttpServletRequest request, HttpSession session) throws IOException {
        String data = (String) request.getAttribute(YTAttr.AJAX_DATA_ATTR);
        return descriptionRegistrationService.updateServingLanguage(session, data);
    }
}
