/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaclass.common;

import com.maxmind.geoip2.DatabaseReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nickn
 */
public class YTData {

    public final static String PARTNER_ROOT_PATH = "/partner";

    public final static String LOCALE_ENGLISH_CODE = "en";
    public final static String LOCALE_ENGLISH_ALTERNATE_CODE = "en";
    public final static String LOCALE_THAI_CODE = "th";
    public final static String LOCALE_THAI_ALTERNATE_CODE = "th-th";
    public final static String LOCALE_THAI_PATH = "/th";
    public final static String PARTNER_PATH = "/partner";
    public final static String TRIPPER_PATH = "/tripper";
    public final static String RESOURCE_URL_START_PATH = "/Resources/";

    public final static String DELETED_COUNTRY_ID = "DEL";

    public final static int ALIVE_DAYS_REMEMBER_TOKEN = 30;

    public final static String DEFAULT_CURRENCY_CODE = "THB";

    public final static String CDN_IMAGE_TEMP_FOLDER = "temp";
    public final static String CDN_ACTIVITY_FOLDER = "youtripper-activity";
    public final static String CDN_ACTIVITY_REGISTRATION_FOLDER = "activity-registration";
    public final static String CDN_PARTNER_VERIFICATION_FOLDER = "pv";
    public final static String CDN_PARTNER_BANK_FOLDER = "pb";
    public final static String CDN_PARTNER_CERTIFICATE_FOLDER = "pc";
    public final static String CDN_PARTNER_PROFILE_FOLDER = "pf";
    public final static String CDN_INVOICE_FOLDER = "iv";
    public final static String CDN_BOOKED_ACTIVITY = "temp-activity";

    public final static int ACTIVITY_COVER_WIDTH = 1920;
    public final static int ACTIVITY_COVER_HEIGHT = 590;

    public final static int ACTIVITY_MAIN_THUMB_WIDTH = 520;
    public final static int ACTIVITY_MAIN_THUMB_HEIGHT = 780;

    public final static String TRIPPER_VERIFICATION_EMAIL_FUNCTION_CODE = "TVE";
    public final static String PARTNER_VERIFICATION_EMAIL_FUNCTION_CODE = "PVE";

    public static DatabaseReader MAXMIND_COUNTRY_DB;
    public static DatabaseReader MAXMIND_CITY_DB;

    public static final String[] PHONE_CODES = new String[]{"1", "1242", "1246", "1264", "1268", "1284", "1340", "1345", "1441", "1473", "1649", "1664", "1670", "1671", "1684", "1758", "1767", "1784", "1787", "1809", "1868", "1869", "1876", "20", "211", "212", "213", "216", "218", "220", "221", "222", "223", "224", "225", "226", "227", "228", "229", "230", "231", "232", "233", "234", "235", "236", "237", "238", "239", "240", "241", "242", "244", "245", "246", "248", "249", "250", "251", "252", "253", "254", "255", "256", "257", "258", "260", "261", "262", "263", "264", "265", "266", "267", "268", "269", "27", "290", "291", "297", "298", "299", "30", "31", "32", "33", "34", "350", "351", "352", "353", "354", "355", "356", "357", "358", "359", "36", "370", "371", "372", "373", "374", "375", "376", "377", "378", "380", "381", "382", "383", "385", "386", "387", "389", "39", "40", "41", "420", "421", "423", "43", "44", "45", "46", "47", "48", "49", "500", "501", "502", "503", "504", "505", "506", "507", "508", "509", "51", "52", "53", "54", "55", "56", "57", "58", "590", "591", "592", "593", "594", "595", "596", "597", "598", "599", "60", "61", "62", "63", "64", "65", "66", "670", "672", "673", "674", "675", "676", "677", "678", "679", "680", "681", "682", "683", "684", "686", "687", "688", "689", "690", "691", "692", "7", "70", "7370", "81", "82", "84", "850", "852", "853", "855", "856", "86", "880", "886", "90", "91", "92", "93", "94", "95", "960", "961", "962", "963", "964", "965", "966", "967", "968", "970", "971", "972", "973", "974", "975", "976", "977", "98", "992", "994", "995", "996", "998"};

    public final static List<WebsiteLanguage> WEBSITE_LANGUAGES = new ArrayList<WebsiteLanguage>() {
        {
            add(new WebsiteLanguage("en", "English"));
            add(new WebsiteLanguage("th", "ไทย"));
        }
    };

    static {
        File countrydb = new File("C:\\countrydb\\country.mmdb");
        try {
            MAXMIND_COUNTRY_DB = new DatabaseReader.Builder(countrydb).build();
        } catch (IOException ex) {
            MAXMIND_COUNTRY_DB = null;
        }
        File citydb = new File("C:\\countrydb\\city.mmdb");
        try {
            MAXMIND_CITY_DB = new DatabaseReader.Builder(citydb).build();
        } catch (IOException ex) {
            MAXMIND_CITY_DB = null;
        }
    }

}
