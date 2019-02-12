/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.func.partnerverification.javaclass;

import java.util.List;

/**
 *
 * @author Hiep
 */
public class AdPersonalVerification {

    private String verificationID;
    private boolean isPSNSubmited;
    private List<AdPersonalFile> files;

    public String getVerificationID() {
        return verificationID;
    }

    public void setVerificationID(String verificationID) {
        this.verificationID = verificationID;
    }

    public boolean isIsPSNSubmited() {
        return isPSNSubmited;
    }

    public void setIsPSNSubmited(boolean isPSNSubmited) {
        this.isPSNSubmited = isPSNSubmited;
    }

    public List<AdPersonalFile> getFiles() {
        return files;
    }

    public void setFiles(List<AdPersonalFile> files) {
        this.files = files;
    }

}
