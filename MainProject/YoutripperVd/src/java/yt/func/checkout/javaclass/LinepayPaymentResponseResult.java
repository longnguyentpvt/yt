/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.func.checkout.javaclass;

/**
 *
 * @author nickn
 */
public class LinepayPaymentResponseResult {

    private String responseJson;
    private boolean success;

    public LinepayPaymentResponseResult() {
    }

    public LinepayPaymentResponseResult(String responseJson, boolean success) {
        this.responseJson = responseJson;
        this.success = success;
    }

    public String getResponseJson() {
        return responseJson;
    }

    public void setResponseJson(String responseJson) {
        this.responseJson = responseJson;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

}
