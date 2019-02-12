/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.func.partnerbookingmanager.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Hiep
 */
public interface PartnerBookingManagerService {

    public void getCommonData(HttpSession session, HttpServletRequest request) throws JsonProcessingException;

    public String loadRegularBookingManager(HttpSession session, String data) throws JsonProcessingException, IOException;

    public String loadOpenBookingManager(HttpSession session, String data) throws JsonProcessingException, IOException;

}
