/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.func.packageregistration.service;

import java.io.IOException;
import javax.servlet.http.HttpSession;

/**
 *
 * @author nickn
 */
public interface CategoryRegistrationService {

    public String getCategoryData(HttpSession session, String data) throws IOException;

    public String getSubCategoryByCategory(String data) throws IOException;

    public String updateCategoryAndSubcategory(HttpSession session, String data) throws IOException;

    public String updateSuitability(HttpSession session, String data) throws IOException;

    public String updateTargetLocation(HttpSession session, String data) throws IOException;

    public String updatePackageColor(HttpSession session, String data) throws IOException;
}
