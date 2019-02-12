/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.func.packageregistration.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javaclass.common.YTData;
import javaclass.common.YTFileMeta;
import javaclass.common.YTSession;
import javaclass.common.YTSessionAccount;
import javaclass.utility.YTDateTimeUtility;
import javaclass.utility.YTFileUtility;
import javax.servlet.http.HttpSession;
import org.imgscalr.Scalr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import yt.entity.tbl.TemporaryPackage;
import yt.entity.tbl.TemporaryPackagePicture;
import yt.func.packageregistration.dao.TemporaryPackageDAO;
import yt.func.packageregistration.dao.TemporaryPackagePictureDAO;
import yt.func.packageregistration.javaclass.CoverUploadResponse;
import yt.func.packageregistration.javaclass.PhotoStepData;
import yt.func.packageregistration.javaclass.RegistrationCommonData;

/**
 *
 * @author nickn
 */
public class PhotoRegistrationServiceImpl implements PhotoRegistrationService {

    @Autowired
    private TemporaryPackageDAO tempPkgResgistrationDAO;

    @Autowired
    private TemporaryPackagePictureDAO tempPkgPictureResgistrationDAO;

    @Override
    @Transactional
    public String getPhotoData(HttpSession session, String data) throws IOException {
        YTSessionAccount account = YTSession.getAccountSession(session);
        String partnerID = account.getPartnerID();
        if (partnerID != null) {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode dataObject = mapper.readTree(data);

            long packageID = dataObject.get("packageID").asLong();

            TemporaryPackage ytPackage = tempPkgResgistrationDAO.getPhotoInfoOfPackage(packageID, partnerID);
            if (ytPackage != null) {
                List<TemporaryPackagePicture> pictures = tempPkgPictureResgistrationDAO.getPackagePictures(packageID);

                String urlLink = YTFileUtility.getPackageRegistrationLink();
                String cvType = ytPackage.getCoverType();
                String fCover = ytPackage.getFirstCover();
                String sCover = ytPackage.getSecondCover();
                String tCover = ytPackage.getThirdCover();
                String vCover = ytPackage.getVideoCover();
                String portrait = ytPackage.getPortraitPhoto();
                String fThumb = ytPackage.getFirstThumbnail();
                String sThumb = ytPackage.getSecondThumbnail();
                String tThumb = ytPackage.getThirdCover();
                String foThumb = ytPackage.getFourthThumbnail();
                String fiThumb = ytPackage.getFifthThumbnail();

                PhotoStepData rData = new PhotoStepData(urlLink, cvType, fCover, sCover, tCover, vCover, portrait, fThumb, sThumb, tThumb, foThumb, fiThumb, pictures);
                return mapper.writeValueAsString(rData);
            }
        }
        throw new IllegalArgumentException("Not Valid Partner");
    }

    @Override
    public String uploadCoverPhoto(HttpSession session, MultipartFile file) throws IOException {
        BufferedImage fileBI = YTFileUtility.getBufferedImageFromFile(file);
        boolean success = false;
        int responseW = 0, responseH = 0, tempIndex = -1, range = 0;

        if (fileBI != null) {
            int fW = fileBI.getWidth();
            int fH = fileBI.getHeight();

            int coverW = YTData.ACTIVITY_COVER_WIDTH;
            int coverH = YTData.ACTIVITY_COVER_HEIGHT;
            responseW = coverW;

            int itW = RegistrationCommonData.CSS_COVER_WIDTH;
            int itH = RegistrationCommonData.CSS_COVER_HEIGHT;

            int resizedW = (int) Math.ceil(((float) fW * (float) itW) / (float) coverW);
            BufferedImage resizedBI = Scalr.resize(fileBI, Scalr.Method.QUALITY, Scalr.Mode.FIT_TO_WIDTH, resizedW);
            int resizedH = resizedBI.getHeight();
            responseH = (int) Math.ceil(((float) responseW * (float) resizedH) / (float) resizedW);
            if (responseH < itH) {
                responseH = itH;
                responseW = (int) Math.ceil(((float) resizedW * (float) responseH) / (float) resizedH);
            }

            range = responseW - itW;
            // check height after resize w
            int rangeH = (int) Math.ceil(((float) responseH * (float) itW) / (float) responseW);
            if (rangeH < itH) {
                int rangeW = (int) Math.ceil(((float) itH * (float) responseW) / (float) responseH);
                range = responseW - rangeW;
            }

            byte[] realBytes = YTFileUtility.getBytesFromBufferedImage(fileBI);
            byte[] bytes = YTFileUtility.getBytesFromBufferedImage(resizedBI);
            if (bytes != null && realBytes != null) {
                success = true;

                String name = YTDateTimeUtility.getCurrentTimeInMilli() + "." + YTFileUtility.IMAGE_EXTENSION;

                YTFileMeta fileMeta = new YTFileMeta(name, YTFileUtility.JPG_IMAGE_CONTENT_TYPE, bytes, realBytes);

                List<YTFileMeta> files = YTSession.getFileMetas(session);
                if (files == null) {
                    files = new ArrayList<>();
                }
                files.add(fileMeta);
                tempIndex = files.size() - 1;

                YTSession.setListFileMeta(session, files);
            }
        }

        CoverUploadResponse uploadResponse = new CoverUploadResponse(success, responseW, responseH, tempIndex, range);
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(uploadResponse);
    }

    @Override
    @Transactional
    public String cropCoverAndSave(HttpSession session, String data) throws IOException, InterruptedException {
        YTSessionAccount account = YTSession.getAccountSession(session);
        String partnerID = account.getPartnerID();
        if (partnerID != null) {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode dataObject = mapper.readTree(data);

            long packageID = dataObject.get("packageID").asLong();

            TemporaryPackage ytPackage = tempPkgResgistrationDAO.getPhotoInfoOfPackage(packageID, partnerID);
            if (ytPackage != null) {
                int coverI = dataObject.get("coverI").asInt();

                List<YTFileMeta> files = YTSession.getFileMetas(session);
                YTFileMeta fileMeta = files.get(coverI);

                byte[] bytes = fileMeta.getRealBytes();
                BufferedImage bi = YTFileUtility.getBufferedImageFromByteArray(bytes);
                int realW = bi.getWidth(), realH = bi.getHeight();

                int resizedW = dataObject.get("resizedW").asInt();
                int resisedH = (int) Math.ceil(((float) resizedW * (float) realH) / (float) realW);

                int cropX = -dataObject.get("left").asInt();
                int cropY = -dataObject.get("top").asInt();

                int x = (int) Math.ceil(((float) cropX * (float) realW) / (float) resizedW);
                int y = (int) Math.ceil(((float) cropY * (float) realH) / (float) resisedH);

                int itW = RegistrationCommonData.CSS_COVER_WIDTH;
                int itH = RegistrationCommonData.CSS_COVER_HEIGHT;

                int cropW = (int) Math.ceil(((float) itW * (float) realW) / (float) resizedW);
                int cropH = (int) Math.ceil(((float) itH * (float) realH) / (float) resisedH);

                int overx = (x + cropW) - realW;
                int overy = (y + cropH) - realH;
                if (overx > 0) {
                    x = x - overx;
                }

                if (overy > 0) {
                    y = y - overy;
                }

                BufferedImage cropBI = Scalr.crop(bi, x, y, cropW, cropH);

                // then resize
                int coverW = YTData.ACTIVITY_COVER_WIDTH;
                int coverH = YTData.ACTIVITY_COVER_HEIGHT;
                cropBI = Scalr.resize(cropBI, Scalr.Method.ULTRA_QUALITY, Scalr.Mode.FIT_TO_WIDTH, coverW);
                int newH = cropBI.getHeight();
                if (newH > coverH) {
                    cropBI = Scalr.crop(cropBI, 0, 0, coverW, coverH);
                }

                int coverIndex = dataObject.get("cover").asInt();
                String extension = YTFileUtility.IMAGE_EXTENSION;
                String coverName = YTFileUtility.generateFileName(extension);
                String folder = YTData.CDN_ACTIVITY_REGISTRATION_FOLDER;

                YTFileUtility.uploadImageToCDN(cropBI, coverName, YTFileUtility.JPG_IMAGE_CONTENT_TYPE, extension, true, folder);
                String oldCover = null;
                switch (coverIndex) {
                    case 0:
                        oldCover = tempPkgResgistrationDAO.updateFirstCover(packageID, coverName);
                        break;
                    case 1:
                        oldCover = tempPkgResgistrationDAO.updateSecondCover(packageID, coverName);
                        break;
                    case 2:
                        oldCover = tempPkgResgistrationDAO.updateThirdCover(packageID, coverName);
                        break;
                }
                if (oldCover != null) {
                    YTFileUtility.deleteFileFromCDN(oldCover, folder);
                }

                return "{\"result\" : \"" + coverName + "\"}";
            }
        }
        throw new IllegalArgumentException("Not Valid Partner");
    }

    @Override
    public String uploadPortraitPhoto(HttpSession session, MultipartFile file) throws IOException {
        BufferedImage fileBI = YTFileUtility.getBufferedImageFromFile(file);
        boolean success = false;
        int responseW = 0, responseH = 0, tempIndex = -1, range = 0;

        if (fileBI != null) {
            int fW = fileBI.getWidth();
            int fH = fileBI.getHeight();

            int thumbW = YTData.ACTIVITY_MAIN_THUMB_WIDTH;
            int thumbH = YTData.ACTIVITY_MAIN_THUMB_HEIGHT;

            int itW = RegistrationCommonData.CSS_MAIN_THUMB_WIDTH;
            int itH = RegistrationCommonData.CSS_MAIN_THUMB_HEIGHT;

            int resizedW = (int) Math.ceil(((float) fW * (float) itW) / (float) thumbW);
            BufferedImage resizedBI = Scalr.resize(fileBI, Scalr.Method.QUALITY, Scalr.Mode.FIT_TO_WIDTH, resizedW);
            int resizedH = resizedBI.getHeight();
            responseW = resizedW;
            if (resizedW < itW) {
                responseW = itW;
            }
            responseH = (int) Math.ceil(((float) responseW * (float) resizedH) / (float) resizedW);
            if (responseH < itH) {
                responseH = itH;
                responseW = (int) Math.ceil(((float) resizedW * (float) responseH) / (float) resizedH);
            }

            range = responseW - itW;
            // check height after resize w
            int rangeH = (int) Math.ceil(((float) responseH * (float) itW) / (float) responseW);
            if (rangeH < itH) {
                int rangeW = (int) Math.ceil(((float) itH * (float) responseW) / (float) responseH);
                range = responseW - rangeW;
            }

            byte[] realBytes = YTFileUtility.getBytesFromBufferedImage(fileBI);
            byte[] bytes = YTFileUtility.getBytesFromBufferedImage(resizedBI);
            if (bytes != null && realBytes != null) {
                success = true;

                String name = YTDateTimeUtility.getCurrentTimeInMilli() + "." + YTFileUtility.IMAGE_EXTENSION;

                YTFileMeta fileMeta = new YTFileMeta(name, YTFileUtility.JPG_IMAGE_CONTENT_TYPE, bytes, realBytes);

                List<YTFileMeta> files = YTSession.getFileMetas(session);
                if (files == null) {
                    files = new ArrayList<>();
                }
                files.add(fileMeta);
                tempIndex = files.size() - 1;

                YTSession.setListFileMeta(session, files);
            }
        }

        CoverUploadResponse uploadResponse = new CoverUploadResponse(success, responseW, responseH, tempIndex, range);
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(uploadResponse);
    }

}
