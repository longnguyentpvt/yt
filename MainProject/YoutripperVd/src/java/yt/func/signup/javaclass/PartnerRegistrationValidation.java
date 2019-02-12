/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.func.signup.javaclass;

import javaclass.utility.RegularExpressionValidatorUtility;

/**
 *
 * @author nickn
 */
public class PartnerRegistrationValidation {

    public static boolean checkPhoneNumberRegularIsCorrect(String phoneNumber) {
        return !phoneNumber.isEmpty() && RegularExpressionValidatorUtility.isNumberOnly(phoneNumber);
    }

    /**
     * This method is to check IDNumbe length is not over maximum length
     *
     * @param idNumber string of idNumber
     * @return true if the length is correct
     */
    public static boolean checkIDNumberRegularIsCorect(String idNumber) {
        // regular first
        if (RegularExpressionValidatorUtility.isLetterAndNumberOnly(idNumber)) {
            if (idNumber.length() <= 15 || idNumber.length() >= 1) {
                // then check length
                return true;
            }
        }
        return false;
    }

    /**
     * This method is to check TaxID length is not over maximum length
     *
     * @param taxID string of taxID
     * @return true if the length is correct
     */
    public static boolean checkTaxIDRegularIsCorect(String taxID) {
        // regular first
        if (RegularExpressionValidatorUtility.isNumberOnly(taxID)) {
            if (taxID.length() <= 13 && taxID.length() >= 1) {
                // then check length
                return true;
            }
        }
        return false;
    }

    /**
     * This method is to check password length is not over maximum length
     *
     * @param password string of password
     * @return true if the length is correct
     */
    public static boolean checkPasswordRegularIsCorrect(String password) {

        if (RegularExpressionValidatorUtility.isCorrectPassword(password)) {
            //check length
            if (password.length() >= 8) {
                return true;
            }
        }

        return false;
    }

    /**
     * This method is to check idName length is not over maximum length
     *
     * @param idName string of idName
     * @return true if the length is correct
     */
    public static boolean checkIDNameRegularIsCorect(String idName) {
        // regular first
        if (RegularExpressionValidatorUtility.isLetterAndNumberOnly(idName)) {
            if (idName.length() <= 200 || idName.length() >= 2) {
                // then check length
                return true;
            }
        }
        return false;
    }

    /**
     * This method is to check email length is not over maximum length
     *
     * @param email string of email
     * @return true if the length is correct
     */
    public static boolean checkEmailRegularIsCorrect(String email) {
        // check email form
        if (RegularExpressionValidatorUtility.isCorrectEmail(email)) {
            // then check length
            if (email.length() <= 255 && !email.isEmpty()) {
                return true;
            }
        }
        return false;
    }

    /**
     * This method is to check display Name length is not over maximum length
     *
     * @param displayName string of display Name
     * @return true if the length is correct
     */
    public static boolean checkDisplayNameRegularIsCorrect(String displayName) {
        while (displayName.contains("  ")) {
            displayName = displayName.replaceAll("  ", " ");
        }
        // check regular expression
        if (RegularExpressionValidatorUtility.isLetterAndNumberOnly(displayName)) {
            // check length
            // Khang Preview - 03/03/2017 - nhập max 20 kí tự là văng lỗi chắc
            if (!displayName.isEmpty() && displayName.length() <= 30 && displayName.length() > 4) {
                return true;
            }
        }
        return false;
    }

    /**
     * This method is to check position length is not over maximum length
     *
     * @param position string of position
     * @return true if the length is correct
     */
    public static boolean checkPositionRegularIsCorrect(String position) {
        // check regular expression
        if (RegularExpressionValidatorUtility.isLetterAndNumberOnly(position)) {
            // check length
            if (!position.isEmpty() && position.length() <= 20) {
                return true;
            }
        }
        return false;
    }
}
