/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.func.tripperbucket.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javaclass.common.YTSession;
import javaclass.common.YTSessionAccount;
import javaclass.common.ytpackage.YTPackageData;
import javaclass.utility.ContentUtility;
import javaclass.utility.YTDateTimeUtility;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yt.entity.tbl.TripperWishlist;
import yt.func.tripperbucket.dao.TripperWishlistDAO;
import yt.func.tripperbucket.javaclass.AdBucket;

/**
 *
 * @author Hiep
 */
@Service
public class TripperBucketServiceImpl implements TripperBucketService {

    @Autowired
    private TripperWishlistDAO tripperWishlistDAO;

    @Override
    @Transactional
    public String addToBucketList(HttpSession session, String data) throws IOException {
        YTSessionAccount account = YTSession.getAccountSession(session);
        String tripperID = account.getTripperID();
        //
        ObjectMapper mapper = new ObjectMapper();
        JsonNode dataObject = mapper.readTree(data);
        //get pkg ID
        String packageID = dataObject.get("packageID").asText();
        //current time
        long currentTime = YTDateTimeUtility.getCurrentTimeInMilli();
        boolean isExist = tripperWishlistDAO.checkExist(tripperID, packageID);
        if (!isExist) {
            TripperWishlist tripperWishlist = new TripperWishlist();
            tripperWishlist.setPackageID(packageID);
            tripperWishlist.setTripperID(tripperID);
            tripperWishlist.setWishlistDateTime(currentTime);
            tripperWishlistDAO.insertToWishlist(tripperWishlist);
            return "{\"result\": " + true + "}";
        }
        throw new IllegalArgumentException("Package is exist in list");
    }

    @Override
    @Transactional
    public String loadBuckets(HttpSession session) throws IOException {
        YTSessionAccount account = YTSession.getAccountSession(session);
        String tripperID = account.getTripperID();
        String jString = null;

        List<AdBucket> adBuckets = tripperWishlistDAO.getBucketList(tripperID);
        List<AdBucket> finalAdBs = new ArrayList<>();
        for (AdBucket aB : adBuckets) {
            String stt = aB.getStatus();
            if (stt.equals(YTPackageData.YT_PACKAGE_STATUS_APPROVED)) {
                finalAdBs.add(aB);
            }
        }
        ObjectMapper mapper = new ObjectMapper();
        jString = mapper.writeValueAsString(finalAdBs);
        return jString;
    }

    @Override
    @Transactional
    public String removeBuckets(HttpSession session, String data) throws IOException {
        YTSessionAccount account = YTSession.getAccountSession(session);
        String tripperID = account.getTripperID();
        //
        ObjectMapper mapper = new ObjectMapper();
        JsonNode dataObject = mapper.readTree(data);
        //get pkg ID
        String packageID = dataObject.get("packageID").asText();

        boolean success = tripperWishlistDAO.removeBucketByID(packageID, tripperID);
        if (success) {
            return "{\"result\": " + success + "}";
        }
        throw new IllegalArgumentException("Remove Error");
    }

}
