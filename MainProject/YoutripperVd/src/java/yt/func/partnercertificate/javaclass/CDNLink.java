/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.func.partnercertificate.javaclass;

/**
 *
 * @author Hiep
 */
public class CDNLink {

    private String pcLink;
    private String tempLink;

    public CDNLink() {
    }

    public CDNLink(String pcLink, String tempLink) {
        this.pcLink = pcLink;
        this.tempLink = tempLink;
    }

    public String getPcLink() {
        return pcLink;
    }

    public void setPcLink(String pcLink) {
        this.pcLink = pcLink;
    }

    public String getTempLink() {
        return tempLink;
    }

    public void setTempLink(String tempLink) {
        this.tempLink = tempLink;
    }

}
