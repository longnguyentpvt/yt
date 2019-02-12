/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.func.partnerverification.service;

import java.io.IOException;
import javaclass.common.YTFileMeta;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author nickn
 */
public interface PartnerVerificationService {

    public String loadPersonal(HttpSession session) throws IOException;

    public String loadBank(HttpSession session) throws IOException;
    
    public String uploadTempFile(MultipartFile porttraitImage, HttpSession session) throws InterruptedException, IOException;

    public String savePersonalVerification(String data, HttpSession session) throws IOException;

    public String saveBankVerification(String data, HttpSession session) throws IOException;

    public String deletePersonalVerification(String data, HttpSession session) throws IOException;

    public String deleteBankVerification(String data, HttpSession session) throws IOException;

    public String loadCommonData(HttpSession session) throws IOException;

    public String submitPSN(String data, HttpSession session) throws IOException;

    public String submitBank(String data, HttpSession session) throws IOException;

}
