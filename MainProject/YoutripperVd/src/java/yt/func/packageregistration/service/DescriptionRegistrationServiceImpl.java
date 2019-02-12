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
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javaclass.common.ytpackage.YTPackageData;
import javaclass.common.YTSession;
import javaclass.common.YTSessionAccount;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import yt.entity.tbl.TemporaryPackage;
import yt.entity.tbl.TemporaryPackageContent;
import yt.func.packageregistration.dao.TemporaryPackageContentDAO;
import yt.func.packageregistration.dao.TemporaryPackageDAO;
import yt.func.packageregistration.javaclass.DescriptionPackageContent;
import yt.func.packageregistration.javaclass.DescriptionStepData;
import yt.func.packageregistration.javaclass.RegistrationCommonData;
import javaclass.common.ytpackage.YTPackageLocation;
import javaclass.common.ytpackage.YTPackageMultiDescription;
import yt.javaclass.config.YTDataConfiguration;

/**
 *
 * @author nickn
 */
public class DescriptionRegistrationServiceImpl implements DescriptionRegistrationService {

    @Autowired
    private TemporaryPackageDAO tempPkgResgistrationDAO;

    @Autowired
    private TemporaryPackageContentDAO tempPkgContentRegistrationDAO;

    @Override
    @Transactional
    public String getDescriptionData(HttpSession session, String data) throws IOException {
        YTSessionAccount account = YTSession.getAccountSession(session);
        String partnerID = account.getPartnerID();
        if (partnerID != null) {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode dataObject = mapper.readTree(data);

            long packageID = dataObject.get("packageID").asLong();
            TemporaryPackage ytPackage = tempPkgResgistrationDAO.getDescriptionInfoOfPackage(packageID, partnerID);
            if (ytPackage != null) {
                String localeCode = dataObject.get("localeCode").asText();
                TypeFactory typeFactory = mapper.getTypeFactory();

                List<TemporaryPackageContent> tempContents = tempPkgContentRegistrationDAO.getRegistrationContent(packageID);
                HashMap<String, DescriptionPackageContent> contentHM = new HashMap<>();
                for (TemporaryPackageContent tempContent : tempContents) {
                    String languageCode = tempContent.getLanguageCode();
                    String packageName = tempContent.getPackageName();
                    String googleDescription = tempContent.getGoogleDescription();

                    String multiDesStr = tempContent.getMultiDescription();
                    List<YTPackageMultiDescription> multiDescriptions = null;
                    if (multiDesStr != null) {
                        multiDescriptions = mapper.readValue(multiDesStr, typeFactory.constructCollectionType(List.class, YTPackageMultiDescription.class));
                    }

                    String activityLocStr = tempContent.getActivityLocations();
                    List<YTPackageLocation> activityLocations = null;
                    if (activityLocStr != null) {
                        activityLocations = mapper.readValue(activityLocStr, typeFactory.constructCollectionType(List.class, YTPackageLocation.class));
                    }

                    String departureLocStr = tempContent.getDepartureLocations();
                    List<YTPackageLocation> departureLocations = null;
                    if (departureLocStr != null) {
                        departureLocations = mapper.readValue(departureLocStr, typeFactory.constructCollectionType(List.class, YTPackageLocation.class));
                    }

                    String pickupLocStr = tempContent.getPickupLocations();
                    List<YTPackageLocation> pickupLocations = null;
                    if (pickupLocStr != null) {
                        pickupLocations = mapper.readValue(pickupLocStr, typeFactory.constructCollectionType(List.class, YTPackageLocation.class));
                    }

                    String kwStr = tempContent.getKeywords();
                    List<String> keywords = null;
                    if (kwStr != null) {
                        keywords = mapper.readValue(kwStr, typeFactory.constructCollectionType(List.class, String.class));
                    }

                    boolean registered = tempContent.isRegisteredContent();
                    DescriptionPackageContent aContent = new DescriptionPackageContent(languageCode, packageName, googleDescription, multiDescriptions, activityLocations, pickupLocations, departureLocations, keywords, registered);
                    contentHM.put(languageCode, aContent);
                }

                String servingLanguage = ytPackage.getServingLanguage();
                String[] servingLanguages = null;
                if (servingLanguage != null) {
                    servingLanguages = mapper.readValue(servingLanguage, String[].class);
                }

                List<String[]> descriptionLanguages = RegistrationCommonData.getDescriptionLanguages(localeCode);
                List<String[]> ytServingLanguages = RegistrationCommonData.getServingLanguages(localeCode);

                String packageStatus = ytPackage.getPackageStatus();
                boolean disableEditing = false;
                if (packageStatus.equals(YTPackageData.TEMPORARY_PACKAGE_STATUS_EDITING)) {
                    disableEditing = true;
                }

                String cURL = YTDataConfiguration.getCommonImageURL();
                String activityMarker = cURL + "event-location-icon.png";
                String pickupMarker = cURL + "pickup-location-icon.png";
                String departureMarker = cURL + "departure-location-icon.png";

                DescriptionStepData responseData = new DescriptionStepData(contentHM, servingLanguages, descriptionLanguages,
                        ytServingLanguages, disableEditing, activityMarker, pickupMarker, departureMarker);

                return mapper.writeValueAsString(responseData);
            }
        }
        throw new IllegalArgumentException("Not Valid Partner");
    }

    @Override
    @Transactional
    public String updatePackageName(HttpSession session, String data) throws IOException {
        YTSessionAccount account = YTSession.getAccountSession(session);
        String partnerID = account.getPartnerID();
        if (partnerID != null) {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode dataObject = mapper.readTree(data);

            long packageID = dataObject.get("packageID").asLong();
            // verify package status
            String packageStatus = tempPkgResgistrationDAO.getStatusOfTempPackage(packageID, partnerID);
            if (packageStatus != null) {
                if (!packageStatus.equalsIgnoreCase(YTPackageData.TEMPORARY_PACKAGE_STATUS_EDITING)) {
                    String pkgName = null;
                    JsonNode pkgNameNode = dataObject.get("packageName");
                    if (!pkgNameNode.isNull()) {
                        pkgName = pkgNameNode.asText();
                    }
                    String languageCode = dataObject.get("languageCode").asText();
                    boolean updated = tempPkgContentRegistrationDAO.updatePackageName(packageID, pkgName, languageCode);
                    if (updated) {
                        return "{\"success\": true}";
                    }
                    throw new IllegalArgumentException("Not Valid Input");
                }
                throw new IllegalArgumentException("Not Valid Status");
            }
        }
        throw new IllegalArgumentException("Not Valid Partner");
    }

    @Override
    @Transactional
    public String updateMultiDescription(HttpSession session, String data) throws IOException {
        YTSessionAccount account = YTSession.getAccountSession(session);
        String partnerID = account.getPartnerID();
        if (partnerID != null) {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode dataObject = mapper.readTree(data);

            long packageID = dataObject.get("packageID").asLong();
            // verify package status
            String packageStatus = tempPkgResgistrationDAO.getStatusOfTempPackage(packageID, partnerID);
            if (packageStatus != null) {
                if (!packageStatus.equalsIgnoreCase(YTPackageData.TEMPORARY_PACKAGE_STATUS_EDITING)) {
                    JsonNode mDesNode = dataObject.get("multiDescription");
                    String mDes = null;
                    if (!mDesNode.isNull()) {
                        TypeFactory typeFactory = mapper.getTypeFactory();
                        List<YTPackageMultiDescription> multiDescriptions = mapper.convertValue(mDesNode, typeFactory.constructCollectionType(List.class, YTPackageMultiDescription.class));
                        if (multiDescriptions.size() > 0) {
                            mDes = mapper.writeValueAsString(multiDescriptions);
                        }
                    }

                    String languageCode = dataObject.get("languageCode").asText();
                    boolean updated = tempPkgContentRegistrationDAO.updateMultiDescription(packageID, mDes, languageCode);
                    if (updated) {
                        return "{\"success\": true}";
                    }
                    throw new IllegalArgumentException("Not Valid Input");
                }
                throw new IllegalArgumentException("Not Valid Status");
            }
        }
        throw new IllegalArgumentException("Not Valid Partner");
    }

    @Override
    @Transactional
    public String activeContentLanguage(HttpSession session, String data) throws IOException {
        YTSessionAccount account = YTSession.getAccountSession(session);
        String partnerID = account.getPartnerID();
        if (partnerID != null) {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode dataObject = mapper.readTree(data);

            long packageID = dataObject.get("packageID").asLong();
            // verify package status
            String packageStatus = tempPkgResgistrationDAO.getStatusOfTempPackage(packageID, partnerID);
            if (packageStatus != null) {
                String languageCode = dataObject.get("languageCode").asText();
                boolean updated = tempPkgContentRegistrationDAO.activeLanguage(packageID, languageCode);
                if (updated) {
                    return "{\"success\": true}";
                }
                throw new IllegalArgumentException("Not Valid Input");
            }
        }
        throw new IllegalArgumentException("Not Valid Partner");
    }

    @Override
    @Transactional
    public String unActiveContentLanguage(HttpSession session, String data) throws IOException {
        YTSessionAccount account = YTSession.getAccountSession(session);
        String partnerID = account.getPartnerID();
        if (partnerID != null) {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode dataObject = mapper.readTree(data);

            long packageID = dataObject.get("packageID").asLong();
            // verify package status
            String packageStatus = tempPkgResgistrationDAO.getStatusOfTempPackage(packageID, partnerID);
            if (packageStatus != null) {
                String languageCode = dataObject.get("languageCode").asText();
                boolean updated = tempPkgContentRegistrationDAO.unactiveLanguage(packageID, languageCode);
                if (updated) {
                    return "{\"success\": true}";
                }
                throw new IllegalArgumentException("Not Valid Input");
            }
        }
        throw new IllegalArgumentException("Not Valid Partner");
    }

    @Override
    @Transactional
    public String activeEditing(HttpSession session, String data) throws IOException {
        YTSessionAccount account = YTSession.getAccountSession(session);
        String partnerID = account.getPartnerID();
        if (partnerID != null) {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode dataObject = mapper.readTree(data);

            long packageID = dataObject.get("packageID").asLong();
            // verify package status
            String packageStatus = tempPkgResgistrationDAO.getStatusOfTempPackage(packageID, partnerID);
            if (packageStatus != null) {
                if (packageStatus.equalsIgnoreCase(YTPackageData.TEMPORARY_PACKAGE_STATUS_EDITING)) {
                    boolean updated = tempPkgResgistrationDAO.activeDescriptionEditing(packageID);
                    if (updated) {
                        return "{\"success\": true}";
                    }
                    throw new IllegalArgumentException("Not Valid Input");
                }
                throw new IllegalArgumentException("Not Valid Status");
            }
        }
        throw new IllegalArgumentException("Not Valid Partner");
    }

    @Override
    @Transactional
    public String updateActivityLocation(HttpSession session, String data) throws IOException {
        YTSessionAccount account = YTSession.getAccountSession(session);
        String partnerID = account.getPartnerID();
        if (partnerID != null) {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode dataObject = mapper.readTree(data);

            long packageID = dataObject.get("packageID").asLong();
            // verify package status
            String packageStatus = tempPkgResgistrationDAO.getStatusOfTempPackage(packageID, partnerID);
            if (packageStatus != null) {
                String languageCode = dataObject.get("languageCode").asText();
                JsonNode locationNode = dataObject.get("locations");
                String activityLocationStr = null;
                boolean valid = true;
                if (!locationNode.isNull()) {
                    TypeFactory typeFactory = mapper.getTypeFactory();
                    List<YTPackageLocation> locations = mapper.convertValue(locationNode, typeFactory.constructCollectionType(List.class, YTPackageLocation.class));
                    if (locations.size() > 0) {
                        valid = false;
                        for (YTPackageLocation location : locations) {
                            String name = location.getName();
                            int nameL = name.length();

                            String addr = location.getAddr();
                            int addrL = addr.length();

                            String desc = location.getDesc();
                            int descL = -1;
                            if (desc != null) {
                                descL = desc.length();
                            }

                            BigDecimal lat = location.getLat();
                            BigDecimal lng = location.getLng();

                            if (nameL > 0 && nameL <= 60 && addrL > 0 && addrL <= 200 && (descL >= -1 && descL <= 300)
                                    && lat != null && lng != null) {
                                valid = true;
                            }
                        }

                        activityLocationStr = mapper.writeValueAsString(locations);
                    }
                }

                if (valid) {
                    boolean updated = tempPkgContentRegistrationDAO.updateActivityLocation(packageID, activityLocationStr, languageCode);
                    if (updated) {
                        return "{\"success\": true}";
                    }
                }
                throw new IllegalArgumentException("Not Valid Input");
            }
            throw new IllegalArgumentException("Not Valid Status");
        }
        throw new IllegalArgumentException("Not Valid Partner");
    }

    @Override
    @Transactional
    public String updateDepartureLocation(HttpSession session, String data) throws IOException {
        YTSessionAccount account = YTSession.getAccountSession(session);
        String partnerID = account.getPartnerID();
        if (partnerID != null) {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode dataObject = mapper.readTree(data);

            long packageID = dataObject.get("packageID").asLong();
            // verify package status
            String packageStatus = tempPkgResgistrationDAO.getStatusOfTempPackage(packageID, partnerID);
            if (packageStatus != null) {
                String languageCode = dataObject.get("languageCode").asText();
                JsonNode locationNode = dataObject.get("locations");
                String activityLocationStr = null;
                boolean valid = true;
                if (!locationNode.isNull()) {
                    TypeFactory typeFactory = mapper.getTypeFactory();
                    List<YTPackageLocation> locations = mapper.convertValue(locationNode, typeFactory.constructCollectionType(List.class, YTPackageLocation.class));
                    if (locations.size() > 0) {
                        valid = false;
                        for (YTPackageLocation location : locations) {
                            String name = location.getName();
                            int nameL = name.length();

                            String addr = location.getAddr();
                            int addrL = addr.length();

                            String desc = location.getDesc();
                            int descL = -1;
                            if (desc != null) {
                                descL = desc.length();
                            }

                            BigDecimal lat = location.getLat();
                            BigDecimal lng = location.getLng();

                            if (nameL > 0 && nameL <= 60 && addrL > 0 && addrL <= 200 && (descL >= -1 && descL <= 300)
                                    && lat != null && lng != null) {
                                valid = true;
                            }
                        }

                        activityLocationStr = mapper.writeValueAsString(locations);
                    }
                }

                if (valid) {
                    boolean updated = tempPkgContentRegistrationDAO.updateDepartureLocation(packageID, activityLocationStr, languageCode);
                    if (updated) {
                        return "{\"success\": true}";
                    }
                }
                throw new IllegalArgumentException("Not Valid Input");
            }
            throw new IllegalArgumentException("Not Valid Status");
        }
        throw new IllegalArgumentException("Not Valid Partner");
    }

    @Override
    @Transactional
    public String updatePickupLocation(HttpSession session, String data) throws IOException {
        YTSessionAccount account = YTSession.getAccountSession(session);
        String partnerID = account.getPartnerID();
        if (partnerID != null) {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode dataObject = mapper.readTree(data);

            long packageID = dataObject.get("packageID").asLong();
            // verify package status
            String packageStatus = tempPkgResgistrationDAO.getStatusOfTempPackage(packageID, partnerID);
            if (packageStatus != null) {
                String languageCode = dataObject.get("languageCode").asText();
                JsonNode locationNode = dataObject.get("locations");
                String activityLocationStr = null;
                boolean valid = true;
                if (!locationNode.isNull()) {
                    TypeFactory typeFactory = mapper.getTypeFactory();
                    List<YTPackageLocation> locations = mapper.convertValue(locationNode, typeFactory.constructCollectionType(List.class, YTPackageLocation.class));
                    if (locations.size() > 0) {
                        valid = false;
                        for (YTPackageLocation location : locations) {
                            String name = location.getName();
                            int nameL = name.length();

                            String addr = location.getAddr();
                            int addrL = addr.length();

                            String desc = location.getDesc();
                            int descL = -1;
                            if (desc != null) {
                                descL = desc.length();
                            }

                            BigDecimal lat = location.getLat();
                            BigDecimal lng = location.getLng();

                            if (nameL > 0 && nameL <= 60 && addrL > 0 && addrL <= 200 && (descL >= -1 && descL <= 300)
                                    && lat != null && lng != null) {
                                valid = true;
                            }
                        }

                        activityLocationStr = mapper.writeValueAsString(locations);
                    }
                }

                if (valid) {
                    boolean updated = tempPkgContentRegistrationDAO.updatePickupLocation(packageID, activityLocationStr, languageCode);
                    if (updated) {
                        return "{\"success\": true}";
                    }
                }
                throw new IllegalArgumentException("Not Valid Input");
            }
            throw new IllegalArgumentException("Not Valid Status");
        }
        throw new IllegalArgumentException("Not Valid Partner");
    }

    @Override
    @Transactional
    public String updateKeywords(HttpSession session, String data) throws IOException {
        YTSessionAccount account = YTSession.getAccountSession(session);
        String partnerID = account.getPartnerID();
        if (partnerID != null) {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode dataObject = mapper.readTree(data);

            long packageID = dataObject.get("packageID").asLong();
            // verify package status
            String packageStatus = tempPkgResgistrationDAO.getStatusOfTempPackage(packageID, partnerID);
            if (packageStatus != null) {
                String languageCode = dataObject.get("languageCode").asText();
                JsonNode kwNode = dataObject.get("kws");

                boolean valid = true;
                String kwStr = null;
                if (!kwNode.isNull()) {
                    TypeFactory typeFactory = mapper.getTypeFactory();
                    List<String> kws = mapper.convertValue(kwNode, typeFactory.constructCollectionType(List.class, String.class));
                    Set<String> keywords = new LinkedHashSet<>();
                    int max = kws.size();
                    if (max > 0) {
                        valid = false;
                        if (max <= 9) {
                            valid = true;
                            for (int i = 0; i < max; i++) {
                                String aW = kws.get(i);
                                if (aW.length() <= 20) {
                                    keywords.add(aW);
                                } else {
                                    valid = false;
                                    break;
                                }
                            }

                            if (valid) {
                                if (keywords.size() == max) {
                                    kwStr = mapper.writeValueAsString(keywords);
                                } else {
                                    valid = false;
                                }
                            }

                        }
                    }
                }

                if (valid) {
                    tempPkgContentRegistrationDAO.updateKeywords(packageID, kwStr, languageCode);
                    return "{\"success\": true}";
                }
                throw new IllegalArgumentException("Not Valid Input");
            }
            throw new IllegalArgumentException("Not Valid Status");
        }
        throw new IllegalArgumentException("Not Valid Partner");
    }

    @Override
    @Transactional
    public String updateGoogleDescription(HttpSession session, String data) throws IOException {
        YTSessionAccount account = YTSession.getAccountSession(session);
        String partnerID = account.getPartnerID();
        if (partnerID != null) {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode dataObject = mapper.readTree(data);

            long packageID = dataObject.get("packageID").asLong();
            // verify package status
            String packageStatus = tempPkgResgistrationDAO.getStatusOfTempPackage(packageID, partnerID);
            if (packageStatus != null) {
                String languageCode = dataObject.get("languageCode").asText();
                JsonNode descNode = dataObject.get("desc");
                if (!descNode.isNull()) {
                    String desc = descNode.asText();
                    int len = desc.length();

                    if (len == 0) {
                        desc = null;
                    }

                    tempPkgContentRegistrationDAO.updateSEODescription(packageID, desc, languageCode);
                    return "{\"success\": true}";
                }
                throw new IllegalArgumentException("Not Valid Input");
            }
        }
        throw new IllegalArgumentException("Not Valid Partner");
    }

    @Override
    @Transactional
    public String updateServingLanguage(HttpSession session, String data) throws IOException {
        YTSessionAccount account = YTSession.getAccountSession(session);
        String partnerID = account.getPartnerID();
        if (partnerID != null) {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode dataObject = mapper.readTree(data);

            long packageID = dataObject.get("packageID").asLong();
            // verify package status
            String packageStatus = tempPkgResgistrationDAO.getStatusOfTempPackage(packageID, partnerID);
            if (packageStatus != null) {
                JsonNode servingNode = dataObject.get("servingLanguages");
                String servingLanguageStr = null;
                if (!servingNode.isNull()) {
                    TypeFactory typeFactory = mapper.getTypeFactory();
                    List<String> servingLanguages = mapper.convertValue(servingNode, typeFactory.constructCollectionType(List.class, String.class));

                    if (servingLanguages.size() > 0) {
                        servingLanguageStr = mapper.writeValueAsString(servingLanguages);
                    }
                }
                tempPkgResgistrationDAO.updateServingLanguage(packageID, servingLanguageStr);
                return "{\"success\": true}";
            }
        }
        throw new IllegalArgumentException("Not Valid Partner");
    }

}
