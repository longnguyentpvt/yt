/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaclass.utility;

import java.io.IOException;
import java.util.List;
import javaclass.common.ytpackage.YTPackageData;
import javaclass.common.ytpackage.YTPackageTimeSlot;

/**
 *
 * @author KyLong
 */
public class YTPackageUtility {

    public static int getTotalQuotaInThisMonth(String servingType, List<YTPackageTimeSlot> packageTimeSlots, String dateSchedule,
            List<Long> dateSlot, String quotaType, Integer slotQuota) throws IOException {
        int totalQuota = 0;
        // days of current month
        int nodays = YTDateTimeUtility.getNoDaysOfThisMonth();

        if (servingType.equalsIgnoreCase(YTPackageData.SERVING_TYPE_OPEN_TIMED)) {
            // OPEN-TIMED PACKAGE
            if (quotaType.equalsIgnoreCase(YTPackageData.QUOTA_TYPE_DAY)) {// DAY TYPE
                totalQuota = slotQuota * nodays;
            } else if (quotaType.equalsIgnoreCase(YTPackageData.QUOTA_TYPE_MONTH)) { // MONTH TYPE
                totalQuota = slotQuota;
            }
        } else {
            // REGULART PACKAGE
            int quotaDay = 0;
            if (quotaType.equalsIgnoreCase(YTPackageData.QUOTA_TYPE_TIME_SLOT)) { // TIME SLOT
                for (YTPackageTimeSlot x : packageTimeSlots) {
                    int quota = x.getQuota();
                    quotaDay += quota;
                }
            } else if (quotaType.equalsIgnoreCase(YTPackageData.QUOTA_TYPE_DAY)) {
                quotaDay = slotQuota;
            }

            //DATE SCHEDULE
            int numerofslotDays = 0;
            if (dateSchedule.equalsIgnoreCase(YTPackageData.DATE_SCHEDULE_AUTO_TYPE)) { //auto
                numerofslotDays = nodays;
            } else if (dateSchedule.equalsIgnoreCase(YTPackageData.DATE_SCHEDULE_FLEXIBE_TYPE)) { // flexible
                //GET BEGIN AND END OF CURRENT MONTH
                long beginOfThisMonth = YTDateTimeUtility.getBeginOfCurrentMonth();
                // last date of current month
                long endOfThisMonth = YTDateTimeUtility.getEndOfCurrentMonth();
                for (long date : dateSlot) {
                    if (date >= beginOfThisMonth && date <= endOfThisMonth) {// check if this date belongs to current month
                        numerofslotDays += 1;
                    }
                }
            }
            totalQuota = quotaDay * numerofslotDays;
        }
        //
        return totalQuota;
    }
}
