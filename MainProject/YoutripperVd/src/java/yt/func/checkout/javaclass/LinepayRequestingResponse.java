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
public class LinepayRequestingResponse {

    private String redirectLink;
    private String linepayID;

    public LinepayRequestingResponse() {
    }

    public LinepayRequestingResponse(String redirectLink, String linepayID) {
        this.redirectLink = redirectLink;
        this.linepayID = linepayID;
    }

    public String getRedirectLink() {
        return redirectLink;
    }

    public String getLinepayID() {
        return linepayID;
    }

}
