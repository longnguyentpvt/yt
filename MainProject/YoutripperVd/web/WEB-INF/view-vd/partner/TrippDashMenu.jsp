<%-- 
    Document   : TrippDashMenu
    Created on : Jan 22, 2018, 4:25:07 PM
    Author     : nickn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div id="trippdash-menu">
    <div class="yt-normal-container">
        <ul class="clearfix menu">
            <li>
                <div class="aMenu" ng-class="{'active' : trippdashSection === 'regular' || trippdashSection === 'open-timed'}">
                    <div class="name">
                        <a href="">
                            <svg width="20px" height="17px" viewBox="0 0 20 17" class="svg">
                            <path id="XMLID_16_" class="icon" d="M20,0H0v6h1v11h18V6h1V0z M18,4h-5V2h5V4z M2,2h5v2H2V2z M17,15H3V6h14V15z"/>
                            </svg>
                            <spring:message code="yt.partner.common.header.myPackage"/>
                        </a>
                    </div>

                    <div class="sub-menu">
                        <ul>
                            <li>
                                <div class="aSub" ng-class="{'active' : trippdashSection === 'regular'}">
                                    <a href="${sessionScope.hrefLocale}/partner/packages/regular">
                                        <spring:message code="yt.partner.common.subHeader1.myPackage"/>
                                    </a>
                                </div>
                            </li>
                            <li>
                                <div class="aSub" ng-class="{'active' : trippdashSection === 'open-timed'}">
                                    <a href="${sessionScope.hrefLocale}/partner/packages/open-timed">
                                        <spring:message code="yt.partner.common.subHeader2.myPackage"/>
                                    </a>
                                </div>
                            </li>
                        </ul>
                    </div>
                </div>
            </li>
            <li>
                <div class="aMenu"  ng-class="{'active' : trippdashSection === 'b-manager' || trippdashSection === 'b-history'}">
                    <div class="name">
                        <a href="">
                            <svg width="20px" height="20px" viewBox="0 0 20 20" class="svg">
                            <path class="icon" d="M19.3,1.4h-2.1v2.1c0,1.2-1,2.1-2.1,2.1c-1.2,0-2.1-1-2.1-2.1V1.4H7.1v2.1
                                  c0,1.2-1,2.1-2.1,2.1c-1.2,0-2.1-1-2.1-2.1V1.4H0.7C0.3,1.4,0,1.7,0,2.1v17.1C0,19.7,0.3,20,0.7,20h18.6c0.4,0,0.7-0.3,0.7-0.7
                                  V2.1C20,1.7,19.7,1.4,19.3,1.4z M17.1,8.6v8.6H2.9v-10h14.3V8.6z"/>
                            <rect x="4.3" y="8.6" class="icon" width="2.9" height="2.9"/>
                            <rect x="8.6" y="8.6" class="icon" width="2.9" height="2.9"/>
                            <rect x="12.9" y="8.6" class="icon" width="2.9" height="2.9"/>
                            <rect x="4.3" y="12.9" class="icon" width="2.9" height="2.9"/>
                            <rect x="8.6" y="12.9" class="icon" width="2.9" height="2.9"/>
                            <rect x="12.9" y="12.9" class="icon" width="2.9" height="2.9"/>
                            <path class="icon" d="M5,4.3c0.4,0,0.7-0.3,0.7-0.7V0.7C5.7,0.3,5.4,0,5,0C4.6,0,4.3,0.3,4.3,0.7v2.9
                                  C4.3,4,4.6,4.3,5,4.3z"/>
                            <path class="icon" d="M15,4.3c0.4,0,0.7-0.3,0.7-0.7V0.7C15.7,0.3,15.4,0,15,0c-0.4,0-0.7,0.3-0.7,0.7v2.9
                                  C14.3,4,14.6,4.3,15,4.3z"/>
                            </svg>
                            <spring:message code="yt.partner.common.header.bookingManagement"/>
                        </a>
                    </div>

                    <div class="sub-menu">
                        <ul>
                            <li>
                                <div class="aSub" ng-class="{'active' : trippdashSection === 'b-manager'}">
                                    <a href="${sessionScope.hrefLocale}/partner/booking-manager">
                                        <spring:message code="yt.partner.common.subHeader1.bookingManagement"/>
                                    </a>
                                </div>
                            </li>
                            <li>
                                <div class="aSub" ng-class="{'active': trippdashSection === 'b-history'}">
                                    <a href="${sessionScope.hrefLocale}/partner/booking-history">
                                        <spring:message code="yt.partner.common.subHeader2.bookingManagement"/>
                                    </a>
                                </div>
                            </li>
                        </ul>
                    </div>
                </div>
            </li>
            <li>
                <div class="aMenu"  ng-class="{'active' : trippdash.vars.menu.sale.active}">
                    <div class="name">
                        <a href="">
                            <svg width="20px" height="15.8px" viewBox="0 0 20 15.8" class="svg">
                            <path class="icon" d="M19.5,0h-3.9c-0.5,0-0.7,0.6-0.4,0.9l0.6,0.6C16,1.7,16,2,15.8,2.3l-2.4,2.4
                                  c-0.2,0.2-0.5,0.2-0.7,0l-1.4-1.4c-0.5-0.5-1.4-0.5-1.9,0L5.1,7.4c-0.2,0.2-0.5,0.2-0.7,0L2.3,5.4c-0.2-0.2-0.5-0.2-0.7,0L0.4,6.5
                                  c-0.2,0.2-0.2,0.5,0,0.7l3.4,3.4c0.3,0.3,0.6,0.4,1,0.4s0.7-0.1,1-0.4l4.2-4.2c0.2-0.2,0.5-0.2,0.7,0L12,8c0.5,0.5,1.4,0.5,1.9,0
                                  l3.8-3.8C18,4,18.3,4,18.5,4.2l0.6,0.6C19.4,5.1,20,4.9,20,4.4V0.5C20,0.2,19.8,0,19.5,0z"/>

                            <path class="icon" d="M0,8.4v6.3c0,0.6,0.5,1.1,1.1,1.1h2.1c0.6,0,1.1-0.5,1.1-1.1v-2.6c-0.4-0.1-0.8-0.3-1.2-0.6
                                  L0,8.4z"/>
                            <path class="icon" d="M5.3,12.1v2.6c0,0.6,0.5,1.1,1.1,1.1h2.1c0.6,0,1.1-0.5,1.1-1.1V8.5l-3,3
                                  C6.1,11.8,5.7,12,5.3,12.1z"/>
                            <path class="icon" d="M13,9.4c-0.6,0-1.3-0.3-1.7-0.7l-0.8-0.8v6.8c0,0.6,0.5,1.1,1.1,1.1h2.1
                                  c0.6,0,1.1-0.5,1.1-1.1V8.7l0,0C14.3,9.2,13.7,9.4,13,9.4z"/>
                            <path class="icon" d="M15.8,7.6v7.1c0,0.6,0.5,1.1,1.1,1.1h2.1c0.6,0,1.1-0.5,1.1-1.1V7.2l-1.5-1.5
                                  c-0.2-0.2-0.5-0.2-0.7,0L15.8,7.6z"/>
                            </svg>
                            <spring:message code="yt.partner.common.header.saleReport"/>
                        </a>
                    </div>
                    <div class="sub-menu">
                        <ul>
                            <li>
                                <div class="aSub" ng-class="{'active' : trippdash.vars.menu.sale.sub.chart}">
                                    <a ng-href="{{trippdash.vars.menu.sale.sub.chart ? '' :
                                       '${sessionScope.hrefLocale}/partner/sale/performance'}}">
                                        <spring:message code="yt.partner.common.subHeader1.saleReport"/>
                                    </a>
                                </div>
                            </li>
                            <li>
                                <div class="aSub" ng-class="{'active' : trippdash.vars.menu.sale.sub.report}">
                                    <a ng-href="{{trippdash.vars.menu.sale.sub.report ? '' :
                                       '${sessionScope.hrefLocale}/partner/sale/report'}}">
                                        <spring:message code="yt.partner.common.subHeader2.saleReport"/>
                                    </a>
                                </div>
                            </li>
                        </ul>
                    </div>
                </div>
            </li>
            <li>
                <div class="aMenu"  ng-class="{'active' : trippdashSection === 'payment'}">
                    <div class="name">
                        <a ng-href="${sessionScope.hrefLocale}/partner/partner-payment">
                            <svg width="15px" height="18.8px" viewBox="0 0 15 18.8" class="svg">
                            <path class="icon" d="M13.1,1.5c0.2,0,0.4,0.2,0.4,0.4v15c0,0.2-0.2,0.4-0.4,0.4H1.9c-0.2,0-0.4-0.2-0.4-0.4v-15
                                  c0-0.2,0.2-0.4,0.4-0.4H13.1 M13.1,0H1.9C0.8,0,0,0.8,0,1.9v15c0,1,0.8,1.9,1.9,1.9h11.3c1,0,1.9-0.8,1.9-1.9v-15
                                  C15,0.8,14.2,0,13.1,0L13.1,0z"/>
                            <rect x="3.3" y="3.8" class="icon" width="6.4" height="1.5"/>
                            <rect x="3.3" y="7" class="icon" width="6.4" height="1.5"/>
                            <rect x="3.3" y="10.2" class="icon" width="6.4" height="1.5"/>
                            <rect x="3.3" y="13.5" class="icon" width="6.4" height="1.5"/>
                            <circle class="icon" cx="1.2" cy="4.5" r="1.2"/>
                            <circle class="icon" cx="1.2" cy="7.8" r="1.2"/>
                            <circle class="icon" cx="1.2" cy="11" r="1.2"/>
                            <circle class="icon" cx="1.2" cy="14.2" r="1.2"/>
                            </svg>
                            <spring:message code="yt.partner.common.header.payment"/>
                        </a>
                    </div>
                </div>
            </li>
        </ul>
    </div>
</div>
<script>
    $(function () {
        $("#trippdash-menu .aMenu.active .name a").click(function () {
            return false;
        });
        $("#trippdash-menu .sub-menu .aSub.active a").click(function () {
            return false;
        });
    });
</script>