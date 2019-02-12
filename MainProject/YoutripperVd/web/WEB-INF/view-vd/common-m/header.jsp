<%-- 
    Document   : header
    Created on : May 23, 2018, 3:57:38 PM
    Author     : nickn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<header id="yt-header" ng-class="{'sc' : ytheader.vars.searching}">
    <div class="yt-container">
        <div class="right">
            <div class="aE">
                <div class="vertical-center-container">
                    <div class="center-content">
                        <div class="ctrls">
                            <div class="c-i">
                                <div class="close-input">
                                    <input type="text" class="yt-input no-b" ng-focus="ytheader.funcs.fc()" ng-blur="ytheader.funcs.ufc()"
                                           ng-model="ytheader.vars.txts"/>
                                    <span class="close-btn" ng-click="ytheader.funcs.ct()"></span>
                                </div>
                            </div>

                            <div class="icos">
                                <ul>
                                    <li class="s-i">
                                        <div class="an-i">
                                            <svg height="25px" viewBox="0 0 25.2 25">
                                            <path style="fill: #4CBDC9" d="M22.5,2.8c-3.7-3.7-9.6-3.7-13.3,0s-3.7,9.5,0,13.2s9.6,3.7,13.3,0
                                                  C26.2,12.3,26.2,6.4,22.5,2.8z M21.1,14.7c-2.9,3-7.7,3-10.6,0c-3-3-3-7.7,0-10.6c2.9-2.9,7.7-3,10.6,0C24.1,7,24.1,11.7,21.1,14.7
                                                  z"/>
                                            <path  style="fill: #4CBDC9" d="M6.4,15.9l-5.8,5.7c-0.8,0.8-0.8,2,0,2.8c0.8,0.8,2.1,0.8,2.8,0l5.8-5.7c0.8-0.8,0.8-2,0-2.8
                                                   C8.4,15.2,7.2,15.2,6.4,15.9z"/>
                                            </svg>
                                        </div>
                                    </li><li class="h-i">
                                        <div class="an-i" ng-click="ytheader.funcs.tgm()">
                                            <svg width="25px" height="17px" viewBox="0 0 25 17" style="enable-background:new 0 0 25 17;" xml:space="preserve">
                                            <line style="fill:none;stroke:#4CBDC9;stroke-width:2;stroke-miterlimit:10;" x1="0" y1="1" x2="25" y2="1"/>
                                            <line style="fill:none;stroke:#4CBDC9;stroke-width:2;stroke-miterlimit:10;" x1="0" y1="8" x2="25" y2="8"/>
                                            <line style="fill:none;stroke:#4CBDC9;stroke-width:2;stroke-miterlimit:10;" x1="0" y1="15" x2="25" y2="15"/>
                                            </svg>
                                        </div>
                                    </li>
                                </ul>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="left">
            <div class="aE">
                <div class="vertical-center-container">
                    <div class="center-content">
                        <div class="logo">
                            <div class="full">
                                <img class="white-ico" src="${requestScope.imageURL}youtripper-white-logo.svg" alt="Youtripper White Logo"/>
                            </div>
                            <div class="short">
                                <svg  width="30px" height="30px" viewBox="0 0 30 30">
                                <path style="fill: #FFF" d="M15,0C6.7,0,0,6.7,0,15c0,8.3,6.7,15,15,15c8.3,0,15-6.7,15-15C30,6.7,23.3,0,15,0z M23.8,15.7
                                      c-1.2,1.2-2.6,2.1-4.1,2.7c-1.5,0.6-3.1,0.9-4.7,0.9c-1.6,0-3.2-0.3-4.7-0.9c-1.5-0.6-2.9-1.5-4.1-2.7c-1.2-1.2-2.1-2.6-2.7-4.1
                                      c-0.1-0.3-0.3-0.7-0.4-1h2.2c0.5,1.3,1.3,2.6,2.4,3.6c2,2,4.5,3,7.3,3c2.4,0,4.7-0.8,6.5-2.3l-4.3-4.3h3l2.9,2.9
                                      c0.7-0.9,1.3-1.8,1.7-2.9h2.2c-0.1,0.3-0.2,0.7-0.4,1C25.9,13.2,25,14.6,23.8,15.7z"/>
                                </svg>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="menu" ng-class="{'op' : ytheader.vars.ming}">
        <div class="menu-c">
            <ul>
                <li>
                    <div class="a-m">
                        <span class="link" ng-click='ytheader.funcs.osignup()'>Do not have account yet?</span>
                    </div>
                </li>
                <li>
                    <div class="a-m">
                        <span class="link" ng-click="ytheader.funcs.ologin()">
                            <svg width="20px" height="20px" viewBox="0 0 20 20">
                            <path style="fill: #fff" d="M10,0C4.5,0,0,4.5,0,10s4.5,10,10,10s10-4.5,10-10S15.5,0,10,0z M10,3c1.7,0,3,1.3,3,3
                                  s-1.3,3-3,3S7,7.7,7,6S8.3,3,10,3z M10,17.2c-2.5,0-4.7-1.3-6-3.2c0-2,4-3.1,6-3.1c2,0,6,1.1,6,3.1C14.7,15.9,12.5,17.2,10,17.2z"/>
                            </svg>
                            Login
                        </span>
                    </div>
                </li>
                <li>
                    <div class="a-m">
                        <span class="link">Language / <span class="lan-btn">English</span></span>
                    </div>
                </li>
                <li>
                    <div class="a-m">
                        <span class="link">Currency / <span class="lan-btn">THB</span></span>
                    </div>
                </li>
                <li>
                    <div class="a-m">
                        <a href="" class="link"  ng-click="ytheader.funcs.ots()">Need help?</a>
                    </div>
                </li>
                <li>
                    <div class="a-m">
                        <a href="" class="link">Home</a>
                    </div>
                </li>
            </ul>
        </div>
    </div>

    <div class="yt-popup" ng-class="ytheader.vars.lpop.cl" id="yt-lp">
        <div class="popup">
            <div class="header">
                <div class="ctrl">
                    <div class="b-c" ng-click="ytheader.funcs.clogin()">
                        <svg width="13.5px" height="24px" viewBox="0 0 13.5 24">
                        <line style="fill:none;stroke:#FFFFFF;stroke-width:2;stroke-linecap:square;stroke-miterlimit:10;" x1="1.4" y1="12" x2="12" y2="1.4"/>
                        <line style="fill:none;stroke:#FFFFFF;stroke-width:2;stroke-linecap:square;stroke-miterlimit:10;" x1="12" y1="22.6" x2="1.4" y2="12"/>
                        </svg>
                    </div>
                </div>
                <div class="txt">
                    Login
                </div>
            </div>
            <div class="bd">
                <div class="bd-c">
                    <div class="row">
                        <button class="yt-btn fb-btn">Facebook</button>
                    </div>
                    <div class="row">
                        <button class="yt-btn red-btn">Google+</button>
                    </div>
                    <div class="row">
                        <div class="line">
                            <span class="txt">Or</span>
                        </div>
                    </div>
                    <div class="row">
                        <div class="yt-l">
                            <ul>
                                <li>
                                    <div class="f-t">
                                        Youtripper Account
                                    </div>
                                </li>
                                <li>
                                    <div class="f-i">
                                        <div class="yt-input-ico">
                                            <input type="text" placeholder="Email" class="yt-input grey-i"/>
                                            <div class="ico">
                                                <div class="vertical-center-container">
                                                    <div class="center-content">
                                                        <svg  width="18.8px" height="15px" viewBox="0 0 18.8 15" >
                                                        <path style="fill: #9C9B9C" d="M16.9,0h-15C0.8,0,0,0.8,0,1.9l0,11.3c0,1,0.8,1.9,1.9,1.9h15c1,0,1.9-0.8,1.9-1.9V1.9
                                                              C18.8,0.8,17.9,0,16.9,0z M16.9,3.8L9.4,8.4L1.9,3.8V1.9l7.5,4.7l7.5-4.7V3.8z"/>
                                                        </svg>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </li>
                                <li>
                                    <div class="f-i">
                                        <div class="yt-input-ico">
                                            <input type="password" placeholder="Password" class="yt-input grey-i"/>
                                            <div class="ico">
                                                <div class="vertical-center-container">
                                                    <div class="center-content">
                                                        <svg  width="11.4px" height="15px" viewBox="0 0 11.4 15">
                                                        <path style="fill: #9C9B9C" d="M10,5H9.3V3.6c0-2-1.6-3.6-3.6-3.6S2.1,1.6,2.1,3.6V5H1.4C0.6,5,0,5.6,0,6.4v7.1
                                                              C0,14.4,0.6,15,1.4,15H10c0.8,0,1.4-0.6,1.4-1.4V6.4C11.4,5.6,10.8,5,10,5z M5.7,11.4c-0.8,0-1.4-0.6-1.4-1.4s0.6-1.4,1.4-1.4
                                                              S7.1,9.2,7.1,10S6.5,11.4,5.7,11.4z M7.9,5H3.5V3.6c0-1.2,1-2.2,2.2-2.2s2.2,1,2.2,2.2V5z"/>
                                                        </svg>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </li>
                                <li>
                                    <div class="f-b">
                                        <button class="yt-btn">Login</button>
                                    </div>
                                </li>
                                <li>
                                    <div class="f-l">
                                        <span class="link">Forget Password</span>
                                    </div>
                                    <div class="f-l">
                                        Don't have an account? <span class="link">Sign Up</span>
                                    </div>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="yt-popup" id='yt-sp'  ng-class="ytheader.vars.spop.cl">
        <div class="popup">
            <div class="header">
                <div class="ctrl">
                    <div class="b-c" ng-click="ytheader.funcs.csignup()">
                        <svg width="13.5px" height="24px" viewBox="0 0 13.5 24">
                        <line style="fill:none;stroke:#FFFFFF;stroke-width:2;stroke-linecap:square;stroke-miterlimit:10;" x1="1.4" y1="12" x2="12" y2="1.4"/>
                        <line style="fill:none;stroke:#FFFFFF;stroke-width:2;stroke-linecap:square;stroke-miterlimit:10;" x1="12" y1="22.6" x2="1.4" y2="12"/>
                        </svg>
                    </div>
                </div>
                <div class="txt">
                    Join Us
                </div>
            </div>
            <div class="bd">
                <div class="bd-c">
                    <div class="row">
                        Join us now for free and discover the best activities around you
                    </div>
                    <div class="row">
                        <button class="yt-btn fb-btn">Facebook</button>
                    </div>
                    <div class="row">
                        <button class="yt-btn red-btn">Google+</button>
                    </div>
                    <div class="row">
                        <div class="line">
                            <span class="txt">Or</span>
                        </div>
                    </div>
                    <div class="row">
                        <div class="yt-l">
                            <ul>
                                <li>
                                    <div class="f-t">
                                        Create Youtripper Account
                                    </div>
                                </li>
                                <li>
                                    <div class="f-i">
                                        <div class="yt-input-ico">
                                            <input type="text" placeholder="Email" class="yt-input grey-i"/>
                                            <div class="ico">
                                                <div class="vertical-center-container">
                                                    <div class="center-content">
                                                        <svg  width="18.8px" height="15px" viewBox="0 0 18.8 15" >
                                                        <path style="fill: #9C9B9C" d="M16.9,0h-15C0.8,0,0,0.8,0,1.9l0,11.3c0,1,0.8,1.9,1.9,1.9h15c1,0,1.9-0.8,1.9-1.9V1.9
                                                              C18.8,0.8,17.9,0,16.9,0z M16.9,3.8L9.4,8.4L1.9,3.8V1.9l7.5,4.7l7.5-4.7V3.8z"/>
                                                        </svg>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </li>
                                <li>
                                    <div class="f-i">
                                        <div class="yt-input-ico">
                                            <input type="text" placeholder="Reconfirm Email" class="yt-input grey-i"/>
                                            <div class="ico">
                                                <div class="vertical-center-container">
                                                    <div class="center-content">
                                                        <svg  width="18.8px" height="15px" viewBox="0 0 18.8 15" >
                                                        <path style="fill: #9C9B9C" d="M16.9,0h-15C0.8,0,0,0.8,0,1.9l0,11.3c0,1,0.8,1.9,1.9,1.9h15c1,0,1.9-0.8,1.9-1.9V1.9
                                                              C18.8,0.8,17.9,0,16.9,0z M16.9,3.8L9.4,8.4L1.9,3.8V1.9l7.5,4.7l7.5-4.7V3.8z"/>
                                                        </svg>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </li>
                                <li>
                                    <div class="f-i">
                                        <div class="yt-input-ico">
                                            <input type="password" placeholder="Password" class="yt-input grey-i"/>
                                            <div class="ico">
                                                <div class="vertical-center-container">
                                                    <div class="center-content">
                                                        <svg  width="11.4px" height="15px" viewBox="0 0 11.4 15">
                                                        <path style="fill: #9C9B9C" d="M10,5H9.3V3.6c0-2-1.6-3.6-3.6-3.6S2.1,1.6,2.1,3.6V5H1.4C0.6,5,0,5.6,0,6.4v7.1
                                                              C0,14.4,0.6,15,1.4,15H10c0.8,0,1.4-0.6,1.4-1.4V6.4C11.4,5.6,10.8,5,10,5z M5.7,11.4c-0.8,0-1.4-0.6-1.4-1.4s0.6-1.4,1.4-1.4
                                                              S7.1,9.2,7.1,10S6.5,11.4,5.7,11.4z M7.9,5H3.5V3.6c0-1.2,1-2.2,2.2-2.2s2.2,1,2.2,2.2V5z"/>
                                                        </svg>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </li>
                                <li>
                                    <div class="f-i">
                                        <div class="yt-input-ico">
                                            <input type="password" placeholder="Reconfirm password" class="yt-input grey-i"/>
                                            <div class="ico">
                                                <div class="vertical-center-container">
                                                    <div class="center-content">
                                                        <svg  width="11.4px" height="15px" viewBox="0 0 11.4 15">
                                                        <path style="fill: #9C9B9C" d="M10,5H9.3V3.6c0-2-1.6-3.6-3.6-3.6S2.1,1.6,2.1,3.6V5H1.4C0.6,5,0,5.6,0,6.4v7.1
                                                              C0,14.4,0.6,15,1.4,15H10c0.8,0,1.4-0.6,1.4-1.4V6.4C11.4,5.6,10.8,5,10,5z M5.7,11.4c-0.8,0-1.4-0.6-1.4-1.4s0.6-1.4,1.4-1.4
                                                              S7.1,9.2,7.1,10S6.5,11.4,5.7,11.4z M7.9,5H3.5V3.6c0-1.2,1-2.2,2.2-2.2s2.2,1,2.2,2.2V5z"/>
                                                        </svg>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </li>
                                <li>
                                    <div class="f-b">
                                        <button class="yt-btn">Sign Up</button>
                                    </div>
                                </li>
                                <li>
                                    <div class="f-l">
                                        By clicking "Sign Up", I hereby agreed to Youtripper's <a class="link">Terms of Service</a> and <a href=''>Privacy Policy</a>
                                    </div>
                                     <div class="f-l" style="font-size: 10px">
                                        By clicking "Sign Up", I hereby agreed to Youtripper's <a class="link">Terms of Service</a> and <a href=''>Privacy Policy</a>
                                    </div>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="yt-popup" id='yt-ts' ng-class="ytheader.vars.tspop.cl">
        <div class="popup">
            <div class="header">
                <div class="ctrl">
                    <div class="b-c" ng-click="ytheader.funcs.cts()">
                        <svg width="13.5px" height="24px" viewBox="0 0 13.5 24">
                        <line style="fill:none;stroke:#FFFFFF;stroke-width:2;stroke-linecap:square;stroke-miterlimit:10;" x1="1.4" y1="12" x2="12" y2="1.4"/>
                        <line style="fill:none;stroke:#FFFFFF;stroke-width:2;stroke-linecap:square;stroke-miterlimit:10;" x1="12" y1="22.6" x2="1.4" y2="12"/>
                        </svg>
                    </div>
                </div>
                <div class="txt">
                    Tripper Support
                </div>
            </div>
            <div class="bd">
                <div class="bd-c">
                    <div class="row">
                        We are here to help. Let's know your questions.
                    </div>
                    <div class="row">
                        <div class='info'>
                            <div class='ic'>
                                <svg  width="50px" height="50px" viewBox="0 0 50 50">
                                <path style="fill: #4CBDC9" d="M25,0C11.2,0,0,11.2,0,25c0,13.8,11.2,25,25,25c13.8,0,25-11.2,25-25C50,11.2,38.8,0,25,0z
                                      M39.6,26.2c-2,2-4.3,3.5-6.9,4.6c-2.5,1-5.1,1.5-7.8,1.5c-2.7,0-5.3-0.5-7.8-1.5c-2.6-1-4.9-2.6-6.9-4.6c-2-2-3.5-4.3-4.5-6.9
                                      c-0.2-0.6-0.4-1.1-0.6-1.7h3.7c0.9,2.2,2.2,4.3,3.9,6.1c3.3,3.3,7.6,5,12.2,5c4,0,7.8-1.4,10.9-3.9l-7.2-7.2h4.9l4.8,4.8
                                      c1.2-1.4,2.1-3.1,2.8-4.8h3.7c-0.2,0.6-0.4,1.1-0.6,1.7C43.2,21.9,41.6,24.3,39.6,26.2z"/>
                                </svg>
                            </div>
                            <div class="is">
                                <ul>
                                    <li>
                                        <div class="i-loc">
                                            Bangkok, Thailand
                                        </div>
                                    </li>
                                    <li>
                                        <div class="i-loc">
                                            Tel: (+66)-2101-3069
                                        </div>
                                    </li>
                                    <li>
                                        <div class="i-loc">
                                            Line ID: @youtripper
                                        </div>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="line">
                            <span class="txt">Or</span>
                        </div>
                    </div>
                    <div class="row">
                        <div class="social">
                            <ul class="clearfix">
                                <li>
                                    <div class="a-s">
                                        <a href="">
                                            <svg  width="13px" height="25px" viewBox="0 0 13 25">
                                            <path style="fill: #D6D4D4" d="M13,4.2h-2.4c-1.9,0-2.1,0.9-2.1,2.1v2.8h4.4l-0.5,4.4H8.5V25H3.9V13.6H0V9.1h3.9V5.9
                                                  C3.9,2,6.2,0,9.5,0c1.6,0,3,0.1,3.5,0.1V4.2z"/>
                                            </svg>
                                        </a>
                                    </div>
                                </li>
                                <li>
                                    <div class="a-s">
                                        <a href="">
                                            <svg width="30.7px" height="25px" viewBox="0 0 30.7 25">
                                            <path style="fill: #D6D4D4" d="M27.5,6.3c0,0.2,0,0.6,0,0.8c0,8.4-6.3,18-18,18c-3.5,0-6.8-1-9.6-2.7c0.6,0,1,0,1.6,0
                                                  c2.9,0,5.7-1,7.8-2.7c-2.7,0-5.1-2-5.9-4.3c0.4,0,0.8,0.2,1.2,0.2c0.6,0,1.2,0,1.6-0.2c-2.9-0.8-5.1-3.3-5.1-6.4l0,0
                                                  C2,9.2,2.9,9.6,4.1,9.6C2.3,8.4,1.4,6.4,1.4,4.3c0-1.2,0.4-2.1,0.8-3.1C5.3,4.9,10,7.4,15,7.8c0-0.6-0.2-1-0.2-1.6
                                                  c0-3.5,2.9-6.3,6.4-6.3C23,0,24.8,0.8,26,2c1.4-0.2,2.7-0.8,3.9-1.6C29.3,2,28.3,3.1,27,3.9c1.4-0.2,2.5-0.4,3.7-1
                                                  C29.9,4.3,28.7,5.3,27.5,6.3z"/>
                                            </svg>
                                        </a>
                                    </div>
                                </li>
                                <li>
                                    <div class="a-s">
                                        <a href="">
                                            <svg width="24.8px" height="25px" viewBox="0 0 24.8 25" >
                                            <path style="fill: #D6D4D4" d="M20,3.7h-2.2c-0.3,0-0.6,0.3-0.6,0.6v2.2c0,0.3,0.3,0.6,0.6,0.6H20c0.3,0,0.6-0.3,0.6-0.6V4.3
                                                  C20.7,4,20.5,3.7,20,3.7z"/>
                                            <path style="fill: #D6D4D4" d="M18.6,0H6.2C2.8,0,0,2.8,0,6.2v12.6C0,22.2,2.8,25,6.2,25h12.4c3.4,0,6.2-2.8,6.2-6.2V6.2
                                                  C24.8,2.8,22,0,18.6,0z M22.5,18.8c0,2.2-1.7,3.9-3.9,3.9H6.2c-2.2,0-3.9-1.7-3.9-3.9v-8.1h5c-0.3,0.8-0.6,1.6-0.6,2.5
                                                  c0,3.3,2.6,5.9,5.9,5.9s5.9-2.6,5.9-5.9c0-0.9-0.2-1.7-0.6-2.5h4.7V18.8z M10.1,13.2c0-1.4,1.1-2.5,2.5-2.5s2.5,1.1,2.5,2.5
                                                  s-1.1,2.5-2.5,2.5C11.2,15.5,10.1,14.4,10.1,13.2z M22.5,8.4H16c-0.9-0.8-2.2-1.1-3.4-1.1c-1.2,0-2.5,0.5-3.4,1.1H2.3V6.2
                                                  C2.3,4,4,2.3,6.2,2.3h12.4c2.2,0,3.9,1.7,3.9,3.9V8.4z"/>
                                            </svg>
                                        </a>
                                    </div>
                                </li>
                            </ul>
                        </div>
                    </div>
                    <div class="row">
                        <div class="line">
                            <span class="txt">Or leave us amessage</span>
                        </div>
                    </div>
                    <div class="row">
                        <div class="yt-l">
                            <ul>
                                <li>
                                    <div class="f-i">
                                        <div class="yt-input-ico">
                                            <input type="text" placeholder="Email" class="yt-input grey-i"/>
                                            <div class="ico">
                                                <div class="vertical-center-container">
                                                    <div class="center-content">
                                                        <svg  width="18.8px" height="15px" viewBox="0 0 18.8 15" >
                                                        <path style="fill: #9C9B9C" d="M16.9,0h-15C0.8,0,0,0.8,0,1.9l0,11.3c0,1,0.8,1.9,1.9,1.9h15c1,0,1.9-0.8,1.9-1.9V1.9
                                                              C18.8,0.8,17.9,0,16.9,0z M16.9,3.8L9.4,8.4L1.9,3.8V1.9l7.5,4.7l7.5-4.7V3.8z"/>
                                                        </svg>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </li>
                                <li>
                                    <div class="f-i">
                                        <div class="yt-input-ico">
                                            <input type="text" placeholder="Your name" class="yt-input grey-i"/>
                                            <div class="ico">
                                                <div class="vertical-center-container">
                                                    <div class="center-content">
                                                        <svg width="20px" height="20px" viewBox="0 0 20 20">
                                                        <path style="fill: #9C9B9C" d="M10,0C4.5,0,0,4.5,0,10s4.5,10,10,10s10-4.5,10-10S15.5,0,10,0z M10,3c1.7,0,3,1.3,3,3
                                                              s-1.3,3-3,3S7,7.7,7,6S8.3,3,10,3z M10,17.2c-2.5,0-4.7-1.3-6-3.2c0-2,4-3.1,6-3.1c2,0,6,1.1,6,3.1C14.7,15.9,12.5,17.2,10,17.2z"/>
                                                        </svg>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </li>
                                <li>
                                    <div class="f-i">
                                        <textarea placeholder="Your message here" class="yt-input grey-i"></textarea>
                                    </div>
                                </li>
                                <li>
                                    <div class="f-b">
                                        <button class="yt-btn">Send</button>
                                    </div>
                                </li>
                                <li>
                                    <div class="f-l">
                                        We will get back to you with 24 hours. Thank You.
                                    </div>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</header>