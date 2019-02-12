/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.func.partnertrippdash.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javaclass.common.YTSession;
import javaclass.common.YTSessionAccount;
import javaclass.common.ytpackage.YTPackageData;
import javaclass.common.ytpackage.YTPackageTimeSlot;
import javaclass.utility.ContentUtility;
import javaclass.utility.YTDateTimeUtility;
import javaclass.utility.YTPackageUtility;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yt.entity.tbl.YTBookingOrder;
import yt.entity.tbl.YTPackage;
import yt.func.partnertrippdash.dao.YTBookingOrderDAO;
import yt.func.partnertrippdash.dao.YTPackageDAO;
import yt.func.partnertrippdash.javaclass.PackageOverview;
import yt.func.partnertrippdash.javaclass.PackageSelling;
import yt.func.partnertrippdash.javaclass.PackageSellingReturnData;
import yt.func.partnertrippdash.javaclass.PackageTotalReturnData;
import yt.func.partnertrippdash.javaclass.PackageWithTotal;
import yt.func.partnertrippdash.javaclass.UpcomingBooking;
import yt.func.partnertrippdash.javaclass.UpcomingReturnData;

/**
 *
 * @author Hiep
 */
@Service
public class PartnerTrippDashServiceImpl implements PartnerTrippDashService {

    @Autowired
    private YTPackageDAO partnerTrippDashPackageDAO;

    @Autowired
    private YTBookingOrderDAO partnerTrippDashBookingOrderDAO;

    @Override
    @Transactional
    public String loadPackageOverview(HttpSession session) throws JsonProcessingException {
        YTSessionAccount account = YTSession.getAccountSession(session);
        String partnerID = account.getPartnerID();
        List<YTPackage> ytPackages = partnerTrippDashPackageDAO.getNumberPackage(partnerID);
        int noRe = 0;
        int noOpen = 0;
        int total = 0;
        // loop to count total , regular , opened packages
        for (YTPackage ytp : ytPackages) {
            String servingType = ytp.getServingType();
            String status = ytp.getPackageStatus();
            if (!status.equalsIgnoreCase(YTPackageData.YT_PACKAGE_STATUS_DELETED)) {
                total += 1;
                if (servingType != null) { // SERVING TPE NOT NULL
                    if (servingType.equalsIgnoreCase(YTPackageData.SERVING_TYPE_OPEN_TIMED)) {
                        noOpen += 1;
                    } else {
                        noRe += 1;
                    }
                }
            }
        }
        //set to class overview
        PackageOverview packageOverview = new PackageOverview();
        packageOverview.setOpened(noOpen);
        packageOverview.setRegular(noRe);
        packageOverview.setTotal(total);
        ObjectMapper mapper = new ObjectMapper();
        //return json string
        return mapper.writeValueAsString(packageOverview);
    }

    @Override
    @Transactional
    public String loadHigestGrossing(HttpSession session) throws JsonProcessingException {
        YTSessionAccount account = YTSession.getAccountSession(session);
        String partnerID = account.getPartnerID();

        // GET BEGIN AND END OF CURRENT MONTH
        long beginOfThisMonth = YTDateTimeUtility.getBeginOfCurrentMonth();
        long endOfThisMonth = YTDateTimeUtility.getCurrentTimeInMilli();
        List<PackageWithTotal> packageWithHighGrossings = partnerTrippDashBookingOrderDAO.getPackagesWithTotalAmount(partnerID,
                beginOfThisMonth, endOfThisMonth);
        // get top 3 highest package within current MONTH
        List<PackageWithTotal> top3 = new ArrayList<>();
        int size = packageWithHighGrossings.size();
        if (size > 0) {
            Collections.sort(packageWithHighGrossings, PackageWithTotal.TotalCompare);
        }
        for (int i = 0; i < 3; i++) {
            PackageWithTotal aP;
            if (i < size) {
                aP = packageWithHighGrossings.get(i);
            } else {
                aP = new PackageWithTotal();
            }
            top3.add(aP);
        }
        ObjectMapper mapper = new ObjectMapper();
        String currentDateMMYY = YTDateTimeUtility.getCurrentTimeUnderMMyyyy();
        PackageTotalReturnData returnData = new PackageTotalReturnData(top3, currentDateMMYY);
        //return json string
        String jString = mapper.writeValueAsString(returnData);
        jString = ContentUtility.refineContentAsJsonInJavaSript(jString);
        return jString;
    }

    @Override
    @Transactional
    public String loadTotalSales(HttpSession session) throws JsonProcessingException {
        YTSessionAccount account = YTSession.getAccountSession(session);
        String partnerID = account.getPartnerID();
        List<PackageWithTotal> packageWithHighGrossings = partnerTrippDashBookingOrderDAO.getPackageTotalSale(partnerID);
        // get top 3 highest package
        List<PackageWithTotal> top3 = new ArrayList<>();
        int size = packageWithHighGrossings.size();

        if (size > 0) {
            Collections.sort(packageWithHighGrossings, PackageWithTotal.TotalCompare);
        }
        for (int i = 0; i < 3; i++) {
            PackageWithTotal aP;
            if (i < size) {
                aP = packageWithHighGrossings.get(i);
            } else {
                aP = new PackageWithTotal();
            }
            top3.add(aP);
        }

        ObjectMapper mapper = new ObjectMapper();
        //return json string
        String jString = mapper.writeValueAsString(top3);
        jString = ContentUtility.refineContentAsJsonInJavaSript(jString);
        return jString;
    }

    @Override
    @Transactional
    public String loadUpcomingToday(HttpSession session, String data) throws JsonProcessingException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode dataObject = mapper.readTree(data);
        //get page
        int page = dataObject.get("page").asInt();
        // get limit
        int limit = dataObject.get("limit").asInt();
        YTSessionAccount account = YTSession.getAccountSession(session);
        String partnerID = account.getPartnerID();
        //upcoming data

        List<UpcomingBooking> bookingOrders = partnerTrippDashBookingOrderDAO.getUpcomingToday(partnerID, page, limit);
        //
        for (UpcomingBooking order : bookingOrders) {
            //BOOKING DATE
            Long bookingDate = order.getBookingDate();
            String bookingDateStr = YTDateTimeUtility.getDateStringUnderDdMMyyyy(bookingDate);
            order.setBookingDateStr(bookingDateStr);
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
        String currentDateDDMMYYY = YTDateTimeUtility.getCurrentTimeUnderDdMMyyyy();
        UpcomingReturnData returnData = new UpcomingReturnData(bookingOrders, currentDateDDMMYYY);
        String jString = mapper.writeValueAsString(returnData);
        jString = ContentUtility.refineContentAsJsonInJavaSript(jString);
        return jString;

    }

    @Override
    @Transactional
    public String loadBestSelling(HttpSession session) throws JsonProcessingException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        YTSessionAccount account = YTSession.getAccountSession(session);
        String partnerID = account.getPartnerID();
        long beginOfCurrenMontth = YTDateTimeUtility.getBeginOfCurrentMonth();
        long endOfCurrentMonth = YTDateTimeUtility.getEndOfCurrentMonth();
        // get used quota in current month
        List<YTBookingOrder> bookingOrders = partnerTrippDashBookingOrderDAO.getUsedQuotaWithInCurrentMonth(partnerID,
                beginOfCurrenMontth, endOfCurrentMonth);
        //map total used quota
        Map<String, YTBookingOrder> totalUsedQuotaPackageHM = new HashMap<>();
        // loot to calculate used quota
        for (YTBookingOrder bookingOrder : bookingOrders) {
            String packageID = bookingOrder.getPackageID();
            int usedQuota = bookingOrder.getUsedQuota();
            if (totalUsedQuotaPackageHM.containsKey(packageID)) {
                YTBookingOrder nbookingOrder = totalUsedQuotaPackageHM.get(packageID);
                int oldUsedQuota = nbookingOrder.getUsedQuota();
                nbookingOrder.setUsedQuota(oldUsedQuota + usedQuota);
                totalUsedQuotaPackageHM.replace(packageID, nbookingOrder);
            } else {
                totalUsedQuotaPackageHM.put(packageID, bookingOrder);
            }
        }
        
        Map<String, Integer> totalQuotaWithinMonthHM = new HashMap<>();
        //get total quota in a month
        List<YTPackage> ytps = partnerTrippDashBookingOrderDAO.getPackageSellingData(partnerID);
        for (YTPackage pkg : ytps) {
            String packageID = pkg.getPackageID();
            String servingType = pkg.getServingType();
            String quotaType = pkg.getQuotaType();
            Integer slotQuota = pkg.getSlotQuota();
            String timeSlotJson = pkg.getTimeSlots();
            String dateSchedule = pkg.getDateSchedule();
            String dateSlots = pkg.getDateSlots();
            //convert to List<YTPackageTimeSlot>
            List<YTPackageTimeSlot> packageTimeSlots = new ArrayList<>();
            if (timeSlotJson != null) {
                packageTimeSlots = mapper.readValue(timeSlotJson, new TypeReference<List<YTPackageTimeSlot>>() {
                });
            }
            //convert to list<Long>
            List<Long> dateSlot = new ArrayList<>();
            if (dateSlots != null) {
                dateSlot = mapper.readValue(dateSlots, new TypeReference<List<Long>>() {
                });
            }
            //get total quota
            int totalQuota = YTPackageUtility.getTotalQuotaInThisMonth(servingType,
                    packageTimeSlots, dateSchedule, dateSlot, quotaType, slotQuota);

            // total within a month HM
            if (!totalQuotaWithinMonthHM.containsKey(packageID)) {
                totalQuotaWithinMonthHM.put(packageID, totalQuota);
            }
        }
        // SORT
        List<PackageSelling> packageSellings = new ArrayList<>();
        for (Entry<String, YTBookingOrder> entry : totalUsedQuotaPackageHM.entrySet()) {
            String packageID = entry.getKey();
            YTBookingOrder order = entry.getValue();
            int totalQuota = totalQuotaWithinMonthHM.get(packageID);
            int usedQuota = order.getUsedQuota();
            double percent = (double) usedQuota / (double) totalQuota;
            PackageSelling aS = new PackageSelling();
            aS.setPackageID(packageID);
            aS.setPackageName(order.getPackageName());
            aS.setUsedQuota(usedQuota);
            aS.setPercent(percent);
            aS.setTotalQuota(totalQuota);
            packageSellings.add(aS);
        }
        int size = packageSellings.size();
        List<PackageSelling> top2 = new ArrayList<>();
        if (size > 0) {
            Collections.sort(packageSellings, PackageSelling.PercentCompare);
        }
        
        for (int i = 0; i < 2; i++) {
            PackageSelling aP;
            if (i < size) {
                aP = packageSellings.get(i);
            } else {
                aP = new PackageSelling();
            }
            top2.add(aP);
        }
        
        // return data
        String currentDateMMYY = YTDateTimeUtility.getCurrentTimeUnderMMyyyy();
        PackageSellingReturnData sellingReturnData = new PackageSellingReturnData(top2, currentDateMMYY);
        
        String jString = mapper.writeValueAsString(sellingReturnData);
        jString = ContentUtility.refineContentAsJsonInJavaSript(jString);
        return jString;
    }
}
