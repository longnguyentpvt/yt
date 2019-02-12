/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.func.checkout.javaclass;

import java.math.BigDecimal;

/**
 *
 * @author nickn
 */
public class DiscountCodeResponse {

    public static final String WRONG_CODE_ERROR = "wc";
    public static final String EXPIRED_CODE_ERROR = "ep";

    private boolean valid;
    private String error;
    private BigDecimal totalDC;

    public DiscountCodeResponse() {
    }

    public DiscountCodeResponse(boolean valid, String error, BigDecimal totalDC) {
        this.valid = valid;
        this.error = error;
        this.totalDC = totalDC;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public BigDecimal getTotalDC() {
        return totalDC;
    }

    public void setTotalDC(BigDecimal totalDC) {
        this.totalDC = totalDC;
    }

}
