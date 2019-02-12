/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.func.partnerprofile.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.List;
import javaclass.common.YTData;
import javaclass.common.YTPartnerData;
import javaclass.common.YTSession;
import javaclass.common.YTSessionAccount;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import yt.entity.tbl.PartnerCountry;
import yt.entity.tbl.PartnerState;
import yt.func.partnerprofile.dao.PartnerDAO;
import yt.func.partnerprofile.javaclass.PartnerBusinesslResponse;
import yt.func.partnerprofile.javaclass.PartnerPersonalResponse;
import yt.func.signup.dao.PartnerCountryDAO;
import yt.func.signup.dao.PartnerStateDAO;
import yt.func.signup.javaclass.PartnerRegistrationForm;
import yt.func.signup.javaclass.PartnerRegistrationValidation;

/**
 *
 * @author nickn
 */
@Service
public class PartnerProfileServiceImpl implements PartnerProfileService {

    @Autowired
    private PartnerDAO partnerprofilePartnerDAO;

    @Autowired
    private PartnerCountryDAO partnerSignupCountryDAO;

    @Autowired
    private PartnerStateDAO partnerSignupStateDAO;

    @Override
    @Transactional
    public void getData(HttpServletRequest request) throws JsonProcessingException {
        List<PartnerCountry> partnerCountries = partnerSignupCountryDAO.getAllCountries();

        PartnerRegistrationForm form = new PartnerRegistrationForm(YTPartnerData.BUSINESS_TYPE_PERSONAL,
                YTPartnerData.BUSINESS_TYPE_COMPANY,
                YTPartnerData.JOP_POSITION_BUSINESS_OWNER,
                YTPartnerData.JOP_POSITION_SALE_MAGAGER,
                YTPartnerData.JOP_POSITION_MARKETING_MAGAGER,
                YTPartnerData.JOP_POSITION_GENERAL_MAGAGER,
                YTPartnerData.JOP_POSITION_GENERAL_OFFICER,
                YTPartnerData.JOP_POSITION_OTHERS, YTData.PHONE_CODES, partnerCountries);

        ObjectMapper mapper = new ObjectMapper();
        request.setAttribute("commonData", mapper.writeValueAsString(form));

    }

    @Override
    @Transactional
    public String loadPartnerPersonalInformation(HttpSession session) throws IOException {
        YTSessionAccount account = YTSession.getAccountSession(session);
        String partnerID = account.getPartnerID();
        PartnerPersonalResponse partnerPersonalResponse = partnerprofilePartnerDAO.getPartnerPersonalInfor(partnerID);
        ObjectMapper mapper = new ObjectMapper();
        if (partnerPersonalResponse != null) {
            return mapper.writeValueAsString(partnerPersonalResponse);
        }
        return null;
    }

    @Override
    @Transactional
    public String updatePartnerPersonalInformation(String data, HttpSession session) throws IOException {
        YTSessionAccount account = YTSession.getAccountSession(session);
        String partnerID = account.getPartnerID();
        //inite object mapper
        ObjectMapper mapper = new ObjectMapper();
        JsonNode dataObject = mapper.readTree(data);
        //get infor from data
        JsonNode firstNameNode = dataObject.get("firstName");
        String firstName = null;
        if (!firstNameNode.isNull()) {
            firstName = firstNameNode.asText();
        }
        JsonNode lastNameNode = dataObject.get("lastName");
        String lastName = null;
        if (!lastNameNode.isNull()) {
            lastName = lastNameNode.asText();
        }
        JsonNode positionNode = dataObject.get("position");
        String jobPosition = null;
        if (!positionNode.isNull()) {
            jobPosition = positionNode.asText();
        }

        //check validate
        boolean success = false;
        if (firstName != null && !firstName.isEmpty()
                && lastName != null && !lastName.isEmpty() && jobPosition != null && !jobPosition.isEmpty()) {
            //update
            success = partnerprofilePartnerDAO.updatePersonalInfor(partnerID, firstName, lastName, jobPosition);
        }

        if (!success) {
            throw new IllegalArgumentException("Wrong validate or wrong partner id");
        }

        //return
        return "{\"result\": " + success + "}";
    }

    @Override
    @Transactional
    public String updatePartnerAccountInformation(String data, HttpSession session) throws IOException {
        YTSessionAccount account = YTSession.getAccountSession(session);
        String partnerID = account.getPartnerID();
        //inite object mapper
        ObjectMapper mapper = new ObjectMapper();
        JsonNode dataObject = mapper.readTree(data);
        //get infor from data
        JsonNode currentPasswordNode = dataObject.get("currentPassword");
        String currentPassword = null;
        if (!currentPasswordNode.isNull()) {
            currentPassword = currentPasswordNode.asText().trim();
        }
        JsonNode newPasswordNode = dataObject.get("newPassword");
        String newPassword = null;
        if (!newPasswordNode.isNull()) {
            newPassword = newPasswordNode.asText().trim();
        }
        JsonNode confirmPasswordNode = dataObject.get("confirmPassword");
        String confirmPassword = null;
        if (!confirmPasswordNode.isNull()) {
            confirmPassword = confirmPasswordNode.asText().trim();
        }
        //check validate
        Boolean success = false;
        if (currentPassword != null && newPassword != null && confirmPassword != null
                && !currentPassword.isEmpty() && !newPassword.isEmpty() && !confirmPassword.isEmpty()
                && PartnerRegistrationValidation.checkPasswordRegularIsCorrect(newPassword)
                && PartnerRegistrationValidation.checkPasswordRegularIsCorrect(confirmPassword)
                && newPassword.equals(confirmPassword)) {
            currentPassword = DigestUtils.md5DigestAsHex(currentPassword.getBytes());
            boolean isCorrect = false;
            String oldPasswword = partnerprofilePartnerDAO.getCurrentPassword(partnerID);
            if (currentPassword.equals(oldPasswword)) {
                isCorrect = true;
            }
            // set null for showing error
            if (!isCorrect) {
                success = null;
            }
            if (isCorrect) {
                newPassword = DigestUtils.md5DigestAsHex(newPassword.getBytes());
                //update new password
                success = partnerprofilePartnerDAO.updatePassword(partnerID, newPassword);
            }
        } else {
            throw new IllegalArgumentException("Partner profile - Wrong validate");
        }

        //return
        return "{\"result\": " + success + "}";
    }

    @Override
    @Transactional
    public String loadPartnerBusinessInformation(HttpSession session) throws IOException {
        YTSessionAccount account = YTSession.getAccountSession(session);
        String partnerID = account.getPartnerID();
        PartnerBusinesslResponse partnerBusinesslResponse = partnerprofilePartnerDAO.getPartnerBusinessInfor(partnerID);
        if (partnerBusinesslResponse != null) {
            ObjectMapper mapper = new ObjectMapper();
            String countryID = partnerBusinesslResponse.getCountryID();
            List<PartnerState> states = partnerSignupStateDAO.getAllStatesFromACountry(countryID);
            states.sort(PartnerState.STATE_NAME_COMPARATOR);
            partnerBusinesslResponse.setCities(states);
            return mapper.writeValueAsString(partnerBusinesslResponse);
        }
        return null;
    }

    @Override
    @Transactional
    public String getPartnerStatesOfCountry(String data) throws JsonProcessingException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode dataObject = mapper.readTree(data);
        String countryID = dataObject.get("countryID").asText();
        List<PartnerState> states = partnerSignupStateDAO.getAllStatesFromACountry(countryID);
        states.sort(PartnerState.STATE_NAME_COMPARATOR);
        return mapper.writeValueAsString(states);
    }

    @Override
    @Transactional
    public String updatePartnerBusinessInformation(String data, HttpSession session) throws IOException {
        YTSessionAccount account = YTSession.getAccountSession(session);
        String partnerID = account.getPartnerID();
        //inite object mapper
        ObjectMapper mapper = new ObjectMapper();
        JsonNode dataObject = mapper.readTree(data);
        //get infor from data
        JsonNode countryIDNode = dataObject.get("countryID");
        String countryID = null;
        if (!countryIDNode.isNull()) {
            countryID = countryIDNode.asText().trim();
        }
        JsonNode stateIDNode = dataObject.get("stateID");
        Long stateID = 0l;
        if (!stateIDNode.isNull()) {
            stateID = stateIDNode.asLong();
        }
        JsonNode businessAddressNode = dataObject.get("businessAddress");
        String businessAddress = null;
        if (!businessAddressNode.isNull()) {
            businessAddress = businessAddressNode.asText().trim();
        }
        JsonNode businessCityNode = dataObject.get("businessCity");
        String businessCity = null;
        if (!businessCityNode.isNull()) {
            businessCity = businessCityNode.asText().trim();
        }
        JsonNode postalCodeNode = dataObject.get("postalCode");
        String postalCode = null;
        if (!postalCodeNode.isNull()) {
            postalCode = postalCodeNode.asText().trim();
        }
        JsonNode phoneCodeNode = dataObject.get("phoneCode");
        String phoneCode = null;
        if (!phoneCodeNode.isNull()) {
            phoneCode = phoneCodeNode.asText().trim();
        }
        JsonNode phoneNumberNode = dataObject.get("phoneNumber");
        String phoneNumber = null;
        if (!phoneNumberNode.isNull()) {
            phoneNumber = phoneNumberNode.asText().trim();
        }
        JsonNode businessBackGroundNode = dataObject.get("businessBackGround");
        String businessBackGround = null;
        if (!businessBackGroundNode.isNull()) {
            businessBackGround = businessBackGroundNode.asText().trim();
        }
        //check validate

        boolean success = false;
        if (countryID != null && !countryID.isEmpty() && stateID > 0
                && businessAddress != null && !businessAddress.isEmpty() && businessCity != null
                && !businessCity.isEmpty() && postalCode != null && !postalCode.isEmpty() && phoneCode != null
                && !phoneCode.isEmpty() && phoneNumber != null && !phoneNumber.isEmpty()
                && PartnerRegistrationValidation.checkPhoneNumberRegularIsCorrect(phoneNumber)) {
            success = partnerprofilePartnerDAO.updateBusinessInfor(partnerID, countryID, stateID, businessAddress,
                    businessCity, postalCode, phoneCode, phoneNumber, businessBackGround);
        }

        if (!success) {
            throw new IllegalArgumentException("wrong validate or wrong partner id");
        }
        //return
        return "{\"result\": " + success + "}";
    }

}
