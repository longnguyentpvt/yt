/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.func.packageregistration.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.List;
import javaclass.common.ytpackage.YTPackageData;
import javaclass.common.YTSession;
import javaclass.common.YTSessionAccount;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import yt.entity.tbl.TargetLocationContent;
import yt.entity.tbl.TemporaryPackage;
import yt.func.packageregistration.dao.TargetLocationDAO;
import yt.func.packageregistration.dao.TemporaryPackageDAO;
import yt.func.packageregistration.dao.YTSubCategoryDAO;
import yt.func.packageregistration.javaclass.CategoryStepData;

/**
 *
 * @author nickn
 */
public class CategoryRegistrationServiceImpl implements CategoryRegistrationService {

    @Autowired
    private TemporaryPackageDAO tempPkgResgistrationDAO;

    @Autowired
    private TargetLocationDAO pkgTargetLocationRegistrationDAO;

    @Autowired
    private YTSubCategoryDAO pkgSubCategoryRegistrationDAO;

    @Override
    @Transactional
    public String getCategoryData(HttpSession session, String data) throws IOException {
        YTSessionAccount account = YTSession.getAccountSession(session);
        String partnerID = account.getPartnerID();
        if (partnerID != null) {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode dataObject = mapper.readTree(data);

            long packageID = dataObject.get("packageID").asLong();
            TemporaryPackage ytPackage = tempPkgResgistrationDAO.getCategoryInfoOfPackage(packageID, partnerID);
            if (ytPackage != null) {
                String localeCode = dataObject.get("localeCode").asText();

                List<TargetLocationContent> targetLocations = pkgTargetLocationRegistrationDAO.getList(localeCode);

                CategoryStepData responseData = new CategoryStepData(YTPackageData.PACKAGE_COLORS, targetLocations, ytPackage);

                return mapper.writeValueAsString(responseData);
            }
        }
        throw new IllegalArgumentException("Not Valid Partner");
    }

    @Override
    @Transactional
    public String getSubCategoryByCategory(String data) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode dataObject = mapper.readTree(data);
        String localeCode = dataObject.get("localeCode").asText();
        String categoryID = dataObject.get("categoryID").asText();

        List<String[]> subCategories = pkgSubCategoryRegistrationDAO.getSubCategoryByCategory(categoryID, localeCode);

        return mapper.writeValueAsString(subCategories);
    }

    @Override
    @Transactional
    public String updateCategoryAndSubcategory(HttpSession session, String data) throws IOException {
        YTSessionAccount account = YTSession.getAccountSession(session);
        String partnerID = account.getPartnerID();

        ObjectMapper mapper = new ObjectMapper();
        JsonNode dataObject = mapper.readTree(data);

        long packageID = dataObject.get("packageID").asLong();

        String categoryID = null;
        JsonNode categoryNode = dataObject.get("categoryID");
        if (!categoryNode.isNull()) {
            categoryID = categoryNode.asText();
        }

        JsonNode subCategoryNode = dataObject.get("subCategoryID");
        String subCategoryID = null;
        if (!subCategoryNode.isNull()) {
            subCategoryID = subCategoryNode.asText();
        }

        JsonNode otherSubCategoryNode = dataObject.get("otherSubCategory");
        String otherSubCategory = null;
        if (!otherSubCategoryNode.isNull()) {
            otherSubCategory = otherSubCategoryNode.asText().trim();
            if (otherSubCategory.isEmpty()) {
                otherSubCategory = null;
            }
        }

        if (subCategoryID == null) {
            otherSubCategory = null;
        }

        boolean success = tempPkgResgistrationDAO.updateCategoryAndSubCategory(partnerID, packageID, categoryID, subCategoryID, otherSubCategory);
        if (success) {
            return "{\"success\": true}";
        }
        throw new IllegalArgumentException("Category And SubCategory Updating Fail");
    }

    @Override
    @Transactional
    public String updateSuitability(HttpSession session, String data) throws IOException {
        YTSessionAccount account = YTSession.getAccountSession(session);
        String partnerID = account.getPartnerID();

        ObjectMapper mapper = new ObjectMapper();
        JsonNode dataObject = mapper.readTree(data);

        long packageID = dataObject.get("packageID").asLong();

        boolean suitableCouples = dataObject.get("suitableCouples").asBoolean();
        boolean suitableElderly = dataObject.get("suitableElderly").asBoolean();
        boolean suitableFamily = dataObject.get("suitableFamily").asBoolean();
        boolean suitableIndividual = dataObject.get("suitableIndividual").asBoolean();
        boolean suitableUniversal = dataObject.get("suitableUniversal").asBoolean();

        String genderSuitability = null;
        JsonNode genderNode = dataObject.get("genderSuitability");
        if (!genderNode.isNull()) {
            genderSuitability = genderNode.asText();
        }

        boolean valid = true;
        if (suitableUniversal) {
            if (suitableCouples || suitableElderly || suitableFamily || suitableFamily || genderSuitability != null) {
                valid = false;
            }
        } else if (!suitableIndividual && genderSuitability != null) {
            valid = false;
        }

        if (valid) {
            boolean success = tempPkgResgistrationDAO.updateSuitability(partnerID, packageID, suitableCouples, suitableElderly, suitableFamily, suitableIndividual, suitableUniversal, genderSuitability);
            if (success) {
                return "{\"success\": true}";
            }
            throw new IllegalArgumentException("Suitability Updating Fail");
        }
        throw new IllegalArgumentException("Invalid Suitability Data");
    }

    @Override
    @Transactional
    public String updateTargetLocation(HttpSession session, String data) throws IOException {
        YTSessionAccount account = YTSession.getAccountSession(session);
        String partnerID = account.getPartnerID();

        ObjectMapper mapper = new ObjectMapper();
        JsonNode dataObject = mapper.readTree(data);

        long packageID = dataObject.get("packageID").asLong();
        Integer targetLocationID = null;
        JsonNode targetLocationNode = dataObject.get("targetLocationID");
        if (!targetLocationNode.isNull()) {
            targetLocationID = targetLocationNode.asInt();
        }

        boolean success = tempPkgResgistrationDAO.updateTargetLocaiton(partnerID, packageID, targetLocationID);
        if (success) {
            return "{\"success\": true}";
        }
        throw new IllegalArgumentException("Target Location Updating Fail");
    }

    @Override
    @Transactional
    public String updatePackageColor(HttpSession session, String data) throws IOException {
        YTSessionAccount account = YTSession.getAccountSession(session);
        String partnerID = account.getPartnerID();

        ObjectMapper mapper = new ObjectMapper();
        JsonNode dataObject = mapper.readTree(data);

        long packageID = dataObject.get("packageID").asLong();
        String colorCode = null;
        JsonNode colorCodeNode = dataObject.get("colorCode");
        if (!colorCodeNode.isNull()) {
            colorCode = colorCodeNode.asText();
        }

        if (colorCode != null) {
            boolean success = tempPkgResgistrationDAO.updatePackageColor(partnerID, packageID, colorCode);
            if (success) {
                return "{\"success\": true}";
            }
            throw new IllegalArgumentException("Color Updating Fail");
        }
        throw new IllegalArgumentException("Invalid Color Data");
    }
}
