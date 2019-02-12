<%-- 
    Document   : RegularPackages
    Created on : Jan 22, 2018, 12:30:22 PM
    Author     : nickn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html lang="${pageContext.response.locale}" currency="${sessionScope.ytCurrency}" ng-app="youtripper" ng-controller="YoutripperController">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="../common/commonhead.jsp" %>

        <link rel="stylesheet" href="${requestScope.cssURL}partner-trippdash-menu.css" >
        <link rel="stylesheet" href="${requestScope.cssURL}partner-package-registration.css" >

        <script src="${requestScope.jsURL}regular-registration.js"></script>
        <script src="${requestScope.jsURL}category-registration.js"></script>
        <script src="${requestScope.jsURL}description-registration.js"></script>
        <script src="${requestScope.jsURL}photo-registration.js"></script>

        <title>Youtripper - Regular Package Editing</title>
    </head>
    <body>
        <div id="padding-header" ng-controller="PackageRegistration" ng-cloak>

            <div id="partner-packages">
                <!--MENU-->
                <%@include file="TrippDashMenu.jsp" %>
                <!--END MENU-->

                <div id="trippdash-body">
                    <div id="package-registration">
                        <!--HEADER-->
                        <div class='header'>
                            <div class='yt-small-container'>
                                <div class='steps clearfix'>
                                    <div class='btns'>
                                        <div class='vertical-center-container'>
                                            <div class='center-content'>
                                                <ul class='clearfix'>
                                                    <li>
                                                        <a ng-href="{{registration.vars.data.packageID > 0 ?
                                                                    ('${localePath}' + '/partner/registration/preview/' + registration.vars.data.packageID) :
                                                                                ''}}"  
                                                           class='yt-btn trans-btn'
                                                           target="_blank">
                                                            Preview
                                                        </a>
                                                    </li><li>
                                                        <form method="POST" action="${localePath}/partner/registration/regular/submitting" id="submit-form">
                                                            <input type="text" name="packageID" ng-model="registration.vars.data.packageID" style="display: none"/>
                                                        </form>
                                                        <a href='' ng-click='registration.funcs.submit()' class='yt-btn trans-btn red-btn'
                                                           ng-class="{'submit-btn': !registration.vars.done ||
                                                                       registration.vars.steps.description.editing}">
                                                            Submit
                                                        </a>
                                                    </li>
                                                </ul>
                                            </div>
                                        </div>
                                    </div>
                                    <ul class='clearfix'>
                                        <li ng-class="{'active': registration.vars.steps.category.active,
                                                'done':  registration.vars.steps.category.done}">
                                            <div class='vertical-center-container'>
                                                <div class='center-content'>
                                                    <a href='' ng-click="registration.funcs.skipStep('category')">
                                                        <spring:message code="yt.pkgEditing.header.category"/>
                                                    </a>
                                                </div>
                                            </div>
                                        </li>
                                        <li ng-class="{'active' : registration.vars.steps.description.active,
                                                'done' : registration.vars.steps.description.done}">
                                            <div class='vertical-center-container'>
                                                <div class='center-content'>
                                                    <a href='' ng-click="registration.funcs.skipStep('description')">
                                                        <spring:message code="yt.pkgEditing.header.description"/>
                                                    </a>
                                                </div>
                                            </div>
                                        </li>
                                        <li ng-class="{ 'active' : registration.vars.steps.photo.active,
                                                'done':  registration.vars.steps.photo.done}">
                                            <div class='vertical-center-container'>
                                                <div class='center-content'>
                                                    <a href='' ng-click="registration.funcs.skipStep('photo')">
                                                        <spring:message  code="yt.pkgEditing.header.photo"/>
                                                    </a>
                                                </div>
                                            </div>
                                        </li>
                                        <li ng-class="{'active': registration.vars.steps.price.active,
                                                'done' :  registration.vars.steps.price.done}">
                                            <div class='vertical-center-container'>
                                                <div class='center-content'>
                                                    <a href=''ng-click="registration.funcs.skipStep('price')">
                                                        <spring:message  code="yt.pkgEditing.header.price"/>
                                                    </a>
                                                </div>
                                            </div>
                                        </li>
                                        <li ng-class="{'active' : registration.vars.steps.availability.active,
                                                'done':registration.vars.steps.availability.done}">
                                            <div class='vertical-center-container'>
                                                <div class='center-content'>
                                                    <a href='' ng-click="registration.funcs.skipStep('availability')">
                                                        <spring:message  code="yt.pkgEditing.header.availability"/>
                                                    </a>
                                                </div>
                                            </div>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                        <!--END HEADER-->

                        <!--BODY-->
                        <div class="body">
                            <!--CATEGORY STEP-->
                            <div class='aStep' ng-show="registration.vars.steps.category.active">
                                <jsp:include page="RegistrationCategoryStep.jsp"/>
                            </div>
                            <!--END CATEGORY STEP-->
                            <!--DESCRIPTION STEP-->
                            <div class='aStep' ng-show="registration.vars.steps.description.active">
                                <jsp:include page="RegistrationDescriptionStep.jsp"/>
                            </div>
                            <!--END DESCRIPTION STEP-->
                             <!--PHOTO STEP-->
                            <div class='aStep' ng-show="registration.vars.steps.photo.active">
                                <jsp:include page="RegistrationPhotoStep.jsp"/>
                            </div>
                            <!--END PHOTO STEP-->
                        </div>
                        <!--END BODY-->

                        <!--STEP SKIPPING POPUP-->
                        <div class="yt-popup common-popup" ng-class="{'active': registration.vars.popups.stepSkipping.creating.active}">
                            <div class="vertical-center-container">
                                <div class="center-content">
                                    <div class="popup">
                                        <div class="header med-txt">
                                            <spring:message code="yt.pkgEditing.popup.stepSkipping.header"/> 
                                            <span class="close-btn"
                                                  ng-click="registration.vars.popups.stepSkipping.creating.complete()"></span>
                                        </div>
                                        <div class="body">
                                            <div class="container">
                                                <div class="row">
                                                    <spring:message code="yt.pkgEditing.popup.stepSkipping.body"/>
                                                </div>
                                                <div class="row">
                                                    <div class="confirm-btns">
                                                        <ul class="clearfix">
                                                            <li>
                                                                <input type="button" class="yt-btn" 
                                                                       ng-click="registration.vars.popups.stepSkipping.creating.complete()"
                                                                       value="<spring:message code="yt.pkgEditing.popup.stepSkipping.btn.complete"/>">
                                                            </li>
                                                            <li>
                                                                <input type="button" class="yt-btn red-btn" 
                                                                       ng-click="registration.funcs.changeStep()"
                                                                       value="<spring:message code="yt.pkgEditing.popup.stepSkipping.btn.skip"/>">
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
                        <!--END STEP SKIPPING POPUP-->

                        <div class="center-spinner-block" ng-class="{'loading' : !registration.vars.loaded}">
                            <div class="vertical-center-container">
                                <div class="center-content">
                                    <span class="yt-spinner center"></span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <%@include file="../common/header.jsp" %>
        </div>
    </body>
    <script>
        var packageID = ${requestScope.packageID};
    </script>

    <script async defer
    src="https://maps.googleapis.com/maps/api/js?key=<spring:message code="yt.config.ggmap.key"/>&libraries=places"></script>
</html>
