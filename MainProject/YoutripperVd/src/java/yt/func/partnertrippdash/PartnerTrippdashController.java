/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.func.partnertrippdash;

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
import yt.func.partnertrippdash.service.PartnerTrippDashService;

/**
 *
 * @author KyLong
 */
@Controller
@RequestMapping(value = {YTData.PARTNER_PATH, "/{locale}" + YTData.PARTNER_PATH})
public class PartnerTrippdashController {

    @Autowired
    private PartnerTrippDashService partnerTrippDashService;

    @RequestMapping(value = PartnerTrippdashViewMapping.TRIPPDASH_URL, method = RequestMethod.GET)
    public String goToPartnerTrippdash(HttpServletRequest request) throws IOException {
        return "partner/TrippDash";
    }

    //LOAD PARTNER PERSONAL INFORMATION
    @RequestMapping(value = PartnerTrippdashViewMapping.PARTNER_TRIPPDASH_LOAD_PACKAGE_OVERVIEW, method = RequestMethod.POST)
    @ResponseBody
    public String getPersonalInformation(HttpServletRequest request, HttpSession session) throws JsonProcessingException {
        return partnerTrippDashService.loadPackageOverview(session);
    }

    //LOAD HIGHEST GROSSING
    @RequestMapping(value = PartnerTrippdashViewMapping.PARTNER_TRIPPDASH_LOAD_HIGHEST_GROSSING, method = RequestMethod.POST)
    @ResponseBody
    public String loadHighestGrossing(HttpServletRequest request, HttpSession session) throws JsonProcessingException {
        return partnerTrippDashService.loadHigestGrossing(session);
    }

    //LOAD TOTAL SALE
    @RequestMapping(value = PartnerTrippdashViewMapping.PARTNER_TRIPPDASH_LOAD_TOTAL_SALE, method = RequestMethod.POST)
    @ResponseBody
    public String loadHighestTotalSale(HttpServletRequest request, HttpSession session) throws JsonProcessingException {
        return partnerTrippDashService.loadTotalSales(session);
    }

    //LOAD TOTAL SALE
    @RequestMapping(value = PartnerTrippdashViewMapping.PARTNER_TRIPPDASH_LOAD_UPCOMING, method = RequestMethod.POST)
    @ResponseBody
    public String loadUpcoming(HttpServletRequest request, HttpSession session) throws IOException {
        String data = (String) request.getAttribute(YTAttr.AJAX_DATA_ATTR);
        return partnerTrippDashService.loadUpcomingToday(session, data);
    }

    //LOAD PACKAGE BEST SELLING
    @RequestMapping(value = PartnerTrippdashViewMapping.PARTNER_TRIPPDASH_LOAD_SELLING, method = RequestMethod.POST)
    @ResponseBody
    public String loadBestSelling(HttpServletRequest request, HttpSession session) throws IOException {
        return partnerTrippDashService.loadBestSelling(session);
    }

}
