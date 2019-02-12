/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.func.tripperprofile.javaclass;

import java.util.List;
import yt.entity.tbl.Country;

/**
 *
 * @author Hiep
 */
public class TripperCommonData {

    private String[] phoneCodes;
    private List<Country> countries;

    public TripperCommonData() {
    }

    public TripperCommonData(String[] phoneCodes, List<Country> countries) {
        this.phoneCodes = phoneCodes;
        this.countries = countries;
    }

    public String[] getPhoneCodes() {
        return phoneCodes;
    }

    public void setPhoneCodes(String[] phoneCodes) {
        this.phoneCodes = phoneCodes;
    }

    public List<Country> getCountries() {
        return countries;
    }

    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }
}
