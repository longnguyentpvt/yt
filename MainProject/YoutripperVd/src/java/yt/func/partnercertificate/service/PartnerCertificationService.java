/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.func.partnercertificate.service;

import java.io.IOException;
import javax.servlet.http.HttpSession;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author nickn
 */
public interface PartnerCertificationService {

    public String uploadTempFile(MultipartFile porttraitImage, HttpSession session) throws IOException, InterruptedException;

    public String saveCerification(String data, HttpSession session) throws IOException;

    public String deleteCerification(String data, HttpSession session) throws IOException;

    public String getCDNLink() throws IOException;

    public String loadCertificate(HttpSession session) throws IOException;

}
