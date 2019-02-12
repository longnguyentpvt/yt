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
public class AdOpenBookingOrder {

    private String bookingNo;
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
    private Long expirationDate;
    private String expirationDateStr;
    private Integer otServingQTY;
    private Integer otServedQTY;

    public AdOpenBookingOrder() {
    }

    public String getBookingNo() {
        return bookingNo;
    }

    public void setBookingNo(String bookingNo) {
        this.bookingNo = bookingNo;
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

    public String getDurationType() {
        return durationType;
    }

    public void setDurationType(String durationType) {
        this.durationType = durationType;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getDurationMinute() {
        return durationMinute;
    }

    public void setDurationMinute(Integer durationMinute) {
        this.durationMinute = durationMinute;
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

    public String getServingType() {
        return servingType;
    }

    public void setServingType(String servingType) {
        this.servingType = servingType;
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

    public Long getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Long expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getExpirationDateStr() {
        return expirationDateStr;
    }

    public void setExpirationDateStr(String expirationDateStr) {
        this.expirationDateStr = expirationDateStr;
    }

    public Integer getOtServingQTY() {
        return otServingQTY;
    }

    public void setOtServingQTY(Integer otServingQTY) {
        this.otServingQTY = otServingQTY;
    }

    public Integer getOtServedQTY() {
        return otServedQTY;
    }

    public void setOtServedQTY(Integer otServedQTY) {
        this.otServedQTY = otServedQTY;
    }

}
