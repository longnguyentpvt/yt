/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.func.tripperprofile.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import javaclass.common.YTData;
import javaclass.common.YTSession;
import javaclass.common.YTSessionAccount;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import yt.entity.tbl.Country;
import yt.entity.tbl.State;
import yt.entity.tbl.Tripper;
import yt.entity.tbl.TripperBillingAddress;
import yt.entity.tbl.TripperCreditCard;
import yt.func.signup.javaclass.PartnerRegistrationValidation;
import yt.func.tripperprofile.dao.CountryDAO;
import yt.func.tripperprofile.dao.TripperBillingAddressDAO;
import yt.func.tripperprofile.dao.TripperCreditCardDAO;
import yt.func.tripperprofile.dao.TripperDAO;
import yt.func.tripperprofile.javaclass.TripperCommonData;
import yt.func.tripperprofile.javaclass.TripperOptional;
import yt.func.tripperprofile.javaclass.TripperPersonalResponse;

/**
 *
 * @author Hiep
 */
@Service
public class TripperProfileServiceImpl implements TripperProfileService {

    @Autowired
    private CountryDAO tripperCountryDAO;

    @Autowired
    private TripperDAO tripperProfileTripperDAO;

    @Autowired
    private TripperCreditCardDAO tripperProfileCardDAO;

    @Autowired
    private TripperBillingAddressDAO tripperProfileBillingDAO;

    @Override
    @Transactional
    public void getData(HttpServletRequest request, Locale locale) throws JsonProcessingException {
        List<Country> countries = tripperCountryDAO.getAllCountries();

        TripperCommonData tcd = new TripperCommonData(YTData.PHONE_CODES, countries);

        ObjectMapper mapper = new ObjectMapper();
        request.setAttribute("commonData", mapper.writeValueAsString(tcd));
    }

    @Override
    @Transactional
    public String loadTripperPersonalInformation(HttpSession session) throws IOException {
        YTSessionAccount account = YTSession.getAccountSession(session);
        String tripperID = account.getTripperID();
        Tripper tripper = tripperProfileTripperDAO.getPersonal(tripperID);
        //
        if (tripper != null) {
            String phoneCode = tripper.getPhoneCode();
            if (phoneCode == null || phoneCode.isEmpty()) {
                phoneCode = "66";
            }

            String countryID = tripper.getCountryID();
            List<State> states = tripperCountryDAO.getAllStatesFromACountry(countryID);
            states.sort(State.STATE_NAME_COMPARATOR);

            String firstName = tripper.getFirstName();
            String lastName = tripper.getLastName();
            Long stateID = tripper.getStateID();
            String personalAddress = tripper.getPersonalAddress();
            String personalCity = tripper.getPersonalCity();
            String postalCode = tripper.getPostalCode();
            String phoneNumber = tripper.getPhoneNumber();
            String company = tripper.getCompany();
            String taxNumber = tripper.getTaxNumber();
            Boolean gender = tripper.getGender();
            String email = tripper.getEmail();
            String accountType = tripper.getAccountType();
            String countryName = tripper.getCountry().getCountryName();
            String stateName = tripper.getState().getStateName();
            String displayName = tripper.getDisplayName();

            TripperPersonalResponse response = new TripperPersonalResponse(firstName, lastName, gender, countryID, countryName,
                    stateID, stateName, personalCity, personalAddress, postalCode, phoneCode, phoneNumber,
                    company, taxNumber, states, email, accountType, displayName);

            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(response);
        }
        throw new IllegalArgumentException("Wrong Tripper ID");
    }

    @Override
    @Transactional
    public String getTripperStatesOfCountry(String data) throws JsonProcessingException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode dataObject = mapper.readTree(data);
        String countryID = dataObject.get("countryID").asText();
        List<State> states = tripperCountryDAO.getAllStatesFromACountry(countryID);
        states.sort(State.STATE_NAME_COMPARATOR);
        return mapper.writeValueAsString(states);
    }

    @Override
    @Transactional
    public String updateTripperPersonalInformation(String data, HttpSession session) throws IOException {
        YTSessionAccount account = YTSession.getAccountSession(session);
        String tripperID = account.getTripperID();
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
        JsonNode genderNode = dataObject.get("gender");
        Boolean gender = null;
        if (!genderNode.isNull()) {
            gender = genderNode.asBoolean();
        }

        JsonNode countryIDNode = dataObject.get("countryID");
        String countryID = null;
        if (!countryIDNode.isNull()) {
            countryID = countryIDNode.asText();
        }

        JsonNode stateIDNode = dataObject.get("stateID");
        Long stateID = null;
        if (!stateIDNode.isNull()) {
            stateID = stateIDNode.asLong();
        }

        JsonNode personalAddressNode = dataObject.get("personalAddress");
        String personalAddress = null;
        if (!personalAddressNode.isNull()) {
            personalAddress = personalAddressNode.asText();
        }

        JsonNode personalCityNode = dataObject.get("personalCity");
        String personalCity = null;
        if (!personalCityNode.isNull()) {
            personalCity = personalCityNode.asText();
        }

        JsonNode postalCodeNode = dataObject.get("postalCode");
        String postalCode = null;
        if (!postalCodeNode.isNull()) {
            postalCode = postalCodeNode.asText();
        }

        JsonNode phoneCodeNode = dataObject.get("phoneCode");
        String phoneCode = null;
        if (!phoneCodeNode.isNull()) {
            phoneCode = phoneCodeNode.asText();
        }

        JsonNode phoneNumberNode = dataObject.get("phoneNumber");
        String phoneNumber = null;
        if (!phoneNumberNode.isNull()) {
            phoneNumber = phoneNumberNode.asText();
        }

        JsonNode companyNameNode = dataObject.get("companyName");
        String companyName = null;
        if (!companyNameNode.isNull()) {
            companyName = companyNameNode.asText();
        }

        JsonNode taxNumberNode = dataObject.get("taxNumber");
        String taxNumber = null;
        if (!taxNumberNode.isNull()) {
            taxNumber = taxNumberNode.asText();
        }

        JsonNode displayNameNode = dataObject.get("displayName");
        String displayName = null;
        if (!displayNameNode.isNull()) {
            displayName = displayNameNode.asText();
        }

        //check validate
        if (firstName != null && !firstName.isEmpty()
                && lastName != null && !lastName.isEmpty() && countryID != null && !countryID.isEmpty()
                && stateID != null && personalAddress != null && !personalAddress.isEmpty()
                && personalCity != null && !personalCity.isEmpty() && postalCode != null && !postalCode.isEmpty()
                && phoneNumber != null && !phoneNumber.isEmpty() && PartnerRegistrationValidation.checkPhoneNumberRegularIsCorrect(phoneNumber)
                && companyName != null && !companyName.isEmpty() && taxNumber != null && !taxNumber.isEmpty()
                && PartnerRegistrationValidation.checkIDNumberRegularIsCorect(taxNumber) && displayName != null && !displayName.isEmpty()) {
            //update
            boolean success = tripperProfileTripperDAO.updatePersonalInfor(tripperID, firstName, lastName, gender, countryID, stateID,
                    personalAddress, personalCity, postalCode, phoneCode, phoneNumber, companyName, taxNumber, displayName);
            if (success) {
                //return
                account.setName(displayName);
                return "{\"result\": " + success + "}";
            }
            throw new IllegalArgumentException("Wrong tripper id");
        }

        throw new IllegalArgumentException("Wrong validate");
    }

    @Override
    @Transactional
    public String updateTripperAccountInformation(String data, HttpSession session) throws IOException {
        YTSessionAccount account = YTSession.getAccountSession(session);
        String tripperID = account.getTripperID();
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
            String oldPasswword = tripperProfileTripperDAO.getCurrentPassword(tripperID);
            if (currentPassword.equals(oldPasswword)) {
                isCorrect = true;
            }

            if (isCorrect) {
                newPassword = DigestUtils.md5DigestAsHex(newPassword.getBytes());
                //update new password
                success = tripperProfileTripperDAO.updatePassword(tripperID, newPassword);
                if (success) {
                    //return
                    return "{\"result\": " + success + "}";
                }
                throw new IllegalArgumentException("Tripper profile - Wrong Tripper Id");
            }
        }

        throw new IllegalArgumentException("Tripper profile - Wrong validate");
    }

    @Override
    @Transactional
    public String updateTripperAccountInformationEmail(String data, HttpSession session) throws IOException {
        YTSessionAccount account = YTSession.getAccountSession(session);
        String tripperID = account.getTripperID();
        //inite object mapper
        ObjectMapper mapper = new ObjectMapper();
        JsonNode dataObject = mapper.readTree(data);
        //get infor from data
        JsonNode newEmailNode = dataObject.get("newEmail");
        String newEmail = null;
        if (!newEmailNode.isNull()) {
            newEmail = newEmailNode.asText().trim();
        }
        JsonNode confirmEmailNode = dataObject.get("confirmEmail");
        String confirmEmail = null;
        if (!confirmEmailNode.isNull()) {
            confirmEmail = confirmEmailNode.asText().trim();
        }
        //check validate
        Boolean success = false;
        if (newEmail != null && confirmEmail != null
                && !newEmail.isEmpty() && !confirmEmail.isEmpty()
                && PartnerRegistrationValidation.checkEmailRegularIsCorrect(newEmail)
                && PartnerRegistrationValidation.checkEmailRegularIsCorrect(confirmEmail)
                && newEmail.equals(confirmEmail)) {
            //update new email
            success = tripperProfileTripperDAO.updateEmail(tripperID, newEmail);

        } else {
            throw new IllegalArgumentException("Tripper profile - Wrong validate");
        }
        //return
        return "{\"result\": " + success + "}";
    }

    @Override
    @Transactional
    public String getListCard(HttpSession session) throws IOException {
        YTSessionAccount account = YTSession.getAccountSession(session);
        String tripperID = account.getTripperID();
        List<TripperCreditCard> tripperCreditCards = tripperProfileCardDAO.listCards(tripperID);
        for (TripperCreditCard tripperCreditCard : tripperCreditCards) {
            String shownNumber = tripperCreditCard.getShownNumber();
            int st = shownNumber.length() - 4;
            shownNumber = shownNumber.substring(st);
            tripperCreditCard.setShownNumber(shownNumber);
        }
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(tripperCreditCards);
    }

    @Override
    @Transactional
    public String removeCard(String data, HttpSession session) throws IOException {
        YTSessionAccount account = YTSession.getAccountSession(session);
        String tripperID = account.getTripperID();
        //inite object mapper
        ObjectMapper mapper = new ObjectMapper();
        JsonNode dataObject = mapper.readTree(data);
        //get infor from data
        JsonNode cardIDNode = dataObject.get("cardID");
        Long cardID = null;
        if (!cardIDNode.isNull()) {
            cardID = cardIDNode.asLong();
        }
        if (cardID != null) {
            boolean success = tripperProfileCardDAO.removeCard(tripperID, cardID);
            if (success) {
                //return
                return "{\"result\": " + success + "}";
            }
            throw new IllegalArgumentException("wrong tripper Id");
        }
        throw new IllegalArgumentException("Wrong Card Id");

    }

    @Override
    @Transactional
    public String getListBilling(HttpSession session) throws IOException {
        YTSessionAccount account = YTSession.getAccountSession(session);
        String tripperID = account.getTripperID();
        List<TripperBillingAddress> tripperBillings = tripperProfileBillingDAO.listBillings(tripperID);
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(tripperBillings);
    }

    @Override
    @Transactional
    public String removeBilling(String data, HttpSession session) throws IOException {
        YTSessionAccount account = YTSession.getAccountSession(session);
        String tripperID = account.getTripperID();
        //inite object mapper
        ObjectMapper mapper = new ObjectMapper();
        JsonNode dataObject = mapper.readTree(data);
        //get infor from data
        JsonNode billingIDNode = dataObject.get("billingID");
        Long billingID = null;
        if (!billingIDNode.isNull()) {
            billingID = billingIDNode.asLong();
        }
        if (billingID != null) {
            boolean success = tripperProfileBillingDAO.removeBilling(tripperID, billingID);
            if (success) {
                //return
                return "{\"result\": " + success + "}";
            }
            throw new IllegalArgumentException("Fail Removing");
        }
        throw new IllegalArgumentException("Wrong Billing Id");
    }

    @Override
    @Transactional
    public String getBillingDetail(String data, HttpSession session) throws IOException {
        YTSessionAccount account = YTSession.getAccountSession(session);
        String tripperID = account.getTripperID();
        //inite object mapper
        ObjectMapper mapper = new ObjectMapper();
        JsonNode dataObject = mapper.readTree(data);
        //get infor from data
        JsonNode billingIDNode = dataObject.get("billingID");
        Long billingID = null;
        if (!billingIDNode.isNull()) {
            billingID = billingIDNode.asLong();
        }
        if (billingID != null) {
            TripperBillingAddress tripperBilling = tripperProfileBillingDAO.getBillingDetail(billingID, tripperID);
            return mapper.writeValueAsString(tripperBilling);
        }
        throw new IllegalArgumentException("Wrong Billing Id");
    }

    @Override
    @Transactional
    public String loadOptional(HttpSession session) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        YTSessionAccount account = YTSession.getAccountSession(session);
        String tripperID = account.getTripperID();
        Tripper tripper = tripperProfileTripperDAO.getOptionalInformation(tripperID);
        String prefferedLanguageID = tripper.getPreferedLanguageID();
        String background = tripper.getBackground();
        TripperOptional tripperOptional = new TripperOptional();
        tripperOptional.setPreferedLanguageID(prefferedLanguageID);
        tripperOptional.setBackground(background);

        return mapper.writeValueAsString(tripperOptional);
    }

    @Override
    @Transactional
    public String saveOptional(HttpSession session, String data) throws IOException {
        YTSessionAccount account = YTSession.getAccountSession(session);
        String tripperID = account.getTripperID();
        //inite object mapper
        ObjectMapper mapper = new ObjectMapper();
        JsonNode dataObject = mapper.readTree(data);
        //get infor from data
        JsonNode backGroundNode = dataObject.get("backGround");
        String background = null;
        if (!backGroundNode.isNull()) {
            background = backGroundNode.asText();
        }
        //
        JsonNode preferedLanguageIDNode = dataObject.get("preferedLanguageID");
        String preferedLanguageID = null;
        if (!preferedLanguageIDNode.isNull()) {
            preferedLanguageID = preferedLanguageIDNode.asText();
        }
        //update
        boolean success = tripperProfileTripperDAO.updateOptional(tripperID, background, preferedLanguageID);
        if (success) {
            //return
            return "{\"result\": " + success + "}";
        }
        throw new IllegalArgumentException("Update Fail");
    }

}
