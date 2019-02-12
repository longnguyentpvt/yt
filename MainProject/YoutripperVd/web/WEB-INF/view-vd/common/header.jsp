<%-- 
    Document   : header
    Created on : Jan 17, 2018, 4:39:33 PM
    Author     : nickn
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<header id="yt-header">
    <div class="container">
        <div class="header-content clearfix">
            <div class="left">
                <ul class="clearfix allEl">
                    <li>
                        <div class="anEl">
                            <div class="vertical-center-container">
                                <div class="center-content">
                                    <a href="${sessionScope.hrefLocale}/" class="logo">
                                        <img class="red-ico" src="${requestScope.imageURL}youtripper-red-logo.svg" alt="Youtripper Red Logo"/>
                                        <img class="white-ico" src="${requestScope.imageURL}youtripper-white-logo.svg" alt="Youtripper White Logo"/>
                                    </a>
                                </div>
                            </div>
                        </div>
                    </li>
                    <li>
                        <div class="anEl">
                            <div class="vertical-center-container">
                                <div class="center-content">
                                    <div class="s-i">
                                        <input type="text" class="yt-input" placeholder="Search" ng-model="search.vars.hdkw"
                                               ng-change="search.funcs.kwc(true)" ytmaxlength="100"
                                               ng-focus="search.vars.hdkwa = true" ng-blur="search.vars.hdkwa = false"/>
                                        <div class="s-btn">
                                            <svg width="20.2px" height="20px" viewBox="0 0 20.2 20" style="enable-background:new 0 0 20.2 20;"><path style="fill: #4DBDC9;" d="M18,2.2c-3-3-7.7-3-10.6,0s-3,7.6,0,10.6s7.7,3,10.6,0S20.9,5.1,18,2.2z M16.9,11.7 c-2.3,2.4-6.2,2.4-8.5,0C6,9.3,6,5.6,8.4,3.3c2.3-2.3,6.2-2.4,8.5,0C19.3,5.6,19.3,9.3,16.9,11.7z"></path> <path style="fill: #4DBDC9;" d="M5.1,12.7l-4.6,4.6c-0.7,0.7-0.7,1.6,0,2.2c0.7,0.7,1.6,0.7,2.2,0l4.6-4.6 c0.7-0.7,0.7-1.6,0-2.2C6.8,12.1,5.8,12.1,5.1,12.7z"></path></svg>
                                        </div>
                                        <div class="search-sg" ng-if="search.vars.hdkwa && search.vars.hdkw.length > 1">
                                            <div class="rows">
                                                <ul>
                                                    <li ng-show="search.vars.ctld">
                                                        <div class="ars">
                                                            <div class="rrs">
                                                                ...
                                                            </div>
                                                            <div class="lrs">
                                                                ...
                                                            </div>
                                                        </div>
                                                    </li>
                                                    <li ng-repeat="al in search.vars.lts">
                                                        <div class="ars">
                                                            <div class="rrs">
                                                                Location
                                                            </div>
                                                            <div class="lrs" ng-bind="al[0]"></div>
                                                        </div>
                                                    </li>
                                                    <li ng-show="search.vars.catld">
                                                        <div class="ars">
                                                            <div class="rrs">
                                                                ...
                                                            </div>
                                                            <div class="lrs">
                                                                ...
                                                            </div>
                                                        </div>
                                                    </li>
                                                    <li ng-repeat="ac in search.vars.cats">
                                                        <div class="ars">
                                                            <div class="rrs">   
                                                                Category
                                                            </div>
                                                            <div class="lrs" ng-bind="ac[0]"></div>
                                                        </div>
                                                    </li>
                                                    <li ng-show="search.vars.subld">
                                                        <div class="ars">
                                                            <div class="rrs">
                                                                ...
                                                            </div>
                                                            <div class="lrs">
                                                                ...
                                                            </div>
                                                        </div>
                                                    </li>
                                                    <li ng-repeat="ac in search.vars.subs">
                                                        <div class="ars">
                                                            <div class="rrs">
                                                                <span ng-bind="'In'"></span> <span class="hl-cat" ng-bind='ac[2]'></span>
                                                            </div>
                                                            <div class="lrs" ng-bind="ac[0]"></div>
                                                        </div>
                                                    </li>
                                                    <li ng-show="search.vars.kwld">
                                                        <div class="ars">
                                                            <div class="rrs">
                                                                ...
                                                            </div>
                                                            <div class="lrs">
                                                                ...
                                                            </div>
                                                        </div>
                                                    </li>
                                                    <li ng-repeat="ac in search.vars.kws">
                                                        <div class="ars rs">
                                                            <div class="rrs">
                                                                <span class="hl-no" ng-bind="ac[2] + ' Results'"></span> <span ng-bind="'In'"></span> <span class="hl-cat" ng-bind="ac[1]"
                                                                                                                                                            ></span>
                                                            </div>
                                                            <div class="lrs">
                                                                <div class="lrs" ng-bind="ac[0]"></div>
                                                            </div>
                                                        </div>
                                                    </li>
                                                </ul>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </li>
                </ul>
            </div>

            <div class="right" ng-if="ytheader.vars.me">
                <ul class="clearfix allEl">
                    <c:choose>
                        <c:when test="${sessionScope.ytAccount == null}">
                            <!--USER-->
                            <li>
                                <div class="anEl">
                                    <div class="vertical-center-container">
                                        <div class="center-content">
                                            <a href="${sessionScope.hrefLocale}/become-partner" class="yt-btn trans-btn auto-width-btn">
                                                <spring:message code="yt.header.button.signupAsPartner"/>
                                            </a>
                                        </div>
                                    </div>
                                </div>
                            </li>

                            <li>
                                <div class="anEl">
                                    <div class="vertical-center-container">
                                        <div class="center-content">
                                            <input type="button" value="<spring:message code="yt.header.button.signupAsTripper"/>"
                                                   class="yt-btn trans-btn auto-width-btn" ng-click="tripperSignup.funcs.openPopup()">
                                        </div>
                                    </div>
                                </div>
                            </li>

                            <li>
                                <div class="anEl">
                                    <div class="vertical-center-container">
                                        <div class="center-content">
                                            <span class="link" ng-click="logining.funcs.openPopup()"><spring:message code="yt.header.button.login"/></span>
                                        </div>
                                    </div>
                                </div>
                            </li>
                            <!--END USER-->
                        </c:when>
                        <c:otherwise>
                            <c:choose>
                                <c:when test="${sessionScope.ytAccount.admin}">
                                </c:when>
                                <c:when test="${sessionScope.ytAccount.partner}">
                                    <!--PARTNER-->
                                    <li>
                                        <div class="anEl">
                                            <div class="vertical-center-container">
                                                <div class="center-content">
                                                    <a href="${sessionScope.hrefLocale}/partner/dashboard"><spring:message code="yt.partner.trippdash.loginMenu.trippdash"/></a>
                                                </div>
                                            </div>
                                        </div>
                                    </li>
                                    <li>
                                        <div class="anEl">
                                            <div class="vertical-center-container">
                                                <div class="center-content">
                                                    <div class="menu">
                                                        <div class="name">
                                                            <img class="avatar" alt="Partner Avatar" src="${sessionScope.ytAccount.avatar}">
                                                            ${sessionScope.ytAccount.name}
                                                        </div>
                                                        <div class="list">
                                                            <span class="m-arrow"></span>
                                                            <ul>
                                                                <li>
                                                                    <a href="${sessionScope.hrefLocale}/partner/account-profile">
                                                                        <spring:message code="yt.header.link.partnerAccountSetting"/>
                                                                    </a>
                                                                </li>
                                                                <li>
                                                                    <a href="${sessionScope.hrefLocale}/logout">
                                                                        <spring:message code="yt.header.link.logout"/>
                                                                    </a>
                                                                </li>
                                                            </ul>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </li>
                                    <!--END PARTNER-->
                                </c:when>
                                <c:when test="${sessionScope.ytAccount.tripper}">
                                    <!--TRIPPER-->
                                    <li>
                                        <div class="anEl">
                                            <div class="vertical-center-container">
                                                <div class="center-content">
                                                    <div class="menu">
                                                        <div class="name">
                                                            <img class="avatar" alt="Tripper Avatar" src="${sessionScope.ytAccount.avatar}">
                                                            <span class="display-name">${sessionScope.ytAccount.name}</span> 
                                                        </div>
                                                        <div class="list">
                                                            <span class="m-arrow"></span>
                                                            <ul>
                                                                <div class="row">
                                                                    <a href="${sessionScope.hrefLocale}/tripper/my-booking">
                                                                        My Booking
                                                                    </a>
                                                                </div>
                                                                <div class="row">
                                                                    <a href="${sessionScope.hrefLocale}/tripper/buckets">
                                                                        My Bucket List
                                                                    </a>
                                                                </div>
                                                                <li>
                                                                    <a href="${sessionScope.hrefLocale}/tripper/account-profile">
                                                                        <spring:message code="yt.header.link.tripperProfile"/>
                                                                    </a>
                                                                </li>
                                                                <li>
                                                                    <a href="${sessionScope.hrefLocale}/logout">
                                                                        <spring:message code="yt.header.link.logout"/>
                                                                    </a>
                                                                </li>
                                                            </ul>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </li>
                                    <!--END TRIPPER-->
                                </c:when>
                            </c:choose>
                        </c:otherwise>
                    </c:choose>
                    <li>
                        <div class="anEl">
                            <div class="vertical-center-container">
                                <div class="center-content">
                                    <div class="menu m-lan">
                                        <span class="link open">
                                            <svg  width="15px" height="15px" viewBox="0 0 15 15">
                                            <defs>
                                            </defs>
                                            <g>
                                            <path style="fill:#4CBDC9;" d="M7.5,0C3.4,0,0,3.4,0,7.5S3.4,15,7.5,15S15,11.6,15,7.5S11.6,0,7.5,0z M6.3,0.8
                                                  c0.2,0,0.4-0.1,0.7-0.1v3.7C6.1,4.4,5.4,4.2,4.7,4c0.2-0.8,0.5-1.5,0.8-2S6.1,1.1,6.3,0.8z M8.3,0.7C8.6,1,9,1.5,9.3,2.2
                                                  c0.3,0.5,0.6,1.1,0.8,1.9C9.4,4.3,8.5,4.4,7.6,4.4l0,0V0.7C7.8,0.7,8.1,0.7,8.3,0.7z M10.3,4.7c0.2,0.7,0.3,1.6,0.4,2.5H7.6V5l0,0
                                                  C8.6,5,9.5,4.9,10.3,4.7z M7,5v2.2H4.1c0-1,0.2-1.8,0.4-2.6C5.2,4.8,6.1,4.9,7,5z M3.6,7.2H0.7c0.1-1.3,0.5-2.6,1.2-3.6
                                                  C2.3,3.8,3.1,4.2,4,4.4C3.8,5.2,3.6,6.2,3.6,7.2z M3.6,7.8c0,0.8,0.1,1.6,0.2,2.3c-1,0.3-1.8,0.7-2.1,0.9c-0.7-1-1-2.1-1-3.2H3.6z
                                                  M4.1,7.8H7v1.7c-1,0-1.9,0.2-2.7,0.4C4.2,9.3,4.1,8.5,4.1,7.8z M7,10v4.3c-0.4,0-0.7-0.1-1.1-0.2c-0.3-0.5-1.1-1.7-1.5-3.7
                                                  C5.2,10.3,6,10.1,7,10z M7.6,10c1.1,0,2.1,0.2,2.9,0.4c-0.3,1.9-1,3.1-1.4,3.7c-0.5,0.1-1,0.2-1.6,0.2L7.6,10L7.6,10z M7.6,9.5V7.8
                                                  h3.2c0,0.8,0,1.5-0.1,2.1C9.7,9.6,8.7,9.5,7.6,9.5z M11.3,7.8h3c0,1.2-0.4,2.3-0.9,3.2c-0.4-0.2-1.2-0.6-2.2-0.9
                                                  C11.2,9.4,11.3,8.6,11.3,7.8z M11.3,7.2c0-1-0.2-1.9-0.4-2.7c1-0.3,1.8-0.6,2.3-0.9c0.7,1,1.1,2.2,1.2,3.6H11.3z M12.8,3.2
                                                  c-0.5,0.2-1.2,0.5-2.1,0.8c-0.4-1.4-1.1-2.4-1.5-3.1C10.6,1.2,11.9,2.1,12.8,3.2z M5.5,1C5,1.6,4.5,2.6,4.1,3.9
                                                  C3.3,3.6,2.7,3.4,2.2,3.1C3.1,2.1,4.2,1.4,5.5,1z M1.9,11.5c0.4-0.2,1-0.5,1.9-0.8C4.1,12.2,4.6,13.3,5,14
                                                  C3.8,13.4,2.7,12.5,1.9,11.5z M9.9,13.9c0.4-0.7,0.9-1.8,1.1-3.3c0.9,0.3,1.6,0.6,2,0.8C12.3,12.5,11.2,13.4,9.9,13.9z"></path>
                                            </g>
                                            </svg>
                                            <span ng-bind="youtripper.vars.LANGS[youtripper.vars.data.currentLan].name + ' / ' + youtripper.vars.data.currentCur"></span>
                                            <span class="arrow"></span>
                                        </span>

                                        <div class="list">
                                            <span class="m-arrow"></span>
                                            <ul>
                                                <li>
                                                    <div class="lan-s">
                                                        <ul>
                                                            <li>
                                                                <div class="txt ico">
                                                                    <svg  width="15px" height="15px" viewBox="0 0 15 15">
                                                                    <path style="fill:#4CBDC9;" d="M7.5,0C3.4,0,0,3.4,0,7.5S3.4,15,7.5,15S15,11.6,15,7.5S11.6,0,7.5,0z M6.3,0.8
                                                                          c0.2,0,0.4-0.1,0.7-0.1v3.7C6.1,4.4,5.4,4.2,4.7,4c0.2-0.8,0.5-1.5,0.8-2S6.1,1.1,6.3,0.8z M8.3,0.7C8.6,1,9,1.5,9.3,2.2
                                                                          c0.3,0.5,0.6,1.1,0.8,1.9C9.4,4.3,8.5,4.4,7.6,4.4l0,0V0.7C7.8,0.7,8.1,0.7,8.3,0.7z M10.3,4.7c0.2,0.7,0.3,1.6,0.4,2.5H7.6V5l0,0
                                                                          C8.6,5,9.5,4.9,10.3,4.7z M7,5v2.2H4.1c0-1,0.2-1.8,0.4-2.6C5.2,4.8,6.1,4.9,7,5z M3.6,7.2H0.7c0.1-1.3,0.5-2.6,1.2-3.6
                                                                          C2.3,3.8,3.1,4.2,4,4.4C3.8,5.2,3.6,6.2,3.6,7.2z M3.6,7.8c0,0.8,0.1,1.6,0.2,2.3c-1,0.3-1.8,0.7-2.1,0.9c-0.7-1-1-2.1-1-3.2H3.6z
                                                                          M4.1,7.8H7v1.7c-1,0-1.9,0.2-2.7,0.4C4.2,9.3,4.1,8.5,4.1,7.8z M7,10v4.3c-0.4,0-0.7-0.1-1.1-0.2c-0.3-0.5-1.1-1.7-1.5-3.7
                                                                          C5.2,10.3,6,10.1,7,10z M7.6,10c1.1,0,2.1,0.2,2.9,0.4c-0.3,1.9-1,3.1-1.4,3.7c-0.5,0.1-1,0.2-1.6,0.2L7.6,10L7.6,10z M7.6,9.5V7.8
                                                                          h3.2c0,0.8,0,1.5-0.1,2.1C9.7,9.6,8.7,9.5,7.6,9.5z M11.3,7.8h3c0,1.2-0.4,2.3-0.9,3.2c-0.4-0.2-1.2-0.6-2.2-0.9
                                                                          C11.2,9.4,11.3,8.6,11.3,7.8z M11.3,7.2c0-1-0.2-1.9-0.4-2.7c1-0.3,1.8-0.6,2.3-0.9c0.7,1,1.1,2.2,1.2,3.6H11.3z M12.8,3.2
                                                                          c-0.5,0.2-1.2,0.5-2.1,0.8c-0.4-1.4-1.1-2.4-1.5-3.1C10.6,1.2,11.9,2.1,12.8,3.2z M5.5,1C5,1.6,4.5,2.6,4.1,3.9
                                                                          C3.3,3.6,2.7,3.4,2.2,3.1C3.1,2.1,4.2,1.4,5.5,1z M1.9,11.5c0.4-0.2,1-0.5,1.9-0.8C4.1,12.2,4.6,13.3,5,14
                                                                          C3.8,13.4,2.7,12.5,1.9,11.5z M9.9,13.9c0.4-0.7,0.9-1.8,1.1-3.3c0.9,0.3,1.6,0.6,2,0.8C12.3,12.5,11.2,13.4,9.9,13.9z"></path>

                                                                    </svg>
                                                                </div>
                                                            </li><li>
                                                                <div class="ddl-input ddl-l nod">
                                                                    <select name="" class="yt-input" ng-model="youtripper.vars.data.tempLan">
                                                                        <option ng-value="aCode" ng-repeat="(aCode, aLan) in youtripper.vars.LANGS" ng-bind="aLan.name"></option>
                                                                    </select>
                                                                </div>
                                                            </li><li>
                                                                <div class="txt">
                                                                    /
                                                                </div>
                                                            </li><li>
                                                                <div class="ddl-input ddl-c nod">
                                                                    <select name="" class="yt-input" ng-model="youtripper.vars.data.tempCur">
                                                                        <option ng-value="aCode" ng-repeat="(aCode, aCur) in youtripper.vars.CURS" ng-bind="aCode"></option>
                                                                    </select>
                                                                </div>
                                                            </li>
                                                        </ul>
                                                    </div>
                                                </li>
                                                <li>
                                                    <input type="button" value="<spring:message code="yt.partner.trippdash.loginMenu.saveBtn"/>" class="yt-btn red-btn" ng-click="youtripper.funcs.changeLanCur()"/>
                                                </li>
                                            </ul>

                                            <div class="center-spinner-block"
                                                 ng-class="{'loading' : youtripper.vars.lanCurChanging}">
                                                <div class="vertical-center-container">
                                                    <div class="center-content">
                                                        <span class="yt-spinner center"></span>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                </div>
                            </div>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
    </div>

    <!--SIGN UP POPUP-->
    <div class="yt-popup" id="signup-popup" ng-class="{'active' : tripperSignup.vars.popup.active}">
        <div class="vertical-center-container">
            <div class="center-content" ng-click="POPUP.closePopup(tripperSignup.vars.popup, $event)">
                <div class="popup">
                    <div class="body">
                        <span class="close-btn" ng-click="POPUP.closePopup(tripperSignup.vars.popup, $event)"></span>
                        <div class="container">
                            <div class="row header-lb">
                                <spring:message code="yt.tripper.registration.popup.header"/>
                            </div>

                            <div class="row">
                                <div class="group-f">
                                    <ul>
                                        <li>
                                            <div class="aG center-label">
                                                <spring:message code="yt.login.popup.label.loginwith"/>
                                            </div>
                                        </li>
                                        <li>
                                            <div class="aG">
                                                <input type="button" class="yt-btn fb-btn"
                                                       value="<spring:message code="yt.tripper.registration.button.facebook"/>"
                                                       ng-click="ytSocial.funcs.fbLogin()" />
                                            </div>
                                        </li>
                                        <li>
                                            <div class="aG">
                                                <input type="button" class="yt-btn red-btn" id="login-gg-2"
                                                       ng-click="ytSocial.funcs.ggLogin()"
                                                       value="<spring:message code="yt.tripper.registration.button.google"/>"/>
                                            </div>
                                        </li>
                                        <li>
                                            <div class="aG">
                                                <input type="button" class="yt-btn line-btn" ng-click="ytSocial.funcs.lpLogin()"
                                                       value="LINE" style="background-color: #06CC06"/>
                                            </div>
                                        </li>
                                    </ul>
                                </div>
                            </div>

                            <div class="row line-label">
                                <span class="txt">
                                    <spring:message code="yt.tripper.registration.txt.or"/>
                                </span>
                            </div>

                            <div class="row">
                                <div class="group-f">
                                    <ul>
                                        <li>
                                            <div class="aG center-label">
                                                <spring:message code="yt.tripper.registration.txt.create"/>
                                            </div>
                                        </li>
                                        <li>
                                            <div class="aG">
                                                <div class="spinner-input yt-input-ico status-input" ng-class="{'success' : tripperSignup.vars.popup.email.sc,
                                                        'err' : tripperSignup.vars.popup.email.e && !tripperSignup.vars.popup.email.sc}">
                                                    <input type="text" class="yt-input"  placeholder="<spring:message code="yt.tripper.registration.placeHolder.email"/>" 
                                                           ng-model="tripperSignup.vars.popup.email.val" ytmaxlength="100"  ng-change="tripperSignup.funcs.ech()"/>
                                                    <span class="yt-spinner" ng-show='tripperSignup.vars.popup.email.loading'></span>    
                                                    <div class="ico">
                                                        <svg width="18.8px" height="15px" viewBox="0 0 18.8 15">
                                                        <path style="fill: #555555" d="M16.9,0h-15C0.8,0,0,0.8,0,1.9l0,11.3c0,1,0.8,1.9,1.9,1.9h15c1,0,1.9-0.8,1.9-1.9V1.9
                                                              C18.8,0.8,17.9,0,16.9,0z M16.9,3.8L9.4,8.4L1.9,3.8V1.9l7.5,4.7l7.5-4.7V3.8z"></path>
                                                        </svg>
                                                    </div>
                                                    <div class="stt">
                                                        <svg class="sc" width="16.5px" height="12.5px" viewBox="0 0 16.5 12.5">
                                                        <line x1="5" y1="11.5" x2="1" y2="7.5"/>
                                                        <line x1="15.5" y1="1" x2="5" y2="11.5"/>
                                                        </svg>
                                                        <svg  class="er" width="12.5px" height="12.5px" viewBox="0 0 12.5 12.5">
                                                        <line  x1="11.5" y1="1" x2="1" y2="11.5"/>
                                                        <line  x1="11.5" y1="11.5" x2="1" y2="1"/>
                                                        </svg>
                                                    </div>
                                                </div>
                                            </div>
                                        </li>
                                        <li ng-if="tripperSignup.vars.popup.email.e">
                                            <div class="aG" ng-hide="tripperSignup.vars.popup.email.valid">
                                                <span class="error-msg">
                                                    <spring:message code="yt.tripper.registration.error.email"/>
                                                </span>
                                            </div>
                                            <div class="aG" ng-show="tripperSignup.vars.popup.email.used">
                                                <span class="error-msg">
                                                    <spring:message code="yt.tripper.registration.error.email.exist"/>
                                                </span>
                                            </div>
                                        </li>
                                        <li>
                                            <div class="aG">
                                                <div class="yt-input-ico status-input" ng-class="{'success' : tripperSignup.vars.popup.email.confirmation,
                                                        'err' : tripperSignup.vars.popup.email.re && !tripperSignup.vars.popup.email.confirmation}">
                                                    <input type="text" class="yt-input" placeholder="<spring:message code="yt.tripper.registration.placeHolder.reEmail"/>"
                                                           ng-model="tripperSignup.vars.popup.email.cVal" ng-change="tripperSignup.funcs.rech()"/>
                                                    <div class="ico">
                                                        <svg width="18.8px" height="15px" viewBox="0 0 18.8 15">
                                                        <path style="fill: #555555" d="M16.9,0h-15C0.8,0,0,0.8,0,1.9l0,11.3c0,1,0.8,1.9,1.9,1.9h15c1,0,1.9-0.8,1.9-1.9V1.9
                                                              C18.8,0.8,17.9,0,16.9,0z M16.9,3.8L9.4,8.4L1.9,3.8V1.9l7.5,4.7l7.5-4.7V3.8z"></path>
                                                        </svg>
                                                    </div>
                                                    <div class="stt">
                                                        <svg class="sc" width="16.5px" height="12.5px" viewBox="0 0 16.5 12.5">
                                                        <line x1="5" y1="11.5" x2="1" y2="7.5"/>
                                                        <line x1="15.5" y1="1" x2="5" y2="11.5"/>
                                                        </svg>
                                                        <svg  class="er" width="12.5px" height="12.5px" viewBox="0 0 12.5 12.5">
                                                        <line  x1="11.5" y1="1" x2="1" y2="11.5"/>
                                                        <line  x1="11.5" y1="11.5" x2="1" y2="1"/>
                                                        </svg>
                                                    </div>
                                                </div>
                                            </div>
                                        </li>
                                        <li>
                                            <div class="aG">
                                                <div class="yt-input-ico status-input" ng-class="{'success' : tripperSignup.vars.popup.password.sc,
                                                        'err' : tripperSignup.vars.popup.password.e && !tripperSignup.vars.popup.password.sc}">
                                                    <input type="password"  class="yt-input" placeholder="<spring:message code="yt.tripper.registration.placeHolder.password"/>"
                                                           ng-model="tripperSignup.vars.popup.password.val" ytmaxlength="32"
                                                           ng-change="tripperSignup.funcs.pch()"  />    
                                                    <div class="ico">
                                                        <svg width="11.4px" height="15px" viewBox="0 0 11.4 15">
                                                        <path style="fill: #555555" d="M10,5H9.3V3.6c0-2-1.6-3.6-3.6-3.6S2.1,1.6,2.1,3.6V5H1.4C0.6,5,0,5.6,0,6.4v7.1C0,14.4,0.6,15,1.4,15H10
                                                              c0.8,0,1.4-0.6,1.4-1.4V6.4C11.4,5.6,10.8,5,10,5z M5.7,11.4c-0.8,0-1.4-0.6-1.4-1.4s0.6-1.4,1.4-1.4S7.1,9.2,7.1,10
                                                              S6.5,11.4,5.7,11.4z M7.9,5H3.5V3.6c0-1.2,1-2.2,2.2-2.2s2.2,1,2.2,2.2V5z"/>
                                                        </svg>
                                                    </div>
                                                    <div class="stt">
                                                        <svg class="sc" width="16.5px" height="12.5px" viewBox="0 0 16.5 12.5">
                                                        <line x1="5" y1="11.5" x2="1" y2="7.5"/>
                                                        <line x1="15.5" y1="1" x2="5" y2="11.5"/>
                                                        </svg>
                                                        <svg  class="er" width="12.5px" height="12.5px" viewBox="0 0 12.5 12.5">
                                                        <line  x1="11.5" y1="1" x2="1" y2="11.5"/>
                                                        <line  x1="11.5" y1="11.5" x2="1" y2="1"/>
                                                        </svg>
                                                    </div>
                                                </div>
                                            </div>
                                        </li>
                                        <li ng-if="tripperSignup.vars.popup.password.e">
                                            <div class="aG"  ng-hide="tripperSignup.vars.popup.password.leValid">
                                                <span class="error-msg">
                                                    <spring:message code="yt.tripper.registration.error.password"/>
                                                </span>
                                            </div>
                                            <div class="aG" ng-hide="tripperSignup.vars.popup.password.foValid">
                                                <span class="error-msg">
                                                    <spring:message code="yt.tripper.registration.error.password.invalid"/>
                                                </span>
                                            </div>
                                        </li>
                                        <li>
                                            <div class="aG">
                                                <div class="yt-input-ico status-input"  ng-class="{'success' : tripperSignup.vars.popup.password.confirmation,
                                                        'err' : tripperSignup.vars.popup.password.re && !tripperSignup.vars.popup.password.confirmation}">
                                                    <input type="password" class="yt-input" placeholder="<spring:message code="yt.tripper.registration.placeHolder.rePassword"/>" 
                                                           ng-model="tripperSignup.vars.popup.password.cVal" 
                                                           ng-change="tripperSignup.funcs.rpch()"  />
                                                    <div class="ico">
                                                        <svg width="11.4px" height="15px" viewBox="0 0 11.4 15">
                                                        <path style="fill: #555555" d="M10,5H9.3V3.6c0-2-1.6-3.6-3.6-3.6S2.1,1.6,2.1,3.6V5H1.4C0.6,5,0,5.6,0,6.4v7.1C0,14.4,0.6,15,1.4,15H10
                                                              c0.8,0,1.4-0.6,1.4-1.4V6.4C11.4,5.6,10.8,5,10,5z M5.7,11.4c-0.8,0-1.4-0.6-1.4-1.4s0.6-1.4,1.4-1.4S7.1,9.2,7.1,10
                                                              S6.5,11.4,5.7,11.4z M7.9,5H3.5V3.6c0-1.2,1-2.2,2.2-2.2s2.2,1,2.2,2.2V5z"/>
                                                        </svg>
                                                    </div>
                                                    <div class="stt">
                                                        <svg class="sc" width="16.5px" height="12.5px" viewBox="0 0 16.5 12.5">
                                                        <line x1="5" y1="11.5" x2="1" y2="7.5"/>
                                                        <line x1="15.5" y1="1" x2="5" y2="11.5"/>
                                                        </svg>
                                                        <svg  class="er" width="12.5px" height="12.5px" viewBox="0 0 12.5 12.5">
                                                        <line  x1="11.5" y1="1" x2="1" y2="11.5"/>
                                                        <line  x1="11.5" y1="11.5" x2="1" y2="1"/>
                                                        </svg>
                                                    </div>
                                                </div>
                                            </div>
                                        </li>
                                        <li>
                                            <div class="aG">
                                                <input type="submit" class="yt-btn center-btn submit-btn" value="<spring:message code="yt.tripper.registration.button.signup"/>" 
                                                       ng-click="tripperSignup.funcs.signup()"/>
                                            </div>
                                        </li>
                                        <li>
                                            <div class="aG term-txt">
                                                <spring:message code="yt.tripper.registration.txt.term"/> 
                                                <a href="">
                                                    <spring:message code="yt.tripper.registration.link.term"/> 
                                                </a>
                                                <spring:message code="yt.tripper.registration.txt.and"/> 
                                                <a href="">
                                                    <spring:message code="yt.tripper.registration.link.privacy"/> 
                                                </a>
                                            </div>
                                        </li>
                                    </ul>
                                </div>
                            </div>

                            <div class="row">
                                <div class="login-txt">
                                    <spring:message code="yt.tripper.registration.txt.alreadyHaveAccount"/>
                                    <span class="link" ng-click="logining.funcs.openPopup()"><spring:message code="yt.tripper.registration.button.login"/></span>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="center-spinner-block" ng-class="{'loading' : tripperSignup.vars.loading}">
                        <div class="vertical-center-container">
                            <div class="center-content">
                                <span class="yt-spinner center"></span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!--END SIGNUP POPUP-->         

    <!--LOGIN POPUP-->
    <div class="yt-popup" id="login-popup" ng-class="{'active' : logining.vars.popup.active}">
        <div class="vertical-center-container">
            <div class="center-content" ng-click="!logining.vars.popup.loading ? POPUP.closePopup(logining.vars.popup, $event) : angular.noob()">
                <div class="popup">
                    <div class="body">
                        <span class="close-btn" ng-click="POPUP.closePopup(logining.vars.popup, $event)"></span>
                        <div class="container">
                            <div class="row">
                                <div class="group-f">
                                    <ul>
                                        <li>
                                            <div class="aG  center-label">
                                                <spring:message code="yt.login.popup.label.loginwith"/>
                                            </div>
                                        </li>
                                        <li>
                                            <div class="aG">
                                                <input type="button" class="yt-btn fb-btn" ng-click="ytSocial.funcs.fbLogin()" 
                                                       value="<spring:message code="yt.login.popup.button.facebook"/>" />
                                            </div>
                                        </li>
                                        <li>
                                            <div class="aG">
                                                <input type="button" class="yt-btn red-btn" ng-click="ytSocial.funcs.ggLogin()"
                                                       value="<spring:message code="yt.login.popup.button.google"/>"/>
                                            </div>
                                        </li>
                                        <li>
                                            <div class="aG">
                                                <input type="button" class="yt-btn line-btn" ng-click="ytSocial.funcs.lpLogin()"
                                                       value="LINE" style="background-color: #06CC06"/>
                                            </div>
                                        </li>
                                    </ul>
                                </div>
                            </div>

                            <div class="row line-label">
                                <span class="txt">
                                    <spring:message code="yt.login.popup.label.or"/>
                                </span>
                            </div>

                            <div class="row">
                                <div class="group-f">
                                    <ul>
                                        <li>
                                            <div class="aG">
                                                <div class="yt-input-ico">
                                                    <input class="yt-input" type="text" name="email" value="" ng-model="logining.vars.popup.data.email"
                                                           placeholder="<spring:message code="yt.login.popup.placeHolder.email.email"/>"/>
                                                    <div class="ico">
                                                        <svg width="18.8px" height="15px" viewBox="0 0 18.8 15">
                                                        <path style="fill: #555555" d="M16.9,0h-15C0.8,0,0,0.8,0,1.9l0,11.3c0,1,0.8,1.9,1.9,1.9h15c1,0,1.9-0.8,1.9-1.9V1.9
                                                              C18.8,0.8,17.9,0,16.9,0z M16.9,3.8L9.4,8.4L1.9,3.8V1.9l7.5,4.7l7.5-4.7V3.8z"></path>
                                                        </svg>
                                                    </div>
                                                </div>
                                            </div>
                                        </li>
                                        <li>
                                            <div class="aG">
                                                <div class="yt-input-ico">
                                                    <input class="yt-input" type="password" name="password" value="" ng-model="logining.vars.popup.data.password"
                                                           placeholder="<spring:message code="yt.login.popup.placeHolder.password"/>"/>
                                                    <div class="ico">
                                                        <svg width="11.4px" height="15px" viewBox="0 0 11.4 15">
                                                        <path style="fill: #555555" d="M10,5H9.3V3.6c0-2-1.6-3.6-3.6-3.6S2.1,1.6,2.1,3.6V5H1.4C0.6,5,0,5.6,0,6.4v7.1C0,14.4,0.6,15,1.4,15H10
                                                              c0.8,0,1.4-0.6,1.4-1.4V6.4C11.4,5.6,10.8,5,10,5z M5.7,11.4c-0.8,0-1.4-0.6-1.4-1.4s0.6-1.4,1.4-1.4S7.1,9.2,7.1,10
                                                              S6.5,11.4,5.7,11.4z M7.9,5H3.5V3.6c0-1.2,1-2.2,2.2-2.2s2.2,1,2.2,2.2V5z"/>
                                                        </svg>
                                                    </div>
                                                </div>
                                            </div>
                                        </li>
                                        <li>
                                            <div class="aG">
                                                <ul class="clearfix remember-forgot-row"
                                                    ng-init="logining.vars.user.remember = false">
                                                    <li>
                                                        <div class="checkbox-ctn">
                                                            <div class="yt-checkbox">
                                                                <input type="checkbox" name="remember" 
                                                                       ng-model="logining.vars.popup.data.remember"/>
                                                                <span></span>
                                                            </div>
                                                            <spring:message code="yt.login.popup.checkbox.remember"/>
                                                        </div>
                                                    </li>
                                                    <li class="forgot-password-col">
                                                        <span class="link"><spring:message code="yt.login.popup.link.forgotpassword"/></span>
                                                    </li>
                                                </ul>
                                            </div>
                                        </li>
                                        <li ng-if="logining.vars.popup.error">
                                            <div class="aG">
                                                <span class="error-msg">
                                                    <spring:message code="yt.login.popup.label.error.incorrectly"/>
                                                </span>
                                            </div>
                                        </li>
                                        <li>
                                            <div class="aG">
                                                <input type="button" class="yt-btn blue-btn" ng-click="logining.funcs.login()"
                                                       value="<spring:message code="yt.login.popup.txt.login"/>"/>
                                            </div>
                                        </li>
                                        <li>
                                            <div class="aG signup-label">
                                                <spring:message code="yt.login.popup.label.donthaveaccount"/>
                                                <span class="link" ng-click="tripperSignup.funcs.openPopup()">
                                                    <spring:message code="yt.login.popup.txt.signup"/>
                                                </span>
                                            </div>
                                        </li>
                                    </ul>
                                </div>
                            </div>


                        </div>
                    </div>

                    <div class="center-spinner-block" ng-class="{'loading' : logining.vars.popup.loading}">
                        <div class="vertical-center-container">
                            <div class="center-content">
                                <span class="yt-spinner center"></span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </div>
    <!--END LOGIN POPUP-->
</header>
