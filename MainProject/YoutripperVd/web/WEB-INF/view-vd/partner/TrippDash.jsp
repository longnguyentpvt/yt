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
        <link rel="stylesheet" href="${requestScope.cssURL}partner-trippdash-dashboard.css" >

        <script src="${requestScope.jsURL}partner-tripdash.js"></script>

        <title><spring:message code="yt.partner.trippdash.browser.header"/></title>
    </head>
    <body>
        <div id="padding-header" ng-cloak ng-controller="PartnerTripDash">
            <!--MENU-->
            <%@include file="TrippDashMenu.jsp" %>
            <!--END MENU-->

            <div id="trippdash-body">
                <div class="yt-normal-container">
                    <div id="td-db">
                        <div class="bd-ct">
                            <div id="top-info">
                                <ul>
                                    <li>
                                        <div class="aBox" id="o-box">
                                            <div class="b-tt">
                                                <spring:message code="yt.partner.trippdash.statistic.packageOverview.header"/>
                                            </div>
                                            <div class="b-bd">
                                                <div class="center-spinner-block loading" ng-show="tripDash.vars.po.lding">
                                                    <div class="vertical-center-container">
                                                        <div class="center-content">
                                                            <span class="yt-spinner center"></span>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="rows t-rows">
                                                    <ul>
                                                        <li>
                                                            <div class="aI">
                                                                <div class="i-tt">
                                                                    <spring:message code="yt.partner.trippdash.statistic.packageOverview.p1"/>
                                                                </div>
                                                                <div class="i-no">
                                                                    {{tripDash.vars.po.dt.total}} <spring:message code="yt.partner.trippdash.statistic.packageOverview.package.text"/>
                                                                </div>
                                                            </div>
                                                        </li>
                                                        <li>
                                                            <div class="aI">
                                                                <div class="i-tt">
                                                                    <spring:message code="yt.partner.trippdash.statistic.packageOverview.p2"/>
                                                                </div>
                                                                <div class="i-no">
                                                                    {{tripDash.vars.po.dt.regular}} <spring:message code="yt.partner.trippdash.statistic.packageOverview.package.text"/>
                                                                </div>
                                                            </div>
                                                        </li>
                                                        <li>
                                                            <div class="aI">
                                                                <div class="i-tt">
                                                                    <spring:message code="yt.partner.trippdash.statistic.packageOverview.p3"/>
                                                                </div>
                                                                <div class="i-no">
                                                                    {{tripDash.vars.po.dt.opened}} <spring:message code="yt.partner.trippdash.statistic.packageOverview.package.text"/>
                                                                </div>
                                                            </div>
                                                        </li>
                                                    </ul>
                                                </div>
                                            </div>
                                        </div>
                                    </li><li>
                                        <div class="aBox" id="bs-box">
                                            <div class="b-tt">
                                                <spring:message code="yt.partner.trippdash.statistic.bestSelling.header"/> ({{tripDash.vars.bs.crd}})
                                            </div>
                                            <div class="b-bd">
                                                <div class="rows tw-rows">
                                                    <ul>
                                                        <li ng-repeat="pkg in tripDash.vars.bs.dt" >
                                                            <div class="aI"  ng-if="pkg.fnpercent > 0">
                                                                <div class="i-tt">
                                                                    {{$index + 1}}. {{pkg.packageName}}
                                                                </div>
                                                                <div class="i-no">
                                                                    <div class="p-bar">
                                                                        <div class="pc" ng-style="{'width': pkg.fnpercent + '%'}">

                                                                        </div>
                                                                    </div>
                                                                    <div class="no">
                                                                        <span class="l-no">
                                                                            {{pkg.totalQuota}} <spring:message code="yt.partner.trippdash.statistic.bestSelling.p1"/>
                                                                        </span>
                                                                        <span class="c-no">
                                                                            {{pkg.usedQuota}} <spring:message code="yt.partner.trippdash.statistic.bestSelling.p1"/>
                                                                        </span>
                                                                        <div class="p-no">
                                                                            {{pkg.fnpercent}}%
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="aI"  ng-if="!pkg.fnpercent">
                                                                <div class="i-tt">
                                                                    {{$index + 1}}. N/A
                                                                </div>
                                                                <div class="i-no">
                                                                    <div>
                                                                        &nbsp;
                                                                    </div>
                                                                    <div class="no">
                                                                        <div class="p-no">
                                                                            &nbsp;
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </li>

                                                    </ul>
                                                </div>
                                            </div>
                                        </div>
                                    </li><li>
                                        <div class="aBox hg-box">
                                            <div class="b-tt">
                                                <spring:message code="yt.partner.trippdash.statistic.grossing.header"/> ({{tripDash.vars.hg.crd}})
                                            </div>
                                            <div class="b-bd">
                                                <div class="center-spinner-block loading" ng-show="tripDash.vars.hg.lding">
                                                    <div class="vertical-center-container">
                                                        <div class="center-content">
                                                            <span class="yt-spinner center"></span>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="rows t-rows">
                                                    <ul>
                                                        <li ng-repeat="pkg in tripDash.vars.hg.dt">
                                                            <div class="aI" ng-if="pkg.packageID.length">
                                                                <div class="i-tt">
                                                                    {{$index + 1}}. {{pkg.packageName}}
                                                                </div>
                                                                <div class="i-no">
                                                                    <span class="price" >{{pkg.totalPay| currency:"":2}}  <spring:message code="yt.partner.trippdash.statistic.grossing.p1"/></span>
                                                                    <div class="p-id">{{pkg.packageID}}</div>
                                                                </div>
                                                            </div>
                                                            <div class="aI" ng-if="!pkg.packageID.length">
                                                                <div class="i-tt">
                                                                    {{$index + 1}}. N/A
                                                                </div>
                                                                <div class="i-no">
                                                                    &nbsp;
                                                                </div>
                                                            </div>
                                                        </li>
                                                    </ul>
                                                </div>
                                            </div>
                                        </div>
                                    </li><li>
                                        <div class="aBox hg-box">
                                            <div class="b-tt">
                                                <spring:message code="yt.partner.trippdash.statistic.total.header"/>
                                            </div>
                                            <div class="b-bd">
                                                <div class="center-spinner-block loading" ng-show="tripDash.vars.ts.lding">
                                                    <div class="vertical-center-container">
                                                        <div class="center-content">
                                                            <span class="yt-spinner center"></span>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="rows t-rows">
                                                    <ul>
                                                        <li ng-repeat="pkg in tripDash.vars.ts.dt">
                                                            <div class="aI" ng-if="pkg.packageID.length">
                                                                <div class="i-tt">
                                                                    {{$index + 1}}. {{pkg.packageName}}
                                                                </div>
                                                                <div class="i-no">
                                                                    <span class="price">{{pkg.totalPay| currency:"":2}}  <spring:message code="yt.partner.trippdash.statistic.grossing.p1"/></span>
                                                                    <div class="p-id">{{pkg.packageID}}</div>
                                                                </div>
                                                            </div>
                                                            <div class="aI" ng-if="!pkg.packageID.length">
                                                                <div class="i-tt">
                                                                    {{$index + 1}}. N/A
                                                                </div>
                                                                <div class="i-no">
                                                                    &nbsp;
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

                            <div id="ut-tb">
                                <div class="center-spinner-block loading" ng-show="tripDash.vars.up.lding">
                                    <div class="vertical-center-container">
                                        <div class="center-content">
                                            <span class="yt-spinner center"></span>
                                        </div>
                                    </div>
                                </div>
                                <div class="ut-tt">
                                    <spring:message code="yt.partner.trippdash.tbl.upcoming.header"/> ({{tripDash.vars.up.DATA.crd}})
                                </div>
                                <div class="tbl">
                                    <div class="row h-row">
                                        <div class="cols">
                                            <ul class="clearfix">
                                                <li class="aCol bo-col">
                                                    <div class="col-t">
                                                        <div class="vertical-center-container">
                                                            <div class="center-content">
                                                                <spring:message code="yt.partner.trippdash.tbl.upcoming.col1"/>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </li>
                                                <li class="aCol bd-col">
                                                    <div class="col-t">
                                                        <div class="vertical-center-container">
                                                            <div class="center-content">
                                                                <spring:message code="yt.partner.trippdash.tbl.upcoming.col2"/>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </li>
                                                <li class="aCol st-col">
                                                    <div class="col-t">
                                                        <div class="vertical-center-container">
                                                            <div class="center-content">
                                                                <spring:message code="yt.partner.trippdash.tbl.upcoming.col3.h1"/><br/>
                                                                [<spring:message code="yt.partner.trippdash.tbl.upcoming.col3.h2"/>]
                                                            </div>
                                                        </div>
                                                    </div>
                                                </li>
                                                <li class="aCol tp-col">
                                                    <div class="col-t">
                                                        <div class="vertical-center-container">
                                                            <div class="center-content">
                                                                <spring:message code="yt.partner.trippdash.tbl.upcoming.col4"/>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </li>
                                                <li class="aCol pn-col">
                                                    <div class="col-t">
                                                        <div class="vertical-center-container">
                                                            <div class="center-content">
                                                                <spring:message code="yt.partner.trippdash.tbl.upcoming.col5"/>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </li>
                                                <li class="aCol qty-col">
                                                    <div class="col-t">
                                                        <div class="vertical-center-container">
                                                            <div class="center-content">
                                                                <spring:message code="yt.partner.trippdash.tbl.upcoming.col6"/>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </li>
                                                <li class="aCol cn-col">
                                                    <div class="col-t">
                                                        <div class="vertical-center-container">
                                                            <div class="center-content">
                                                                <spring:message code="yt.partner.trippdash.tbl.upcoming.col7"/>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </li>
                                            </ul>
                                        </div>
                                    </div>

                                    <div class="row o-row" ng-repeat="up in tripDash.vars.up.dt">
                                        <div class="cols">
                                            <ul class="clearfix">
                                                <li class="aCol bo-col">
                                                    <div class="col-t">
                                                        <span class="link">{{up.bookingNo}}</span>
                                                    </div>
                                                </li>
                                                <li class="aCol bd-col">
                                                    <div class="col-t">
                                                        {{up.bookingDateStr}}
                                                    </div>
                                                </li>
                                                <li class="aCol st-col">
                                                    <div class="col-t">
                                                        <div class="vertical-center-container">
                                                            <div class="center-content">
                                                                {{up.startTimeStr.length?up.startTimeStr : "N/A"}} <br/>
                                                                <span ng-if="up.durationType !== tripDash.vars.up.DATA.dmin"> 
                                                                    [ {{up.duration}} <span ng-if="up.durationType === tripDash.vars.up.DATA.dd">
                                                                        <spring:message code="yt.partner.trippdash.tbl.upcoming.day.text"/>
                                                                    </span> 
                                                                    <span ng-if="up.durationType === tripDash.vars.up.DATA.dw">
                                                                        <spring:message code="yt.partner.trippdash.tbl.upcoming.week.text"/>
                                                                    </span> 
                                                                    <span ng-if="up.durationType === tripDash.vars.up.DATA.dmonth">
                                                                        <spring:message code="yt.partner.trippdash.tbl.upcoming.month.text"/>
                                                                    </span> ]
                                                                </span> 
                                                                <span ng-if="up.durationType === tripDash.vars.up.DATA.dmin"> 
                                                                    [ {{up.duration}}<spring:message code="yt.partner.trippdash.tbl.upcoming.hour.text"/> 
                                                                    <span ng-if="up.durationMinute > 0">{{up.durationMinute}}<spring:message code="yt.partner.trippdash.tbl.upcoming.min.text"/></span> ]
                                                                </span> 
                                                            </div>
                                                        </div>
                                                    </div>
                                                </li>
                                                <li class="aCol tp-col">
                                                    <div class="col-t">
                                                        {{up.billingFirstName}}, {{up.billingLastName}}
                                                    </div>
                                                </li>
                                                <li class="aCol pn-col">
                                                    <div class="col-t">
                                                        {{up.packageName}}
                                                    </div>
                                                </li>
                                                <li class="aCol qty-col">
                                                    <div class="col-t">
                                                        <div class="vertical-center-container">
                                                            <div class="center-content">
                                                                <span class="qty-no">{{up.bookedQty}}</span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </li>
                                                <li class="aCol cn-col">
                                                    <div class="col-t">
                                                        <div class="vertical-center-container">
                                                            <div class="center-content">
                                                                <div class="cn-ico" ng-class="{'off':up.offlineBooking}">
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
                                            </ul>
                                        </div>
                                    </div>


                                </div>

                                <div class="no-rows" ng-if="!tripDash.vars.up.dt.length">                                  
                                    <spring:message code="yt.partner.trippdash.tbl.upcoming.noBooking"/>
                                </div>

                                <div class="paging" ng-if="tripDash.vars.up.dt.length">
                                    <ul>
                                        <li>
                                            <div class="page">
                                                {{tripDash.vars.up.pageRange}} <spring:message code="yt.partner.trippdash.tbl.upcoming.paging.of.text"/> {{tripDash.vars.up.total}}
                                            </div>
                                        </li>
                                        <li>
                                            <div class="ctrls">
                                                <ul class="clearfix">
                                                    <li>
                                                        <div class="aCtrl">
                                                            <span class="btn prv" ng-click="tripDash.funcs.prevU()"></span>
                                                        </div>
                                                    </li>
                                                    <li>
                                                        <div class="aCtrl">
                                                            <span class="btn nxt" ng-click="tripDash.funcs.nextU()"></span>
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
            </div>

            <%@include file="../common/header.jsp" %>
        </div>
        <script>
            var limit = 2;
        </script>
    </body
</html>
