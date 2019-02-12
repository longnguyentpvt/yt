/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.func.tripperprofile.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.io.IOException;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Hiep
 */
public interface TripperProfileService {

    public void getData(HttpServletRequest request, Locale locale) throws JsonProcessingException;

    public String loadTripperPersonalInformation(HttpSession session) throws IOException;

    public String getTripperStatesOfCountry(String data) throws JsonProcessingException, IOException;

    public String updateTripperPersonalInformation(String data, HttpSession session) throws IOException;

    public String updateTripperAccountInformation(String data, HttpSession session) throws IOException;

    public String updateTripperAccountInformationEmail(String data, HttpSession session) throws IOException;

    public String getListCard(HttpSession session) throws IOException;

    public String removeCard(String data, HttpSession session) throws IOException;

    public String getListBilling(HttpSession session) throws IOException;

    public String removeBilling(String data, HttpSession session) throws IOException;

    public String getBillingDetail(String data, HttpSession session) throws IOException;

    public String loadOptional(HttpSession session) throws IOException;

    public String saveOptional(HttpSession session, String data) throws IOException;

}
