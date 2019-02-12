/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.func.partnerbookingmanager.javaclass;

import java.util.List;
import yt.entity.tbl.YTPackage;

/**
 *
 * @author Hiep
 */
public class InitData {

    private List<AdYTPackage> ytps;
    private long currentDate;

    public InitData() {
    }

    public List<AdYTPackage> getYtps() {
        return ytps;
    }

    public void setYtps(List<AdYTPackage> ytps) {
        this.ytps = ytps;
    }

    public long getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(long currentDate) {
        this.currentDate = currentDate;
    }

}
