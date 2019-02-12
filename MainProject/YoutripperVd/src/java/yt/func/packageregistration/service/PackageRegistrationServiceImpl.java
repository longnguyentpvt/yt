/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.func.packageregistration.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import java.io.IOException;
import java.util.List;
import javaclass.common.WebsiteLanguage;
import javaclass.common.YTData;
import javaclass.common.ytpackage.YTPackageData;
import javaclass.common.YTSession;
import javaclass.common.YTSessionAccount;
import javaclass.utility.YTDateTimeUtility;
import javaclass.utility.YoutripperIDUtility;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import yt.entity.tbl.TemporaryPackage;
import yt.entity.tbl.TemporaryPackageContent;
import yt.func.packageregistration.dao.TemporaryPackageContentDAO;
import yt.func.packageregistration.dao.TemporaryPackageDAO;
import yt.func.packageregistration.dao.TemporaryPackagePriceDAO;
import yt.func.packageregistration.dao.YTPackageDAO;
import javaclass.common.ytpackage.YTPackageMultiDescription;
import yt.func.partnerpackage.javaclass.MyPackageFilter;

/**
 *
 * @author nickn
 */
public class PackageRegistrationServiceImpl implements PackageRegistrationService {

    @Autowired
    private TemporaryPackageDAO tempPkgResgistrationDAO;

    @Autowired
    private TemporaryPackagePriceDAO tempPkgPriceRegistrationDAO;

    @Autowired
    private TemporaryPackageContentDAO tempPkgContentRegistrationDAO;

    @Autowired
    private YTPackageDAO ytPkgRegistrationDAO;

    @Override
    @Transactional
    public String registerNewPackage(HttpSession session, String data) throws IOException {
        YTSessionAccount account = YTSession.getAccountSession(session);
        String partnerID = account.getPartnerID();
        if (partnerID != null) {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode dataObject = mapper.readTree(data);

            String packageType = dataObject.get("servingType").asText();
            long registrationTime = YTDateTimeUtility.getCurrentTimeInMilli();
            String coverType = YTPackageData.COVER_TYPE_IMG;
            String durationType = YTPackageData.DURATION_TYPE_MINUTES;
            int frequencyServing = 1;

            long registeredTempPackageID = 0;
            boolean isOpenTimed = false;
            if (packageType.equals(MyPackageFilter.PACKAGE_SERVING_TYPE_REGULAR)) {
                String servingType = YTPackageData.SERVING_TYPE_PUBLIC;
                int minPerson = 1;
                String timeSchedule = YTPackageData.TIME_SCHEDULE_AUTO_TYPE;
                String advancedBooking = YTPackageData.ADVANCED_BOOKING_HOURS_TYPE;
                int advancedTime = 1;
                String dateSchedule = YTPackageData.DATE_SCHEDULE_AUTO_TYPE;

                registeredTempPackageID = tempPkgResgistrationDAO.registerNewRegular(partnerID, coverType, durationType,
                        servingType, minPerson, timeSchedule, advancedBooking, advancedTime, dateSchedule, frequencyServing, registrationTime);
            } else if (packageType.equals(MyPackageFilter.PACKAGE_SERVING_TYPE_OPEN_TIMED)) {
                String servingType = YTPackageData.SERVING_TYPE_OPEN_TIMED;
                int minPerson = 1;
                int servingTimes = 1;
                String timeSchedule = YTPackageData.TIME_SCHEDULE_FREE_TIME_TYPE;
                String dateSchedule = YTPackageData.DATE_SCHEDULE_OPENED_TIME_SLOT;
                int validationWeeks = 1;
                String quotaType = YTPackageData.QUOTA_TYPE_DAY;

                registeredTempPackageID = tempPkgResgistrationDAO.registerNewOpenTimed(partnerID, coverType, durationType, servingType, minPerson, servingTimes, timeSchedule,
                        dateSchedule, frequencyServing, validationWeeks, quotaType, registrationTime);
                isOpenTimed = true;
            }

            if (registeredTempPackageID > 0) {
                tempPkgPriceRegistrationDAO.registerDefaultPrice(registeredTempPackageID);

                List<WebsiteLanguage> languages = YTData.WEBSITE_LANGUAGES;
                for (WebsiteLanguage language : languages) {
                    String languageCode = language.getLanguageCode();
                    boolean registeredContent = languageCode.equals(YTPackageData.MANDATORY_CONTENT_LANGUAGE);
                    tempPkgContentRegistrationDAO.registerDefaultContent(registeredTempPackageID, languageCode, registeredContent);
                }

                // init yt package
                String mainPackageID = YoutripperIDUtility.generatePackageID(registeredTempPackageID, isOpenTimed);
                ytPkgRegistrationDAO.registerMainPackage(mainPackageID, registeredTempPackageID, partnerID);

                return "{\"packageID\" : " + registeredTempPackageID + "}";
            }
            throw new IllegalArgumentException("Insert Fail");
        }
        throw new IllegalArgumentException("Not Valid Partner");
    }

    @Override
    @Transactional
    public boolean checkCategoryStep(long packageID, String partnerID) {
        TemporaryPackage ytPackage = tempPkgResgistrationDAO.getCategoryInfoOfPackage(packageID, partnerID);
        if (ytPackage != null) {
            boolean success = false;
            String categoryID = ytPackage.getCategoryID();
            String subCategoryID = ytPackage.getSubCategoryID();
            String otherSubCategory = ytPackage.getOtherSubCategory();
            boolean suitableCouples = ytPackage.isSuitableCouples();
            boolean suitableElderly = ytPackage.isSuitableElderly();
            boolean suitableFamily = ytPackage.isSuitableFamily();
            boolean suitableIndividual = ytPackage.isSuitableIndividual();
            boolean suitableUniversal = ytPackage.isSuitableUniversal();
            String genderSuitability = ytPackage.getGenderSuitability();
            String colorCode = ytPackage.getColorCode();

            if (categoryID != null && subCategoryID != null && colorCode != null) {
                if (!subCategoryID.equals(YTPackageData.SUB_CATEGORY_OTHERS_ID) || otherSubCategory != null) {
                    if (suitableIndividual) {
                        success = genderSuitability != null;
                    } else if (suitableCouples || suitableElderly || suitableFamily || suitableUniversal) {
                        success = genderSuitability == null;
                    }
                }
            }
            return success;
        }
        throw new IllegalArgumentException("Not Valid Partner");
    }

    @Override
    @Transactional
    public String checkCategoryStep(HttpSession session, String data) throws IOException {
        YTSessionAccount account = YTSession.getAccountSession(session);
        String partnerID = account.getPartnerID();
        boolean success = false;
        if (partnerID != null) {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode dataObject = mapper.readTree(data);

            long packageID = dataObject.get("packageID").asLong();
            success = checkCategoryStep(packageID, partnerID);
        }

        return "{\"success\" : " + success + "}";
    }

    @Override
    @Transactional
    public boolean checkDescriptionStep(long packageID, String partnerID) throws IOException {
        TemporaryPackage ytPackage = tempPkgResgistrationDAO.getDescriptionInfoOfPackage(packageID, partnerID);
        if (ytPackage != null) {
            boolean success = false;

            List<TemporaryPackageContent> tempContents = tempPkgContentRegistrationDAO.getRegistrationContent(packageID);
            String servingLanguage = ytPackage.getServingLanguage();
            if (servingLanguage != null) {
                success = true;
                for (TemporaryPackageContent tempContent : tempContents) {
                    boolean registered = tempContent.isRegisteredContent();
                    if (registered) {
                        String pkgName = tempContent.getPackageName();
                        String googleDescription = tempContent.getGoogleDescription();
                        String mulDescription = tempContent.getMultiDescription();
                        String activities = tempContent.getActivityLocations();
                        String kws = tempContent.getKeywords();
                        if (pkgName == null || pkgName.length() == 0
                                || googleDescription == null || googleDescription.length() == 0
                                || mulDescription == null || activities == null || kws == null) {
                            success = false;
                        } else {
                            ObjectMapper mapper = new ObjectMapper();
                            TypeFactory typeFactory = mapper.getTypeFactory();
                            List<YTPackageMultiDescription> descs = mapper.readValue(mulDescription, typeFactory.constructCollectionType(List.class, YTPackageMultiDescription.class));

                            for (int i = 0, len = descs.size(); i < len; i++) {
                                YTPackageMultiDescription aD = descs.get(i);
                                String tt = aD.getTitle();
                                String des = aD.getDescription();
                                if (tt == null || des == null || tt.length() == 0 || des.length() == 0) {
                                    success = false;
                                    break;
                                }
                            }
                        }
                    }
                }
            }
            return success;
        }
        throw new IllegalArgumentException("Not Valid Partner");
    }

    @Override
    @Transactional
    public String checkDescriptionStep(HttpSession session, String data) throws IOException {
        YTSessionAccount account = YTSession.getAccountSession(session);
        String partnerID = account.getPartnerID();
        boolean success = false;
        if (partnerID != null) {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode dataObject = mapper.readTree(data);

            long packageID = dataObject.get("packageID").asLong();
            success = checkDescriptionStep(packageID, partnerID);
        }
        return "{\"success\" : " + success + "}";
    }

    @Override
    @Transactional
    public boolean checkPhotoStep(long packageID, String partnerID) {
        return true;
    }

    @Override
    @Transactional
    public String checkPhotoStep(HttpSession session, String data) throws IOException {
        YTSessionAccount account = YTSession.getAccountSession(session);
        String partnerID = account.getPartnerID();
        boolean success = false;
        if (partnerID != null) {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode dataObject = mapper.readTree(data);

            long packageID = dataObject.get("packageID").asLong();
            success = checkPhotoStep(packageID, partnerID);
        }
        return "{\"success\" : " + success + "}";
    }

}
