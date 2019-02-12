/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.func.partnertrippdash.javaclass;

import java.util.List;

/**
 *
 * @author Hiep
 */
public class PackageSellingReturnData {

    private List<PackageSelling> top2;
    private String currentDateMMYY;

    public PackageSellingReturnData() {
    }

    public PackageSellingReturnData(List<PackageSelling> top2, String currentDateMMYY) {
        this.top2 = top2;
        this.currentDateMMYY = currentDateMMYY;
    }

    public List<PackageSelling> getTop2() {
        return top2;
    }

    public void setTop2(List<PackageSelling> top2) {
        this.top2 = top2;
    }

    public String getCurrentDateMMYY() {
        return currentDateMMYY;
    }

    public void setCurrentDateMMYY(String currentDateMMYY) {
        this.currentDateMMYY = currentDateMMYY;
    }

}
