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
        <script src="${requestScope.jsURL}tripper-account-profile.js"></script>

        <link rel="stylesheet" href="${requestScope.cssURL}tripper-account-setting.css" >
        <link rel="stylesheet" href="${requestScope.cssURL}tripper-account-profile.css" >

        <title><spring:message code="yt.tripper.accountprofile.browser.header"/></title>
    </head>
    <body id="padding-header" ng-cloak ng-init="hes = 'profile'">
        <%@include file="AccountSettingMenu.jsp" %>

        <div id="account-body" ng-controller="TripperAccountProfile">
            <div class="clearfix">
                <div class="yt-normal-container">
                    <div class="body-l">
                        <%@include file="TripperAccountCommon.jsp" %>
                    </div>
                    <div class="body-r">
                        <div id="account-content">
                            <div class="secs">
                                <ul>
                                    <li>
                                        <div class="aSec">
                                            <div class="sec-tt">
                                                <spring:message code="yt.tripper.accountprofile.personal.header"/>
                                            </div>
                                            <div class="sec-bd">
                                                <div class="center-spinner-block loading" ng-show="profile.vars.psn.lding">
                                                    <div class="vertical-center-container">
                                                        <div class="center-content">
                                                            <span class="yt-spinner center"></span>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="ins " ng-class="{'editing':profile.vars.psn.eding}">
                                                    <ul>
                                                        <li>
                                                            <div class="ain">
                                                                <div class="lb">
                                                                    <spring:message code="yt.tripper.accountprofile.personal.firstname.label"/>
                                                                </div>
                                                                <div class="val">
                                                                    <div class="txt" >
                                                                        {{profile.vars.psn.fn.length?profile.vars.psn.fn:"N/A"}}
                                                                    </div>
                                                                    <div class="val-i status-input"  ng-class="{
                                                                            'err':profile.vars.psn.eFn}">
                                                                        <input ng-model="profile.vars.psn.fn" ytmaxlength="30" 
                                                                               ng-change="profile.funcs.cfn()"
                                                                               type="text" placeholder=" <spring:message code="yt.tripper.accountprofile.personal.firstname.input"/>" class="yt-input"/>
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
                                                                    <spring:message code="yt.tripper.accountprofile.personal.lastname.label"/>
                                                                </div>
                                                                <div class="val">
                                                                    <div class="txt" >
                                                                        {{profile.vars.psn.ln.length? profile.vars.psn.ln : "N/A"}}
                                                                    </div>
                                                                    <div class="val-i status-input"  ng-class="{
                                                                            'err':profile.vars.psn.eLn}">
                                                                        <input ng-model="profile.vars.psn.ln" ytmaxlength="30" 
                                                                               ng-change="profile.funcs.cln()"
                                                                               type="text" placeholder=" <spring:message code="yt.tripper.accountprofile.personal.lastname.input"/>" class="yt-input"/>
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
                                                                    <spring:message code="yt.tripper.accountprofile.personal.displayName.label"/>
                                                                </div>
                                                                <div class="val">
                                                                    <div class="txt" >
                                                                        {{profile.vars.psn.dn.length? profile.vars.psn.dn : "N/A"}}
                                                                    </div>
                                                                    <div class="val-i status-input"  ng-class="{
                                                                            'err':profile.vars.psn.eDn}">
                                                                        <input ng-model="profile.vars.psn.dn" ytmaxlength="30" 
                                                                               ng-change="profile.funcs.cdn()"
                                                                               type="text" placeholder=" <spring:message code="yt.tripper.accountprofile.personal.displayName.input"/>" class="yt-input"/>
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
                                                                    <spring:message code="yt.tripper.accountprofile.personal.gender.label"/>
                                                                </div>
                                                                <div class="val">
                                                                    <div class="txt">
                                                                        {{profile.vars.psn.gd === null?"N/A": (profile.vars.psn.gd? "<spring:message code="yt.tripper.accountprofile.personal.gender.male"/>" : "<spring:message code="yt.tripper.accountprofile.personal.gender.female"/>")}}
                                                                    </div>
                                                                    <div class="val-i">
                                                                        <div class="ddl-input status-input" ng-class="{
                                                                                'err':profile.vars.psn.eGd,'nov':profile.vars.psn.gd === null}">
                                                                            <select class="yt-input" ng-model="profile.vars.psn.gd"
                                                                                    ng-change="profile.funcs.cgd()"
                                                                                    >
                                                                                <option ng-value="null" disabled>
                                                                                    <spring:message code="yt.tripper.accountprofile.personal.gender.select"/>
                                                                                </option>
                                                                                <option ng-value="true">
                                                                                    <spring:message code="yt.tripper.accountprofile.personal.gender.male"/>
                                                                                </option>
                                                                                <option ng-value="false">
                                                                                    <spring:message code="yt.tripper.accountprofile.personal.gender.female"/>
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
                                                                    <spring:message code="yt.tripper.accountprofile.personal.country.label"/>
                                                                </div>
                                                                <div class="val">
                                                                    <div class="txt">
                                                                        {{profile.vars.psn.cn.length?profile.vars.psn.cn:"N/A"}}
                                                                    </div>
                                                                    <div class="val-i status-input" ng-class="{
                                                                            'err':profile.vars.psn.eCon}">
                                                                        <div class="ddl-input" ng-class="{'nov':profile.vars.psn.con === null}">
                                                                            <select class="yt-input" ng-model="profile.vars.psn.con"
                                                                                    ng-change="profile.funcs.countryChange()">
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
                                                                    <spring:message code="yt.tripper.accountprofile.personal.state.label"/>
                                                                </div>
                                                                <div class="val">
                                                                    <div class="txt">
                                                                        {{profile.vars.psn.sn.length?profile.vars.psn.sn:"N/A"}}
                                                                    </div>
                                                                    <div class="val-i status-input" ng-class="{
                                                                            'err':profile.vars.psn.eSta}">
                                                                        <div class="ddl-input" ng-class="{'nov':profile.vars.psn.sta === null}">
                                                                            <select class="yt-input" ng-model="profile.vars.psn.sta"
                                                                                    ng-change="profile.funcs.cSta()" >
                                                                                <option ng-value="null" disabled="">
                                                                                    <spring:message code="yt.tripper.accountprofile.personal.state.input"/>
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
                                                                    <spring:message code="yt.tripper.accountprofile.personal.address.label"/>
                                                                </div>
                                                                <div class="val">
                                                                    <div class="txt">
                                                                        {{profile.vars.psn.addr.length?profile.vars.psn.addr:"N/A"}}
                                                                    </div>
                                                                    <div class="val-i status-input" ng-class="{
                                                                            'err':profile.vars.psn.eAddr}">
                                                                        <input type="text" 
                                                                               ng-model="profile.vars.psn.addr" 
                                                                               ng-change="profile.funcs.caddr()"
                                                                               ytmaxlength="100"
                                                                               placeholder="<spring:message code="yt.tripper.accountprofile.personal.address.input"/>" class="yt-input"/>
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
                                                                    <spring:message code="yt.tripper.accountprofile.personal.city.label"/>
                                                                </div>
                                                                <div class="val status-input" ng-class="{
                                                                        'err':profile.vars.psn.eCt}">
                                                                    <div class="txt">
                                                                        {{profile.vars.psn.ct.length?profile.vars.psn.ct:"N/A"}}
                                                                    </div>
                                                                    <div class="val-i">
                                                                        <input  ng-model="profile.vars.psn.ct" 
                                                                                ytmaxlength="50"
                                                                                ng-change="profile.funcs.cct()"
                                                                                type="text" placeholder="<spring:message code="yt.tripper.accountprofile.personal.city.input"/>" class="yt-input"/>
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
                                                                    <spring:message code="yt.tripper.accountprofile.personal.postal.label"/>
                                                                </div>
                                                                <div class="val">
                                                                    <div class="txt">
                                                                        {{profile.vars.psn.zip.length?profile.vars.psn.zip:"N/A"}}
                                                                    </div>
                                                                    <div class="val-i status-input" ng-class="{
                                                                            'err':profile.vars.psn.eZip}">
                                                                        <input type="text" ng-model="profile.vars.psn.zip" 
                                                                               ytmaxlength="10" 
                                                                               ng-change="profile.funcs.czip()"
                                                                               placeholder="<spring:message code="yt.tripper.accountprofile.personal.postal.input"/>" class="yt-input"/>
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
                                                                    <spring:message code="yt.tripper.accountprofile.personal.phone.label"/>
                                                                </div>
                                                                <div class="val">
                                                                    <div class="txt">
                                                                        {{profile.vars.psn.pn.length?"+(" + profile.vars.psn.pc + ") " + profile.vars.psn.pn:"N/A"}}   
                                                                    </div>
                                                                    <div class="val-i">
                                                                        <div class='phone-i'>
                                                                            <ul>
                                                                                <li class='phone-c'>
                                                                                    <div class="ddl-input">
                                                                                        <select class="yt-input" ng-model="profile.vars.psn.pc">
                                                                                            <option ng-value="item" 
                                                                                                    ng-repeat="item in profile.vars.data.phoneCodes">
                                                                                                +{{item}}
                                                                                            </option>
                                                                                        </select>
                                                                                    </div>
                                                                                </li>
                                                                                <li class='phone-n'>
                                                                                    <div class="status-input" ng-class="{
                                                                                            'err':profile.vars.psn.ePn}">
                                                                                        <input type='text'
                                                                                               ng-model="profile.vars.psn.pn" number-str-input
                                                                                               ytmaxlength="20"
                                                                                               ng-change="profile.funcs.cpn()"
                                                                                               class='yt-input' placeholder="<spring:message code="yt.tripper.accountprofile.personal.phone.input"/>"/>
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
                                                                    <spring:message code="yt.tripper.accountprofile.personal.company.label"/>
                                                                </div>
                                                                <div class="val">
                                                                    <div class="txt">
                                                                        {{profile.vars.psn.comp.length?profile.vars.psn.comp:"N/A"}}
                                                                    </div>
                                                                    <div class="val-i status-input" ng-class="{
                                                                            'err':profile.vars.psn.eComp}"  >
                                                                        <input ng-model="profile.vars.psn.comp" 
                                                                               ytmaxlength="100"
                                                                               ng-change="profile.funcs.ccomp()"
                                                                               type="text" placeholder=" <spring:message code="yt.tripper.accountprofile.personal.company.input"/>" class="yt-input"/>
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
                                                                    <spring:message code="yt.tripper.accountprofile.personal.tax.label"/>
                                                                </div>
                                                                <div class="val">
                                                                    <div class="txt">
                                                                        {{profile.vars.psn.tax.length?profile.vars.psn.tax:"N/A"}}
                                                                    </div>
                                                                    <div class="val-i status-input" ng-class="{
                                                                            'err':profile.vars.psn.eTax}">
                                                                        <input ytmaxlength="15" 
                                                                               ng-model="profile.vars.psn.tax"
                                                                               ng-change="profile.funcs.ctax()"
                                                                               number-str-input
                                                                               type="text" placeholder=" <spring:message code="yt.tripper.accountprofile.personal.tax.input"/>" class="yt-input"/>
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
                                                                    &nbsp;
                                                                </div>
                                                                <div class="val">
                                                                    <div class="btns">
                                                                        <ul>
                                                                            <li>
                                                                                <button class="yt-btn" ng-click="profile.funcs.edpsn()"><spring:message code="yt.tripper.accountprofile.ai.editbtn"/></button>
                                                                            </li><li class="edit-btn">
                                                                                <button class="yt-btn red-btn" ng-click="profile.funcs.svpsn()"><spring:message code="yt.tripper.accountprofile.ai.savebtn"/></button>
                                                                            </li><li class="edit-btn">
                                                                                <button class="yt-btn grey-btn" ng-click="profile.funcs.ccpsn()"><spring:message code="yt.tripper.accountprofile.ai.cancelbtn"/></button>
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
                                                <spring:message code="yt.tripper.accountprofile.ai.accountInfo.label"/>
                                            </div>
                                            <div class="sec-bd">
                                                <div class="center-spinner-block loading" ng-show="profile.vars.ai.lding">
                                                    <div class="vertical-center-container">
                                                        <div class="center-content">
                                                            <span class="yt-spinner center"></span>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="ins" ng-class="{
                                                        'editing':profile.vars.ai.pwe}" ng-if="profile.vars.psn.act === 'yt'">
                                                    <ul>
                                                        <li>
                                                            <div class="ain">
                                                                <div class="lb">
                                                                    <spring:message code="yt.tripper.accountprofile.ai.email.label"/>
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
                                                                    <spring:message code="yt.tripper.accountprofile.ai.password.label"/>
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
                                                                               placeholder="<spring:message code="yt.tripper.accountprofile.ai.currentpw.input"/>" class="yt-input"/>
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
                                                                               ng-model="profile.vars.ai.npw"
                                                                               ng-change="profile.funcs.cnpw()"
                                                                               placeholder="<spring:message code="yt.tripper.accountprofile.ai.newpw.input"/>" class="yt-input"/>
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
                                                                               placeholder="<spring:message code="yt.tripper.accountprofile.ai.confirmpw.input"/>" class="yt-input"/>
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
                                                                                <button class="yt-btn" ng-click="profile.funcs.edai()"><spring:message code="yt.tripper.accountprofile.ai.editbtn"/></button>
                                                                            </li><li class="edit-btn">
                                                                                <button class="yt-btn red-btn" ng-click="profile.funcs.svai()"><spring:message code="yt.tripper.accountprofile.ai.savebtn"/></button>
                                                                            </li><li class="edit-btn">
                                                                                <button class="yt-btn grey-btn" ng-click="profile.funcs.cai()"><spring:message code="yt.tripper.accountprofile.ai.cancelbtn"/></button>
                                                                            </li>
                                                                        </ul>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </li>
                                                    </ul>
                                                </div>
                                                <!--social account information-->
                                                <div class="ins" ng-class="{
                                                        'editing':profile.vars.ai.eE}" ng-if="profile.vars.psn.act !== 'yt'">
                                                    <ul>
                                                        <li>
                                                            <div class="ain">
                                                                <div class="lb">
                                                                    <spring:message code="yt.tripper.accountprofile.ai.email.label"/>
                                                                </div>
                                                                <div class="val">
                                                                    <div class="txt fixed">
                                                                        {{profile.vars.psn.em}}
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
                                                                            'err':profile.vars.ai.nemE}">
                                                                        <input type="text" 
                                                                               ytmaxlength="255" ng-maxlength="255"
                                                                               ng-model="profile.vars.ai.nem"
                                                                               ng-change="profile.funcs.cnem()"
                                                                               placeholder="<spring:message code="yt.tripper.accountprofile.ai.newem.input"/>" class="yt-input"/>
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
                                                                        <span class='error-msg' ng-show="profile.vars.ai.nemE">
                                                                            <spring:message code="yt.tripper.accountprofile.ai.newem.blank"/>
                                                                        </span>
                                                                        <span class='error-msg' ng-show="profile.vars.ai.nemFE">
                                                                            <spring:message code="yt.tripper.accountprofile.ai.newem.error"/>
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
                                                                            'err':profile.vars.ai.cfemE}">
                                                                        <input type="text" 
                                                                               ytmaxlength="255" ng-maxlength="255"
                                                                               ng-model="profile.vars.ai.cfem" 
                                                                               ng-change="profile.funcs.ccfem()"
                                                                               placeholder="<spring:message code="yt.tripper.accountprofile.ai.confirmem.input"/>" class="yt-input"/>
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
                                                                        <span class='error-msg' ng-show="profile.vars.ai.cfemE">
                                                                            <spring:message code="yt.tripper.accountprofile.ai.confirmem.error"/>
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
                                                                                <button class="yt-btn" ng-click="profile.funcs.edemai()"><spring:message code="yt.tripper.accountprofile.ai.editbtn"/></button>
                                                                            </li><li class="edit-btn">
                                                                                <button class="yt-btn red-btn" ng-click="profile.funcs.svemai()"><spring:message code="yt.tripper.accountprofile.ai.savebtn"/></button>
                                                                            </li><li class="edit-btn">
                                                                                <button class="yt-btn grey-btn" ng-click="profile.funcs.cemai()"><spring:message code="yt.tripper.accountprofile.ai.cancelbtn"/></button>
                                                                            </li>
                                                                        </ul>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </li>
                                                    </ul>
                                                </div>
                                                <!--end account information-->
                                            </div>
                                        </div>
                                    </li>

                                    <li>
                                        <div class="aSec">
                                            <div class="sec-tt">
                                                <spring:message code="yt.tripper.accountprofile.payment.header"/>
                                            </div>
                                            <div class="sec-bd">
                                                <div class="center-spinner-block loading" ng-show="profile.vars.cdc.lding">
                                                    <div class="vertical-center-container">
                                                        <div class="center-content">
                                                            <span class="yt-spinner center"></span>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="ins">
                                                    <ul>
                                                        <li ng-repeat="card in profile.vars.cdc.cards">
                                                            <div class="ain">
                                                                <div class="lb">
                                                                    {{card.holderName}}
                                                                </div>  
                                                                <div class="val">
                                                                    <div class="txt">
                                                                        <div class="aCard">
                                                                            <div class="no">
                                                                                {{card.sn}}
                                                                            </div>
                                                                            <div class="ed">
                                                                                <spring:message code="yt.tripper.accountprofile.payment.expire"/> - {{card.ed}}
                                                                            </div>
                                                                            <span class="close-btn" ng-click="profile.funcs.odcd(card.cardID)"></span>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>  
                                                        </li>
                                                        <li ng-if="!profile.vars.cdc.cards.length">
                                                            <div class="ain">

                                                                <div class="val">
                                                                    <div class="txt">
                                                                        <div class="aCard">
                                                                            <div class="no">
                                                                                <spring:message code="yt.tripper.accountprofile.payment.noCard"/>
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
                                    </li>

                                    <li>
                                        <div class="aSec">
                                            <div class="sec-tt">
                                                <spring:message code="yt.tripper.accountprofile.billing.header"/>
                                            </div>
                                            <div class="sec-bd">
                                                <div class="center-spinner-block loading" ng-show="profile.vars.billing.lding">
                                                    <div class="vertical-center-container">
                                                        <div class="center-content">
                                                            <span class="yt-spinner center"></span>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="ins">
                                                    <ul>
                                                        <li ng-repeat="billing in profile.vars.billing.billings">
                                                            <div class="ain">
                                                                <div class="lb">
                                                                    {{billing.billingName}}
                                                                </div>  
                                                                <div class="val">
                                                                    <div class="txt">
                                                                        <div class="aBill">
                                                                            <span class="close-btn" ng-click="profile.funcs.odb(billing.billingID)"></span>
                                                                            <div class="no">
                                                                                {{billing.firstName}} {{billing.lastName}}
                                                                            </div>
                                                                            <div class="addr">
                                                                                {{billing.address}}
                                                                            </div>
                                                                            <div class="btn">
                                                                                <span class="link" ng-click="profile.funcs.obd(billing.billingID)"><spring:message code="yt.tripper.accountprofile.billing.detail"/></span>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>  
                                                        </li>
                                                        <li ng-if="!profile.vars.billing.billings.length">
                                                            <div class="ain">

                                                                <div class="val">
                                                                    <div class="txt">
                                                                        <div class="aCard">
                                                                            <div class="no">
                                                                                <spring:message code="yt.tripper.accountprofile.billing.noBilling"/>
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
                                    </li>
                                    <li>
                                        <div class="aSec">
                                            <div class="sec-tt">
                                                <spring:message code="yt.tripper.accountprofile.optional.header"/>
                                            </div>
                                            <div class="sec-bd">
                                                <div class="center-spinner-block loading" ng-show="profile.vars.op.lding">
                                                    <div class="vertical-center-container">
                                                        <div class="center-content">
                                                            <span class="yt-spinner center"></span>
                                                        </div>
                                                    </div>
                                                </div>

                                                <div class="ins" ng-class="{
                                                        'editing':profile.vars.op.eding}">
                                                    <ul>
                                                        <li>
                                                            <div class="ain">
                                                                <div class="lb">
                                                                    <spring:message code="yt.tripper.accountprofile.optional.preferedLanguage.label"/>
                                                                </div>
                                                                <div class="val">
                                                                    <div class="txt">
                                                                        <span>{{profile.vars.op.pl != null ? profile.vars.data.pls[profile.vars.op.pl] : "N/A"}}</span> 
                                                                    </div>
                                                                    <div class="val-i">
                                                                        <div class="ddl-input" ng-class="{'nov':profile.vars.op.pl === null}">
                                                                            <select id="selectL" class="yt-input" ng-model="profile.vars.op.pl">
                                                                                <option ng-value="null">
                                                                                    <spring:message code="yt.tripper.accountprofile.optional.preferedLanguage.select"/>
                                                                                </option>
                                                                                <option ng-init="profile.vars.data.pls['cn'] = '<spring:message code="yt.tripperprofile.language.cn"/>'" 
                                                                                        value="cn" ng-bind="profile.vars.data.pls['cn']"></option>
                                                                                <option ng-init="profile.vars.data.pls['en'] = '<spring:message code="yt.tripperprofile.language.en"/>'" 
                                                                                        value="en" ng-bind="profile.vars.data.pls['en']"></option>
                                                                                <option ng-init="profile.vars.data.pls['fr'] = '<spring:message code="yt.tripperprofile.language.fr"/>'" 
                                                                                        value="fr" ng-bind="profile.vars.data.pls['fr']"></option>
                                                                                <option ng-init="profile.vars.data.pls['de'] = '<spring:message code="yt.tripperprofile.language.de"/>'" 
                                                                                        value="de" ng-bind="profile.vars.data.pls['de']"></option>
                                                                                <option ng-init="profile.vars.data.pls['ja'] = '<spring:message code="yt.tripperprofile.language.ja"/>'" 
                                                                                        value="ja" ng-bind="profile.vars.data.pls['ja']"></option>
                                                                                <option ng-init="profile.vars.data.pls['ko'] = '<spring:message code="yt.tripperprofile.language.ko"/>'" 
                                                                                        value="ko" ng-bind="profile.vars.data.pls['ko']"></option>
                                                                                <option ng-init="profile.vars.data.pls['ru'] = '<spring:message code="yt.tripperprofile.language.ru"/>'" 
                                                                                        value="ru" ng-bind="profile.vars.data.pls['ru']"></option>
                                                                                <option ng-init="profile.vars.data.pls['es'] = '<spring:message code="yt.tripperprofile.language.es"/>'" 
                                                                                        value="es" ng-bind="profile.vars.data.pls['es']"></option>
                                                                            </select>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </li>
                                                        <li>
                                                            <div class="ain">
                                                                <div class="lb">
                                                                    <spring:message code="yt.tripper.accountprofile.optional.aboutMe.label"/>
                                                                </div>
                                                                <div class="val">
                                                                    <div class="txt">
                                                                        <pre>{{profile.vars.op.bg.length ? profile.vars.op.bg : "N/A"}}</pre>
                                                                    </div>
                                                                    <div class="val-i">
                                                                        <textarea class="yt-input" ytmaxlength="4000" placeholder=" <spring:message code="yt.tripper.accountprofile.optional.aboutMe.input"/>"
                                                                                  ng-model="profile.vars.op.bg"></textarea>
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
                                                                                <button class="yt-btn" ng-click="profile.funcs.edop()"><spring:message code="yt.tripper.accountprofile.ai.editbtn"/></button>
                                                                            </li><li class="edit-btn">
                                                                                <button class="yt-btn red-btn" ng-click="profile.funcs.svop()"><spring:message code="yt.tripper.accountprofile.ai.savebtn"/></button>
                                                                            </li><li class="edit-btn">
                                                                                <button class="yt-btn grey-btn" ng-click="profile.funcs.cop()"><spring:message code="yt.tripper.accountprofile.ai.cancelbtn"/></button>
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

            <div class="yt-popup cf-pop" ng-class="{
                    'active': profile.vars.cdc.dpop.active}">
                <div class="vertical-center-container">
                    <div class="center-content">
                        <div class="popup">
                            <div class="center-spinner-block loading" ng-show="profile.vars.cdc.dlting">
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
                                            <spring:message code="yt.tripper.accountprofile.popup.del.credit.header"/>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="ctrls">
                                            <ul class="clearfix">
                                                <li>
                                                    <div class="abtn">
                                                        <button class="yt-btn red-btn" ng-click="profile.funcs.rmcd()">
                                                            <spring:message code="yt.tripper.accountprofile.popup.del.credit.contine"/>
                                                        </button>
                                                    </div>
                                                </li>
                                                <li>
                                                    <div class="abtn">
                                                        <button class="yt-btn grey-btn" ng-click="profile.funcs.cdcd()">
                                                            <spring:message code="yt.tripper.accountprofile.popup.del.credit.cancel"/>
                                                        </button>
                                                    </div>
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

            <div class="yt-popup cf-pop" ng-class="{
                    'active' : profile.vars.billing.dpop.active}">
                <div class="vertical-center-container">
                    <div class="center-content">
                        <div class="popup">
                            <div class="center-spinner-block loading" ng-show="profile.vars.billing.dlting">
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
                                            <spring:message code="yt.tripper.accountprofile.popup.del.billing.header"/>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="ctrls">
                                            <ul class="clearfix">
                                                <li>
                                                    <div class="abtn">
                                                        <button class="yt-btn red-btn" ng-click="profile.funcs.rmb()">
                                                            <spring:message code="yt.tripper.accountprofile.popup.del.credit.contine"/>
                                                        </button>
                                                    </div>
                                                </li>
                                                <li>
                                                    <div class="abtn">
                                                        <button class="yt-btn grey-btn" ng-click="profile.funcs.cdb()">
                                                            <spring:message code="yt.tripper.accountprofile.popup.del.credit.cancel"/>
                                                        </button>
                                                    </div>
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

            <div class="yt-popup" id="billing-pop" ng-class="{
                    'active' : profile.vars.billing.detail.active}">
                <div class="vertical-center-container">
                    <div class="center-content" ng-click="POPUP.closePopup(profile.vars.billing.detail, $event)">
                        <div class="popup">
                            <div class="center-spinner-block loading" ng-show="profile.vars.billing.detail.lding">
                                <div class="vertical-center-container">
                                    <div class="center-content">
                                        <span class="yt-spinner center"></span>
                                    </div>
                                </div>
                            </div>
                            <div class="body">
                                <span class="close-btn"></span>
                                <div class="container">
                                    <div class="row">
                                        <div class="aI">
                                            <div class="lb">
                                                Billing Name
                                            </div>
                                            <div class="val">
                                                {{profile.vars.billing.detail.dt.billingName.length?profile.vars.billing.detail.dt.billingName:"N/A"}}
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="aI">
                                            <div class="lb">
                                                <spring:message code="yt.tripper.accountprofile.personal.firstname.label"/>
                                            </div>
                                            <div class="val">
                                                {{profile.vars.billing.detail.dt.firstName.length?profile.vars.billing.detail.dt.firstName:"N/A"}}
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="aI">
                                            <div class="lb">
                                                <spring:message code="yt.tripper.accountprofile.personal.lastname.label"/>
                                            </div>
                                            <div class="val">
                                                {{profile.vars.billing.detail.dt.lastName.length?profile.vars.billing.detail.dt.lastName:"N/A"}}
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="aI">
                                            <div class="lb">
                                                <spring:message code="yt.tripper.accountprofile.popup.detail.billing.branch"/>
                                            </div>
                                            <div class="val">
                                                {{profile.vars.billing.detail.dt.branch.length?profile.vars.billing.detail.dt.branch:"N/A"}}
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="aI">
                                            <div class="lb">
                                                <spring:message code="yt.tripper.accountprofile.personal.tax.label"/>
                                            </div>
                                            <div class="val">
                                                {{profile.vars.billing.detail.dt.taxID.length?profile.vars.billing.detail.dt.taxID:"N/A"}}
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="aI">
                                            <div class="lb">
                                                <spring:message code="yt.tripper.accountprofile.popup.detail.billing.address"/>
                                            </div>
                                            <div class="val">
                                                {{profile.vars.billing.detail.dt.address.length?profile.vars.billing.detail.dt.address:"N/A"}}
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>  

        </div>
        <script>
            var pData = ${requestScope.commonData};
        </script>

        <%@include file="../common/header.jsp" %>
    </body>
</html>
