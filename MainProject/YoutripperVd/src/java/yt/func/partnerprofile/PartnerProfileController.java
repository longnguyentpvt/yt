/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.func.partnerprofile;

import com.fasterxml.jackson.core.JsonProcessingException;
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
import yt.func.partnerprofile.service.PartnerProfileService;

/**
 *
 * @author nickn
 */
@Controller
@RequestMapping(value = {YTData.PARTNER_PATH, "/{locale}" + YTData.PARTNER_PATH})
public class PartnerProfileController {

    @Autowired
    PartnerProfileService partnerprofileService;

    @RequestMapping(value = PartnerProfileViewMapping.PARTER_PROFILE_URL, method = RequestMethod.GET)

    public String goToPartnerProfile(HttpServletRequest request) throws JsonProcessingException {
        partnerprofileService.getData(request);
        return "partner/PartnerProfile";
    }

    //LOAD PARTNER PERSONAL INFORMATION
    @RequestMapping(value = PartnerProfileViewMapping.PARTER_LOAD_PERSONAL_INFOR, method = RequestMethod.POST)
    @ResponseBody
    public String getPersonalInformation(HttpServletRequest request, HttpSession session) throws IOException {
        String returnData = partnerprofileService.loadPartnerPersonalInformation(session);
        if (returnData != null) {
            return returnData;
        }
        throw new IllegalArgumentException("Partner profile - Load personal information");

    }

    //UPDATE PARTNER PERSONAL INFORMATION
    @RequestMapping(value = PartnerProfileViewMapping.PARTER_UPDATE_PERSONAL_INFOR, method = RequestMethod.POST)
    @ResponseBody
    public String updatePersonalInformation(HttpServletRequest request, HttpSession session) throws IOException {
        String data = (String) request.getAttribute(YTAttr.AJAX_DATA_ATTR);
        return partnerprofileService.updatePartnerPersonalInformation(data, session);
    }

    //UPDATE PARTNER ACCOUNT INFORMATION
    @RequestMapping(value = PartnerProfileViewMapping.PARTER_UPDATE_ACCOUNT_INFOR, method = RequestMethod.POST)
    @ResponseBody
    public String updateAccountInformation(HttpServletRequest request, HttpSession session) throws IOException {
        String data = (String) request.getAttribute(YTAttr.AJAX_DATA_ATTR);
        return partnerprofileService.updatePartnerAccountInformation(data, session);
        //    throw new IllegalArgumentException("Partner profile - Update personal information");
    }

    //LOAD PARTNER PERSONAL INFORMATION
    @RequestMapping(value = PartnerProfileViewMapping.PARTER_LOAD_BUSINESS_INFOR, method = RequestMethod.POST)
    @ResponseBody
    public String getBusinessInformation(HttpServletRequest request, HttpSession session) throws IOException {
        String returnData = partnerprofileService.loadPartnerBusinessInformation(session);
        if (returnData != null) {
            return returnData;
        }
        throw new IllegalArgumentException("Partner profile - Load business information");

    }

    // LOAD STATES
    @RequestMapping(value = PartnerProfileViewMapping.PARTER_LOAD_STATE, method = RequestMethod.POST)
    @ResponseBody
    public String getState(HttpServletRequest request) throws JsonProcessingException, IOException {
        String data = (String) request.getAttribute(YTAttr.AJAX_DATA_ATTR);
        return partnerprofileService.getPartnerStatesOfCountry(data);
    }

    //UPDATE BUSINESS INFORMATION
    @RequestMapping(value = PartnerProfileViewMapping.PARTER_UPDATE_BUSINESS_INFOR, method = RequestMethod.POST)
    @ResponseBody
    public String updateBusinessInformation(HttpServletRequest request, HttpSession session) throws IOException {
        String data = (String) request.getAttribute(YTAttr.AJAX_DATA_ATTR);
        return partnerprofileService.updatePartnerBusinessInformation(data, session);
        //    throw new IllegalArgumentException("Partner profile - Update personal information");
    }

}
