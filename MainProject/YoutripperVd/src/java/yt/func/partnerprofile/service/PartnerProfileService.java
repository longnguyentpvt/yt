/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.func.partnerprofile.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author nickn
 */
public interface PartnerProfileService {

    public String loadPartnerPersonalInformation(HttpSession session) throws IOException;

    public String updatePartnerPersonalInformation(String data, HttpSession session) throws IOException;

    public String updatePartnerAccountInformation(String data, HttpSession session) throws IOException;

    public void getData(HttpServletRequest request) throws JsonProcessingException;

    public String loadPartnerBusinessInformation(HttpSession session) throws IOException;

    public String getPartnerStatesOfCountry(String data) throws JsonProcessingException, IOException;

    public String updatePartnerBusinessInformation(String data, HttpSession session) throws IOException;
}
