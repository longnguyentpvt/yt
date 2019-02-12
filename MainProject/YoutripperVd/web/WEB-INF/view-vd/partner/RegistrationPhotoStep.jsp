<%-- 
    Document   : RegistrationPhotoStep
    Created on : May 18, 2018, 12:02:59 PM
    Author     : nickn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<div id='photo-step' ng-controller="PhotoRegistration">
    <div class='section clearfix'>
        <div class='yt-small-container'>
            <div class='left-info'>
                <!--COVER-->
                <div class="question covers">
                    <div class="text">
                        <spring:message code="yt.pkgEditing.photoStep.cover.question"/>
                    </div>
                    <div class="answer">
                        <div class='options'>
                            <ul>
                                <li>
                                    <div class="yt-checkbox radio">
                                        <input type="radio" value="img" ng-model='photo.vars.cTy'>
                                        <span></span>
                                    </div>
                                    <spring:message code="yt.pkgEditing.photoStep.cover.option.photo"/>
                                </li>
                                <li>
                                    <div class="yt-checkbox radio">
                                        <input type="radio" value="video" ng-model='photo.vars.cTy'>
                                        <span></span>
                                    </div>
                                    <spring:message code="yt.pkgEditing.photoStep.cover.option.video"/>
                                </li>
                            </ul>
                        </div>
                        <div class="uploading">
                            <ul ng-show="photo.vars.cTy === 'img'">
                                <li ng-repeat="aC in photo.vars.cvs track by $index">
                                    <div class="aCover" ng-class="{'uploaded' : aC.length}">
                                        <div class="bg"></div>
                                        <div class="img">
                                            <img ng-src="{{aC}}"/>
                                        </div>
                                        <div class="btns">
                                            <div class='vertical-center-container'>
                                                <div class='center-content'>
                                                    <a href="" class="yt-btn white-btn center-txt upload-btn" ng-click="photo.funcs.ocBrowser($index)">
                                                        <svg width="30px" height="20px" viewBox="0 0 30 20">
                                                        <path style="fill: #F8F7F9" d="M26.1,2h-3.8c-0.3,0-0.6-0.2-0.8-0.4c-0.6-0.8-1-1.6-1.7-1.6H9.3C8.6,0,8.1,0.7,7.6,1.6
                                                              C7.4,1.8,7.1,2,6.7,2H2.1C0.5,2,0,3.2,0,4.9v12C0,18.5,0.5,20,2.1,20h24c1.7,0,3.9-1.4,3.9-3.1v-12C30,3.2,27.8,2,26.1,2z"/>
                                                        <path style="fill: #E94848" d="M14.1,4.9c3.3,0,6,2.7,6,6s-2.7,6-6,6c-3.3,0-6-2.7-6-6S10.8,4.9,14.1,4.9 M14.1,3.9
                                                              c-3.9,0-7,3.1-7,7s3.1,7,7,7s7-3.1,7-7S18,3.9,14.1,3.9L14.1,3.9z"/>
                                                        <circle style="fill: #FFFFFF" cx="2.1" cy="5.1" r="1.5"/>
                                                        </svg><br/>
                                                        <spring:message code="yt.pkgEditing.photoStep.cover.btn.coverupload"/>
                                                    </a>

                                                    <button class='yt-btn grey-btn edit-btn' ng-click="photo.funcs.ocBrowser($index)">
                                                        <spring:message code="yt.pkgEditing.photoStep.cover.btn.edit"/>
                                                    </button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </li>
                            </ul>
                            <ul ng-show="photo.vars.cTy === 'video'">
                                <li>
                                    <div class="aCover">
                                        <div class="bg"></div>
                                    </div>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
                <!--END COVER-->

                <!--PORTRAIT-->
                <div class="question portrait">
                    <div class="text">
                        <spring:message code="yt.pkgEditing.photoStep.portrait.question"/>
                    </div>
                    <div class="answer">
                        <div class='aPortrait' ng-class="{'uploaded' : photo.vars.ptI.length}">
                            <div class="bg">
                            </div>
                            <div class="img">
                                <img ng-src="{{photo.vars.ptI}}"/>
                            </div>
                            <div class="btns">
                                <div class='vertical-center-container'>
                                    <div class='center-content'>
                                        <a href="" class="yt-btn white-btn center-txt upload-btn">
                                            <svg width="30px" height="20px" viewBox="0 0 30 20">
                                            <path style="fill: #F8F7F9" d="M26.1,2h-3.8c-0.3,0-0.6-0.2-0.8-0.4c-0.6-0.8-1-1.6-1.7-1.6H9.3C8.6,0,8.1,0.7,7.6,1.6
                                                  C7.4,1.8,7.1,2,6.7,2H2.1C0.5,2,0,3.2,0,4.9v12C0,18.5,0.5,20,2.1,20h24c1.7,0,3.9-1.4,3.9-3.1v-12C30,3.2,27.8,2,26.1,2z"/>
                                            <path style="fill: #E94848" d="M14.1,4.9c3.3,0,6,2.7,6,6s-2.7,6-6,6c-3.3,0-6-2.7-6-6S10.8,4.9,14.1,4.9 M14.1,3.9
                                                  c-3.9,0-7,3.1-7,7s3.1,7,7,7s7-3.1,7-7S18,3.9,14.1,3.9L14.1,3.9z"/>
                                            <circle style="fill: #FFFFFF" cx="2.1" cy="5.1" r="1.5"/>
                                            </svg><br/>
                                            <spring:message code="yt.pkgEditing.photoStep.portrait.btn.upload"/>
                                        </a>
                                        <button class='yt-btn grey-btn edit-btn'>
                                            <spring:message code="yt.pkgEditing.photoStep.portrait.btn.edit"/>
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!--END PORTRAIT-->

                <!--THUMBNAIL-->
                <div class="question">
                    <div class="text">
                        <spring:message code="yt.pkgEditing.photoStep.picture.question"/>
                    </div>
                    <div class="answer">
                        <div class='thumbnails'>
                            <ul>
                                <li ng-repeat="aP in photo.vars.tps track by $index">
                                    <div class='aThumbnail' ng-class="{'uploaded' : aP.length}">
                                        <div class='bg'></div>
                                        <div class="img" ng-if="aP.length" 
                                             ng-style="{'background-image': ('url(' + aP + ')')}">
                                        </div>
                                        <div class="btns">
                                            <div class='vertical-center-container'>
                                                <div class='center-content'>
                                                    <a href="" class="yt-btn white-btn center-txt upload-btn">
                                                        <svg width="30px" height="20px" viewBox="0 0 30 20">
                                                        <path style="fill: #F8F7F9" d="M26.1,2h-3.8c-0.3,0-0.6-0.2-0.8-0.4c-0.6-0.8-1-1.6-1.7-1.6H9.3C8.6,0,8.1,0.7,7.6,1.6
                                                              C7.4,1.8,7.1,2,6.7,2H2.1C0.5,2,0,3.2,0,4.9v12C0,18.5,0.5,20,2.1,20h24c1.7,0,3.9-1.4,3.9-3.1v-12C30,3.2,27.8,2,26.1,2z"/>
                                                        <path style="fill: #E94848" d="M14.1,4.9c3.3,0,6,2.7,6,6s-2.7,6-6,6c-3.3,0-6-2.7-6-6S10.8,4.9,14.1,4.9 M14.1,3.9
                                                              c-3.9,0-7,3.1-7,7s3.1,7,7,7s7-3.1,7-7S18,3.9,14.1,3.9L14.1,3.9z"/>
                                                        <circle style="fill: #FFFFFF" cx="2.1" cy="5.1" r="1.5"/>
                                                        </svg><br/>
                                                        <spring:message code="yt.pkgEditing.photoStep.picture.btn.upload"/>
                                                    </a>

                                                    <button class='yt-btn grey-btn edit-btn'>
                                                        <spring:message code="yt.pkgEditing.photoStep.picture.btn.edit"/>
                                                    </button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </li>
                                <li ng-repeat="aP in photo.vars.aps">
                                    <div class='aThumbnail uploaded'>
                                        <div class='bg'></div>
                                        <div class="img" ng-style="{'background-image': ('url(' + aP.pictureName + ')')}">
                                        </div>
                                        <div class="btns">
                                            <button class='yt-btn grey-btn edit-btn'>
                                                <spring:message code="yt.pkgEditing.photoStep.picture.btn.edit"/>
                                            </button>
                                            <span class="close-btn"></span>
                                        </div>
                                    </div>
                                </li>
                                <li>
                                    <div class='aThumbnail more'>
                                        <div class='bg'></div>
                                        <div class="btns">
                                            <div class='vertical-center-container'>
                                                <div class='center-content'>
                                                    <div class='a-btn'>
                                                        <span class="auto-ctrl plus-ctrl"></span>
                                                        <spring:message code="yt.pkgEditing.photoStep.picture.btn.more"/>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
                <!--END THUMBNAIL-->
            </div>

            <div class='instructions'>
                <div class='aInstruction'>
                    <p>
                        <spring:message code="yt.pkgEditing.photoStep.cover.instruction"/>
                    </p>
                </div>
            </div>
        </div>
    </div>

    <input type="file" id="cover-file" style="display: none" 
           accept="image/png,image/jpeg" image-file
           ng-model="photo.vars.cFile" 
           ng-change="photo.funcs.cFileChanged()"/>   

    <!--COVER EDITING POPUP-->
    <div class="yt-popup" id="cover-popup" ng-class="{'active' : photo.vars.cPop.active}"
         ng-mousemove="photo.funcs.rCover($event)"
         ng-mouseup="photo.funcs.erCover()">
        <div class="vertical-center-container">
            <div class="center-content">
                <div class="popup">
                    <div class="center-spinner-block" ng-class="{'loading' : photo.vars.cPop.uploading}">
                        <div class="vertical-center-container">
                            <div class="center-content">
                                <span class="yt-spinner center"></span>
                            </div>
                        </div>
                    </div>

                    <div class="body">
                        <div class="container">
                            <div class="row">
                                <div class='aCover uploaded'>
                                    <div class="bg"></div>
                                    <div class="img" ng-if="photo.vars.cPop.imgI >= 0"  ng-style="photo.vars.cPop.cstyle"
                                         ng-mousedown="photo.funcs.smCover($event)"
                                         ng-mousemove="photo.funcs.mCover($event)"
                                         ng-mouseleave="photo.funcs.emCover()"
                                         ng-mouseup="photo.funcs.emCover()">
                                        <img ng-src="{{photo.vars.cPop.imgS}}"/>
                                    </div>

                                    <button class='yt-btn grey-btn edit-btn'  ng-click="photo.funcs.ocBrowser()">
                                        <spring:message code="yt.pkgEditing.photoStep.cover.btn.edit"/>
                                    </button>
                                </div>
                            </div>

                            <div class="row">
                                <div class="resizer">
                                    <div class="origin-bar">
                                        <div class="width-bar" ng-style="photo.vars.cPop.resizer.s">
                                            <span class="dragger" ng-mousedown="photo.funcs.srCover($event)"></span>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="row">
                                <div class="confirm-btns">
                                    <ul class="clearfix">
                                        <li>
                                            <input type="button" class="yt-btn" value="Crop and Save" ng-click="photo.funcs.cropCover()">
                                        </li>
                                        <li>
                                            <input type="button" class="yt-btn red-btn" value="Cancel" ng-click="photo.funcs.cCoPop()">
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
    <!--END COVER EDITING POPUP-->
</div>