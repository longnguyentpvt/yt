/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.func.home.service;

import java.io.IOException;

/**
 *
 * @author Hiep
 */
public interface HomePackageService {

    public String loadHotestDeal(String data) throws IOException;

    public String loadJustBooked(String data) throws IOException;

    public String loadExploreMore(String data) throws Exception ;

}
