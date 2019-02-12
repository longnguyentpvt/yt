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
public class UpcomingReturnData {

    private List<UpcomingBooking> bookingOrders;
    private String currentDateDDMMYYY;

    public UpcomingReturnData() {
    }

    public UpcomingReturnData(List<UpcomingBooking> bookingOrders, String currentDateDDMMYYY) {
        this.bookingOrders = bookingOrders;
        this.currentDateDDMMYYY = currentDateDDMMYYY;
    }

    public List<UpcomingBooking> getBookingOrders() {
        return bookingOrders;
    }

    public void setBookingOrders(List<UpcomingBooking> bookingOrders) {
        this.bookingOrders = bookingOrders;
    }

    public String getCurrentDateDDMMYYY() {
        return currentDateDDMMYYY;
    }

    public void setCurrentDateDDMMYYY(String currentDateDDMMYYY) {
        this.currentDateDDMMYYY = currentDateDDMMYYY;
    }

}
