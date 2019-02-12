/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaclass.common;

import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author nickn
 */
public class YTPackageInvoiceInfo {

    private String partnerLogo;
    private String partnerCompanyName;
    private String partnerAddress;
    private String partnerState;
    private String partnerCountry;
    private String partnerPostalCode;
    private String partnerPhoneNo;
    private String partnerPhoneCode;
    private String partnerTaxID;
    private String tripperID;
    private String tripperBillingName;
    private String tripperBillingCompany;
    private String tripperBillingAddress;
    private String tripperBillingTax;
    private String invoiceNo;
    private long invoiceDate;
    private String bookingNo;
    private String currencyCode;
    private BigDecimal total;
    private BigDecimal dcPrice;
    private String dcCode;
    private BigDecimal finalTotal;
    private BigDecimal exRate;
    private BigDecimal amountInTax;
    private BigDecimal vat;
    private BigDecimal amountExTax;
    private String paymentMethod;
    private String cartNo;
    private String paypalID;
    private String linepayID;
    private List<YTPackageInvoiceOrder> orders;

    public YTPackageInvoiceInfo() {
    }

    public YTPackageInvoiceInfo(String partnerLogo, String partnerCompanyName, String partnerAddress, String partnerState, String partnerCountry,
            String partnerPostalCode, String partnerPhoneNo, String partnerPhoneCode, String partnerTaxID, String tripperID,
            String tripperBillingName, String tripperBillingCompany, String tripperBillingAddress, String tripperBillingTax,
            String invoiceNo, long invoiceDate, String bookingNo, String currencyCode, BigDecimal total, BigDecimal dcPrice,
            String dcCode, BigDecimal finalTotal, BigDecimal exRate, BigDecimal amountInTax, BigDecimal vat,
            BigDecimal amountExTax, String paymentMethod, String cartNo, String paypalID, String linepayID, List<YTPackageInvoiceOrder> orders) {
        this.partnerLogo = partnerLogo;
        this.partnerCompanyName = partnerCompanyName;
        this.partnerAddress = partnerAddress;
        this.partnerState = partnerState;
        this.partnerCountry = partnerCountry;
        this.partnerPostalCode = partnerPostalCode;
        this.partnerPhoneNo = partnerPhoneNo;
        this.partnerPhoneCode = partnerPhoneCode;
        this.partnerTaxID = partnerTaxID;
        this.tripperID = tripperID;
        this.tripperBillingName = tripperBillingName;
        this.tripperBillingCompany = tripperBillingCompany;
        this.tripperBillingAddress = tripperBillingAddress;
        this.tripperBillingTax = tripperBillingTax;
        this.invoiceNo = invoiceNo;
        this.invoiceDate = invoiceDate;
        this.bookingNo = bookingNo;
        this.currencyCode = currencyCode;
        this.total = total;
        this.dcPrice = dcPrice;
        this.dcCode = dcCode;
        this.finalTotal = finalTotal;
        this.exRate = exRate;
        this.amountInTax = amountInTax;
        this.vat = vat;
        this.amountExTax = amountExTax;
        this.paymentMethod = paymentMethod;
        this.cartNo = cartNo;
        this.paypalID = paypalID;
        this.linepayID = linepayID;
        this.orders = orders;
    }

    public String getPartnerLogo() {
        return partnerLogo;
    }

    public void setPartnerLogo(String partnerLogo) {
        this.partnerLogo = partnerLogo;
    }

    public String getPartnerCompanyName() {
        return partnerCompanyName;
    }

    public void setPartnerCompanyName(String partnerCompanyName) {
        this.partnerCompanyName = partnerCompanyName;
    }

    public String getPartnerAddress() {
        return partnerAddress;
    }

    public void setPartnerAddress(String partnerAddress) {
        this.partnerAddress = partnerAddress;
    }

    public String getPartnerState() {
        return partnerState;
    }

    public void setPartnerState(String partnerState) {
        this.partnerState = partnerState;
    }

    public String getPartnerCountry() {
        return partnerCountry;
    }

    public void setPartnerCountry(String partnerCountry) {
        this.partnerCountry = partnerCountry;
    }

    public String getPartnerPostalCode() {
        return partnerPostalCode;
    }

    public void setPartnerPostalCode(String partnerPostalCode) {
        this.partnerPostalCode = partnerPostalCode;
    }

    public String getPartnerPhoneNo() {
        return partnerPhoneNo;
    }

    public void setPartnerPhoneNo(String partnerPhoneNo) {
        this.partnerPhoneNo = partnerPhoneNo;
    }

    public String getPartnerPhoneCode() {
        return partnerPhoneCode;
    }

    public void setPartnerPhoneCode(String partnerPhoneCode) {
        this.partnerPhoneCode = partnerPhoneCode;
    }

    public String getPartnerTaxID() {
        return partnerTaxID;
    }

    public void setPartnerTaxID(String partnerTaxID) {
        this.partnerTaxID = partnerTaxID;
    }

    public String getTripperID() {
        return tripperID;
    }

    public void setTripperID(String tripperID) {
        this.tripperID = tripperID;
    }

    public String getTripperBillingName() {
        return tripperBillingName;
    }

    public void setTripperBillingName(String tripperBillingName) {
        this.tripperBillingName = tripperBillingName;
    }

    public String getTripperBillingCompany() {
        return tripperBillingCompany;
    }

    public void setTripperBillingCompany(String tripperBillingCompany) {
        this.tripperBillingCompany = tripperBillingCompany;
    }

    public String getTripperBillingAddress() {
        return tripperBillingAddress;
    }

    public void setTripperBillingAddress(String tripperBillingAddress) {
        this.tripperBillingAddress = tripperBillingAddress;
    }

    public String getTripperBillingTax() {
        return tripperBillingTax;
    }

    public void setTripperBillingTax(String tripperBillingTax) {
        this.tripperBillingTax = tripperBillingTax;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public long getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(long invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getBookingNo() {
        return bookingNo;
    }

    public void setBookingNo(String bookingNo) {
        this.bookingNo = bookingNo;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getDcPrice() {
        return dcPrice;
    }

    public void setDcPrice(BigDecimal dcPrice) {
        this.dcPrice = dcPrice;
    }

    public String getDcCode() {
        return dcCode;
    }

    public void setDcCode(String dcCode) {
        this.dcCode = dcCode;
    }

    public BigDecimal getFinalTotal() {
        return finalTotal;
    }

    public void setFinalTotal(BigDecimal finalTotal) {
        this.finalTotal = finalTotal;
    }

    public BigDecimal getExRate() {
        return exRate;
    }

    public void setExRate(BigDecimal exRate) {
        this.exRate = exRate;
    }

    public BigDecimal getAmountInTax() {
        return amountInTax;
    }

    public void setAmountInTax(BigDecimal amountInTax) {
        this.amountInTax = amountInTax;
    }

    public BigDecimal getVat() {
        return vat;
    }

    public void setVat(BigDecimal vat) {
        this.vat = vat;
    }

    public BigDecimal getAmountExTax() {
        return amountExTax;
    }

    public void setAmountExTax(BigDecimal amountExTax) {
        this.amountExTax = amountExTax;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getCartNo() {
        return cartNo;
    }

    public void setCartNo(String cartNo) {
        this.cartNo = cartNo;
    }

    public String getPaypalID() {
        return paypalID;
    }

    public void setPaypalID(String paypalID) {
        this.paypalID = paypalID;
    }

    public String getLinepayID() {
        return linepayID;
    }

    public void setLinepayID(String linepayID) {
        this.linepayID = linepayID;
    }

    public List<YTPackageInvoiceOrder> getOrders() {
        return orders;
    }

    public void setOrders(List<YTPackageInvoiceOrder> orders) {
        this.orders = orders;
    }

}
