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
        <script src="${requestScope.jsURL}tripper-booking.js"></script>

        <link rel="stylesheet" href="${requestScope.cssURL}tripper-account-setting.css" >
        <link rel="stylesheet" href="${requestScope.cssURL}tripper-account-profile.css" >
        <link rel="stylesheet" href="${requestScope.cssURL}tripper-booking-management.css" >
        <title>Tripper Booking Management - youtripper</title>
    </head>
    <body id="padding-header" ng-cloak ng-init="hes = 'booking'">
        <%@include file="AccountSettingMenu.jsp" %>

        <div id="account-body" ng-controller="MyBooking">
            <div class="clearfix">
                <div class="yt-normal-container">
                    <div class="body-l">
                        <%@include file="TripperAccountCommon.jsp" %>
                    </div>
                    <div class="body-r">
                        <div id="tp-bookings">
                            <div id="bm-filter">
                                <ul>
                                    <li>
                                        <div class="af">
                                            <div class="yt-input-ico close-input">

                                                <input ng-keypress="bking.funcs.sbbk($event)"
                                                       ng-change="bking.funcs.bts()"
                                                       ng-model="bking.vars.filter.bookingCode"
                                                       ytmaxlength="10" 
                                                       type="text" class="yt-input" placeholder="Booking Code"/>
                                                <span class="close-btn" 
                                                      ng-click="bking.funcs.cbkn()"
                                                      ng-show="bking.vars.filter.bookingCode.length"></span>
                                                <div class="ico" ng-show="!bking.vars.filter.bookingCode.length">
                                                    <svg height="25px" viewBox="0 0 25.2 25">
                                                    <path style="fill: #555555" d="M22.5,2.8c-3.7-3.7-9.6-3.7-13.3,0s-3.7,9.5,0,13.2s9.6,3.7,13.3,0
                                                          C26.2,12.3,26.2,6.4,22.5,2.8z M21.1,14.7c-2.9,3-7.7,3-10.6,0c-3-3-3-7.7,0-10.6c2.9-2.9,7.7-3,10.6,0C24.1,7,24.1,11.7,21.1,14.7
                                                          z"></path>
                                                    <path style="fill: #555555" d="M6.4,15.9l-5.8,5.7c-0.8,0.8-0.8,2,0,2.8c0.8,0.8,2.1,0.8,2.8,0l5.8-5.7c0.8-0.8,0.8-2,0-2.8
                                                          C8.4,15.2,7.2,15.2,6.4,15.9z"></path>
                                                    </svg>
                                                </div>
                                            </div>
                                        </div>
                                    </li><li>
                                        <div class="af">
                                            <div class="ddl-input">
                                                <select class="yt-input">
                                                    <option value="">
                                                        Package Status
                                                    </option>
                                                </select>
                                            </div>
                                        </div>
                                    </li>
                                </ul>
                            </div>

                            <div id="bks">
                                <ul>
                                    <li ng-repeat="bk in bking.vars.dt">
                                        <div class="abk" ng-class="{'f-bk' : !bk.completedPayment}">
                                            <div class="bk-h">
                                                <div class="h-r">
                                                    <div class="ops">
                                                        <div class="hbg">
                                                            <svg width="15px" height="12px" viewBox="0 0 15 12">
                                                            <line style="fill:none;stroke:#FFFFFF;stroke-width:2;stroke-miterlimit:10;" x1="0" y1="1" x2="15" y2="1"/>
                                                            <line style="fill:none;stroke:#FFFFFF;stroke-width:2;stroke-miterlimit:10;" x1="0" y1="6" x2="15" y2="6"/>
                                                            <line style="fill:none;stroke:#FFFFFF;stroke-width:2;stroke-miterlimit:10;" x1="0" y1="11" x2="15" y2="11"/>
                                                            </svg>
                                                        </div>
                                                        <div class="mn">
                                                            <ul>
                                                                <li>
                                                                    <div class="am">
                                                                        Booking Detail
                                                                    </div>
                                                                </li>
                                                                <li class="iv-m" ng-if="bk.invoiceFile.length" 
                                                                    ng-click="bking.funcs.openInv(bk.invoiceFile)">
                                                                    <div class="am" >
                                                                        Invoice
                                                                    </div>
                                                                </li>
                                                            </ul>
                                                        </div>
                                                    </div>
                                                    <span class="bd-txt">
                                                        Booking Date: {{CALENDAR.funcs.cMiToDDMMyyyy(bk.bookingDateTime)}}
                                                    </span>
                                                </div>
                                                <div class="orderNo">
                                                    Booking No. {{bk.bookingCode}}
                                                </div>
                                            </div>
                                            <div class="bk-b">
                                                <div class="bk-info">
                                                    <div class="thumb">
                                                        <div class="bg">
                                                        </div>
                                                    </div>
                                                    <div class="info">
                                                        <div class="ar">
                                                            <div class="lb">
                                                                Package Title
                                                            </div>
                                                            <div class="vl">
                                                                {{bk.packageName}}
                                                            </div>
                                                        </div>
                                                        <div class="ar stt-r">
                                                            <div class="lb">
                                                                Payment Status
                                                            </div>
                                                            <div class="vl">
                                                                {{bk.completedPayment? "Success":"Fail"}}
                                                            </div>
                                                        </div>
                                                        <div class="ar tt-r">
                                                            <div class="lb">
                                                                Total
                                                            </div>
                                                            <div class="vl">
                                                                THB {{bk.amount|currency:"":2}}
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </li>
                                    <li ng-if="bking.vars.dt.length"> 
                                        <div id="paging">
                                            <ul>
                                                <li>
                                                    <div class="page">
                                                        {{bking.vars.paging.pageRange}} of {{bking.vars.paging.total}}
                                                    </div>
                                                </li>
                                                <li>
                                                    <div class="ctrls">
                                                        <ul class="clearfix">
                                                            <li>
                                                                <div class="aCtrl">
                                                                    <span class="btn prv" ng-class="{'dat':bking.vars.filter.page === 1}"
                                                                          ng-click="bking.funcs.prevU()"
                                                                          ></span>
                                                                </div>
                                                            </li>
                                                            <li>
                                                                <div class="aCtrl">
                                                                    <span class="btn nxt" ng-class="{'dat':bking.vars.filter.page === bking.vars.paging.totalPage}"
                                                                          ng-click="bking.funcs.nextU()"
                                                                          ></span>
                                                                </div>
                                                            </li>
                                                        </ul>
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
            <!--INVOICE POPUP-->
            <div class="yt-popup" ng-class="{'active': bking.vars.popup.active}">
                <div class="vertical-center-container">
                    <div class="center-content" ng-click="bking.vars.popup.invoiceUrl = null; POPUP.closePopup(bking.vars.popup, $event)">
                        <div class="popup">
                            <div class="body">
                                <span class="close-btn" ng-click="bking.vars.popup.invoiceUrl = null; POPUP.closePopup(bking.vars.popup, $event)"></span>
                                <div class="container">
                                    <div class="row">
                                        <iframe height="500px;" width="900px;" 
                                                ng-src="{{trustSrc(bking.vars.popup.invoiceUrl)}}">
                                        </iframe>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
            <!--END INVOICE POPUP-->
        </div>
        <script>
            var limit = 3;
        </script>
        <%@include file="../common/header.jsp" %>
    </body>
</html>
