/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaclass.utility;

import java.util.Random;
import org.springframework.util.DigestUtils;

/**
 *
 * @author nickn
 */
public class YTTokenUtility {

    private static final int REGITRATION_RANDOM_LENGTH = 10;
    private static final int REMEMBER_RANDOM_LENGTH = 10;
    private static final String RANDOM_ALLOWED_CHARACTERS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static String generateRegistrationToken(String email) {
        // random a registration code
        Random rnd = new Random();
        StringBuilder sb;
        sb = new StringBuilder(REGITRATION_RANDOM_LENGTH);
        for (int i = 0; i < REGITRATION_RANDOM_LENGTH; i++) {
            sb.append(RANDOM_ALLOWED_CHARACTERS.charAt(rnd.nextInt(RANDOM_ALLOWED_CHARACTERS.length())));
        }
        String code = sb.toString();
        // generate email unique token
        String emailTime = email + "-" + YTDateTimeUtility.getCurrentTimeUnderDdMMyyyyHHmmss() + "-" + code;

        // md5
        emailTime = DigestUtils.md5DigestAsHex(emailTime.getBytes());
        return emailTime;
    }

    public static String generateRememberToken(String id) {
        // random a remember code
        Random rnd = new Random();
        StringBuilder sb;
        sb = new StringBuilder(REMEMBER_RANDOM_LENGTH);
        for (int i = 0; i < REMEMBER_RANDOM_LENGTH; i++) {
            sb.append(RANDOM_ALLOWED_CHARACTERS.charAt(rnd.nextInt(RANDOM_ALLOWED_CHARACTERS.length())));
        }
        String code = sb.toString();

        // generate remember unique
        String remember = id + "-" + YTDateTimeUtility.getCurrentTimeUnderDdMMyyyyHHmmss() + "-" + code;

        // md5
        remember = DigestUtils.md5DigestAsHex(remember.getBytes());
        return remember;
    }
}
