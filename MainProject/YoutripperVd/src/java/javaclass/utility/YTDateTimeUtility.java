/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaclass.utility;

import org.joda.time.DateTime;
import org.joda.time.Days;

/**
 *
 * @author nickn
 */
public class YTDateTimeUtility {

    private final static String DD_MM_YYYY_HH_MM_SS_FORMAT = "dd/MM/yyyy HH:mm:ss";
    private final static String DD_MM_YYYY_FORMAT = "dd/MM/yyyy";
    private final static String MM_YYYY_FORMAT = "MM/yyyy";

    public static String getCurrentTimeUnderDdMMyyyyHHmmss() {
        DateTime now = new DateTime();
        return now.toString(DD_MM_YYYY_HH_MM_SS_FORMAT);
    }

    public static String getCurrentTimeUnderDdMMyyyy() {
        DateTime now = new DateTime();
        return now.toString(DD_MM_YYYY_FORMAT);
    }

    public static String getCurrentTimeUnderMMyyyy() {
        DateTime now = new DateTime();
        return now.toString(MM_YYYY_FORMAT);
    }

    public static String getDateStringUnderDdMMyyyy(Long milli) {
        DateTime now = new DateTime(milli);
        return now.toString(DD_MM_YYYY_FORMAT);
    }

    public static long getCurrentDateInMilli() {
        DateTime now = new DateTime();
        now = now.withTime(0, 0, 0, 0);
        return now.getMillis();
    }

    public static long getCurrentTimeInMilli() {
        return new DateTime().getMillis();
    }

    public static String convertMilliToddMMyyyy(long milli) {
        DateTime now = new DateTime(milli);
        return now.toString(DD_MM_YYYY_FORMAT);
    }

    public static int getNoDaysOfThisMonth() {
        DateTime now = new DateTime();
        DateTime endDate = now.withDayOfMonth(1).plusMonths(1).minusDays(1);
        return endDate.getDayOfMonth();
    }

    public static String convertMinutesToHHmm(int minutes) {
        if (minutes >= 0) {
            int leftMinutes = minutes % 60;
            int hours = (minutes - leftMinutes) / 60;
            if (hours >= 24) {
                hours -= 24;
            }
            String hoursInHH = hours < 10 ? "0" + hours : "" + hours;
            String minuteInMM = leftMinutes < 10 ? "0" + leftMinutes : "" + leftMinutes;
            return hoursInHH + ":" + minuteInMM;
        }
        return null;
    }

    public static long getBeginOfCurrentMonth() {
        DateTime now = new DateTime();
        now = now.withTime(0, 0, 0, 0).withDayOfMonth(1);
        return now.getMillis();
    }

    public static long getEndOfCurrentMonth() {
        DateTime now = new DateTime();
        now = now.plusMonths(1).withDayOfMonth(1).minusDays(1).withTime(0, 0, 0, 0);
        return now.getMillis();
    }

    public static int calculateDifferenceFromToday(Long ableDate) {
        DateTime toDay = new DateTime().withTime(0, 0, 0, 0);
        DateTime ableD = new DateTime(ableDate).withTime(0, 0, 0, 0);
        return Days.daysBetween(toDay.toLocalDate(), ableD.toLocalDate()).getDays();
    }
}
