<%-- 
    Document   : commonhead
    Created on : Jan 11, 2018, 12:49:59 PM
    Author     : nickn
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<spring:message code="yt.config.css.url" var="cssURL" scope="request"/>
<spring:message code="yt.config.js.url" var="jsURL" scope="request"/>
<spring:message code="yt.config.images.url" var="imageURL" scope="request"/>
<meta name="viewport" content="width=1200">
<link rel="icon" href="${requestScope.imageURL}youtripper-favico.ico" >
<link rel="stylesheet" href="${requestScope.cssURL}common-youtripper.css" >
<script src="${requestScope.jsURL}jquery-3.1.1.min.js"></script>
<script src="${requestScope.jsURL}angular.min.js"></script>
<script src="${requestScope.jsURL}youtripper.js"></script>
<script>
    var fbid = '<spring:message code="yt.social.fb.id" />';
    var ggid = '<spring:message code="yt.social.gg.id" />';
</script>