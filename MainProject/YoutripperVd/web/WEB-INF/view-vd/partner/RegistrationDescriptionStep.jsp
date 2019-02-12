<%-- 
    Document   : RegistrationDescriptionStep
    Created on : Apr 10, 2018, 12:20:40 PM
    Author     : nickn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<div id='description-step' ng-controller="DescriptionRegistration">
    <!--LANGUAGE TAB-->
    <div class="language-tab">
        <div class="yt-small-container">
            <ul class="clearfix">
                <li ng-repeat="aLan in description.vars.DATA.descriptionLanguages"
                    ng-class="{'active' : aLan[0] === description.vars.selectedLanguage}" ng-if="description.vars.contentHM[aLan[0]].registered">
                    <div class="aLanguage" ng-class="{'success' : aLan[2]}" ng-click="description.funcs.changeDL(aLan[0])">
                        <span class="error"></span> {{aLan[1]}}
                    </div>
                    <span class="close-btn" ng-if="aLan[0] !== description.vars.DATA.mandatoryDescriptionLanguage"
                          ng-click="description.funcs.oDL(aLan[0])"></span>
                </li>
                <li ng-if="description.vars.lAdding">
                    <div class="aLanguage plus" ng-click="description.funcs.openAddingLanguage()">
                        <span class="auto-ctrl plus-ctrl"></span> Add more languages
                    </div>
                </li>
            </ul>
        </div>
    </div>
    <!--END LANGUAGE TAB-->

    <!--TITLE AND DESCRIPTION-->
    <div class="section grey clearfix">
        <div class='yt-small-container'>
            <div class='left-info'>
                <div class='question' ng-class="{'done' : description.vars.questions.pkgName.done[description.vars.selectedLanguage],
                            'mandatory': description.vars.questions.pkgName.mandatory}">
                    <div class='text'>
                        <spring:message code="yt.pkgEditing.descriptionStep.packageName.question"/>
                    </div>
                    <div class='answer'>
                        <input type="text" class="yt-input" 
                               placeholder="<spring:message code="yt.pkgEditing.descriptionStep.packageName.input"/>"
                               ng-model="description.vars.contentHM[description.vars.selectedLanguage].packageName"
                               ng-change="description.funcs.pkgNameChanged()"
                               ytmaxlength="50"/>
                    </div>
                </div>

                <div class='question last multi-descriptions' 
                     ng-class="{'done' : description.vars.questions.mDs.done[description.vars.selectedLanguage],
                            'mandatory': description.vars.questions.mDs.mandatory}">
                    <div class='text'>
                        <spring:message code="yt.pkgEditing.descriptionStep.description.question"/>
                    </div>
                    <div class='answer'>
                        <ul>
                            <li ng-repeat="aDescription in description.vars.contentHM[description.vars.selectedLanguage].multiDescriptions">
                                <div class="aDescription">
                                    <input type="text" class="yt-input" 
                                           placeholder="<spring:message code="yt.pkgEditing.descriptionStep.descriptionTitle.input"/>"
                                           ng-model="aDescription.title" ng-change="description.funcs.mdChanged()"
                                           ytmaxlength="50"/>
                                    <textarea class="yt-input"
                                              placeholder="<spring:message code="yt.pkgEditing.descriptionStep.descriptionContent.input"/>"
                                              ng-model="aDescription.description" ng-change="description.funcs.mdChanged()"
                                              ytmaxlength="1000"></textarea>
                                    <span class="close-btn"ng-if="$index > 0" 
                                          ng-click="description.funcs.removeMD($index)"></span>
                                </div>
                            </li>
                        </ul>
                    </div>
                    <div class="btn" style="margin-top: 25px">
                        <a href="" class="yt-btn" ng-click="description.funcs.addNMD()">
                            <spring:message code="yt.pkgEditing.descriptionStep.description.btn"/>
                        </a>
                    </div>
                </div>
            </div>
            <div class='instructions'>
                <div class='aInstruction'>
                    <p>
                        <spring:message code="yt.pkgEditing.descriptionStep.packageName.instruction"/>
                    </p>
                </div>
            </div>
        </div>
    </div>
    <!--END TITLE AND DESCRIPTION-->

    <!--LOCATION-->
    <div class="section clearfix">
        <div class='yt-small-container'>
            <div class='left-info'>
                <div class="preview-map">
                    <div class="map">

                    </div>
                </div>

                <div class='question location' ng-class="{'done' : description.vars.questions.activityLocation.done[description.vars.selectedLanguage],
                            'mandatory': description.vars.questions.activityLocation.mandatory}">
                    <div class='text'>
                        <spring:message code="yt.pkgEditing.descriptionStep.activityLocation.question"/>
                    </div>
                    <div class='answer'>
                        <div class="row" ng-repeat="aDescription in description.vars.contentHM[description.vars.selectedLanguage].activityLocations">
                            <div class="aLocation clearfix activity">
                                <div class="detail clearfix">
                                    <div class="btns">
                                        <a href="" class="yt-btn edit-btn" ng-click="description.funcs.enLoc('activity', aDescription, $index)">
                                            Edit
                                        </a>
                                        <a href="" class="yt-btn red-btn" ng-click="description.funcs.daLoc('activity', $index)">
                                            Delete
                                        </a>
                                    </div>

                                    <div class="info">
                                        <div class="name">
                                            {{$index + 1}}. {{aDescription.name}}
                                        </div>
                                        <svg  width="30px" height="42px" viewBox="0 0 30 42" class="svg">
                                        <path  class="icon" d="M30,14.8C30,6.6,23.4,0,15,0S0,6.6,0,14.8c0,0.2,0,0.4,0,0.6c0,0,0,10.2,9.8,23.7
                                               c0,0,0,0.2,0.1,0.2l0.1,0.1l0,0c1.1,1.5,2.9,2.6,5,2.6s3.9-1.1,5-2.6l0,0c10-13.8,10-24,10-24l0,0C30,15.3,30,15,30,14.8z M15.2,21
                                               c-3.4,0-6.3-2.8-6.3-6.2s2.9-6.1,6.3-6.1s6.3,2.8,6.3,6.1S18.6,21,15.2,21z"/>
                                        </svg>
                                        <p>
                                            {{aDescription.addr}}
                                        </p>
                                    </div>
                                </div>
                                <div class="editing clearfix">
                                    <div class="time-input">
                                        <div class="yt-clock-input right close-input">
                                            <div class="clock">
                                                <ul class="clearfix">
                                                    <li>
                                                        <div class="label">
                                                            Hour
                                                        </div>
                                                        <div class="input">
                                                            <select name="" class="yt-input"
                                                                    ng-change="description.funcs.lostC(aDescription, true, false, false)"
                                                                    ng-model="aDescription.sH">
                                                                <option ng-value="-1">HH</option>
                                                                <option ng-value="0">00</option>
                                                                <option ng-value="1">01</option>
                                                                <option ng-value="2">02</option>
                                                                <option ng-value="3">03</option>
                                                                <option ng-value="4">04</option>
                                                                <option ng-value="5">05</option>
                                                                <option ng-value="6">06</option>
                                                                <option ng-value="7">07</option>
                                                                <option ng-value="8">08</option>
                                                                <option ng-value="9">09</option>
                                                                <option ng-value="10">10</option>
                                                                <option ng-value="11">11</option>
                                                                <option ng-value="12">12</option>
                                                                <option ng-value="13">13</option>
                                                                <option ng-value="14">14</option>
                                                                <option ng-value="15">15</option>
                                                                <option ng-value="16">16</option>
                                                                <option ng-value="17">17</option>
                                                                <option ng-value="18">18</option>
                                                                <option ng-value="19">19</option>
                                                                <option ng-value="20">20</option>
                                                                <option ng-value="21">21</option>
                                                                <option ng-value="22">22</option>
                                                                <option ng-value="23">23</option>
                                                            </select>
                                                        </div>
                                                    </li>
                                                    <li>
                                                        <div class="label">
                                                            &nbsp;
                                                        </div>
                                                        <div class="text bold-txt">
                                                            :
                                                        </div>
                                                    </li>
                                                    <li>
                                                        <div class="label">
                                                            Minute
                                                        </div>
                                                        <div class="input">
                                                            <select name="" class="yt-input"
                                                                    ng-change="description.funcs.lostC(aDescription, true, false, false)"
                                                                    ng-model="aDescription.sM">
                                                                <option ng-value="-1">mm</option>
                                                                <option ng-value="0">00</option>
                                                                <option ng-value="5">05</option>
                                                                <option ng-value="10">10</option>
                                                                <option ng-value="15">15</option>
                                                                <option ng-value="20">20</option>
                                                                <option ng-value="25">25</option>
                                                                <option ng-value="30">30</option>
                                                                <option ng-value="35">35</option>
                                                                <option ng-value="40">40</option>
                                                                <option ng-value="45">45</option>
                                                                <option ng-value="50">50</option>
                                                                <option ng-value="55">55</option>
                                                            </select>
                                                        </div>
                                                    </li>
                                                </ul>
                                            </div>
                                            <input type="text" class="yt-input center-txt" placeholder="Start or Meeting Time" readonly="readonly" ng-model="aDescription.stT"/>
                                            <span class="close-btn" ng-show="aDescription.stT.length" ng-click="description.funcs.clsT(aDescription, true, false, false)"></span>
                                        </div>
                                        <div class="yt-clock-input right close-input">
                                            <div class="clock">
                                                <ul class="clearfix">
                                                    <li>
                                                        <div class="label">
                                                            Hour
                                                        </div>
                                                        <div class="input">
                                                            <select name="" class="yt-input"
                                                                    ng-change="description.funcs.loetC(aDescription, true, false, false)"
                                                                    ng-model="aDescription.eH">
                                                                <option ng-value="-1">HH</option>
                                                                <option ng-value="0">00</option>
                                                                <option ng-value="1">01</option>
                                                                <option ng-value="2">02</option>
                                                                <option ng-value="3">03</option>
                                                                <option ng-value="4">04</option>
                                                                <option ng-value="5">05</option>
                                                                <option ng-value="6">06</option>
                                                                <option ng-value="7">07</option>
                                                                <option ng-value="8">08</option>
                                                                <option ng-value="9">09</option>
                                                                <option ng-value="10">10</option>
                                                                <option ng-value="11">11</option>
                                                                <option ng-value="12">12</option>
                                                                <option ng-value="13">13</option>
                                                                <option ng-value="14">14</option>
                                                                <option ng-value="15">15</option>
                                                                <option ng-value="16">16</option>
                                                                <option ng-value="17">17</option>
                                                                <option ng-value="18">18</option>
                                                                <option ng-value="19">19</option>
                                                                <option ng-value="20">20</option>
                                                                <option ng-value="21">21</option>
                                                                <option ng-value="22">22</option>
                                                                <option ng-value="23">23</option>
                                                            </select>
                                                        </div>
                                                    </li>
                                                    <li>
                                                        <div class="label">
                                                            &nbsp;
                                                        </div>
                                                        <div class="text bold-txt">
                                                            :
                                                        </div>
                                                    </li>
                                                    <li>
                                                        <div class="label">
                                                            Minute
                                                        </div>
                                                        <div class="input">
                                                            <select name="" class="yt-input"
                                                                    ng-change="description.funcs.loetC(aDescription, true, false, false)"
                                                                    ng-model="aDescription.eM">
                                                                <option ng-value="-1">mm</option>
                                                                <option ng-value="0">00</option>
                                                                <option ng-value="5">05</option>
                                                                <option ng-value="10">10</option>
                                                                <option ng-value="15">15</option>
                                                                <option ng-value="20">20</option>
                                                                <option ng-value="25">25</option>
                                                                <option ng-value="30">30</option>
                                                                <option ng-value="35">35</option>
                                                                <option ng-value="40">40</option>
                                                                <option ng-value="45">45</option>
                                                                <option ng-value="50">50</option>
                                                                <option ng-value="55">55</option>
                                                            </select>
                                                        </div>
                                                    </li>
                                                </ul>
                                            </div>
                                            <input type="text" class="yt-input center-txt" placeholder="End or Finish Time" readonly="readonly" ng-model="aDescription.etT"/>
                                            <span class="close-btn" ng-show="aDescription.etT.length" ng-click="description.funcs.cleT(aDescription, true, false, false)"></span>
                                        </div>
                                    </div>
                                    <div class="description-input">
                                        <textarea class="yt-input " 
                                                  placeholder="Describe to your customers the best and most cost-effective options to get to this location."
                                                  ytmaxlength="300" ng-model="aDescription.desc" ng-change="description.funcs.alChanged()"></textarea>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <a href="" class="yt-btn" ng-click="description.funcs.anLoc('activity')">
                                <spring:message code="yt.pkgEditing.descriptionStep.activityLocation.btn.add"/>
                            </a>
                        </div>
                    </div>
                </div>

                <div class='question location'>
                    <div class='text'>
                        <spring:message code="yt.pkgEditing.descriptionStep.departureLocation.question"/>
                    </div>
                    <div class='answer'>
                        <div class="row" ng-repeat="aDescription in description.vars.contentHM[description.vars.selectedLanguage].departureLocations">
                            <div class="aLocation clearfix departure">
                                <div class="detail clearfix">
                                    <div class="btns">
                                        <a href="" class="yt-btn edit-btn" ng-click="description.funcs.enLoc('departure', aDescription, $index)">
                                            Edit
                                        </a>
                                        <a href="" class="yt-btn red-btn"  ng-click="description.funcs.daLoc('departure', $index)">
                                            Delete
                                        </a>
                                    </div>

                                    <div class="info">
                                        <div class="name">
                                            {{$index + 1}}. {{aDescription.name}}
                                        </div>
                                        <svg width="30px" height="42px" viewBox="0 0 30 42" class="svg">
                                        <path class="icon" d="M30,14.8C30,6.6,23.4,0,15,0S0,6.6,0,14.8c0,0.2,0,0.4,0,0.6c0,0,0,10.2,9.8,23.7
                                              c0,0,0,0.2,0.1,0.2l0.1,0.1l0,0c1.1,1.5,2.9,2.6,5,2.6s3.9-1.1,5-2.6l0,0c10-13.8,10-24,10-24l0,0C30,15.3,30,15,30,14.8z M15.2,21
                                              c-3.4,0-6.3-2.8-6.3-6.2s2.9-6.1,6.3-6.1s6.3,2.8,6.3,6.1S18.6,21,15.2,21z"></path>
                                        </svg>
                                        <p>
                                            {{aDescription.addr}}
                                        </p>
                                    </div>
                                </div>
                                <div class="editing clearfixclose-input">
                                    <div class="time-input">
                                        <div class="yt-clock-input right close-input">
                                            <div class="clock">
                                                <ul class="clearfix">
                                                    <li>
                                                        <div class="label">
                                                            Hour
                                                        </div>
                                                        <div class="input">
                                                            <select name="" class="yt-input"
                                                                    ng-change="description.funcs.lostC(aDescription, false, true, false)"
                                                                    ng-model="aDescription.sH">
                                                                <option ng-value="-1">HH</option>
                                                                <option ng-value="0">00</option>
                                                                <option ng-value="1">01</option>
                                                                <option ng-value="2">02</option>
                                                                <option ng-value="3">03</option>
                                                                <option ng-value="4">04</option>
                                                                <option ng-value="5">05</option>
                                                                <option ng-value="6">06</option>
                                                                <option ng-value="7">07</option>
                                                                <option ng-value="8">08</option>
                                                                <option ng-value="9">09</option>
                                                                <option ng-value="10">10</option>
                                                                <option ng-value="11">11</option>
                                                                <option ng-value="12">12</option>
                                                                <option ng-value="13">13</option>
                                                                <option ng-value="14">14</option>
                                                                <option ng-value="15">15</option>
                                                                <option ng-value="16">16</option>
                                                                <option ng-value="17">17</option>
                                                                <option ng-value="18">18</option>
                                                                <option ng-value="19">19</option>
                                                                <option ng-value="20">20</option>
                                                                <option ng-value="21">21</option>
                                                                <option ng-value="22">22</option>
                                                                <option ng-value="23">23</option>
                                                            </select>
                                                        </div>
                                                    </li>
                                                    <li>
                                                        <div class="label">
                                                            &nbsp;
                                                        </div>
                                                        <div class="text bold-txt">
                                                            :
                                                        </div>
                                                    </li>
                                                    <li>
                                                        <div class="label">
                                                            Minute
                                                        </div>
                                                        <div class="input">
                                                            <select name="" class="yt-input"
                                                                    ng-change="description.funcs.lostC(aDescription, false, true, false)"
                                                                    ng-model="aDescription.sM">
                                                                <option ng-value="-1">mm</option>
                                                                <option ng-value="0">00</option>
                                                                <option ng-value="5">05</option>
                                                                <option ng-value="10">10</option>
                                                                <option ng-value="15">15</option>
                                                                <option ng-value="20">20</option>
                                                                <option ng-value="25">25</option>
                                                                <option ng-value="30">30</option>
                                                                <option ng-value="35">35</option>
                                                                <option ng-value="40">40</option>
                                                                <option ng-value="45">45</option>
                                                                <option ng-value="50">50</option>
                                                                <option ng-value="55">55</option>
                                                            </select>
                                                        </div>
                                                    </li>
                                                </ul>
                                            </div>
                                            <input type="text" class="yt-input center-txt" placeholder="Start or Meeting Time" readonly="readonly" 
                                                   ng-model="aDescription.stT"/>
                                            <span class="close-btn" ng-show="aDescription.stT.length" ng-click="description.funcs.clsT(aDescription, false, true, false)"></span>
                                        </div>
                                        <div class="yt-clock-input right close-input">
                                            <div class="clock">
                                                <ul class="clearfix">
                                                    <li>
                                                        <div class="label">
                                                            Hour
                                                        </div>
                                                        <div class="input">
                                                            <select name="" class="yt-input"
                                                                    ng-change="description.funcs.loetC(aDescription, false, true, false)"
                                                                    ng-model="aDescription.eH">
                                                                <option ng-value="-1">HH</option>
                                                                <option ng-value="0">00</option>
                                                                <option ng-value="1">01</option>
                                                                <option ng-value="2">02</option>
                                                                <option ng-value="3">03</option>
                                                                <option ng-value="4">04</option>
                                                                <option ng-value="5">05</option>
                                                                <option ng-value="6">06</option>
                                                                <option ng-value="7">07</option>
                                                                <option ng-value="8">08</option>
                                                                <option ng-value="9">09</option>
                                                                <option ng-value="10">10</option>
                                                                <option ng-value="11">11</option>
                                                                <option ng-value="12">12</option>
                                                                <option ng-value="13">13</option>
                                                                <option ng-value="14">14</option>
                                                                <option ng-value="15">15</option>
                                                                <option ng-value="16">16</option>
                                                                <option ng-value="17">17</option>
                                                                <option ng-value="18">18</option>
                                                                <option ng-value="19">19</option>
                                                                <option ng-value="20">20</option>
                                                                <option ng-value="21">21</option>
                                                                <option ng-value="22">22</option>
                                                                <option ng-value="23">23</option>
                                                            </select>
                                                        </div>
                                                    </li>
                                                    <li>
                                                        <div class="label">
                                                            &nbsp;
                                                        </div>
                                                        <div class="text bold-txt">
                                                            :
                                                        </div>
                                                    </li>
                                                    <li>
                                                        <div class="label">
                                                            Minute
                                                        </div>
                                                        <div class="input">
                                                            <select name="" class="yt-input"
                                                                    ng-change="description.funcs.loetC(aDescription, false, true, false)"
                                                                    ng-model="aDescription.eM">
                                                                <option ng-value="-1">mm</option>
                                                                <option ng-value="0">00</option>
                                                                <option ng-value="5">05</option>
                                                                <option ng-value="10">10</option>
                                                                <option ng-value="15">15</option>
                                                                <option ng-value="20">20</option>
                                                                <option ng-value="25">25</option>
                                                                <option ng-value="30">30</option>
                                                                <option ng-value="35">35</option>
                                                                <option ng-value="40">40</option>
                                                                <option ng-value="45">45</option>
                                                                <option ng-value="50">50</option>
                                                                <option ng-value="55">55</option>
                                                            </select>
                                                        </div>
                                                    </li>
                                                </ul>
                                            </div>
                                            <input type="text" class="yt-input center-txt" placeholder="End or Finish Time" readonly="readonly" ng-model="aDescription.etT"/>
                                            <span class="close-btn" ng-show="aDescription.etT.length" ng-click="description.funcs.cleT(aDescription, false, true, false)"></span>
                                        </div>
                                    </div>
                                    <div class="description-input">
                                        <textarea class="yt-input " 
                                                  placeholder="Describe to your customers the best and most cost-effective options to get to this location."
                                                  ytmaxlength="300" ng-model="aDescription.desc" ng-change="description.funcs.dlChanged()"></textarea>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <a href="" class="yt-btn yellow-btn" ng-click="description.funcs.anLoc('departure')">
                                <spring:message code="yt.pkgEditing.descriptionStep.departureLocation.btn.add"/>
                            </a>
                        </div>
                    </div>
                </div>

                <div class='question location last'>
                    <div class='text'>
                        <spring:message code="yt.pkgEditing.descriptionStep.pickupLocation.question"/>
                    </div>
                    <div class='answer'>
                        <div class="row" ng-repeat="aDescription in description.vars.contentHM[description.vars.selectedLanguage].pickupLocations">
                            <div class="aLocation clearfix pickup">
                                <div class="detail clearfix">
                                    <div class="btns">
                                        <a href="" class="yt-btn edit-btn" ng-click="description.funcs.enLoc('pickup', aDescription, $index)">
                                            Edit
                                        </a>
                                        <a href="" class="yt-btn red-btn"  ng-click="description.funcs.daLoc('pickup', $index)">
                                            Delete
                                        </a>
                                    </div>

                                    <div class="info">
                                        <div class="name">
                                            {{$index + 1}}. {{aDescription.name}}
                                        </div>
                                        <svg  width="30px" height="42px" viewBox="0 0 30 42" class="svg">
                                        <path  class="icon" d="M30,14.8C30,6.6,23.4,0,15,0S0,6.6,0,14.8c0,0.2,0,0.4,0,0.6c0,0,0,10.2,9.8,23.7
                                               c0,0,0,0.2,0.1,0.2l0.1,0.1l0,0c1.1,1.5,2.9,2.6,5,2.6s3.9-1.1,5-2.6l0,0c10-13.8,10-24,10-24l0,0C30,15.3,30,15,30,14.8z M15.2,21
                                               c-3.4,0-6.3-2.8-6.3-6.2s2.9-6.1,6.3-6.1s6.3,2.8,6.3,6.1S18.6,21,15.2,21z"/>
                                        </svg>
                                        <p>
                                            {{aDescription.addr}}
                                        </p>
                                    </div>
                                </div>
                                <div class="editing clearfix">
                                    <div class="time-input">
                                        <div class="yt-clock-input right close-input">
                                            <div class="clock">
                                                <ul class="clearfix">
                                                    <li>
                                                        <div class="label">
                                                            Hour
                                                        </div>
                                                        <div class="input">
                                                            <select name="" class="yt-input"
                                                                    ng-change="description.funcs.lostC(aDescription, false, false, true)"
                                                                    ng-model="aDescription.sH">
                                                                <option ng-value="-1">HH</option>
                                                                <option ng-value="0">00</option>
                                                                <option ng-value="1">01</option>
                                                                <option ng-value="2">02</option>
                                                                <option ng-value="3">03</option>
                                                                <option ng-value="4">04</option>
                                                                <option ng-value="5">05</option>
                                                                <option ng-value="6">06</option>
                                                                <option ng-value="7">07</option>
                                                                <option ng-value="8">08</option>
                                                                <option ng-value="9">09</option>
                                                                <option ng-value="10">10</option>
                                                                <option ng-value="11">11</option>
                                                                <option ng-value="12">12</option>
                                                                <option ng-value="13">13</option>
                                                                <option ng-value="14">14</option>
                                                                <option ng-value="15">15</option>
                                                                <option ng-value="16">16</option>
                                                                <option ng-value="17">17</option>
                                                                <option ng-value="18">18</option>
                                                                <option ng-value="19">19</option>
                                                                <option ng-value="20">20</option>
                                                                <option ng-value="21">21</option>
                                                                <option ng-value="22">22</option>
                                                                <option ng-value="23">23</option>
                                                            </select>
                                                        </div>
                                                    </li>
                                                    <li>
                                                        <div class="label">
                                                            &nbsp;
                                                        </div>
                                                        <div class="text bold-txt">
                                                            :
                                                        </div>
                                                    </li>
                                                    <li>
                                                        <div class="label">
                                                            Minute
                                                        </div>
                                                        <div class="input">
                                                            <select name="" class="yt-input"
                                                                    ng-change="description.funcs.lostC(aDescription, false, false, true)"
                                                                    ng-model="aDescription.sM">
                                                                <option ng-value="-1">mm</option>
                                                                <option ng-value="0">00</option>
                                                                <option ng-value="5">05</option>
                                                                <option ng-value="10">10</option>
                                                                <option ng-value="15">15</option>
                                                                <option ng-value="20">20</option>
                                                                <option ng-value="25">25</option>
                                                                <option ng-value="30">30</option>
                                                                <option ng-value="35">35</option>
                                                                <option ng-value="40">40</option>
                                                                <option ng-value="45">45</option>
                                                                <option ng-value="50">50</option>
                                                                <option ng-value="55">55</option>
                                                            </select>
                                                        </div>
                                                    </li>
                                                </ul>
                                            </div>
                                            <input type="text" class="yt-input center-txt" placeholder="Start or Meeting Time" readonly="readonly" ng-model="aDescription.stT"/>
                                            <span class="close-btn" ng-show="aDescription.stT.length" ng-click="description.funcs.clsT(aDescription, false, false, true)"></span>
                                        </div>
                                        <div class="yt-clock-input right close-input">
                                            <div class="clock">
                                                <ul class="clearfix">
                                                    <li>
                                                        <div class="label">
                                                            Hour
                                                        </div>
                                                        <div class="input">
                                                            <select name="" class="yt-input"
                                                                    ng-change="description.funcs.loetC(aDescription, false, false, true)"
                                                                    ng-model="aDescription.eH">
                                                                <option ng-value="-1">HH</option>
                                                                <option ng-value="0">00</option>
                                                                <option ng-value="1">01</option>
                                                                <option ng-value="2">02</option>
                                                                <option ng-value="3">03</option>
                                                                <option ng-value="4">04</option>
                                                                <option ng-value="5">05</option>
                                                                <option ng-value="6">06</option>
                                                                <option ng-value="7">07</option>
                                                                <option ng-value="8">08</option>
                                                                <option ng-value="9">09</option>
                                                                <option ng-value="10">10</option>
                                                                <option ng-value="11">11</option>
                                                                <option ng-value="12">12</option>
                                                                <option ng-value="13">13</option>
                                                                <option ng-value="14">14</option>
                                                                <option ng-value="15">15</option>
                                                                <option ng-value="16">16</option>
                                                                <option ng-value="17">17</option>
                                                                <option ng-value="18">18</option>
                                                                <option ng-value="19">19</option>
                                                                <option ng-value="20">20</option>
                                                                <option ng-value="21">21</option>
                                                                <option ng-value="22">22</option>
                                                                <option ng-value="23">23</option>
                                                            </select>
                                                        </div>
                                                    </li>
                                                    <li>
                                                        <div class="label">
                                                            &nbsp;
                                                        </div>
                                                        <div class="text bold-txt">
                                                            :
                                                        </div>
                                                    </li>
                                                    <li>
                                                        <div class="label">
                                                            Minute
                                                        </div>
                                                        <div class="input">
                                                            <select name="" class="yt-input"
                                                                    ng-change="description.funcs.loetC(aDescription, false, false, true)"
                                                                    ng-model="aDescription.eM">
                                                                <option ng-value="-1">mm</option>
                                                                <option ng-value="0">00</option>
                                                                <option ng-value="5">05</option>
                                                                <option ng-value="10">10</option>
                                                                <option ng-value="15">15</option>
                                                                <option ng-value="20">20</option>
                                                                <option ng-value="25">25</option>
                                                                <option ng-value="30">30</option>
                                                                <option ng-value="35">35</option>
                                                                <option ng-value="40">40</option>
                                                                <option ng-value="45">45</option>
                                                                <option ng-value="50">50</option>
                                                                <option ng-value="55">55</option>
                                                            </select>
                                                        </div>
                                                    </li>
                                                </ul>
                                            </div>
                                            <input type="text" class="yt-input center-txt" placeholder="End or Finish Time" readonly="readonly" ng-model="aDescription.etT"/>
                                            <span class="close-btn" ng-show="aDescription.etT.length" ng-click="description.funcs.cleT(aDescription, false, false, true)"></span>
                                        </div>
                                    </div>
                                    <div class="description-input">
                                        <textarea class="yt-input " 
                                                  placeholder="Describe to your customers the best and most cost-effective options to get to this location."
                                                  ytmaxlength="300" ng-model="aDescription.desc" ng-change="description.funcs.plChanged()"></textarea>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <a href="" class="yt-btn green-btn" ng-click="description.funcs.anLoc('pickup')">
                                <spring:message code="yt.pkgEditing.descriptionStep.pickupLocation.btn.add"/>
                            </a>
                        </div>
                    </div>
                </div>
            </div>

            <div class='instructions'>
                <div class='aInstruction'>
                    <p>
                        <spring:message code="yt.pkgEditing.descriptionStep.location.instruction"/>
                    </p>
                </div>
            </div>
        </div>
    </div>

    <!--location popup-->
    <div class="yt-popup" id="location-popup" ng-class="{'active' : description.vars.popup.loc.active}">
        <div class="vertical-center-container">
            <div class="center-content">
                <div class="popup">
                    <div class="body">
                        <div class="container">
                            <div class="row">
                                <div class="editing-map">
                                    <div class="map"></div>
                                    <div class='input'>
                                        <input type='text' class='yt-input'
                                               placeholder="<spring:message code="yt.pkgEditing.descriptionStep.locationPopup.search.input"/>"/>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <input type='text' class='yt-input'
                                       ytmaxlength="50"
                                       placeholder="<spring:message code="yt.pkgEditing.descriptionStep.locationPopup.name.input"/>"
                                       ng-model="description.vars.popup.loc.name"/>
                            </div>
                            <div class="row">
                                <textarea class='yt-input'
                                          ytmaxlength="300" 
                                          special-area
                                          ng-model="description.vars.popup.loc.addr"
                                          placeholder="<spring:message code="yt.pkgEditing.descriptionStep.locationPopup.address.input"/>"></textarea>
                            </div>
                            <div class="row" ng-show="description.vars.popup.loc.err">
                                <span class="error-msg">Please fill all above information!</span>
                            </div>
                            <div class="row">
                                <div class="confirm-btns">
                                    <ul class="clearfix">
                                        <li>
                                            <input type="button" class="yt-btn" value="<spring:message code="yt.pkgEditing.descriptionStep.locationPopup.save.btn"/>"
                                                   ng-click="description.funcs.seLoc()">
                                        </li>
                                        <li>
                                            <input type="button" class="yt-btn red-btn" value="<spring:message code="yt.pkgEditing.descriptionStep.locationPopup.cancel.btn"/>"
                                                   ng-click="description.funcs.closeLoc()">
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
    <!--end location popup-->

    <!--END LOCATION-->

    <!--SEARCH ENGINE--> 
    <div class='section grey clearfix'>
        <div class='yt-small-container'>
            <div class='left-info'>
                <div class="question" ng-class="{'done' : description.vars.questions.kw.done[description.vars.selectedLanguage],
                            'mandatory': description.vars.questions.kw.mandatory}">
                    <div class='text'>
                        <spring:message code="yt.pkgEditing.descriptionStep.seo.keyword.question"/>
                    </div>
                    <div class="answer">
                        <div class='keyword-input'>
                            <div class='keywords'>
                                <ul class='clearfix'>
                                    <li ng-repeat="aKeyword in description.vars.contentHM[description.vars.selectedLanguage].keywords">
                                        <div class='aKeyword'>
                                            {{aKeyword}}
                                            <span class='close-btn' ng-click="description.funcs.dlKW($index)"></span>
                                        </div>
                                    </li>
                                </ul>
                            </div>
                            <input type="text" class="yt-input" 
                                   placeholder="<spring:message code="yt.pkgEditing.descriptionStep.seo.keyword.input"/>"
                                   ng-model="description.vars.kwTemp" ng-keydown="description.funcs.anKW($event)"
                                   ytmaxlength="20"/>
                        </div>
                    </div>
                </div>

                <div class="question" ng-class="{'done' : description.vars.questions.seoDes.done[description.vars.selectedLanguage],
                            'mandatory': description.vars.questions.seoDes.mandatory}">
                    <div class='text'>
                        <spring:message code="yt.pkgEditing.descriptionStep.seo.desc.question"/>
                    </div>
                    <div class='answer'>
                        <textarea class="yt-input" 
                                  placeholder="<spring:message code="yt.pkgEditing.descriptionStep.seo.desc.input"/>"
                                  ng-model="description.vars.contentHM[description.vars.selectedLanguage].googleDescription"
                                  ng-change="description.funcs.gdChanged()"
                                  ytmaxlength="200"></textarea>
                    </div>
                </div>

                <div class="question serving-language"  ng-class="{'done' : description.vars.questions.sLan.done,
                            'mandatory': description.vars.questions.sLan.mandatory}">
                    <div class='text'>
                        <spring:message code="yt.pkgEditing.descriptionStep.servingLanguage.question"/>
                    </div>
                    <div class='answer'>
                        <ul class="clearfix">
                            <li ng-repeat="aL in description.vars.DATA.servingLanguages">
                                <div class="checkbox-ctn">
                                    <div class="yt-checkbox">
                                        <input type="checkbox" ng-model="description.vars.sLanHM[aL[0]].s" ng-change="description.funcs.slChanged(aL[0])">
                                        <span></span>
                                    </div>
                                    {{aL[1]}}
                                </div>
                            </li>
                        </ul>
                    </div>
                </div>

                <div class='question last'>
                    <a href='' ng-click='registration.funcs.skipStep("photo", false)' class='yt-btn red-btn'>
                        <spring:message code="yt.pkgEditing.btns.save.txt"/>
                    </a>
                </div>
            </div>

            <div class='instructions'>
                <div class='aInstruction'>
                    <p>
                        <spring:message code="yt.pkgEditing.descriptionStep.seo.instruction"/>
                    </p>
                </div>
            </div>
        </div>
    </div>
    <!--END SEARCH ENGINE-->

    <!--ADDING LANGUAGE POPUP-->
    <div class="yt-popup" ng-class="{'active' : description.vars.popup.lAdding.active}">
        <div class="vertical-center-container">
            <div class="center-content">
                <div class="popup">
                    <div class="center-spinner-block" ng-class="{loading : description.vars.popup.lAdding.adding}">
                        <div class="vertical-center-container">
                            <div class="center-content">
                                <span class="yt-spinner center"></span>
                            </div>
                        </div>
                    </div>
                    <div class="body">
                        <div class="container">
                            <div class="row">
                                Which language would you like to add?
                            </div>
                            <div class="row">
                                <div class="ddl-input">
                                    <select class="yt-input" ng-model="description.vars.popup.lAdding.mL">
                                        <option ng-value="null">Please choose a language</option>
                                        <option ng-repeat="aLan in description.vars.DATA.descriptionLanguages" 
                                                ng-if="!description.vars.contentHM[aLan[0]].registered"
                                                ng-value="aLan[0]">
                                            {{aLan[1]}}
                                        </option>
                                    </select>
                                </div>
                            </div>
                            <div class="row">
                                <div class="confirm-btns">
                                    <ul class="clearfix">
                                        <li>
                                            <input type="button" class="yt-btn" value="Yes" ng-click="description.funcs.aNewL()">
                                        </li>
                                        <li>
                                            <input type="button" class="yt-btn red-btn" value="Cancel" ng-click="description.funcs.cancelAddingP()">
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
    <!--END ADDING LANGUAGE POPUP-->

    <!--LANGUAGE DELETING POPUP-->
    <div class="yt-popup" ng-class="{'active': description.vars.popup.lD.active}">
        <div class="vertical-center-container">
            <div class="center-content">
                <div class="popup">
                    <div class="center-spinner-block" ng-class="{loading : description.vars.popup.lD.dl}">
                        <div class="vertical-center-container">
                            <div class="center-content">
                                <span class="yt-spinner center"></span>
                            </div>
                        </div>
                    </div>
                    <div class="header med-txt">
                        Confirmation
                    </div>
                    <div class="body">
                        <div class="container">
                            <div class="row">
                                Do you want to continue to delete this language?
                            </div>
                            <div class="row">
                                <div class="confirm-btns">
                                    <ul class="clearfix">
                                        <li>
                                            <input type="button" class="yt-btn" 
                                                   value="Yes" ng-click="description.funcs.raL()">
                                        </li>
                                        <li>
                                            <input type="button" class="yt-btn red-btn" 
                                                   value="No" ng-click="POPUP.closePopup(description.vars.popup.lD)">
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
    <!--END LANGUAGE DELETING POPUP-->

    <!--ACTIVE EDITING POPUP-->
    <div class="yt-popup" ng-class="{'active': description.vars.popup.activeEditing.active}">
        <div class="vertical-center-container">
            <div class="center-content">
                <div class="popup">
                    <div class="center-spinner-block" ng-class="{loading : description.vars.popup.activeEditing.updating}">
                        <div class="vertical-center-container">
                            <div class="center-content">
                                <span class="yt-spinner center"></span>
                            </div>
                        </div>
                    </div>
                    <div class="header med-txt">
                        Confirmation
                    </div>
                    <div class="body">
                        <div class="container">
                            <div class="row">
                                Require Approval?
                            </div>
                            <div class="row">
                                <div class="confirm-btns">
                                    <ul class="clearfix">
                                        <li>
                                            <input type="button" class="yt-btn" 
                                                   value="Yes" ng-click="description.funcs.acceptApproval()">
                                        </li>
                                        <li>
                                            <input type="button" class="yt-btn red-btn" 
                                                   value="Cancel" ng-click="description.funcs.cancelEditing()">
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
    <!--END ACTIVE EDITING POPUP-->
</div>