<%-- 
    Document   : PackageDetail
    Created on : Jul 11, 2018, 11:50:03 AM
    Author     : nickn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="${pageContext.response.locale}" currency="${sessionScope.ytCurrency}" ng-app="youtripper" ng-controller="YoutripperController">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="commonhead.jsp" %>

        <script src="${requestScope.jsURL}package-detail.js"></script>
        <title>Package Detail - Youtripper</title>
    </head>
    <body ng-cloak>
        <div id="padding-header">
            <div id="pkg-dt" ng-controller="PackageDetail">
                <div class="yt-normal-container">
                    <c:choose>
                        <c:when test="${requestScope.dt != null}">
                            Package ID : ${requestScope.dt.packageID} <a href="" ng-click="dt.funcs.atwl()"> Add to wish list </a><br/>    
                            Package Name : ${requestScope.dt.packageName}<br/>
                            Package Price : ${requestScope.dt.price}<br/>
                            Currency : ${requestScope.dt.currencyCode}<br/>
                            <c:if test="${!requestScope.dt.openedPkg}">
                                TripDate<br/>
                                <div class="in" style="width: 300px">
                                    <div class="search-input calendar-input">
                                        <div class="yt-calendar">
                                            <div class="common">
                                                <div class="month semi-bold-txt">
                                                    <span class="ctrl prv-ctrl" ng-show="dt.vars.td.cld.lav" ng-click="dt.vars.td.cld = CALENDAR.funcs.prvM(dt.vars.td.cld)"></span>
                                                    <span class="ctrl nxt-ctrl" ng-show="dt.vars.td.cld.rav" ng-click="dt.vars.td.cld = CALENDAR.funcs.nxtM(dt.vars.td.cld)"></span>
                                                    {{dt.vars.td.cld["moT"]}} {{dt.vars.td.cld["yeT"]}}
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
                                                    <li ng-repeat="ad in dt.vars.td.cld.dates" ng-class="{
                                                            'disable' : ad.ty === 'out', 'active' : ad.ty === 'mo',
                                                                    'today' : ad.ty === 'to', 'in' : ad.ty === 'in'}">
                                                        <span ng-click="dt.funcs.slsd($index)">
                                                            {{ad.txt}}
                                                        </span>
                                                    </li>
                                                </ul>
                                            </div>
                                        </div>
                                        <input type="text" name="" value="" readonly="readonly" placeholder="Serving Date" 
                                               style="width: 300px;"
                                               class="yt-input" ng-model="dt.vars.td.txt"
                                               ng-click="dt.vars.td.cld = CALENDAR.funcs.getCa($event, dt.vars.cd, dt.vars.td.ml, null, dt.vars.td.lml, null)">
                                    </div>
                                </div>
                            </c:if>
                            No Packages<br/>
                            <input type="number" placeholder="Quota" class="yt-input" style="width: 300px" ng-model="dt.vars.npkgs" number-str-input/>
                            <br/>
                            <button class="yt-btn red-btn" style="width: 300px" ng-click="dt.funcs.book()">Check out</button>
                        </c:when>
                        <c:otherwise>
                            This package does not support this language.
                        </c:otherwise>
                    </c:choose>
                </div>

                <form id="co-f" method="POST" action="/checkout-review" style="display: none">
                    <input type="text" name="tid" ng-model="dt.vars.tid"/>
                    <input type="submit" value="Submit"/>
                </form>
            </div>
            <%@include file="header.jsp" %>
        </div>
    </body>
    <script>
        var pid = '${requestScope.dt.packageID}';
    </script>
</html>
