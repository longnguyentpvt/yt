/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaclass.common;

/**
 *
 * @author nickn
 */
public class YTPartnerData {

    public final static String BUSINESS_TYPE_PERSONAL = "personal";
    public final static String BUSINESS_TYPE_COMPANY = "company";

    public final static String JOP_POSITION_BUSINESS_OWNER = "buOwner";
    public final static String JOP_POSITION_SALE_MAGAGER = "saManager";
    public final static String JOP_POSITION_MARKETING_MAGAGER = "maManager";
    public final static String JOP_POSITION_GENERAL_MAGAGER = "geManager";
    public final static String JOP_POSITION_GENERAL_OFFICER = "geOfficer";
    public final static String JOP_POSITION_OTHERS = "others";

    public final static String ACCOUNT_TYPE_YT = "yt";
    public final static String ACCOUNT_STATUS_NON_ACTIVED = "n-actived";
    public final static String ACCOUNT_STATUS_ACTIVED = "actived";
    public final static String ACCOUNT_STATUS_DELETED = "deleted";

    public final static String ACCOUNT_VERIFICATION_STATUS_NOT_UPLOADED = "n-u";
    public final static String ACCOUNT_VERIFICATION_STATUS_PENDING = "pending";
    public final static String ACCOUNT_VERIFICATION_STATUS_APPROVED = "approved";
    public final static String ACCOUNT_VERIFICATION_STATUS_FAIL = "fail";

    public final static String ACCOUNT_BANK_STATUS_NOT_UPLOADED = "n-u";
    public final static String ACCOUNT_BANK_STATUS_PENDING = "pending";
    public final static String ACCOUNT_BANK_STATUS_APPROVED = "approved";
    public final static String ACCOUNT_BANK_STATUS_FAIL = "fail";

    public final static String PARTNER_DELETED_ACCOUNT_ID = "DP00000000";

    public final static String PARTNER_FILE_UPLOADED = "uld";
    public final static String PARTNER_FILE_DELETED = "dlt";

    private final static String[] PARTNER_BANK_CODES = {"bk", "ktb", "boa", "kskb", "knkb", "ctb", "tmb", "tiscb", "bt", "scb", "tb", "sacb", "uob", "scbt", "micb", "aslb", "sbot", "aac", "eibt", "gsb", "ghb", "ibt"};

    public static String[] getPARTNER_BANK_CODES() {
        int len = PARTNER_BANK_CODES.length;
        String[] codes = new String[len];
        for (int i = 0; i < len; i++) {
            codes[i] = PARTNER_BANK_CODES[i];
        }
        return codes;
    }

}
