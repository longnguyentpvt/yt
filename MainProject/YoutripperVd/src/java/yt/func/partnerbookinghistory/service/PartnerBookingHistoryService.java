/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.func.partnerbookinghistory.service;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Hiep
 */
public interface PartnerBookingHistoryService {

    public void getCommonData(HttpSession session, HttpServletRequest request) throws IOException;

    public String loadRegularBookingHistory(HttpSession session, String data) throws IOException;

    public String loadOpenBookingHistory(HttpSession session, String data) throws IOException;

}
