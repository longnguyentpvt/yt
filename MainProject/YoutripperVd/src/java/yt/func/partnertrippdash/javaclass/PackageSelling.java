/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.func.partnertrippdash.javaclass;

import java.util.Comparator;

/**
 *
 * @author Hiep
 */
public class PackageSelling {

    private String packageID;

    private String packageName;

    private String servingType;

    private String partnerID;

    private Integer usedQuota;

    private Integer totalQuota;

    private String dateSlots;

    private String quotaType;

    private Integer slotQuota;

    private String dateSchedule;

    private Integer startTime;

    private Integer endTime;

    private Integer slotInterval;

    private String timeSlots;

    private double percent;

    public PackageSelling() {

    }

    public String getPackageID() {
        return packageID;
    }

    public void setPackageID(String packageID) {
        this.packageID = packageID;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getPartnerID() {
        return partnerID;
    }

    public void setPartnerID(String partnerID) {
        this.partnerID = partnerID;
    }

    public Integer getUsedQuota() {
        return usedQuota;
    }

    public void setUsedQuota(Integer usedQuota) {
        this.usedQuota = usedQuota;
    }

    public Integer getTotalQuota() {
        return totalQuota;
    }

    public void setTotalQuota(Integer totalQuota) {
        this.totalQuota = totalQuota;
    }

    public String getDateSlots() {
        return dateSlots;
    }

    public void setDateSlots(String dateSlots) {
        this.dateSlots = dateSlots;
    }

    public String getQuotaType() {
        return quotaType;
    }

    public void setQuotaType(String quotaType) {
        this.quotaType = quotaType;
    }

    public Integer getSlotQuota() {
        return slotQuota;
    }

    public void setSlotQuota(Integer slotQuota) {
        this.slotQuota = slotQuota;
    }

    public String getDateSchedule() {
        return dateSchedule;
    }

    public void setDateSchedule(String dateSchedule) {
        this.dateSchedule = dateSchedule;
    }

    public Integer getStartTime() {
        return startTime;
    }

    public void setStartTime(Integer startTime) {
        this.startTime = startTime;
    }

    public Integer getEndTime() {
        return endTime;
    }

    public void setEndTime(Integer endTime) {
        this.endTime = endTime;
    }

    public Integer getSlotInterval() {
        return slotInterval;
    }

    public void setSlotInterval(Integer slotInterval) {
        this.slotInterval = slotInterval;
    }

    public String getTimeSlots() {
        return timeSlots;
    }

    public void setTimeSlots(String timeSlots) {
        this.timeSlots = timeSlots;
    }

    public String getServingType() {
        return servingType;
    }

    public void setServingType(String servingType) {
        this.servingType = servingType;
    }

    public double getPercent() {
        return percent;
    }

    public void setPercent(double percent) {
        this.percent = percent;
    }

    public static Comparator<PackageSelling> PercentCompare = (PackageSelling booking1, PackageSelling booking2) -> {
        double percent1 = booking1.getPercent();
        double percent2 = booking2.getPercent();
        if (percent1 < percent2) {
            return 1;
        }
        if (percent1 > percent2) {
            return -1;
        }
        return 0;
    };
}
