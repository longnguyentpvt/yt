/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.func.partnercertificate.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import javaclass.common.YTData;
import javaclass.common.YTSession;
import javaclass.common.YTSessionAccount;
import javaclass.utility.YTFileUtility;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import yt.func.partnercertificate.dao.PartnerCertificationDAO;
import yt.func.partnercertificate.javaclass.AdCertificateFile;
import yt.func.partnercertificate.javaclass.CDNLink;
import yt.func.partnercertificate.javaclass.PartnerCertificationData;

/**
 *
 * @author Hiep
 */
@Service
public class PartnerCertificationServiceImpl implements PartnerCertificationService {

    @Autowired
    PartnerCertificationDAO partnerCertificationDAO;

    @Override
    public String uploadTempFile(MultipartFile file, HttpSession session) throws IOException, InterruptedException {
        long size = file.getSize();

        String contentType = file.getContentType();
        boolean img = contentType.equals(YTFileUtility.JPG_IMAGE_CONTENT_TYPE) || contentType.equals(YTFileUtility.PNG_IMAGE_CONTENT_TYPE);
        String fn = null;
        String linkURL = null;
        boolean broken = true;
        if (size < PartnerCertificationData.MAX_FILE_SIZE) {
            if (img) {
                BufferedImage fileBI = null;
                try {
                    fileBI = YTFileUtility.getBufferedImageFromFile(file);
                } catch (Exception e) {
                }

                if (fileBI != null) {
                    int width = fileBI.getWidth();
                    int height = fileBI.getHeight();
                    if (width >= PartnerCertificationData.CERTIFICATION_IMG_WIDTH && height >= PartnerCertificationData.CSS_VERIFICATION__HEIGHT) {
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
                }
            }
        }

        if (!broken) {
            String[] rp = {fn, YTFileUtility.getTempPhotoLink() + fn};
            ObjectMapper mapper = new ObjectMapper();
            linkURL = mapper.writeValueAsString(rp);
        }
        return "{\"result\" : " + linkURL + "}";

    }

    @Override
    @Transactional
    public String saveCerification(String data, HttpSession session) throws IOException {
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
        String folder = YTData.CDN_PARTNER_CERTIFICATE_FOLDER;
        //uploading
        boolean success = YTFileUtility.cloneTempFile(imageFile, folder);
        if (success) {//if sucess insert DB
            partnerCertificationDAO.insertCertificate(partnerID, fileName, imageFile, PartnerCertificationData.FILE_UPLOADED);
            return "{\"result\" : \"" + imageFile + "\"}";
        }
        throw new IllegalArgumentException("Clone Fail");
    }

    @Override
    @Transactional
    public String deleteCerification(String data, HttpSession session) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode dataObject = mapper.readTree(data);
        //get file name
        Long fileID = dataObject.get("fileID").asLong();
        // get partnerID
        YTSessionAccount account = YTSession.getAccountSession(session);
        String partnerID = account.getPartnerID();
        //
        boolean success = partnerCertificationDAO.deleteImg(fileID, partnerID);
        return "{\"result\": " + success + "}";
    }

    @Override
    public String getCDNLink() throws IOException {
        String pcLink = YTFileUtility.getCertificateLink();
        String tempLink = YTFileUtility.getTempPhotoLink();
        CDNLink link = new CDNLink(pcLink, tempLink);
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(link);
    }

    @Override
    @Transactional
    public String loadCertificate(HttpSession session) throws IOException {
        YTSessionAccount account = YTSession.getAccountSession(session);
        String partnerID = account.getPartnerID();
        if (partnerID != null) {
            //get personal verification
            List<AdCertificateFile> files = partnerCertificationDAO.getFiles(partnerID);
            // loop to concate CDN link
            for (AdCertificateFile file : files) {
                String imgfile = file.getImgFile();
                String imgShow = null;
                if (imgfile != null) {
                    imgShow = YTFileUtility.getCertificateLink() + imgfile;
                }
                file.setImgShow(imgShow);
            }
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(files);
        }
        throw new IllegalArgumentException("Not Valid Partner");
    }

}
