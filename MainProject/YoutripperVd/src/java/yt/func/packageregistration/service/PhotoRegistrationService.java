/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.func.packageregistration.service;

import java.io.IOException;
import javax.servlet.http.HttpSession;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author nickn
 */
public interface PhotoRegistrationService {

    public String getPhotoData(HttpSession session, String data) throws IOException;

    public String uploadCoverPhoto(HttpSession session, MultipartFile file) throws IOException;

    public String cropCoverAndSave(HttpSession session, String data) throws IOException, InterruptedException;

    public String uploadPortraitPhoto(HttpSession session, MultipartFile file) throws IOException;
}
