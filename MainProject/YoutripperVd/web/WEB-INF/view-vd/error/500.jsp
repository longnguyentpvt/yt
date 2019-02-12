<%-- 
    Document   : 404
    Created on : Jun 5, 2018, 4:36:55 PM
    Author     : nickn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="${pageContext.response.locale}" currency="${sessionScope.ytCurrency}" ng-app="youtripper" ng-controller="YoutripperController">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="../common/commonhead.jsp" %>
        <link rel="stylesheet" href="${requestScope.cssURL}er-page.css" >
        <title>Youtripper - System Error</title>
    </head>
    <body ng-cloak>
        <div id="padding-header">
            <div id="error-page" ng-controller="PageController">
                <div class="yt-normal-container">
                    <div class="clearfix">
                        <div class="img">
                            <img src="${requestScope.imageURL}yt-error-page-ico.png"/>
                        </div>
                        <div class="error-ct">
                            <div class="logo">
                                <svg  width="200px" height="45.2px" viewBox="0 0 200 45.2">
                                <path style="fill: #E94848" d="M76.5,14.1c-6.2,0-9.8,4.8-9.8,9.7v0c0,4.9,3.6,9.7,9.8,9.7c6.2,0,9.8-4.7,9.8-9.7S82.7,14.1,76.5,14.1z
                                      M76.5,30.2c-3.5,0-6.3-2.7-6.3-6.4v0c0-3.7,2.8-6.4,6.3-6.4c3.5,0,6.3,2.7,6.3,6.4C82.8,27.5,80,30.2,76.5,30.2z"/>
                                <path style="fill: #E94848" d="M101.4,24.9c0,3.1-1.6,5.3-5,5.4c-3.6,0-4.9-2.4-4.9-5.7v-9.9H88v10.7c0,3.8,1.5,8.1,7.4,8.1
                                      c3.3,0,5.3-1.5,6.2-3.4V33h3.3V14.6h-3.5V24.9z"/>
                                <path style="fill: #E94848" d="M181.1,14.1c-5.6,0-9.7,4.5-9.7,9.7c0,4.8,3.7,9.7,9.7,9.7c1.9,0,3.7-0.5,5.2-1.5c1.5-1,2.8-2.5,3.7-4.5h-3.6
                                      c-1,1.9-2.8,3.1-5.3,3.1c-2.8,0-6-1.9-6.2-5.6h15.7c0.3-3.1-0.6-5.9-2.8-8.1C186.1,15.1,183.7,14.1,181.1,14.1z M175,22.2
                                      c0.2-2.5,2.5-5.1,6.1-5.1c3.5-0.1,5.9,2.7,6.1,5.1H175z"/>
                                <circle style="fill: #E94848" cx="130.4" cy="10" r="2.4"/>
                                <path style="fill: #E94848" d="M160.4,14.1c-3.3,0-5.6,1.5-7,3.7v-3.2H150v23h3.5V33v-3c0.8,1.5,3.1,3.6,6.9,3.6c5.5,0,9.2-4.4,9.2-9.6
                                      C169.6,19.4,166.7,14.1,160.4,14.1z M166.1,23.8c0,3.6-2.7,6.4-6.4,6.4c-1.8,0-3.4-0.7-4.5-1.9c-1.1-1.1-1.8-2.7-1.8-4.5
                                      c0-1.8,0.7-3.4,1.8-4.6c1.1-1.2,2.7-1.9,4.5-1.9C164,17.4,166.1,20.8,166.1,23.8L166.1,23.8z"/>
                                <path style="fill: #E94848" d="M139,14.1c-3.3,0-5.6,1.5-7,3.7v-3.2h-3.3v23h3.5V33v-3c0.8,1.5,3.1,3.6,6.9,3.6c5.5,0,9.2-4.4,9.2-9.6
                                      C148.3,19.4,145.3,14.1,139,14.1z M144.7,23.8c0,3.6-2.7,6.4-6.4,6.4c-1.8,0-3.4-0.7-4.5-1.9c-1.1-1.1-1.8-2.7-1.8-4.5
                                      c0-1.8,0.7-3.4,1.8-4.6c1.1-1.2,2.7-1.9,4.5-1.9C142.6,17.4,144.7,20.8,144.7,23.8L144.7,23.8z"/>
                                <polygon style="fill: #E94848" points="64.1,14.6 59,28 53.9,14.6 50.2,14.6 57.2,32.4 55,37.6 58.7,37.6 67.9,14.6 		"/>
                                <path style="fill: #E94848" d="M198.1,14.6c-1.1,0-2,0.6-2.5,1.6v-1.6h-3.2V33h3.5V20.3c0-1.5,1.2-2.7,2.7-2.7h1.4v-2.9H198.1z"/>
                                <path style="fill: #E94848" d="M125,14.6c-1.1,0-2,0.6-2.5,1.6v-1.6h-3.2v18.3h3.5V20.3c0-1.5,1.2-2.7,2.7-2.7h1.4v-2.9H125z"/>
                                <path style="fill: #E94848" d="M106.7,14.6v3h3.7v10.1c0,0-0.1,4.6,4.6,5.3c1.2,0,2.6,0,2.6,0v-3.2H116c0,0-2.1,0-2.1-2.5c0-2.5,0-9.7,0-9.7
                                      h3.7v-3h-3.7V10h-3.5v4.7H106.7z"/>
                                <path style="fill: #E94848"  d="M22.6,0C10.1,0,0,10.1,0,22.6C0,35,10.1,45.2,22.6,45.2C35,45.2,45.2,35,45.2,22.6C45.2,10.1,35,0,22.6,0z
                                      M35.8,23.7c-1.8,1.8-3.9,3.2-6.2,4.1c-2.2,0.9-4.6,1.4-7,1.4c-2.4,0-4.8-0.5-7-1.4c-2.3-0.9-4.4-2.3-6.2-4.1
                                      c-1.8-1.8-3.2-3.9-4.1-6.2C5,17,4.9,16.5,4.7,16H8c0.8,2,2,3.9,3.6,5.5c2.9,2.9,6.8,4.6,11,4.6c3.6,0,7.1-1.2,9.8-3.5L25.9,16h4.5
                                      l4.3,4.3c1.1-1.3,1.9-2.8,2.5-4.3h3.3c-0.2,0.5-0.3,1-0.5,1.5C39,19.8,37.6,21.9,35.8,23.7z"/>
                                </svg>
                            </div>
                            <div class="txt">
                                <div class="name">
                                    <spring:message code="yt.500.error.name"/>
                                </div>
                                <p class="err-txt">
                                    <spring:message code="yt.500.error.content"/> <span class="hl-s" ng-bind="scs"></span> <spring:message code="yt.500.error.txt.seconds"/>
                                </p>
                            </div>
                            <div class="b-btn">
                                <a href="${sessionScope.hrefLocale}/" class="yt-btn red-btn">
                                    <spring:message code="yt.500.error.btn.back"/>
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <%@include file="../common/footer.jsp" %>
        <%@include file="../common/header.jsp" %>
    </body>
</html>
