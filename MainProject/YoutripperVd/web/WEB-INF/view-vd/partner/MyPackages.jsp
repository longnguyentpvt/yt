<%-- 
    Document   : RegularPackages
    Created on : Jan 22, 2018, 12:30:22 PM
    Author     : nickn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="${pageContext.response.locale}" currency="${sessionScope.ytCurrency}" ng-app="youtripper" ng-controller="YoutripperController">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="../common/commonhead.jsp" %>

        <link rel="stylesheet" href="${requestScope.cssURL}partner-trippdash-menu.css" >
        <link rel="stylesheet" href="${requestScope.cssURL}partner-regular-packages.css" >

        <script src="${requestScope.jsURL}partner-regulars.js"></script>

        <title>Youtripper - Regular Packages</title>
    </head>
    <body>
        <div id="padding-header" ng-controller="PartnerRegulars" ng-cloak>

            <div id="partner-packages">
                <!--MENU-->
                <%@include file="TrippDashMenu.jsp" %>
                <!--END MENU-->

                <div id="trippdash-body">
                    <div class="yt-normal-container">
                        <!--FILTER-->
                        <div class="filter">
                            <div class="btn">
                                <a href="" class="yt-btn red-btn"
                                   ng-click="registration.funcs.registration()">
                                    Create Package
                                </a>
                            </div>

                            <div class="options">
                                <ul>
                                    <li>
                                        <div class="aOption">
                                            <input type="text" ng-model="filter.vars.searchTxt" ng-change="regulars.funcs.filter()" class="yt-input search-input" placeholder="Search for Package"/>
                                        </div>
                                    </li><li>
                                        <div class="aOption">
                                            <div class="ddl-input">
                                                <select name="" ng-model="filter.vars.sortCri" class="yt-input" ng-change="regulars.funcs.filter()">
                                                    <option value="alphabetically">Sort by Alphabetically</option>
                                                    <option value="packageStatus">Sort by Package Status</option>
                                                    <option value="oldest">Sort by Oldest Package First</option>
                                                    <option value="youngest">Sort by Youngest Package First</option>
                                                    <option value="highestPrice">Sort by Highest Price First</option>
                                                    <option value="lowestPrice">Sort by Lowest Price First</option>
                                                </select>
                                            </div>
                                        </div>
                                    </li><li>
                                        <div class="aOption">
                                            <div class="ddl-input">
                                                <select name="" ng-model="filter.vars.statusCri" class="yt-input" ng-change="regulars.funcs.filter()">
                                                    <option value="">All Packages</option>
                                                    <option value="approved">Approved Only</option>
                                                    <option value="creating">Creating in Process Only</option>
                                                    <option value="editing">Editing in Process Only</option>
                                                    <option value="pending">Pending Approval Only</option>
                                                    <option value="fail">Fail Approval Only</option>
                                                    <option value="not-operating">Not Operating Only</option>
                                                    <option value="deleting">Deleting in Process Only</option>
                                                </select>
                                            </div>
                                        </div>
                                    </li>
                                </ul>
                            </div>
                        </div>
                        <!--END FILTER-->

                        <!--PACKAGES-->
                        <div class="all-packages" style="position: relative">
                            <ul class="clearfix">
                                <li ng-repeat="aPackage in regulars.vars.ytPackages">
                                    <div class="aPackage" 
                                         ng-class="aPackage.pkgStatus">
                                        <div class="aPkg-bg"></div>
                                        <img ng-if="aPackage.portraint.length" ng-src="{{aPackage.portrait}}"/>
                                        <div class="status">
                                            <span class="creating name">Creating In Process</span>
                                            <span class="pending name">Pending Approval</span>
                                            <span class="approved name">Approved</span>
                                            <span class="fail name">Fail Approval</span>
                                            <span class="not-operating name">Not Operating</span>
                                            <span class="editing name">Editing In Process</span>
                                            <span class="deleting name">Deleting In Process</span>
                                            <div class="options">
                                                <div class="icon">
                                                    <div class="vertical-center-container">
                                                        <div class="center-content">
                                                            <svg width="15px" height="12px" viewBox="0 0 15 12" class="svg">
                                                            <line style="fill:none;stroke:#FFFFFF;stroke-width:2;stroke-miterlimit:10;" x1="0" y1="1" x2="15" y2="1"/>
                                                            <line style="fill:none;stroke:#FFFFFF;stroke-width:2;stroke-miterlimit:10;" x1="0" y1="6" x2="15" y2="6"/>
                                                            <line style="fill:none;stroke:#FFFFFF;stroke-width:2;stroke-miterlimit:10;" x1="0" y1="11" x2="15" y2="11"/>
                                                            </svg>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="sub">
                                                    <ul>
                                                        <li class="creating editing fail approved" ng-click="regulars.funcs.edit(aPackage)">
                                                            <div class="aSub">
                                                                <div class="svg">
                                                                    <svg width="15px" height="15px" viewBox="0 0 15 15" class="svg">
                                                                    <polygon class="icon" points="0,15 3.9,14 1,11.1 "/>
                                                                    <polygon class="icon" points="1.7,9.4 5.6,13.3 12.8,6.1 8.9,2.2 	"/>
                                                                    <path class="icon" d="M14.2,0.8c-1-1-2.8-1-3.8,0L10.1,1L14,4.9l0.2-0.2C15.3,3.6,15.3,1.8,14.2,0.8z"/>
                                                                    </svg>
                                                                </div>
                                                                <span class="txt">
                                                                    Edit
                                                                </span>
                                                            </div>
                                                        </li>
                                                        <li class="pending approved not-operating" ng-click="POPUP.openPopup(regulars.vars.clonePopup); regulars.vars.clonePopup.selectedPackage = package">
                                                            <div class="aSub">
                                                                <div class="svg">
                                                                    <svg width="15.5px" height="15.4px" viewBox="0 0 15.5 15.4" class="svg">
                                                                    <rect x="5.5" y="5.5" class="icon" width="10" height="9.9"/>
                                                                    <polygon class="icon" points="4,4 10,4 10,0 0,0 0,10 4,10 	"/>
                                                                    </svg>
                                                                </div>
                                                                <span class="txt">
                                                                    Clone
                                                                </span>
                                                            </div>
                                                        </li>
                                                        <li class="approved off" ng-click="regulars.funcs.turnOff(package.packageID)">
                                                            <div class="aSub">
                                                                <div class="svg">
                                                                    <svg width="15px" height="15px" viewBox="0 0 15 15" lass="svg">
                                                                    <path class="icon" d="M7.5,15c-2,0-3.9-0.8-5.3-2.2C-0.7,9.9-0.7,5.1,2.1,2.2c0.3-0.3,0.9-0.3,1.2,0s0.3,0.9,0,1.2
                                                                          c-2.2,2.2-2.1,5.9,0.1,8.1c1.1,1.1,2.5,1.7,4.1,1.7c1.5,0,3-0.6,4-1.7c2.2-2.2,2.2-5.9,0.1-8.1c-0.3-0.3-0.3-0.9,0-1.2
                                                                          c0.3-0.3,0.9-0.3,1.2,0c2.8,2.8,2.8,7.7-0.1,10.6C11.4,14.2,9.5,15,7.5,15z"/>
                                                                    <path class="icon" d="M6.9,8.8c-0.5,0-1-0.4-1-0.9V0.9c0-0.5,0.5-0.9,1-0.9s1,0.4,1,0.9v7.1C7.9,8.4,7.4,8.8,6.9,8.8
                                                                          z"/>
                                                                    </svg>
                                                                </div>
                                                                <span class="txt">
                                                                    Turn off
                                                                </span>
                                                            </div>
                                                        </li>                                                    
                                                        <li class="not-operating" ng-click="regulars.funcs.turnOn(package.packageID)">
                                                            <div class="aSub">
                                                                <div class="svg">
                                                                    <svg  width="15px" height="15px" viewBox="0 0 15 15" class="svg">
                                                                    <path class="icon" d="M7.5,15c-2,0-3.9-0.8-5.3-2.2C-0.7,9.9-0.7,5.1,2.1,2.2c0.3-0.3,0.9-0.3,1.2,0s0.3,0.9,0,1.2
                                                                          c-2.2,2.2-2.1,5.9,0.1,8.1c1.1,1.1,2.5,1.7,4.1,1.7c1.5,0,3-0.6,4-1.7c2.2-2.2,2.2-5.9,0.1-8.1c-0.3-0.3-0.3-0.9,0-1.2
                                                                          c0.3-0.3,0.9-0.3,1.2,0c2.8,2.8,2.8,7.7-0.1,10.6C11.4,14.2,9.5,15,7.5,15z"/>
                                                                    <path class="icon" d="M6.9,8.8c-0.5,0-1-0.4-1-0.9V0.9c0-0.5,0.5-0.9,1-0.9s1,0.4,1,0.9v7.1C7.9,8.4,7.4,8.8,6.9,8.8
                                                                          z"/>
                                                                    </svg>
                                                                </div>
                                                                <span class="txt">
                                                                    Turn on
                                                                </span>
                                                            </div>
                                                        </li>
                                                        <li class="approved not-operating" ng-click="POPUP.openPopup(regulars.vars.deletingPopup); regulars.vars.deletingPopup.selectedPackage = package.packageID">
                                                            <div class="aSub">
                                                                <div class="svg">
                                                                    <svg width="11px" height="15.2px" viewBox="0 0 11 15.2" class="svg">
                                                                    <path class="icon" d="M8.5,15.2h-6c-1.1,0-2-0.9-2-2v-8h10v8C10.5,14.3,9.6,15.2,8.5,15.2z"/>
                                                                    <path class="icon" d="M2,1.8h7c1.1,0,2,0.9,2,2v0.4H0l0-0.4C0,2.7,0.9,1.8,2,1.8z"/>
                                                                    <path class="icon" d="M4.5,0l2.1,0c0.5,0,1,0.4,1,1v0h-4v0C3.5,0.4,3.9,0,4.5,0z"/>
                                                                    <path class="icon" d="M5.4,12.7c-0.3,0-0.5-0.2-0.5-0.5v-4c0-0.3,0.2-0.5,0.5-0.5s0.5,0.2,0.5,0.5v4
                                                                          C5.9,12.5,5.7,12.7,5.4,12.7z"/>
                                                                    <path class="icon" d="M7.4,12.7c-0.3,0-0.5-0.2-0.5-0.5v-4c0-0.3,0.2-0.5,0.5-0.5s0.5,0.2,0.5,0.5v4
                                                                          C7.9,12.5,7.7,12.7,7.4,12.7z"/>
                                                                    <path class="icon" d="M3.4,12.7c-0.3,0-0.5-0.2-0.5-0.5v-4c0-0.3,0.2-0.5,0.5-0.5s0.5,0.2,0.5,0.5v4
                                                                          C3.9,12.5,3.7,12.7,3.4,12.7z"/>
                                                                    </svg>
                                                                </div>
                                                                <span class="txt">
                                                                    Delete
                                                                </span>
                                                            </div>
                                                        </li>
                                                        <li class="editing" ng-click="regulars.funcs.cancelEditing(package.tempPackageID)">
                                                            <div class="aSub">
                                                                <span class="txt">
                                                                    Cancel
                                                                </span>
                                                            </div>
                                                        </li>
                                                        <li class="deleting" ng-click="regulars.funcs.cancelDeleting(package.packageID)">
                                                            <div class="aSub">
                                                                <span class="txt">
                                                                    Cancel
                                                                </span>
                                                            </div>
                                                        </li>
                                                    </ul>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="price">
                                            <ul>
                                                <li class="clearfix" ng-repeat="(currencyCode, currency) in youtripper.vars.CURS"
                                                    ng-init="aPrice = regulars.funcs.getPrice(aPackage.prices, currencyCode)">
                                                    <div class="aPrice" 
                                                         ng-class="{'promotional' :aPrice.promotionPercent > 0}">
                                                        <div class="promotion"> 
                                                            <div class="vertical-center-container">
                                                                <div class="center-content">
                                                                    <div class="percent bold-txt">
                                                                        {{aPrice.promotionPercent}}%
                                                                    </div>
                                                                    <div class="small-txt label">
                                                                        Discount
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div><div class="number">
                                                            <div class="vertical-center-container">
                                                                <div class="center-content">
                                                                    <div class="detail">
                                                                        <div class="old small-txt">
                                                                            {{currency.sym}} {{aPrice.pkgPrice| currency:"":2}}
                                                                        </div>
                                                                        <div class="main bold-txt med-txt">
                                                                            {{currency.sym}} {{aPrice.paidPrice| currency:"":2}}
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </li>
                                            </ul>
                                        </div>

                                        <div class="info">
                                            <div class="name">
                                                <a>
                                                    {{aPackage.name}}
                                                </a>
                                            </div>
                                            <div class="sub">
                                                <ul>
                                                    <li>
                                                        <div class="aSub small-txt solds">
                                                            {{aPackage.noSolds}} Sold(s)
                                                        </div>
                                                    </li>
                                                    <li>
                                                        <div class="aSub small-txt rates">
                                                            <ul>
                                                                <li ng-repeat="i in [0, 1, 2, 3, 4]">
                                                                    <div class="rate" ng-class="{'active':(i < aPackage.rate)}">
                                                                        <svg width="10.4px" height="10px" viewBox="0 0 10.4 10" class="svg">
                                                                        <path class="icon" d="M5.2,0.7l1.1,2.2c0.2,0.4,0.6,0.6,1,0.7L9.7,4L8,5.7C7.7,6,7.5,6.4,7.6,6.9L8,9.3L5.8,8.2
                                                                              C5.6,8.1,5.4,8,5.2,8S4.8,8,4.6,8.2L2.4,9.3l0.4-2.4C2.9,6.4,2.7,6,2.5,5.7L0.7,4l2.4-0.4c0.4-0.1,0.8-0.3,1-0.7L5.2,0.7 M5.2,0
                                                                              C5,0,4.8,0.1,4.7,0.4L3.5,2.6C3.4,2.8,3.2,3,3,3L0.5,3.3C0,3.4-0.2,4,0.2,4.4L2,6.2c0.2,0.2,0.2,0.4,0.2,0.6L1.7,9.2
                                                                              C1.7,9.7,2,10,2.4,10c0.1,0,0.2,0,0.3-0.1l2.2-1.2c0.1,0,0.2-0.1,0.3-0.1c0.1,0,0.2,0,0.3,0.1l2.2,1.2C7.8,10,7.9,10,8,10
                                                                              c0.4,0,0.7-0.3,0.6-0.8L8.2,6.8c0-0.2,0-0.4,0.2-0.6l1.8-1.8c0.4-0.4,0.2-1-0.4-1.1L7.3,3C7.1,3,6.9,2.8,6.8,2.6L5.7,0.4
                                                                              C5.7,0.1,5.4,0,5.2,0L5.2,0z"></path>
                                                                        <path class="icon fill" d="M5.2,0C5,0,4.8,0.1,4.7,0.4L3.5,2.6C3.5,2.8,3.3,3,3.1,3L0.5,3.4C0,3.4-0.2,4.1,0.2,4.4L2,6.2
                                                                              c0.2,0.2,0.2,0.4,0.2,0.6L1.7,9.2C1.7,9.6,2,10,2.4,10c0.1,0,0.2,0,0.3-0.1l2.2-1.2c0.1,0,0.2-0.1,0.3-0.1c0.1,0,0.2,0,0.3,0.1
                                                                              l2.2,1.2C7.8,10,7.9,10,8,10c0.4,0,0.7-0.3,0.6-0.8L8.2,6.8c0-0.2,0-0.4,0.2-0.6l1.8-1.8c0.4-0.4,0.2-1-0.4-1.1L7.3,3
                                                                              C7.1,3,6.9,2.8,6.8,2.6L5.7,0.4C5.7,0.1,5.5,0,5.2,0L5.2,0z"></path>
                                                                        </svg>
                                                                    </div>
                                                                </li>                                                            
                                                            </ul>
                                                        </div>
                                                    </li>
                                                    <li>
                                                        <div class="aSub small-txt reviews">
                                                            {{aPackage.noReviews}} Review(s)
                                                        </div>
                                                    </li>
                                                </ul>
                                            </div>
                                        </div>
                                    </div>
                                </li>
                            </ul>

                            <div class="center-spinner-block" ng-class="{'loading' : filter.vars.loading}">
                                <div class="vertical-center-container">
                                    <div class="center-content">
                                        <span class="yt-spinner center"></span>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!--END PACKAGES-->
                    </div>
                </div>
            </div>

            <div class="center-spinner-block full" ng-class="{'loading' : registration.vars.loading}">
                <div class="vertical-center-container">
                    <div class="center-content">
                        <span class="yt-spinner center"></span>
                    </div>
                </div>
            </div>

            <%@include file="../common/header.jsp" %>
        </div>
    </body>
    <script>
        var servingType = '${requestScope.servingType}';
    </script>
</html>
