<%-- 
    Document   : AccountSetting
    Created on : Jun 18, 2018, 10:54:44 AM
    Author     : nickn
--%>

<%@page import="javaclass.common.YTPartnerData"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="${pageContext.response.locale}" currency="${sessionScope.ytCurrency}" ng-app="youtripper" ng-controller="YoutripperController">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="../common/commonhead.jsp" %>
        <script src="${requestScope.jsURL}partner-account-setting.js"></script>
        <script src="${requestScope.jsURL}partner-account-certification.js"></script>

        <link rel="stylesheet" href="${requestScope.cssURL}partner-account-setting.css" >
        <link rel="stylesheet" href="${requestScope.cssURL}partner-account-certificate.css" >

        <title><spring:message code="yt.partner.accountcertification.browser.header"/></title>
    </head>
    <body>
        <div id="padding-header" ng-cloak ng-init="hes = 'certificate'">
            <%@include file="AccountSettingMenu.jsp" %>

            <div id="account-body" ng-controller="PartnerAccountCertification">
                <div class="clearfix">
                    <div class="yt-normal-container">
                        <div class="body-l">
                            <%@include file="PartnerAccountCommon.jsp" %>
                        </div>
                        <div class="body-r">
                            <div id="account-certificate">
                                <div class="ct-b">
                                    <div class="ct-tt">
                                        <spring:message code="yt.partner.accountcertification.header"/>
                                    </div>
                                    <div class="ct-bd">
                                        <div class="ins">
                                            <spring:message code="yt.partner.accountcertification.content"/>
                                        </div>
                                        <div class="docs">
                                            <div class="doc-tt">
                                                <spring:message code="yt.partner.accountcertification.subheader"/>
                                            </div>
                                            <div class="cers">
                                                <ul>
                                                    <li ng-repeat="doc in  certification.vars.cal.docs">
                                                        <div class="aCer">
                                                            <div class="ctrls">
                                                                <span class="link"><a target="_blank" 
                                                                                      ng-href="{{doc.imgShow}}"><spring:message code="yt.partner.accountcertification.common.view.txt"/></a></span> / 
                                                                <span class="link" ng-click="certification.funcs.odc(doc.fileID)"><spring:message code="yt.partner.accountcertification.common.delete.txt"/></span>
                                                            </div>
                                                            <div class="name">
                                                                {{doc.fileName}}
                                                            </div>
                                                        </div>
                                                    </li>
                                                </ul>
                                                <div class="no-cer" ng-if="!certification.vars.cal.docs.length">
                                                    <spring:message code="yt.partner.accountcertification.common.noDocument"/>
                                                </div>
                                            </div>

                                            <div class="upload-btn">
                                                <button class="yt-btn" ng-click="certification.funcs.oup()"><spring:message code="yt.partner.accountcertification.common.upload.txt"/></button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="yt-popup" id="upp" ng-class="{
                        'active':certification.vars.pops.upop.active}">
                    <div class="vertical-center-container">
                        <div class="center-content">
                            <div class="popup">
                                <div class="body">
                                    <div class="container">
                                        <div class="row">
                                            <div class="inr">
                                                <div class="lb">
                                                    <spring:message code="yt.partner.accountcertification.popup.upload.documentName.text"/>
                                                </div>
                                                <div class="in status-input" ng-class="{
                                                        'err':certification.vars.pops.upop.fnError}">
                                                    <input ng-model="certification.vars.pops.upop.fn" 
                                                           ytmaxlength="200"
                                                           type="text" class="yt-input" placeholder="<spring:message code="yt.partner.accountcertification.popup.upload.documentName.input"/>"/>
                                                    <div class="stt">
                                                        <svg class="sc" width="16.5px" height="12.5px" viewBox="0 0 16.5 12.5">
                                                        <line x1="5" y1="11.5" x2="1" y2="7.5"></line>
                                                        <line x1="15.5" y1="1" x2="5" y2="11.5"></line>
                                                        </svg>
                                                        <svg class="er" width="12.5px" height="12.5px" viewBox="0 0 12.5 12.5">
                                                        <line x1="11.5" y1="1" x2="1" y2="11.5"></line>
                                                        <line x1="11.5" y1="11.5" x2="1" y2="1"></line>
                                                        </svg>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="up-b" ng-style="{
                                                             'background-image': certification.vars.pops.upop.burl}" style="position: relative" >
                                                <div class="center-spinner-block loading"  ng-show="certification.vars.pops.upop.lding">
                                                    <div class="vertical-center-container">
                                                        <div class="center-content">
                                                            <span class="yt-spinner center"></span>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="b-ct" 

                                                     >
                                                    <div class="u-btn" ng-click="certification.funcs.bi()"
                                                         ng-if="!certification.vars.pops.upop.img.length"
                                                         >
                                                        <div class="vertical-center-container">
                                                            <div class="center-content" >

                                                                <svg width="20px" height="13.3px" viewBox="0 0 20 13.3" >
                                                                <path style="fill: #9C9B9C" d="M10.1,3.3c-2.2,0-4,1.8-4,4s1.8,4,4,4s4-1.8,4-4S12.3,3.3,10.1,3.3z"></path>
                                                                <path style="fill: #9C9B9C" d="M18.1,1.3h-2.6c-0.2,0-0.4-0.1-0.6-0.3c-0.4-0.6-0.7-1-1.1-1h-7C6.4,0,6.1,0.5,5.7,1
                                                                      C5.6,1.2,5.4,1.3,5.2,1.3H2.1C1,1.3,0,2.2,0,3.3v8c0,1.1,1,2,2.1,2h16c1.1,0,1.9-0.9,1.9-2v-8C20,2.2,19.2,1.3,18.1,1.3z M2.1,4.5
                                                                      c-0.6,0-1-0.4-1-1s0.4-1,1-1s1,0.4,1,1S2.6,4.5,2.1,4.5z M10.1,12c-2.6,0-4.7-2.1-4.7-4.7s2.1-4.7,4.7-4.7c2.6,0,4.7,2.1,4.7,4.7
                                                                      S12.6,12,10.1,12z"></path>
                                                                </svg><br/>
                                                                <span >
                                                                    <spring:message code="yt.partner.accountcertification.popup.upload.clickHere.text"/>  
                                                                </span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <input type="file" id="certificationImage" name=""
                                                           hidden=""
                                                           accept="image/png,image/jpeg" image-file
                                                           ng-model="certification.vars.pops.upop.na"
                                                           ng-change="certification.funcs.giu()"
                                                           />
                                                    <div class="e-btn" ng-click="certification.funcs.bi()"
                                                         ng-if="certification.vars.pops.upop.img.length"
                                                         >
                                                        <button class="yt-btn red-btn"><spring:message code="yt.partner.accountcertification.popup.upload.edit.text"/></button>
                                                    </div>
                                                </div>



                                                <div class="er-msgs" >
                                                    <span class="msg error-msg" ng-if="certification.vars.pops.upop.uError">
                                                        <spring:message code="yt.partner.accountcertification.popup.upload.notUpload.error"/>
                                                    </span>
                                                    <span class="msg error-msg" ng-if="certification.vars.pops.upop.dError">
                                                        <spring:message code="yt.partner.accountcertification.popup.upload.demension.error"/>
                                                    </span>
                                                    <span class="msg error-msg" ng-if="certification.vars.pops.upop.sError">
                                                        <spring:message code="yt.partner.accountcertification.popup.upload.size.error"/>
                                                    </span>
                                                    <span class="msg error-msg" ng-if="certification.vars.pops.upop.bError">
                                                        Your Document file is broken.
                                                    </span>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="ctrls">
                                                <ul class="clearfix">
                                                    <li>
                                                        <button class="yt-btn" 
                                                                ng-click="certification.funcs.sud()"><spring:message code="yt.partner.accountcertification.common.upload.txt"/></button>
                                                    </li>
                                                    <li>
                                                        <button class="yt-btn grey-btn"
                                                                ng-click="certification.funcs.cup()"
                                                                ><spring:message code="yt.partner.accountcertification.common.cancel.txt"/></button>
                                                    </li>
                                                </ul>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="yt-popup" id="de-pop" ng-class="{'active':certification.vars.pops.dcf.active}">
                    <div class="vertical-center-container">
                        <div class="center-content">
                            <div class="popup">
                                <div class="center-spinner-block loading"  ng-show="certification.vars.pops.dcf.lding">
                                    <div class="vertical-center-container">
                                        <div class="center-content">
                                            <span class="yt-spinner center"></span>
                                        </div>
                                    </div>
                                </div>
                                <div class="body">
                                    <div class="container">
                                        <div class="row">
                                            <div class="ques">
                                                <spring:message code="yt.partner.accountcertification.popup.delete.content"/>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="ctrls">
                                                <ul class="clearfix">
                                                    <li>
                                                        <button class="yt-btn red-btn" ng-click="certification.funcs.cdp()"><spring:message code="yt.partner.accountcertification.common.continue.txt"/></button>
                                                    </li>
                                                    <li>
                                                        <button class="yt-btn grey-btn" ng-click="certification.funcs.cdlp()"><spring:message code="yt.partner.accountcertification.common.cancel.txt"/></button>
                                                    </li>
                                                </ul>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <%@include file="../common/header.jsp" %>
        </div>
    </body>
</html>
