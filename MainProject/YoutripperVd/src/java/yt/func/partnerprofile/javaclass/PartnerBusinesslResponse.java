/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.func.partnerprofile.javaclass;

import java.util.List;
import yt.entity.tbl.PartnerState;

/**
 *
 * @author nickn
 */
public class PartnerBusinesslResponse {

    private String businessName;
    private String businessType;
    private String countryID;
    private Long stateID;
    private String businessAddress;
    private String businessCity;
    private String postalCode;
    private String phoneCode;
    private String phoneNumber;
    private String businessBackGround;
    private String countryName;
    private String cityName;
    private List<PartnerState> cities;

    public PartnerBusinesslResponse() {
    }

    public PartnerBusinesslResponse(String businessName, String businessType, String countryID, Long stateID, String businessAddress, String businessCity, String postalCode, String phoneCode, String phoneNumber, String businessBackGround, String countryName, String cityName, List<PartnerState> cities) {
        this.businessName = businessName;
        this.businessType = businessType;
        this.countryID = countryID;
        this.stateID = stateID;
        this.businessAddress = businessAddress;
        this.businessCity = businessCity;
        this.postalCode = postalCode;
        this.phoneCode = phoneCode;
        this.phoneNumber = phoneNumber;
        this.businessBackGround = businessBackGround;
        this.countryName = countryName;
        this.cityName = cityName;
        this.cities = cities;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public List<PartnerState> getCities() {
        return cities;
    }

    public void setCities(List<PartnerState> cities) {
        this.cities = cities;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getCountryID() {
        return countryID;
    }

    public void setCountryID(String countryID) {
        this.countryID = countryID;
    }

    public Long getStateID() {
        return stateID;
    }

    public void setStateID(Long stateID) {
        this.stateID = stateID;
    }

    public String getBusinessAddress() {
        return businessAddress;
    }

    public void setBusinessAddress(String businessAddress) {
        this.businessAddress = businessAddress;
    }

    public String getBusinessCity() {
        return businessCity;
    }

    public void setBusinessCity(String businessCity) {
        this.businessCity = businessCity;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPhoneCode() {
        return phoneCode;
    }

    public void setPhoneCode(String phoneCode) {
        this.phoneCode = phoneCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getBusinessBackGround() {
        return businessBackGround;
    }

    public void setBusinessBackGround(String businessBackGround) {
        this.businessBackGround = businessBackGround;
    }

}
