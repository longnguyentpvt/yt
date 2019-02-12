/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.func.home.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yt.func.home.dao.YTPackageDAO;
import java.util.List;
import javaclass.common.ytpackage.YTPackageModule;
import yt.entity.view.HottestDeal;
import yt.entity.view.LatestBookedPackage;

/**
 *
 * @author Hiep
 */
@Service
public class HomePackageServiceImpl implements HomePackageService {

    @Autowired
    private YTPackageDAO homePackageDAO;

    @Override
    @Transactional
    public String loadHotestDeal(String data) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        // get infor from json
        JsonNode dataObject = mapper.readTree(data);
        String languageCode = dataObject.get("locale").asText();
        String currencyCode = dataObject.get("currencyCode").asText();
        List<HottestDeal> hottestList = homePackageDAO.loadHottestDealPackage(languageCode, currencyCode);
        List<YTPackageModule> rList = new ArrayList<>();
        for (HottestDeal ap : hottestList) {
            String packageID = ap.getPackageID();
            String name = ap.getPackageName();
            String siteURL = ap.getSiteURL();
            String city = ap.getCityName();
            String countryName = ap.getCountryName();
            BigDecimal price = ap.getPrice();
            BigDecimal promotionPrice = ap.getPromotionPrice();
            Integer noSolds = ap.getNoSolds();
            Integer noComments = ap.getNoComments();
            Integer rate = ap.getRate();
            String portraitPhoto = ap.getPortraitPhoto();
            String promotionPortrait = ap.getPromotionPortrait();
            boolean onPromotional = ap.isOnPromotional();
            // no solds, no comments, rate
            noSolds = (noSolds == null ? 0 : noSolds);
            noComments = (noComments == null ? 0 : noComments);
            rate = (rate == null ? 0 : rate);
            YTPackageModule ytpM = new YTPackageModule(packageID, name, siteURL,
                    countryName, city, portraitPhoto, promotionPortrait, price, promotionPrice, onPromotional,
                    rate, noComments, noSolds, languageCode, currencyCode);
            rList.add(ytpM);
        }

        String rtJs = mapper.writeValueAsString(rList);
        return rtJs;
    }

    @Override
    @Transactional
    public String loadJustBooked(String data) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        // get infor from json
        JsonNode dataObject = mapper.readTree(data);
        String languageCode = dataObject.get("locale").asText();
        String currencyCode = dataObject.get("currencyCode").asText();
        List<LatestBookedPackage> justBookedList = homePackageDAO.loadJustBookedPackage(languageCode, currencyCode);
        List<YTPackageModule> rList = new ArrayList<>();
        for (LatestBookedPackage ap : justBookedList) {
            String packageID = ap.getPackageID();
            String name = ap.getPackageName();
            String siteURL = ap.getSiteURL();
            String city = ap.getCityName();
            String countryName = ap.getCountryName();
            BigDecimal price = ap.getPrice();
            BigDecimal promotionPrice = ap.getPromotionPrice();
            Integer noSolds = ap.getNoSolds();
            Integer noComments = ap.getNoComments();
            Integer rate = ap.getRate();
            String portraitPhoto = ap.getPortraitPhoto();
            String promotionPortrait = ap.getPromotionPortrait();
            boolean onPromotional = ap.isOnPromotional();
            // no solds, no comments, rate
            noSolds = (noSolds == null ? 0 : noSolds);
            noComments = (noComments == null ? 0 : noComments);
            rate = (rate == null ? 0 : rate);
            YTPackageModule ytpM = new YTPackageModule(packageID, name, siteURL,
                    countryName, city, portraitPhoto, promotionPortrait, price, promotionPrice, onPromotional,
                    rate, noComments, noSolds, languageCode, currencyCode);
            rList.add(ytpM);
        }
        //return json String
        String rtJs = mapper.writeValueAsString(rList);
        return rtJs;
    }

    @Override
    @Transactional
    public String loadExploreMore(String data) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        // get infor from json
        JsonNode dataObject = mapper.readTree(data);
        String languageCode = dataObject.get("locale").asText();
        String currencyCode = dataObject.get("currencyCode").asText();
        List<LatestBookedPackage> justBookedList = homePackageDAO.loadJustBookedPackage(languageCode, currencyCode);
        List<YTPackageModule> rList = new ArrayList<>();
        for (LatestBookedPackage ap : justBookedList) {
            String packageID = ap.getPackageID();
            String name = ap.getPackageName();
            String siteURL = ap.getSiteURL();
            String city = ap.getCityName();
            String countryName = ap.getCountryName();
            BigDecimal price = ap.getPrice();
            BigDecimal promotionPrice = ap.getPromotionPrice();
            Integer noSolds = ap.getNoSolds();
            Integer noComments = ap.getNoComments();
            Integer rate = ap.getRate();
            String portraitPhoto = ap.getPortraitPhoto();
            String promotionPortrait = ap.getPromotionPortrait();
            boolean onPromotional = ap.isOnPromotional();
            // no solds, no comments, rate
            noSolds = (noSolds == null ? 0 : noSolds);
            noComments = (noComments == null ? 0 : noComments);
            rate = (rate == null ? 0 : rate);
            YTPackageModule ytpM = new YTPackageModule(packageID, name, siteURL,
                    countryName, city, portraitPhoto, promotionPortrait, price, promotionPrice, onPromotional,
                    rate, noComments, noSolds, languageCode, currencyCode);
            rList.add(ytpM);
        }
        //return json String
        String rtJs = mapper.writeValueAsString(rList);
        return rtJs;
    }
}
