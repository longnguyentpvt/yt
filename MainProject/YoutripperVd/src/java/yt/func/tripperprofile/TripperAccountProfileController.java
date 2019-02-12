/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.func.tripperprofile;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.io.IOException;
import java.util.Locale;
import javaclass.common.YTAttr;
import javaclass.common.YTData;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import yt.func.tripperprofile.service.TripperProfileService;

/**
 *
 * @author nickn
 */
@Controller
@RequestMapping(value = {YTData.TRIPPER_PATH, "/{locale}" + YTData.TRIPPER_PATH})
public class TripperAccountProfileController {

    @Autowired
    TripperProfileService tripperprofileService;

    @RequestMapping(value = TripperAccountProfileViewMapping.TRIPPER_ACCOUNT_PROFILE_URL, method = RequestMethod.GET)
    public String goToTripperProfile(HttpServletRequest request, Locale locale) throws JsonProcessingException {
        tripperprofileService.getData(request, locale);
        return "tripper/TripperProfile";
    }

    //LOAD TRIPPER PERSONAL INFORMATION
    @RequestMapping(value = TripperAccountProfileViewMapping.TRIPPER_LOAD_PERSONAL_INFOR, method = RequestMethod.POST)
    @ResponseBody
    public String getPersonalInformation(HttpServletRequest request, HttpSession session) throws IOException {
        String returnData = tripperprofileService.loadTripperPersonalInformation(session);
        return returnData;
    }

    // LOAD STATES
    @RequestMapping(value = TripperAccountProfileViewMapping.TRIPPER_LOAD_STATE, method = RequestMethod.POST)
    @ResponseBody
    public String getState(HttpServletRequest request) throws JsonProcessingException, IOException {
        String data = (String) request.getAttribute(YTAttr.AJAX_DATA_ATTR);
        return tripperprofileService.getTripperStatesOfCountry(data);
    }

    //UPDATE TRIPPER PERSONAL INFORMATION
    @RequestMapping(value = TripperAccountProfileViewMapping.TRIPPER_UPDATE_PERSONAL_INFOR, method = RequestMethod.POST)
    @ResponseBody
    public String updatePersonalInformation(HttpServletRequest request, HttpSession session) throws IOException {
        String data = (String) request.getAttribute(YTAttr.AJAX_DATA_ATTR);
        return tripperprofileService.updateTripperPersonalInformation(data, session);
    }

    //UPDATE PARTNER ACCOUNT INFORMATION
    @RequestMapping(value = TripperAccountProfileViewMapping.TRIPPER_UPDATE_ACCOUNT_INFOR, method = RequestMethod.POST)
    @ResponseBody
    public String updateAccountInformation(HttpServletRequest request, HttpSession session) throws IOException {
        String data = (String) request.getAttribute(YTAttr.AJAX_DATA_ATTR);
        return tripperprofileService.updateTripperAccountInformation(data, session);

    }

    //UPDATE TRIPPER ACCOUNT INFORMATION EMAIL
    @RequestMapping(value = TripperAccountProfileViewMapping.TRIPPER_UPDATE_ACCOUNT_INFOR_EMAIL, method = RequestMethod.POST)
    @ResponseBody
    public String updateAccountInformationEmail(HttpServletRequest request, HttpSession session) throws IOException {
        String data = (String) request.getAttribute(YTAttr.AJAX_DATA_ATTR);
        return tripperprofileService.updateTripperAccountInformationEmail(data, session);
        //    throw new IllegalArgumentException("Partner profile - Update personal information");
    }

    //GET CREDIT LIST
    @RequestMapping(value = TripperAccountProfileViewMapping.TRIPPER_GET_CREDIT_CARD, method = RequestMethod.POST)
    @ResponseBody
    public String getTripperCards(HttpServletRequest request, HttpSession session) throws IOException {
        return tripperprofileService.getListCard(session);

    }

    //REMOVE CREDIT
    @RequestMapping(value = TripperAccountProfileViewMapping.TRIPPER_REMOVE_CREDIT_CARD, method = RequestMethod.POST)
    @ResponseBody
    public String removeCredit(HttpServletRequest request, HttpSession session) throws IOException {
        String data = (String) request.getAttribute(YTAttr.AJAX_DATA_ATTR);
        return tripperprofileService.removeCard(data, session);

    }

    //GET BILLING
    @RequestMapping(value = TripperAccountProfileViewMapping.TRIPPER_GET_BILLING, method = RequestMethod.POST)
    @ResponseBody
    public String getTripperBilling(HttpServletRequest request, HttpSession session) throws IOException {
        return tripperprofileService.getListBilling(session);

    }

    //REMOVE BILLING
    @RequestMapping(value = TripperAccountProfileViewMapping.TRIPPER_REMOVE_BILLING, method = RequestMethod.POST)
    @ResponseBody
    public String removeBilling(HttpServletRequest request, HttpSession session) throws IOException {
        String data = (String) request.getAttribute(YTAttr.AJAX_DATA_ATTR);
        return tripperprofileService.removeBilling(data, session);

    }

    //REMOVE CREDIT
    @RequestMapping(value = TripperAccountProfileViewMapping.TRIPPER_GET_BILLING_DETAIL, method = RequestMethod.POST)
    @ResponseBody
    public String getBillingDetail(HttpServletRequest request, HttpSession session) throws IOException {
        String data = (String) request.getAttribute(YTAttr.AJAX_DATA_ATTR);
        return tripperprofileService.getBillingDetail(data, session);

    }

    //GET BILLING
    @RequestMapping(value = TripperAccountProfileViewMapping.TRIPPER_LOAD_OPTIONAL, method = RequestMethod.POST)
    @ResponseBody
    public String loadOptional(HttpSession session) throws IOException {
        return tripperprofileService.loadOptional(session);
    }

    //GET BILLING
    @RequestMapping(value = TripperAccountProfileViewMapping.TRIPPER_SAVE_OPTIONAL, method = RequestMethod.POST)
    @ResponseBody
    public String saveOptional(HttpServletRequest request, HttpSession session) throws IOException {
        String data = (String) request.getAttribute(YTAttr.AJAX_DATA_ATTR);
        return tripperprofileService.saveOptional(session, data);

    }

}
