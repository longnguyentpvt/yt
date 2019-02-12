/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.func.partnerpackage.javaclass;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.HashMap;
import javaclass.common.YTData;

/**
 *
 * @author nickn
 */
public class MyPackage {

    private String mainID;
    private long tempID;
    private String pkgStatus;
    private String name;
    private int noSolds;
    private int rate;
    private int noReviews;
    private String portrait;
    private HashMap<String, PartnerPackagePrice> prices;

    public MyPackage() {
    }

    public MyPackage(String mainID, long tempID, String pkgStatus, String name, int noSolds, int rate, int noReviews, String portrait, HashMap<String, PartnerPackagePrice> prices) {
        this.mainID = mainID;
        this.tempID = tempID;
        this.pkgStatus = pkgStatus;
        this.name = name;
        this.noSolds = noSolds;
        this.rate = rate;
        this.noReviews = noReviews;
        this.portrait = portrait;
        this.prices = prices;
    }

    public String getMainID() {
        return mainID;
    }

    public long getTempID() {
        return tempID;
    }

    public String getPkgStatus() {
        return pkgStatus;
    }

    public String getName() {
        return name;
    }

    public int getNoSolds() {
        return noSolds;
    }

    public int getRate() {
        return rate;
    }

    public int getNoReviews() {
        return noReviews;
    }

    public String getPortrait() {
        return portrait;
    }

    public HashMap<String, PartnerPackagePrice> getPrices() {
        return prices;
    }

    public void setPrices(HashMap<String, PartnerPackagePrice> prices) {
        this.prices = prices;
    }

    public static Comparator<MyPackage> nameComparator = (MyPackage p1, MyPackage p2) -> {
        String p1Name = p1.getName();
        String p2Name = p2.getName();
        if (p1Name == null) {
            p1Name = "";
        }
        if (p2Name == null) {
            p2Name = "";
        }
        return p1Name.compareToIgnoreCase(p2Name);
    };

    public static Comparator<MyPackage> youngestComparator = (MyPackage p1, MyPackage p2) -> {
        long id1 = p1.getTempID();
        long id2 = p2.getTempID();
        if (id1 < id2) {
            return 1;
        }
        if (id1 > id2) {
            return -1;
        }
        return 0;
    };

    public static Comparator<MyPackage> oldestComparator = (MyPackage p1, MyPackage p2) -> {
        long id1 = p1.getTempID();
        long id2 = p2.getTempID();
        if (id1 < id2) {
            return -1;
        }
        if (id1 > id2) {
            return 1;
        }
        return 0;
    };

    public static Comparator<MyPackage> priceAscComparator = (MyPackage p1, MyPackage p2) -> {
        BigDecimal price1 = p1.getPrices().get(YTData.DEFAULT_CURRENCY_CODE).getPaidPrice();
        BigDecimal price2 = p2.getPrices().get(YTData.DEFAULT_CURRENCY_CODE).getPaidPrice();
        return price1.compareTo(price2);
    };

    public static Comparator<MyPackage> priceDscComparator = (MyPackage p1, MyPackage p2) -> {
        BigDecimal price1 = p1.getPrices().get(YTData.DEFAULT_CURRENCY_CODE).getPaidPrice();
        BigDecimal price2 = p2.getPrices().get(YTData.DEFAULT_CURRENCY_CODE).getPaidPrice();
        return -price1.compareTo(price2);
    };

    public static Comparator<MyPackage> statusComparator = (MyPackage p1, MyPackage p2) -> {
        String status1 = p1.getPkgStatus();
        String status2 = p2.getPkgStatus();
        
        int i1 = 0;
        switch (status1) {
            case MyPackageFilter.PACKAGE_STATUS_APPROVED:
                i1 = 1;
                break;
            case MyPackageFilter.PACKAGE_STATUS_CREATING:
                i1 = 2;
                break;
            case MyPackageFilter.PACKAGE_STATUS_EDITING:
                i1 = 3;
                break;
            case MyPackageFilter.PACKAGE_STATUS_PENDING:
                i1 = 4;
                break;
            case MyPackageFilter.PACKAGE_STATUS_FAIL:
                i1 = 5;
                break;
            case MyPackageFilter.PACKAGE_STATUS_NOT_OPERATING:
                i1 = 6;
                break;
            case MyPackageFilter.PACKAGE_STATUS_DELETING:
                i1 = 7;
                break;
        }

        int i2 = 0;
        switch (status1) {
            case MyPackageFilter.PACKAGE_STATUS_APPROVED:
                i2 = 1;
                break;
            case MyPackageFilter.PACKAGE_STATUS_CREATING:
                i2 = 2;
                break;
            case MyPackageFilter.PACKAGE_STATUS_EDITING:
                i2 = 3;
                break;
            case MyPackageFilter.PACKAGE_STATUS_PENDING:
                i2 = 4;
                break;
            case MyPackageFilter.PACKAGE_STATUS_FAIL:
                i2 = 5;
                break;
            case MyPackageFilter.PACKAGE_STATUS_NOT_OPERATING:
                i2 = 6;
                break;
            case MyPackageFilter.PACKAGE_STATUS_DELETING:
                i2 = 7;
                break;
        }

        if (i1 < i2) {
            return -1;
        }
        if (i1 > i2) {
            return 1;
        }
        return 0;
    };
}
