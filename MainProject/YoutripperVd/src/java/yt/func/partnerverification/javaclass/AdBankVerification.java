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
public class AdBankVerification {

    private String bankID;
    private String bankAccountName;
    private String bankAccountNumber;
    private boolean isBankSubmited;
    private List<AdBankFile> files;

    public AdBankVerification() {
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

    public boolean isIsBankSubmited() {
        return isBankSubmited;
    }

    public void setIsBankSubmited(boolean isBankSubmited) {
        this.isBankSubmited = isBankSubmited;
    }

    public List<AdBankFile> getFiles() {
        return files;
    }

    public void setFiles(List<AdBankFile> files) {
        this.files = files;
    }

}
