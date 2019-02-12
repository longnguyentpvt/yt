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
        <script src="${requestScope.jsURL}partner-account-profile.js"></script>

        <link rel="stylesheet" href="${requestScope.cssURL}partner-account-setting.css" >
        <link rel="stylesheet" href="${requestScope.cssURL}partner-account-profile.css" >

        <title>Account Setting - Youtripper</title>
    </head>
    <body>
        <div id="padding-header"  ng-controller="PartnerAccountProfile" ng-cloak ng-init="hes = 'profile'">
            <%@include file="AccountSettingMenu.jsp" %>

            <div id="account-body">
                <div class="clearfix">
                    <div class="yt-normal-container">
                        <div class="body-l">
                            <%@include file="PartnerAccountCommon.jsp" %>
                        </div>
                        <div class="body-r">
                            <div id="account-content">
                                <div class="in-txt">
                                    <spring:message code="yt.partner.accountprofile.vericationnoti.txt"/>
                                </div>

                                <div class="secs">
                                    <ul>
                                        <li>
                                            <div class="aSec">
                                                <div class="sec-tt">
                                                    <spring:message code="yt.partner.accountprofile.personal.header"/>
                                                </div>
                                                <div class="sec-bd">
                                                    <div class="center-spinner-block loading" ng-show="profile.vars.psn.loading">
                                                        <div class="vertical-center-container">
                                                            <div class="center-content">
                                                                <span class="yt-spinner center"></span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="ins" ng-class="{
                                                            'editing':profile.vars.psn.eding}">
                                                        <ul>
                                                            <li>
                                                                <div class="ain">
                                                                    <div class="lb">
                                                                        <spring:message code="yt.partner.accountprofile.personal.firstname.label"/>
                                                                    </div>
                                                                    <div class="val">
                                                                        <div class="txt">
                                                                            {{profile.vars.psn.fn}}
                                                                        </div>
                                                                        <div class="val-i status-input" 
                                                                             ng-class="{'err':!profile.vars.psn.fnsc}">
                                                                            <input type="text" ng-model="profile.vars.psn.fn" ytmaxlength="30"
                                                                                   ng-change="profile.funcs.cfn()"
                                                                                   placeholder="<spring:message code="yt.partner.accountprofile.personal.firstname.input"/>" class="yt-input"/>
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
                                                                <div class="ain">
                                                                    <div class="lb">
                                                                        <spring:message code="yt.partner.accountprofile.personal.lastname.label"/>
                                                                    </div>
                                                                    <div class="val">
                                                                        <div class="txt">
                                                                            {{profile.vars.psn.ln}}
                                                                        </div>
                                                                        <div class="val-i status-input" ng-class="{
                                                                                'err':!profile.vars.psn.lnsc}">
                                                                            <input type="text" ng-model="profile.vars.psn.ln" ytmaxlength="30"
                                                                                   ng-change="profile.funcs.cln()"
                                                                                   placeholder="<spring:message code="yt.partner.accountprofile.personal.lastname.input"/>" class="yt-input"/>
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
                                                                <div class="ain">
                                                                    <div class="lb">
                                                                        <spring:message code="yt.partner.accountprofile.personal.position.label"/>
                                                                    </div>
                                                                    <div class="val">
                                                                        <div class="txt">
                                                                            <span ng-if="profile.vars.psn.pos === profile.vars.data.job.bo"> 
                                                                                <spring:message code="yt.becomePartner.registration.input.posBusinessOnwer"/>
                                                                            </span> 
                                                                            <span ng-if="profile.vars.psn.pos === profile.vars.data.job.sm"> 
                                                                                <spring:message code="yt.becomePartner.registration.input.posSaleManager"/>
                                                                            </span> 
                                                                            <span ng-if="profile.vars.psn.pos === profile.vars.data.job.mm"> 
                                                                                <spring:message code="yt.becomePartner.registration.input.posMarketingManager"/>
                                                                            </span> 
                                                                            <span ng-if="profile.vars.psn.pos === profile.vars.data.job.gm"> 
                                                                                <spring:message code="yt.becomePartner.registration.input.posGeneralManager"/>
                                                                            </span> 
                                                                            <span ng-if="profile.vars.psn.pos === profile.vars.data.job.go"> 
                                                                                <spring:message code="yt.becomePartner.registration.input.posGeneralOfficer"/>
                                                                            </span> 
                                                                            <span ng-if="profile.vars.psn.pos === profile.vars.data.job.o"> 
                                                                                <spring:message code="yt.becomePartner.registration.input.posOthers"/>
                                                                            </span> 
                                                                        </div>
                                                                        <div class="val-i">
                                                                            <div class="ddl-input" ng-class="{'nov' : profile.vars.psn.pos === null}">
                                                                                <select class="yt-input"
                                                                                        ng-model="profile.vars.psn.pos" required>
                                                                                    <option ng-value="null" disabled>
                                                                                        <spring:message code="yt.becomePartner.registration.input.position"/>
                                                                                    </option>
                                                                                    <option ng-value="profile.vars.data.job.bo">
                                                                                        <spring:message code="yt.becomePartner.registration.input.posBusinessOnwer"/>
                                                                                    </option>
                                                                                    <option ng-value="profile.vars.data.job.sm">
                                                                                        <spring:message code="yt.becomePartner.registration.input.posSaleManager"/>
                                                                                    </option>
                                                                                    <option ng-value="profile.vars.data.job.mm">
                                                                                        <spring:message code="yt.becomePartner.registration.input.posMarketingManager"/>
                                                                                    </option>
                                                                                    <option ng-value="profile.vars.data.job.gm">
                                                                                        <spring:message code="yt.becomePartner.registration.input.posGeneralManager"/>
                                                                                    </option>
                                                                                    <option ng-value="profile.vars.data.job.go">
                                                                                        <spring:message code="yt.becomePartner.registration.input.posGeneralOfficer"/>
                                                                                    </option>
                                                                                    <option ng-value="profile.vars.data.job.o">
                                                                                        <spring:message code="yt.becomePartner.registration.input.posOthers"/>
                                                                                    </option>
                                                                                </select>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </li>
                                                            <li>
                                                                <div class="ain">
                                                                    <div class="lb">
                                                                        &nbsp;
                                                                    </div>
                                                                    <div class="val">
                                                                        <div class="btns">
                                                                            <ul>
                                                                                <li>
                                                                                    <button class="yt-btn"
                                                                                            ng-click="profile.funcs.edpsn()"
                                                                                            ><spring:message code="yt.partner.accountprofile.personal.editbtn"/></button>
                                                                                </li><li class="edit-btn">
                                                                                    <button class="yt-btn red-btn" ng-click="profile.funcs.svpsn()"><spring:message code="yt.partner.accountprofile.personal.savebtn"/></button>
                                                                                </li><li class="edit-btn">
                                                                                    <button class="yt-btn grey-btn" ng-click="profile.funcs.ccpsn()"><spring:message code="yt.partner.accountprofile.personal.cancelbtn"/></button>
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
                                        </li>

                                        <li>
                                            <div class="aSec">
                                                <div class="sec-tt">
                                                    <spring:message code="yt.partner.accountprofile.personal.accountInfo.label"/>
                                                </div>
                                                <div class="sec-bd">
                                                    <div class="center-spinner-block loading" ng-show="profile.vars.ai.loading">
                                                        <div class="vertical-center-container">
                                                            <div class="center-content">
                                                                <span class="yt-spinner center"></span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="ins" ng-class="{
                                                            'editing':profile.vars.ai.pwe}">
                                                        <ul>
                                                            <li>
                                                                <div class="ain">
                                                                    <div class="lb">
                                                                        <spring:message code="yt.partner.accountprofile.personal.email.label"/>
                                                                    </div>
                                                                    <div class="val">
                                                                        <div class="txt fixed">
                                                                            {{profile.vars.psn.em}}
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </li>
                                                            <li>
                                                                <div class="ain">
                                                                    <div class="lb">
                                                                        <spring:message code="yt.partner.accountprofile.personal.password.label"/>
                                                                    </div>
                                                                    <div class="val">
                                                                        <div class="txt">
                                                                            *********
                                                                        </div>
                                                                        <div class="val-i status-input" ng-class="{
                                                                                'err':profile.vars.ai.crpwsc}">
                                                                            <input type="password" 
                                                                                   ng-model="profile.vars.ai.crpw"
                                                                                   ng-change="profile.funcs.ccpw()"
                                                                                   placeholder="<spring:message code="yt.partner.accountprofile.personal.currentpw.input"/>" class="yt-input"/>
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

                                                                            <span class='error-msg' ng-show="profile.vars.ai.crpwnc">
                                                                                <spring:message code="yt.partner.accountprofile.personal.currentPassword.error"/>
                                                                            </span>
                                                                            <span class='error-msg' ng-show="profile.vars.ai.cper">
                                                                                <spring:message code="yt.becomePartner.registration.error.password.invalidFormat"/>
                                                                            </span>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </li>
                                                            <li class='edit-row'>
                                                                <div class="ain">
                                                                    <div class="lb">
                                                                        &nbsp;
                                                                    </div>
                                                                    <div class="val">
                                                                        <div class="val-i status-input" ng-class="{
                                                                                'err':profile.vars.ai.npwsc}">
                                                                            <input type="password" 
                                                                                   minlength="8" ng-minlength="8"  ytmaxlength="32" ng-maxlength="32"
                                                                                   ng-change="profile.funcs.cnpw()"
                                                                                   ng-model="profile.vars.ai.npw"
                                                                                   placeholder="<spring:message code="yt.partner.accountprofile.personal.newpw.input"/>" class="yt-input"/>
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
                                                                            <span class='error-msg' ng-show="profile.vars.ai.npwsc">
                                                                                <spring:message code="yt.becomePartner.registration.error.password.missing"/>
                                                                            </span>
                                                                            <span class='error-msg' ng-show="profile.vars.ai.nper">
                                                                                <spring:message code="yt.becomePartner.registration.error.password.invalidFormat"/>
                                                                            </span>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </li>
                                                            <li class='edit-row'>
                                                                <div class="ain">
                                                                    <div class="lb">
                                                                        &nbsp;
                                                                    </div>
                                                                    <div class="val">
                                                                        <div class="val-i status-input" ng-class="{
                                                                                'err':profile.vars.ai.cfpwsc}">
                                                                            <input type="password" 
                                                                                   ytmaxlength="32" ng-maxlength="32"
                                                                                   ng-model="profile.vars.ai.cfpw" 
                                                                                   ng-change="profile.funcs.ccfpw()"
                                                                                   placeholder="<spring:message code="yt.partner.accountprofile.personal.confirmpw.input"/>" class="yt-input"/>
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
                                                                            <span class='error-msg' ng-show="profile.vars.ai.cfpwsc">
                                                                                <spring:message code="yt.becomePartner.registration.error.password.confirm.notMatch"/>
                                                                            </span>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </li>
                                                            <li>
                                                                <div class="ain">
                                                                    <div class="lb">
                                                                        &nbsp;
                                                                    </div>
                                                                    <div class="val">
                                                                        <div class="btns">
                                                                            <ul>
                                                                                <li>
                                                                                    <button class="yt-btn"
                                                                                            ng-click="profile.funcs.edai()"
                                                                                            ><spring:message code="yt.partner.accountprofile.personal.editbtn"/></button>
                                                                                </li><li class="edit-btn">
                                                                                    <button class="yt-btn red-btn"
                                                                                            ng-click="profile.funcs.svai()"
                                                                                            ><spring:message code="yt.partner.accountprofile.personal.savebtn"/></button>
                                                                                </li><li class="edit-btn">
                                                                                    <button class="yt-btn grey-btn" ng-click="profile.funcs.cai()"><spring:message code="yt.partner.accountprofile.personal.cancelbtn"/></button>
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
                                        </li>

                                        <li>
                                            <div class="aSec">
                                                <div class="sec-tt">
                                                    <spring:message code="yt.partner.accountprofile.business.header"/>
                                                </div>
                                                <div class="sec-bd">
                                                    <div class="center-spinner-block loading" ng-show="profile.vars.bs.loading">
                                                        <div class="vertical-center-container">
                                                            <div class="center-content">
                                                                <span class="yt-spinner center"></span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="ins" ng-class="{'editing':profile.vars.bs.eding}">
                                                        <ul>
                                                            <li>
                                                                <div class="ain">
                                                                    <div class="lb">
                                                                        <spring:message code="yt.partner.accountprofile.business.compname.label"/>
                                                                    </div>
                                                                    <div class="val">
                                                                        <div class="txt fixed">
                                                                            {{profile.vars.bs.comp}}
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </li>
                                                            <li>
                                                                <div class="ain">
                                                                    <div class="lb">
                                                                        <spring:message code="yt.partner.accountprofile.business.businesstype.label"/>
                                                                    </div>
                                                                    <div class="val">
                                                                        <div class="txt fixed">
                                                                            {{profile.vars.bs.ty === profile.vars.data.partnerType.i ? "<spring:message code="yt.partner.accountprofile.business.option.personal"/>" : 
                                                                            "<spring:message code="yt.partner.accountprofile.business.option.company"/>"}}
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </li>
                                                            <li>
                                                                <div class="ain">
                                                                    <div class="lb">
                                                                        <spring:message code="yt.partner.accountprofile.business.country.label"/>
                                                                    </div>
                                                                    <div class="val">
                                                                        <div class="txt">
                                                                            {{profile.vars.bs.cn}}
                                                                        </div>
                                                                        <div class="val-i status-input" ng-class="{'err':profile.vars.bs.eCon}" >
                                                                            <div class="ddl-input" ng-class="{'nov' : profile.vars.bs.con === null}">
                                                                                <select class="yt-input" ng-model="profile.vars.bs.con"
                                                                                        ng-change="profile.funcs.countryChange()"
                                                                                        >
                                                                                    <option disabled ng-value="null">
                                                                                        <spring:message code="yt.tripper.accountprofile.personal.country.input"/>
                                                                                    </option>
                                                                                    <option ng-value="aCountry.countryID"
                                                                                            ng-repeat="aCountry in profile.vars.data.countries">
                                                                                        {{aCountry.countryName}}
                                                                                    </option>
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
                                                                </div>
                                                            </li>
                                                            <li>
                                                                <div class="ain">
                                                                    <div class="lb">
                                                                        <spring:message code="yt.partner.accountprofile.business.state.label"/>
                                                                    </div>
                                                                    <div class="val">
                                                                        <div class="txt">
                                                                            {{profile.vars.bs.sn}} 
                                                                        </div>
                                                                        <div class="val-i status-input" ng-class="{'err':profile.vars.bs.eSta}">
                                                                            <div class="ddl-input" ng-class="{'nov' : profile.vars.bs.sta === null}">
                                                                                <select class="yt-input" ng-model="profile.vars.bs.sta" 
                                                                                        ng-change="profile.funcs.csta()" >
                                                                                    <option ng-value="null" disabled>
                                                                                        <spring:message code="yt.becomePartner.registration.input.state"/>
                                                                                    </option>
                                                                                    <option ng-value="aCity.stateID"
                                                                                            ng-repeat="aCity in profile.vars.data.cities">
                                                                                        {{aCity.stateName}}
                                                                                    </option>
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
                                                                </div>
                                                            </li>
                                                            <li>
                                                                <div class="ain">
                                                                    <div class="lb">
                                                                        <spring:message code="yt.partner.accountprofile.business.address.label"/>
                                                                    </div>
                                                                    <div class="val">
                                                                        <div class="txt">
                                                                            {{profile.vars.bs.addr}}
                                                                        </div>
                                                                        <div class="val-i status-input" ng-class="{'err':profile.vars.bs.eAddr}">
                                                                            <input type='text' ng-model="profile.vars.bs.addr" 
                                                                                   ytmaxlength="100"
                                                                                   ng-change="profile.funcs.caddr()"
                                                                                   class='yt-input' placeholder="<spring:message code="yt.partner.accountprofile.business.address.input"/>"/>
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
                                                                <div class="ain">
                                                                    <div class="lb">
                                                                        <spring:message code="yt.partner.accountprofile.business.city.label"/>
                                                                    </div>
                                                                    <div class="val">
                                                                        <div class="txt">
                                                                            {{profile.vars.bs.ct}}
                                                                        </div>
                                                                        <div class="val-i status-input" ng-class="{
                                                                                'err':profile.vars.bs.eCt}" >
                                                                            <input type='text' ng-model="profile.vars.bs.ct" 
                                                                                   ytmaxlength="50"
                                                                                   ng-change="profile.funcs.cct()"
                                                                                   class='yt-input' placeholder="<spring:message code="yt.partner.accountprofile.business.city.input"/>"/>

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
                                                                <div class="ain">
                                                                    <div class="lb">
                                                                        <spring:message code="yt.partner.accountprofile.business.postal.label"/>
                                                                    </div>
                                                                    <div class="val">
                                                                        <div class="txt">
                                                                            {{profile.vars.bs.zip}}
                                                                        </div>
                                                                        <div class="val-i status-input" ng-class="{
                                                                                'err':profile.vars.bs.eZip}">
                                                                            <input type='text' 
                                                                                   ng-model="profile.vars.bs.zip"
                                                                                   ytmaxlength ="10"
                                                                                   ng-change="profile.funcs.czip()"
                                                                                   class='yt-input' placeholder="<spring:message code="yt.partner.accountprofile.business.postal.input"/>"/>
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
                                                                <div class="ain">
                                                                    <div class="lb">
                                                                        <spring:message code="yt.partner.accountprofile.business.phone.label"/>
                                                                    </div>
                                                                    <div class="val">
                                                                        <div class="txt">
                                                                            +({{profile.vars.bs.pc}}) {{profile.vars.bs.pn}}
                                                                        </div>
                                                                        <div class="val-i">
                                                                            <div class='phone-i'>
                                                                                <ul>
                                                                                    <li class='phone-c'>
                                                                                        <div class="ddl-input">
                                                                                            <select class="yt-input" ng-model="profile.vars.bs.pc">
                                                                                                <option ng-value="item" 
                                                                                                        ng-repeat="item in profile.vars.data.phoneCodes">
                                                                                                    +{{item}}
                                                                                                </option>
                                                                                            </select>
                                                                                        </div>
                                                                                    </li>
                                                                                    <li class='phone-n'>
                                                                                        <div class="status-input" ng-class="{
                                                                                                'err':profile.vars.bs.ePn}">
                                                                                            <input type='text' class='yt-input' 
                                                                                                   ng-model="profile.vars.bs.pn" number-str-input
                                                                                                   ytmaxlength="20"
                                                                                                   ng-change="profile.funcs.cpn()"
                                                                                                   placeholder="<spring:message code="yt.partner.accountprofile.business.phone.input"/>"/>
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
                                                                                    </li>
                                                                                </ul>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </li>
                                                            <li>
                                                                <div class="ain">
                                                                    <div class="lb">
                                                                        <spring:message code="yt.partner.accountprofile.business.about.label"/>
                                                                    </div>
                                                                    <div class="val">
                                                                        <div class="txt">
                                                                            <pre> {{profile.vars.bs.ab.length?profile.vars.bs.ab:"N/A"}}</pre>
                                                                        </div>
                                                                        <div class="val-i">
                                                                            <textarea class='yt-input' 
                                                                                      ytmaxlength="4000"
                                                                                      ng-model="profile.vars.bs.ab"
                                                                                      placeholder="<spring:message code="yt.partner.accountprofile.business.about.input"/>"></textarea>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </li>
                                                            <li>
                                                                <div class="ain">
                                                                    <div class="lb">
                                                                        &nbsp;
                                                                    </div>
                                                                    <div class="val">
                                                                        <div class="btns">
                                                                            <ul>
                                                                                <li>
                                                                                    <button class="yt-btn"  ng-click="profile.funcs.edbs()"><spring:message code="yt.partner.accountprofile.personal.editbtn"/></button>
                                                                                </li><li class="edit-btn">
                                                                                    <button class="yt-btn red-btn" 
                                                                                            ng-click="profile.funcs.svbs()"
                                                                                            ><spring:message code="yt.partner.accountprofile.personal.savebtn"/></button>
                                                                                </li><li class="edit-btn">
                                                                                    <button class="yt-btn grey-btn"
                                                                                            ng-click="profile.funcs.ccbs()"
                                                                                            ><spring:message code="yt.partner.accountprofile.personal.cancelbtn"/></button>
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
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <%@include file="../common/header.jsp" %>
        </div>
        <script>
            var responseData = ${requestScope.commonData};
        </script>
    </body>
</html>
