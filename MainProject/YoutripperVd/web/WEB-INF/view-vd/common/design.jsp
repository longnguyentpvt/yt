<%-- 
    Document   : index
    Created on : Jun 26, 2017, 12:20:16 PM
    Author     : nickn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="${pageContext.response.locale}" currency="${sessionScope.ytCurrency}" ng-app="youtripper" ng-controller="YoutripperController">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="commonhead.jsp" %>
        <script src="${requestScope.jsURL}test.js"></script>

        <title>Youtripper - Common CSS</title>
    </head>
    <body ng-cloak>
        <div ng-controller="Common" id="padding-header" class="center-content small">
            <div class="yt-normal-container">
                This is normal container
                <span class="light-txt">Light Text</span>
                <br/>
                <br/>
                <span class="nm-bold-txt">Normal Text</span>
                <br/>
                <br/>
                <span class="semi-bold-txt">Semi Bold Text</span>
                <br/>
                <br/>
                <span class="bold-txt">Bold Text</span>
            </div>

            <div class="yt-small-container">
                This is small container
            </div>

            <div class="yt-smallest-container">
                Smallest Container
            </div>

            <div class="yt-small-container">
                <a href="">Link</a>
                <br/>
                <span class="link">Fake Link</a>
                    <br/>
                    <input type="button" value="Input Button" class="yt-btn"/>
                    <br/>
                    <a href="" class="yt-btn">Href Button</a>
                    <br/>
                    <a href="" class="yt-btn" style="width: 150px;">Fixed Width Button</a>
                    <br/>
                    <a href="" class="yt-btn center-btn" style="width: 150px;">Center Button</a>
                    <br/>
                    <input type="button" value="Input Button" class="yt-btn red-btn"/>
                    <br/>
                    <input type="button" value="Input Button" class="yt-btn orange-btn"/>
                    <br/>
                    <input type="button" value="Input Button" class="yt-btn yellow-btn"/>
                    <br/>
                    <input type="button" value="Input Button" class="yt-btn green-btn"/>
                    <br/>
                    <input type="button" value="Input Button" class="yt-btn grey-btn"/>
                    <br/>
                    <input type="button" value="Input Button" class="yt-btn fb-btn"/>
                    <br/>
                    <input type="button" value="Auto Width Button" class="yt-btn auto-width-btn"/>
            </div>

            <div class="yt-small-container">
                <br/>
                Inputs
                <br/>
                <br/>
                <input type="text" name="" value="" placeholder="Text Input" class="yt-input" />
                <br/>
                <input type="text" name="" value="" placeholder="Text Input With No Border" class="yt-input no-b" />
                <br/>
                <input type="text" name="" value="" placeholder="Readonly Input"  readonly="readonly"
                       class="yt-input readonly" />
                <br/>
                <div class="close-input">
                    <input type="text" name="" value="" placeholder="Text Input With Close Btn" class="yt-input" 
                           ng-model="input123"
                           minlength="10"
                           ng-minlength="10"
                           ng-model-options="{allowInvalid: true}"/>
                    <span class="close-btn"
                          ng-show="input123.length"></span>
                </div>
                <br/>
                <input type="text" name="" value="" placeholder="Center Text Input" class="yt-input center-txt" />
                <br/>
                <div class="yt-input-ico">
                    <input type="text" placeholder="Text Input With Icon" class="yt-input"/>
                    <div class="ico">
                        <svg  width="18.8px" height="15px" viewBox="0 0 18.8 15">
                        <path style="fill: #555555" d="M16.9,0h-15C0.8,0,0,0.8,0,1.9l0,11.3c0,1,0.8,1.9,1.9,1.9h15c1,0,1.9-0.8,1.9-1.9V1.9
                              C18.8,0.8,17.9,0,16.9,0z M16.9,3.8L9.4,8.4L1.9,3.8V1.9l7.5,4.7l7.5-4.7V3.8z"/>
                        </svg>
                    </div>
                </div>
                <br/>
                <div class='status-input success'>
                    <input type="text" name="" value="" placeholder="Text Input With Success Status" class="yt-input" />
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
                <br/>
                <div class='status-input err'>
                    <input type="text" name="" value="" placeholder="Text Input With Error Status" class="yt-input" />
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
                <br/>
                <div class="ddl-input nod">
                    <select name="" class="yt-input">
                        <option value="">No Default</option>
                        <option value="1">1</option>
                        <option value="2">2</option>
                    </select>
                </div>
                <br/>
                <div class="ddl-input" ng-init="ddli = null" ng-class="{'nov' : ddli === null}">
                    <select name="" class="yt-input"  ng-model="ddli">
                        <option ng-value="null">Dropdown list</option>
                        <option ng-value="1">1</option>
                        <option ng-value="2">2</option>
                    </select>
                </div>
                <br/>
                <div class="auto-number-input">
                    <span class="auto-ctrl minus-ctrl"></span>
                    <div class="input">
                        <input type="text" name="" value="" placeholder="Auto Number Input" readonly="readonly" class="yt-input"/>
                    </div>
                    <span class="auto-ctrl plus-ctrl active"></span>
                </div>
                <br/>
                <div class="auto-number-input"  style="width: 250px">
                    <span class="auto-ctrl minus-ctrl"></span>
                    <div class="input">
                        <input type="text" name="" value="" placeholder="Auto - Fix Width" readonly="readonly" class="yt-input"/>
                    </div>
                    <span class="auto-ctrl plus-ctrl active"></span>
                </div>
                <br/>
                <textarea name="" rows="4" cols="20" class="yt-input" placeholder="Textarea"></textarea>
                <br/>
                <div class="checkbox-ctn">
                    <div class="yt-checkbox">
                        <input type="checkbox" name="" value="ON">
                        <span></span>
                    </div> 
                    Checkbox
                </div>
                <div class="checkbox-ctn">
                    <div class="yt-checkbox radio">
                        <input type="radio" name="test" value="ON">
                        <span></span>
                    </div>
                    Radio 1
                </div>
                <div class="checkbox-ctn">
                    <div class="yt-checkbox radio">
                        <input type="radio" name="test" value="ON">
                        <span></span>
                    </div>
                    Radio 2
                </div>
                <br/>
                <br/>
                <div class="yt-selection-btn">
                    <input type="checkbox" name="test" value="ON">
                    <span>Checkbox - Radio - Button</span>
                </div>
                <br/>
                <div class="yt-selection-btn auto-width-btn">
                    <input type="radio" name="test" value="ON">
                    <span>Checkbox - Radio - Auto Width Button</span>
                </div>
                <br/>
                <br/>
                Horizontal Checkbox - Radio
                <ul class="hor-selection clearfix">
                    <li>
                        <input type="checkbox" name="" value="ON">
                        <span>Selection 1</span>
                    </li>
                    <li>
                        <input type="checkbox" name="" value="ON">
                        <span>Selection 2</span>
                    </li>
                    <li>
                        <input type="checkbox" name="" value="ON">
                        <span>Selection 3</span>
                    </li>
                </ul>
                <br/>
                Vertical Checkbox - Radio
                <ul class="hor-selection vertical clearfix">
                    <li>
                        <input type="radio" name="test1" value="ON">
                        <span>Selection 1</span>
                    </li>
                    <li>
                        <input type="radio" name="test1" value="ON">
                        <span>Selection 2</span>
                    </li>
                    <li>
                        <input type="radio" name="test1" value="ON">
                        <span>Selection 3</span>
                    </li>
                </ul>
            </div>
            <br/><br/>
            <div class="yt-small-container">
                Spinner
                <br/>
                <span class="yt-spinner">
                </span>
                <br/>
                <span class="yt-spinner center">
                </span>
                <br/>
                <div style="background: black; position: relative; height: 100px">
                    <div class="center-spinner-block loading" ng-class="{
                            'full': common.fullSpinner}" ng-click="common.fullSpinner = false">
                        <div class="vertical-center-container">
                            <div class="center-content">
                                <span class="yt-spinner center"></span>
                            </div>
                        </div>
                    </div>
                </div>
                <br/>
                <a href="" ng-click="common.fullSpinner = true">Open Full Spinner</a>
                <br/>
                <br/>

                <div class="spinner-input">
                    <input class="yt-input" placeholder="Input with spinner"/>
                    <span class="yt-spinner center"></span>
                </div>
                <br/>
                <br/>
            </div>

            <div class="yt-small-container">
                Calendar
                <br/>
                <br/>
                <div class="yt-calendar">
                    <div class="common">
                        <div class="month bold-txt">
                            <span class="ctrl prv-ctrl"></span>
                            <span class="ctrl nxt-ctrl"></span>
                            June 2017
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
                            <li class="disable">
                                <span>
                                    29
                                </span>
                            </li>
                            <li class="disable">
                                <span>
                                    30
                                </span>
                            </li>
                            <li class="disable">
                                <span>
                                    31
                                </span>
                            </li>
                            <li >
                                <span>
                                    1
                                </span>
                            </li>
                            <li>
                                <span>
                                    2
                                </span>
                            </li>
                            <li>
                                <span>
                                    3
                                </span>
                            </li>
                            <li>
                                <span>
                                    4
                                </span>
                            </li>
                            <li>
                                <span>
                                    5
                                </span>
                            </li>
                            <li class="today">
                                <span>
                                    6
                                </span>
                            </li>
                            <li>
                                <span>
                                    7
                                </span>
                            </li>
                            <li>
                                <span>
                                    8
                                </span>
                            </li>
                            <li>
                                <span>
                                    9
                                </span>
                            </li>
                            <li class="active">
                                <span>
                                    10
                                </span>
                            </li>
                            <li class="in">
                                <span>
                                    11
                                </span>
                            </li>
                        </ul>
                    </div>
                </div>
                <br/>
                <div class="search-input calendar-input">
                    <div class="yt-calendar">
                        <div class="common">
                            <div class="month semi-bold-txt">
                                <span class="ctrl prv-ctrl" ng-show="startDate.rO.lav" ng-click="startDate.rO = CALENDAR.funcs.prvM(startDate.rO)"></span>
                                <span class="ctrl nxt-ctrl" ng-show="startDate.rO.rav" ng-click="startDate.rO = CALENDAR.funcs.nxtM(startDate.rO)"></span>
                                {{startDate.rO["moT"]}} {{startDate.rO["yeT"]}}
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
                                <li ng-repeat="ad in startDate.rO.dates" ng-class="{'disable' : ad.ty === 'out', 'active' : ad.ty === 'mo',
                                    'today' : ad.ty === 'to',  'in' : ad.ty === 'in'}">
                                    <span ng-click="startDate.change($index)">
                                        {{ad.txt}}
                                    </span>
                                </li>
                            </ul>
                        </div>
                    </div>
                    <input type="text" name="" value="" readonly="readonly" placeholder="bottom left" 
                           style="width: 200px;"
                           class="yt-input" ng-model="startDate.txt"
                           ng-click="startDate.rO = CALENDAR.funcs.getCa($event, startDate.todayMilli, startDate.milli, null, null, startDate.limitRight)">
                </div>
                <br/>
                <div class="search-input calendar-input top right">
                    <div class="yt-calendar">
                        <div class="common">
                            <div class="month bold-txt">
                                <span class="ctrl prv-ctrl" ng-show="startDate.rO.lav" ng-click="startDate.rO = CALENDAR.funcs.prvM(startDate.rO)"></span>
                                <span class="ctrl nxt-ctrl" ng-show="startDate.rO.rav" ng-click="startDate.rO = CALENDAR.funcs.nxtM(startDate.rO)"></span>
                                {{startDate.rO["moT"]}} {{startDate.rO["yeT"]}}
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
                                <li ng-repeat="ad in startDate.rO.dates" ng-class="{'disable' : ad.ty === 'out', 'active' : ad.ty === 'mo',
                                    'today' : ad.ty === 'to',  'in' : ad.ty === 'in'}">
                                    <span ng-click="startDate.change($index)">
                                        {{ad.txt}}
                                    </span>
                                </li>
                            </ul>
                        </div>
                    </div>
                    <input type="text" name="" value="" readonly="readonly" placeholder="bottom left" 
                           style="width: 200px;"
                           class="yt-input" ng-model="startDate.txt"
                           ng-click="startDate.rO = CALENDAR.funcs.getCa($event, startDate.todayMilli, startDate.milli, null, null, startDate.limitRight)">
                </div>
                <br/><br/>
            </div>

            <div class="yt-small-container">
                CLOCK INPUT
                <br/><br/>
                <div class="yt-clock-input right" style="width: 150px;">
                    <div class="clock">
                        <ul class="clearfix">
                            <li>
                                <div class="label">
                                    Hour
                                </div>
                                <div class="input">
                                    <select name="" class="yt-input"
                                            ng-change="common.time.change()"
                                            ng-model="common.time.hour">
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
                                            ng-change="common.time.change()"
                                            ng-model="common.time.min">
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
                    <input type="text" readonly="readonly" 
                           placeholder="Clock Right Input"
                           ng-model="common.time.HHmm"
                           class="yt-input center-txt" style="width: 150px"/>
                </div>
                <br/>
                <div class="yt-clock-input left" style="width: 150px;">
                    <div class="clock">
                        <ul class="clearfix">
                            <li>
                                <div class="label">
                                    fadsfasdf
                                </div>
                                <div class="input">
                                    <select name="" class="yt-input" 
                                            ng-model="CLOCK.vars.hour">
                                        <option value="">HH</option>
                                        <option value="0">00</option>
                                        <option value="1">01</option>
                                        <option value="2">02</option>
                                        <option value="3">03</option>
                                        <option value="4">04</option>
                                        <option value="5">05</option>
                                        <option value="6">06</option>
                                        <option value="7">07</option>
                                        <option value="8">08</option>
                                        <option value="9">09</option>
                                        <option value="10">10</option>
                                        <option value="11">11</option>
                                        <option value="12">12</option>
                                        <option value="13">13</option>
                                        <option value="14">14</option>
                                        <option value="15">15</option>
                                        <option value="16">16</option>
                                        <option value="17">17</option>
                                        <option value="18">18</option>
                                        <option value="19">19</option>
                                        <option value="20">20</option>
                                        <option value="21">21</option>
                                        <option value="22">22</option>
                                        <option value="23">23</option>
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
                                    adfsasdfasdf
                                </div>
                                <div class="input">
                                    <select name="" class="yt-input" 
                                            ng-model="CLOCK.vars.minute">
                                        <option value="">mm</option>
                                        <option value="0">00</option>
                                        <option value="5">05</option>
                                        <option value="10">10</option>
                                        <option value="15">15</option>
                                        <option value="20">20</option>
                                        <option value="25">25</option>
                                        <option value="30">30</option>
                                        <option value="35">35</option>
                                        <option value="40">40</option>
                                        <option value="45">45</option>
                                        <option value="50">50</option>
                                        <option value="55">55</option>
                                    </select>
                                </div>
                            </li>
                        </ul>
                    </div>
                    <input type="text" readonly="readonly" 
                           placeholder="Clock Left Input"
                           class="yt-input center-txt" style="width: 150px"/>
                </div>
            </div>
            <br/>
            <div class="yt-small-container">
                <a href="" ng-click="POPUP.openPopup(popup.common)">Open Popup</a>
                <br/><br/>
                <div class="yt-popup" ng-class="{'active': popup.common.active}">
                    <div class="vertical-center-container">
                        <div class="center-content" ng-click="POPUP.closePopup(popup.common, $event)">
                            <div class="popup">
                                <div class="body">
                                    <span class="close-btn" ng-click="POPUP.closePopup(popup.common, $event)"></span>
                                    <div class="container">
                                        <div class="row">
                                            Row 1
                                        </div>
                                        <div class="row">
                                            Row 2
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>

        <%@include file="footer.jsp" %>
        <%@include file="header.jsp" %>
    </body>
</html>
