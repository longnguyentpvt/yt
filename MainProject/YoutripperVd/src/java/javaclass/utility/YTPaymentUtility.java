/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaclass.utility;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.paypal.api.payments.Amount;
import com.paypal.api.payments.Authorization;
import com.paypal.api.payments.Capture;
import com.paypal.api.payments.Payer;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.PaymentExecution;
import com.paypal.api.payments.RedirectUrls;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.OAuthTokenCredential;
import com.paypal.base.rest.PayPalRESTException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javaclass.common.ytbooking.YTBookingData;
import yt.func.checkout.CheckoutViewMapping;
import yt.func.checkout.javaclass.LinepayPaymentResponseResult;
import yt.func.checkout.javaclass.LinepayRequestingResponse;
import yt.func.checkout.javaclass.PaypalPaymentResponseResult;
import yt.func.checkout.javaclass.PaypalRequestingResponse;
import yt.func.checkout.javaclass.VisaMasterResponseResult;
import yt.javaclass.config.YTDataConfiguration;

/**
 *
 * @author nickn
 */
public class YTPaymentUtility {

    private static final String PAYMENT_2C2P_PAN_COUNTRY = "TH";
    private static final String PAMENT_2C2P_VERSION = "9.0";

    public static final String PAYPAL_RESPONSE_PARAM_STT = "stt";
    public static final String PAYPAL_RESPONSE_PARAM_ORDER_NO = "on";

    public static final String LINE_RESPONSE_PARAM_ORDER_NO = "on";

    public static PaypalRequestingResponse generatePaypalRequest(String orderNo, String currencyCode, BigDecimal total) throws PayPalRESTException {
        String paypalMode = YTDataConfiguration.getPAYPAL_MODE();
        String clientID = YTDataConfiguration.getPAYPAL_CLIENT_ID();
        String clientSecret = YTDataConfiguration.getPAYPAL_CLIENT_SECRET();

        Map<String, String> sdkConfig = new HashMap<>();
        sdkConfig.put("mode", paypalMode);
        String accessToken = new OAuthTokenCredential(clientID, clientSecret, sdkConfig).getAccessToken();
        APIContext apiContext = new APIContext(accessToken);
        apiContext.setConfigurationMap(sdkConfig);

        Amount amount1 = new Amount();
        String totalStr = YTPriceUtility.convertBigDecimalToPaymentFormat(total);
        amount1.setCurrency(currencyCode);
        amount1.setTotal(totalStr);

        Transaction transaction = new Transaction();
        String desc = "Booking Code: " + orderNo + " - " + totalStr;
        transaction.setDescription(desc);
        transaction.setAmount(amount1);

        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction);

        Payer payer = new Payer();
        payer.setPaymentMethod("paypal");

        Payment payment = new Payment();
        payment.setIntent("authorize");
        payment.setPayer(payer);
        payment.setTransactions(transactions);

        RedirectUrls redirectUrls = new RedirectUrls();
        String successStt = PAYPAL_RESPONSE_PARAM_STT + "=1";
        String failStt = PAYPAL_RESPONSE_PARAM_STT + "=0";
        String onParam = PAYPAL_RESPONSE_PARAM_ORDER_NO + "=" + orderNo;
        String baseURL = YTDataConfiguration.getYTDomain() + "/" + CheckoutViewMapping.PAYPAY_PAYMENT_RESPONSE_URL + "?";
        String successURL = baseURL + onParam + "&" + successStt;
        String cancelURL = baseURL + onParam + "&" + failStt;
        redirectUrls.setCancelUrl(cancelURL);
        redirectUrls.setReturnUrl(successURL);

        payment.setRedirectUrls(redirectUrls);

        Payment createdPayment = payment.create(apiContext);
        String redirectLink = createdPayment.getLinks().get(1).getHref();
        String paymentID = createdPayment.getId();
        PaypalRequestingResponse rp = new PaypalRequestingResponse(redirectLink, paymentID);
        return rp;

//        ObjectMapper mapper = new ObjectMapper();
//        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
//        String json = gson.toJson(createdPayment);
//        String json = mapper.writeValueAsString(createdPayment);
//        JsonParser parser = new JsonParser();
//        JsonObject jObject = parser.parse(json).getAsJsonObject();
//        JsonArray jArray = jObject.getAsJsonArray("links");
//        return jArray.get(1).getAsJsonObject().get("href").getAsString();
    }

    public static PaypalPaymentResponseResult processPaypalResponse(String paymentID, String payerID, String orderNo) throws PayPalRESTException {
        String paypalMode = YTDataConfiguration.getPAYPAL_MODE();
        String clientID = YTDataConfiguration.getPAYPAL_CLIENT_ID();
        String clientSecret = YTDataConfiguration.getPAYPAL_CLIENT_SECRET();

        Map<String, String> sdkConfig = new HashMap<>();
        sdkConfig.put("mode", paypalMode);
        String accessToken = new OAuthTokenCredential(clientID, clientSecret, sdkConfig).getAccessToken();
        APIContext apiContext = new APIContext(accessToken);
        apiContext.setConfigurationMap(sdkConfig);

        Payment payment = new Payment();
        payment.setId(paymentID);

        PaymentExecution paymentExecute = new PaymentExecution();
        paymentExecute.setPayerId(payerID);

        //get transaction from response
        Payment payment2 = payment.execute(apiContext, paymentExecute);
        Transaction transaction = payment2.getTransactions().get(0);
        Authorization authorization = transaction.getRelatedResources().get(0).getAuthorization();
        Amount amount = transaction.getAmount();

        // Set as final capture amount
        Capture capture = new Capture();
        capture.setAmount(amount);
        capture.setIsFinalCapture(true);
        Capture responseCapture = authorization.capture(apiContext, capture);
        String paymentStatus = responseCapture.getState();
        boolean success = paymentStatus.equals("completed");
        PaypalPaymentResponseResult rs = new PaypalPaymentResponseResult(success, paymentStatus);
        return rs;
    }

    public static String generate2c2pRequest(String encryptCreditCard, String holderName, BigDecimal total, String currencyCode, String orderNo) throws NoSuchAlgorithmException,
            InvalidKeyException {

        // generate total in 2c2p format
        String totalStr = YTPriceUtility.convertBigDecimalToPaymentFormat(total);
        String amtStr = totalStr.replace(".", "");
        String amt = "000000000000" + amtStr;
        amt = amt.substring(amtStr.length());

        // generate 2c2p setting
        String desc = "Booking Code: " + orderNo + " - " + totalStr;

        String merchant = YTDataConfiguration.getMERCHANT_2C2P();

        String strToHash = merchant + orderNo + amt;
        String secureHash = DataEncodingUtility.calculateRFC2104HMAC(strToHash, YTDataConfiguration.getSECRET_2C2P());

        if (encryptCreditCard == null) {
            encryptCreditCard = "";
        }

        String isoCurrency = YTBookingData.getVisaMasterCurrencyCode(currencyCode);
        String requestFinal = "<PaymentRequest>"
                + "<version>" + PAMENT_2C2P_VERSION + "</version>"
                + "<merchantID>" + merchant + "</merchantID>"
                + "<uniqueTransactionCode>" + orderNo + "</uniqueTransactionCode>"
                + "<desc>" + desc + "</desc>"
                + "<amt>" + amt + "</amt>"
                + "<currencyCode>" + isoCurrency + "</currencyCode>"
                + "<panCountry>" + PAYMENT_2C2P_PAN_COUNTRY + "</panCountry>"
                + "<cardholderName>" + holderName + "</cardholderName>"
                + "<hashValue>" + secureHash + "</hashValue>"
                + "<encCardData>" + encryptCreditCard + "</encCardData>"
                + "</PaymentRequest>";

        byte[] encodedBytes = Base64.getEncoder().encode(requestFinal.getBytes());
        requestFinal = new String(encodedBytes);

        return requestFinal;
    }

    public static VisaMasterResponseResult verifyPaymentResponse(String response) throws Exception {
        String xml = DataEncodingUtility.decryptPaymentResponse(response);
        XmlMapper xmlMapper = new XmlMapper();
        JsonNode jsonNode = xmlMapper.readTree(xml);

        String stt = jsonNode.get("status").asText();
        boolean success = stt.equalsIgnoreCase("A");
        String orderNo = jsonNode.get("uniqueTransactionCode").asText();

        VisaMasterResponseResult result = new VisaMasterResponseResult();
        result.setSuccess(success);
        result.setOrderNo(orderNo);
        result.setResponse(xml);

        return result;
    }

    public static LinepayRequestingResponse generateLinepayRequest(String orderNo, BigDecimal total, String currencyCode) throws IOException {
        String pmURL = YTDataConfiguration.getLINE_PAYMENT_URL() + "v2/payments/request";
        URL url = new URL(pmURL);
        HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
        httpCon.setDoOutput(true);
        httpCon.setRequestMethod("POST");
        httpCon.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
        httpCon.setRequestProperty("X-LINE-ChannelId", YTDataConfiguration.getLINE_CHANNEL_ID());
        httpCon.setRequestProperty("X-LINE-ChannelSecret", YTDataConfiguration.getLINE_SECRET());
        String totalStr = YTPriceUtility.convertBigDecimalToPaymentFormat(total);

        String orderNoParam = LINE_RESPONSE_PARAM_ORDER_NO + "=" + orderNo;
        String returnURL = YTDataConfiguration.getYTDomain() + "/" + CheckoutViewMapping.LINEPAY_PAYMENT_RESPONSE_URL + "?" + orderNoParam;

        String rawData = "{\"amount\": " + totalStr + ",\"confirmUrl\":"
                + " \"" + returnURL + "\",\"productImageUrl\": \"https://pgw-static.s3.amazonaws.com/images/merchantlogo/764764000000516.png?v=170922174556\","
                + "\"productName\": \"Order No. " + orderNo + "\","
                + "\"orderId\": \"" + orderNo + "\",\"currency\": \"" + currencyCode + "\"}";
        OutputStream os = httpCon.getOutputStream();
        os.write(rawData.getBytes());
        os.flush();
        OutputStreamWriter out = new OutputStreamWriter(
                httpCon.getOutputStream());

        StringBuffer text = new StringBuffer();
        InputStreamReader in = new InputStreamReader((InputStream) httpCon.getContent());
        BufferedReader buff = new BufferedReader(in);
        String line;
        do {
            line = buff.readLine();
            if (line != null) {
                text.append(line);
            }

        } while (line != null);
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String jsonStr = text.toString();
        System.out.println("str " + jsonStr);
        JsonObject jsonObject = gson.fromJson(jsonStr, JsonObject.class);
        JsonObject infoNode = jsonObject.getAsJsonObject("info");
        String urlpayment = infoNode.getAsJsonObject("paymentUrl").get("web").getAsString();
        String linepayID = infoNode.get("transactionId").getAsString();
        out.close();

        LinepayRequestingResponse rp = new LinepayRequestingResponse(urlpayment, linepayID);
        return rp;
    }

    public static LinepayPaymentResponseResult processLinepayResponse(String transactionID, BigDecimal total, String currencyCode) throws IOException {
        String pmURL = YTDataConfiguration.getLINE_PAYMENT_URL() + "v2/payments/" + transactionID + "/confirm";
        URL url = new URL(pmURL);
        HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
        httpCon.setDoOutput(true);
        httpCon.setRequestMethod("POST");
        httpCon.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
        httpCon.setRequestProperty("X-LINE-ChannelId", YTDataConfiguration.getLINE_CHANNEL_ID());
        httpCon.setRequestProperty("X-LINE-ChannelSecret", YTDataConfiguration.getLINE_SECRET());
        String totalStr = YTPriceUtility.convertBigDecimalToPaymentFormat(total);

        String rawData = "{\"amount\":" + totalStr + ",\"currency\": \"" + currencyCode + "\"}";
        OutputStream os = httpCon.getOutputStream();
        os.write(rawData.getBytes());
        os.flush();
        OutputStreamWriter out = new OutputStreamWriter(
                httpCon.getOutputStream());

        StringBuffer text = new StringBuffer();
        InputStreamReader in = new InputStreamReader((InputStream) httpCon.getContent());
        BufferedReader buff = new BufferedReader(in);
        String line;
        do {
            line = buff.readLine();
            if (line != null) {
                text.append(line);
            }
        } while (line != null);
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String jsonStr = text.toString();
        JsonObject jsonObject = gson.fromJson(jsonStr, JsonObject.class);
        String paymentStatus = jsonObject.get("returnCode").getAsString();
        out.close();

        boolean success = paymentStatus.equals("0000");
        LinepayPaymentResponseResult result = new LinepayPaymentResponseResult(jsonStr, success);
        return result;
    }

}
