/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.func.partnerpayment.javaclass;

import java.util.List;

/**
 *
 * @author Hiep
 */
public class PaymentFilterResult {

    private long totalResults;
    private List<PartnerPaymentOrder> orders;

    public PaymentFilterResult() {
    }

    public PaymentFilterResult(long totalResults, List<PartnerPaymentOrder> orders) {
        this.totalResults = totalResults;
        this.orders = orders;
    }

    public long getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(long totalResults) {
        this.totalResults = totalResults;
    }

    public List<PartnerPaymentOrder> getOrders() {
        return orders;
    }

    public void setOrders(List<PartnerPaymentOrder> orders) {
        this.orders = orders;
    }

}
