/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaclass.utility;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javaclass.common.OrderPaymentInfo;
import javaclass.common.ytbooking.YTBookingData;

/**
 *
 * @author nickn
 */
public class YTPriceUtility {

    private final static DecimalFormat SHOWING_PRICE_FORMAT = new DecimalFormat("#,###,##0.00");
    private final static DecimalFormat PAYMENT_PRICE_FORMAT = new DecimalFormat("0.00");

    private static final BigDecimal PAYPAL_CONVERSATION_RATE_PERCENT = new BigDecimal("0.88");
    private static final BigDecimal NORMAL_CONVERSATION_RATE_PERCENT = new BigDecimal("0.94");

    private static final Map<String, String> CURRENCY_SYM_HM;

    static {
        Map<String, String> aMap = new HashMap<>();
        aMap.put("THB", "฿");
        aMap.put("EUR", "€");
        aMap.put("USD", "$");
        aMap.put("CNY", "¥");
        CURRENCY_SYM_HM = Collections.unmodifiableMap(aMap);
    }

    public static BigDecimal getPaidPrice(BigDecimal price, int discountPercent, boolean rounded) {
        if (price != null) {
            BigDecimal finalPrice = price.add(BigDecimal.ZERO);
            if (finalPrice.compareTo(BigDecimal.ZERO) > 0) {
                if (discountPercent > 0) {
                    BigDecimal aPercent = new BigDecimal(100 - discountPercent);
                    finalPrice = finalPrice.multiply(aPercent).divide(new BigDecimal(100));

                    if (rounded) {
                        finalPrice = finalPrice.setScale(0, BigDecimal.ROUND_HALF_UP);
                    }

                    finalPrice = finalPrice.setScale(2, BigDecimal.ROUND_HALF_UP);
                }
            }
            return finalPrice;
        }
        return BigDecimal.ZERO;
    }

    public static int getPromotionPercent(BigDecimal price, BigDecimal promotionPrice) {
        if (price != null && promotionPrice != null && promotionPrice.compareTo(price) < 0) {
            return BigDecimal.ONE.subtract(promotionPrice.divide(price, 2, BigDecimal.ROUND_FLOOR)).multiply(new BigDecimal(100)).intValue();
        }
        return 0;
    }

    public static String convertBigDecimalToPriceFormat(BigDecimal price) {
        return SHOWING_PRICE_FORMAT.format(price);
    }

    public static String convertBigDecimalToPaymentFormat(BigDecimal total) {
        return PAYMENT_PRICE_FORMAT.format(total);
    }

    public static BigDecimal getDiscountPriceByPercent(BigDecimal packagePrice, int dcP, boolean rounded) {
        BigDecimal finalPrice = packagePrice.add(BigDecimal.ZERO);
        if (dcP > 0) {
            BigDecimal aPercent = new BigDecimal(dcP);
            finalPrice = finalPrice.multiply(aPercent).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP);
            if (rounded) {
                finalPrice = finalPrice.setScale(0, BigDecimal.ROUND_HALF_UP);
            }
        }
        return finalPrice;
    }

    public static OrderPaymentInfo getPaymentInfo(BigDecimal total, BigDecimal exchangeRate, String paymentMethod, BigDecimal commissionRate) {
        BigDecimal finalRate = exchangeRate.add(BigDecimal.ZERO);
        BigDecimal paymentRate = null;
        if (finalRate.compareTo(BigDecimal.ONE) != 0) {
            if (paymentMethod.equals(YTBookingData.PAYPAL_METHOD)) {
                paymentRate = PAYPAL_CONVERSATION_RATE_PERCENT.add(BigDecimal.ZERO);
            } else {
                paymentRate = NORMAL_CONVERSATION_RATE_PERCENT.add(BigDecimal.ZERO);
            }

            finalRate = finalRate.multiply(paymentRate).setScale(2, BigDecimal.ROUND_HALF_UP);
        }

        BigDecimal amount = total.multiply(finalRate).setScale(2, BigDecimal.ROUND_HALF_UP);
        BigDecimal ytCommission = amount.multiply(commissionRate).setScale(2, BigDecimal.ROUND_HALF_UP);
        BigDecimal partnerPayable = amount.subtract(ytCommission);

        OrderPaymentInfo info = new OrderPaymentInfo(paymentRate, finalRate, amount, ytCommission, partnerPayable);
        return info;
    }

    public static BigDecimal[] getVATInfo(int vatPercent, BigDecimal amount) {
        BigDecimal amountExVAT = amount.add(BigDecimal.ZERO);
        BigDecimal aPercent = new BigDecimal(100 + vatPercent);

        amountExVAT = amountExVAT.multiply(new BigDecimal(100)).divide(aPercent, 2, BigDecimal.ROUND_HALF_UP);
        BigDecimal vat = amount.subtract(amountExVAT);
        BigDecimal[] info = {amountExVAT, vat};
        return info;
    }
    
    public static String getCurrencySymbol(String currencyCode) {
        if (CURRENCY_SYM_HM.containsKey(currencyCode)) {
            return CURRENCY_SYM_HM.get(currencyCode);
        }
        throw new IllegalArgumentException("Currency Code does not exist!");
    }
}
