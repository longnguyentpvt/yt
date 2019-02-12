/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaclass.common.ytpackage;

import java.math.BigDecimal;
import javaclass.utility.YTFileUtility;
import javaclass.utility.YTPriceUtility;
import javaclass.utility.YTUrlUtility;

/**
 *
 * @author nickn
 */
public class YTPackageModule {

    private String packageID;
    private String name;
    private String siteURL;
    private String loc; // location
    private String thumb;
    private BigDecimal price;
    private BigDecimal oPrice;
    private String sym;
    private int dcPercent;
    private boolean promotional;
    private int rate;
    private int noComments;
    private int noSolds;

    public YTPackageModule() {
    }

    public YTPackageModule(String packageID, String name, String siteURL, String country, String city,
            String portraitPhoto, String promotionPotrait, BigDecimal price, BigDecimal promotionPrice, boolean promotional,
            int rate, int noComments, int noSolds, String languageCode, String currencyCode) {
        this.packageID = packageID;
        this.name = name;
        this.siteURL = siteURL;
        this.promotional = promotional;
        this.rate = rate;
        this.noComments = noComments;
        this.noSolds = noSolds;
        this.thumb = YTFileUtility.getPortraitPhoto(portraitPhoto, promotionPotrait, promotional);

        this.loc = city + ", " + country;
        this.siteURL = YTUrlUtility.getLocalePath(languageCode) + "/" + siteURL + "/" + packageID;

        if (promotional) {
            this.price = promotionPrice;
            this.oPrice = price;
            this.dcPercent = YTPriceUtility.getPromotionPercent(price, promotionPrice);
        } else {
            this.price = price;
        }
        
        this.sym = YTPriceUtility.getCurrencySymbol(currencyCode);
    }

    public String getPackageID() {
        return packageID;
    }

    public String getName() {
        return name;
    }

    public String getSiteURL() {
        return siteURL;
    }

    public String getLoc() {
        return loc;
    }

    public String getThumb() {
        return thumb;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public BigDecimal getoPrice() {
        return oPrice;
    }

    public int getDcPercent() {
        return dcPercent;
    }

    public boolean isPromotional() {
        return promotional;
    }

    public int getRate() {
        return rate;
    }

    public int getNoComments() {
        return noComments;
    }

    public int getNoSolds() {
        return noSolds;
    }

    public String getSym() {
        return sym;
    }

}
