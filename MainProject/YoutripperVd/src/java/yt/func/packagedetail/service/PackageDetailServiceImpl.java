/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.func.packagedetail.service;

import java.math.BigDecimal;
import java.util.Locale;
import javaclass.common.YTSession;
import javaclass.common.ytpackage.YTPackageData;
import javaclass.utility.ContentUtility;
import javaclass.utility.YTLocationUtility;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yt.func.packagedetail.dao.YTPackageDAO;
import yt.func.packagedetail.javaclass.PackageDetailDAORespone;
import yt.func.packagedetail.javaclass.YTPackageDetailData;

/**
 *
 * @author nickn
 */
@Service
public class PackageDetailServiceImpl implements PackageDetailService {

    @Autowired
    private YTPackageDAO ytPkgDetailDAO;

    @Override
    @Transactional
    public void getPackageDetailData(String packageID, HttpServletRequest request, Locale locale, HttpSession session) {
        String localeStr = YTLocationUtility.getLocale(locale);
        String cc = YTSession.getCurrencyCode(session);

        PackageDetailDAORespone dt = ytPkgDetailDAO.getPackageDetail(packageID, localeStr, cc);
        if (dt != null) {
            String stt = dt.getPkgStt();
            if (stt.equals(YTPackageData.YT_PACKAGE_STATUS_APPROVED)) {
                String pkgName = dt.getPackageName();
                pkgName = ContentUtility.refineContentAsTextHTML(pkgName);

                BigDecimal price = dt.getPrice();
                String servingType = dt.getServingType();
                boolean opened = servingType.equals(YTPackageData.SERVING_TYPE_OPEN_TIMED);

                YTPackageDetailData rp = new YTPackageDetailData(packageID, pkgName, price, cc, opened);
                request.setAttribute("dt", rp);
            } else {
                throw new IllegalArgumentException("Wrong Package Status");
            }
        }
    }

}
