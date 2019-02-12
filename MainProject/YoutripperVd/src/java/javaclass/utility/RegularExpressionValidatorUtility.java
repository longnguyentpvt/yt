/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaclass.utility;

/**
 *
 * @author nickn
 */
public class RegularExpressionValidatorUtility {

    private static final String LETTER_NUMBER_REGEX = "^[a-zA-Z0-9\\s]*$";
    private static final String NUMBER_REGEX = "^[0-9]*$";
    private static final String EMAIL_REGEX
            = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
    private static final String PASSWORD_REGEX = "^[a-zA-Z0-9#?!@$%^&*-]*$";
    private static final String ADDRESS_REGEX = "^[a-zA-Z0-9,.-//-\\\\-\\s]*$";
    private static final String POSTCODE_REGEX = "^[a-zA-Z0-9\\-\\s]*$";

    /**
     * This method is to check a string whether or not contains only letter and
     * number.
     *
     * @param text a string, it could be null string
     * @return true if the text is a string and contains letters and numbers
     * only
     */
    public static boolean isLetterAndNumberOnly(String text) {
        if (text != null && !text.isEmpty()) {
            return text.matches(LETTER_NUMBER_REGEX);
        }
        return false;
    }

    /**
     * This method is to check a string whether or not contains only numbers.
     *
     * @param text a string, it could be null string
     * @return true if the text is a string and contains only numbers
     */
    public static boolean isNumberOnly(String text) {
        if (text != null && !text.isEmpty()) {
            return text.matches(NUMBER_REGEX);
        }
        return false;
    }

    /**
     * This method is to check a string whether or not correct form of address
     *
     * @param text a string, it could be null string
     * @return true if the text is correct form of address.
     */
    public static boolean isCorrectAddress(String text) {
        if (text != null && !text.isEmpty()) {
            return text.matches(ADDRESS_REGEX);
        }
        return false;
    }

    /**
     * This method is to check a string whether or not correct form of email
     *
     * @param text a string, it could be null string
     * @return true if the text is correct form of email.
     */
    public static boolean isCorrectEmail(String text) {
        text = text.toLowerCase();
        if (text != null && !text.isEmpty()) {
            return text.matches(EMAIL_REGEX);
        }
        return false;
    }

    /**
     * This method is to check a string whether or not correct form of password
     *
     * @param text a string, it could be null string
     * @return true if the text is correct form of password.
     */
    public static boolean isCorrectPassword(String text) {
        if (text != null && !text.isEmpty()) {
            return text.matches(PASSWORD_REGEX);
        }
        return false;
    }

    /**
     * This method is to check a string whether or not correct form of password
     *
     * @param text a string, it could be null string
     * @return true if the text is correct form of password.
     */
    public static boolean isCorrectPostCode(String text) {
        if (text != null && !text.isEmpty()) {
            return text.matches(POSTCODE_REGEX);
        }
        return false;
    }
}
