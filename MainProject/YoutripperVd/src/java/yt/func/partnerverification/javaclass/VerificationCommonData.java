/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.func.partnerverification.javaclass;

/**
 *
 * @author Hiep
 */
public class VerificationCommonData {

    private String verificationID;
    private String bankID;
    private String bankAccountName;
    private String bankAccountNumber;
    private String bankAccountStatus;
    private String verificationStatus;
    private String cdnTempLink;
    private String cdnPVLink;
    private String cdnPBLink;
    private boolean isPSNSubmited;
    private boolean isBankSubmited;

    public VerificationCommonData() {
    }

    public String getVerificationID() {
        return verificationID;
    }

    public void setVerificationID(String verificationID) {
        this.verificationID = verificationID;
    }

    public String getBankID() {
        return bankID;
    }

    public void setBankID(String bankID) {
        this.bankID = bankID;
    }

    public String getBankAccountName() {
        return bankAccountName;
    }

    public void setBankAccountName(String bankAccountName) {
        this.bankAccountName = bankAccountName;
    }

    public String getBankAccountNumber() {
        return bankAccountNumber;
    }

    public void setBankAccountNumber(String bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
    }

    public String getBankAccountStatus() {
        return bankAccountStatus;
    }

    public void setBankAccountStatus(String bankAccountStatus) {
        this.bankAccountStatus = bankAccountStatus;
    }

    public String getVerificationStatus() {
        return verificationStatus;
    }

    public void setVerificationStatus(String verificationStatus) {
        this.verificationStatus = verificationStatus;
    }

    public String getCdnTempLink() {
        return cdnTempLink;
    }

    public void setCdnTempLink(String cdnTempLink) {
        this.cdnTempLink = cdnTempLink;
    }

    public String getCdnPVLink() {
        return cdnPVLink;
    }

    public void setCdnPVLink(String cdnPVLink) {
        this.cdnPVLink = cdnPVLink;
    }

    public String getCdnPBLink() {
        return cdnPBLink;
    }

    public void setCdnPBLink(String cdnPBLink) {
        this.cdnPBLink = cdnPBLink;
    }

    public boolean isIsPSNSubmited() {
        return isPSNSubmited;
    }

    public void setIsPSNSubmited(boolean isPSNSubmited) {
        this.isPSNSubmited = isPSNSubmited;
    }

    public boolean isIsBankSubmited() {
        return isBankSubmited;
    }

    public void setIsBankSubmited(boolean isBankSubmited) {
        this.isBankSubmited = isBankSubmited;
    }

}
