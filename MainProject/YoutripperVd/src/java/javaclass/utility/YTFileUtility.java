/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaclass.utility;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.CopyObjectRequest;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javaclass.common.YTData;
import javax.imageio.ImageIO;
import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MultipartFile;
import yt.globalexception.GlobalExceptionHandler;
import yt.javaclass.config.YTDataConfiguration;

/**
 *
 * @author nickn
 */
public class YTFileUtility {

    public static final String IMAGE_EXTENSION = "jpg";
    public static final String PDF_EXTENSION = "pdf";

    public static final String JPG_IMAGE_CONTENT_TYPE = "image/jpeg";
    public static final String PNG_IMAGE_CONTENT_TYPE = "image/png";
    public static final String VIDEO_CONTENT_TYPE = "video/mp4";
    public static final String PDF_CONTENT_TYPE = "application/pdf";

    private static final String AMAZON_KEY_ID = "AKIAJH6Y4R2ETD6XPOIA";
    private static final String AMAZON_KEY_SECRET = "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";
    private static final String DEFAULT_CACHE_TIME = "public,max-age=604800";

    private static final AmazonS3 S3_CLIENT = new AmazonS3Client(new BasicAWSCredentials(
            AMAZON_KEY_ID,
            AMAZON_KEY_SECRET));

    public static String getPortraitPhoto(String portrait, String promotionPortrait, boolean onPromotional) {
        if (portrait != null) {
            if (onPromotional && promotionPortrait != null) {
                portrait = promotionPortrait;
            }
            return YTDataConfiguration.getCDN_URL_LINK() + "/" + YTData.CDN_ACTIVITY_FOLDER + "/" + portrait;
        }
        return null;
    }

    public static String getInvoiceLink() {
        return YTDataConfiguration.getCDN_URL_LINK() + "/" + YTData.CDN_INVOICE_FOLDER + "/";
    }

    public static String getPackageRegistrationLink() {
        return YTDataConfiguration.getCDN_URL_LINK() + "/" + YTData.CDN_ACTIVITY_REGISTRATION_FOLDER + "/";
    }

    public static String getTempPhotoLink() {
        return YTDataConfiguration.getCDN_URL_LINK() + "/" + YTData.CDN_IMAGE_TEMP_FOLDER + "/";
    }

    public static String getPartnerPersonalFileLink() {
        return YTDataConfiguration.getCDN_URL_LINK() + "/" + YTData.CDN_PARTNER_VERIFICATION_FOLDER + "/";
    }

    public static String getPartnerBankLink() {
        return YTDataConfiguration.getCDN_URL_LINK() + "/" + YTData.CDN_PARTNER_BANK_FOLDER + "/";
    }

    public static String getCertificateLink() {
        return YTDataConfiguration.getCDN_URL_LINK() + "/" + YTData.CDN_PARTNER_CERTIFICATE_FOLDER + "/";
    }

    public static String getDefaultSmallAvatar() {
        return YTDataConfiguration.getCommonImageURL() + "small-default-avatar.jpg";
    }

    public static BufferedImage getBufferedImageFromInputStream(InputStream is) throws IOException {
        BufferedImage bi = ImageIO.read(is);
        return bi;
    }

    public static BufferedImage getBufferedImageFromFile(MultipartFile file) throws IOException {
        if (file != null) {
            String contentType = file.getContentType();
            boolean isJPG = contentType.equals(JPG_IMAGE_CONTENT_TYPE);
            boolean isPNG = contentType.equals(PNG_IMAGE_CONTENT_TYPE);
            if (isJPG || isPNG) {
                InputStream is = file.getInputStream();
                BufferedImage bufferedImage = ImageIO.read(is);
                if (isPNG) {
                    int width = bufferedImage.getWidth();
                    int height = bufferedImage.getHeight();

                    BufferedImage tempBuffered = new BufferedImage(width,
                            height, BufferedImage.TYPE_INT_RGB);
                    tempBuffered.createGraphics().drawImage(bufferedImage, 0, 0, Color.WHITE, null);

                    return tempBuffered;
                }
                return bufferedImage;
            }
        }
        return null;
    }

    public static byte[] getBytesFromBufferedImage(BufferedImage bi) throws IOException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ImageIO.write(bi, IMAGE_EXTENSION, os);
        os.flush();
        byte[] bytes = os.toByteArray();
        os.close();
        return bytes;
    }

    public static byte[] getBytesFromBufferedImageAsPNG(BufferedImage bi) throws IOException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ImageIO.write(bi, "png", os);
        os.flush();
        byte[] bytes = os.toByteArray();
        os.close();
        return bytes;
    }

    public static BufferedImage getBufferedImageFromByteArray(byte[] bytes) throws IOException {
        InputStream is = new ByteArrayInputStream(bytes);
        BufferedImage bi = ImageIO.read(is);
        return bi;
    }

    public static BufferedImage makeRoundedCorner(BufferedImage image, int cornerRadius) {
        int w = image.getWidth();
        int h = image.getHeight();
        BufferedImage output = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2 = output.createGraphics();

        g2.setComposite(AlphaComposite.Src);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.WHITE);
        g2.fill(new RoundRectangle2D.Float(0, 0, w, h, cornerRadius, cornerRadius));

        // ... then compositing the image on top,
        // using the white shape from above as alpha source
        g2.setComposite(AlphaComposite.SrcAtop);
        g2.drawImage(image, 0, 0, null);

        g2.dispose();

        return output;
    }

    public static void uploadImageToCDN(BufferedImage bi, String fileName, String contentType, String extension, boolean cache,
            String folder) throws IOException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ImageIO.write(bi, extension, os);
        byte[] bytes = os.toByteArray();
        InputStream is = new ByteArrayInputStream(bytes);
        long contentLength = bytes.length;

        ObjectMetadata meta = new ObjectMetadata();
        meta.setContentLength(contentLength);
        meta.setContentType(contentType);
        if (cache) {
            meta.setCacheControl(DEFAULT_CACHE_TIME);
        }

        folder = folder + "/" + fileName;

        String bucket = YTDataConfiguration.getCDN_FOLDER();

        PutObjectRequest objectRequest = new PutObjectRequest(bucket, folder, is, meta)
                .withCannedAcl(CannedAccessControlList.PublicRead);

        S3_CLIENT.putObject(objectRequest);
    }

    public static void uploadFileToCDN(byte[] bytes, String fileName, String contentType, boolean cache,
            String folder) throws IOException {
        InputStream is = new ByteArrayInputStream(bytes);
        long contentLength = bytes.length;

        ObjectMetadata meta = new ObjectMetadata();
        meta.setContentLength(contentLength);
        meta.setContentType(contentType);
        if (cache) {
            meta.setCacheControl(DEFAULT_CACHE_TIME);
        }

        folder = folder + "/" + fileName;

        String bucket = YTDataConfiguration.getCDN_FOLDER();

        PutObjectRequest objectRequest = new PutObjectRequest(bucket, folder, is, meta)
                .withCannedAcl(CannedAccessControlList.PublicRead);

        S3_CLIENT.putObject(objectRequest);
    }

    public static void uploadMFileToCDN(MultipartFile file, String fileName, boolean cache,
            String folder) throws IOException {
        InputStream is = file.getInputStream();
        long contentLength = file.getSize();
        String contentType = file.getContentType();

        ObjectMetadata meta = new ObjectMetadata();
        meta.setContentLength(contentLength);
        meta.setContentType(contentType);
        if (cache) {
            meta.setCacheControl(DEFAULT_CACHE_TIME);
        }

        folder = folder + "/" + fileName;

        String bucket = YTDataConfiguration.getCDN_FOLDER();
        PutObjectRequest objectRequest = new PutObjectRequest(bucket, folder, is, meta)
                .withCannedAcl(CannedAccessControlList.PublicRead);

        S3_CLIENT.putObject(objectRequest);
    }

    public static BufferedImage getBufferedImageFromCDN(String fileName, String folder) throws IOException {
        BufferedImage bi = null;
        String fileURL = folder + "/" + fileName;
        String bucket = YTDataConfiguration.getCDN_FOLDER();
        boolean exists = S3_CLIENT.doesObjectExist(bucket, fileURL);
        if (exists) {
            S3Object object = S3_CLIENT.getObject(
                    new GetObjectRequest(bucket, fileURL));
            InputStream is = object.getObjectContent();
            bi = getBufferedImageFromInputStream(is);
        }
        return bi;
    }

    public static BufferedImage getBufferedImageOfDefaultAvatar() throws IOException {
        BufferedImage bi = null;
        String fileURL = YTData.CDN_ACTIVITY_FOLDER + "/small-default-avatar.jpg";
        String bucket = YTDataConfiguration.getCDN_FOLDER();
        boolean exists = S3_CLIENT.doesObjectExist(bucket, fileURL);
        if (exists) {
            S3Object object = S3_CLIENT.getObject(
                    new GetObjectRequest(bucket, fileURL));
            InputStream is = object.getObjectContent();
            bi = getBufferedImageFromInputStream(is);
        }
        return bi;
    }

    public static void deleteFileFromCDN(String fileName, String folder) {
        try {
            String deletedURL = folder + "/" + fileName;
            String bucket = YTDataConfiguration.getCDN_FOLDER();
            boolean exists = S3_CLIENT.doesObjectExist(bucket, deletedURL);
            if (exists) {
                DeleteObjectRequest objectRequest = new DeleteObjectRequest(bucket, deletedURL);
                S3_CLIENT.deleteObject(objectRequest);
            }
        } catch (Exception e) {
            String inputs = "fileName: " + fileName + "\n"
                    + "folder: " + folder + "\n";
            GlobalExceptionHandler.logUtilityError(e, inputs);
        }
    }

    public static boolean cloneTempFile(String fileName, String destinationFolder) {
        String tempFolder = YTData.CDN_IMAGE_TEMP_FOLDER;
        String oldURL = tempFolder + "/" + fileName;
        //        
        String newURL = destinationFolder + "/" + fileName;

        String bucket = YTDataConfiguration.getCDN_FOLDER();
        boolean exists = S3_CLIENT.doesObjectExist(bucket, oldURL);
        if (exists) {
            // if exists then copy
            CopyObjectRequest objectRequest = new CopyObjectRequest(bucket, oldURL, bucket, newURL)
                    .withCannedAccessControlList(CannedAccessControlList.PublicRead);
            S3_CLIENT.copyObject(objectRequest);
            return true;
        }
        return false;
    }

    public synchronized static String generateFileName(String extension) throws InterruptedException {
        long currentMilli = YTDateTimeUtility.getCurrentTimeInMilli();
        Thread.sleep(5);
        String name = "yt-" + currentMilli;
        name = DigestUtils.md5DigestAsHex(name.getBytes()) + "." + extension;
        return name;
    }

    public synchronized static String generateInvoiceFileName(String invoiceID) throws InterruptedException {
        long currentMilli = YTDateTimeUtility.getCurrentTimeInMilli();
        Thread.sleep(5);
        String name = "yt-" + invoiceID + "-" + currentMilli;
        name = DigestUtils.md5DigestAsHex(name.getBytes()) + "." + PDF_EXTENSION;
        return name;
    }

}
