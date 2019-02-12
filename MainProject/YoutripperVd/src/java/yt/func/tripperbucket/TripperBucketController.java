/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.func.tripperbucket;

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
import yt.func.tripperbucket.service.TripperBucketService;

/**
 *
 * @author nickn
 */
@Controller
@RequestMapping(value = {YTData.TRIPPER_PATH, "/{locale}" + YTData.TRIPPER_PATH})
public class TripperBucketController {

    @Autowired
    TripperBucketService tripperBucketService;

    @RequestMapping(value = TripperBucketViewMapping.TRIPPER_BUCKET_URL, method = RequestMethod.GET)
    public String goToTripperBucket(HttpServletRequest request) {
        return "tripper/TripperBucket";
    }

    //add to bucket list
    @RequestMapping(value = TripperBucketViewMapping.TRIPPER_ADD_BUCKET, method = RequestMethod.POST)
    @ResponseBody
    public String addToBucketList(HttpServletRequest request, HttpSession session) throws IOException {
        String data = (String) request.getAttribute(YTAttr.AJAX_DATA_ATTR);
        String returnData = tripperBucketService.addToBucketList(session, data);
        return returnData;
    }

    //get bucket list
    @RequestMapping(value = TripperBucketViewMapping.TRIPPER_BUCKET_LIST, method = RequestMethod.POST)
    @ResponseBody
    public String loadBucketList(HttpSession session) throws IOException {
        String returnData = tripperBucketService.loadBuckets(session);
        return returnData;
    }

    //remove 
    @RequestMapping(value = TripperBucketViewMapping.TRIPPER_BUCKET_REMOVE, method = RequestMethod.POST)
    @ResponseBody
    public String removePackageList(HttpSession session, HttpServletRequest request) throws IOException {
        String data = (String) request.getAttribute(YTAttr.AJAX_DATA_ATTR);
        String returnData = tripperBucketService.removeBuckets(session, data);
        return returnData;
    }

}
