/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaclass.common;

import java.math.BigDecimal;

/**
 *
 * @author nickn
 */
public class YTPackageInvoiceOrder {

    private String packageID;
    private String packageName;
    private String optionName;
    private String currenyCode;
    private BigDecimal packagePrice;
    private int qty;
    private BigDecimal total;

    public YTPackageInvoiceOrder() {
    }

    public YTPackageInvoiceOrder(String packageID, String packageName, String optionName, String currenyCode, BigDecimal packagePrice, int qty, BigDecimal total) {
        this.packageID = packageID;
        this.packageName = packageName;
        this.optionName = optionName;
        this.currenyCode = currenyCode;
        this.packagePrice = packagePrice;
        this.qty = qty;
        this.total = total;
    }

    public String getPackageID() {
        return packageID;
    }

    public String getPackageName() {
        return packageName;
    }

    public String getOptionName() {
        return optionName;
    }

    public String getCurrenyCode() {
        return currenyCode;
    }

    public BigDecimal getPackagePrice() {
        return packagePrice;
    }

    public int getQty() {
        return qty;
    }

    public BigDecimal getTotal() {
        return total;
    }

}
