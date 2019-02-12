<%-- 
    Document   : PartnerVerification
    Created on : Jun 28, 2018, 11:33:15 AM
    Author     : nickn
--%>

<%@page import="javaclass.utility.YTFileUtility"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="${pageContext.response.locale}" currency="${sessionScope.ytCurrency}" ng-app="youtripper" ng-controller="YoutripperController">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="../common/commonhead.jsp" %>
        <script src="${requestScope.jsURL}partner-account-setting.js"></script>
        <script src="${requestScope.jsURL}partner-account-verification.js"></script>

        <link rel="stylesheet" href="${requestScope.cssURL}partner-account-setting.css" >
        <link rel="stylesheet" href="${requestScope.cssURL}partner-account-verification-temp.css" >

        <title><spring:message code="yt.partner.accountverification.browser.header"/></title>

    </head>
    <body>
        <div id="padding-header"  ng-controller="PartnerAccountVerification" ng-cloak ng-init="hes = 'verification'">
            <%@include file="AccountSettingMenu.jsp" %>

            <div id="account-body">
                <div class="clearfix">
                    <div class="yt-normal-container">
                        <div class="body-l">
                            <%@include file="PartnerAccountCommon.jsp" %>
                        </div>
                        <div class="body-r">
                            <div id="account-verification">
                                <ul>
                                    <li>
                                        <div class="aSec">
                                            <div class="s-ct">
                                                <div class="s-tt">
                                                    <spring:message code="yt.partner.accountverification.personal.header"/>
                                                </div>
                                                <div class="s-bd">
                                                    <div class="center-spinner-block loading" ng-show="verification.vars.psn.loading">
                                                        <div class="vertical-center-container">
                                                            <div class="center-content">
                                                                <span class="yt-spinner center"></span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div id="psn-bd">
                                                        <ul class="rows">
                                                            <li>
                                                                <div class="ins">
                                                                    <spring:message code="yt.partner.accountverification.personal.content"/> 

                                                                </div> 
                                                            </li>
                                                            <li>
                                                                <div class="f-sec">
                                                                    <div class="f-tt">
                                                                        <spring:message code="yt.partner.accountverification.common.requiredDoc"/>
                                                                        <span class="add-btn" ng-if="!verification.vars.psn.isS"
                                                                              ng-click="verification.funcs.oup('personal')">
                                                                            <span class="btn"></span><spring:message code="yt.partner.accountverification.common.upload.txt"/>
                                                                        </span>
                                                                    </div>
                                                                    <div class="f-bd">
                                                                        <div class="doc-s">
                                                                            <ul class="docs">
                                                                                <li ng-repeat="doc in  verification.vars.psn.docs">
                                                                                    <div class="aDoc">
                                                                                        <div class="ctrs">
                                                                                            <a target="_blank" ng-href="{{doc.imgShow}}">
                                                                                                <spring:message code="yt.partner.accountverification.common.view.txt"/>
                                                                                            </a>
                                                                                            <span ng-if="!verification.vars.psn.isS">/</span>   
                                                                                            <span ng-if="!verification.vars.psn.isS" class="link" ng-click="verification.funcs.odc(doc.fileID, 'personal')">
                                                                                                <spring:message code="yt.partner.accountverification.common.delete.txt"/>
                                                                                            </span>
                                                                                        </div>
                                                                                        <div class="na">
                                                                                            {{doc.fileName}}
                                                                                        </div>
                                                                                    </div>
                                                                                </li>
                                                                            </ul>

                                                                            <div class="no-doc" ng-if="!verification.vars.psn.docs.length"
                                                                                 ng-class="{'err-no-doc':verification.vars.psn.noDError}"
                                                                                 >
                                                                                <spring:message code="yt.partner.accountverification.common.noDocument"/>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </li>
                                                            <li>
                                                                <div class="f-sec">
                                                                    <div class="f-tt">
                                                                        <spring:message code="yt.partner.accountverification.common.requiredInfo"/>
                                                                    </div>
                                                                    <div class="f-bd">
                                                                        <div class="form">
                                                                            <ul>
                                                                                <li>
                                                                                    <div class="aI">
                                                                                        <div class="lb">
                                                                                            <spring:message code="yt.partner.accountverification.personal.taxID.text"/>
                                                                                        </div>
                                                                                        <div class="in status-input" ng-class="{'err':verification.vars.psn.error}">
                                                                                            <input number-str-input ng-model="verification.vars.psn.vid" 
                                                                                                   ng-readonly="verification.vars.psn.isS" ytmaxlength="15" class="yt-input"
                                                                                                   ng-class="{'readonly':verification.vars.psn.isS}"
                                                                                                   type="text" placeholder="<spring:message code="yt.partner.accountverification.personal.taxID.text"/>"/>
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
                                                                                </li>
                                                                            </ul>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </li>
                                                            <li ng-if="!verification.vars.psn.isS">
                                                                <div class="sm-btn">
                                                                    <button class="yt-btn red-btn" ng-click="verification.funcs.ospp('personal')"><spring:message code="yt.partner.accountverification.common.submit.txt"/></button>
                                                                </div>
                                                            </li>
                                                        </ul>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </li>
                                    <li>
                                        <div class="aSec">
                                            <div class="s-ct">
                                                <div class="s-tt">
                                                    <spring:message code="yt.partner.accountverification.bank.header"/>
                                                </div>
                                                <div class="s-bd">
                                                    <div class="center-spinner-block loading"  ng-show="verification.vars.bank.loading">
                                                        <div class="vertical-center-container">
                                                            <div class="center-content">
                                                                <span class="yt-spinner center"></span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <ul class="rows">
                                                        <li>
                                                            <div class="ins">
                                                                <spring:message code="yt.partner.accountverification.bank.content"/>
                                                            </div>
                                                        </li>
                                                        <li>
                                                            <div class="f-sec">
                                                                <div class="f-tt">
                                                                    <spring:message code="yt.partner.accountverification.common.requiredDoc"/> <span class="add-btn" ng-click="verification.funcs.oup('bank')" ng-if="!verification.vars.bank.isS">
                                                                        <span class="btn"></span><spring:message code="yt.partner.accountverification.common.upload.txt"/></span>
                                                                </div>
                                                                <div class="f-bd">
                                                                    <div class="doc-s">
                                                                        <ul class="docs">
                                                                            <li ng-repeat="doc in  verification.vars.bank.docs">
                                                                                <div class="aDoc">
                                                                                    <div class="ctrs">
                                                                                        <span class="link"><a target="_blank" 
                                                                                                              ng-href="{{doc.imgShow}}"><spring:message code="yt.partner.accountverification.common.view.txt"/></a></span> 
                                                                                        <span ng-if="!verification.vars.bank.isS">/</span>    
                                                                                        <span ng-if="!verification.vars.bank.isS" 
                                                                                              class="link" ng-click="verification.funcs.odc(doc.fileID, 'bank')">
                                                                                            <spring:message code="yt.partner.accountverification.common.delete.txt"/>
                                                                                        </span>
                                                                                    </div>
                                                                                    <div class="na">
                                                                                        {{doc.fileName}}
                                                                                    </div>
                                                                                </div>
                                                                            </li>
                                                                        </ul>

                                                                        <div class="no-doc" 
                                                                             ng-class="{'err-no-doc':verification.vars.bank.noDError}"
                                                                             ng-if="!verification.vars.bank.docs.length">
                                                                            <spring:message code="yt.partner.accountverification.common.noDocument"/>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </li>
                                                        <li>
                                                            <div class="f-sec">
                                                                <div class="f-tt">
                                                                    <spring:message code="yt.partner.accountverification.common.requiredInfo"/>
                                                                </div>
                                                                <div class="f-bd">
                                                                    <div class="form">
                                                                        <ul>
                                                                            <li>
                                                                                <div class="aI">
                                                                                    <div class="lb">
                                                                                        <spring:message code="yt.partner.accountverification.bank.bankName.text"/>
                                                                                    </div>
                                                                                    <div class="in status-input" ng-class="{
                                                                                            'err':verification.vars.bank.errorBid}">
                                                                                        <div class="ddl-input" ng-class="{'readonly':verification.vars.bank.isS,'nov':verification.vars.bank.bid === null}">
                                                                                            <select name="" class="yt-input" ng-class="{'readonly':verification.vars.bank.isS}"
                                                                                                    ng-disabled="verification.vars.bank.isS"
                                                                                                    ng-model="verification.vars.bank.bid">
                                                                                                <option ng-value="null" disabled><spring:message code="yt.partner.accountverification.bank.bankName.select"/></option>
                                                                                                <option value="bk"><spring:message code="yt.partner.accountverification.bank1"/></option>
                                                                                                <option value="ktb"><spring:message code="yt.partner.accountverification.bank2"/></option>
                                                                                                <option value="boa"><spring:message code="yt.partner.accountverification.bank3"/></option>
                                                                                                <option value="kskb"><spring:message code="yt.partner.accountverification.bank4"/></option>
                                                                                                <option value="knkb"><spring:message code="yt.partner.accountverification.bank5"/></option>
                                                                                                <option value="ctb"><spring:message code="yt.partner.accountverification.bank6"/></option>
                                                                                                <option value="tmb"><spring:message code="yt.partner.accountverification.bank7"/></option>
                                                                                                <option value="tiscb"><spring:message code="yt.partner.accountverification.bank8"/></option>
                                                                                                <option value="bt"><spring:message code="yt.partner.accountverification.bank9"/></option>
                                                                                                <option value="scb"><spring:message code="yt.partner.accountverification.bank10"/></option>
                                                                                                <option value="tb"><spring:message code="yt.partner.accountverification.bank11"/></option>
                                                                                                <option value="sacb"><spring:message code="yt.partner.accountverification.bank12"/></option>
                                                                                                <option value="uob"><spring:message code="yt.partner.accountverification.bank13"/></option>
                                                                                                <option value="scbt"><spring:message code="yt.partner.accountverification.bank14"/></option>
                                                                                                <option value="micb"><spring:message code="yt.partner.accountverification.bank15"/></option>
                                                                                                <option value="aslb"><spring:message code="yt.partner.accountverification.bank16"/></option>
                                                                                                <option value="sbot"><spring:message code="yt.partner.accountverification.bank17"/></option>
                                                                                                <option value="aac"><spring:message code="yt.partner.accountverification.bank18"/></option>
                                                                                                <option value="eibt"><spring:message code="yt.partner.accountverification.bank19"/></option>
                                                                                                <option value="gsb"><spring:message code="yt.partner.accountverification.bank20"/></option>
                                                                                                <option value="ghb"><spring:message code="yt.partner.accountverification.bank21"/></option>
                                                                                                <option value="ibt"><spring:message code="yt.partner.accountverification.bank22"/></option>
                                                                                            </select>
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
                                                                            </li>
                                                                            <li>
                                                                                <div class="aI">
                                                                                    <div class="lb">
                                                                                        <spring:message code="yt.partner.accountverification.bank.holder.text"/>
                                                                                    </div>
                                                                                    <div class="in status-input" ng-class="{
                                                                                            'err':verification.vars.bank.errorBna}">
                                                                                        <input ng-model="verification.vars.bank.bna" 
                                                                                               ytmaxlength="200"
                                                                                               ng-class="{
                                                                                                       'readonly':verification.vars.bank.isS}"
                                                                                               ng-readonly="verification.vars.bank.isS"
                                                                                               type="text" placeholder="<spring:message code="yt.partner.accountverification.bank.holder.input"/>" class="yt-input"/>
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
                                                                            </li>
                                                                            <li>
                                                                                <div class="aI">
                                                                                    <div class="lb">
                                                                                        <spring:message code="yt.partner.accountverification.bank.accountNo.text"/>
                                                                                    </div>
                                                                                    <div class="in status-input" ng-class="{
                                                                                            'err':verification.vars.bank.errorBno}">
                                                                                        <input ng-model="verification.vars.bank.bno"  
                                                                                               number-str-input
                                                                                               ytmaxlength="50"
                                                                                               ng-class="{
                                                                                                       'readonly':verification.vars.bank.isS}"
                                                                                               ng-readonly="verification.vars.bank.isS"
                                                                                               type="text" placeholder="<spring:message code="yt.partner.accountverification.bank.accountNo.input"/>" class="yt-input"/>
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
                                                                            </li>
                                                                        </ul>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </li>
                                                        <li ng-if="!verification.vars.bank.isS">
                                                            <div class="sm-btn">
                                                                <button class="yt-btn red-btn" ng-click="verification.funcs.osbp('bank')">
                                                                    <spring:message code="yt.partner.accountverification.common.submit.txt"/>
                                                                </button>
                                                            </div>
                                                        </li>
                                                    </ul>

                                                </div>
                                            </div>
                                        </div>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="yt-popup" id="upp" ng-class="{
                    'active':verification.vars.pops.upop.active}">
                <div class="vertical-center-container">
                    <div class="center-content">
                        <div class="popup">
                            <div class="body">
                                <div class="container">
                                    <div class="row">
                                        <div class="inr">
                                            <div class="lb">
                                                <spring:message code="yt.partner.accountverification.popup.upload.documentName.text"/>
                                            </div>
                                            <div class="in status-input" ng-class="{
                                                    'err':verification.vars.pops.upop.fnError}">
                                                <input ng-model="verification.vars.pops.upop.fn" 
                                                       ytmaxlength="200"
                                                       type="text" class="yt-input" placeholder="<spring:message code="yt.partner.accountverification.popup.upload.documentName.input"/>"/>
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
                                        <div class="up-b" style="position: relative" ng-style="{'background-image': verification.vars.pops.upop.burl}">
                                            <div class="center-spinner-block loading"  ng-show="verification.vars.pops.upop.lding">
                                                <div class="vertical-center-container">
                                                    <div class="center-content">
                                                        <span class="yt-spinner center"></span>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="b-ct">
                                                <div class="u-btn" ng-click="verification.funcs.bi()" ng-if="!verification.vars.pops.upop.img.length">
                                                    <div class="vertical-center-container">
                                                        <div class="center-content" >
                                                            <svg width="20px" height="13.3px" viewBox="0 0 20 13.3">
                                                            <path style="fill: #9C9B9C" d="M10.1,3.3c-2.2,0-4,1.8-4,4s1.8,4,4,4s4-1.8,4-4S12.3,3.3,10.1,3.3z"></path>
                                                            <path style="fill: #9C9B9C" d="M18.1,1.3h-2.6c-0.2,0-0.4-0.1-0.6-0.3c-0.4-0.6-0.7-1-1.1-1h-7C6.4,0,6.1,0.5,5.7,1
                                                                  C5.6,1.2,5.4,1.3,5.2,1.3H2.1C1,1.3,0,2.2,0,3.3v8c0,1.1,1,2,2.1,2h16c1.1,0,1.9-0.9,1.9-2v-8C20,2.2,19.2,1.3,18.1,1.3z M2.1,4.5
                                                                  c-0.6,0-1-0.4-1-1s0.4-1,1-1s1,0.4,1,1S2.6,4.5,2.1,4.5z M10.1,12c-2.6,0-4.7-2.1-4.7-4.7s2.1-4.7,4.7-4.7c2.6,0,4.7,2.1,4.7,4.7
                                                                  S12.6,12,10.1,12z"></path>
                                                            </svg><br/>
                                                            <span ng-if="!verification.vars.pops.upop.tn.length">
                                                                <spring:message code="yt.partner.accountverification.popup.upload.clickHere.text"/>  
                                                            </span>
                                                            <!--                                                            <span class="link" ng-if="verification.vars.pops.upop.img.length"> 
                                                                                                                            <a target="_blank" 
                                                                                                                                ng-href="{{verification.vars.pops.upop.link + verification.vars.pops.upop.img}}">
                                                                                                                                {{verification.vars.pops.upop.tn}}
                                                                                                                            </a>
                                                                                                                        </span>-->
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="pdf-file" ng-if="verification.vars.pops.upop.fty === verification.vars.pops.upop.FILE_TYPE.pdf &&
                                                            verification.vars.pops.upop.img.length">
                                                    <div class="vertical-center-container">
                                                        <div class="center-content" >
                                                            <div class="fi">
                                                                <a target="_blank" 
                                                                   ng-href="{{verification.vars.pops.upop.linkPDF}}">
                                                                    <div class="ico">
                                                                        <svg  width="30px" height="37.4px" viewBox="-479.5 261.9 30 37.4">
                                                                        <path style="fill:#4CBDC9;" d="M-453.4,264.9c0.4,0,0.8,0.4,0.8,0.8v29.8c0,0.4-0.4,0.8-0.8,0.8h-22.3c-0.4,0-0.8-0.4-0.8-0.8v-29.8
                                                                              c0-0.4,0.4-0.8,0.8-0.8H-453.4 M-453.4,261.9h-22.3c-2.2,0-3.8,1.6-3.8,3.8v29.8c0,2,1.6,3.8,3.8,3.8h22.5c2,0,3.8-1.6,3.8-3.8
                                                                              v-29.8C-449.6,263.5-451.2,261.9-453.4,261.9L-453.4,261.9z"/>
                                                                        <rect x="-472.9" y="269.5" style="fill:#4CBDC9;" width="12.7" height="3"/>
                                                                        <rect x="-472.9" y="275.8" style="fill:#4CBDC9;" width="12.7" height="3"/>
                                                                        <rect x="-472.9" y="282.2" style="fill:#4CBDC9;" width="12.7" height="3"/>
                                                                        <rect x="-472.9" y="288.7" style="fill:#4CBDC9;" width="12.7" height="3"/>
                                                                        <circle style="fill:#4CBDC9;" cx="-477.1" cy="270.9" r="2.4"/>
                                                                        <circle style="fill:#4CBDC9;" cx="-477.1" cy="277.4" r="2.4"/>
                                                                        <circle style="fill:#4CBDC9;" cx="-477.1" cy="283.8" r="2.4"/>
                                                                        <circle style="fill:#4CBDC9;" cx="-477.1" cy="290.1" r="2.4"/>
                                                                        </svg>
                                                                    </div>
                                                                    <div class="na">
                                                                        {{verification.vars.pops.upop.tn}}
                                                                    </div>
                                                                </a>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <input type="file" id="verificationImage" name="" hidden=""
                                                       accept="image/png,image/jpeg,application/pdf" image-pdf-file
                                                       ng-model="verification.vars.pops.upop.na"
                                                       ng-change="verification.funcs.giu()"/>
                                                <div class="e-btn" ng-click="verification.funcs.bi()"
                                                     ng-if="verification.vars.pops.upop.img.length">
                                                    <button class="yt-btn red-btn"><spring:message code="yt.partner.accountverification.popup.upload.edit.text"/></button>
                                                </div>
                                            </div>
                                            <div class="er-msgs" >
                                                <span class="msg error-msg" ng-if="verification.vars.pops.upop.uError">
                                                    <spring:message code="yt.partner.accountverification.popup.upload.notUpload.error"/>
                                                </span>
                                                <span class="msg error-msg" ng-if="verification.vars.pops.upop.dError">
                                                    <spring:message code="yt.partner.accountverification.popup.upload.demension.error"/>
                                                </span>
                                                <span class="msg error-msg" ng-if="verification.vars.pops.upop.sError">
                                                    <spring:message code="yt.partner.accountverification.popup.upload.size.error"/>
                                                </span>
                                                <span class="msg error-msg" ng-if="verification.vars.pops.upop.bError">
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
                                                            ng-click="verification.funcs.sud()"><spring:message code="yt.partner.accountverification.common.upload.txt"/></button>
                                                </li>
                                                <li>
                                                    <button class="yt-btn grey-btn"
                                                            ng-click="verification.funcs.cup()"
                                                            ><spring:message code="yt.partner.accountverification.common.cancel.txt"/></button>
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

            <div class="yt-popup de-pop" ng-class="{'active':verification.vars.pops.dcf.active}">
                <div class="vertical-center-container">
                    <div class="center-content">
                        <div class="popup">
                            <div class="center-spinner-block loading"  ng-show="verification.vars.pops.dcf.lding">
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
                                            <spring:message code="yt.partner.accountverification.popup.delete.content"/>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="ctrls">
                                            <ul class="clearfix">
                                                <li>
                                                    <button class="yt-btn red-btn" ng-click="verification.funcs.cdp()"><spring:message code="yt.partner.accountverification.common.continue.txt"/></button>
                                                </li>
                                                <li>
                                                    <button class="yt-btn grey-btn" ng-click="verification.funcs.cdlp()"><spring:message code="yt.partner.accountverification.common.cancel.txt"/></button>
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

            <div class="yt-popup de-pop" ng-class="{'active':verification.vars.psn.popup.active}">
                <div class="vertical-center-container">
                    <div class="center-content">
                        <div class="popup">
                            <div class="body">
                                <div class="center-spinner-block" ng-class="{'loading' : verification.vars.psn.popup.loading}">
                                    <div class="vertical-center-container">
                                        <div class="center-content">
                                            <span class="yt-spinner center"></span>
                                        </div>
                                    </div>
                                </div>
                                <div class="container">
                                    <div class="row">
                                        <div class="ques">
                                            <spring:message code="yt.partner.accountverification.popup.confirm.personal"/>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="ctrls">
                                            <ul class="clearfix">
                                                <li>
                                                    <button class="yt-btn red-btn" ng-click="verification.funcs.smpsn()"><spring:message code="yt.partner.accountverification.common.continue.txt"/></button>
                                                </li>
                                                <li>
                                                    <button class="yt-btn grey-btn" ng-click="POPUP.closePopup(verification.vars.psn.popup)"><spring:message code="yt.partner.accountverification.common.cancel.txt"/></button>
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

            <div class="yt-popup de-pop" ng-class="{'active':verification.vars.bank.popup.active}">
                <div class="vertical-center-container">
                    <div class="center-content">
                        <div class="popup">
                            <div class="body">
                                <div class="center-spinner-block" ng-class="{'loading' : verification.vars.bank.popup.loading}">
                                    <div class="vertical-center-container">
                                        <div class="center-content">
                                            <span class="yt-spinner center"></span>
                                        </div>
                                    </div>
                                </div>
                                <div class="container">
                                    <div class="row">
                                        <div class="ques">
                                            <spring:message code="yt.partner.accountverification.popup.confirm.bank"/>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="ctrls">
                                            <ul class="clearfix">
                                                <li>
                                                    <button class="yt-btn red-btn" ng-click="verification.funcs.smbank()"><spring:message code="yt.partner.accountverification.common.continue.txt"/></button>
                                                </li>
                                                <li>
                                                    <button class="yt-btn grey-btn" ng-click="POPUP.closePopup(verification.vars.bank.popup)"><spring:message code="yt.partner.accountverification.common.cancel.txt"/></button>
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
            <%@include file="../common/header.jsp" %>
        </div>
    </body>
</html>
