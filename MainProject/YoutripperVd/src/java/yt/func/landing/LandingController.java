/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.func.landing;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author nickn
 */
@Controller
@RequestMapping(value = {"", "/{locale}"})
public class LandingController {

    @RequestMapping(value = LandingViewMapping.SYSTEM_ERROR_PAGE, method = RequestMethod.GET)
    public String goToMainPage(HttpSession session, HttpServletRequest request) {
        return "error/500";
    }

    @RequestMapping(value = LandingViewMapping.PAGE_NOT_FOUND_PAGE, method = RequestMethod.GET)
    public String goToPageNotFound(HttpSession session, HttpServletRequest request) {
        return "error/404";
    }
}
