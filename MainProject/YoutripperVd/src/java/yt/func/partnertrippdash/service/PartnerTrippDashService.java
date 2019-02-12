/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.func.partnertrippdash.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author nickn
 */
public interface PartnerTrippDashService {

    public String loadPackageOverview(HttpSession session) throws JsonProcessingException;

    public String loadHigestGrossing(HttpSession session) throws JsonProcessingException;

    public String loadTotalSales(HttpSession session) throws JsonProcessingException;

    public String loadUpcomingToday(HttpSession session, String data) throws JsonProcessingException, IOException;

    public String loadBestSelling(HttpSession session) throws JsonProcessingException, IOException;

}
