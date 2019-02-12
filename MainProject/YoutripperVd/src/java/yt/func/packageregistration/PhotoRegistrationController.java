/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.func.packageregistration;

import java.io.IOException;
import java.util.List;
import javaclass.common.YTAttr;
import javaclass.common.YTData;
import javaclass.common.YTFileMeta;
import javaclass.common.YTSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import yt.func.packageregistration.service.PhotoRegistrationService;

/**
 *
 * @author nickn
 */
@Controller
@RequestMapping(value = {YTData.PARTNER_PATH, "/{locale}" + YTData.PARTNER_PATH})
public class PhotoRegistrationController {

    @Autowired
    private PhotoRegistrationService photoRegistrationService;

    @RequestMapping(value = PackageRegistrationViewMapping.PHOTO_DATA_LOADING_URL, method = RequestMethod.POST)
    @ResponseBody
    public String getDescriptionData(HttpServletRequest request, HttpSession session) throws IOException {
        String data = (String) request.getAttribute(YTAttr.AJAX_DATA_ATTR);
        return photoRegistrationService.getPhotoData(session, data);
    }

    @RequestMapping(value = PackageRegistrationViewMapping.PHOTO_COVER_UPLOADING_URL, method = RequestMethod.POST)
    @ResponseBody
    public String uploadCover(@RequestParam("cover") MultipartFile file, HttpServletRequest request, HttpSession session) throws IOException {
        return photoRegistrationService.uploadCoverPhoto(session, file);
    }

    @RequestMapping(value = PackageRegistrationViewMapping.PHOTO_UPLOADED_FILE_META_URL, method = RequestMethod.GET)
    public void getFileMeta(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws IOException {
        int index = Integer.parseInt(request.getParameter("registration"));
        // get temporary avatar in session
        List<YTFileMeta> files = YTSession.getFileMetas(session);
        YTFileMeta file = files.get(index);

        response.setContentType(file.getContentType());
        response.setHeader("Content-disposition", "attachment; filename=\"" + file.getName() + "\"");
        FileCopyUtils.copy(file.getBytes(), response.getOutputStream());
    }

    @RequestMapping(value = PackageRegistrationViewMapping.PHOTO_COVER_EDITING_URL, method = RequestMethod.POST)
    @ResponseBody
    public String editCover(HttpServletRequest request, HttpSession session) throws IOException, InterruptedException {
        String data = (String) request.getAttribute(YTAttr.AJAX_DATA_ATTR);
        return photoRegistrationService.cropCoverAndSave(session, data);
    }
}
