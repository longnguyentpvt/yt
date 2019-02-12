/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.func.partnerpayment.javaclass;

import java.math.BigDecimal;

/**
 *
 * @author Hiep
 */
public class PartnerPaymentOrder {

    private long bookingDateTime;
    private String bookingDateStr;
    private String bookingCode;
    private String currencyCode;
    private String packageID;
    private String packageName;
    private BigDecimal totalExDc;
    private BigDecimal discount;
    private BigDecimal payback;
    private BigDecimal total;
    private BigDecimal exRate;
    private BigDecimal commission;
    private BigDecimal partnerTotal;
    private boolean pkgDone;
    private String doneDate;
    private int pendingDays;
    private String status;

    public PartnerPaymentOrder() {
    }

    public PartnerPaymentOrder(long bookingDateTime, String bookingDateStr, String bookingCode, String currencyCode, String packageID, String packageName, BigDecimal totalExDc, BigDecimal discount, BigDecimal payback, BigDecimal total, BigDecimal exRate, BigDecimal commission, BigDecimal partnerTotal, boolean pkgDone, String doneDate, int pendingDays, String status) {
        this.bookingDateTime = bookingDateTime;
        this.bookingDateStr = bookingDateStr;
        this.bookingCode = bookingCode;
        this.currencyCode = currencyCode;
        this.packageID = packageID;
        this.packageName = packageName;
        this.totalExDc = totalExDc;
        this.discount = discount;
        this.payback = payback;
        this.total = total;
        this.exRate = exRate;
        this.commission = commission;
        this.partnerTotal = partnerTotal;
        this.pkgDone = pkgDone;
        this.doneDate = doneDate;
        this.pendingDays = pendingDays;
        this.status = status;
    }

    public long getBookingDateTime() {
        return bookingDateTime;
    }

    public void setBookingDateTime(long bookingDateTime) {
        this.bookingDateTime = bookingDateTime;
    }

    public String getBookingDateStr() {
        return bookingDateStr;
    }

    public void setBookingDateStr(String bookingDateStr) {
        this.bookingDateStr = bookingDateStr;
    }

    public String getBookingCode() {
        return bookingCode;
    }

    public void setBookingCode(String bookingCode) {
        this.bookingCode = bookingCode;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
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

    public BigDecimal getTotalExDc() {
        return totalExDc;
    }

    public void setTotalExDc(BigDecimal totalExDc) {
        this.totalExDc = totalExDc;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public BigDecimal getPayback() {
        return payback;
    }

    public void setPayback(BigDecimal payback) {
        this.payback = payback;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getExRate() {
        return exRate;
    }

    public void setExRate(BigDecimal exRate) {
        this.exRate = exRate;
    }

    public BigDecimal getCommission() {
        return commission;
    }

    public void setCommission(BigDecimal commission) {
        this.commission = commission;
    }

    public BigDecimal getPartnerTotal() {
        return partnerTotal;
    }

    public void setPartnerTotal(BigDecimal partnerTotal) {
        this.partnerTotal = partnerTotal;
    }

    public boolean isPkgDone() {
        return pkgDone;
    }

    public void setPkgDone(boolean pkgDone) {
        this.pkgDone = pkgDone;
    }

    public String getDoneDate() {
        return doneDate;
    }

    public void setDoneDate(String doneDate) {
        this.doneDate = doneDate;
    }

    public int getPendingDays() {
        return pendingDays;
    }

    public void setPendingDays(int pendingDays) {
        this.pendingDays = pendingDays;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
