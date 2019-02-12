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
public class PackageTotalReturnData {

    private List<PackageWithTotal> top3;
    private String currentDateMMYY;

    public PackageTotalReturnData() {
    }

    public PackageTotalReturnData(List<PackageWithTotal> top3, String currentDateMMYY) {
        this.top3 = top3;
        this.currentDateMMYY = currentDateMMYY;
    }

    public List<PackageWithTotal> getTop3() {
        return top3;
    }

    public void setTop3(List<PackageWithTotal> top3) {
        this.top3 = top3;
    }

    public String getCurrentDateMMYY() {
        return currentDateMMYY;
    }

    public void setCurrentDateMMYY(String currentDateMMYY) {
        this.currentDateMMYY = currentDateMMYY;
    }

}
