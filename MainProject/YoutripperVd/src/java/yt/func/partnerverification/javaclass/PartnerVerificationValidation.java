/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.func.partnerverification.javaclass;

import javaclass.utility.RegularExpressionValidatorUtility;

/**
 *
 * @author Hiep
 */
public class PartnerVerificationValidation {

    public static boolean checkBankAccountNameRegularIsCorrect(String bankAccountkName) {
        if (!bankAccountkName.isEmpty() && bankAccountkName.length() <= 200) {
            return true;
        }
        return false;
    }

    public static boolean checkBankAccountNumberRegularIsCorrect(String bankAccountNumber) {
        if (!bankAccountNumber.isEmpty() && bankAccountNumber.length() <= 50
                && RegularExpressionValidatorUtility.isNumberOnly(bankAccountNumber)) {
            return true;
        }
        return false;
    }

}
