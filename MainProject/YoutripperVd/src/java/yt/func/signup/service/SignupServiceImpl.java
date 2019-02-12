/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.func.signup.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.List;
import javaclass.common.YTData;
import javaclass.common.YTPartnerData;
import javaclass.common.YTSession;
import javaclass.common.YTTripperData;
import javaclass.utility.YTDateTimeUtility;
import javaclass.utility.YTTokenUtility;
import javaclass.utility.YoutripperIDUtility;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import yt.entity.tbl.PartnerCountry;
import yt.entity.tbl.PartnerState;
import yt.entity.view.AbleResendPartnerActivation;
import yt.entity.view.PartnerActivation;
import yt.func.loginout.service.LogInOutService;
import yt.func.signup.dao.CountryDAO;
import yt.func.signup.dao.PartnerContactDAO;
import yt.func.signup.dao.PartnerCountryDAO;
import yt.func.signup.dao.PartnerDAO;
import yt.func.signup.dao.PartnerRegistrationNumberDAO;
import yt.func.signup.dao.PartnerStateDAO;
import yt.func.signup.dao.TripperDAO;
import yt.func.signup.dao.TripperRegistrationNumberDAO;
import yt.func.signup.javaclass.PartnerRegistrationForm;
import yt.func.signup.javaclass.PartnerRegistrationValidation;

/**
 *
 * @author nickn
 */
@Service
public class SignupServiceImpl implements SignupService {

    @Autowired
    private PartnerCountryDAO partnerSignupCountryDAO;

    @Autowired
    private PartnerStateDAO partnerSignupStateDAO;

    @Autowired
    private TripperDAO signupTripperDAO;

    @Autowired
    private PartnerDAO signupPartnerDAO;

    @Autowired
    private CountryDAO signupTripperCountryDAO;

    @Autowired
    private PartnerContactDAO signupPartnerContactDAO;

    @Autowired
    private PartnerRegistrationNumberDAO signupPartnerRegistrationNumberDAO;

    @Autowired
    private TripperRegistrationNumberDAO signupTripperRegistrationNumberDAO;

    @Autowired
    private EmailService signupEmailService;

    @Autowired
    private LogInOutService logInOutService;

    @Override
    @Transactional
    public String getPartnerSignupData() throws JsonProcessingException {
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

        String response = mapper.writeValueAsString(form);
        return response;
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
    public String isValidSignupEmail(String data) throws JsonProcessingException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode dataObject = mapper.readTree(data);

        String email = dataObject.get("email").asText();

        boolean partnerExists = signupPartnerDAO.checkEmailExists(email);
        boolean tripperExists = signupTripperDAO.checkEmailExists(email);

        boolean valid = !partnerExists && !tripperExists;

        return "{\"valid\":" + valid + "}";
    }

    @Override
    @Transactional
    public String registerPartner(HttpServletRequest request) {
        // get data from parameter
        String email = request.getParameter("email").trim().toLowerCase();
        String businessName = request.getParameter("businessName").trim();
        String businessType = request.getParameter("businessType").trim();
        String[] businessTypeTemp = businessType.split(":");
        businessType = businessTypeTemp[businessTypeTemp.length - 1];
        String country = request.getParameter("country").trim();
        String[] countryTemp = country.split(":");
        country = countryTemp[countryTemp.length - 1];
        String stateStr = request.getParameter("state").trim();
        String[] stateTemp = stateStr.split(":");
        stateStr = stateTemp[stateTemp.length - 1];
        long state = Long.parseLong(stateStr);
        String address = request.getParameter("address").trim();
        String phonecode = request.getParameter("phonecode").trim();
        String[] phoneCodeTemp = phonecode.split(":");
        phonecode = phoneCodeTemp[phoneCodeTemp.length - 1];
        String phone = request.getParameter("phone").trim();
        String firstName = request.getParameter("firstName").trim();
        String lastName = request.getParameter("lastName").trim();
        String displayName = request.getParameter("displayName").trim();
        String jobPosition = request.getParameter("jobTitle").trim();
        String[] jobTemp = jobPosition.split(":");
        jobPosition = jobTemp[jobTemp.length - 1];
        String password = request.getParameter("password").trim();
        String postcode = request.getParameter("postcode").trim();
        String city = request.getParameter("city").trim();

        if (PartnerRegistrationValidation.checkEmailRegularIsCorrect(email)
                && PartnerRegistrationValidation.checkPasswordRegularIsCorrect(password)
                && !businessName.isEmpty() && !displayName.isEmpty() && !address.isEmpty() && !postcode.isEmpty()
                && !firstName.isEmpty() && !lastName.isEmpty()
                && PartnerRegistrationValidation.checkPhoneNumberRegularIsCorrect(phone) && !phonecode.isEmpty()
                && !city.isEmpty() && !country.isEmpty() && !businessType.isEmpty() && !jobPosition.isEmpty()) {

            boolean partnerExists = signupPartnerDAO.checkEmailExists(email);
            boolean tripperExists = signupTripperDAO.checkEmailExists(email);
            if (!partnerExists && !tripperExists) {
                String tokenStr = YTTokenUtility.generateRegistrationToken(email);
                password = DigestUtils.md5DigestAsHex(password.getBytes());
                long signupTime = YTDateTimeUtility.getCurrentTimeInMilli();

                long partnerNo = signupPartnerRegistrationNumberDAO.registerPartnerNumber(signupTime);
                String partnerID = YoutripperIDUtility.generatePartnerID(partnerNo, businessType);
                boolean registered = signupPartnerDAO.registerNewPartner(partnerID, businessType, businessName, country, state, address,
                        phonecode, phone, postcode, firstName, lastName, displayName, jobPosition, email, password, tokenStr, signupTime, city);
                if (registered) {
                    // send async email
                    signupEmailService.sendPartnerAccountVerificationEmail(displayName, email, tokenStr);
                    return tokenStr;
                }
            }
        }
        return null;
    }

    @Override
    @Transactional
    public boolean resendPartnerActivationEmail(String emailToken) {
        AbleResendPartnerActivation partnerInfo = signupPartnerDAO.getPartnerResendActivationInfo(emailToken);
        if (partnerInfo != null) {
            String displayName = partnerInfo.getDisplayName();
            String email = partnerInfo.getEmail();
            signupEmailService.sendPartnerAccountVerificationEmail(displayName, email, emailToken);
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public boolean activatePartnerAccount(HttpServletRequest request, HttpServletResponse response,
            HttpSession session, String registrationToken) {
        if (registrationToken != null && !registrationToken.isEmpty()) {
            PartnerActivation partnerActivationInfo = signupPartnerDAO.getPartnerActivationInfoByToken(registrationToken);
            if (partnerActivationInfo != null) {
                String name = partnerActivationInfo.getDisplayName();
                String partnerID = partnerActivationInfo.getPartnerID();

                // update
                signupPartnerDAO.activatePartnerAccount(partnerID);

                // login
                logInOutService.loginAsPartner(request, response, session, partnerID, null, name, true, false);

                return true;
            }
        }
        return false;
    }

    @Override
    @Transactional
    public String registrationPartnerContact(HttpSession session, String data) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode dataObject = mapper.readTree(data);

        String email = dataObject.get("email").asText();
        // check exists in partner contact
        boolean exist = signupPartnerContactDAO.checkPartnerContactExists(email);
        // if not add to db
        if (!exist) {
            long currentTime = YTDateTimeUtility.getCurrentTimeInMilli();
            signupPartnerContactDAO.addNewPartnerContact(email, currentTime);
            YTSession.setPartnerContact(session, true);
        }

        return "{\"sc\" : " + !exist + "}";
    }

    @Override
    @Transactional
    public String closePartnerContact(HttpSession session) {
        YTSession.setPartnerContact(session, true);
        return "{\"sc\" : true}";
    }

    @Override
    @Transactional
    public String registerTripperAsYTAccount(String data, HttpServletRequest request, HttpServletResponse response,
            HttpSession session) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        JsonNode dataObject = mapper.readTree(data);

        String email = null;
        JsonNode emailNode = dataObject.get("em");
        if (!emailNode.isNull()) {
            email = emailNode.asText().trim().toLowerCase();
        }
        String password = null;
        JsonNode passwordNode = dataObject.get("pw");
        if (!passwordNode.isNull()) {
            password = passwordNode.asText().trim();
        }

        if (PartnerRegistrationValidation.checkEmailRegularIsCorrect(email)
                && PartnerRegistrationValidation.checkPasswordRegularIsCorrect(password)) {

            boolean partnerExists = signupPartnerDAO.checkEmailExists(email);
            boolean tripperExists = signupTripperDAO.checkEmailExists(email);
            if (!partnerExists && !tripperExists) {
                password = DigestUtils.md5DigestAsHex(password.getBytes());
                long signupTime = YTDateTimeUtility.getCurrentTimeInMilli();

                long tripperNo = signupTripperRegistrationNumberDAO.registerTripperNumber(signupTime);
                String tripperID = YoutripperIDUtility.generateTripperID(tripperNo);

                signupTripperDAO.registerNewTripper(tripperID, email, password,
                        signupTime, YTTripperData.ACCOUNT_TYPE_YT, YTTripperData.ACCOUNT_STATUS_ACTIVED, null, null, null);
                if (tripperID != null) {
                    // login
                    logInOutService.loginAsTripper(request, response, session, tripperID, null, null, false, false);
                    return "{\"success\": true}";
                }
                throw new IllegalArgumentException("Null TripperID");
            }
        }
        throw new IllegalArgumentException("Validation Fail");
    }

    @Override
    @Transactional
    public String registerTripperGoogleAccount(String email, String googleID) {
        long signupTime = YTDateTimeUtility.getCurrentTimeInMilli();
        long tripperNo = signupTripperRegistrationNumberDAO.registerTripperNumber(signupTime);
        String tripperID = YoutripperIDUtility.generateTripperID(tripperNo);

        String password = DigestUtils.md5DigestAsHex(googleID.getBytes());
        signupTripperDAO.registerNewTripper(tripperID, email, password,
                signupTime, YTTripperData.ACCOUNT_TYPE_GOOGLE, YTTripperData.ACCOUNT_STATUS_ACTIVED, null, googleID, null);
        return tripperID;
    }

    @Override
    @Transactional
    public String registerTripperFacebookAccount(String email, String facebookID) {
        long signupTime = YTDateTimeUtility.getCurrentTimeInMilli();
        long tripperNo = signupTripperRegistrationNumberDAO.registerTripperNumber(signupTime);
        String tripperID = YoutripperIDUtility.generateTripperID(tripperNo);

        String password = DigestUtils.md5DigestAsHex(facebookID.getBytes());
        signupTripperDAO.registerNewTripper(tripperID, email, password,
                signupTime, YTTripperData.ACCOUNT_TYPE_FACEBOOK, YTTripperData.ACCOUNT_STATUS_ACTIVED, facebookID, null, null);
        return tripperID;
    }

    @Override
    @Transactional
    public String registerTripperLinePayAccount(String email, String linepayID) {
        long signupTime = YTDateTimeUtility.getCurrentTimeInMilli();
        long tripperNo = signupTripperRegistrationNumberDAO.registerTripperNumber(signupTime);
        String tripperID = YoutripperIDUtility.generateTripperID(tripperNo);

        String password = DigestUtils.md5DigestAsHex(linepayID.getBytes());
        signupTripperDAO.registerNewTripper(tripperID, email, password,
                signupTime, YTTripperData.ACCOUNT_TYPE_LINEPAY, YTTripperData.ACCOUNT_STATUS_ACTIVED, null, null, linepayID);
        return tripperID;
    }

}
