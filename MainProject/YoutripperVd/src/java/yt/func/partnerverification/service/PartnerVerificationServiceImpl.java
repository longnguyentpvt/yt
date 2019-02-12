/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.func.partnerverification.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import javaclass.common.YTData;
import javaclass.common.YTPartnerData;
import javaclass.common.YTSession;
import javaclass.common.YTSessionAccount;
import javaclass.utility.YTFileUtility;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import yt.func.partnerverification.dao.PartnerDAO;
import yt.func.partnerverification.dao.PartnerBankFileDAO;
import yt.func.partnerverification.dao.PartnerPersonalFileDAO;
import yt.func.partnerverification.javaclass.AdBankFile;
import yt.func.partnerverification.javaclass.AdBankVerification;
import yt.func.partnerverification.javaclass.AdPersonalFile;
import yt.func.partnerverification.javaclass.AdPersonalVerification;
import yt.func.partnerverification.javaclass.BankCommonData;
import yt.func.partnerverification.javaclass.PartnerVerificationData;
import yt.func.partnerverification.javaclass.PartnerVerificationValidation;
import yt.func.partnerverification.javaclass.VerificationCommonData;
import yt.func.signup.javaclass.PartnerRegistrationValidation;

/**
 *
 * @author Hiep
 */
@Service
public class PartnerVerificationServiceImpl implements PartnerVerificationService {

    @Autowired
    private PartnerPersonalFileDAO partnerPersonalFileDAO;

    @Autowired
    private PartnerBankFileDAO partnerBankFileDAO;

    @Autowired
    private PartnerDAO verificationPartnerDAO;

    @Override
    @Transactional
    public String loadPersonal(HttpSession session) throws IOException {
        YTSessionAccount account = YTSession.getAccountSession(session);
        String partnerID = account.getPartnerID();
        if (partnerID != null) {
            //get personal verification
            List<AdPersonalFile> files = partnerPersonalFileDAO.getPersonalFiles(partnerID);
            //get common data
            VerificationCommonData verificationCommonData = verificationPartnerDAO.getVerificationData(partnerID);

            // verificate ID
            String verificationID = verificationCommonData.getVerificationID();

            // PSN CHECK SUBMITED
            String verificationStatus = verificationCommonData.getVerificationStatus();
            boolean isPSNSubmited = !verificationStatus.equalsIgnoreCase(YTPartnerData.ACCOUNT_VERIFICATION_STATUS_NOT_UPLOADED)
                    && !verificationStatus.equalsIgnoreCase(YTPartnerData.ACCOUNT_VERIFICATION_STATUS_FAIL);

            // loop to concate CDN link
            String cdnLink = YTFileUtility.getPartnerPersonalFileLink();
            for (AdPersonalFile file : files) {
                String imgfile = file.getImgFile();
                String imgShow = null;
                if (imgfile != null) {
                    imgShow = cdnLink + imgfile;
                }
                file.setImgShow(imgShow);
            }
            //set to advance class
            AdPersonalVerification apv = new AdPersonalVerification();
            apv.setIsPSNSubmited(isPSNSubmited);
            apv.setFiles(files);
            apv.setVerificationID(verificationID);
            //return
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(apv);
        }
        throw new IllegalArgumentException("Not Valid Partner");

    }

    @Override
    @Transactional
    public String loadBank(HttpSession session) throws IOException {
        YTSessionAccount account = YTSession.getAccountSession(session);
        String partnerID = account.getPartnerID();
        if (partnerID != null) {
            //get personal verification
            List<AdBankFile> files = partnerBankFileDAO.getBankFiles(partnerID);
            BankCommonData bankData = verificationPartnerDAO.getBankData(partnerID);
            // verificate ID
            String bankID = bankData.getBankID();
            String bankAccountName = bankData.getBankAccountName();
            String bankAccountNumber = bankData.getBankAccountNumber();
            // check submitted
            String bankAccountStatus = bankData.getBankAccountStatus();
            boolean isBankSubmited = !bankAccountStatus.equalsIgnoreCase(YTPartnerData.ACCOUNT_BANK_STATUS_NOT_UPLOADED)
                    && !bankAccountStatus.equalsIgnoreCase(YTPartnerData.ACCOUNT_BANK_STATUS_FAIL);

            // loop to concate CDN link
            String cdnLink = YTFileUtility.getPartnerBankLink();
            for (AdBankFile file : files) {
                String imgfile = file.getImgFile();
                String imgShow = null;
                if (imgfile != null) {
                    imgShow = cdnLink + imgfile;
                }
                file.setImgShow(imgShow);
            }
            //set to advance class
            AdBankVerification abv = new AdBankVerification();
            abv.setIsBankSubmited(isBankSubmited);
            abv.setFiles(files);
            abv.setBankID(bankID);
            abv.setBankAccountName(bankAccountName);
            abv.setBankAccountNumber(bankAccountNumber);
            //return
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(abv);
        }
        throw new IllegalArgumentException("Not Valid Partner");
    }

    @Override
    public String uploadTempFile(MultipartFile file, HttpSession session) throws InterruptedException, IOException {
        long size = file.getSize();

        String contentType = file.getContentType();
        boolean img = contentType.equals(YTFileUtility.JPG_IMAGE_CONTENT_TYPE) || contentType.equals(YTFileUtility.PNG_IMAGE_CONTENT_TYPE);
        boolean pdf = contentType.equals(YTFileUtility.PDF_CONTENT_TYPE);
        String fn = null;
        boolean broken = true;
        String fileURL = null;
        if (size < PartnerVerificationData.MAX_FILE_SIZE) {
            if (img) {
                BufferedImage fileBI = null;
                int width = 0;
                int height = 0;
                try {
                    fileBI = YTFileUtility.getBufferedImageFromFile(file);
                    width = fileBI.getWidth();
                    height = fileBI.getHeight();
                } catch (Exception e) {
                }
                if (width >= PartnerVerificationData.VERIFICATION_IMG_WIDTH && height >= PartnerVerificationData.VERIFICATION_IMG_WIDTH) {
                    //uploading
                    // config for uploading
                    String extension = YTFileUtility.IMAGE_EXTENSION;
                    fn = YTFileUtility.generateFileName(extension);
                    String folder = YTData.CDN_IMAGE_TEMP_FOLDER;
                    try {
                        YTFileUtility.uploadImageToCDN(fileBI, fn,
                                YTFileUtility.JPG_IMAGE_CONTENT_TYPE, extension, true, folder);
                        broken = false;
                    } catch (Exception e) {
                    }
                }
            } else if (pdf) {
                String extension = YTFileUtility.PDF_EXTENSION;
                fn = YTFileUtility.generateFileName(extension);
                String folder = YTData.CDN_IMAGE_TEMP_FOLDER;
                try {
                    YTFileUtility.uploadMFileToCDN(file, fn, true, folder);
                    broken = false;
                } catch (Exception e) {
                }
            }
        }

        if (!broken) {
            ObjectMapper mapper = new ObjectMapper();
            String[] rp = {fn, YTFileUtility.getTempPhotoLink() + fn};
            fileURL = mapper.writeValueAsString(rp);
        }
        return "{\"result\" : " + fileURL + "}";
    }

    @Override
    @Transactional
    public String savePersonalVerification(String data, HttpSession session) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode dataObject = mapper.readTree(data);
        //get file name
        String fileName = dataObject.get("fileName").asText();
        // get image file
        String imageFile = dataObject.get("imageFile").asText();
        // get partnerID      
        YTSessionAccount account = YTSession.getAccountSession(session);
        String partnerID = account.getPartnerID();
        // config for uploading
        String folder = YTData.CDN_PARTNER_VERIFICATION_FOLDER;

        //uploading
        boolean success = YTFileUtility.cloneTempFile(imageFile, folder);
        if (success) {//if sucess insert DB
            partnerPersonalFileDAO.insertPersonalFile(partnerID, fileName, imageFile, YTPartnerData.PARTNER_FILE_UPLOADED);
            return "{\"result\" : \"" + imageFile + "\"}";
        }
        throw new IllegalArgumentException("File not exists");
    }

    @Override
    @Transactional
    public String saveBankVerification(String data, HttpSession session) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode dataObject = mapper.readTree(data);
        //get file name
        String fileName = dataObject.get("fileName").asText();
        // get image file
        String imageFile = dataObject.get("imageFile").asText();
        // get partnerID      
        YTSessionAccount account = YTSession.getAccountSession(session);
        String partnerID = account.getPartnerID();
        // config for uploading
        String folder = YTData.CDN_PARTNER_BANK_FOLDER;

        //uploading
        boolean success = YTFileUtility.cloneTempFile(imageFile, folder);
        if (success) {//if sucess insert DB
            partnerBankFileDAO.insertBankFile(partnerID, fileName, imageFile, YTPartnerData.PARTNER_FILE_UPLOADED);
            return "{\"result\" : \"" + imageFile + "\"}";
        }
        throw new IllegalArgumentException("File not exists");
    }

    @Override
    @Transactional
    public String deletePersonalVerification(String data, HttpSession session) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode dataObject = mapper.readTree(data);
        //get file name
        Long fileID = dataObject.get("fileID").asLong();
        // get partnerID
        YTSessionAccount account = YTSession.getAccountSession(session);
        String partnerID = account.getPartnerID();
        // validate status
        String status = verificationPartnerDAO.getVerificationStatus(partnerID);
        if (status.equalsIgnoreCase(YTPartnerData.ACCOUNT_VERIFICATION_STATUS_NOT_UPLOADED)
                || status.equalsIgnoreCase(YTPartnerData.ACCOUNT_VERIFICATION_STATUS_FAIL)) {

            boolean success = partnerPersonalFileDAO.deleteImg(fileID, partnerID);
            if (success) {
                return "{\"result\": " + success + "}";
            }

            throw new IllegalArgumentException("Wrong Partner ID");
        }

        throw new IllegalArgumentException("Wrong Partner Status");
    }

    @Override
    @Transactional
    public String deleteBankVerification(String data, HttpSession session) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode dataObject = mapper.readTree(data);
        //get file name
        Long fileID = dataObject.get("fileID").asLong();
        // get partnerID
        YTSessionAccount account = YTSession.getAccountSession(session);
        String partnerID = account.getPartnerID();
        // validate status
        String status = verificationPartnerDAO.getBankStatus(partnerID);
        if (status.equalsIgnoreCase(YTPartnerData.ACCOUNT_BANK_STATUS_NOT_UPLOADED)
                || status.equalsIgnoreCase(YTPartnerData.ACCOUNT_BANK_STATUS_FAIL)) {
            boolean success = partnerBankFileDAO.deleteImg(fileID, partnerID);
            if (success) {
                return "{\"result\": " + success + "}";
            }

            throw new IllegalArgumentException("Wrong Partner ID");
        }

        throw new IllegalArgumentException("Wrong Partner Status");
    }

    @Override
    @Transactional
    public String loadCommonData(HttpSession session) throws IOException {
        //get common data
        VerificationCommonData verificationCommonData = new VerificationCommonData();
        String tempLink = YTFileUtility.getTempPhotoLink();
        verificationCommonData.setCdnTempLink(tempLink);
        //return
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(verificationCommonData);
    }

    @Override
    @Transactional
    public String submitPSN(String data, HttpSession session) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode dataObject = mapper.readTree(data);
        //get taxID
        JsonNode taxIDNode = dataObject.get("taxID");
        String taxID = null;
        if (!taxIDNode.isNull()) {
            taxID = taxIDNode.asText();
        }
        // get partnerID
        YTSessionAccount account = YTSession.getAccountSession(session);
        String partnerID = account.getPartnerID();

        String status = verificationPartnerDAO.getVerificationStatus(partnerID);
        if (status.equalsIgnoreCase(YTPartnerData.ACCOUNT_VERIFICATION_STATUS_NOT_UPLOADED)
                || status.equalsIgnoreCase(YTPartnerData.ACCOUNT_VERIFICATION_STATUS_FAIL)) {
            Boolean success = false;
            //check validate
            if (taxID != null && !taxID.isEmpty() && PartnerRegistrationValidation.checkIDNumberRegularIsCorect(taxID)) {
                // not exist, update taxID
                success = verificationPartnerDAO.updatePersonalVerification(partnerID, taxID, YTPartnerData.ACCOUNT_VERIFICATION_STATUS_PENDING);

                if (success) {
                    return "{\"result\": " + success + "}";
                }
                throw new IllegalArgumentException("Fail Update");
            }
            throw new IllegalArgumentException("Verification Fail");

        }
        throw new IllegalArgumentException("Wrong Status");
    }

    @Override
    @Transactional
    public String submitBank(String data, HttpSession session) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode dataObject = mapper.readTree(data);
        //get bankID
        JsonNode bankIDNode = dataObject.get("bankID");
        String bankID = null;
        String bankCodes[] = YTPartnerData.getPARTNER_BANK_CODES();
        boolean isExistBank = false;
        if (!bankIDNode.isNull()) {
            bankID = bankIDNode.asText();
            for (int i = 0; i < bankCodes.length; i++) {
                if (bankID.equalsIgnoreCase(bankCodes[i])) {
                    isExistBank = true;
                    break;
                }
            }
        }
        //get bank account name
        JsonNode bankAccountNameNode = dataObject.get("bankAccountName");
        String bankAccountName = null;
        if (!bankAccountNameNode.isNull()) {
            bankAccountName = bankAccountNameNode.asText();
        }
        //get bank account number
        JsonNode bankAccountNumberNode = dataObject.get("bankAccountNumber");
        String bankAccountNumber = null;
        if (!bankAccountNumberNode.isNull()) {
            bankAccountNumber = bankAccountNumberNode.asText();
        }
        // get partnerID
        YTSessionAccount account = YTSession.getAccountSession(session);
        String partnerID = account.getPartnerID();
        // VALIDATE STATUS
        String status = verificationPartnerDAO.getBankStatus(partnerID);

        if (status.equalsIgnoreCase(YTPartnerData.ACCOUNT_BANK_STATUS_NOT_UPLOADED)
                || status.equalsIgnoreCase(YTPartnerData.ACCOUNT_BANK_STATUS_FAIL)) {
            if (bankID != null && !bankID.isEmpty() && bankAccountName != null && PartnerVerificationValidation.checkBankAccountNameRegularIsCorrect(bankAccountName)
                    && bankAccountNumber != null && PartnerVerificationValidation.checkBankAccountNumberRegularIsCorrect(bankAccountNumber)
                    && isExistBank) {
                boolean success = verificationPartnerDAO.updateBankVerification(partnerID, bankID, bankAccountName, bankAccountNumber, YTPartnerData.ACCOUNT_BANK_STATUS_PENDING);
                if (success) {
                    return "{\"result\": " + success + "}";
                }
                throw new IllegalArgumentException("Fail Update");
            }
            throw new IllegalArgumentException("Fail Verification");
        }
        throw new IllegalArgumentException("Wrong Partner Status");
    }
}
