/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.func.packagedetail.service;

import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author nickn
 */
public interface PackageDetailService {

    public void getPackageDetailData(String packageID, HttpServletRequest request, Locale locale, HttpSession session);
}
