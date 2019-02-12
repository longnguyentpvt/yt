/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.func.signup.javaclass;

import java.util.List;
import yt.entity.tbl.PartnerCountry;

/**
 *
 * @author nickn
 */
public class PartnerRegistrationForm {

    private String businessTypePersonal;
    private String businessTypeCompany;
    private String positionBusinessOwner;
    private String positionSaleManager;
    private String positionMarketingManager;
    private String positionGeneralManager;
    private String positionGeneralOfficer;
    private String positionOthers;
    private String[] phoneCodes;
    private List<PartnerCountry> partnerCountries;

    public PartnerRegistrationForm() {
    }

    public PartnerRegistrationForm(String businessTypePersonal, String businessTypeCompany, String positionBusinessOwner,
            String positionSaleManager, String positionMarketingManager, String positionGeneralManager,
            String positionGeneralOfficer, String positionOthers, String[] phoneCodes, List<PartnerCountry> partnerCountries) {
        this.businessTypePersonal = businessTypePersonal;
        this.businessTypeCompany = businessTypeCompany;
        this.positionBusinessOwner = positionBusinessOwner;
        this.positionSaleManager = positionSaleManager;
        this.positionMarketingManager = positionMarketingManager;
        this.positionGeneralManager = positionGeneralManager;
        this.positionGeneralOfficer = positionGeneralOfficer;
        this.positionOthers = positionOthers;
        this.phoneCodes = phoneCodes;
        this.partnerCountries = partnerCountries;
    }

    public String getBusinessTypePersonal() {
        return businessTypePersonal;
    }

    public String getBusinessTypeCompany() {
        return businessTypeCompany;
    }

    public String getPositionBusinessOwner() {
        return positionBusinessOwner;
    }

    public String getPositionSaleManager() {
        return positionSaleManager;
    }

    public String getPositionMarketingManager() {
        return positionMarketingManager;
    }

    public String getPositionGeneralManager() {
        return positionGeneralManager;
    }

    public String getPositionGeneralOfficer() {
        return positionGeneralOfficer;
    }

    public String[] getPhoneCodes() {
        return phoneCodes;
    }

    public void setPhoneCodes(String[] phoneCodes) {
        this.phoneCodes = phoneCodes;
    }

    public String getPositionOthers() {
        return positionOthers;
    }

    public List<PartnerCountry> getPartnerCountries() {
        return partnerCountries;
    }

}
