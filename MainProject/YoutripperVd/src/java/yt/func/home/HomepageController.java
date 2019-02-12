/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.func.home;

import yt.func.home.service.HomePackageService;
import java.io.IOException;
import javaclass.common.YTAttr;
import javaclass.utility.YTLocationUtility;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.Device;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author nickn
 */
@Controller
@RequestMapping(value = {"", "/{locale}"})
public class HomepageController {

    @Autowired
    private HomeService homeService;

    @Autowired
    private HomePackageService homePackageService;

    @RequestMapping(value = {HomeViewMapping.HOME_PAGE_URL}, method = RequestMethod.GET)
    public String goToMainPage(HttpSession session, HttpServletRequest request, Device device) {
        YTLocationUtility.generateLocationAlternate(request, HomeViewMapping.HOME_PAGE_URL);
        if (!device.isNormal()) {
            return "common-m/homepage";
        }
        return "common/homepage";
    }

    @RequestMapping(value = {HomeViewMapping.CURRENCY_SETTING_URL}, method = RequestMethod.POST)
    @ResponseBody
    public String currencySetting(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String data = (String) request.getAttribute(YTAttr.AJAX_DATA_ATTR);
        return homeService.currencySetting(data, request, response, session);
    }

    @RequestMapping(value = {"/design"}, method = RequestMethod.GET)
    public String goToDesignPage(HttpSession session, HttpServletRequest request, Device device) {
        if (!device.isNormal()) {
            return "common-m/design";
        }
        return "common/design";
    }

    @RequestMapping(value = {"/package-module"}, method = RequestMethod.GET)
    public String goToPackageDesign(HttpSession session, HttpServletRequest request, Device device) {
        return "common-m/activity";
    }

    @RequestMapping(value = {"/search-module"}, method = RequestMethod.GET)
    public String goToSearchDesign(HttpSession session, HttpServletRequest request, Device device) {
        return "common-m/search";
    }

    // LOAD HOSTEST DEAL
    @RequestMapping(value = HomeViewMapping.LOAD_HOTEST_DEAL, method = RequestMethod.POST)
    public @ResponseBody
    String loadHotestDealPackage(HttpSession session, HttpServletRequest request) throws IOException {
        String data = (String) request.getAttribute(YTAttr.AJAX_DATA_ATTR);
        return homePackageService.loadHotestDeal(data);
    }

    // LOAD JUST BOOKED PACKAGE
    @RequestMapping(value = HomeViewMapping.LOAD_JUST_BOOK, method = RequestMethod.POST)
    public @ResponseBody
    String loadJustBookedPackage(HttpSession session, HttpServletRequest request) throws IOException {
        String data = (String) request.getAttribute(YTAttr.AJAX_DATA_ATTR);
        return homePackageService.loadJustBooked(data);
    }

    //LOAD EXPLORE MORE
    @RequestMapping(value = HomeViewMapping.LOAD_EXPLORE_MORE, method = RequestMethod.POST)
    public @ResponseBody
    String exploreMorePackage(HttpSession session, HttpServletRequest request) throws Exception {
        String data = (String) request.getAttribute(YTAttr.AJAX_DATA_ATTR);
        return homePackageService.loadExploreMore(data);
    }

}
