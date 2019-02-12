/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.func.search.javaclass;

/**
 *
 * @author nickn
 */
public class LocationSearchSuggestion {

    public String lname;
    public String lurl;

    public LocationSearchSuggestion() {
    }

    public LocationSearchSuggestion(String lname, String lurl) {
        this.lname = lname;
        this.lurl = lurl;
    }

    public String getLname() {
        return lname;
    }

    public String getLurl() {
        return lurl;
    }

}
