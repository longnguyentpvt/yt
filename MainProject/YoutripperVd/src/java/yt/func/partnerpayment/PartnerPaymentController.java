/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.func.partnerpayment;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.io.IOException;
import javaclass.common.YTAttr;
import javaclass.common.YTData;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import yt.func.partnerpayment.service.PartnerPaymentService;

/**
 *
 * @author nickn
 */
@Controller
@RequestMapping(value = {YTData.PARTNER_PATH, "/{locale}" + YTData.PARTNER_PATH})
public class PartnerPaymentController {

    @Autowired
    private PartnerPaymentService partnerPaymentService;

    @RequestMapping(value = PartnerPaymentViewMapping.PARTNER_PAYMENT_URL, method = RequestMethod.GET)

    public String goToPartnerProfile(HttpServletRequest request) throws JsonProcessingException {
        // partnerprofileService.getData(request);
        return "partner/PaymentManagement";
    }

    // LOAD STATES
    @RequestMapping(value = PartnerPaymentViewMapping.PARTNER_PAYMENT_LOAD_LIST, method = RequestMethod.POST)
    @ResponseBody
    public String getPaymentList(HttpServletRequest request, HttpSession session) throws JsonProcessingException, IOException {
        String data = (String) request.getAttribute(YTAttr.AJAX_DATA_ATTR);
        return partnerPaymentService.getPaymentList(session, data);
    }

}
