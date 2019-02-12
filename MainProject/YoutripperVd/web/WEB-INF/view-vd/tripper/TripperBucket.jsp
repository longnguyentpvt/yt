<%-- 
    Document   : TripperProfile
    Created on : Jul 4, 2018, 11:26:43 AM
    Author     : nickn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="${pageContext.response.locale}" currency="${sessionScope.ytCurrency}" ng-app="youtripper" ng-controller="YoutripperController">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="../common/commonhead.jsp" %>

        <script src="${requestScope.jsURL}tripper-account-setting.js"></script>
        <script src="${requestScope.jsURL}tripper-bucket.js"></script>

        <link rel="stylesheet" href="${requestScope.cssURL}tripper-account-setting.css" >
        <link rel="stylesheet" href="${requestScope.cssURL}tripper-account-profile.css" >

        <title><spring:message code="yt.tripper.accountprofile.browser.header"/></title>
    </head>
    <body id="padding-header" ng-cloak ng-init="hes = 'bucket'">
        <%@include file="AccountSettingMenu.jsp" %>

        <div id="account-body" ng-controller="TripperBucket">
            <div class="clearfix">
                <div class="yt-normal-container">
                    <div class="body-l">
                        <%@include file="TripperAccountCommon.jsp" %>
                    </div>
                    <div class="body-r">
                        <div id="bucket-content">
                            <div ng-repeat="pkg in bucket.vars.dt">
                                {{pkg.packageName}} <a href="" ng-click="bucket.funcs.rm(pkg.packageID)">remove</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%@include file="../common/header.jsp" %>
    </body>
</html>
