/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaclass.common.ytpackage;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author nickn
 */
public class YTPackageData {

    public final static String YT_PACKAGE_STATUS_APPROVED = "approved";
    public static final String YT_PACKAGE_STATUS_DELETING = "deleting";
    public static final String YT_PACKAGE_STATUS_DELETED = "deleted";
    public static final String YT_PACKAGE_STATUS_NOT_OPERATING = "inactive";
    public static final String YT_PACKAGE_STATUS_CREATING = "creating";

    public static final String TEMPORARY_PACKAGE_STATUS_CREATING = "creating";
    public static final String TEMPORARY_PACKAGE_STATUS_EDITING = "editing";
    public static final String TEMPORARY_PACKAGE_STATUS_REQUIRED_APPROVAL_EDITING = "ra-editing";
    public static final String TEMPORARY_PACKAGE_STATUS_PENDING = "pending";
    public static final String TEMPORARY_PACKAGE_STATUS_DELETED = "deleted";
    public static final String TEMPORARY_PACKAGE_STATUS_FAIL = "fail";

    public static final String SUB_CATEGORY_OTHERS_ID = "others";

    public static final String SERVING_TYPE_OPEN_TIMED = "open-timed";
    public static final String SERVING_TYPE_GROUP = "Group";
    public static final String SERVING_TYPE_PRIVATE = "Private";
    public static final String SERVING_TYPE_PUBLIC = "Public";

    public static final String COVER_TYPE_VIDEO = "video";
    public static final String COVER_TYPE_IMG = "img";

    public static final String DURATION_TYPE_MINUTES = "minutes";
    public static final String DURATION_TYPE_DAYS = "days";
    public static final String DURATION_TYPE_WEEKS = "weeks";
    public static final String DURATION_TYPE_MONTHS = "months";

    public static final String TIME_SCHEDULE_AUTO_TYPE = "auto";
    public static final String TIME_SCHEDULE_FLEXIBE_TYPE = "flexible";
    public static final String TIME_SCHEDULE_FREE_TIME_TYPE = "freeTime";

    public static final String ADVANCED_BOOKING_HOURS_TYPE = "hours";
    public static final String ADVANCED_BOOKING_DAYS_TYPE = "days";

    public static final String DATE_SCHEDULE_AUTO_TYPE = "auto";
    public static final String DATE_SCHEDULE_FLEXIBE_TYPE = "flexible";
    public static final String DATE_SCHEDULE_OPENED_TIME_SLOT = "timeslot";
    public static final String DATE_SCHEDULE_OPENED_LIMIT = "limit";
    public static final String DATE_SCHEDULE_OPENED_RANGE = "range";
    public static final String DATE_SCHEDULE_OPENED_LIFETIME = "lifetime";

    public static final String QUOTA_TYPE_TIME_SLOT = "timeslot";
    public static final String QUOTA_TYPE_DAY = "day";
    public static final String QUOTA_TYPE_MONTH = "month";

    public final static String MANDATORY_CONTENT_LANGUAGE = "en";

    public final static String SUITABLE_GENDER_MALE = "male";
    public final static String SUITABLE_GENDER_FEMALE = "female";
    public final static String SUITABLE_GENDER_UNISEX = "unisex";

    public final static String PACKAGE_COLOR_BLACK = "black";
    public final static String PACKAGE_COLOR_GREY = "grey";
    public final static String PACKAGE_COLOR_DARK_GREY = "darkgrey";
    public final static String PACKAGE_COLOR_DARK_RED = "darkred";
    public final static String PACKAGE_COLOR_RED = "red";
    public final static String PACKAGE_COLOR_OLIVE = "olive";
    public final static String PACKAGE_COLOR_YELLOW = "yellow";
    public final static String PACKAGE_COLOR_GREEN = "green";
    public final static String PACKAGE_COLOR_LIME = "lime";
    public final static String PACKAGE_COLOR_TEAL = "teal";
    public final static String PACKAGE_COLOR_CYAN = "cyan";
    public final static String PACKAGE_COLOR_DARKBLUE = "darkblue";
    public final static String PACKAGE_COLOR_BLUE = "blue";
    public final static String PACKAGE_COLOR_PURPLE = "purple";
    public final static String PACKAGE_COLOR_PINK = "pink";

    public final static long UNLIMIT_OPEN_TIMED_SERVING_TIMES = 0;
    public final static long UNLIMIT_EXPIRE_DATE = -1;

    public final static Map<String, YTPackageColor> PACKAGE_COLORS;

    static {
        Map<String, YTPackageColor> aMap = new HashMap<>();
        aMap.put(PACKAGE_COLOR_BLACK, new YTPackageColor(PACKAGE_COLOR_BLACK, "#000000"));
        aMap.put(PACKAGE_COLOR_GREY, new YTPackageColor(PACKAGE_COLOR_GREY, "#808080"));
        aMap.put(PACKAGE_COLOR_DARK_GREY, new YTPackageColor(PACKAGE_COLOR_DARK_GREY, "#a9a9a9"));
        aMap.put(PACKAGE_COLOR_DARK_RED, new YTPackageColor(PACKAGE_COLOR_DARK_RED, "#8b0000"));
        aMap.put(PACKAGE_COLOR_RED, new YTPackageColor(PACKAGE_COLOR_RED, "#ff0000"));
        aMap.put(PACKAGE_COLOR_OLIVE, new YTPackageColor(PACKAGE_COLOR_OLIVE, "#808000"));
        aMap.put(PACKAGE_COLOR_YELLOW, new YTPackageColor(PACKAGE_COLOR_YELLOW, "#ffff00"));
        aMap.put(PACKAGE_COLOR_GREEN, new YTPackageColor(PACKAGE_COLOR_GREEN, "#008000"));
        aMap.put(PACKAGE_COLOR_LIME, new YTPackageColor(PACKAGE_COLOR_LIME, "#00ff00"));
        aMap.put(PACKAGE_COLOR_TEAL, new YTPackageColor(PACKAGE_COLOR_TEAL, "#008080"));
        aMap.put(PACKAGE_COLOR_CYAN, new YTPackageColor(PACKAGE_COLOR_CYAN, "#00ffff"));
        aMap.put(PACKAGE_COLOR_DARKBLUE, new YTPackageColor(PACKAGE_COLOR_DARKBLUE, "#00008b"));
        aMap.put(PACKAGE_COLOR_BLUE, new YTPackageColor(PACKAGE_COLOR_BLUE, "#0000ff"));
        aMap.put(PACKAGE_COLOR_PURPLE, new YTPackageColor(PACKAGE_COLOR_PURPLE, "#800080"));
        aMap.put(PACKAGE_COLOR_PINK, new YTPackageColor(PACKAGE_COLOR_PINK, "#ff00ff"));
        PACKAGE_COLORS = Collections.unmodifiableMap(aMap);
    }

}
