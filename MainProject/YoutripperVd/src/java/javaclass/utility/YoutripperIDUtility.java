/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaclass.utility;

import com.amazonaws.util.Md5Utils;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javaclass.common.YTPartnerData;
import org.joda.time.DateTime;
import org.springframework.util.DigestUtils;

/**
 *
 * @author nickn
 */
public class YoutripperIDUtility {

    private static final int PARTNER_ID_LENGTH = 10;
    private static final String BUSINESS_TYPE_PERSONAL_SIGNATURE = "I";
    private static final String BUSINESS_TYPE_COMPANY_SIGNATURE = "S";

    private static final int TRIPPER_ID_LENGTH = 10;
    private static final String TRIPPER_ID_SIGNATURE = "T";

    private static final int PACKAGE_ID_LENGTH = 10;
    private static final String REGULAR_PACKAGE_SYMBOL = "RP";
    private static final String OPEN_TIMED_PACKAGE_SYMBOL = "OP";

    private static final String RANDOM_CHARACTERS = "ABCDEFGHIJKLMNPQRSTUVWXYZ";

    public static String generatePartnerID(long partnerNo, String businessType) {
        //create partner ID String
        String partnerID = null;
        String businessCode = null;
        if (businessType.equals(YTPartnerData.BUSINESS_TYPE_PERSONAL)) {
            businessCode = BUSINESS_TYPE_PERSONAL_SIGNATURE;
        } else if (businessType.equals(YTPartnerData.BUSINESS_TYPE_COMPANY)) {
            businessCode = BUSINESS_TYPE_COMPANY_SIGNATURE;
        }

        if (businessCode != null) {
            //calculate the next ID
            String partnerIDInStr = partnerNo + "";

            //create time object
            DateTime now = new DateTime();
            String yy = now.toString("yy");
            partnerID = businessCode + yy;
            int noLefts = PARTNER_ID_LENGTH - partnerID.length() - partnerIDInStr.length();
            // fill gap by 0
            for (int i = 0; i < noLefts; i++) {
                partnerID += "0";
            }
            partnerID += partnerIDInStr;
        }
        return partnerID;
    }

    public static String generateTripperID(long tripperNo) {
        //create partner ID String
        String tripperID = null;

        //calculate the next ID
        String tripperNumberStr = tripperNo + "";

        //create time object
        DateTime now = new DateTime();
        String yy = now.toString("yy");
        tripperID = TRIPPER_ID_SIGNATURE + yy;
        int noLefts = TRIPPER_ID_LENGTH - tripperID.length() - tripperNumberStr.length();
        // fill gap by 0
        for (int i = 0; i < noLefts; i++) {
            tripperID += "0";
        }
        tripperID += tripperNumberStr;

        return tripperID;
    }

    public static String generatePackageID(long packageNumber, boolean isOpenTimed) {
        // construct packageID
        String packageTypeSymbol = isOpenTimed ? OPEN_TIMED_PACKAGE_SYMBOL : REGULAR_PACKAGE_SYMBOL;

        String packageID = "" + packageTypeSymbol;
        String packageNumberStr = packageNumber + "";

        // fill to full length
        int noLefts = PACKAGE_ID_LENGTH - packageNumberStr.length() - packageID.length();
        for (int i = 0; i < noLefts; i++) {
            packageID += "0";
        }
        packageID += packageNumberStr;
        return packageID;
    }

    public synchronized static String generatBookingTransactionID(long transactionTime) throws InterruptedException {
        Thread.sleep(5);
        String ct = YTDateTimeUtility.getCurrentTimeInMilli() + "";
        String id = "ybt" + transactionTime + ct;
        return DigestUtils.md5DigestAsHex(id.getBytes());
    }

    public static String generateBookingOrderNo(long orderID, DateTime bookingDate) {
        int year = bookingDate.getYear();
        String yy = year + "";
        yy = yy.substring(2);

        String idStr = orderID + "";
        String reNo = "00000" + orderID;
        reNo = reNo.substring(idStr.length());

        int rdL = 3;
        Random rnd = new Random();
        StringBuilder sb;
        sb = new StringBuilder(rdL);
        for (int i = 0; i < rdL; i++) {
            sb.append(RANDOM_CHARACTERS.charAt(rnd.nextInt(RANDOM_CHARACTERS.length())));
        }
        String rdStr = sb.toString();

        return yy + reNo + rdStr;
    }

    public static String generateInvoiceID(long invoiceID, DateTime gDT) {
        int year = gDT.getYear();
        String yy = year + "";
        yy = yy.substring(2);

        int month = gDT.getMonthOfYear();
        String mm = (month < 10 ? "0" : "") + month;

        String ivID = invoiceID + "";
        int min = 7, cl = ivID.length();
        for (int i = cl; i < min; i++) {
            ivID = "0" + ivID;
        }

        ivID = yy + mm + "-" + ivID;
        return ivID;
    }

    public static synchronized String generateLineTransactionID() {
        try {
            Thread.sleep(3);
        } catch (InterruptedException ex) {
            return null;
        }
        long crTime = YTDateTimeUtility.getCurrentTimeInMilli();
        String timeid = crTime + 17l + "";
        String tid = DigestUtils.md5DigestAsHex(timeid.getBytes());
        return tid;
    }
}
