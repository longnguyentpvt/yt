/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.func.partnerpackage.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javaclass.utility.YTFileUtility;
import javaclass.common.ytpackage.YTPackageData;
import javaclass.common.YTSession;
import javaclass.common.YTSessionAccount;
import javaclass.utility.YTPriceUtility;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yt.entity.view.PartnerPackage;
import yt.func.partnerpackage.dao.YTPackageDAO;
import yt.func.partnerpackage.javaclass.MyPackage;
import yt.func.partnerpackage.javaclass.MyPackageFilter;
import yt.func.partnerpackage.javaclass.PartnerPackagePrice;

/**
 *
 * @author nickn
 */
@Service
public class MyPackageServiceImpl implements MyPackageService {

    @Autowired
    private YTPackageDAO myPackageDAO;

    @Override
    @Transactional
    public String getPartnerPackages(HttpSession session, String data) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode dataObject = mapper.readTree(data);

        JsonNode pkgNameNode = dataObject.get("packageName");
        String pkgNameFilter = null;
        if (!pkgNameNode.isNull()) {
            pkgNameFilter = pkgNameNode.asText();
        }

        String sortCri = dataObject.get("sortCri").asText();

        JsonNode statusNode = dataObject.get("statusCri");
        String statusCri = null;
        if (!statusNode.isNull()) {
            statusCri = statusNode.asText();
        }
        String mainStatus = null;
        String tempStatus = null;
        if (statusCri != null && !statusCri.isEmpty()) {
            switch (statusCri) {
                case MyPackageFilter.PACKAGE_STATUS_APPROVED:
                    mainStatus = YTPackageData.YT_PACKAGE_STATUS_APPROVED;
                    break;
                case MyPackageFilter.PACKAGE_STATUS_CREATING:
                    tempStatus = YTPackageData.TEMPORARY_PACKAGE_STATUS_CREATING;
                    break;
                case MyPackageFilter.PACKAGE_STATUS_PENDING:
                    tempStatus = YTPackageData.TEMPORARY_PACKAGE_STATUS_PENDING;
                    break;
                case MyPackageFilter.PACKAGE_STATUS_NOT_OPERATING:
                    mainStatus = YTPackageData.YT_PACKAGE_STATUS_NOT_OPERATING;
                    break;
                case MyPackageFilter.PACKAGE_STATUS_FAIL:
                    tempStatus = YTPackageData.TEMPORARY_PACKAGE_STATUS_FAIL;
                    break;
                case MyPackageFilter.PACKAGE_STATUS_EDITING:
                    tempStatus = YTPackageData.TEMPORARY_PACKAGE_STATUS_EDITING;
                    break;
                case MyPackageFilter.PACKAGE_STATUS_DELETING:
                    mainStatus = YTPackageData.YT_PACKAGE_STATUS_DELETING;
                    break;
            }
        }

        String servingType = dataObject.get("servingType").asText();
        boolean openTimedFilter = false;
        if (servingType.equals(MyPackageFilter.PACKAGE_SERVING_TYPE_OPEN_TIMED)) {
            openTimedFilter = true;
        }

        YTSessionAccount ytAccount = YTSession.getAccountSession(session);
        String partnerID = ytAccount.getPartnerID();

        List<PartnerPackage> partnerPackages = myPackageDAO.getAllPackages(partnerID, openTimedFilter,
                pkgNameFilter, mainStatus, tempStatus);
        List<MyPackage> myPackages = new ArrayList<>();
        HashMap<String, MyPackage> packageHM = new HashMap<>();

        partnerPackages.forEach((PartnerPackage partnerPackage) -> {
            String packageID = partnerPackage.getMainPackageID();
            boolean onPromotional = partnerPackage.isOnPromotional();

            MyPackage aPackage;
            if (packageHM.containsKey(packageID)) {
                aPackage = packageHM.get(packageID);
            } else {
                String mainID = partnerPackage.getMainPackageID();
                long tempID = partnerPackage.getTempPackageID();
                String pkgStatus = null;
                String mainPkgStatus = partnerPackage.getMainStatus();
                String tempPkgStatus = partnerPackage.getTempStatus();
                if (!tempPkgStatus.equals(YTPackageData.TEMPORARY_PACKAGE_STATUS_DELETED)) {
                    switch (tempPkgStatus) {
                        case YTPackageData.TEMPORARY_PACKAGE_STATUS_CREATING:
                            pkgStatus = MyPackageFilter.PACKAGE_STATUS_CREATING;
                            break;
                        case YTPackageData.TEMPORARY_PACKAGE_STATUS_EDITING:
                            pkgStatus = MyPackageFilter.PACKAGE_STATUS_EDITING;
                            break;
                        case YTPackageData.TEMPORARY_PACKAGE_STATUS_PENDING:
                            pkgStatus = MyPackageFilter.PACKAGE_STATUS_PENDING;
                            break;
                        case YTPackageData.TEMPORARY_PACKAGE_STATUS_FAIL:
                            pkgStatus = MyPackageFilter.PACKAGE_STATUS_FAIL;
                            break;
                    }
                } else {
                    switch (mainPkgStatus) {
                        case YTPackageData.YT_PACKAGE_STATUS_APPROVED:
                            pkgStatus = MyPackageFilter.PACKAGE_STATUS_APPROVED;
                            break;
                        case YTPackageData.YT_PACKAGE_STATUS_NOT_OPERATING:
                            pkgStatus = MyPackageFilter.PACKAGE_STATUS_NOT_OPERATING;
                            break;
                        case YTPackageData.YT_PACKAGE_STATUS_DELETING:
                            pkgStatus = MyPackageFilter.PACKAGE_STATUS_DELETING;
                            break;
                    }
                }

                String name = partnerPackage.getPackageName();
                int noSolds = 0;
                try {
                    noSolds = partnerPackage.getNoSolds();
                } catch (Exception e) {
                }
                int rate = 0;
                try {
                    rate = partnerPackage.getRate();
                } catch (Exception e) {
                }
                int noReviews = 0;
                try {
                    noReviews = partnerPackage.getNoComments();
                } catch (Exception e) {
                }

                String pkgPortrait = partnerPackage.getPortraitPhoto();
                String promotionPortrait = partnerPackage.getPromotionPortrait();
                String portrait = YTFileUtility.getPortraitPhoto(pkgPortrait, promotionPortrait, onPromotional);

                aPackage = new MyPackage(mainID, tempID, pkgStatus, name, noSolds, rate, noReviews, portrait, new HashMap<>());
                packageHM.put(packageID, aPackage);

                myPackages.add(aPackage);
            }

            HashMap<String, PartnerPackagePrice> prices = aPackage.getPrices();
            int promotionPercent = 0;

            String currencyCode = partnerPackage.getCurrencyCode();
            BigDecimal price = partnerPackage.getPrice();
            if (price == null) {
                price = BigDecimal.ZERO;
            }

            BigDecimal promotionPrice = null;
            if (onPromotional) {
                promotionPrice = partnerPackage.getPromotionPrice();
                if (promotionPrice != null) {
                    promotionPercent = YTPriceUtility.getPromotionPercent(price, promotionPrice);
                }
            }
            if (promotionPrice == null) {
                promotionPrice = BigDecimal.ZERO;
            }

            PartnerPackagePrice aPrice = new PartnerPackagePrice(promotionPercent, currencyCode, price, promotionPrice);
            prices.put(currencyCode, aPrice);
        });

        switch (sortCri) {
            case MyPackageFilter.ALPHABETICALLY_SORTING:
                myPackages.sort(MyPackage.nameComparator);
                break;
            case MyPackageFilter.PACKAGE_STATUS_SORTING:
                myPackages.sort(MyPackage.statusComparator);
                break;
            case MyPackageFilter.OLDEST_PACKAGE_SORTING:
                myPackages.sort(MyPackage.oldestComparator);
                break;
            case MyPackageFilter.YOUNGEST_PACKAGE_SORTING:
                myPackages.sort(MyPackage.youngestComparator);
                break;
            case MyPackageFilter.HIGHEST_PRICE_SORTING:
                myPackages.sort(MyPackage.priceDscComparator);
                break;
            case MyPackageFilter.LOWEST_PRICE_SORTING:
                myPackages.sort(MyPackage.priceAscComparator);
                break;
        }

        return mapper.writeValueAsString(myPackages);
    }

}
