/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.func.partnerbookinghistory.javaclass;

import yt.func.partnerbookingmanager.javaclass.*;
import java.util.List;
import yt.entity.tbl.YTPackage;

/**
 *
 * @author Hiep
 */
public class InitData {

    private List<YTPackage> ytps;
    private Long currentDate;
    private String durationMin;
    private String durationDay;
    private String durationWeek;
    private String durationMonth;
    private long unlimitDate;

    public InitData() {
    }

    public InitData(List<YTPackage> ytps, Long currentDate, String durationMin, String durationDay, String durationWeek, String durationMonth, long unlimitDate) {
        this.ytps = ytps;
        this.currentDate = currentDate;
        this.durationMin = durationMin;
        this.durationDay = durationDay;
        this.durationWeek = durationWeek;
        this.durationMonth = durationMonth;
        this.unlimitDate = unlimitDate;
    }

    public List<YTPackage> getYtps() {
        return ytps;
    }

    public void setYtps(List<YTPackage> ytps) {
        this.ytps = ytps;
    }

    public Long getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(Long currentDate) {
        this.currentDate = currentDate;
    }

    public String getDurationMin() {
        return durationMin;
    }

    public void setDurationMin(String durationMin) {
        this.durationMin = durationMin;
    }

    public String getDurationDay() {
        return durationDay;
    }

    public void setDurationDay(String durationDay) {
        this.durationDay = durationDay;
    }

    public String getDurationWeek() {
        return durationWeek;
    }

    public void setDurationWeek(String durationWeek) {
        this.durationWeek = durationWeek;
    }

    public String getDurationMonth() {
        return durationMonth;
    }

    public void setDurationMonth(String durationMonth) {
        this.durationMonth = durationMonth;
    }

    public long getUnlimitDate() {
        return unlimitDate;
    }

    public void setUnlimitDate(long unlimitDate) {
        this.unlimitDate = unlimitDate;
    }

}
