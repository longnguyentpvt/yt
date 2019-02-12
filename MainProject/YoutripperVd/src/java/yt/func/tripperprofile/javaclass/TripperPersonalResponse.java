/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.func.tripperprofile.javaclass;

import java.util.List;
import yt.entity.tbl.State;

/**
 *
 * @author Hiep
 */
public class TripperPersonalResponse {

    private String firstName;
    private String lastName;
    private Boolean isMale;
    private String countryID;
    private String countryName;
    private Long stateID;
    private String stateName;
    private String personalCity;
    private String personalAddress;
    private String postalCode;
    private String phoneCode;
    private String phoneNumber;
    private String company;
    private String taxNumber;
    private List<State> cities;
    private String email;
    private String accountType;
    private String displayName;

    public TripperPersonalResponse() {
    }

    public TripperPersonalResponse(String firstName, String lastName, Boolean isMale, String countryID, String countryName, Long stateID, String stateName, String personalCity, String personalAddress, String postalCode, String phoneCode, String phoneNumber, String company, String taxNumber, List<State> cities, String email, String accountType, String displayName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.isMale = isMale;
        this.countryID = countryID;
        this.countryName = countryName;
        this.stateID = stateID;
        this.stateName = stateName;
        this.personalCity = personalCity;
        this.personalAddress = personalAddress;
        this.postalCode = postalCode;
        this.phoneCode = phoneCode;
        this.phoneNumber = phoneNumber;
        this.company = company;
        this.taxNumber = taxNumber;
        this.cities = cities;
        this.email = email;
        this.accountType = accountType;
        this.displayName = displayName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Boolean getIsMale() {
        return isMale;
    }

    public void setIsMale(Boolean isMale) {
        this.isMale = isMale;
    }

    public String getCountryID() {
        return countryID;
    }

    public void setCountryID(String countryID) {
        this.countryID = countryID;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public Long getStateID() {
        return stateID;
    }

    public void setStateID(Long stateID) {
        this.stateID = stateID;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getPersonalCity() {
        return personalCity;
    }

    public void setPersonalCity(String personalCity) {
        this.personalCity = personalCity;
    }

    public String getPersonalAddress() {
        return personalAddress;
    }

    public void setPersonalAddress(String personalAddress) {
        this.personalAddress = personalAddress;
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

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getTaxNumber() {
        return taxNumber;
    }

    public void setTaxNumber(String taxNumber) {
        this.taxNumber = taxNumber;
    }

    public List<State> getCities() {
        return cities;
    }

    public void setCities(List<State> cities) {
        this.cities = cities;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

}
