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
public interface PackageRegistrationService {

    public String registerNewPackage(HttpSession session, String data) throws IOException;

    public boolean checkCategoryStep(long packageID, String partnerID);

    public String checkCategoryStep(HttpSession session, String data) throws IOException;

    public boolean checkDescriptionStep(long packageID, String partnerID) throws IOException;

    public String checkDescriptionStep(HttpSession session, String data) throws IOException;

    public boolean checkPhotoStep(long packageID, String partnerID);

    public String checkPhotoStep(HttpSession session, String data) throws IOException;

}
