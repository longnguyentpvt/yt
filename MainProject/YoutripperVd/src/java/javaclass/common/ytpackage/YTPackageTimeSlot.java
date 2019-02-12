/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaclass.common.ytpackage;

/**
 *
 * @author KyLong
 */
public class YTPackageTimeSlot {

    private int timeSlot;
    private Integer quota;

    public YTPackageTimeSlot() {
    }

    public YTPackageTimeSlot(int timeSlot, Integer quota) {
        this.timeSlot = timeSlot;
        this.quota = quota;
    }

    public int getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(int timeSlot) {
        this.timeSlot = timeSlot;
    }

    public Integer getQuota() {
        return quota;
    }

    public void setQuota(Integer quota) {
        this.quota = quota;
    }

}
