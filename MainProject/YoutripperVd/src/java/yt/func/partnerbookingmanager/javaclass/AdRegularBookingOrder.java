/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.func.partnerbookingmanager.javaclass;

import yt.func.partnertrippdash.javaclass.*;

/**
 *
 * @author Hiep
 */
public class AdRegularBookingOrder {

    private String bookingNo;
    private Long tripDate;
    private String tripDateStr;
    private Integer startTime;
    private String startTimeStr;
    private String durationType;
    private Integer duration;
    private Integer durationMinute;
    private String billingFirstName;
    private String billingLastName;
    private String packageName;
    private String servingType;
    private Integer bookedQty;
    private Boolean offlineBooking;
    private Long totalResults;
    private Boolean tripperAbsence;

    public AdRegularBookingOrder() {
    }

    public String getBookingNo() {
        return bookingNo;
    }

    public void setBookingNo(String bookingNo) {
        this.bookingNo = bookingNo;
    }

    public Long getTripDate() {
        return tripDate;
    }

    public void setTripDate(Long tripDate) {
        this.tripDate = tripDate;
    }

    public String getTripDateStr() {
        return tripDateStr;
    }

    public void setTripDateStr(String tripDateStr) {
        this.tripDateStr = tripDateStr;
    }

    public Integer getStartTime() {
        return startTime;
    }

    public void setStartTime(Integer startTime) {
        this.startTime = startTime;
    }

    public String getStartTimeStr() {
        return startTimeStr;
    }

    public void setStartTimeStr(String startTimeStr) {
        this.startTimeStr = startTimeStr;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getBillingFirstName() {
        return billingFirstName;
    }

    public void setBillingFirstName(String billingFirstName) {
        this.billingFirstName = billingFirstName;
    }

    public String getBillingLastName() {
        return billingLastName;
    }

    public void setBillingLastName(String billingLastName) {
        this.billingLastName = billingLastName;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public Integer getBookedQty() {
        return bookedQty;
    }

    public void setBookedQty(Integer bookedQty) {
        this.bookedQty = bookedQty;
    }

    public Boolean getOfflineBooking() {
        return offlineBooking;
    }

    public void setOfflineBooking(Boolean offlineBooking) {
        this.offlineBooking = offlineBooking;
    }

    public Long getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Long totalResults) {
        this.totalResults = totalResults;
    }

    public String getDurationType() {
        return durationType;
    }

    public void setDurationType(String durationType) {
        this.durationType = durationType;
    }

    public Integer getDurationMinute() {
        return durationMinute;
    }

    public void setDurationMinute(Integer durationMinute) {
        this.durationMinute = durationMinute;
    }

    public String getServingType() {
        return servingType;
    }

    public void setServingType(String servingType) {
        this.servingType = servingType;
    }

    public Boolean getTripperAbsence() {
        return tripperAbsence;
    }

    public void setTripperAbsence(Boolean tripperAbsence) {
        this.tripperAbsence = tripperAbsence;
    }

}
