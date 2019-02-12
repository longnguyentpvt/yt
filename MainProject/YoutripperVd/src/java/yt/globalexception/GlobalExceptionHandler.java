/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.globalexception;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import javaclass.common.YTInterceptorException;
import javaclass.utility.YTDateTimeUtility;
import javaclass.utility.YTUrlUtility;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import yt.func.landing.LandingViewMapping;

/**
 *
 * @author nickn
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = Logger.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = YTInterceptorException.class)
    public ModelAndView handleInterceptorException(HttpServletRequest request,
            HttpServletResponse response,
            Exception ex, Locale locale) {
        ObjectMapper mapper = new ObjectMapper();

        StringBuffer requestPath = request.getRequestURL();
        String queryString = request.getQueryString();
        String url = requestPath.toString();
        if (queryString != null) {
            url += "?" + queryString;
        }

        Map<String, String[]> inputMap = request.getParameterMap();
        String paramStr = "";
        try {
            paramStr = mapper.writeValueAsString(inputMap);
        } catch (Exception e) {
        }

        Enumeration<String> reAttrs = request.getAttributeNames();
        String attrVal = "";
        Map<String, Object> requestValMap = new HashMap<>();
        while (reAttrs.hasMoreElements()) {
            String n = reAttrs.nextElement();
            if (!n.contains("springframework")) {
                Object val = request.getAttribute(n);
                String valStr = null;
                try {
                    valStr = mapper.writeValueAsString(val);
                } catch (Exception e) {
                }
                if (valStr != null) {
                    requestValMap.put(n, val);
                }
            }
        }
        try {
            attrVal = mapper.writeValueAsString(requestValMap);
        } catch (Exception e) {
        }

        HttpSession session = request.getSession();
        String sessionVal = "";
        if (session != null) {
            Map<String, Object> sessionValMap = new HashMap<>();
            Enumeration<String> attrEs = session.getAttributeNames();
            while (attrEs.hasMoreElements()) {
                String n = attrEs.nextElement();
                Object val = session.getAttribute(n);
                String valStr = null;
                try {
                    valStr = mapper.writeValueAsString(val);
                } catch (Exception e) {
                }
                if (valStr != null) {
                    sessionValMap.put(n, val);
                }
            }

            try {
                sessionVal = mapper.writeValueAsString(sessionValMap);
            } catch (Exception e) {
            }
        }

        String msg = ex.getMessage();

        String stackTraceStr = "";
        StackTraceElement[] stackTraces = ex.getStackTrace();
        for (StackTraceElement stackTrace : stackTraces) {
            stackTraceStr += stackTrace.toString() + "\n";
        }

        String time = YTDateTimeUtility.getCurrentTimeUnderDdMMyyyyHHmmss();

        String logContent = "-----------------------------\n"
                + "time: " + time + "\n"
                + "url: " + url + "\n"
                + "message: " + msg + "\n"
                + "param: " + paramStr + "\n"
                + "attr: " + attrVal + "\n"
                + "session: " + sessionVal + "\n"
                + "------ stacktrace\n"
                + stackTraceStr
                + "------\n"
                + "----------- End -------------\n\n";

        // write
        logger.error(logContent);

        // detect request is ajax or not
        String contentType = request.getHeader("Content-Type");
        String requestedWith = request.getHeader("X-Requested-With");
        boolean isAjax = false;
        if ((contentType != null && contentType.contains("application/json"))
                || (requestedWith != null && "XMLHttpRequest".equals(requestedWith))) {
            isAjax = true;
        }

        ModelAndView mv = null;
        if (isAjax) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        } else {
            mv = new ModelAndView(YTUrlUtility.getDirectURL(locale, LandingViewMapping.PAGE_NOT_FOUND_PAGE));
        }

        return mv;
    }

    @ExceptionHandler(value = Exception.class)
    public ModelAndView handleAllException(HttpServletRequest request,
            HttpServletResponse response,
            Exception ex, Locale locale) {
        ObjectMapper mapper = new ObjectMapper();

        StringBuffer requestPath = request.getRequestURL();
        String queryString = request.getQueryString();
        String url = requestPath.toString();
        if (queryString != null) {
            url += "?" + queryString;
        }

        Map<String, String[]> inputMap = request.getParameterMap();
        String paramStr = "";
        try {
            paramStr = mapper.writeValueAsString(inputMap);
        } catch (Exception e) {
        }

        Enumeration<String> reAttrs = request.getAttributeNames();
        String attrVal = "";
        Map<String, Object> requestValMap = new HashMap<>();
        while (reAttrs.hasMoreElements()) {
            String n = reAttrs.nextElement();
            if (!n.contains("springframework")) {
                Object val = request.getAttribute(n);
                String valStr = null;
                try {
                    valStr = mapper.writeValueAsString(val);
                } catch (Exception e) {
                }
                if (valStr != null) {
                    requestValMap.put(n, val);
                }
            }
        }
        try {
            attrVal = mapper.writeValueAsString(requestValMap);
        } catch (Exception e) {
        }

        HttpSession session = request.getSession();
        String sessionVal = "";
        if (session != null) {
            Map<String, Object> sessionValMap = new HashMap<>();
            Enumeration<String> attrEs = session.getAttributeNames();
            while (attrEs.hasMoreElements()) {
                String n = attrEs.nextElement();
                Object val = session.getAttribute(n);
                String valStr = null;
                try {
                    valStr = mapper.writeValueAsString(val);
                } catch (Exception e) {
                }
                if (valStr != null) {
                    sessionValMap.put(n, val);
                }
            }

            try {
                sessionVal = mapper.writeValueAsString(sessionValMap);
            } catch (Exception e) {
            }
        }

        String msg = ex.getMessage();

        String stackTraceStr = "";
        StackTraceElement[] stackTraces = ex.getStackTrace();
        for (StackTraceElement stackTrace : stackTraces) {
            stackTraceStr += stackTrace.toString() + "\n";
        }

        String time = YTDateTimeUtility.getCurrentTimeUnderDdMMyyyyHHmmss();

        String logContent = "-----------------------------\n"
                + "time: " + time + "\n"
                + "url: " + url + "\n"
                + "message: " + msg + "\n"
                + "param: " + paramStr + "\n"
                + "attr: " + attrVal + "\n"
                + "session: " + sessionVal + "\n"
                + "------ stacktrace\n"
                + stackTraceStr
                + "------\n"
                + "----------- End -------------\n\n";

        // write
        logger.error(logContent);

        // detect request is ajax or not
        String contentType = request.getHeader("Content-Type");
        String requestedWith = request.getHeader("X-Requested-With");
        boolean isAjax = false;
        if ((contentType != null && contentType.contains("application/json"))
                || (requestedWith != null && "XMLHttpRequest".equals(requestedWith))) {
            isAjax = true;
        }

        ModelAndView mv = null;
        if (isAjax) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } else {
            mv = new ModelAndView(YTUrlUtility.getDirectURL(locale, LandingViewMapping.SYSTEM_ERROR_PAGE));
        }

        return mv;
    }

    public static void logEmailException(String senderEmail, String senderPassword, String receiverEmail,
            String title, String nickname, String content, String contentTitle, String thankName, String contactEmail, Exception ex) {

        String msg = "Fail";

        String stackTraceStr = "";
        if (ex != null) {
            StackTraceElement[] stackTraces = ex.getStackTrace();
            for (StackTraceElement stackTrace : stackTraces) {
                stackTraceStr += stackTrace.toString() + "\n";
            }
            msg = ex.getMessage();
        }
        String time = YTDateTimeUtility.getCurrentTimeUnderDdMMyyyyHHmmss();

        String logContent = "-----------------------------\n"
                + "time: " + time + "\n"
                + "message: " + msg + "\n"
                + "senderEmail: " + senderEmail + "\n"
                + "senderPassword: " + senderPassword + "\n"
                + "receiverEmail: " + receiverEmail + "\n"
                + "title: " + title + "\n"
                + "nickname: " + nickname + "\n"
                + "content: " + content + "\n"
                + "contentTitle: " + contentTitle + "\n"
                + "thankName: " + thankName + "\n"
                + "contactEmail: " + contactEmail + "\n"
                + "------ stacktrace\n"
                + stackTraceStr
                + "------\n"
                + "----------- End -------------\n\n";

        // write
        logger.error(logContent);
    }

    public static void logVerificationEmail(Exception ex, String displayName, String email, String token) {
        String time = YTDateTimeUtility.getCurrentTimeUnderDdMMyyyyHHmmss();
        String msg = "Fail";
        String stackTraceStr = "";
        if (ex != null) {
            StackTraceElement[] stackTraces = ex.getStackTrace();
            for (StackTraceElement stackTrace : stackTraces) {
                stackTraceStr += stackTrace.toString() + "\n";
            }
            msg = ex.getMessage();
        }
        String logContent = "-----------------------------\n"
                + "Time: " + time + "\n"
                + "Message : " + msg + "\n"
                + "displayName : " + displayName + "\n"
                + "email : " + email + "\n"
                + "token : " + token + "\n"
                + "------ stacktrace\n"
                + stackTraceStr
                + "------\n"
                + "----------- End -------------\n\n";

        logger.error(logContent);
    }

    public static void logCompleteCheckout(Exception ex, String orderNo) {
        String time = YTDateTimeUtility.getCurrentTimeUnderDdMMyyyyHHmmss();
        String msg = "Fail";
        String stackTraceStr = "";
        if (ex != null) {
            StackTraceElement[] stackTraces = ex.getStackTrace();
            for (StackTraceElement stackTrace : stackTraces) {
                stackTraceStr += stackTrace.toString() + "\n";
            }
            msg = ex.getMessage();
        }
        String logContent = "-----------------------------\n"
                + "Time: " + time + "\n"
                + "Message : " + msg + "\n"
                + "orderNo : " + orderNo + "\n"
                + "------ stacktrace\n"
                + stackTraceStr
                + "------\n"
                + "----------- End -------------\n\n";

        logger.error(logContent);
    }

    public static void logExpireScheduler(Exception ex) {
        String time = YTDateTimeUtility.getCurrentTimeUnderDdMMyyyyHHmmss();
        String msg = "Scheduler Fail";
        String stackTraceStr = "";
        if (ex != null) {
            StackTraceElement[] stackTraces = ex.getStackTrace();
            for (StackTraceElement stackTrace : stackTraces) {
                stackTraceStr += stackTrace.toString() + "\n";
            }
            msg = ex.getMessage();
        }
        String logContent = "-----------------------------\n"
                + "Time: " + time + "\n"
                + "Message : " + msg + "\n"
                + "------ stacktrace\n"
                + stackTraceStr
                + "------\n"
                + "----------- End -------------\n\n";

        logger.error(logContent);
    }

    public static void logUtilityError(Exception ex, String inputs) {
        String time = YTDateTimeUtility.getCurrentTimeUnderDdMMyyyyHHmmss();
        String msg = "Fail";
        String stackTraceStr = "";
        if (ex != null) {
            StackTraceElement[] stackTraces = ex.getStackTrace();
            for (StackTraceElement stackTrace : stackTraces) {
                stackTraceStr += stackTrace.toString() + "\n";
            }
            msg = ex.getMessage();
        }
        String logContent = "-----------------------------\n"
                + "Time: " + time + "\n"
                + "Message : " + msg + "\n"
                + "Inputs : " + inputs + "\n"
                + "------ stacktrace\n"
                + stackTraceStr
                + "------\n"
                + "----------- End -------------\n\n";

        logger.error(logContent);
    }

}
