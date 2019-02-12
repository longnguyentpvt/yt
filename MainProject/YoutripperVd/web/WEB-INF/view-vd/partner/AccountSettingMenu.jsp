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
                <div class="am" ng-class="{'active' : hes === 'profile'}">
                    <a href="${sessionScope.hrefLocale}/partner/account-profile">
                        <spring:message code="yt.partner.accountsetting.header.profile"/>
                    </a>
                </div>
            </li>
            <li>
                <div class="am" ng-class="{'active' : hes === 'verification'}">
                    <a href="${sessionScope.hrefLocale}/partner/account-verification">
                        <spring:message code="yt.partner.accountsetting.header.verification"/>
                    </a>
                </div>
            </li>
            <li>
                <div class="am" ng-class="{'active' : hes === 'certificate'}">
                    <a href="${sessionScope.hrefLocale}/partner/account-certificate">
                        <spring:message code="yt.partner.accountsetting.header.certificate"/>
                    </a>
                </div>
            </li>
        </ul>
    </div>
</div>