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
public interface DescriptionRegistrationService {

    public String getDescriptionData(HttpSession session, String data) throws IOException;

    public String updatePackageName(HttpSession session, String data) throws IOException;

    public String updateMultiDescription(HttpSession session, String data) throws IOException;

    public String activeEditing(HttpSession session, String data) throws IOException;

    public String activeContentLanguage(HttpSession session, String data) throws IOException;

    public String unActiveContentLanguage(HttpSession session, String data) throws IOException;

    public String updateActivityLocation(HttpSession session, String data) throws IOException;

    public String updateDepartureLocation(HttpSession session, String data) throws IOException;

    public String updatePickupLocation(HttpSession session, String data) throws IOException;

    public String updateKeywords(HttpSession session, String data) throws IOException;

    public String updateGoogleDescription(HttpSession session, String data) throws IOException;
    
    public String updateServingLanguage(HttpSession session, String data) throws IOException;
}
