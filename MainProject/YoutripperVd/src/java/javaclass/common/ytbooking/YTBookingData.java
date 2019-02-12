/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaclass.common.ytbooking;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author nickn
 */
public class YTBookingData {

    public final static String PAYPAL_METHOD = "paypal";
    public final static String LINE_METHOD = "line";
    public final static String VISA_MASTER_METHOD = "2c2p";

    public static final String PAYMENT_STATUS_SUCCESS = "success";
    public static final String PAYMENT_STATUS_FAIL = "fail";
    public static final String PAYMENT_STATUS_RESERVED = "reserved";
    public static final String PAYMENT_STATUS_EXPIRED = "expired";
    public static final int RESERVED_INTERVAL = 20;

    private final static BigDecimal YT_COMMISSION_RATE = new BigDecimal("0.04");
    public final static int VAT_PERCENT = 7;

    private static final Map<String, String> VISA_MASTER_CURRENCY_CODE;

    public static final String DISCOUNT_CODE_TYPE_EVERY_PACKAGE = "ep";
    public static final String DISCOUNT_CODE_TYPE_SPECIFIC_PACKAGE = "spkg";
    public static final String DISCOUNT_CODE_TYPE_SPECIFIC_PARTNER = "spn";
    public static final String DISCOUNT_CODE_TYPE_SPECIFIC_CATEGORY = "scat";
    public static final String DISCOUNT_CODE_TYPE_SPECIFIC_SUB_CATEGORY = "sscat";

    static {
        Map<String, String> codes = new HashMap<>();
        codes.put("THB", "764");
        codes.put("USD", "840");
        codes.put("EUR", "978");
        codes.put("CNY", "156");
        VISA_MASTER_CURRENCY_CODE = Collections.unmodifiableMap(codes);
    }

    public static String getVisaMasterCurrencyCode(String currencyCode) {
        String isoCode = VISA_MASTER_CURRENCY_CODE.get(currencyCode);
        if (isoCode != null) {
            return isoCode;
        }
        throw new IllegalArgumentException("Currency does not exist!");
    }

    public static BigDecimal getYT_COMMISSION_RATE() {
        return YT_COMMISSION_RATE.add(BigDecimal.ZERO);
    }

}
