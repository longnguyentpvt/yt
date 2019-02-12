/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.func.partnerbookingmanager;

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
import yt.func.partnerbookingmanager.service.PartnerBookingManagerService;

/**
 *
 * @author nickn
 */
@Controller
@RequestMapping(value = {YTData.PARTNER_PATH, "/{locale}" + YTData.PARTNER_PATH})
public class PartnerBookingManagerController {

    @Autowired
    private PartnerBookingManagerService partnerBookingManagerService;

    @RequestMapping(value = PartnerBookingViewMapping.BOOKING_MANAGER_URL, method = RequestMethod.GET)
    public String packageRegistration(HttpSession session, HttpServletRequest request) throws IOException {
        partnerBookingManagerService.getCommonData(session, request);
        return "partner/BookingManager";
    }

    // LOAD REGUALR BOOKING LIST
    @RequestMapping(value = PartnerBookingViewMapping.BOOKING_MANAGER_LOAD_REGULAR_BOOKING, method = RequestMethod.POST)
    public @ResponseBody
    String loadRegularBooking(HttpSession session, HttpServletRequest request) throws IOException {
        String data = (String) request.getAttribute(YTAttr.AJAX_DATA_ATTR);
        return partnerBookingManagerService.loadRegularBookingManager(session, data);
    }

    // LOAD OPEN BOOKING LIST
    @RequestMapping(value = PartnerBookingViewMapping.BOOKING_MANAGER_LOAD_OPEN_BOOKING, method = RequestMethod.POST)
    public @ResponseBody
    String loadOpenBooking(HttpSession session, HttpServletRequest request) throws IOException {
        String data = (String) request.getAttribute(YTAttr.AJAX_DATA_ATTR);
        return partnerBookingManagerService.loadOpenBookingManager(session, data);
    }
}
