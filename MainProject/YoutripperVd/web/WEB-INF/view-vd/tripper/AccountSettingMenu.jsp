<%-- 
    Document   : AccountSettingMenu
    Created on : Jun 18, 2018, 4:33:32 PM
    Author     : nickn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div id="account-header">
    <div class="yt-normal-container">
        <ul class="ams clearfix">
            <li>
                <div class="am" ng-class="{'active' : hes === 'booking'}">
                    <a href="${sessionScope.hrefLocale}/tripper/my-booking">
                        <svg x="0px" y="0px" width="20px" height="17px" viewBox="0 0 20 17">
                        <path style="fill: #FFFFFF" d="M20,0H0v6h1v11h18V6h1V0z M18,4h-5V2h5V4z M2,2h5v2H2V2z M17,15H3V6h14V15z"></path>
                        </svg>
                        <spring:message code="yt.tripper.accountsetting.header.booking"/>
                    </a>
                </div>
            </li>
            <li>
                <div class="am" ng-class="{'active' : hes === 'bucket'}">
                    <a href="${sessionScope.hrefLocale}/tripper/buckets">
                        <svg width="20px" height="17.8px" viewBox="0 0 20 17.8">
                        <path style="fill: #FFFFFF" d="M5.2,0C2.6,0,0,2,0,5.7c0,4.4,6.4,9.7,9.8,11.9l0.2,0.1l0.2-0.1c3.4-2.2,9.8-7.6,9.8-11.9
                              C20,2,17.4,0,14.7,0c-1.7,0-3.2,0.8-4.4,2.1L10,2.6L9.6,2.1C8.5,0.8,6.9,0,5.2,0"></path>
                        </svg>
                        <spring:message code="yt.tripper.accountsetting.header.bucket"/>
                    </a>
                </div>
            </li>
            <li>
                <div class="am" ng-class="{'active' : hes === 'profile'}">
                    <a href="${sessionScope.hrefLocale}/tripper/account-profile">
                        <svg  x="0px" y="0px" width="15px" height="18.8px" viewBox="0 0 15 18.8" >
                        <path style="fill: #FFFFFF" d="M13.1,1.5c0.2,0,0.4,0.2,0.4,0.4v15c0,0.2-0.2,0.4-0.4,0.4H1.9c-0.2,0-0.4-0.2-0.4-0.4v-15
                              c0-0.2,0.2-0.4,0.4-0.4H13.1 M13.1,0H1.9C0.8,0,0,0.8,0,1.9v15c0,1,0.8,1.9,1.9,1.9h11.3c1,0,1.9-0.8,1.9-1.9v-15
                              C15,0.8,14.2,0,13.1,0L13.1,0z"></path>
                        <rect x="3.3" y="3.8" style="fill: #FFFFFF" width="6.4" height="1.5"></rect>
                        <rect x="3.3" y="7" style="fill: #FFFFFF" width="6.4" height="1.5"></rect>
                        <rect x="3.3" y="10.2" style="fill: #FFFFFF" width="6.4" height="1.5"></rect>
                        <rect x="3.3" y="13.5" style="fill: #FFFFFF" width="6.4" height="1.5"></rect>
                        <circle style="fill: #FFFFFF" cx="1.2" cy="4.5" r="1.2"></circle>
                        <circle style="fill: #FFFFFF" cx="1.2" cy="7.8" r="1.2"></circle>
                        <circle style="fill: #FFFFFF" cx="1.2" cy="11" r="1.2"></circle>
                        <circle style="fill: #FFFFFF" cx="1.2" cy="14.2" r="1.2"></circle>
                        </svg>
                        <spring:message code="yt.tripper.accountsetting.header.profile"/>
                    </a>
                </div>
            </li>
        </ul>
    </div>
</div>