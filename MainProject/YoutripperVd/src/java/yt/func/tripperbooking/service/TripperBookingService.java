/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.func.tripperbooking.service;

import java.io.IOException;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Hiep
 */
public interface TripperBookingService {

    public String loadTripperBooking(HttpSession session, String data) throws IOException;

}
