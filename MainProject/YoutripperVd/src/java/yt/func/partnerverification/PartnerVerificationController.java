/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.func.partnerverification;

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
import yt.func.partnerverification.service.PartnerVerificationService;

/**
 *
 * @author nickn
 */
@Controller
@RequestMapping(value = {YTData.PARTNER_PATH, "/{locale}" + YTData.PARTNER_PATH})
public class PartnerVerificationController {

    @Autowired
    PartnerVerificationService partnerVerificationService;

    @RequestMapping(value = PartnerVerificationViewMapping.PARTER_VERIFICATION_URL, method = RequestMethod.GET)
    public String goToPartnerVerification(HttpServletRequest request) {
        return "partner/PartnerVerification";
    }

    //UPDATE TEMP VERIFICATION
    @RequestMapping(value = PartnerVerificationViewMapping.PARTER_VERIFICATION_IMAGE_UPLOAD, method = RequestMethod.POST)
    public @ResponseBody
    String uploadTemporaryPortrait(@RequestParam("verificationImage") MultipartFile porttraitImage,
            HttpSession session) throws IOException, InterruptedException {
        // get data from image
        // save temporary Image to session
        return partnerVerificationService.uploadTempFile(porttraitImage, session);
    }

    // SAVE VERIFICATION
    @RequestMapping(value = PartnerVerificationViewMapping.PARTER_VERIFICATION_SAVE_PERSONAL_FILE, method = RequestMethod.POST)
    public @ResponseBody
    String savePersonalImage(HttpServletRequest request, HttpSession session) throws IOException {
        String data = (String) request.getAttribute(YTAttr.AJAX_DATA_ATTR);
        return partnerVerificationService.savePersonalVerification(data, session);
    }

    // SAVE BANK
    @RequestMapping(value = PartnerVerificationViewMapping.PARTER_VERIFICATION_SAVE_BANK_FILE, method = RequestMethod.POST)
    public @ResponseBody
    String saveBankImage(HttpServletRequest request, HttpSession session) throws IOException {
        String data = (String) request.getAttribute(YTAttr.AJAX_DATA_ATTR);
        return partnerVerificationService.saveBankVerification(data, session);
    }

    // LOAD PERSONAL VERIFICATION
    @RequestMapping(value = PartnerVerificationViewMapping.PARTNER_LOAD_PERSONAL_VERIFICATION, method = RequestMethod.POST)
    public @ResponseBody
    String loadPersonalVerification(HttpSession session) throws IOException {
        return partnerVerificationService.loadPersonal(session);
    }

    // LOAD BANK VERIFICATION
    @RequestMapping(value = PartnerVerificationViewMapping.PARTNER_LOAD_BANK_VERIFICATION, method = RequestMethod.POST)
    public @ResponseBody
    String loadBankVerification(HttpSession session) throws IOException {
        return partnerVerificationService.loadBank(session);
    }

    // DELETE PERSONAL
    @RequestMapping(value = PartnerVerificationViewMapping.PARTER_VERIFICATION_DELETE_PERSONAL_FILE, method = RequestMethod.POST)
    public @ResponseBody
    String deletePersonalImage(HttpServletRequest request, HttpSession session) throws IOException {
        String data = (String) request.getAttribute(YTAttr.AJAX_DATA_ATTR);
        return partnerVerificationService.deletePersonalVerification(data, session);
    }

    // DELETE BANK
    @RequestMapping(value = PartnerVerificationViewMapping.PARTER_VERIFICATION_DELETE_BANK_FILE, method = RequestMethod.POST)
    public @ResponseBody
    String deleteBankImage(HttpServletRequest request, HttpSession session) throws IOException {
        String data = (String) request.getAttribute(YTAttr.AJAX_DATA_ATTR);
        return partnerVerificationService.deleteBankVerification(data, session);
    }

    // LOAD COMMON DATA
    @RequestMapping(value = PartnerVerificationViewMapping.PARTER_VERIFICATION_LOAD_COMMON_DATA, method = RequestMethod.POST)
    public @ResponseBody
    String loadCommonData(HttpServletRequest request, HttpSession session) throws IOException {
        return partnerVerificationService.loadCommonData(session);
    }

    // SUBMIT PERSONAL
    @RequestMapping(value = PartnerVerificationViewMapping.PARTER_VERIFICATION_SUBMIT_PSN, method = RequestMethod.POST)
    public @ResponseBody
    String submitPersonal(HttpServletRequest request, HttpSession session) throws IOException {
        String data = (String) request.getAttribute(YTAttr.AJAX_DATA_ATTR);
        return partnerVerificationService.submitPSN(data, session);
    }

    // SUBMIT BANK
    @RequestMapping(value = PartnerVerificationViewMapping.PARTER_VERIFICATION_SUBMIT_BANK, method = RequestMethod.POST)
    public @ResponseBody
    String submitBank(HttpServletRequest request, HttpSession session) throws IOException {
        String data = (String) request.getAttribute(YTAttr.AJAX_DATA_ATTR);
        return partnerVerificationService.submitBank(data, session);
    }
}
