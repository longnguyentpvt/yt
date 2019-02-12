/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.func.partnerbookinghistory.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.List;
import javaclass.common.YTSession;
import javaclass.common.YTSessionAccount;
import javaclass.common.ytpackage.YTPackageData;
import javaclass.utility.ContentUtility;
import javaclass.utility.YTDateTimeUtility;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yt.entity.tbl.YTPackage;
import yt.func.partnerbookinghistory.dao.YTBookingOrderDAO;
import yt.func.partnerbookinghistory.dao.YTPackageDAO;
import yt.func.partnerbookinghistory.javaclass.AdHistoryOpenBookingOrder;
import yt.func.partnerbookinghistory.javaclass.AdHistoryRegularBookingOrder;
import yt.func.partnerbookinghistory.javaclass.InitData;

/**
 *
 * @author Hiep
 */
@Service
public class PartnerBookingHistoryServiceImpl implements PartnerBookingHistoryService {

    @Autowired
    private YTBookingOrderDAO partnerBookingHistoryOrderDAO;

    @Autowired
    private YTPackageDAO partnerBookingHistoryPackageDAO;

    @Override
    @Transactional
    public void getCommonData(HttpSession session, HttpServletRequest request) throws IOException {
        // get partnerID      
        YTSessionAccount account = YTSession.getAccountSession(session);
        String partnerID = account.getPartnerID();
        List<YTPackage> ytps = partnerBookingHistoryPackageDAO.loadAllPackage(partnerID);
        for (YTPackage dt : ytps) {
            String pkgName = dt.getContents().iterator().next().getPackageName();
            pkgName = ContentUtility.refineContentAsTextHTML(pkgName);
            dt.getContents().iterator().next().setPackageName(pkgName);
        }
        InitData initData = new InitData();
        initData.setYtps(ytps);
        initData.setCurrentDate(YTDateTimeUtility.getCurrentDateInMilli());
        ObjectMapper mapper = new ObjectMapper();
        String jsonStr = mapper.writeValueAsString(initData);
        jsonStr = ContentUtility.refineContentAsJsonInJavaSript(jsonStr);
        //
        request.setAttribute("initData", jsonStr);
    }

    @Override
    @Transactional
    public String loadRegularBookingHistory(HttpSession session, String data) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        // get partnerID      
        YTSessionAccount account = YTSession.getAccountSession(session);
        String partnerID = account.getPartnerID();
        // get packageID id from json
        JsonNode dataObject = mapper.readTree(data);
        Boolean channel = null;
        JsonNode channelNode = dataObject.get("channel");
        if (!channelNode.isNull()) {
            channel = channelNode.asBoolean();
        }
        //packageID
        String packageID = dataObject.get("packageID").asText();
        String text_search = null;
        JsonNode searchNode = dataObject.get("key_search");
        if (!searchNode.isNull()) {
            text_search = searchNode.asText().trim();
            if (text_search.isEmpty()) {
                text_search = null;
            }
        }
        // start date , end date
        Long startDate = null;
        JsonNode nodeS = dataObject.get("startDate").get("milli");
        Long endDate = null;
        JsonNode nodeE = dataObject.get("endDate").get("milli");
        // startdate node
        if (!nodeS.isNull()) {
            startDate = nodeS.asLong();
        }
        // enddate node
        if (!nodeE.isNull()) {
            endDate = nodeE.asLong();
        }
        // set 23:59 59s
        if (endDate != null) {
            DateTime endObject = new DateTime(endDate);
            endObject = endObject.plusDays(1).minusSeconds(1);
            endDate = endObject.getMillis();
        }
        // current and limini
        int page = dataObject.get("currentPage").asInt();
        int limit = dataObject.get("limit").asInt();
        int skipRows = (limit * page) - limit;
        // BOoLEAN
        Boolean reviewed = dataObject.get("reviewed").asBoolean();

        //INIT LIST
        List<AdHistoryRegularBookingOrder> historyBookingOrders;
        // LOAD FROM DB
        historyBookingOrders = partnerBookingHistoryOrderDAO.loadDataForBookingHistoryRegular(partnerID,
                channel, packageID, text_search, startDate, endDate, skipRows, limit, reviewed);

        // LOOP TO SET DATESTRING
        for (AdHistoryRegularBookingOrder order : historyBookingOrders) {
            //BOOKING DATE
            Long tripDate = order.getTripDate();
            String tripDateStr = YTDateTimeUtility.getDateStringUnderDdMMyyyy(tripDate);
            order.setTripDateStr(tripDateStr);

            // DURATION TYPE MINUTES
            String durationType = order.getDurationType();
            if (durationType.equalsIgnoreCase(YTPackageData.DURATION_TYPE_MINUTES)) {
                Integer duration = order.getDuration();
                Integer durationHour = duration / 60;
                Integer durationMinutes = duration % 60;
                order.setDuration(durationHour);
                order.setDurationMinute(durationMinutes);
            }
            // TRIP TIME
            Integer startTime = order.getStartTime();
            if (startTime != null) {
                String startTimeStr = YTDateTimeUtility.convertMinutesToHHmm(startTime);
                order.setStartTimeStr(startTimeStr);
            }
        }
        //return json string
        String jsonString = mapper.writeValueAsString(historyBookingOrders);
        jsonString = ContentUtility.refineContentAsJsonInJavaSript(jsonString);
        return jsonString;
    }

    @Override
    @Transactional
    public String loadOpenBookingHistory(HttpSession session, String data) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        // get partnerID      
        YTSessionAccount account = YTSession.getAccountSession(session);
        String partnerID = account.getPartnerID();
        // get packageID id from json
        JsonNode dataObject = mapper.readTree(data);
        Boolean channel = null;
        JsonNode channelNode = dataObject.get("channel");
        if (!channelNode.isNull()) {
            channel = channelNode.asBoolean();
        }
        //packageID
        String packageID = dataObject.get("packageID").asText();
        String text_search = null;
        JsonNode searchNode = dataObject.get("key_search");
        if (!searchNode.isNull()) {
            text_search = searchNode.asText().trim();
            if (text_search.isEmpty()) {
                text_search = null;
            }
        }
        // start date , end date
        Long startDate = null;
        JsonNode nodeS = dataObject.get("startDate").get("milli");
        Long endDate = null;
        JsonNode nodeE = dataObject.get("endDate").get("milli");
        // startdate node
        if (!nodeS.isNull()) {
            startDate = nodeS.asLong();
        }
        // enddate node
        if (!nodeE.isNull()) {
            endDate = nodeE.asLong();
        }
        // set 23:59 59s
        if (endDate != null) {
            DateTime endObject = new DateTime(endDate);
            endObject = endObject.plusDays(1).minusSeconds(1);
            endDate = endObject.getMillis();
        }
        // current and limini
        int page = dataObject.get("currentPage").asInt();
        int limit = dataObject.get("limit").asInt();
        int skipRows = (limit * page) - limit;
        // BOoLEAN
        Boolean reviewed = dataObject.get("reviewed").asBoolean();
        //INIT LIST
        List<AdHistoryOpenBookingOrder> historyBookingOrders;

        // LOAD FROM DB
        historyBookingOrders = partnerBookingHistoryOrderDAO.loadDataForBookingHistoryOpened(partnerID,
                channel, packageID, text_search, startDate, endDate, skipRows, limit, reviewed);

        // LOOP TO SET DATESTRING
        for (AdHistoryOpenBookingOrder order : historyBookingOrders) {
            //BOOKING EXPIRED DATE
            Long expirationDate = order.getExpirationDate();
            String expirationDateStr = YTDateTimeUtility.getDateStringUnderDdMMyyyy(expirationDate);
            order.setExpirationDateStr(expirationDateStr);
            // FINISHED DATE
            Long otExpiredDate = order.getOtExpiredDate();
            String otExpiredDateStr = YTDateTimeUtility.getDateStringUnderDdMMyyyy(otExpiredDate);
            order.setOtExpiredDateStr(otExpiredDateStr);

            // DURATION TYPE MINUTES
            String durationType = order.getDurationType();
            if (durationType.equalsIgnoreCase(YTPackageData.DURATION_TYPE_MINUTES)) {
                Integer duration = order.getDuration();
                Integer durationHour = duration / 60;
                Integer durationMinutes = duration % 60;
                order.setDuration(durationHour);
                order.setDurationMinute(durationMinutes);
            }
            // TRIP TIME
            Integer startTime = order.getStartTime();
            if (startTime != null) {
                String startTimeStr = YTDateTimeUtility.convertMinutesToHHmm(startTime);
                order.setStartTimeStr(startTimeStr);
            }
        }
        //return json string
        String jsonString = mapper.writeValueAsString(historyBookingOrders);
        jsonString = ContentUtility.refineContentAsJsonInJavaSript(jsonString);
        return jsonString;
    }

}
