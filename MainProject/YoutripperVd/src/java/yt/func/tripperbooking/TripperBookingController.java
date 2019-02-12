/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.func.tripperbooking;

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
import yt.func.tripperbooking.service.TripperBookingService;

/**
 *
 * @author nickn
 */
@Controller
@RequestMapping(value = {YTData.TRIPPER_PATH, "/{locale}" + YTData.TRIPPER_PATH})
public class TripperBookingController {

    @Autowired
    TripperBookingService tripperBookingService;

    @RequestMapping(value = TripperBookingViewMapping.TRIPPER_BOOKING_URL, method = RequestMethod.GET)
    public String goToTripperBooking(HttpServletRequest request) {
        return "tripper/MyBooking";
    }

    //booking list
    @RequestMapping(value = TripperBookingViewMapping.TRIPPER_BOOKING_LIST, method = RequestMethod.POST)
    @ResponseBody
    public String loadTripperBooking(HttpSession session, HttpServletRequest request) throws IOException {
        String data = (String) request.getAttribute(YTAttr.AJAX_DATA_ATTR);
        String returnData = tripperBookingService.loadTripperBooking(session, data);
        return returnData;
    }
}
