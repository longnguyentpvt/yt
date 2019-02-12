<%-- 
    Document   : TrippDash
    Created on : Jul 5, 2018, 10:27:58 PM
    Author     : KyLong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="${pageContext.response.locale}" currency="${sessionScope.ytCurrency}" ng-app="youtripper" ng-controller="YoutripperController">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="../common/commonhead.jsp" %>

        <link rel="stylesheet" href="${requestScope.cssURL}partner-trippdash-menu.css" >
        <link rel="stylesheet" href="${requestScope.cssURL}partner-booking-history.css" >

        <script src="${requestScope.jsURL}partner-booking-history.js"></script>

        <title><spring:message code="yt.partner.bookinghistory.browser.header"/></title>
        <style>
            .reviews {
                height: 25px;
                line-height: 20px;
            }

            .reviews .t-r > ul {
                margin: 0 -2px;
            }

            .reviews .t-r > ul > li {
                display: inline-block;
                vertical-align: middle;
                padding: 0 2px;
            }

            .aR .unactive {
                display: none;
            }

            .aR.unactive .active {
                display: none;
            }

            .aR.unactive .unactive {
                display: block;
            }

            .reviews.nrv .aR .unactive path {
                stroke:#E94848;
                /*                fill: #E94848;*/
            }

            .reviews .t-r a{
                font-size: 11px;
                line-height: 11px;
                display: block;
            }
        </style>
    </head>
    <body>
        <div id="padding-header" ng-cloak ng-controller="BookingHistory">
            <!--MENU-->
            <%@include file="TrippDashMenu.jsp" %>
            <!--END MENU-->

            <div id="trippdash-body" ng-init="trippdashSection = 'b-history'">
                <div class="yt-normal-container">
                    <div id="booking-manager">
                        <div id="bm-filter">
                            <div class="cols">
                                <div class="clearfix">
                                    <div class="date-f">
                                        <div class="top-r">
                                            <div class="ddl-input">
                                                <select class="yt-input" ng-model="bookingM.vars.up.filter.time"
                                                        ng-change="bookingM.funcs.tfc()"
                                                        > 
                                                    <option ng-value="bookingM.vars.up.TIME_DATA.ALL"><spring:message code="yt.partner.bookinghistory.filter.allBooking"/></option>
                                                    <option ng-value="bookingM.vars.up.TIME_DATA.YESTERDAY"><spring:message code="yt.partner.bookinghistory.filter.yesterday"/></option>
                                                    <option ng-value="bookingM.vars.up.TIME_DATA.PREV_MONTH"><spring:message code="yt.partner.bookinghistory.filter.preMonth"/></option>
                                                    <option ng-value="bookingM.vars.up.TIME_DATA.SPECIFICED"><spring:message code="yt.partner.bookinghistory.filter.specifiedTime"/></option>
                                                </select>
                                            </div>

                                        </div>
                                        <div class="bottom-r">
                                            <div id="bk-date-i">
                                                <ul class="ins">
                                                    <li>                                                       
                                                        <div class="aI">
                                                            <div class="yt-input-ico search-input calendar-input">
                                                                <div class="yt-calendar">
                                                                    <div class="common">
                                                                        <div class="month semi-bold-txt">
                                                                            <span class="ctrl prv-ctrl" ng-show="startDate.rO.lav" ng-click="startDate.rO = CALENDAR.funcs.prvM(startDate.rO)"></span>
                                                                            <span class="ctrl nxt-ctrl" ng-show="startDate.rO.rav" ng-click="startDate.rO = CALENDAR.funcs.nxtM(startDate.rO)"></span>
                                                                            {{startDate.rO["moT"]}} {{startDate.rO["yeT"]}}
                                                                        </div>
                                                                        <div class="days  small-txt">
                                                                            <ul class="clearfix">
                                                                                <li>
                                                                                    MON
                                                                                </li>
                                                                                <li>
                                                                                    TUE
                                                                                </li>
                                                                                <li>
                                                                                    WED
                                                                                </li>
                                                                                <li>
                                                                                    THU
                                                                                </li>
                                                                                <li>
                                                                                    FRI
                                                                                </li>
                                                                                <li>
                                                                                    SAT
                                                                                </li>
                                                                                <li>
                                                                                    SUN
                                                                                </li>
                                                                            </ul>
                                                                        </div>
                                                                    </div>
                                                                    <div class="dates">
                                                                        <ul class="clearfix">
                                                                            <li ng-repeat="ad in startDate.rO.dates" ng-class="{
                                                                                    'disable' : ad.ty === 'out', 'active' : ad.ty === 'mo',
                                                                                            'today' : ad.ty === 'to', 'in' : ad.ty === 'in'}">
                                                                                <span ng-click="bookingM.funcs.csd($index)">
                                                                                    {{ad.txt}}
                                                                                </span>
                                                                            </li>
                                                                        </ul>
                                                                    </div>
                                                                </div>
                                                                <input type="text" name="" readonly="readonly"
                                                                       placeholder="<spring:message code="yt.partner.bookinghistory.filter.startDate.from"/>"
                                                                       ng-disabled="bookingM.vars.up.filter.time !== bookingM.vars.up.TIME_DATA.SPECIFICED"
                                                                       class="yt-input center-txt" ng-model="bookingM.vars.up.filter.startDate.ddMMyyyy"
                                                                       ng-class="{
                                                                               'readonly':bookingM.vars.up.filter.time !== bookingM.vars.up.TIME_DATA.SPECIFICED}"
                                                                       ng-click="startDate.rO = CALENDAR.funcs.getCa($event, bookingM.vars.cm.cd, bookingM.vars.up.filter.startDate.milli, null, null, bookingM.vars.up.filter.endDate.milli)">
                                                                <div class="ico">
                                                                    <svg width="24.4px" height="24.4px" viewBox="0 0 24.4 24.4">
                                                                    <path style="fill: #555555;" d="M23.5,1.7h-2.6v2.6c0,1.4-1.2,2.6-2.6,2.6c-1.4,0-2.6-1.2-2.6-2.6V1.7h-7v2.6
                                                                          C8.7,5.8,7.5,7,6.1,7C4.7,7,3.5,5.8,3.5,4.4V1.7H0.9C0.4,1.7,0,2.1,0,2.6v20.9c0,0.5,0.4,0.9,0.9,0.9h22.6c0.5,0,0.9-0.4,0.9-0.9
                                                                          V2.6C24.4,2.1,24,1.7,23.5,1.7z M20.9,10.4v10.4H3.5V8.7h17.4V10.4z"/>
                                                                    <rect x="5.2" y="10.4" style="fill: #555555;" width="3.5" height="3.5"/>
                                                                    <rect x="10.5" y="10.4" style="fill: #555555;" width="3.5" height="3.5"/>
                                                                    <rect x="15.7" y="10.4" style="fill: #555555;" width="3.5" height="3.5"/>
                                                                    <rect x="5.2" y="15.7" style="fill: #555555;" width="3.5" height="3.5"/>
                                                                    <rect x="10.5" y="15.7" style="fill:#555555;" width="3.5" height="3.5"/>
                                                                    <rect x="15.7" y="15.7" style="fill: #555555;" width="3.5" height="3.5"/>
                                                                    <path  style="fill: #555555;" d="M6.1,5.2C6.6,5.2,7,4.8,7,4.4V0.9C7,0.4,6.6,0,6.1,0C5.6,0,5.2,0.4,5.2,0.9v3.5
                                                                           C5.2,4.8,5.6,5.2,6.1,5.2z"/>
                                                                    <path  style="fill: #555555;" d="M18.3,5.2c0.5,0,0.9-0.4,0.9-0.9V0.9c0-0.5-0.4-0.9-0.9-0.9c-0.5,0-0.9,0.4-0.9,0.9v3.5
                                                                           C17.4,4.8,17.8,5.2,18.3,5.2z"/>
                                                                    </svg>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </li><li>
                                                        <div class="aI">
                                                            <div class="yt-input-ico search-input calendar-input">
                                                                <div class="yt-calendar">
                                                                    <div class="common">
                                                                        <div class="month semi-bold-txt">
                                                                            <span class="ctrl prv-ctrl" ng-show="startDate.rO.lav" ng-click="startDate.rO = CALENDAR.funcs.prvM(startDate.rO)"></span>
                                                                            <span class="ctrl nxt-ctrl" ng-show="startDate.rO.rav" ng-click="startDate.rO = CALENDAR.funcs.nxtM(startDate.rO)"></span>
                                                                            {{startDate.rO["moT"]}} {{startDate.rO["yeT"]}}
                                                                        </div>
                                                                        <div class="days  small-txt">
                                                                            <ul class="clearfix">
                                                                                <li>
                                                                                    MON
                                                                                </li>
                                                                                <li>
                                                                                    TUE
                                                                                </li>
                                                                                <li>
                                                                                    WED
                                                                                </li>
                                                                                <li>
                                                                                    THU
                                                                                </li>
                                                                                <li>
                                                                                    FRI
                                                                                </li>
                                                                                <li>
                                                                                    SAT
                                                                                </li>
                                                                                <li>
                                                                                    SUN
                                                                                </li>
                                                                            </ul>
                                                                        </div>
                                                                    </div>
                                                                    <div class="dates">
                                                                        <ul class="clearfix">
                                                                            <li ng-repeat="ad in startDate.rO.dates" ng-class="{
                                                                                    'disable' : ad.ty === 'out', 'active' : ad.ty === 'mo',
                                                                                            'today' : ad.ty === 'to', 'in' : ad.ty === 'in'}">
                                                                                <span ng-click="bookingM.funcs.ced($index)">
                                                                                    {{ad.txt}}
                                                                                </span>
                                                                            </li>
                                                                        </ul>
                                                                    </div>
                                                                </div>
                                                                <input type="text" name="" readonly="readonly"
                                                                       placeholder="<spring:message code="yt.partner.bookinghistory.filter.endDate.to"/>"
                                                                       ng-disabled="bookingM.vars.up.filter.time !== bookingM.vars.up.TIME_DATA.SPECIFICED"
                                                                       class="yt-input center-txt" ng-model="bookingM.vars.up.filter.endDate.ddMMyyyy"
                                                                       ng-class="{
                                                                               'readonly':bookingM.vars.up.filter.time !== bookingM.vars.up.TIME_DATA.SPECIFICED}"
                                                                       ng-click="startDate.rO = CALENDAR.funcs.getCa($event, bookingM.vars.cm.cd, bookingM.vars.up.filter.endDate.milli, null, bookingM.vars.up.filter.startDate.milli, null)">
                                                                <div class="ico">
                                                                    <svg width="24.4px" height="24.4px" viewBox="0 0 24.4 24.4">
                                                                    <path style="fill: #555555;" d="M23.5,1.7h-2.6v2.6c0,1.4-1.2,2.6-2.6,2.6c-1.4,0-2.6-1.2-2.6-2.6V1.7h-7v2.6
                                                                          C8.7,5.8,7.5,7,6.1,7C4.7,7,3.5,5.8,3.5,4.4V1.7H0.9C0.4,1.7,0,2.1,0,2.6v20.9c0,0.5,0.4,0.9,0.9,0.9h22.6c0.5,0,0.9-0.4,0.9-0.9
                                                                          V2.6C24.4,2.1,24,1.7,23.5,1.7z M20.9,10.4v10.4H3.5V8.7h17.4V10.4z"/>
                                                                    <rect x="5.2" y="10.4" style="fill: #555555;" width="3.5" height="3.5"/>
                                                                    <rect x="10.5" y="10.4" style="fill: #555555;" width="3.5" height="3.5"/>
                                                                    <rect x="15.7" y="10.4" style="fill: #555555;" width="3.5" height="3.5"/>
                                                                    <rect x="5.2" y="15.7" style="fill: #555555;" width="3.5" height="3.5"/>
                                                                    <rect x="10.5" y="15.7" style="fill:#555555;" width="3.5" height="3.5"/>
                                                                    <rect x="15.7" y="15.7" style="fill: #555555;" width="3.5" height="3.5"/>
                                                                    <path  style="fill: #555555;" d="M6.1,5.2C6.6,5.2,7,4.8,7,4.4V0.9C7,0.4,6.6,0,6.1,0C5.6,0,5.2,0.4,5.2,0.9v3.5
                                                                           C5.2,4.8,5.6,5.2,6.1,5.2z"/>
                                                                    <path  style="fill: #555555;" d="M18.3,5.2c0.5,0,0.9-0.4,0.9-0.9V0.9c0-0.5-0.4-0.9-0.9-0.9c-0.5,0-0.9,0.4-0.9,0.9v3.5
                                                                           C17.4,4.8,17.8,5.2,18.3,5.2z"/>
                                                                    </svg>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </li>
                                                </ul>
                                                <div class="bold-txt lb">
                                                    -
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="booking-f">
                                        <div class="top-r">
                                            <div class="aI">
                                                <div id="bk-no-i" class="close-input">
                                                    <div class="ico" ng-show="!bookingM.vars.up.filter.key_search.length">
                                                        <svg height="25px" viewBox="0 0 25.2 25">
                                                        <path style="fill: #555555" d="M22.5,2.8c-3.7-3.7-9.6-3.7-13.3,0s-3.7,9.5,0,13.2s9.6,3.7,13.3,0
                                                              C26.2,12.3,26.2,6.4,22.5,2.8z M21.1,14.7c-2.9,3-7.7,3-10.6,0c-3-3-3-7.7,0-10.6c2.9-2.9,7.7-3,10.6,0C24.1,7,24.1,11.7,21.1,14.7
                                                              z"></path>
                                                        <path style="fill: #555555" d="M6.4,15.9l-5.8,5.7c-0.8,0.8-0.8,2,0,2.8c0.8,0.8,2.1,0.8,2.8,0l5.8-5.7c0.8-0.8,0.8-2,0-2.8
                                                              C8.4,15.2,7.2,15.2,6.4,15.9z"></path>
                                                        </svg>
                                                    </div>
                                                    <input ng-keypress="bookingM.funcs.sbbk($event)"
                                                           ng-change="bookingM.funcs.bts()"
                                                           ng-model="bookingM.vars.up.filter.key_search"
                                                           ytmaxlength="10"
                                                           type="text" class="yt-input" placeholder="<spring:message code="yt.partner.bookinghistory.filter.bookingNo.input"/>"/>
                                                    <span class="close-btn" 
                                                          ng-click="bookingM.funcs.cbkn()"
                                                          ng-show="bookingM.vars.up.filter.key_search.length"></span>
                                                </div>
                                            </div>
                                        </div>

                                    </div>
                                    <div class="pkg-f">
                                        <div class="top-r">
                                            <div class="ddl-input">
                                                <select class="yt-input" ng-model="bookingM.vars.up.filter.spkg" 
                                                        ng-change="bookingM.funcs.cpkg()">
                                                    <option ng-value="bookingM.vars.up.PACKAGE_TYPE.REGULAR"><spring:message code="yt.partner.bookinghistory.filter.allRegular"/></option>
                                                    <option ng-value="bookingM.vars.up.PACKAGE_TYPE.OPENED"><spring:message code="yt.partner.bookinghistory.filter.allOpen"/></option>
                                                    <option ng-repeat="pkg in bookingM.vars.PACKAGE_LIST"
                                                            ng-value="pkg"
                                                            >
                                                        {{pkg.contents[0].packageName}}
                                                    </option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="bottom-r">
                                            <div id="cn-i">
                                                <ul class="lrs">
                                                    <li class="cn-lr">
                                                        <div class="ddl-input">
                                                            <select class="yt-input" ng-model="bookingM.vars.up.filter.channel"
                                                                    ng-change="bookingM.funcs.ccn()"
                                                                    >
                                                                <option ng-value="null"><spring:message code="yt.partner.bookinghistory.filter.allChannel"/></option>
                                                                <option ng-value="true"><spring:message code="yt.partner.bookinghistory.filter.offlineChannel"/></option>
                                                                <option ng-value="false"><spring:message code="yt.partner.bookinghistory.filter.onlineChannel"/></option>
                                                            </select>
                                                        </div>

                                                    </li>
                                                    <li class="rv-lr">
                                                        <div class="vertical-center-container">
                                                            <div class="center-content">
                                                                <div class="checkbox-ctn">
                                                                    <div class="yt-checkbox">
                                                                        <input ng-model="bookingM.vars.up.filter.reviewed"
                                                                               ng-change="bookingM.funcs.crv()" 
                                                                               type="checkbox" name="" value="ON">
                                                                        <span></span>
                                                                    </div> 
                                                                    Show only packages that Trippers have reviewed
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

                        <div id="ut-tb" ng-if="bookingM.vars.up.filter.type === bookingM.vars.up.PACKAGE_TYPE.REGULAR">
                            <div class="tbl">

                                <div class="row h-row">
                                    <div class="cols">
                                        <ul class="clearfix">
                                            <li class="aCol bo-col">
                                                <div class="col-t">
                                                    <div class="vertical-center-container">
                                                        <div class="center-content">
                                                            <spring:message code="yt.partner.bookinghistory.tbl.upcoming.col.bookingNo"/>
                                                        </div>
                                                    </div>
                                                </div>
                                            </li>
                                            <li class="aCol bd-col">
                                                <div class="col-t">
                                                    <div class="vertical-center-container">
                                                        <div class="center-content">
                                                            <spring:message code="yt.partner.bookinghistory.tbl.upcoming.col.servingDate"/>
                                                        </div>
                                                    </div>
                                                </div>
                                            </li>
                                            <li class="aCol st-col">
                                                <div class="col-t">
                                                    <div class="vertical-center-container">
                                                        <div class="center-content">
                                                            <spring:message code="yt.partner.bookinghistory.tbl.upcoming.col.h1.startTime"/><br/>
                                                            [<spring:message code="yt.partner.bookinghistory.tbl.upcoming.col.h2.duration"/>]
                                                        </div>
                                                    </div>
                                                </div>
                                            </li>
                                            <li class="aCol tp-col">
                                                <div class="col-t">
                                                    <div class="vertical-center-container">
                                                        <div class="center-content">
                                                            <spring:message code="yt.partner.bookinghistory.tbl.upcoming.col.tripper"/>
                                                        </div>
                                                    </div>
                                                </div>
                                            </li>
                                            <li class="aCol pn-col">
                                                <div class="col-t">
                                                    <div class="vertical-center-container">
                                                        <div class="center-content">
                                                            <spring:message code="yt.partner.bookinghistory.tbl.upcoming.col.packageName"/>
                                                        </div>
                                                    </div>
                                                </div>
                                            </li>
                                            <li class="aCol qty-col">
                                                <div class="col-t">
                                                    <div class="vertical-center-container">
                                                        <div class="center-content">
                                                            <spring:message code="yt.partner.bookinghistory.tbl.upcoming.col.bookedQty"/>
                                                        </div>
                                                    </div>
                                                </div>
                                            </li>
                                            <li class="aCol cn-col">
                                                <div class="col-t">
                                                    <div class="vertical-center-container">
                                                        <div class="center-content">
                                                            <spring:message code="yt.partner.bookinghistory.tbl.upcoming.col.channel"/>
                                                        </div>
                                                    </div>
                                                </div>
                                            </li>
                                            <li class="aCol am-col">
                                                <div class="col-t">
                                                    <div class="vertical-center-container">
                                                        <div class="center-content">
                                                            <spring:message code="yt.partner.bookinghistory.tbl.upcoming.col.paymentAmount"/>
                                                        </div>
                                                    </div>
                                                </div>
                                            </li>
                                            <li class="aCol rv-col">
                                                <div class="col-t">
                                                    <div class="vertical-center-container">
                                                        <div class="center-content">
                                                            <spring:message code="yt.partner.bookinghistory.tbl.upcoming.col.tripperReview"/>
                                                        </div>
                                                    </div>
                                                </div>
                                            </li>
                                        </ul>
                                    </div>
                                </div>

                                <div class="row o-row" ng-repeat="bk in bookingM.vars.up.dt">
                                    <div class="cols">
                                        <ul class="clearfix">
                                            <li class="aCol bo-col">
                                                <div class="col-t">
                                                    <div>
                                                        <span class="link">{{bk.bookingNo}}</span>
                                                    </div>
                                                    <div ng-if="bk.tripperAbsence">
                                                        <span class="absence-txt"><spring:message code="yt.partner.bookinghistory.tbl.upcoming.absence"/></span>
                                                    </div>
                                            </li>
                                            <li class="aCol bd-col">
                                                <div class="col-t">
                                                    {{bk.tripDateStr}}
                                                </div>
                                            </li>
                                            <li class="aCol st-col">
                                                <div class="col-t">
                                                    <div class="vertical-center-container">
                                                        <div class="center-content">
                                                            {{bk.startTimeStr.length?bk.startTimeStr:"N/A"}}<br/>
                                                            <span ng-if="bk.durationType !== bookingM.vars.up.DATA.dmin"> 
                                                                [ {{bk.duration}} 
                                                                <span ng-if="bk.durationType === bookingM.vars.up.DATA.dd">
                                                                    <spring:message code="yt.partner.bookinghistory.tbl.upcoming.day.text"/>
                                                                </span> 
                                                                <span ng-if="bk.durationType === bookingM.vars.up.DATA.dw">
                                                                    <spring:message code="yt.partner.bookinghistory.tbl.upcoming.week.text"/>
                                                                </span> 
                                                                <span ng-if="bk.durationType === bookingM.vars.up.DATA.dmonth">
                                                                    <spring:message code="yt.partner.bookinghistory.tbl.upcoming.month.text"/>
                                                                </span> ]
                                                            </span> 
                                                            <span ng-if="bk.durationType === bookingM.vars.up.DATA.dmin"> 
                                                                [ {{bk.duration}}<spring:message code="yt.partner.bookinghistory.tbl.upcoming.hour.text"/> 
                                                                <span ng-if="bk.durationMinute > 0">{{bk.durationMinute}}<spring:message code="yt.partner.bookinghistory.tbl.upcoming.min.text"/></span> ]
                                                            </span> 
                                                        </div>
                                                    </div>
                                                </div>
                                            </li>
                                            <li class="aCol tp-col">
                                                <div class="col-t">
                                                    {{bk.billingFirstName}}, {{bk.billingLastName}}
                                                </div>
                                            </li>
                                            <li class="aCol pn-col">
                                                <div class="col-t">
                                                    {{bk.packageName}}
                                                </div>
                                            </li>
                                            <li class="aCol qty-col">
                                                <div class="col-t">
                                                    <div class="vertical-center-container">
                                                        <div class="center-content">
                                                            <span class="qty-no">{{bk.bookedQty}}</span>
                                                        </div>
                                                    </div>
                                                </div>
                                            </li>
                                            <li class="aCol cn-col">
                                                <div class="col-t">
                                                    <div class="vertical-center-container">
                                                        <div class="center-content">
                                                            <div class="cn-ico" ng-class="{
                                                                    'off':bk.offlineBooking}">
                                                                <svg  width="30px" height="30px" viewBox="0 0 30 30" class="yt-cn">
                                                                <path style="fill: #E94848" d="M15,30c8.3,0,15-6.7,15-15c0-8.3-6.7-15-15-15C6.7,0,0,6.7,0,15C0,23.3,6.7,30,15,30"/>
                                                                <path style="fill: #FFF" d="M23.8,15.7c-1.2,1.2-2.6,2.1-4.1,2.7c-1.5,0.6-3.1,0.9-4.7,0.9c-1.6,0-3.2-0.3-4.7-0.9
                                                                      c-1.5-0.6-2.9-1.5-4.1-2.7c-1.2-1.2-2.1-2.9-2.7-4.4C3.3,11,3.2,11,3.1,10h2.2c0.5,2,1.3,2.9,2.4,3.9c2,2,4.5,3.2,7.3,3.2
                                                                      c2.4,0,4.7-1.1,6.5-2.6L17.2,10h3l2.9,2.9c0.7-0.9,1.3-1.9,1.7-2.9h2.2c-0.1,1-0.2,1-0.4,1.3C25.9,12.9,25,14.6,23.8,15.7z"/>
                                                                </svg>
                                                                <svg width="30px" height="30px" viewBox="0 0 30 30" class="off-cn">
                                                                <circle style="fill: #F0803D" cx="15" cy="15" r="15"/>
                                                                <circle style="fill: #FFF" cx="15.8" cy="5.6" r="1.9"/>
                                                                <path style="fill: #FFF" d="M21,14.3L21,14.3l0.4-0.3c0,0,0,0,0,0l-2.7-1.7c0,0-1.5-2.8-1.8-3.6c-0.4-0.8-1.6-1.2-2.7-0.4
                                                                      c-0.8,0.6-2.8,2.2-3.7,2.8c-0.2,0.2-0.5,0.3-0.5,0.7c-0.1,0.4-1,3.4-1,3.4v0c0,0.1,0.1,0.1,0.1,0.2c0,0.5,0.4,1.1,0.9,1.1
                                                                      c0.4,0,0.8-0.4,0.9-0.4h0l0.8-3.4l1.3-1L11,20.1L8.5,24h0c-0.1,0-0.2,0.6-0.2,0.8c0,0.7,0.6,1,1.3,1c0.5,0,0.9-0.8,1.1-0.8h0
                                                                      c0,0,2.5-4,2.7-4.2c0.1-0.2,0.2-0.3,0.3-0.6c0.1-0.3,0.6-2.5,0.6-2.5l2.7,2.9l0.8,4.4c0,0.1,0,0,0,0.1l0-0.1h0
                                                                      c0.1,0,0.6,1.1,1.2,1.1c0.7,0,1.3-0.8,1.3-1.5c0-0.1,0-0.6,0-0.6h0c0,0-0.9-4-0.9-4.2c0-0.2-0.2-0.3-0.3-0.5
                                                                      c-0.1-0.2-3.1-3.6-3.1-3.6l0.8-3.9c0,0,0.5,1.1,0.6,1.3c0.1,0.2,0,0.3,0.1,0.4c0.2,0.1,2.6,2.1,2.6,2.1v0c1,0.1,0.5,0.1,0.7,0.1
                                                                      c0.5,0,0.8-0.4,0.8-0.8C21.4,14.7,22,14.4,21,14.3z"/>
                                                                </svg>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </li>
                                            <li class="aCol am-col">
                                                <div class="col-t">
                                                    <div class="vertical-center-container">
                                                        <div class="center-content">
                                                            {{bk.currencyCode}} {{bk.total| currency:"":2}}
                                                        </div>
                                                    </div>
                                                </div>
                                            </li>
                                            <li class="aCol rv-col">
                                                <div class="col-t">
                                                    <div class="vertical-center-container">
                                                        <div class="center-content">
                                                            <div class="reviews" ng-class="{
                                                                    'nrv':!bk.reviewed}">
                                                                <div class="t-r">
                                                                    <ul>
                                                                        <li ng-repeat="i in [0, 1, 2, 3, 4]">
                                                                            <div class="aR" ng-class="{
                                                                                    'active'
                                                                                            : (i < bk.packageRate), 'unactive' : (i >= bk.packageRate)}">
                                                                                <svg width="11.4px" height="11px" viewBox="0 0 10.4 10"
                                                                                     class="unactive">
                                                                                <path style="fill:#E99C48;" d="M5.2,0.7l1.1,2.2c0.2,0.4,0.6,0.7,1,0.7L9.8,4L8,5.7C7.7,6,7.5,6.4,7.6,6.9L8,9.3L5.8,8.2
                                                                                      C5.6,8.1,5.4,8,5.2,8C5,8,4.8,8.1,4.6,8.2L2.4,9.3l0.4-2.4C2.9,6.4,2.8,6,2.5,5.7L0.7,4l2.5-0.4c0.4-0.1,0.8-0.3,1-0.7L5.2,0.7
                                                                                      M5.2,0C5,0,4.8,0.1,4.7,0.4L3.5,2.6C3.4,2.8,3.3,2.9,3.1,3L0.5,3.3C0,3.4-0.2,4.1,0.2,4.4L2,6.2c0.2,0.1,0.2,0.4,0.2,0.6L1.8,9.3
                                                                                      C1.7,9.7,2,10,2.4,10c0.1,0,0.2,0,0.3-0.1l2.2-1.2c0.1,0,0.2-0.1,0.3-0.1c0.1,0,0.2,0,0.3,0.1l2.2,1.2C7.9,10,8,10,8.1,10
                                                                                      c0.4,0,0.7-0.3,0.6-0.7L8.3,6.8c0-0.2,0-0.4,0.2-0.6l1.8-1.8c0.4-0.4,0.2-1-0.4-1.1L7.4,3C7.2,2.9,7,2.8,6.9,2.6L5.8,0.4
                                                                                      C5.7,0.1,5.5,0,5.2,0L5.2,0z"/>
                                                                                </svg>  
                                                                                <svg width="11.4px" height="11px" viewBox="0 0 10.4 10"
                                                                                     class="active">
                                                                                <path style="fill:#E99C48;" d="M5.2,0C5,0,4.8,0.1,4.7,0.4L3.5,2.6C3.4,2.8,3.3,2.9,3.1,3L0.5,3.3C0,3.4-0.2,4.1,0.2,4.4L2,6.2
                                                                                      c0.2,0.1,0.2,0.4,0.2,0.6L1.8,9.3C1.7,9.7,2,10,2.4,10c0.1,0,0.2,0,0.3-0.1l2.2-1.2c0.1,0,0.2-0.1,0.3-0.1c0.1,0,0.2,0,0.3,0.1
                                                                                      l2.2,1.2C7.9,10,8,10,8.1,10c0.4,0,0.7-0.3,0.6-0.7L8.3,6.8c0-0.2,0-0.4,0.2-0.6l1.8-1.8c0.4-0.4,0.2-1-0.4-1.1L7.4,3
                                                                                      C7.2,2.9,7,2.8,6.9,2.6L5.8,0.4C5.7,0.1,5.5,0,5.2,0L5.2,0z"/>
                                                                                </svg>  
                                                                            </div>
                                                                        </li>
                                                                    </ul>
                                                                    <a ng-if="!bk.reviewed && bk.reviewToken != null"
                                                                       href=""
                                                                       >
                                                                        Request Review
                                                                    </a>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                                <div class="center-spinner-block loading" ng-show="bookingM.vars.up.lding">
                                    <div class="vertical-center-container">
                                        <div class="center-content">
                                            <span class="yt-spinner center"></span>
                                        </div>
                                    </div>
                                </div>


                            </div>

                            <div class="no-rows" ng-if="!bookingM.vars.up.dt.length">
                                <spring:message code="yt.partner.bookinghistory.tbl.upcoming.noBooking"/>
                            </div>



                            <div class="paging" ng-if="bookingM.vars.up.dt.length">
                                <ul>
                                    <li>
                                        <div class="page">
                                            {{bookingM.vars.up.paging.pageRange}} <spring:message code="yt.partner.bookinghistory.tbl.upcoming.paging.of.text"/> {{bookingM.vars.up.paging.total}}
                                        </div>
                                    </li>
                                    <li>
                                        <div class="ctrls">
                                            <ul class="clearfix">
                                                <li>
                                                    <div class="aCtrl">
                                                        <span class="btn prv"  ng-class="{'dat':bookingM.vars.up.filter.currentPage === 1}"
                                                              ng-click="bookingM.funcs.prevU()"></span>
                                                    </div>
                                                </li>
                                                <li>
                                                    <div class="aCtrl">
                                                        <span class="btn nxt" ng-class="{'dat':bookingM.vars.up.filter.currentPage === bookingM.vars.up.paging.totalPage}"
                                                              ng-click="bookingM.funcs.nextU()"></span>
                                                    </div>
                                                </li>
                                            </ul>
                                        </div>
                                    </li>
                                </ul>
                            </div>
                        </div>

                        <div id="op-tb" ng-if="bookingM.vars.up.filter.type === bookingM.vars.up.PACKAGE_TYPE.OPENED">
                            <div class="tbl">
                                <div class="row h-row">
                                    <div class="cols">
                                        <ul class="clearfix">
                                            <li class="aCol bo-col">
                                                <div class="col-t">
                                                    <div class="vertical-center-container">
                                                        <div class="center-content">
                                                            <spring:message code="yt.partner.bookinghistory.tbl.upcoming.col.bookingNo"/>
                                                        </div>
                                                    </div>
                                                </div>
                                            </li>
                                            <li class="aCol fd-col">
                                                <div class="col-t">
                                                    <div class="vertical-center-container">
                                                        <div class="center-content">
                                                            <spring:message code="yt.partner.bookinghistory.tbl.upcoming.col.finishedDate"/>
                                                        </div>
                                                    </div>
                                                </div>
                                            </li>
                                            <li class="aCol bd-col">
                                                <div class="col-t">
                                                    <div class="vertical-center-container">
                                                        <div class="center-content">
                                                            <spring:message code="yt.partner.bookinghistory.tbl.upcoming.col.expirationDate"/>
                                                        </div>
                                                    </div>
                                                </div>
                                            </li>
                                            <li class="aCol st-col">
                                                <div class="col-t">
                                                    <div class="vertical-center-container">
                                                        <div class="center-content">
                                                            <spring:message code="yt.partner.bookinghistory.tbl.upcoming.col.h1.startTime"/><br/>
                                                            [<spring:message code="yt.partner.bookinghistory.tbl.upcoming.col.h2.duration"/>]
                                                        </div>
                                                    </div>
                                                </div>
                                            </li>
                                            <li class="aCol tp-col">
                                                <div class="col-t">
                                                    <div class="vertical-center-container">
                                                        <div class="center-content">
                                                            <spring:message code="yt.partner.bookinghistory.tbl.upcoming.col.tripper"/>
                                                        </div>
                                                    </div>
                                                </div>
                                            </li>
                                            <li class="aCol pn-col">
                                                <div class="col-t">
                                                    <div class="vertical-center-container">
                                                        <div class="center-content">
                                                            <spring:message code="yt.partner.bookinghistory.tbl.upcoming.col.packageName"/>
                                                        </div>
                                                    </div>
                                                </div>
                                            </li>
                                            <li class="aCol cn-col">
                                                <div class="col-t">
                                                    <div class="vertical-center-container">
                                                        <div class="center-content">
                                                            <spring:message code="yt.partner.bookinghistory.tbl.upcoming.col.channel"/>
                                                        </div>
                                                    </div>
                                                </div>
                                            </li>
                                            <li class="aCol rm-col">
                                                <div class="col-t">
                                                    <div class="vertical-center-container">
                                                        <div class="center-content">
                                                            <spring:message code="yt.partner.bookinghistory.tbl.upcoming.col.remaining"/>
                                                        </div>
                                                    </div>
                                                </div>
                                            </li>
                                            <li class="aCol am-col">
                                                <div class="col-t">
                                                    <div class="vertical-center-container">
                                                        <div class="center-content">
                                                            <spring:message code="yt.partner.bookinghistory.tbl.upcoming.col.paymentAmount"/>
                                                        </div>
                                                    </div>
                                                </div>
                                            </li>
                                            <li class="aCol rv-col">
                                                <div class="col-t">
                                                    <div class="vertical-center-container">
                                                        <div class="center-content">
                                                            <spring:message code="yt.partner.bookinghistory.tbl.upcoming.col.tripperReview"/>
                                                        </div>
                                                    </div>
                                                </div>
                                            </li>
                                        </ul>
                                    </div>
                                </div>

                                <div class="row o-row" ng-repeat="op in bookingM.vars.up.dt">
                                    <div class="cols">
                                        <ul class="clearfix">
                                            <li class="aCol bo-col">
                                                <div class="col-t">
                                                    <div>
                                                        <span class="link">{{op.bookingNo}}</span>
                                                    </div>
                                                    <div ng-if="op.tripperAbsence">
                                                        <span class="absence-txt"><spring:message code="yt.partner.bookinghistory.tbl.upcoming.absence"/></span>
                                                    </div>
                                                </div>
                                            </li>
                                            <li class="aCol fd-col">
                                                <div class="col-t">
                                                    {{op.otExpiredDate > 0 ? op.otExpiredDateStr : "N/A" }}
                                                </div>
                                            </li>
                                            <li class="aCol bd-col">
                                                <div class="col-t">
                                                    {{op.expirationDate === null? "N/A": (op.expirationDate === bookingM.vars.up.DATA.nepd ?"<spring:message code="yt.partner.bookinghistory.tbl.upcoming.noExp.txt"/>" :op.expirationDateStr)}}
                                                </div>
                                            </li>
                                            <li class="aCol st-col">
                                                <div class="col-t">
                                                    <div class="vertical-center-container">
                                                        <div class="center-content">
                                                            <div class="center-content">
                                                                {{op.startTimeStr.length?op.startTimeStr:"N/A"}}<br/>
                                                                <span ng-if="op.durationType !== bookingM.vars.up.DATA.dmin"> 
                                                                    [ {{op.duration}} 
                                                                    <span ng-if="op.durationType === bookingM.vars.up.DATA.dd">
                                                                        <spring:message code="yt.partner.bookinghistory.tbl.upcoming.day.text"/>
                                                                    </span> 
                                                                    <span ng-if="op.durationType === bookingM.vars.up.DATA.dw">
                                                                        <spring:message code="yt.partner.bookinghistory.tbl.upcoming.week.text"/>
                                                                    </span> 
                                                                    <span ng-if="op.durationType === bookingM.vars.up.DATA.dmonth">
                                                                        <spring:message code="yt.partner.bookinghistory.tbl.upcoming.month.text"/>
                                                                    </span> ]
                                                                </span> 
                                                                <span ng-if="op.durationType === bookingM.vars.up.DATA.dmin"> 
                                                                    [ {{op.duration}}<spring:message code="yt.partner.bookinghistory.tbl.upcoming.hour.text"/> 
                                                                    <span ng-if="op.durationMinute > 0">{{op.durationMinute}}<spring:message code="yt.partner.bookinghistory.tbl.upcoming.min.text"/></span> ]
                                                                </span> 
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </li>
                                            <li class="aCol tp-col">
                                                <div class="col-t">
                                                    {{op.billingFirstName}}, {{op.billingLastName}}
                                                </div>
                                            </li>
                                            <li class="aCol pn-col">
                                                <div class="col-t">
                                                    {{op.packageName}}
                                                </div>
                                            </li>
                                            <li class="aCol cn-col">
                                                <div class="col-t">
                                                    <div class="vertical-center-container">
                                                        <div class="center-content">
                                                            <div class="cn-ico" ng-class="{
                                                                    'off':op.offlineBooking}">
                                                                <svg  width="30px" height="30px" viewBox="0 0 30 30" class="yt-cn">
                                                                <path style="fill: #E94848" d="M15,30c8.3,0,15-6.7,15-15c0-8.3-6.7-15-15-15C6.7,0,0,6.7,0,15C0,23.3,6.7,30,15,30"/>
                                                                <path style="fill: #FFF" d="M23.8,15.7c-1.2,1.2-2.6,2.1-4.1,2.7c-1.5,0.6-3.1,0.9-4.7,0.9c-1.6,0-3.2-0.3-4.7-0.9
                                                                      c-1.5-0.6-2.9-1.5-4.1-2.7c-1.2-1.2-2.1-2.9-2.7-4.4C3.3,11,3.2,11,3.1,10h2.2c0.5,2,1.3,2.9,2.4,3.9c2,2,4.5,3.2,7.3,3.2
                                                                      c2.4,0,4.7-1.1,6.5-2.6L17.2,10h3l2.9,2.9c0.7-0.9,1.3-1.9,1.7-2.9h2.2c-0.1,1-0.2,1-0.4,1.3C25.9,12.9,25,14.6,23.8,15.7z"/>
                                                                </svg>
                                                                <svg width="30px" height="30px" viewBox="0 0 30 30" class="off-cn">
                                                                <circle style="fill: #F0803D" cx="15" cy="15" r="15"/>
                                                                <circle style="fill: #FFF" cx="15.8" cy="5.6" r="1.9"/>
                                                                <path style="fill: #FFF" d="M21,14.3L21,14.3l0.4-0.3c0,0,0,0,0,0l-2.7-1.7c0,0-1.5-2.8-1.8-3.6c-0.4-0.8-1.6-1.2-2.7-0.4
                                                                      c-0.8,0.6-2.8,2.2-3.7,2.8c-0.2,0.2-0.5,0.3-0.5,0.7c-0.1,0.4-1,3.4-1,3.4v0c0,0.1,0.1,0.1,0.1,0.2c0,0.5,0.4,1.1,0.9,1.1
                                                                      c0.4,0,0.8-0.4,0.9-0.4h0l0.8-3.4l1.3-1L11,20.1L8.5,24h0c-0.1,0-0.2,0.6-0.2,0.8c0,0.7,0.6,1,1.3,1c0.5,0,0.9-0.8,1.1-0.8h0
                                                                      c0,0,2.5-4,2.7-4.2c0.1-0.2,0.2-0.3,0.3-0.6c0.1-0.3,0.6-2.5,0.6-2.5l2.7,2.9l0.8,4.4c0,0.1,0,0,0,0.1l0-0.1h0
                                                                      c0.1,0,0.6,1.1,1.2,1.1c0.7,0,1.3-0.8,1.3-1.5c0-0.1,0-0.6,0-0.6h0c0,0-0.9-4-0.9-4.2c0-0.2-0.2-0.3-0.3-0.5
                                                                      c-0.1-0.2-3.1-3.6-3.1-3.6l0.8-3.9c0,0,0.5,1.1,0.6,1.3c0.1,0.2,0,0.3,0.1,0.4c0.2,0.1,2.6,2.1,2.6,2.1v0c1,0.1,0.5,0.1,0.7,0.1
                                                                      c0.5,0,0.8-0.4,0.8-0.8C21.4,14.7,22,14.4,21,14.3z"/>
                                                                </svg>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </li>
                                            <li class="aCol rm-col">
                                                <div class="col-t">
                                                    <div class="vertical-center-container">
                                                        <div class="center-content">
                                                            <div class="rm-d" ng-if="op.otServingQTY > 0">
                                                                <ul>
                                                                    <li>
                                                                        <div class="no">
                                                                            {{op.otServingQTY - op.otServedQTY}}
                                                                        </div>
                                                                    </li> 
                                                                    <li>
                                                                        <div class="ico bold-txt">
                                                                            /
                                                                        </div>
                                                                    </li> 
                                                                    <li>
                                                                        <div class="no tt-n">
                                                                            {{op.otServingQTY}}
                                                                        </div>
                                                                    </li> 
                                                                </ul>
                                                            </div>
                                                            <div class="rm-d" ng-if="op.otServingQTY === 0">
                                                                <div class="txt">
                                                                    <spring:message code="yt.partner.bookinghistory.tbl.upcoming.unlimit.txt"/>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </li>
                                            <li class="aCol am-col">
                                                <div class="col-t">
                                                    <div class="vertical-center-container">
                                                        <div class="center-content">
                                                            {{op.currencyCode}} {{op.total| currency:"":2}}
                                                        </div>
                                                    </div>
                                                </div>
                                            </li>
                                            <li class="aCol rv-col">
                                                <div class="col-t">
                                                    <div class="vertical-center-container">
                                                        <div class="center-content">
                                                            <div class="reviews" ng-class="{
                                                                    'nrv':!op.reviewed}">
                                                                <div class="t-r">
                                                                    <ul>
                                                                        <li ng-repeat="i in [0, 1, 2, 3, 4]">
                                                                            <div class="aR" ng-class="{
                                                                                    'active'
                                                                                            : (i < op.packageRate), 'unactive' : (i >= op.packageRate)}">
                                                                                <svg width="11.4px" height="11px" viewBox="0 0 10.4 10"
                                                                                     class="unactive">
                                                                                <path style="fill:#E99C48;" d="M5.2,0.7l1.1,2.2c0.2,0.4,0.6,0.7,1,0.7L9.8,4L8,5.7C7.7,6,7.5,6.4,7.6,6.9L8,9.3L5.8,8.2
                                                                                      C5.6,8.1,5.4,8,5.2,8C5,8,4.8,8.1,4.6,8.2L2.4,9.3l0.4-2.4C2.9,6.4,2.8,6,2.5,5.7L0.7,4l2.5-0.4c0.4-0.1,0.8-0.3,1-0.7L5.2,0.7
                                                                                      M5.2,0C5,0,4.8,0.1,4.7,0.4L3.5,2.6C3.4,2.8,3.3,2.9,3.1,3L0.5,3.3C0,3.4-0.2,4.1,0.2,4.4L2,6.2c0.2,0.1,0.2,0.4,0.2,0.6L1.8,9.3
                                                                                      C1.7,9.7,2,10,2.4,10c0.1,0,0.2,0,0.3-0.1l2.2-1.2c0.1,0,0.2-0.1,0.3-0.1c0.1,0,0.2,0,0.3,0.1l2.2,1.2C7.9,10,8,10,8.1,10
                                                                                      c0.4,0,0.7-0.3,0.6-0.7L8.3,6.8c0-0.2,0-0.4,0.2-0.6l1.8-1.8c0.4-0.4,0.2-1-0.4-1.1L7.4,3C7.2,2.9,7,2.8,6.9,2.6L5.8,0.4
                                                                                      C5.7,0.1,5.5,0,5.2,0L5.2,0z"/>
                                                                                </svg>  
                                                                                <svg width="11.4px" height="11px" viewBox="0 0 10.4 10"
                                                                                     class="active">
                                                                                <path style="fill:#E99C48;" d="M5.2,0C5,0,4.8,0.1,4.7,0.4L3.5,2.6C3.4,2.8,3.3,2.9,3.1,3L0.5,3.3C0,3.4-0.2,4.1,0.2,4.4L2,6.2
                                                                                      c0.2,0.1,0.2,0.4,0.2,0.6L1.8,9.3C1.7,9.7,2,10,2.4,10c0.1,0,0.2,0,0.3-0.1l2.2-1.2c0.1,0,0.2-0.1,0.3-0.1c0.1,0,0.2,0,0.3,0.1
                                                                                      l2.2,1.2C7.9,10,8,10,8.1,10c0.4,0,0.7-0.3,0.6-0.7L8.3,6.8c0-0.2,0-0.4,0.2-0.6l1.8-1.8c0.4-0.4,0.2-1-0.4-1.1L7.4,3
                                                                                      C7.2,2.9,7,2.8,6.9,2.6L5.8,0.4C5.7,0.1,5.5,0,5.2,0L5.2,0z"/>
                                                                                </svg>  
                                                                            </div>

                                                                        </li>
                                                                    </ul>
                                                                    <a ng-if="!op.reviewed && op.reviewToken != null"
                                                                       href=""
                                                                       >
                                                                        Request Review
                                                                    </a>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </li>
                                        </ul>
                                    </div>
                                </div>

                                <div class="center-spinner-block loading" ng-show="bookingM.vars.up.lding">
                                    <div class="vertical-center-container">
                                        <div class="center-content">
                                            <span class="yt-spinner center"></span>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="no-rows" ng-if="!bookingM.vars.up.dt.length">
                                <spring:message code="yt.partner.bookinghistory.tbl.upcoming.noBooking"/>
                            </div>


                            <div class="paging" ng-if="bookingM.vars.up.dt.length">
                                <ul>
                                    <li>
                                        <div class="page">
                                            {{bookingM.vars.up.paging.pageRange}} <spring:message code="yt.partner.bookinghistory.tbl.upcoming.paging.of.text"/> {{bookingM.vars.up.paging.total}}
                                        </div>
                                    </li>
                                    <li>
                                        <div class="ctrls">
                                            <ul class="clearfix">
                                                <li>
                                                    <div class="aCtrl">
                                                        <span class="btn prv"  ng-class="{'dat':bookingM.vars.up.filter.currentPage === 1}"
                                                              ng-click="bookingM.funcs.prevU()"></span>
                                                    </div>
                                                </li>
                                                <li>
                                                    <div class="aCtrl">
                                                        <span class="btn nxt" ng-class="{'dat':bookingM.vars.up.filter.currentPage === bookingM.vars.up.paging.totalPage}"
                                                              ng-click="bookingM.funcs.nextU()"></span>
                                                    </div>
                                                </li>
                                            </ul>
                                        </div>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <%@include file="../common/header.jsp" %>
        </div>
        <script>
            var initData = ${requestScope.initData};
            var limit = 1;
        </script>
    </body>
</html>
