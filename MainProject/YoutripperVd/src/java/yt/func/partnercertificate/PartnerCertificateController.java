/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.func.partnercertificate;

import java.io.IOException;
import javaclass.common.YTAttr;
import javaclass.common.YTData;
import javaclass.utility.YTFileUtility;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import yt.func.partnercertificate.service.PartnerCertificationService;

/**
 *
 * @author KyLong
 */
@Controller
@RequestMapping(value = {YTData.PARTNER_PATH, "/{locale}" + YTData.PARTNER_PATH})
public class PartnerCertificateController {

    @Autowired
    PartnerCertificationService partnerCertificationService;

    @RequestMapping(value = PartnerCertificateViewMapping.PARTNER_ACCOUNT_CERTIFICATE_URL, method = RequestMethod.GET)
    public String goToRegularPackages(HttpServletRequest request) throws IOException {
        return "partner/PartnerCertificate";
    }

    // LOAD PERSONAL VERIFICATION
    @RequestMapping(value = PartnerCertificateViewMapping.PARTER_CERTIFICATE_LOAD_FILE, method = RequestMethod.POST)
    public @ResponseBody
    String loadPersonalVerification(HttpSession session) throws IOException {
        return partnerCertificationService.loadCertificate(session);
    }

    //UPDATE TEMP CERTIFICATION
    @RequestMapping(value = PartnerCertificateViewMapping.PARTNER_CERTIFICATE_IMAGE_UPLOAD, method = RequestMethod.POST)
    public @ResponseBody
    String uploadTemporaryPortrait(@RequestParam("certificationImage") MultipartFile porttraitImage,
            HttpSession session) throws IOException, InterruptedException {
        return partnerCertificationService.uploadTempFile(porttraitImage, session);
    }

    // SAVE CERTIFICATION
    @RequestMapping(value = PartnerCertificateViewMapping.PARTER_CERTIFICATE_SAVE_IMG_FILE, method = RequestMethod.POST)
    public @ResponseBody
    String saveImage(HttpServletRequest request, HttpSession session) throws IOException {
        String data = (String) request.getAttribute(YTAttr.AJAX_DATA_ATTR);
        return partnerCertificationService.saveCerification(data, session);
    }

    // SAVE CERTIFICATION
    @RequestMapping(value = PartnerCertificateViewMapping.PARTER_CERTIFICATE_DELETE_FILE, method = RequestMethod.POST)
    public @ResponseBody
    String delImage(HttpServletRequest request, HttpSession session) throws IOException {
        String data = (String) request.getAttribute(YTAttr.AJAX_DATA_ATTR);
        return partnerCertificationService.deleteCerification(data, session);
    }
}
