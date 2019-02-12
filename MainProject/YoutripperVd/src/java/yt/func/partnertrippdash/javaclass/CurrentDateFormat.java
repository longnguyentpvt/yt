/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.func.partnertrippdash.javaclass;

/**
 *
 * @author Hiep
 */
public class CurrentDateFormat {

    private String currentDateDDMMYYY;
    private String currentDateMMYY;

    public CurrentDateFormat() {
    }

    public CurrentDateFormat(String currentDateDDMMYYY, String currentDateMMYY) {
        this.currentDateDDMMYYY = currentDateDDMMYYY;
        this.currentDateMMYY = currentDateMMYY;
    }

    public String getCurrentDateDDMMYYY() {
        return currentDateDDMMYYY;
    }

    public void setCurrentDateDDMMYYY(String currentDateDDMMYYY) {
        this.currentDateDDMMYYY = currentDateDDMMYYY;
    }

    public String getCurrentDateMMYY() {
        return currentDateMMYY;
    }

    public void setCurrentDateMMYY(String currentDateMMYY) {
        this.currentDateMMYY = currentDateMMYY;
    }
}
