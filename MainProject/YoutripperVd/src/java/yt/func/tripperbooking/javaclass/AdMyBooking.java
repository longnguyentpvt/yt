/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.func.tripperbooking.javaclass;

import java.math.BigDecimal;

/**
 *
 * @author Hiep
 */
public class AdMyBooking {

    private String bookingCode;
    private long bookingDateTime;
    private long totalResults;
    private BigDecimal amount;
    private boolean completedPayment;
    private String packageName;
    private String invoiceFile;

    public AdMyBooking() {
    }

    public String getBookingCode() {
        return bookingCode;
    }

    public void setBookingCode(String bookingCode) {
        this.bookingCode = bookingCode;
    }

    public long getBookingDateTime() {
        return bookingDateTime;
    }

    public void setBookingDateTime(long bookingDateTime) {
        this.bookingDateTime = bookingDateTime;
    }

    public long getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(long totalResults) {
        this.totalResults = totalResults;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public boolean isCompletedPayment() {
        return completedPayment;
    }

    public void setCompletedPayment(boolean completedPayment) {
        this.completedPayment = completedPayment;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getInvoiceFile() {
        return invoiceFile;
    }

    public void setInvoiceFile(String invoiceFile) {
        this.invoiceFile = invoiceFile;
    }

}
