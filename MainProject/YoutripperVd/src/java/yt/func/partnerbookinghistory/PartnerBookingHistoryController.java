/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.func.partnerbookinghistory;

import yt.func.partnerbookingmanager.*;
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
import yt.func.partnerbookinghistory.service.PartnerBookingHistoryService;

/**
 *
 * @author nickn
 */
@Controller
@RequestMapping(value = {YTData.PARTNER_PATH, "/{locale}" + YTData.PARTNER_PATH})
public class PartnerBookingHistoryController {

    @Autowired
    private PartnerBookingHistoryService partnerBookingHistoryService;

    @RequestMapping(value = PartnerBookingHistoryViewMapping.BOOKING_HISTORY_URL, method = RequestMethod.GET)
    public String goToPartBookingHistory(HttpSession session, HttpServletRequest request) throws IOException {
        partnerBookingHistoryService.getCommonData(session, request);
        return "partner/BookingHistory";
    }

    // LOAD REGUALR BOOKING LIST
    @RequestMapping(value = PartnerBookingHistoryViewMapping.BOOKING_HISTORY_LOAD_REGULAR_BOOKING, method = RequestMethod.POST)
    public @ResponseBody
    String loadRegularBookingHistoryList(HttpSession session, HttpServletRequest request) throws IOException {
        String data = (String) request.getAttribute(YTAttr.AJAX_DATA_ATTR);
        return partnerBookingHistoryService.loadRegularBookingHistory(session, data);
    }

    // LOAD OPEN BOOKING LIST
    @RequestMapping(value = PartnerBookingHistoryViewMapping.BOOKING_HISTORY_LOAD_OPEN_BOOKING, method = RequestMethod.POST)
    public @ResponseBody
    String loadOpenBookingHistoryList(HttpSession session, HttpServletRequest request) throws IOException {
        String data = (String) request.getAttribute(YTAttr.AJAX_DATA_ATTR);
        return partnerBookingHistoryService.loadOpenBookingHistory(session, data);
    }
}
