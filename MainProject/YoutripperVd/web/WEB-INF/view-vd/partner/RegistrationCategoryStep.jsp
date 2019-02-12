<%-- 
    Document   : RegistrationCategoryStep
    Created on : Jan 24, 2018, 4:03:10 PM
    Author     : nickn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<div id='category-step'  ng-controller="CategoryRegistration">
    <!--CATEGORY-->
    <div class='section clearfix'>
        <div class='yt-small-container'>
            <div class='left-info'>
                <div class='question last mandatory' ng-class="{'done' : category.vars.questions.category.done}">
                    <div class='text'>
                        <spring:message code="yt.pkgEditing.categoryStep.category.question"/>
                    </div>
                    <div class='answer'>
                        <div class="categories">
                            <ul class="clearfix">
                                <li>
                                    <div class="aCategory"
                                         ng-class="{'editing' : category.vars.ytPackage.categoryID === category.vars.data.CATEGORIES.attractions}">
                                        <div class="content default" ng-click="category.vars.questions.category.categoryChanged(category.vars.data.CATEGORIES.attractions)">
                                            <div class="vertical-center-container">
                                                <div class="center-content">
                                                    <div class="detail">
                                                        <div class="img">
                                                            <div class="vertical-center-container">
                                                                <div class="center-content">
                                                                    <svg width="32.2px" height="50px" viewBox="0 0 32.2 50"><g> <g> <path style="fill:#4CBDC9;" d="M28.3,16.9c-0.3,0.1-0.6,0.1-1,0.2c0.3,0.2,0.6,0.5,0.9,0.7c0.3,0.3,0.7,0.4,1,0.4c0.4,0,0.8-0.2,1-0.5 c0.4-0.5,0.5-1.3,0.1-1.8C29.6,16.4,28.8,16.7,28.3,16.9z"></path> <path style="fill:#4CBDC9;" d="M31.9,11.7c-0.5-1.1-2.8-2.5-4.2-3.1c-0.6-0.3-1.3-0.1-1.6,0.5c-0.3,0.6-0.1,1.3,0.5,1.6 c1.4,0.7,2.9,1.7,3.2,2.1c0.1,0.2,0.1,0.3,0,0.4c-0.3,0.6-1.3,1.1-2,1.3c-0.5,0.1-1.4,0.3-2.2,0.4l0-0.1c0,0-0.1-0.2-0.4-0.4 c0.8-0.1,1.7-0.2,2.2-0.4c0.1,0,0.1,0,0.2-0.1c-1.4-0.9-3-1.5-5-1.8c-0.8,0.6-1.7,0.9-2.7,0.9c-1.1,0-2.1-0.4-2.9-1.1 c-1.6,0.1-3,0.4-4.1,0.7c-0.1,0-0.3-0.1-0.4-0.1H5.2c-0.2,0-0.3,0-0.5,0.1c-1.2-1.4-2.4-3.4-1.5-4.8c1.3-1.8,4.9-2.5,6.2-2.6 c0.8-0.1,1.4-0.8,1.3-1.6c-0.1-0.8-0.8-1.4-1.6-1.3C8.6,2.3,3.1,2.8,0.9,6c-0.9,1.4-1.6,3.7,0.7,7c0.7,1,1.5,1.9,2,2.4v11.7 c0,0.1,0,0.2,0,0.3L0.9,48c-0.1,0.9,0.5,1.7,1.4,1.8L3.2,50c0.9,0.1,1.7-0.5,1.8-1.4l2.5-19.6h2.6v19.4c0,0.9,0.7,1.6,1.6,1.6h0.8 c0.9,0,1.6-0.7,1.6-1.6V36.6h3v11.9c0,0.8,0.7,1.5,1.5,1.5h0.3c0.8,0,1.5-0.7,1.5-1.5V36.6h1.1v11.9c0,0.8,0.7,1.5,1.5,1.5h0.3 c0.8,0,1.5-0.7,1.5-1.5V36.6h5.4l-4.1-19.6c0.8-0.1,1.7-0.3,2.3-0.4c1-0.3,2.8-1.1,3.5-2.6C32.3,13.3,32.3,12.4,31.9,11.7z M14.2,25.3V15.2c0.6-0.1,1.4-0.3,2.2-0.4c-1,0.7-1.6,1.4-1.6,1.4V19l1.1-0.8L14.2,25.3z"></path> </g> <path style="fill:#4CBDC9;" d="M9.7,6.1c-0.4,0-0.8-0.2-1-0.5C8,5.8,7,6,6,6.3c0,0.2,0,0.4,0,0.6C6,9.2,7.8,11,10.1,11s4.1-1.8,4.1-4.1 c0-0.3,0-0.5-0.1-0.7H9.7z"></path> <path style="fill:#4CBDC9;" d="M20,4.2c-0.4,0-0.8,0.1-1.1,0.2v0.5c0,0.7-0.6,1.2-1.2,1.2h-1.1c-0.4,0.6-0.6,1.4-0.6,2.2 c0,2.3,1.8,4.1,4.1,4.1s4.1-1.8,4.1-4.1S22.3,4.2,20,4.2z"></path> <g> <path style="fill:#4CBDC9;" d="M17.6,0H9.8C9.3,0,8.9,0.4,8.9,0.9v1c0.2,0,0.3,0,0.3,0c0,0,0.1,0,0.1,0c1,0,1.8,0.8,1.9,1.7 c0.1,1-0.7,1.9-1.7,2c-0.1,0-0.1,0-0.2,0c0.1,0.1,0.3,0.2,0.5,0.2h7.8c0.5,0,0.9-0.4,0.9-0.9v-4C18.5,0.4,18.1,0,17.6,0z M17.7,4.8h-0.8V3.9h-0.1c-0.2,0.2-0.4,0.3-0.7,0.3s-0.6-0.1-0.7-0.3h0v0.9H15V3.7h-0.4c-0.2,0.1-0.4,0.2-0.6,0.2 s-0.4-0.1-0.6-0.2h-0.3v1.1h-0.9V0.9h5.4V4.8z"></path> <circle style="fill:#4CBDC9;" cx="14" cy="2.9" r="0.8"></circle> <circle style="fill:#4CBDC9;" cx="16" cy="3.3" r="0.8"></circle> </g> </g> </svg>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="name">
                                                            <spring:message code="yt.pkgEditing.categoryStep.category.attractions.name"/>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="content editing" ng-if="category.vars.ytPackage.categoryID === category.vars.data.CATEGORIES.attractions">
                                            <div class="name">
                                                <spring:message code="yt.pkgEditing.categoryStep.category.attractions.name"/>
                                            </div>
                                            <div class="input" ng-if="category.vars.questions.category.subs.length">
                                                <div class="ddl-input">
                                                    <select class="yt-input" ng-model="category.vars.ytPackage.subCategoryID"
                                                            ng-change="category.vars.questions.category.subCategoryChanged()">
                                                        <option ng-value="null">
                                                            Sub Category
                                                        </option>
                                                        <option ng-repeat="aSub in category.vars.questions.category.subs"
                                                                ng-value="aSub[0]">
                                                            {{aSub[1]}}
                                                        </option>
                                                        <option ng-value="category.vars.data.SUB_OTHER_ID">
                                                            <spring:message code="yt.pkgEditing.categoryStep.category.notListed.option"/>
                                                        </option>
                                                    </select>
                                                </div>
                                                <input type="text" class="yt-input" ng-model="category.vars.ytPackage.otherSubCategory"
                                                       ytmaxlength="30" ng-show="category.vars.ytPackage.subCategoryID === category.vars.data.SUB_OTHER_ID"
                                                       ng-change="category.vars.questions.category.othersCategoryChanged()"
                                                       placeholder="<spring:message code="yt.pkgEditing.categoryStep.category.notListed.input"/>"/>
                                            </div>
                                        </div>
                                    </div>
                                </li>
                                <li>
                                    <div class="aCategory"
                                         ng-class="{'editing' : category.vars.ytPackage.categoryID === category.vars.data.CATEGORIES.learning}">
                                        <div class="content default" ng-click="category.vars.questions.category.categoryChanged(category.vars.data.CATEGORIES.learning)">
                                            <div class="vertical-center-container">
                                                <div class="center-content">
                                                    <div class="detail">
                                                        <div class="img">
                                                            <div class="vertical-center-container">
                                                                <div class="center-content">
                                                                    <svg width="33.5px" height="58px" viewBox="0 0 33.5 58"> <defs> </defs> <g> <circle style="fill:#4CBDC9;" cx="21.8" cy="15.1" r="4.1"></circle> <path style="fill:#4CBDC9;" d="M33.4,38c-0.1-0.2-1.5-5-3.5-8.6c-1.8-3.4-4.1-5.9-4.6-6.5C24.5,22,23,21.2,23,21.2s-0.5,0.9-1.2,3.5 c-0.7,2.6-1.1,4.9-1.1,4.9s-0.4-1.3-0.6-4c-0.2-2.7-0.2-5.2-0.2-5.2s-1.3-0.3-2.5,0.3c-0.2,0.1-0.5,0.2-0.6,0.4c0,0-0.5,0.5-1,1.1 c-0.8-0.9-2.1-2.8-2.9-4.5c-1.2-2.6-1.9-6.1-1.9-6.1c-0.2-1-1.1-1.6-2.1-1.4c-1,0.2-1.6,1.1-1.4,2.1c0,0.2,0.8,4,2.1,6.9 c0.6,1.3,1.5,2.9,2.5,4.2c1.7,2.3,2.8,2.8,3.5,2.9c0.1,0,0.3,0,0.4,0c0.2,0,0.4,0,0.6-0.1c-0.5,3.3-1.4,7.3-2.7,11.4 c-2,6.1-3.6,10.7-3.6,10.7H13l-2.9,6.9c-0.4,0.8,0,1.8,0.9,2.2l0.8,0.3c0.8,0.4,1.8,0,2.2-0.9l3.6-8.5h6.1l1.5,8.2 c0.2,0.9,1,1.5,1.9,1.3l0.8-0.1c0.9-0.2,1.5-1,1.3-1.9l-1.3-7.5h3c0,0-1.5-8.2-2.9-15.2C29.1,36,30,39,30,39c0.2,0.8,1,1.3,1.7,1.3 c0.2,0,0.3,0,0.5-0.1C33.2,39.9,33.7,38.9,33.4,38z"></path> <path style="fill:#4CBDC9;" d="M11.3,0.4L4.6,0C4.5,0,4.3,0,4.3,0.1L0.1,4.9C-0.1,5.1,0,5.5,0.3,5.6L3,6.1l0.9,2.1C4,8.5,4.2,8.6,4.4,8.5 l6.1-2.3c0.2-0.1,0.3-0.3,0.2-0.5L9.9,3.4l0.9-1.2l4.1,3c0.2,0.2,0.5,0.1,0.7-0.1s0.1-0.5-0.1-0.7l-4.1-3l0.2-0.3 C11.8,0.8,11.6,0.4,11.3,0.4z"></path> </g> </svg>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="name">
                                                            <spring:message code="yt.pkgEditing.categoryStep.category.learning.name"/>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="content editing" ng-if="category.vars.ytPackage.categoryID === category.vars.data.CATEGORIES.learning">
                                            <div class="name">
                                                <spring:message code="yt.pkgEditing.categoryStep.category.learning.name"/>
                                            </div>
                                            <div class="input" ng-if="category.vars.questions.category.subs.length">
                                                <div class="ddl-input">
                                                    <select class="yt-input" ng-model="category.vars.ytPackage.subCategoryID"
                                                            ng-change="category.vars.questions.category.subCategoryChanged()">
                                                        <option value="">
                                                            Sub Category
                                                        </option>
                                                        <option ng-repeat="aSub in category.vars.questions.category.subs"
                                                                ng-value="aSub[0]">
                                                            {{aSub[1]}}
                                                        </option>
                                                        <option ng-value="category.vars.data.SUB_OTHER_ID">
                                                            <spring:message code="yt.pkgEditing.categoryStep.category.notListed.option"/>
                                                        </option>
                                                    </select>
                                                </div>
                                                <input type="text" class="yt-input" ng-model="category.vars.ytPackage.otherSubCategory"
                                                       ytmaxlength="30" ng-show="category.vars.ytPackage.subCategoryID === category.vars.data.SUB_OTHER_ID"
                                                       ng-change="category.vars.questions.category.othersCategoryChanged()"
                                                       placeholder="<spring:message code="yt.pkgEditing.categoryStep.category.notListed.input"/>"/>
                                            </div>
                                        </div>
                                    </div>
                                </li>
                                <li>
                                    <div class="aCategory"
                                         ng-class="{'editing' : category.vars.ytPackage.categoryID === category.vars.data.CATEGORIES.indoor}">
                                        <div class="content default" ng-click="category.vars.questions.category.categoryChanged(category.vars.data.CATEGORIES.indoor)">
                                            <div class="vertical-center-container">
                                                <div class="center-content">
                                                    <div class="detail">
                                                        <div class="img">
                                                            <div class="vertical-center-container">
                                                                <div class="center-content">
                                                                    <svg width="37.2px" height="59px" viewBox="0 0 37.2 59"> <defs> </defs> <g> <path style="fill:#4CBDC9;" d="M22.4,38.2c0-0.1,0-0.2,0-0.2l1.3-11.2c0.1,0,0.2,0,0.2,0c1.5,0,3.4-0.1,4.6-0.4c4.1-1.2,8-5.7,8.2-5.9 c0.7-0.8,0.6-1.9-0.2-2.6c-0.8-0.7-1.9-0.6-2.6,0.2c-0.9,1.1-3.9,4-6.5,4.8c-1.1,0.3-3.7,0.1-5.3,0l-0.6-0.1h0l-6.1-0.7l0,0 c-1-0.1-2.1-0.5-5.5-2.1c-3.3-1.5-7.1-4.2-7.1-4.2c-0.8-0.6-2-0.4-2.5,0.5c-0.6,0.8-0.4,2,0.5,2.5c0.2,0.1,4.1,2.8,7.6,4.5 c1.9,0.9,3.5,1.5,4.6,1.9l-1.3,11.5c0,0,0,0.1,0,0.1c-0.3,0.4-0.7,0.9-1.1,1.5c-1,1.4-2.2,3.2-2.6,4.6c-1.5,4.7,0,13.8,0.1,14.1 c0.2,1.2,0.8,1.8,2,1.7c0.4-0.1,0.6-0.1,1.2-0.2c0.9-0.3,1.3-0.8,1.1-2.3c-0.2-1.7-1-8.7-0.1-11.7c0.4-1.2,1.7-3.5,2.8-5l0.5-0.5 c0.1-0.1,0.1-0.1,0.1-0.2l2.7,0.3l6.4,18.7c0.3,0.9,1.2,1.3,2.1,1l0.8-0.3c0.9-0.3,1.3-1.2,1-2.1L22.4,38.2z"></path> <circle style="fill:#4CBDC9;" cx="19.1" cy="17.3" r="4.2"></circle> <path style="fill:#4CBDC9;" d="M5.7,3.4l2.9,1.3C9.3,5,10,4.3,9.8,3.6L8.5,0.5C8.3,0,7.6-0.2,7.2,0.3L5.5,2.1C5.1,2.5,5.2,3.2,5.7,3.4z"></path> <path style="fill:#4CBDC9;" d="M27.8,10.3l4.9,7.5L32.5,18l0.4,0.7c0.3-0.3,0.6-0.6,0.8-0.8c0.1-0.1,0.2-0.2,0.2-0.2l-0.2-0.4l-0.3,0.2 l-4.9-7.5c1.8-1.4,2.1-4.1,0.1-6.4c-2.1-2.3-4.8-3-6.2-2.1c-1.4,1-1.6,4-0.3,6.5C23.4,10.5,25.9,11.3,27.8,10.3z"></path> <path style="fill:#4CBDC9;" d="M35.4,22.5l0.2,0.4l1.2-0.8l-0.4-0.6C36.2,21.7,35.8,22.1,35.4,22.5z"></path> </g> </svg>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="name">
                                                            <spring:message code="yt.pkgEditing.categoryStep.category.indoors.name"/>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="content editing" ng-if=" category.vars.ytPackage.categoryID === category.vars.data.CATEGORIES.indoor">
                                            <div class="name">
                                                <spring:message code="yt.pkgEditing.categoryStep.category.indoors.name"/>
                                            </div>
                                            <div class="input" ng-if="category.vars.questions.category.subs.length">
                                                <div class="ddl-input">
                                                    <select class="yt-input" ng-model="category.vars.ytPackage.subCategoryID"
                                                            ng-change="category.vars.questions.category.subCategoryChanged()">
                                                        <option ng-value="null">
                                                            Sub Category
                                                        </option>
                                                        <option ng-repeat="aSub in category.vars.questions.category.subs"
                                                                ng-value="aSub[0]">
                                                            {{aSub[1]}}
                                                        </option>
                                                        <option ng-value="category.vars.data.SUB_OTHER_ID">
                                                            <spring:message code="yt.pkgEditing.categoryStep.category.notListed.option"/>
                                                        </option>
                                                    </select>
                                                </div>
                                                <input type="text" class="yt-input" ng-model="category.vars.ytPackage.otherSubCategory"
                                                       ytmaxlength="30" ng-show="category.vars.ytPackage.subCategoryID === category.vars.data.SUB_OTHER_ID"
                                                       ng-change="category.vars.questions.category.othersCategoryChanged()"
                                                       placeholder="<spring:message code="yt.pkgEditing.categoryStep.category.notListed.input"/>"/>
                                            </div>
                                        </div>
                                    </div>
                                </li>
                                <li>
                                    <div class="aCategory"
                                         ng-class="{'editing' : category.vars.ytPackage.categoryID === category.vars.data.CATEGORIES.outdoor}">
                                        <div class="content default" ng-click="category.vars.questions.category.categoryChanged(category.vars.data.CATEGORIES.outdoor)">
                                            <div class="vertical-center-container">
                                                <div class="center-content">
                                                    <div class="detail">
                                                        <div class="img">
                                                            <div class="vertical-center-container">
                                                                <div class="center-content">
                                                                    <svg width="36px" height="54px" viewBox="0 0 36 54"> <defs> </defs> <g> <circle style="fill:#4CBDC9;" cx="19.2" cy="4.3" r="4.3"></circle> <path style="fill:#4CBDC9;" d="M34,40.3l-2.5-20.8c-0.3,0-0.7,0.1-1.3,0.1l2.5,21.2l-3.9,1.5c0.1-0.3,0.1-0.8,0-1.2 c-0.1-0.3,0.5-7.8-2.9-12.1c-1-1.2-5-3.8-6.5-4.8c-0.2-0.1-0.4-0.3-0.6-0.4l0.3-2.3c2.8,2.7,6.6,4.4,6.8,4.5c1,0.4,2.1,0,2.6-1 c0.4-1,0-2.1-1-2.6c-1.2-0.6-4.5-2.3-6.2-4.4c0,0,0,0,0,0c4.1,1.9,10.4,1,10.6,1c1.1-0.2,1.8-1.1,1.6-2.2c-0.2-1.1-1.1-1.8-2.2-1.6 c-1.5,0.2-5.9,0.5-8.5-0.7c-0.7-0.3-1.8-1.3-2.8-2.3c-0.2-0.8-0.8-1.7-2-2.2c-3.6-1.7-5.7-1-6.5-0.1c0-0.1,0-0.2,0-0.3l0,0 c0,0,0,0,0,0c0-2.1-1.7-3.8-3.8-3.8S4.1,7.5,4.1,9.6c0,0.3,0,0.5,0.1,0.8c-1.8,1.8-3.4,4.7-4.1,9.4c-0.9,5.8,8.3,11,9.3,4.7l0,0 l-0.1,0.8c-0.1,0.6,0.2,1.3,0.8,1.6l0.1,0.2c0.4,1.9,0.4,4.8,0.2,6.2c-0.6,3.3-5,9.7-6.1,11.2c-1,1.4-0.8,2.1-0.1,2.8 c0.1,0.1,0.2,0.2,0.3,0.3l-1.6-0.3l-2.6,0.9V54l3.1-3l5.5-0.1l3.9-3.2H18l3.6-2.3l4.6,0.5l9.8-3.4v-2.9L34,40.3z M15.1,45.2l-4,0.1 l-3,1.7c1.4-1.9,6.1-8.4,6.9-13c0.3-1.5,0.1-3.9-0.2-5.8c0-0.2,0-0.4-0.1-0.5l1.4,0.2c2,1.3,5.2,3.5,6,4.4c2.1,2.6,1.5,7.8,1.9,9.6 c0.1,0.4,0.2,0.7,0.3,0.9l-4-1.2L15.1,45.2z"></path> <path style="fill:#4CBDC9;" d="M29.8,14.9c0.5,0,0.9-0.1,1.3-0.1l0-0.1c0-0.4-0.4-0.6-0.7-0.6C30,14.1,29.7,14.4,29.8,14.9L29.8,14.9z"></path> </g> </svg>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="name">
                                                            <spring:message code="yt.pkgEditing.categoryStep.category.outdoors.name"/>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="content editing" ng-if=" category.vars.ytPackage.categoryID === category.vars.data.CATEGORIES.outdoor">
                                            <div class="name">
                                                <spring:message code="yt.pkgEditing.categoryStep.category.outdoors.name"/>
                                            </div>
                                            <div class="input" ng-if="category.vars.questions.category.subs.length">
                                                <div class="ddl-input">
                                                    <select class="yt-input" ng-model="category.vars.ytPackage.subCategoryID"
                                                            ng-change="category.vars.questions.category.subCategoryChanged()">
                                                        <option ng-value="null">
                                                            Sub Category
                                                        </option>
                                                        <option ng-repeat="aSub in category.vars.questions.category.subs"
                                                                ng-value="aSub[0]">
                                                            {{aSub[1]}}
                                                        </option>
                                                        <option ng-value="category.vars.data.SUB_OTHER_ID">
                                                            <spring:message code="yt.pkgEditing.categoryStep.category.notListed.option"/>
                                                        </option>
                                                    </select>
                                                </div>
                                                <input type="text" class="yt-input" ng-model="category.vars.ytPackage.otherSubCategory"
                                                       ytmaxlength="30" ng-show="category.vars.ytPackage.subCategoryID === category.vars.data.SUB_OTHER_ID"
                                                       ng-change="category.vars.questions.category.othersCategoryChanged()"
                                                       placeholder="<spring:message code="yt.pkgEditing.categoryStep.category.notListed.input"/>"/>
                                            </div>
                                        </div>
                                    </div>
                                </li>
                                <li>
                                    <div class="aCategory"
                                         ng-class="{'editing' : category.vars.ytPackage.categoryID === category.vars.data.CATEGORIES.extreme}">
                                        <div class="content default" ng-click="category.vars.questions.category.categoryChanged(category.vars.data.CATEGORIES.extreme)">
                                            <div class="vertical-center-container">
                                                <div class="center-content">
                                                    <div class="detail">
                                                        <div class="img">
                                                            <div class="vertical-center-container">
                                                                <div class="center-content">
                                                                    <svg width="50px" height="61.4px" viewBox="0 0 50 61.4"> <defs> </defs> <g> <circle style="fill:#4CBDC9;" cx="27" cy="25.2" r="3.7"></circle> <path style="fill:#4CBDC9;" d="M0.9,16.8c0.2,0.1,0.4,0.1,0.6,0.1c0.6,0,1.2-0.4,1.4-0.9c0,0,0.3-0.6,0.8-1.5L14,23.5 c0.2-0.1,0.3-0.3,0.5-0.3c0.1,0,0.2-0.1,0.3-0.1L4.2,13.8c0.7-1,1.6-2.2,3-3.4C9,8.7,11,7.3,13.3,6.2l10.2,24.7l-0.1,0l-6.2-5.5 c0.3,0.7,0.8,1.5,1.3,2.3l2.9,2.6c-0.7-0.3-1.4-0.5-1.8-0.8c-1.6-1.2-2.9-4-3.3-5c-0.3-0.7-1.1-1.1-1.8-0.8 c-0.7,0.3-1.1,1.1-0.8,1.8c0.1,0.2,1.6,4.3,4.2,6.3c0.7,0.6,2.1,1.1,3.1,1.5c0.7,0.3,1.3,0.5,1.5,0.5c0,0,0,0,0,0l1.4,11 c0.1,0.7,0.5,1.4,1.2,1.7l3.1,13.8c0.2,0.9,1.1,1.4,1.9,1.2l0.5-0.1c0.9-0.2,1.4-1.1,1.2-1.9l-2.9-13l3.4-0.4 c0.3,0,0.6-0.2,0.9-0.3L39,58.3c0.4,0.8,1.3,1.2,2.1,0.8l0.5-0.2c0.8-0.4,1.2-1.3,0.8-2.1l-6.2-14.2c-0.3-0.7-1.1-1.1-1.9-0.9 l-1.2-9.2c0.2-0.2,0.7-0.5,1.2-0.9c0.9-0.7,2-1.6,2.5-2.4c1.9-2.6,2.9-8.3,2.9-8.5c0-0.8-0.5-1.4-1.3-1.4c-0.8,0-1.4,0.5-1.4,1.3 c-0.1,1.1-1.2,5.4-2.4,7c-0.2,0.3-0.8,0.8-1.4,1.2l2-3.4l0,0c0.4-1.1,0.8-2.3,1-3.3l-4.7,7.7L34,3.4c5.3,0.8,8.9,2.7,11,4.2 l-6.7,11.2c0,0,0.1,0,0.1,0c0,0,0.1,0,0.1,0c0.2,0,0.5,0.1,0.7,0.2l6.6-10.9c1.1,0.8,1.6,1.4,1.6,1.4c0.5,0.6,1.5,0.7,2.1,0.2 c0.6-0.5,0.7-1.5,0.2-2.1c-0.1-0.1-2.1-2.5-6.5-4.5c-3.9-1.9-10.6-3.8-20.1-2.7C13.9,1.4,8.2,5.2,5,8.2c-3.5,3.3-4.8,6.4-4.9,6.5 C-0.2,15.6,0.2,16.4,0.9,16.8z M14.1,5.8c2.8-1.2,5.9-2.1,9.3-2.5c3.7-0.4,6.9-0.4,9.7,0L30.8,30c-0.1,0-0.2,0-0.3,0l-5.8,0.7 c-0.1,0-0.2,0-0.2,0L14.1,5.8z"></path> </g> </svg>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="name">
                                                            <spring:message code="yt.pkgEditing.categoryStep.category.extreme.name"/>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="content editing" ng-if=" category.vars.ytPackage.categoryID === category.vars.data.CATEGORIES.extreme">
                                            <div class="name">
                                                <spring:message code="yt.pkgEditing.categoryStep.category.extreme.name"/>
                                            </div>
                                            <div class="input" ng-if="category.vars.questions.category.subs.length">
                                                <div class="ddl-input">
                                                    <select class="yt-input" ng-model="category.vars.ytPackage.subCategoryID"
                                                            ng-change="category.vars.questions.category.subCategoryChanged()">
                                                        <option ng-value="null">
                                                            Sub Category
                                                        </option>
                                                        <option ng-repeat="aSub in category.vars.questions.category.subs"
                                                                ng-value="aSub[0]">
                                                            {{aSub[1]}}
                                                        </option>
                                                        <option ng-value="category.vars.data.SUB_OTHER_ID">
                                                            <spring:message code="yt.pkgEditing.categoryStep.category.notListed.option"/>
                                                        </option>
                                                    </select>
                                                </div>
                                                <input type="text" class="yt-input" ng-model="category.vars.ytPackage.otherSubCategory"
                                                       ytmaxlength="30" ng-show="category.vars.ytPackage.subCategoryID === category.vars.data.SUB_OTHER_ID"
                                                       ng-change="category.vars.questions.category.othersCategoryChanged()"
                                                       placeholder="<spring:message code="yt.pkgEditing.categoryStep.category.notListed.input"/>"/>
                                            </div>
                                        </div>
                                    </div>
                                </li>
                                <li>
                                    <div class="aCategory"
                                         ng-class="{'editing' : category.vars.ytPackage.categoryID === category.vars.data.CATEGORIES.water}">
                                        <div class="content default" ng-click="category.vars.questions.category.categoryChanged(category.vars.data.CATEGORIES.water)">
                                            <div class="vertical-center-container">
                                                <div class="center-content">
                                                    <div class="detail">
                                                        <div class="img">
                                                            <div class="vertical-center-container">
                                                                <div class="center-content">
                                                                    <svg width="41.1px" height="53px" viewBox="0 0 41.1 53"> <defs> </defs> <g> <path style="fill:#4CBDC9;" d="M8.4,46.5c0.3,0.9,1.3,1.4,2.2,1.1l0.8-0.3c0.9-0.3,1.4-1.3,1.1-2.2L6.9,27.9l11.4,14.2 c0.6,0.8,1.7,0.9,2.5,0.3l0.7-0.5c0.8-0.6,0.9-1.7,0.3-2.5L10.4,25.4c0-0.9-0.1-4.6-0.7-8.2c0.4,0.2,0.7,0.3,0.9,0.4 c1.5,0.4,6.4,0.2,7.5,0.2c1.1,0,1.7-0.3,1.9-1.1l0.1-0.4c0.2-0.8-0.3-1.7-1.1-1.9c0,0-0.1,0-0.4,0l-0.1-0.3l22.5-2.3L41,11.1 l-22.7,2.3l-0.5-1.4c-0.1-0.3-0.4-0.6-0.7-0.5c-0.3,0.1-0.6,0.4-0.5,0.7l0.8,2c-2.1,0-5.4-0.1-5.8-0.2c-0.1,0-0.5-0.2-1.1-0.5 l6.1,0l-0.5-1.2l0,0c-0.1-0.3,0-0.6,0.2-0.8c0.2-0.2,0.4-0.4,0.7-0.5c0.1,0,0.2,0,0.3,0c0.5,0,0.9,0.4,1,0.8l0.2,0.4 c0-0.1,0-0.1,0-0.2l0-0.4c0-0.9-0.6-1.6-1.5-1.7l-13.2,0c0,0,0,0,0,0c-1.3-0.1-2.4,0.1-2.4,0.1C0.6,10.3-0.1,11.1,0,12l1.4,14.5 c0,0.4,0.3,0.8,0.6,1.1L8.4,46.5z"></path> <circle style="fill:#4CBDC9;" cx="4.5" cy="4.4" r="4.4"></circle> <path style="fill:#4CBDC9;" d="M38.7,50.1c-0.6,0.3-1.3,0.5-2.2,0.4c-1.3-0.2-2.1-1.4-2.5-2.3c-0.2-0.3-0.6-0.3-0.8,0c-0.6,1-1.7,2.6-3.2,2.6 c-1.4,0-2.3-1.5-2.7-2.5c-0.1-0.3-0.6-0.4-0.8-0.1c-0.6,0.8-1.7,2.1-3.2,2.1c-1.4,0-2.1-1-2.4-1.8c-0.1-0.3-0.6-0.4-0.8-0.1 c-0.6,1-1.7,2.3-3.2,2.3c-1.3,0-2-1-2.3-1.9c-0.1-0.4-0.6-0.4-0.8-0.1c-0.5,0.9-1.5,1.9-3.4,2c-1.7,0.1-2.5-1.2-2.8-2.2 c-0.1-0.3-0.5-0.4-0.8-0.1c-0.5,0.6-1.3,1.5-2.6,2.1c-0.7,0.3-1.3,0.3-1.8,0.2c-0.5-0.1-0.8,0.4-0.4,0.8c0.4,0.4,1,0.7,1.6,0.7 c2.2,0,3.4-1.7,3.4-1.7S8.1,53,10.4,53c2.3,0,3.4-1.7,3.4-1.7s1.1,1.7,3.2,1.7s3.7-2.1,3.7-2.1s1.2,2.1,3.2,2.1s3.2-2.2,3.2-2.2 s0.9,2.2,3.3,2.2c2.4,0,3.5-2.9,3.5-2.9s0.8,2.1,3.2,2.1c1,0,1.8-0.7,2.4-1.5C39.5,50.4,39.1,49.9,38.7,50.1z"></path> <path style="fill:#4CBDC9;" d="M18.7,18.2l0.1,0.2c0.1,0.3,0.3,0.5,0.6,0.5c0.1,0,0.1,0,0.2,0c0.3-0.1,0.6-0.4,0.5-0.7l-0.1-0.4 C19.5,18,19.1,18.1,18.7,18.2z"></path> <path style="fill:#4CBDC9;" d="M11.6,47.7L10.7,48c-0.2,0.1-0.5,0.1-0.7,0.1c-1,0-1.8-0.6-2.1-1.5l-0.2-0.7c-2.2,1.8-4,3.4-5,4.2 c0.2,0.1,0.3,0.1,0.5,0.1c0.3,0,0.6-0.1,0.9-0.2c1.2-0.6,2-1.4,2.4-1.9c0.2-0.2,0.4-0.3,0.7-0.3c0.4,0,0.7,0.2,0.8,0.6 c0.4,1.2,1.1,1.9,2.2,1.9c0,0,0.1,0,0.1,0c1.7-0.1,2.6-1,3.1-1.8c0.2-0.3,0.4-0.4,0.8-0.4c0.4,0,0.7,0.2,0.8,0.6 c0.4,1.1,1,1.6,1.9,1.6c1.3,0,2.4-1.3,2.9-2.1c0.2-0.3,0.4-0.4,0.7-0.4c0.4,0,0.7,0.2,0.8,0.5c0,0,0,0,0,0 c6.2-5.7,7.7-7.7,8.7-10.3c0.3-0.8,0.3-1.4-0.5-1.6c0,0-2.8-0.8-3.7-0.8c-0.9,0-0.5,0.3-1.9,0.8c-0.8,0.3-1.3,0.4-3.2,1.3l1.2,1.4 c0.7,1,0.6,2.4-0.4,3.1L21,42.8c-0.4,0.3-0.8,0.4-1.3,0.4c-0.7,0-1.4-0.3-1.8-0.9l-1.7-2.2c-1.1,0.6-2.3,1.3-3.4,2.1 c-0.2,0.1-0.3,0.2-0.5,0.3L13,45c0.2,0.6,0.1,1.2-0.1,1.7C12.6,47.2,12.1,47.6,11.6,47.7z"></path> </g> </svg>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="name">
                                                            <spring:message code="yt.pkgEditing.categoryStep.category.water.name"/>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="content editing" ng-if=" category.vars.ytPackage.categoryID === category.vars.data.CATEGORIES.water">
                                            <div class="name">
                                                <spring:message code="yt.pkgEditing.categoryStep.category.water.name"/>
                                            </div>
                                            <div class="input" ng-if="category.vars.questions.category.subs.length">
                                                <div class="ddl-input">
                                                    <select class="yt-input" ng-model="category.vars.ytPackage.subCategoryID"
                                                            ng-change="category.vars.questions.category.subCategoryChanged()">
                                                        <option ng-value="null">
                                                            Sub Category
                                                        </option>
                                                        <option ng-repeat="aSub in category.vars.questions.category.subs"
                                                                ng-value="aSub[0]">
                                                            {{aSub[1]}}
                                                        </option>
                                                        <option ng-value="category.vars.data.SUB_OTHER_ID">
                                                            <spring:message code="yt.pkgEditing.categoryStep.category.notListed.option"/>
                                                        </option>
                                                    </select>
                                                </div>
                                                <input type="text" class="yt-input" ng-model="category.vars.ytPackage.otherSubCategory"
                                                       ytmaxlength="30" ng-show="category.vars.ytPackage.subCategoryID === category.vars.data.SUB_OTHER_ID"
                                                       ng-change="category.vars.questions.category.othersCategoryChanged()"
                                                       placeholder="<spring:message code="yt.pkgEditing.categoryStep.category.notListed.input"/>"/>
                                            </div>
                                        </div>
                                    </div>
                                </li>
                                <li>
                                    <div class="aCategory"
                                         ng-class="{'editing' : category.vars.ytPackage.categoryID === category.vars.data.CATEGORIES.spa}">
                                        <div class="content default" ng-click="category.vars.questions.category.categoryChanged(category.vars.data.CATEGORIES.spa)">
                                            <div class="vertical-center-container">
                                                <div class="center-content">
                                                    <div class="detail">
                                                        <div class="img">
                                                            <div class="vertical-center-container">
                                                                <div class="center-content">
                                                                    <svg width="41.4px" height="46px" viewBox="0 0 41.4 46"> <defs> </defs> <g> <circle style="fill:#4CBDC9;" cx="10.1" cy="4.5" r="4.5"></circle> <circle style="fill:#4CBDC9;" cx="36.1" cy="16.7" r="4.5"></circle> <path style="fill:#4CBDC9;" d="M40.8,34.3H24.9c0.3-0.1,0.6-0.4,0.9-0.6l7.6-8.1c1-1.1,1-2.9-0.1-3.9l-1.4-1.3c0-0.1-0.1-0.1-0.1-0.2 c-0.2-0.2-0.9-1.2-1.6-1.8c-0.9-0.9-2.1-2.1-3-2.6c-2.2-1.2-5.2-1.6-7-1.6c0.7,0.5,1,0.9,0.9,1.7c-0.1,0.5-0.5,1.1-1.1,1.4 c1.6,0.1,4.2,0.4,5.6,1.3c0.4,0.2,0.9,0.7,1.5,1.3l-7.5,8c-0.7,0.7-0.9,1.7-0.6,2.6l-3.5,0c0.6-0.2,1-0.7,1-1.3l0-0.7 c0-0.3-0.1-0.6-0.3-0.9c2.2-0.4,3-1.1,3-1.1s-2.7-2.9-5.4-9.5c2.7,0.6,5.6,0.2,5.8,0.2c0.8-0.1,1.3-0.8,1.2-1.6 c-0.1-0.8-0.8-1.3-1.6-1.2c-1.1,0.1-4.3,0.3-6.2-0.6c-0.1-0.1-0.3-0.2-0.5-0.3c-0.1-0.3-0.2-0.7-0.3-1c0,0-0.6-2.7-3.6-2.5 c-2.6,0.2-3.1,3-3.1,3S4.3,22.7,5.6,27l-2.5,0c-0.8,0-1.4,0.6-1.4,1.4l0,0.7c0,0.7,0.5,1.3,1.2,1.4c-0.7,0.2-1.2,0.8-1.2,1.6l0,0.9 c0,0.6,0.3,1.1,0.7,1.3H0.6c-0.3,0-0.6,0.3-0.6,0.6v6.5C0,41.7,0.3,42,0.6,42h0.3c0.3,0,0.6,0.3,0.6,0.6v2.8c0,0.3,0.3,0.6,0.6,0.6 h1.3c0.3,0,0.6-0.3,0.6-0.6v-2.8c0-0.3,0.3-0.6,0.6-0.6H37c0.3,0,0.6,0.3,0.6,0.6v2.8c0,0.3,0.3,0.6,0.6,0.6h1.3 c0.3,0,0.6-0.3,0.6-0.6v-2.8c0-0.3,0.3-0.6,0.6-0.6c0.3,0,0.6-0.3,0.6-0.6v-6.5C41.4,34.6,41.1,34.3,40.8,34.3z"></path> </g> </svg>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="name">
                                                            <spring:message code="yt.pkgEditing.categoryStep.category.spa.name"/>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="content editing" ng-if=" category.vars.ytPackage.categoryID === category.vars.data.CATEGORIES.spa">
                                            <div class="name">
                                                <spring:message code="yt.pkgEditing.categoryStep.category.spa.name"/>
                                            </div>
                                            <div class="input" ng-if="category.vars.questions.category.subs.length">
                                                <div class="ddl-input">
                                                    <select class="yt-input" ng-model="category.vars.ytPackage.subCategoryID"
                                                            ng-change="category.vars.questions.category.subCategoryChanged()">
                                                        <option ng-value="null">
                                                            Sub Category
                                                        </option>
                                                        <option ng-repeat="aSub in category.vars.questions.category.subs"
                                                                ng-value="aSub[0]">
                                                            {{aSub[1]}}
                                                        </option>
                                                        <option ng-value="category.vars.data.SUB_OTHER_ID">
                                                            <spring:message code="yt.pkgEditing.categoryStep.category.notListed.option"/>
                                                        </option>
                                                    </select>
                                                </div>
                                                <input type="text" class="yt-input" ng-model="category.vars.ytPackage.otherSubCategory"
                                                       ytmaxlength="30" ng-show="category.vars.ytPackage.subCategoryID === category.vars.data.SUB_OTHER_ID"
                                                       ng-change="category.vars.questions.category.othersCategoryChanged()"
                                                       placeholder="<spring:message code="yt.pkgEditing.categoryStep.category.notListed.input"/>"/>
                                            </div>
                                        </div>
                                    </div>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
            <div class='instructions'>
                <div class='aInstruction'>
                    <p>
                        <spring:message code="yt.pkgEditing.categoryStep.category.instruction"/>
                    </p>
                </div>
            </div>
        </div>
    </div>
    <!--END CATEGORY-->

    <div class='section grey clearfix'>
        <div class='yt-small-container'>
            <div class='left-info'>
                <!--SUITABILITY-->
                <div class='question mandatory' ng-class="{'done' : category.vars.questions.suitability.done}">
                    <div class='text'>
                        <spring:message code="yt.pkgEditing.categoryStep.suitability.question"/>
                    </div>
                    <div class='answer'>
                        <div class="suitability">
                            <ul class="hor-selection clearfix">
                                <li>
                                    <input type="checkbox" ng-model="category.vars.ytPackage.suitableCouples"
                                           ng-change="category.vars.questions.suitability.changed(true, false)">
                                    <span>
                                        <spring:message code="yt.pkgEditing.categoryStep.suitability.couple.option"/>
                                    </span>
                                </li>
                                <li>
                                    <input type="checkbox" ng-model="category.vars.ytPackage.suitableElderly"
                                           ng-change="category.vars.questions.suitability.changed(true, false)">
                                    <span>
                                        <spring:message code="yt.pkgEditing.categoryStep.suitability.elderly.option"/>
                                    </span>
                                </li>
                                <li>
                                    <input type="checkbox" ng-model="category.vars.ytPackage.suitableFamily"
                                           ng-change="category.vars.questions.suitability.changed(true, false)">
                                    <span>
                                        <spring:message code="yt.pkgEditing.categoryStep.suitability.family.option"/>
                                    </span>
                                </li>
                                <li>
                                    <input type="checkbox" ng-model="category.vars.ytPackage.suitableIndividual"
                                           ng-change="category.vars.questions.suitability.changed(true, true)">
                                    <span>
                                        <spring:message code="yt.pkgEditing.categoryStep.suitability.individual.option"/>
                                    </span>
                                </li>
                                <li>
                                    <input type="checkbox" ng-model="category.vars.ytPackage.suitableUniversal"
                                           ng-change="category.vars.questions.suitability.changed(false, true)">
                                    <span>
                                        <spring:message code="yt.pkgEditing.categoryStep.suitability.universal.option"/>
                                    </span>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
                <!--END SUITABILITY-->

                <!--GENDER-->
                <div class='question' ng-class="{'done' : category.vars.questions.gender.done,
                                'mandatory' : category.vars.questions.gender.mandatory}"
                     ng-show="category.vars.questions.gender.mandatory">
                    <div class='text'>
                        <spring:message code="yt.pkgEditing.categoryStep.gender.question"/>
                    </div>
                    <div class='answer'>
                        <div class="suitability">
                            <ul class="hor-selection clearfix">
                                <li>
                                    <input type="radio" value="male"
                                           ng-model="category.vars.ytPackage.genderSuitability"
                                           ng-change="category.vars.questions.suitability.changed()">
                                    <span>
                                        <spring:message code="yt.pkgEditing.categoryStep.gender.male.option"/>
                                    </span>
                                </li>
                                <li>
                                    <input type="radio" value="female"
                                           ng-model="category.vars.ytPackage.genderSuitability"
                                           ng-change="category.vars.questions.suitability.changed()">
                                    <span>
                                        <spring:message code="yt.pkgEditing.categoryStep.gender.female.option"/>
                                    </span>
                                </li>
                                <li>
                                    <input type="radio" value="unisex"
                                           ng-model="category.vars.ytPackage.genderSuitability"
                                           ng-change="category.vars.questions.suitability.changed()">
                                    <span>
                                        <spring:message code="yt.pkgEditing.categoryStep.gender.unisex.option"/>
                                    </span>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
                <!--END GENDER-->

                <!--TARGET LOCATION-->
                <div class='question last' ng-class="{'done' : category.vars.questions.target.done,
                        'mandatory' : category.vars.questions.target.mandatory}">
                    <div class='text'>
                        <spring:message code="yt.pkgEditing.categoryStep.locationCluster.question"/>
                    </div>
                    <div class='answer'>
                        <select name="" class="yt-input" 
                                ng-model="category.vars.ytPackage.targetLocationID"
                                ng-change="category.vars.questions.target.changed()">
                            <option ng-value="null">
                                <spring:message code="yt.pkgEditing.categoryStep.locationCluster.ddl"/>
                            </option>
                            <option ng-value="location.targetLocationID"
                                    ng-repeat="location in category.vars.locations">
                                {{location.locationName}}
                            </option>
                        </select>
                    </div>
                </div>
                <!--END TARGET LOCATION-->
            </div>

            <div class='instructions'>
                <div class='aInstruction'>
                    <p>
                        <spring:message code="yt.pkgEditing.categoryStep.suitability.instruction"/>
                    </p>
                </div>
            </div>
        </div>
    </div>

    <!--COLOR-->
    <div class='section grey clearfix'>
        <div class='yt-small-container'>
            <div class='left-info'>
                <div class='question' ng-class="{'done' : category.vars.questions.color.done,
                        'mandatory' : category.vars.questions.color.mandatory}">
                    <div class='text'>
                        <spring:message code="yt.pkgEditing.categoryStep.color.question"/>
                    </div>
                    <div class='answer'>
                        <div class="color" ng-class="{'active' : category.vars.questions.color.ddl.active}">
                            <div class="ddl" ng-click="category.vars.questions.color.ddl.open()">
                                <div class="vertical-center-container">
                                    <div class="center-content">
                                        <span class="default" ng-hide="category.vars.questions.color.ddl.color">
                                            <spring:message code="yt.pkgEditing.categoryStep.color.ddl"/>
                                        </span>
                                        <span class="color-point" ng-show="category.vars.questions.color.ddl.color"
                                              ng-style="{'background-color' : category.vars.questions.color.ddl.color}">
                                        </span>
                                    </div>
                                </div>
                            </div>
                            <div class="value">
                                <ul class="clearfix">
                                    <li ng-repeat="(code, aColor) in category.vars.colors">
                                        <span class="color-point" 
                                              ng-click="category.vars.questions.color.changed(code, aColor.hexCode)"
                                              ng-style="{'background-color' : aColor.hexCode}"></span>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>

                <div class='question mandatory last'>
                    <a href='' ng-click='registration.funcs.skipStep("description", false)' class='yt-btn red-btn'>
                        <spring:message code="yt.pkgEditing.btns.save.txt"/>
                    </a>
                </div>
            </div>
            <div class='instructions'>
                <div class='aInstruction'>
                    <p>
                        <spring:message code="yt.pkgEditing.categoryStep.color.instruction"/>
                    </p>
                </div>
            </div>
        </div>
    </div>
    <!--END COLOR-->
</div>