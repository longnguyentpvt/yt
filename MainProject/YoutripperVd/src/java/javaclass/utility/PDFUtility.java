/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaclass.utility;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import javaclass.common.YTData;
import javaclass.common.YTPackageInvoiceInfo;
import javaclass.common.YTPackageInvoiceOrder;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDFontDescriptor;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

/**
 *
 * @author nickn
 */
public class PDFUtility {

    private static final String VERTICAL_ALIGNMENT_MIDDLE = "middle";
    private static final String VERTICAL_ALIGNMENT_TOP = "top";
    private static final String VERTICAL_ALIGNMENT_BOTTOM = "bottom";

    private static final String HORIZONTAL_ALIGNMENT_CENTER = "center";
    private static final String HORIZONTAL_ALIGNMENT_LEFT = "left";
    private static final String HORIZONTAL_ALIGNMENT_RIGHT = "right";
    private static final File FONT_REGULAR = new File("C:\\newpdf\\Prompt-Regular.ttf");
    private static final File FONT_BOLD = new File("C:\\newpdf\\Prompt-Bold.ttf");

    private static final Color DARK_TXT_COLOR = Color.decode("#555555");
    private static final Color DARK_BLUE_COLOR = Color.decode("#2B2E40");
    private static final Color BLUE_COLOR = Color.decode("#4CBDC9");
    private static final Color RED_COLOR = Color.decode("#E94848");
    private static final Color GREY_COLOR = Color.decode("#9C9B9C");

    private static final String PARTNER_LOGO_64 = "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";

    private static float drawPDFtxt(String txt, float posX, float posY, PDFont txtFont, Color txtColor,
            PDPageContentStream contentStream, String vAlignment, String hAlignment, float fontSize,
            Float fixW, Float lineH) throws IOException {
        List<String> sens = new ArrayList<>();
        String[] brSens = txt.split("<br/>");
        for (String brSen : brSens) {
            if (fixW != null && fixW > 0) {
                String[] words = brSen.split(" ");
                String aSen = "";
                for (String word : words) {
                    String tempSen = aSen + word;
                    float tempW = (txtFont.getStringWidth(tempSen) / 1000.0f) * fontSize;
                    if (tempW >= fixW) {
                        String pSen = aSen + "";
                        sens.add(pSen);
                        aSen = word + " ";
                    } else {
                        aSen = tempSen + " ";
                    }
                }
                sens.add(aSen);
            } else {
                sens.add(brSen);
            }
        }

        PDFontDescriptor fDesc = txtFont.getFontDescriptor();
        float asc = fDesc.getAscent() / 1000f * fontSize - 1;
        float desc = -(fDesc.getDescent() / 1000f * fontSize);
        float wH = asc + desc;
        if (lineH == null || lineH == 0f) {
            lineH = wH;
        }
        int noSens = sens.size();
        float tHeight = (float) (noSens - 1) * lineH;

        float disY = 0;
        if (vAlignment != null) {
            switch (vAlignment) {
                case VERTICAL_ALIGNMENT_MIDDLE:
                    float sHeight = (float) (wH / 2f);
                    disY = sHeight - desc;
                    disY -= (tHeight / 2f);
//                    posY = posY - d;
//                    posY += (tHeight / 2f);
                    break;
                case VERTICAL_ALIGNMENT_TOP:
//                    posY = posY - asc;
                    disY = asc;
                    break;
                case VERTICAL_ALIGNMENT_BOTTOM:
                    disY = -desc;
                    disY = -tHeight;
//                    posY = posY + desc;
//                    posY += tHeight;
                    break;
            }
        }
        float endY = posY;
        posY -= disY;

        for (String sen : sens) {
            contentStream.beginText();
            contentStream.setNonStrokingColor(txtColor);
            contentStream.setFont(txtFont, fontSize);

            float x = posX;
            if (hAlignment != null) {
                sen = sen.trim();
                float txtW = (txtFont.getStringWidth(sen) / 1000.0f) * fontSize;
                switch (hAlignment) {
                    case HORIZONTAL_ALIGNMENT_CENTER:
                        x = x - (txtW / 2f);
                        break;
                    case HORIZONTAL_ALIGNMENT_RIGHT:
                        x = x - txtW;
                        break;
                }
            }

            contentStream.newLineAtOffset(x, posY);
            contentStream.showText(sen);
            contentStream.endText();
            posY -= lineH;
            endY -= lineH;
        }

        endY += lineH;
        return endY;
    }

    public static byte[] generateInvoicePDF(YTPackageInvoiceInfo invoiceInfo) throws IOException {
        // create pdf file
        PDDocument doc = new PDDocument();
        // load font for this file
        PDFont regularFont = PDType0Font.load(doc, FONT_REGULAR);

        // define size for pdf
        float pageWidth = PDRectangle.A4.getWidth();
        float pageHeight = PDRectangle.A4.getHeight();

        // defind colors
        PDPage page = new PDPage(new PDRectangle(pageWidth, pageHeight));
        doc.addPage(page);
        PDPageContentStream contentStream = new PDPageContentStream(doc, page);

        float paddingLR = 20f, xLeft = paddingLR, xRight = pageWidth - paddingLR, yTop = pageHeight, contentWidth = pageWidth - 2f * paddingLR;

        // draw header bg
        contentStream.setNonStrokingColor(DARK_BLUE_COLOR);
        contentStream.fillRect(0, yTop - 40, pageWidth, 40);
        drawPDFtxt("Invoice / Receipt", xRight, yTop - 20, regularFont, Color.WHITE, contentStream, VERTICAL_ALIGNMENT_MIDDLE, HORIZONTAL_ALIGNMENT_RIGHT, 14, null, null);

        // draw header logo
        byte[] logoBytes = null;
        String partnerAva = invoiceInfo.getPartnerLogo();
        if (partnerAva != null && partnerAva.isEmpty()) {
            BufferedImage logoBI = YTFileUtility.getBufferedImageFromCDN(partnerAva, YTData.CDN_PARTNER_PROFILE_FOLDER);
            if (logoBI != null) {
                logoBI = YTFileUtility.makeRoundedCorner(logoBI, 300);
                logoBytes = YTFileUtility.getBytesFromBufferedImageAsPNG(logoBI);
            }
        } else {
            logoBytes = Base64.getDecoder().decode(PARTNER_LOGO_64);
        }

        if (logoBytes != null) {
            PDImageXObject logo = PDImageXObject.createFromByteArray(doc, logoBytes, "logo.png");
            contentStream.drawImage(logo, xLeft, yTop - 32.5f, 25f, 25f);
        }

        String partnerCompanyName = invoiceInfo.getPartnerCompanyName();
        String partnerCompanyAddress = invoiceInfo.getPartnerAddress() + ", " + invoiceInfo.getPartnerState() + ", "
                + invoiceInfo.getPartnerPostalCode() + ", " + invoiceInfo.getPartnerCountry();
        String partnerContactInfo = "(+" + invoiceInfo.getPartnerPhoneCode() + ") " + invoiceInfo.getPartnerPhoneNo() + ", Registered No. " + invoiceInfo.getPartnerTaxID();
        String partnerInfo = partnerCompanyName + "<br/>"
                + partnerCompanyAddress
                + "<br/>"
                + partnerContactInfo;
        float headerLH = 9, startHeaderX = xLeft + 30f, headerFS = 6;
        drawPDFtxt(partnerInfo, startHeaderX, yTop - 20f, regularFont, Color.WHITE, contentStream, VERTICAL_ALIGNMENT_MIDDLE, HORIZONTAL_ALIGNMENT_LEFT, headerFS, 300f, headerLH);

        // draw footer
        float ftBottom = 20f, ftLH = 11, ftFS = 6, ftCenter = pageWidth / 2f;
        contentStream.setStrokingColor(GREY_COLOR);
        contentStream.drawLine(xLeft, ftBottom, xRight, ftBottom);

        ftBottom += 10f;
        drawPDFtxt("56 soi Seri Villa, Nongbon, Pravet, Bangkok 10250, Thailand Tel: +66-2101-3069, +66-2743-4000, Fax: +66-2361-5852, Registered No. 0105551043176",
                ftCenter, ftBottom, regularFont, GREY_COLOR, contentStream, VERTICAL_ALIGNMENT_BOTTOM, HORIZONTAL_ALIGNMENT_CENTER,
                ftFS, null, ftLH);

        ftBottom += ftLH;
        drawPDFtxt("Powered By YOU TRIPPER CO., LTD (Headquarter)",
                ftCenter, ftBottom, regularFont, GREY_COLOR, contentStream, VERTICAL_ALIGNMENT_BOTTOM, HORIZONTAL_ALIGNMENT_CENTER,
                ftFS, null, ftLH);

        ftBottom += ftLH + 20f;
        drawPDFtxt("(Long distance charge may apply)",
                xLeft, ftBottom, regularFont, GREY_COLOR, contentStream, VERTICAL_ALIGNMENT_BOTTOM, HORIZONTAL_ALIGNMENT_LEFT,
                ftFS, null, ftLH);

        ftBottom += ftLH;
        drawPDFtxt("Customer Support: +66-2101-3069",
                xLeft, ftBottom, regularFont, GREY_COLOR, contentStream, VERTICAL_ALIGNMENT_BOTTOM, HORIZONTAL_ALIGNMENT_LEFT,
                ftFS, null, ftLH);

        ftBottom += ftLH;
        drawPDFtxt("Please contact support@youtripper.com",
                xLeft, ftBottom, regularFont, GREY_COLOR, contentStream, VERTICAL_ALIGNMENT_BOTTOM, HORIZONTAL_ALIGNMENT_LEFT,
                ftFS, null, ftLH);

        ftBottom += ftLH;
        drawPDFtxt("Need help with your reservation or receipt?",
                xLeft, ftBottom, regularFont, BLUE_COLOR, contentStream, VERTICAL_ALIGNMENT_BOTTOM, HORIZONTAL_ALIGNMENT_LEFT,
                ftFS, null, ftLH);

        // tripper billing info
        float startLeftY = yTop - 40f - 15f, startRightY = startLeftY, commonFS = 9, commonNextLine = 14, commonLH = 11, commonLeftTTWidth = 70,
                startLeftV = xLeft + commonLeftTTWidth, commonLeftVWidth = 150,
                commonRightX = xLeft + contentWidth / 2f, commonRightTTWidth = 120f, startRightV = commonRightX + commonRightTTWidth;
        String tripperID = invoiceInfo.getTripperID();
        if (tripperID == null || tripperID.isEmpty()) {
            tripperID = "N/A";
        }
        drawPDFtxt("Tripper ID", xLeft, startLeftY, regularFont, BLUE_COLOR, contentStream, VERTICAL_ALIGNMENT_TOP, HORIZONTAL_ALIGNMENT_LEFT,
                commonFS, null, commonLH);
        drawPDFtxt(":", startLeftV, startLeftY, regularFont, BLUE_COLOR, contentStream, VERTICAL_ALIGNMENT_TOP, HORIZONTAL_ALIGNMENT_LEFT,
                commonFS, null, commonLH);
        startLeftY = drawPDFtxt(tripperID, startLeftV + 10, startLeftY, regularFont, GREY_COLOR, contentStream, VERTICAL_ALIGNMENT_TOP, HORIZONTAL_ALIGNMENT_LEFT,
                commonFS, commonLeftVWidth, commonLH);
        startLeftY -= commonNextLine;

        drawPDFtxt("Tripper Name", xLeft, startLeftY, regularFont, BLUE_COLOR, contentStream, VERTICAL_ALIGNMENT_TOP, HORIZONTAL_ALIGNMENT_LEFT,
                commonFS, null, commonLH);
        drawPDFtxt(":", startLeftV, startLeftY, regularFont, BLUE_COLOR, contentStream, VERTICAL_ALIGNMENT_TOP, HORIZONTAL_ALIGNMENT_LEFT,
                commonFS, null, commonLH);
        String tripperName = invoiceInfo.getTripperBillingName();
        startLeftY = drawPDFtxt(tripperName, startLeftV + 10, startLeftY, regularFont, GREY_COLOR, contentStream, VERTICAL_ALIGNMENT_TOP, HORIZONTAL_ALIGNMENT_LEFT,
                commonFS, commonLeftVWidth, commonLH);
        startLeftY -= commonNextLine;

        String companyName = invoiceInfo.getTripperBillingCompany();
        if (companyName == null || companyName.isEmpty()) {
            companyName = "N/A";
        }
        drawPDFtxt("Company", xLeft, startLeftY, regularFont, BLUE_COLOR, contentStream, VERTICAL_ALIGNMENT_TOP, HORIZONTAL_ALIGNMENT_LEFT,
                commonFS, null, commonLH);
        drawPDFtxt(":", startLeftV, startLeftY, regularFont, BLUE_COLOR, contentStream, VERTICAL_ALIGNMENT_TOP, HORIZONTAL_ALIGNMENT_LEFT,
                commonFS, null, commonLH);
        startLeftY = drawPDFtxt(companyName, startLeftV + 10, startLeftY, regularFont, GREY_COLOR, contentStream, VERTICAL_ALIGNMENT_TOP, HORIZONTAL_ALIGNMENT_LEFT,
                commonFS, commonLeftVWidth, commonLH);
        startLeftY -= commonNextLine;

        String billingAddr = invoiceInfo.getTripperBillingAddress();
        if (billingAddr == null || billingAddr.isEmpty()) {
            billingAddr = "N/A";
        }
        drawPDFtxt("Address", xLeft, startLeftY, regularFont, BLUE_COLOR, contentStream, VERTICAL_ALIGNMENT_TOP, HORIZONTAL_ALIGNMENT_LEFT,
                commonFS, null, commonLH);
        drawPDFtxt(":", startLeftV, startLeftY, regularFont, BLUE_COLOR, contentStream, VERTICAL_ALIGNMENT_TOP, HORIZONTAL_ALIGNMENT_LEFT,
                commonFS, null, commonLH);
        startLeftY = drawPDFtxt(billingAddr, startLeftV + 10, startLeftY, regularFont, GREY_COLOR, contentStream, VERTICAL_ALIGNMENT_TOP, HORIZONTAL_ALIGNMENT_LEFT,
                commonFS, commonLeftVWidth, commonLH);
        startLeftY -= commonNextLine;

        // invoice info
        String invoiceID = invoiceInfo.getInvoiceNo();
        drawPDFtxt("Tax Invoice / Receipt No.", commonRightX, startRightY, regularFont, BLUE_COLOR, contentStream, VERTICAL_ALIGNMENT_TOP, HORIZONTAL_ALIGNMENT_LEFT,
                commonFS, null, commonLH);
        drawPDFtxt(":", startRightV, startRightY, regularFont, BLUE_COLOR, contentStream, VERTICAL_ALIGNMENT_TOP, HORIZONTAL_ALIGNMENT_LEFT,
                commonFS, null, commonLH);
        startRightY = drawPDFtxt(invoiceID, startRightV + 10, startRightY, regularFont, GREY_COLOR, contentStream, VERTICAL_ALIGNMENT_TOP, HORIZONTAL_ALIGNMENT_LEFT,
                commonFS, null, commonLH);
        startRightY -= commonNextLine;

        long invoiceDate = invoiceInfo.getInvoiceDate();
        String invoiceDateStr = YTDateTimeUtility.convertMilliToddMMyyyy(invoiceDate);
        drawPDFtxt("Date", commonRightX, startRightY, regularFont, BLUE_COLOR, contentStream, VERTICAL_ALIGNMENT_TOP, HORIZONTAL_ALIGNMENT_LEFT,
                commonFS, null, commonLH);
        drawPDFtxt(":", startRightV, startRightY, regularFont, BLUE_COLOR, contentStream, VERTICAL_ALIGNMENT_TOP, HORIZONTAL_ALIGNMENT_LEFT,
                commonFS, null, commonLH);
        startRightY = drawPDFtxt(invoiceDateStr, startRightV + 10, startRightY, regularFont, GREY_COLOR, contentStream, VERTICAL_ALIGNMENT_TOP, HORIZONTAL_ALIGNMENT_LEFT,
                commonFS, null, commonLH);
        startRightY -= commonNextLine;

        String tripperTax = invoiceInfo.getTripperBillingTax();
        if (tripperTax == null || tripperTax.isEmpty()) {
            tripperTax = "N/A";
        }
        drawPDFtxt("Tax ID.", commonRightX, startRightY, regularFont, BLUE_COLOR, contentStream, VERTICAL_ALIGNMENT_TOP, HORIZONTAL_ALIGNMENT_LEFT,
                commonFS, null, commonLH);
        drawPDFtxt(":", startRightV, startRightY, regularFont, BLUE_COLOR, contentStream, VERTICAL_ALIGNMENT_TOP, HORIZONTAL_ALIGNMENT_LEFT,
                commonFS, null, commonLH);
        startRightY = drawPDFtxt(tripperTax, startRightV + 10, startRightY, regularFont, GREY_COLOR, contentStream, VERTICAL_ALIGNMENT_TOP, HORIZONTAL_ALIGNMENT_LEFT,
                commonFS, null, commonLH);
        startRightY -= commonNextLine;

        String bookingNumber = invoiceInfo.getBookingNo();
        drawPDFtxt("Booking No.", commonRightX, startRightY, regularFont, BLUE_COLOR, contentStream, VERTICAL_ALIGNMENT_TOP, HORIZONTAL_ALIGNMENT_LEFT,
                commonFS, null, commonLH);
        drawPDFtxt(":", startRightV, startRightY, regularFont, BLUE_COLOR, contentStream, VERTICAL_ALIGNMENT_TOP, HORIZONTAL_ALIGNMENT_LEFT,
                commonFS, null, commonLH);
        drawPDFtxt(bookingNumber, startRightV + 10, startRightY, regularFont, GREY_COLOR, contentStream, VERTICAL_ALIGNMENT_TOP, HORIZONTAL_ALIGNMENT_LEFT,
                commonFS, null, commonLH);

        // order table
        float lineY = startLeftY - 10;
        contentStream.setStrokingColor(GREY_COLOR);
        contentStream.drawLine(xLeft, lineY, xRight, lineY);
        float tbY = lineY - 10, tbFS = 8, tbLH = 11, paddingCol = 5, rowH = 15f, colNoW = 15, colPackID = 75, colPkgNameW = 190, pkgPriceW = 100,
                colQTYW = 30, nxtRowMargin = 5;
        contentStream.setNonStrokingColor(BLUE_COLOR);
        contentStream.addRect(xLeft, tbY - rowH, contentWidth, rowH);
        contentStream.fill();

        float startColX = xLeft, headerTxtY = tbY - rowH / 2f, endColX;

        endColX = startColX + colNoW;
        drawPDFtxt("No.", startColX + paddingCol, headerTxtY, regularFont, Color.WHITE, contentStream,
                VERTICAL_ALIGNMENT_MIDDLE, HORIZONTAL_ALIGNMENT_LEFT, tbFS, null, tbLH);

        startColX = endColX;
        endColX = startColX + colPackID;
        drawPDFtxt("Package ID", startColX + paddingCol, headerTxtY, regularFont, Color.WHITE, contentStream,
                VERTICAL_ALIGNMENT_MIDDLE, HORIZONTAL_ALIGNMENT_LEFT, tbFS, null, tbLH);

        startColX = endColX;
        endColX = startColX + colPkgNameW;
        drawPDFtxt("Package Name", startColX + paddingCol, headerTxtY, regularFont, Color.WHITE, contentStream,
                VERTICAL_ALIGNMENT_MIDDLE, HORIZONTAL_ALIGNMENT_LEFT, tbFS, null, tbLH);

        startColX = endColX;
        endColX = startColX + pkgPriceW;
        drawPDFtxt("Price", endColX - paddingCol, headerTxtY, regularFont, Color.WHITE, contentStream,
                VERTICAL_ALIGNMENT_MIDDLE, HORIZONTAL_ALIGNMENT_RIGHT, tbFS, null, tbLH);

        startColX = endColX;
        endColX = startColX + colQTYW;
        drawPDFtxt("QTY.", endColX - paddingCol, headerTxtY, regularFont, Color.WHITE, contentStream,
                VERTICAL_ALIGNMENT_MIDDLE, HORIZONTAL_ALIGNMENT_RIGHT, tbFS, null, tbLH);

        startColX = endColX;
        endColX = xRight;
        drawPDFtxt("Sub Total", endColX - paddingCol, headerTxtY, regularFont, Color.WHITE, contentStream,
                VERTICAL_ALIGNMENT_MIDDLE, HORIZONTAL_ALIGNMENT_RIGHT, tbFS, null, tbLH);
        tbY -= rowH;

        // orders
        List<YTPackageInvoiceOrder> orders = invoiceInfo.getOrders();
        for (int i = 0, len = orders.size(); i < len; i++) {
            YTPackageInvoiceOrder anO = orders.get(i);

            String oNo = (i + 1) + "";
            String pkgID = anO.getPackageID();
            String pkgName = anO.getPackageName();
            String optionNa = anO.getOptionName();
            String curCode = anO.getCurrenyCode();

            BigDecimal oPrice = anO.getPackagePrice();
            String pricetr = YTPriceUtility.convertBigDecimalToPriceFormat(oPrice) + " " + curCode;
            String qty = anO.getQty() + "";

            BigDecimal tPrice = anO.getTotal();
            String totalStr = YTPriceUtility.convertBigDecimalToPriceFormat(tPrice) + " " + curCode;

            startColX = xLeft;
            headerTxtY = tbY - nxtRowMargin;

            endColX = startColX + colNoW;
            drawPDFtxt(oNo, startColX + paddingCol, headerTxtY, regularFont, GREY_COLOR, contentStream,
                    VERTICAL_ALIGNMENT_TOP, HORIZONTAL_ALIGNMENT_LEFT, tbFS, null, tbLH);

            startColX = endColX;
            endColX = startColX + colPackID;
            drawPDFtxt(pkgID, startColX + paddingCol, headerTxtY, regularFont, GREY_COLOR, contentStream,
                    VERTICAL_ALIGNMENT_TOP, HORIZONTAL_ALIGNMENT_LEFT, tbFS, null, tbLH);

            startColX = endColX;
            endColX = startColX + colPkgNameW;
            float nxtRow = drawPDFtxt(pkgName, startColX + paddingCol, headerTxtY, regularFont, GREY_COLOR, contentStream,
                    VERTICAL_ALIGNMENT_TOP, HORIZONTAL_ALIGNMENT_LEFT, tbFS, colPkgNameW, tbLH);
            nxtRow -= tbLH;
            nxtRow = drawPDFtxt(optionNa, startColX + paddingCol, nxtRow, regularFont, GREY_COLOR, contentStream,
                    VERTICAL_ALIGNMENT_TOP, HORIZONTAL_ALIGNMENT_LEFT, tbFS, colPkgNameW, tbLH);
            nxtRow -= tbLH;

            startColX = endColX;
            endColX = startColX + pkgPriceW;
            drawPDFtxt(pricetr, endColX - paddingCol, headerTxtY, regularFont, GREY_COLOR, contentStream,
                    VERTICAL_ALIGNMENT_TOP, HORIZONTAL_ALIGNMENT_RIGHT, tbFS, null, tbLH);

            startColX = endColX;
            endColX = startColX + colQTYW;
            drawPDFtxt(qty, endColX - paddingCol, headerTxtY, regularFont, GREY_COLOR, contentStream,
                    VERTICAL_ALIGNMENT_TOP, HORIZONTAL_ALIGNMENT_RIGHT, tbFS, null, tbLH);

            startColX = endColX;
            endColX = xRight;
            drawPDFtxt(totalStr, endColX - paddingCol, headerTxtY, regularFont, GREY_COLOR, contentStream,
                    VERTICAL_ALIGNMENT_TOP, HORIZONTAL_ALIGNMENT_RIGHT, tbFS, null, tbLH);

            tbY = nxtRow;
        }

        lineY = tbY - 10;
        contentStream.setStrokingColor(GREY_COLOR);
        contentStream.drawLine(xLeft, lineY, xRight, lineY);

        // final total
        float startTTy = lineY - 10, rightTTx = xRight - 100, totalLH = 11f, totalFS = 8;
        BigDecimal total = invoiceInfo.getTotal();
        BigDecimal dcPrice = invoiceInfo.getDcPrice();
        String dcCode = invoiceInfo.getDcCode();
        BigDecimal finalTotal = invoiceInfo.getFinalTotal();
        String currencyCode = invoiceInfo.getCurrencyCode();
        BigDecimal exRate = invoiceInfo.getExRate();
        BigDecimal amountInVat = invoiceInfo.getAmountInTax();
        BigDecimal amountExVat = invoiceInfo.getAmountExTax();
        BigDecimal VAT = invoiceInfo.getVat();

        drawPDFtxt("(For full package details and conditions, please check your booking details)", xLeft, startTTy, regularFont, GREY_COLOR, contentStream, VERTICAL_ALIGNMENT_TOP, HORIZONTAL_ALIGNMENT_LEFT,
                totalFS, null, totalLH);

        String totalPm = YTPriceUtility.convertBigDecimalToPriceFormat(total) + " " + currencyCode;
        drawPDFtxt("Total", rightTTx, startTTy, regularFont, GREY_COLOR, contentStream, VERTICAL_ALIGNMENT_TOP, HORIZONTAL_ALIGNMENT_RIGHT,
                totalFS, null, totalLH);
        startTTy = drawPDFtxt(totalPm, xRight, startTTy, regularFont, GREY_COLOR, contentStream, VERTICAL_ALIGNMENT_TOP, HORIZONTAL_ALIGNMENT_RIGHT,
                totalFS, null, totalLH);
        
        if (dcCode == null || dcCode.isEmpty()) {
            dcCode = "N/A";
        }
        String dc = "Discount - " + dcCode;
        String dcP = "-" + YTPriceUtility.convertBigDecimalToPriceFormat(dcPrice) + " " + currencyCode;
        startTTy -= totalLH;
        drawPDFtxt(dc, rightTTx, startTTy, regularFont, GREY_COLOR, contentStream, VERTICAL_ALIGNMENT_TOP, HORIZONTAL_ALIGNMENT_RIGHT,
                totalFS, null, totalLH);
        startTTy = drawPDFtxt(dcP, xRight, startTTy, regularFont, RED_COLOR, contentStream, VERTICAL_ALIGNMENT_TOP, HORIZONTAL_ALIGNMENT_RIGHT,
                totalFS, null, totalLH);

        String finalTotalStr = YTPriceUtility.convertBigDecimalToPriceFormat(finalTotal) + " " + currencyCode;
        startTTy -= totalLH;
        drawPDFtxt("Total after discount", rightTTx, startTTy, regularFont, GREY_COLOR, contentStream, VERTICAL_ALIGNMENT_TOP, HORIZONTAL_ALIGNMENT_RIGHT,
                totalFS, null, totalLH);
        startTTy = drawPDFtxt(finalTotalStr, xRight, startTTy, regularFont, GREY_COLOR, contentStream, VERTICAL_ALIGNMENT_TOP, HORIZONTAL_ALIGNMENT_RIGHT,
                totalFS, null, totalLH);

        String exRateStr = YTPriceUtility.convertBigDecimalToPriceFormat(exRate);
        startTTy -= totalLH;
        drawPDFtxt("Ex. Rate", rightTTx, startTTy, regularFont, GREY_COLOR, contentStream, VERTICAL_ALIGNMENT_TOP, HORIZONTAL_ALIGNMENT_RIGHT,
                totalFS, null, totalLH);
        startTTy = drawPDFtxt(exRateStr, xRight, startTTy, regularFont, GREY_COLOR, contentStream, VERTICAL_ALIGNMENT_TOP, HORIZONTAL_ALIGNMENT_RIGHT,
                totalFS, null, totalLH);

        String amountInVatStr = YTPriceUtility.convertBigDecimalToPriceFormat(amountInVat) + " THB";
        startTTy -= (totalLH + 10);
        drawPDFtxt("Amount Incl. VAT", rightTTx, startTTy, regularFont, GREY_COLOR, contentStream, VERTICAL_ALIGNMENT_TOP, HORIZONTAL_ALIGNMENT_RIGHT,
                totalFS, null, totalLH);
        startTTy = drawPDFtxt(amountInVatStr, xRight, startTTy, regularFont, GREY_COLOR, contentStream, VERTICAL_ALIGNMENT_TOP, HORIZONTAL_ALIGNMENT_RIGHT,
                totalFS, null, totalLH);

        String amountExVatStr = YTPriceUtility.convertBigDecimalToPriceFormat(amountExVat) + " THB";
        startTTy -= totalLH;
        drawPDFtxt("Amount Excl. VAT", rightTTx, startTTy, regularFont, GREY_COLOR, contentStream, VERTICAL_ALIGNMENT_TOP, HORIZONTAL_ALIGNMENT_RIGHT,
                totalFS, null, totalLH);
        startTTy = drawPDFtxt(amountExVatStr, xRight, startTTy, regularFont, GREY_COLOR, contentStream, VERTICAL_ALIGNMENT_TOP, HORIZONTAL_ALIGNMENT_RIGHT,
                totalFS, null, totalLH);

        String vatStr = YTPriceUtility.convertBigDecimalToPriceFormat(VAT) + " THB";
        startTTy -= totalLH;
        drawPDFtxt("VAT", rightTTx, startTTy, regularFont, GREY_COLOR, contentStream, VERTICAL_ALIGNMENT_TOP, HORIZONTAL_ALIGNMENT_RIGHT,
                totalFS, null, totalLH);
        startTTy = drawPDFtxt(vatStr, xRight, startTTy, regularFont, GREY_COLOR, contentStream, VERTICAL_ALIGNMENT_TOP, HORIZONTAL_ALIGNMENT_RIGHT,
                totalFS, null, totalLH);

        startTTy -= totalLH;

        lineY = startTTy - 10;
        contentStream.setStrokingColor(GREY_COLOR);
        contentStream.drawLine(xLeft, lineY, xRight, lineY);

        float autoY = lineY - 5;
        drawPDFtxt("This Tax Invoice / Receipt is automatically generated.", xRight, autoY, regularFont, GREY_COLOR, contentStream, VERTICAL_ALIGNMENT_TOP, HORIZONTAL_ALIGNMENT_RIGHT,
                6f, null, 9f);
        autoY -= 9f;

        contentStream.close();

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        doc.save(os);
        byte[] bytes = os.toByteArray();

//        doc.save(new File("D:/test.pdf"));
        doc.close();
        return bytes;
    }

    public static void main(String[] args) throws IOException {
        YTPackageInvoiceOrder order = new YTPackageInvoiceOrder("OOOOOOOOOO", "Package Name LONG LONG LONG LONG LONG LONG LONG", "(Option Name)",
                "THB", BigDecimal.ZERO, 1, BigDecimal.ONE);
        List<YTPackageInvoiceOrder> orders = new ArrayList<>();
        orders.add(order);
        orders.add(order);
        orders.add(order);
        orders.add(order);
        orders.add(order);

        YTPackageInvoiceInfo invoiceInfo = new YTPackageInvoiceInfo(null, "Partner Company Name adsf asdf asdf asdf asdf",
                "Partner Address", "Sate", "Country", "Postal Code", "2397238957", "66", "325789572957", null, "First Name Last Name",
                null, null, null, "0718-293532985", 1530788546937l, "FIOSDJFIODS", "THB", BigDecimal.ZERO, BigDecimal.ZERO,
                "FIRST25", BigDecimal.ZERO, BigDecimal.ONE, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ONE, null, null, null, null, orders);
        generateInvoicePDF(invoiceInfo);
    }
}
