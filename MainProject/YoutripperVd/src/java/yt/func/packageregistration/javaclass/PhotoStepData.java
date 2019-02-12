/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.func.packageregistration.javaclass;

import java.util.List;
import yt.entity.tbl.TemporaryPackagePicture;

/**
 *
 * @author nickn
 */
public class PhotoStepData {

    private String urlLink;
    private String coverType;
    private String fCover;
    private String sCover;
    private String tCover;
    private String vCover;
    private String portrait;
    private String fThumb;
    private String sThumb;
    private String tThumb;
    private String foThumb;
    private String fiThumb;
    private List<TemporaryPackagePicture> pics;

    public PhotoStepData() {
    }

    public PhotoStepData(String urlLink, String coverType, String fCover, String sCover, String tCover, String vCover, String portrait, String fThumb, String sThumb, String tThumb, String foThumb, String fiThumb, List<TemporaryPackagePicture> pics) {
        this.urlLink = urlLink;
        this.coverType = coverType;
        this.fCover = fCover;
        this.sCover = sCover;
        this.tCover = tCover;
        this.vCover = vCover;
        this.portrait = portrait;
        this.fThumb = fThumb;
        this.sThumb = sThumb;
        this.tThumb = tThumb;
        this.foThumb = foThumb;
        this.fiThumb = fiThumb;
        this.pics = pics;
    }

    public String getUrlLink() {
        return urlLink;
    }

    public void setUrlLink(String urlLink) {
        this.urlLink = urlLink;
    }

    public String getCoverType() {
        return coverType;
    }

    public void setCoverType(String coverType) {
        this.coverType = coverType;
    }

    public String getfCover() {
        return fCover;
    }

    public void setfCover(String fCover) {
        this.fCover = fCover;
    }

    public String getsCover() {
        return sCover;
    }

    public void setsCover(String sCover) {
        this.sCover = sCover;
    }

    public String gettCover() {
        return tCover;
    }

    public void settCover(String tCover) {
        this.tCover = tCover;
    }

    public String getvCover() {
        return vCover;
    }

    public void setvCover(String vCover) {
        this.vCover = vCover;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public String getfThumb() {
        return fThumb;
    }

    public void setfThumb(String fThumb) {
        this.fThumb = fThumb;
    }

    public String getsThumb() {
        return sThumb;
    }

    public void setsThumb(String sThumb) {
        this.sThumb = sThumb;
    }

    public String gettThumb() {
        return tThumb;
    }

    public void settThumb(String tThumb) {
        this.tThumb = tThumb;
    }

    public String getFoThumb() {
        return foThumb;
    }

    public void setFoThumb(String foThumb) {
        this.foThumb = foThumb;
    }

    public String getFiThumb() {
        return fiThumb;
    }

    public void setFiThumb(String fiThumb) {
        this.fiThumb = fiThumb;
    }

    public List<TemporaryPackagePicture> getPics() {
        return pics;
    }

    public void setPics(List<TemporaryPackagePicture> pics) {
        this.pics = pics;
    }

}
