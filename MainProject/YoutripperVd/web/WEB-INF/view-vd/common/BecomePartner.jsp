<%-- 
    Document   : becomepartner
    Created on : Jan 10, 2018, 10:54:44 AM
    Author     : nickn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="${pageContext.response.locale}" currency="${sessionScope.ytCurrency}" ng-app="youtripper" ng-controller="YoutripperController">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="commonhead.jsp" %>
        <link rel="stylesheet" href="${requestScope.cssURL}user-become-partner.css" >
        <script src="${requestScope.jsURL}user-become-partner.js"></script>
        <title><spring:message code="yt.becomePartner.browser.header"/></title>
    </head>
    <body ng-app="youtripper" ng-controller="YoutripperController">
        <div id="padding-header">
            <div id='become-partner' ng-controller="BecomePartner" ng-cloak>
                <!--COVER-->
                <div class='cover'>
                    <div class="bg" style='background-image: url(${requestScope.imageURL}a1f7bc6d56f3a4bdb890d0ac5ea06d14.jpg);'></div>
                    <div class='info'>
                        <div class="vertical-center-container">
                            <div class="center-content">
                                <div class="txt">
                                    <h1 class='bold-txt'>
                                        <spring:message code="yt.becomePartner.firstContent.p1"/>
                                    </h1>
                                    <p>
                                        <spring:message code="yt.becomePartner.firstContent.p2"/>
                                    </p>
                                    <input type="button" value="<spring:message code="yt.becomePartner.firstContent.registerBtn"/>" class='yt-btn auto-width-btn'/>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!--END COVER-->
                <div class='body'>
                    <!--INFORMATION-->
                    <div class='single-section'>
                        <div class="yt-small-container">
                            <h2 class="header bold-txt big-txt">
                                <spring:message code="yt.becomePartner.category.header"/>
                            </h2>
                            <div class="categories">
                                <ul class="clearfix">
                                    <li>
                                        <div class="category">
                                            <img src="${requestScope.imageURL}youtripper-sport-activity.jpg" 
                                                 alt="Become Partner - Sport Activities - Youtripper"/>
                                            <h3 class="title big-txt">
                                                <spring:message code="yt.becomePartner.category.sport.title"/>
                                            </h3>
                                            <p>
                                                <spring:message code="yt.becomePartner.category.sport.par"/>
                                            </p>
                                        </div>
                                    </li>
                                    <li>
                                        <div class="category">
                                            <img src="${requestScope.imageURL}youtripper-life-style-activity.jpg" 
                                                 alt="Become Partner - Sport Activities - Youtripper"/>
                                            <h3 class="title big-txt">
                                                <spring:message code="yt.becomePartner.category.lifestyle.title"/>
                                            </h3>
                                            <p>
                                                <spring:message code="yt.becomePartner.category.lifestyle.par"/>
                                            </p>
                                        </div>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>

                    <div class='single-section'>
                        <div class="yt-small-container">
                            <h2 class="header bold-txt big-txt">
                                <spring:message code="yt.becomePartner.reason.header"/>
                            </h2>
                            <div class="reasons">
                                <img src="${requestScope.imageURL}why-partner-with-youtripper.jpg"
                                     alt="Why partner with youtripper?"/>
                                <ul class="clearfix">
                                    <li>
                                        <div class="reason">
                                            <h4 class="title bold-txt">
                                                <spring:message code="yt.becomePartner.reason.commission.title"/>
                                            </h4>
                                            <p>
                                                <spring:message code="yt.becomePartner.reason.commission.par"/>
                                            </p>
                                        </div>
                                    </li>
                                    <li>
                                        <div class="reason">
                                            <h4 class="title bold-txt">
                                                <spring:message code="yt.becomePartner.reason.business.title"/>
                                            </h4>
                                            <p>
                                                <spring:message code="yt.becomePartner.reason.business.par"/>
                                            </p>
                                        </div>
                                    </li>
                                    <li>
                                        <div class="reason">
                                            <h4 class="title bold-txt">
                                                <spring:message code="yt.becomePartner.reason.brand.title"/>
                                            </h4>
                                            <p>
                                                <spring:message code="yt.becomePartner.reason.brand.par"/>
                                            </p>
                                        </div>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>

                    <div class='single-section'>
                        <div class="yt-small-container">
                            <h2 class="header bold-txt big-txt">
                                <spring:message code="yt.becomePartner.how.header"/>
                            </h2>
                            <div class="how-it-works">
                                <ul class="clearfix">
                                    <li>
                                        <div class="how">
                                            <div class="img">
                                                <img src="${requestScope.imageURL}signup-as-partner.png" 
                                                     alt="How It Works - Sign up as a Partner - Youtripper"/>
                                            </div>
                                            <h4 class="title med-txt">
                                                <spring:message code="yt.becomePartner.how.signup.title"/>
                                            </h4>
                                            <p>
                                                <spring:message code="yt.becomePartner.how.signup.par"/>
                                            </p>
                                        </div>
                                    </li>
                                    <li>
                                        <div class="how">
                                            <div class="img">
                                                <img src="${requestScope.imageURL}create-package.png" 
                                                     alt="How It Works - Create your package - Youtripper"/>
                                            </div>
                                            <h4 class="title med-txt">
                                                <spring:message code="yt.becomePartner.how.create.title"/>
                                            </h4>
                                            <p>
                                                <spring:message code="yt.becomePartner.how.create.par"/>
                                            </p>
                                        </div>
                                    </li>
                                    <li>
                                        <div class="how">
                                            <div class="img">
                                                <img src="${requestScope.imageURL}earn-money.png" 
                                                     alt="How It Works - Earn money weekly - Youtripper"/>
                                            </div>

                                            <h4 class="title med-txt">
                                                <spring:message code="yt.becomePartner.how.earn.title"/>
                                            </h4>
                                            <p>
                                                <spring:message code="yt.becomePartner.how.earn.par"/>
                                            </p>
                                        </div>
                                    </li>
                                    <li>
                                        <div class="how">
                                            <div class="img">
                                                <img src="${requestScope.imageURL}reach-people.png" 
                                                     alt="How It Works - Reach more people - Youtripper"/>   
                                            </div>

                                            <h4 class="title med-txt">
                                                <spring:message code="yt.becomePartner.how.reach.title"/>
                                            </h4>
                                            <p>
                                                <spring:message code="yt.becomePartner.how.reach.par"/>
                                            </p>
                                        </div>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <!--END INFORMATION-->

                    <div class="single-section" id="registration-form"
                         ng-mousemove="captcha.funcs.drag($event)" ng-mouseup="captcha.funcs.stop()" ng-mouseleave="captcha.funcs.stop()">
                        <div class='yt-small-container'>
                            <div class="header">
                                <h2 class='bold-txt'>
                                    <spring:message code="yt.becomePartner.registration.header"/>
                                </h2>
                                <div class="sub-txt med-txt">
                                    <spring:message code="yt.becomePartner.registration.subHeader"/>
                                </div>
                            </div>
                            <form method="POST" 
                                  action="/become-partner/registration" 
                                  name="signupForm"
                                  ng-submit="registration.funcs.validate($event)" novalidate autocomplete="off">
                                <div class="forms">
                                    <ul>
                                        <li>
                                            <div class="form">
                                                <div class="title">
                                                    <spring:message code="yt.becomePartner.registration.business.title"/>
                                                </div>
                                                <ul class='clearfix'>
                                                    <li>
                                                        <div class='input status-input ddl-input'
                                                             ng-class="{
                                                                     'success': signupForm.businessType.$valid,
                                                                             'err': registration.vars.error && !signupForm.businessType.$valid,
                                                                             'nov':!registration.vars.partner.businessType.length}">
                                                            <select name="businessType" class="yt-input no-b" 
                                                                    ng-model="registration.vars.partner.businessType" required>
                                                                <option value="" disabled>
                                                                    <spring:message code="yt.becomePartner.registration.input.businessType"/>
                                                                </option>
                                                                <option ng-value="registration.vars.formData.businessTypePersonal">
                                                                    <spring:message code="yt.becomePartner.registration.input.option.personal"/>
                                                                </option>
                                                                <option ng-value="registration.vars.formData.businessTypeCompany">
                                                                    <spring:message code="yt.becomePartner.registration.input.option.company"/>
                                                                </option>
                                                            </select>
                                                            <div class="stt">
                                                                <svg class="sc" width="16.5px" height="12.5px" viewBox="0 0 16.5 12.5">
                                                                <line x1="5" y1="11.5" x2="1" y2="7.5"/>
                                                                <line x1="15.5" y1="1" x2="5" y2="11.5"/>
                                                                </svg>
                                                                <svg  class="er" width="12.5px" height="12.5px" viewBox="0 0 12.5 12.5">
                                                                <line  x1="11.5" y1="1" x2="1" y2="11.5"/>
                                                                <line  x1="11.5" y1="11.5" x2="1" y2="1"/>
                                                                </svg>
                                                            </div>
                                                        </div>
                                                        <span class='error-msg' ng-show="(registration.vars.error && !signupForm.businessType.$valid)">
                                                            <spring:message code="yt.becomePartner.registration.error.businessType.missing"/>
                                                        </span>
                                                    </li>
                                                    <li>
                                                        <div class='input status-input'
                                                             ng-class="{'success': signupForm.businessName.$valid,
                                                                             'err': registration.vars.error && !signupForm.businessName.$valid}">
                                                            <input type='text' class='yt-input no-b' name="businessName"
                                                                   placeholder="<spring:message code="yt.becomePartner.registration.input.businessName"/>" 
                                                                   ng-model="registration.vars.partner.businessName" ytmaxlength="50" required/>
                                                            <div class="stt">
                                                                <svg class="sc" width="16.5px" height="12.5px" viewBox="0 0 16.5 12.5">
                                                                <line x1="5" y1="11.5" x2="1" y2="7.5"/>
                                                                <line x1="15.5" y1="1" x2="5" y2="11.5"/>
                                                                </svg>
                                                                <svg  class="er" width="12.5px" height="12.5px" viewBox="0 0 12.5 12.5">
                                                                <line  x1="11.5" y1="1" x2="1" y2="11.5"/>
                                                                <line  x1="11.5" y1="11.5" x2="1" y2="1"/>
                                                                </svg>
                                                            </div>
                                                        </div>
                                                        <span class='error-msg' ng-show="(registration.vars.error && !signupForm.businessName.$valid)">
                                                            <spring:message code="yt.becomePartner.registration.error.businessName.length"/>
                                                        </span>
                                                    </li>
                                                    <li>
                                                        <div class='input status-input ddl-input' ng-class="{
                                                                'success' : signupForm.country.$valid,
                                                                        'err': registration.vars.error && !signupForm.country.$valid,
                                                                    'nov':!registration.vars.partner.country.length}">
                                                            <select class="yt-input  no-b" name="country"
                                                                    ng-model="registration.vars.partner.country" 
                                                                    ng-change="registration.funcs.countryChange()" required>
                                                                <option value="" disabled>
                                                                    <spring:message code="yt.becomePartner.registration.input.country"/>
                                                                </option>                                                            
                                                                <option ng-value="aCountry.countryID" 
                                                                        ng-repeat="aCountry in registration.vars.formData.partnerCountries">
                                                                    {{aCountry.countryName}}
                                                                </option>
                                                            </select>
                                                            <div class="stt">
                                                                <svg class="sc" width="16.5px" height="12.5px" viewBox="0 0 16.5 12.5">
                                                                <line x1="5" y1="11.5" x2="1" y2="7.5"/>
                                                                <line x1="15.5" y1="1" x2="5" y2="11.5"/>
                                                                </svg>
                                                                <svg  class="er" width="12.5px" height="12.5px" viewBox="0 0 12.5 12.5">
                                                                <line  x1="11.5" y1="1" x2="1" y2="11.5"/>
                                                                <line  x1="11.5" y1="11.5" x2="1" y2="1"/>
                                                                </svg>
                                                            </div>
                                                        </div>
                                                        <span class='error-msg' ng-show="registration.vars.error && !signupForm.country.$valid">
                                                            <spring:message code="yt.becomePartner.registration.error.country.missing"/>
                                                        </span>
                                                    </li>
                                                    <li>
                                                        <div class='input status-input ddl-input' ng-class="{
                                                                'success' : signupForm.state.$valid,
                                                                        'err': registration.vars.error && !signupForm.state.$valid,
                                                                    'nov': registration.vars.partner.city == null }">
                                                            <select name="state" class="yt-input no-b" ng-model="registration.vars.partner.city" required>
                                                                <option value="" disabled>
                                                                    <spring:message code="yt.becomePartner.registration.input.state"/>
                                                                </option>
                                                                <option ng-repeat="aState in registration.vars.formData.states"
                                                                        ng-value="aState.stateID">
                                                                    {{aState.stateName}}
                                                                </option>
                                                            </select>
                                                            <div class="stt">
                                                                <svg class="sc" width="16.5px" height="12.5px" viewBox="0 0 16.5 12.5">
                                                                <line x1="5" y1="11.5" x2="1" y2="7.5"/>
                                                                <line x1="15.5" y1="1" x2="5" y2="11.5"/>
                                                                </svg>
                                                                <svg  class="er" width="12.5px" height="12.5px" viewBox="0 0 12.5 12.5">
                                                                <line  x1="11.5" y1="1" x2="1" y2="11.5"/>
                                                                <line  x1="11.5" y1="11.5" x2="1" y2="1"/>
                                                                </svg>
                                                            </div>
                                                        </div>
                                                        <span class='error-msg' ng-show="registration.vars.error && !signupForm.state.$valid">
                                                            <spring:message code="yt.becomePartner.registration.error.state.missing"/>  
                                                        </span>
                                                    </li>
                                                </ul>
                                                <ul class='clearfix'>
                                                    <li class='address-input'>
                                                        <div class='input status-input'  ng-class="{
                                                                'success' : signupForm.address.$valid,
                                                                        'err': registration.vars.error && !signupForm.address.$valid}">
                                                            <input type='text' class='yt-input no-b' name="address"
                                                                   placeholder="<spring:message code="yt.becomePartner.registration.input.businessAddress"/>"
                                                                   ng-model="registration.vars.partner.address" ytmaxlength="100" required/>
                                                            <div class="stt">
                                                                <svg class="sc" width="16.5px" height="12.5px" viewBox="0 0 16.5 12.5">
                                                                <line x1="5" y1="11.5" x2="1" y2="7.5"/>
                                                                <line x1="15.5" y1="1" x2="5" y2="11.5"/>
                                                                </svg>
                                                                <svg  class="er" width="12.5px" height="12.5px" viewBox="0 0 12.5 12.5">
                                                                <line  x1="11.5" y1="1" x2="1" y2="11.5"/>
                                                                <line  x1="11.5" y1="11.5" x2="1" y2="1"/>
                                                                </svg>
                                                            </div>
                                                        </div>
                                                        <span class='error-msg'  ng-show="registration.vars.error && signupForm.address.$error.required">
                                                            <spring:message code="yt.becomePartner.registration.error.businessAddress.missing"/>  
                                                        </span>
                                                    </li>
                                                    <li>
                                                        <div class='input status-input' ng-class="{
                                                                'success': signupForm.city.$valid,
                                                                        'err' : registration.vars.error && !signupForm.city.$valid}">
                                                            <input type='text' class='yt-input no-b' name="city"
                                                                   placeholder="<spring:message code="yt.becomePartner.registration.input.city"/>" 
                                                                   ng-model="registration.vars.partner.otherCity" ytmaxlength="50" required/>
                                                            <div class="stt">
                                                                <svg class="sc" width="16.5px" height="12.5px" viewBox="0 0 16.5 12.5">
                                                                <line x1="5" y1="11.5" x2="1" y2="7.5"/>
                                                                <line x1="15.5" y1="1" x2="5" y2="11.5"/>
                                                                </svg>
                                                                <svg  class="er" width="12.5px" height="12.5px" viewBox="0 0 12.5 12.5">
                                                                <line  x1="11.5" y1="1" x2="1" y2="11.5"/>
                                                                <line  x1="11.5" y1="11.5" x2="1" y2="1"/>
                                                                </svg>
                                                            </div>
                                                        </div>
                                                        <span class='error-msg' ng-show="registration.vars.error && signupForm.city.$error.required">
                                                            <spring:message code="yt.becomePartner.registration.error.cityName.missing"/>
                                                        </span>
                                                    </li>
                                                    <li>
                                                        <div class='input status-input' ng-class="{
                                                                'success' : signupForm.postcode.$valid,
                                                                        'err' : registration.vars.error && !signupForm.postcode.$valid}">
                                                            <input type='text' class='yt-input no-b' name="postcode"
                                                                   placeholder="<spring:message code="yt.becomePartner.registration.input.postcode"/>"
                                                                   ng-model="registration.vars.partner.postcode" ytmaxlength="10" required/>
                                                            <div class="stt">
                                                                <svg class="sc" width="16.5px" height="12.5px" viewBox="0 0 16.5 12.5">
                                                                <line x1="5" y1="11.5" x2="1" y2="7.5"/>
                                                                <line x1="15.5" y1="1" x2="5" y2="11.5"/>
                                                                </svg>
                                                                <svg  class="er" width="12.5px" height="12.5px" viewBox="0 0 12.5 12.5">
                                                                <line  x1="11.5" y1="1" x2="1" y2="11.5"/>
                                                                <line  x1="11.5" y1="11.5" x2="1" y2="1"/>
                                                                </svg>
                                                            </div>
                                                        </div>
                                                        <span class='error-msg small-txt' ng-show="registration.vars.error && signupForm.postcode.$error.required">
                                                            <spring:message code="yt.becomePartner.registration.error.zipCode.missing"/>
                                                        </span>
                                                    </li>                                              
                                                </ul>
                                                <ul class='clearfix'> 
                                                    <li class="phone-code">
                                                        <div class="status-input ddl-input" ng-init="registration.vars.partner.pcode = '66'">
                                                            <select class="yt-input no-b" ng-model="registration.vars.partner.pcode" name="phonecode">
                                                                <option ng-repeat="ac in registration.vars.formData.phoneCodes" ng-value="ac">+{{ac}}</option>
                                                            </select>
                                                        </div>
                                                    </li>
                                                    <li class="phone-no">
                                                        <div class='input status-input' ng-class="{
                                                                'success': signupForm.phone.$valid,
                                                                        'err': registration.vars.error && !signupForm.phone.$valid}">
                                                            <input type='text' class='yt-input no-b' name="phone" number-str-input
                                                                   placeholder="<spring:message code="yt.becomePartner.registration.input.phone"/>"
                                                                   ng-model="registration.vars.partner.phone"  ytmaxlength="20" required/>
                                                            <div class="stt">
                                                                <svg class="sc" width="16.5px" height="12.5px" viewBox="0 0 16.5 12.5">
                                                                <line x1="5" y1="11.5" x2="1" y2="7.5"/>
                                                                <line x1="15.5" y1="1" x2="5" y2="11.5"/>
                                                                </svg>
                                                                <svg  class="er" width="12.5px" height="12.5px" viewBox="0 0 12.5 12.5">
                                                                <line  x1="11.5" y1="1" x2="1" y2="11.5"/>
                                                                <line  x1="11.5" y1="11.5" x2="1" y2="1"/>
                                                                </svg>
                                                            </div>
                                                        </div>
                                                        <span class='error-msg small-txt' ng-show="registration.vars.error && !signupForm.phone.$valid">
                                                            <spring:message code="yt.becomePartner.registration.error.phoneNumber.invalid"/>
                                                        </span>
                                                    </li>
                                                </ul>
                                            </div>
                                        </li>
                                        <li>
                                            <div class="form">
                                                <div class="title">
                                                    <spring:message code="yt.becomePartner.registration.account.title"/>
                                                </div>
                                                <ul class='clearfix'>
                                                    <li>
                                                        <div class='input status-input' ng-class="{
                                                                'success' : signupForm.firstName.$valid,
                                                                        'err' : registration.vars.error && !signupForm.firstName.$valid}">
                                                            <input type='text' class='yt-input no-b' name="firstName"
                                                                   placeholder="<spring:message code="yt.becomePartner.registration.input.firstname"/>"
                                                                   ng-model="registration.vars.partner.firstName" ytmaxlength="30" required/>
                                                            <div class="stt">
                                                                <svg class="sc" width="16.5px" height="12.5px" viewBox="0 0 16.5 12.5">
                                                                <line x1="5" y1="11.5" x2="1" y2="7.5"/>
                                                                <line x1="15.5" y1="1" x2="5" y2="11.5"/>
                                                                </svg>
                                                                <svg  class="er" width="12.5px" height="12.5px" viewBox="0 0 12.5 12.5">
                                                                <line  x1="11.5" y1="1" x2="1" y2="11.5"/>
                                                                <line  x1="11.5" y1="11.5" x2="1" y2="1"/>
                                                                </svg>
                                                            </div>
                                                        </div>
                                                        <span class='error-msg small-txt' ng-show="(registration.vars.error && !signupForm.firstName.$valid)">
                                                            <spring:message code="yt.becomePartner.registration.error.firstName.missing"/>
                                                        </span>
                                                    </li>
                                                    <li>
                                                        <div class='input status-input' ng-class="{
                                                                'success' : signupForm.lastName.$valid,
                                                                        'err' : registration.vars.error && !signupForm.lastName.$valid}">
                                                            <input type='text' class='yt-input no-b' name="lastName"
                                                                   placeholder="<spring:message code="yt.becomePartner.registration.input.lastname"/>" 
                                                                   ng-model="registration.vars.partner.lastName" ytmaxlength="30" required/>
                                                            <div class="stt">
                                                                <svg class="sc" width="16.5px" height="12.5px" viewBox="0 0 16.5 12.5">
                                                                <line x1="5" y1="11.5" x2="1" y2="7.5"/>
                                                                <line x1="15.5" y1="1" x2="5" y2="11.5"/>
                                                                </svg>
                                                                <svg  class="er" width="12.5px" height="12.5px" viewBox="0 0 12.5 12.5">
                                                                <line  x1="11.5" y1="1" x2="1" y2="11.5"/>
                                                                <line  x1="11.5" y1="11.5" x2="1" y2="1"/>
                                                                </svg>
                                                            </div>
                                                        </div>
                                                        <span class='error-msg small-txt' ng-show="(registration.vars.error && !signupForm.lastName.$valid)">
                                                            <spring:message code="yt.becomePartner.registration.error.lastName.missing"/>
                                                        </span>
                                                    </li>
                                                    <li>
                                                        <div class='input status-input' ng-class="{
                                                                'success' : signupForm.displayName.$valid,
                                                                        'err' : registration.vars.error && !signupForm.displayName.$valid}">
                                                            <input type='text' class='yt-input no-b' name="displayName"
                                                                   placeholder="<spring:message code="yt.becomePartner.registration.input.displayName"/>"
                                                                   ng-model="registration.vars.partner.displayName" ytmaxlength="30" required/>
                                                            <div class="stt">
                                                                <svg class="sc" width="16.5px" height="12.5px" viewBox="0 0 16.5 12.5">
                                                                <line x1="5" y1="11.5" x2="1" y2="7.5"/>
                                                                <line x1="15.5" y1="1" x2="5" y2="11.5"/>
                                                                </svg>
                                                                <svg  class="er" width="12.5px" height="12.5px" viewBox="0 0 12.5 12.5">
                                                                <line  x1="11.5" y1="1" x2="1" y2="11.5"/>
                                                                <line  x1="11.5" y1="11.5" x2="1" y2="1"/>
                                                                </svg>
                                                            </div>
                                                        </div>
                                                        <span class='error-msg small-txt' ng-show="registration.vars.error && !signupForm.displayName.$valid">
                                                            <spring:message code="yt.becomePartner.registration.error.displayName.missing"/>
                                                        </span>
                                                    </li>
                                                    <li>
                                                        <div class='input status-input  ddl-input' ng-class="{
                                                                'success' : signupForm.jobTitle.$valid,
                                                                        'err': registration.vars.error && !signupForm.jobTitle.$valid,
                                                                    'nov':!registration.vars.partner.jobTitle.length}">
                                                            <select name="jobTitle" class="yt-input no-b"
                                                                    ng-model="registration.vars.partner.jobTitle" required>
                                                                <option value="" disabled>
                                                                    <spring:message code="yt.becomePartner.registration.input.position"/>
                                                                </option>
                                                                <option ng-value="registration.vars.formData.positionBusinessOwner">
                                                                    <spring:message code="yt.becomePartner.registration.input.posBusinessOnwer"/>
                                                                </option>
                                                                <option ng-value="registration.vars.formData.positionSaleManager">
                                                                    <spring:message code="yt.becomePartner.registration.input.posSaleManager"/>
                                                                </option>
                                                                <option ng-value="registration.vars.formData.positionMarketingManager">
                                                                    <spring:message code="yt.becomePartner.registration.input.posMarketingManager"/>
                                                                </option>
                                                                <option ng-value="registration.vars.formData.positionGeneralManager">
                                                                    <spring:message code="yt.becomePartner.registration.input.posGeneralManager"/>
                                                                </option>
                                                                <option ng-value="registration.vars.formData.positionGeneralOfficer">
                                                                    <spring:message code="yt.becomePartner.registration.input.posGeneralOfficer"/>
                                                                </option>
                                                                <option ng-value="registration.vars.formData.positionOthers">
                                                                    <spring:message code="yt.becomePartner.registration.input.posOthers"/>
                                                                </option>
                                                            </select>
                                                            <div class="stt">
                                                                <svg class="sc" width="16.5px" height="12.5px" viewBox="0 0 16.5 12.5">
                                                                <line x1="5" y1="11.5" x2="1" y2="7.5"/>
                                                                <line x1="15.5" y1="1" x2="5" y2="11.5"/>
                                                                </svg>
                                                                <svg  class="er" width="12.5px" height="12.5px" viewBox="0 0 12.5 12.5">
                                                                <line  x1="11.5" y1="1" x2="1" y2="11.5"/>
                                                                <line  x1="11.5" y1="11.5" x2="1" y2="1"/>
                                                                </svg>
                                                            </div>
                                                        </div>
                                                        <span class='error-msg small-txt' ng-show="registration.vars.error &&
                                                                    signupForm.jobTitle.$error.required">
                                                            <spring:message code="yt.becomePartner.registration.error.position.missing"/>
                                                        </span>
                                                    </li>
                                                </ul>
                                                <ul class='clearfix'>
                                                    <li>
                                                        <div class='input status-input spinner-input' ng-class="{
                                                                'success' : !registration.vars.email.loading && signupForm.email.$valid && !registration.vars.email.exists,
                                                                        'err' : !registration.vars.email.loading && registration.vars.error && (!signupForm.email.$valid || registration.vars.email.exists)}">
                                                            <input type='text' class='yt-input no-b' name="email" ytmaxlength="255"
                                                                   placeholder="<spring:message code="yt.becomePartner.registration.input.email"/>"
                                                                   ng-change="registration.funcs.emailCheck()" ng-model="registration.vars.partner.email" 
                                                                   ng-pattern="registration.vars.regex.email" required/>
                                                            <div class="stt">
                                                                <svg class="sc" width="16.5px" height="12.5px" viewBox="0 0 16.5 12.5">
                                                                <line x1="5" y1="11.5" x2="1" y2="7.5"/>
                                                                <line x1="15.5" y1="1" x2="5" y2="11.5"/>
                                                                </svg>
                                                                <svg  class="er" width="12.5px" height="12.5px" viewBox="0 0 12.5 12.5">
                                                                <line  x1="11.5" y1="1" x2="1" y2="11.5"/>
                                                                <line  x1="11.5" y1="11.5" x2="1" y2="1"/>
                                                                </svg>
                                                            </div>
                                                            <span class="yt-spinner" ng-show="registration.vars.email.loading"></span>
                                                        </div>
                                                        <span class='error-msg small-txt' ng-if="!registration.vars.email.loading" ng-show="registration.vars.error && !signupForm.email.$valid">
                                                            <spring:message code="yt.becomePartner.registration.error.businessEmail.invalid"/>
                                                        </span>
                                                        <span class='error-msg small-txt' ng-if="!registration.vars.email.loading" ng-show="registration.vars.error &&
                                                                    signupForm.email.$valid && registration.vars.email.exists && !registration.vars.email.loading">
                                                            <spring:message code="yt.becomePartner.registration.error.businessEmail.exist"/>
                                                        </span>
                                                    </li>
                                                    <li>
                                                        <div class='input status-input' ng-class="{
                                                                'success' : registration.vars.email.confirm,
                                                                        'err': registration.vars.error && !registration.vars.email.confirm}">
                                                            <input type='text' class='yt-input no-b' name="reEmail"
                                                                   placeholder="<spring:message code="yt.becomePartner.registration.input.emailConfirm"/>" 
                                                                   ng-change="registration.funcs.emailConfirm()" ng-model="registration.vars.partner.reEmail"/>
                                                            <div class="stt">
                                                                <svg class="sc" width="16.5px" height="12.5px" viewBox="0 0 16.5 12.5">
                                                                <line x1="5" y1="11.5" x2="1" y2="7.5"/>
                                                                <line x1="15.5" y1="1" x2="5" y2="11.5"/>
                                                                </svg>
                                                                <svg  class="er" width="12.5px" height="12.5px" viewBox="0 0 12.5 12.5">
                                                                <line  x1="11.5" y1="1" x2="1" y2="11.5"/>
                                                                <line  x1="11.5" y1="11.5" x2="1" y2="1"/>
                                                                </svg>
                                                            </div>
                                                        </div>
                                                        <span class='error-msg small-txt' ng-show="registration.vars.error && !registration.vars.email.confirm">
                                                            <spring:message code="yt.becomePartner.registration.error.businessEmail.confirm.notMatch"/>
                                                        </span>
                                                    </li>
                                                    <li>
                                                        <div class='input status-input' ng-class="{
                                                                'success' : signupForm.password.$valid,
                                                                        'err': registration.vars.error && !signupForm.password.$valid}">
                                                            <input type='password' class='yt-input no-b' name="password" 
                                                                   placeholder="<spring:message code="yt.becomePartner.registration.input.password"/>"
                                                                   ng-model="registration.vars.partner.password" ng-model-options="{
                                                                       allowInvalid: true}"
                                                                   ng-change="registration.funcs.passwordConfirm()" minlength="8" ng-minlength="8"  ytmaxlength="32" ng-maxlength="32"
                                                                   ng-pattern="registration.vars.regex.password" required/>
                                                            <div class="stt">
                                                                <svg class="sc" width="16.5px" height="12.5px" viewBox="0 0 16.5 12.5">
                                                                <line x1="5" y1="11.5" x2="1" y2="7.5"/>
                                                                <line x1="15.5" y1="1" x2="5" y2="11.5"/>
                                                                </svg>
                                                                <svg  class="er" width="12.5px" height="12.5px" viewBox="0 0 12.5 12.5">
                                                                <line  x1="11.5" y1="1" x2="1" y2="11.5"/>
                                                                <line  x1="11.5" y1="11.5" x2="1" y2="1"/>
                                                                </svg>
                                                            </div>
                                                        </div>
                                                        <span class='error-msg small-txt' ng-show="registration.vars.error &&
                                                                    (signupForm.password.$error.required || signupForm.password.$error.minlength)">
                                                            <spring:message code="yt.becomePartner.registration.error.password.missing"/>
                                                        </span>
                                                        <span class='error-msg small-txt' ng-show="registration.vars.error && signupForm.password.$error.pattern">
                                                            <spring:message code="yt.becomePartner.registration.error.password.invalidFormat"/>
                                                        </span>
                                                    </li>
                                                    <li>
                                                        <div class='input status-input' ng-class="{
                                                                'success' : registration.vars.password.confirm,
                                                                        'err': registration.vars.error && !registration.vars.password.confirm}">
                                                            <input type='password' class='yt-input no-b' name="rePassword"
                                                                   placeholder="<spring:message code="yt.becomePartner.registration.input.passwordConfirm"/>" 
                                                                   ng-change="registration.funcs.passwordConfirm()" ng-model="registration.vars.partner.rePassword"/>
                                                            <div class="stt">
                                                                <svg class="sc" width="16.5px" height="12.5px" viewBox="0 0 16.5 12.5">
                                                                <line x1="5" y1="11.5" x2="1" y2="7.5"/>
                                                                <line x1="15.5" y1="1" x2="5" y2="11.5"/>
                                                                </svg>
                                                                <svg  class="er" width="12.5px" height="12.5px" viewBox="0 0 12.5 12.5">
                                                                <line  x1="11.5" y1="1" x2="1" y2="11.5"/>
                                                                <line  x1="11.5" y1="11.5" x2="1" y2="1"/>
                                                                </svg>
                                                            </div>
                                                        </div>
                                                        <span class='error-msg small-txt' ng-show="registration.vars.error && !registration.vars.password.confirm">
                                                            <spring:message code="yt.becomePartner.registration.error.password.confirm.notMatch"/>
                                                        </span>
                                                    </li>
                                                </ul>
                                                <ul class='clearfix'>
                                                    <li class="address-input">
                                                        <div class="checkbox-ctn" id="term-cb">
                                                            <div class="yt-checkbox">
                                                                <input type="checkbox" name="term" ng-model="registration.vars.term">
                                                                <span></span>
                                                            </div>
                                                            <span class='small-txt term'>
                                                                <spring:message code="yt.becomePartner.registration.txt.term"/>
                                                                <a href='${localePath}/terms' target='_blank'>
                                                                    <spring:message code="yt.becomePartner.registration.link.term"/>
                                                                </a>
                                                                <spring:message code="yt.becomePartner.registration.txt.and"/>
                                                                <a href='${localePath}/terms/privacy-policy' target='_blank'>
                                                                    <spring:message code="yt.becomePartner.registration.link.privacy"/>
                                                                </a>
                                                            </span>
                                                        </div>
                                                        <span class='error-msg' ng-show="registration.vars.error && !registration.vars.term">
                                                            <spring:message code="yt.becomePartner.registration.error.captcha.term"/>
                                                        </span>
                                                    </li>
                                                    <li>
                                                        <div class='slider-captcha' ng-class="{
                                                                'success' : registration.vars.captcha.success}">
                                                            <div class='content small-txt'>
                                                                <spring:message code="yt.becomePartner.registration.button.drag"/>
                                                            </div>
                                                            <div class='content success small-txt'>
                                                                <spring:message code="yt.becomePartner.registration.error.captcha.valid"/>
                                                            </div>
                                                            <span class='drag' ng-style="{
                                                                    'left' : captcha.vars.left + 'px'}"
                                                                  ng-mousedown="captcha.funcs.start($event)">

                                                            </span>
                                                        </div>
                                                        <span class='error-msg small-txt' ng-show="registration.vars.error &&
                                                                    !registration.vars.captcha.success">
                                                            <spring:message code="yt.becomePartner.registration.error.captcha.invalid"/>
                                                        </span>
                                                    </li>
                                                    <li>
                                                        <input type='submit'
                                                               value='<spring:message code="yt.becomePartner.registration.button.register"/>'
                                                               class='yt-btn red-btn'/>
                                                    </li>
                                                </ul>
                                                <ul class='clearfix'>
                                                    <li>
                                                        &nbsp;
                                                    </li>
                                                    <li>
                                                        &nbsp;
                                                    </li>
                                                    <li>
                                                        &nbsp;
                                                    </li>
                                                    <li>

                                                    </li>
                                                </ul>
                                            </div>
                                        </li>
                                    </ul>
                                </div>
                            </form>

                        </div>
                    </div>
                </div>

                <!--NEWSLETTER POPUP-->
                <div class="yt-popup" id="partner-news-pop" ng-class="{
                        'active' : firstD.popup.active}">
                    <div class="vertical-center-container" ng-click="firstD.close($event)">
                        <div class="center-content">
                            <div class="popup">
                                <div class="body">
                                    <span class="close-btn" ng-click="firstD.close($event)"></span>
                                    <div class="container">
                                        <div class="row">
                                            <div class="pop-tt">
                                                <spring:message code="yt.becomePartner.news.tt"/>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="news-txt">
                                                <spring:message code="yt.becomePartner.news.txt"/>
                                            </div>
                                        </div>
                                        <div class="row" ng-if="!firstD.popup.sc">
                                            <div class="group-f">
                                                <ul>
                                                    <li>
                                                        <div class="yt-input-ico">
                                                            <input type="text" class="yt-input" ng-model="firstD.popup.email" placeholder="<spring:message code="yt.becomePartner.news.email.input"/>"/>
                                                            <div class="ico">
                                                                <svg width="18.8px" height="15px" viewBox="0 0 18.8 15">
                                                                <path style="fill: #555555" d="M16.9,0h-15C0.8,0,0,0.8,0,1.9l0,11.3c0,1,0.8,1.9,1.9,1.9h15c1,0,1.9-0.8,1.9-1.9V1.9
                                                                      C18.8,0.8,17.9,0,16.9,0z M16.9,3.8L9.4,8.4L1.9,3.8V1.9l7.5,4.7l7.5-4.7V3.8z"></path>
                                                                </svg>
                                                            </div>
                                                        </div>
                                                    </li>
                                                    <li ng-if="firstD.popup.e">
                                                        <div class="er-msg">
                                                            <spring:message code="yt.becomePartner.news.email.er.invalid"/>
                                                        </div>
                                                    </li>
                                                    <li ng-if="firstD.popup.ex">
                                                        <div class="er-msg">
                                                            <spring:message code="yt.becomePartner.news.email.er.alr"/>
                                                        </div>
                                                    </li>
                                                    <li>
                                                        <input type="button" value="<spring:message code="yt.becomePartner.news.email.btn"/>" ng-click="firstD.subcribe()" class="yt-btn"/>
                                                    </li>
                                                </ul>
                                            </div>
                                        </div>
                                        <div class="row" ng-if="firstD.popup.sc">
                                            <div class="sc">
                                                <spring:message code="yt.becomePartner.news.email.er.ty"/>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!--END NEWSLETTER POPUP-->
            </div>

            <%@include file="footer.jsp" %>
            <%@include file="header.jsp" %>
        </div>
    </body>
    <script>
        <c:if test="${not empty sessionScope.pnContact}">
        var pct = true;
        </c:if>
    </script>
</html>
