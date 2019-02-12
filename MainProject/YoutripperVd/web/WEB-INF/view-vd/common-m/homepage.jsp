<%-- 
    Document   : homepage
    Created on : May 24, 2018, 3:31:55 PM
    Author     : nickn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="${pageContext.response.locale}" currency="${sessionScope.ytCurrency}" ng-app="youtripper" ng-controller="YoutripperController">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="commonhead.jsp" %>

        <title>Youtripper - Homepage</title>      
    </head>
    <body>
        <div id="padding-header">
            <div id="hp-page">
                <div class="hp-c">
                    <div class="hp-cover">
                        <ul>
                            <li>
                                <div class="aCover">
                                    <div class="bg"></div>
                                </div>
                            </li>
                        </ul>
                    </div>

                    <div class="hp-m hi" id="cat-m">
                        <div class="yt-container">
                            <div class="hp-m-con">
                                <div class="m-h cen">
                                    <div class="txt">Find what suit you the most!</div>
                                </div>
                                <div class="m-b">
                                    <div class="cates">
                                        <ul class="clearfix">
                                            <li>
                                                <div class="acat">
                                                    <div class="img">
                                                        <div class="bg"></div>
                                                    </div>
                                                    <div class="name">
                                                        Category Name
                                                    </div>
                                                </div>
                                            </li><li>
                                                <div class="acat">
                                                    <div class="img">
                                                        <div class="bg"></div>
                                                    </div>
                                                    <div class="name">
                                                        Category Name
                                                    </div>
                                                </div>
                                            </li><li>
                                                <div class="acat">
                                                    <div class="img">
                                                        <div class="bg"></div>
                                                    </div>
                                                    <div class="name">
                                                        Category Name
                                                    </div>
                                                </div>
                                            </li>
                                        </ul>
                                    </div>

                                    <div class="m-btn">
                                        <a href="" class="yt-btn white-bg-btn grey-btn">See all activities</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="hp-m hi">
                        <div class="yt-container">
                            <div class="hp-m-con">
                                <div class="m-b">
                                    <div class="ad-m">
                                        <div class="bg"></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="hp-m">
                        <div class="yt-container">
                            <div class="hp-m-con">
                                <div class="m-h">
                                    Our Recommended For You <a href="" class="yt-btn white-bg-btn grey-btn">See All</a>
                                </div>
                                <div class="m-b">
                                    <div class="pk-m">
                                        <ul class="pkgs">
                                            <li>
                                                <div class="aP">
                                                    <div class="yt-pk-m">
                                                        <div class="pt">
                                                            <div class="bg"></div>
                                                            <div class="img">
                                                                <img src="https://cdn.youtripper.com/m-mockup/sample-package.jpg"/>
                                                            </div>
                                                            <div class="name">
                                                                Package Name 1<br/>
                                                                Line 2
                                                            </div>
                                                            <div class="loc">
                                                                <svg width="11.3px" height="16px" viewBox="0 0 11.3 16" >
                                                                <path style="fill: #FFFFFF" d="M11.3,5.6C11.3,2.5,8.7,0,5.6,0C2.5,0,0,2.5,0,5.6c0,0.1,0,0.2,0,0.3c0,0,0,3.9,3.7,9
                                                                      c0,0,0,0,0,0c0,0,0,0.1,0.1,0.1l0,0C4.2,15.6,4.9,16,5.6,16c0.8,0,1.5-0.4,1.9-1l0,0c3.7-5.2,3.7-9.2,3.7-9.2l0,0
                                                                      C11.3,5.8,11.3,5.7,11.3,5.6z M5.7,8C4.4,8,3.3,6.9,3.3,5.6c0-1.3,1.1-2.4,2.4-2.4c1.3,0,2.4,1.1,2.4,2.4C8.1,6.9,7,8,5.7,8z"/>
                                                                </svg>
                                                                Bangkok
                                                            </div>
                                                        </div>
                                                        <div class="info">
                                                            <div class="clearfix">

                                                                <div class="price">
                                                                    <ul>
                                                                        <li>
                                                                            <div class="aE">
                                                                                <div class="vertical-center-container">
                                                                                    <div class="center-content">
                                                                                        <div class="pricing">
                                                                                            <div class="currency semi-bold-txt">
                                                                                                ฿
                                                                                            </div>
                                                                                            <div class="number">
                                                                                                <div class="main semi-bold-txt">
                                                                                                    690.00
                                                                                                </div>
                                                                                                <div class="old">
                                                                                                    890.00
                                                                                                </div>
                                                                                            </div>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                            </div> 
                                                                        </li><li>
                                                                            <div class="aE">
                                                                                <div class="vertical-center-container">
                                                                                    <div class="center-content">
                                                                                        <div class="dcP semi-bold-txt">
                                                                                            -60%
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                            </div>
                                                                        </li>
                                                                    </ul>
                                                                </div>

                                                                <div class="book-info">
                                                                    <div class="no-s">
                                                                        24 Sold(s)
                                                                    </div>
                                                                    <div class="reviews">
                                                                        <ul>
                                                                            <li>
                                                                                <div class="aR">
                                                                                    <svg width="10.4px" height="10px" viewBox="0 0 10.4 10">
                                                                                    <path style="fill:#E99C48;" d="M5.2,0.7l1.1,2.2c0.2,0.4,0.6,0.7,1,0.7L9.8,4L8,5.7C7.7,6,7.5,6.4,7.6,6.9L8,9.3L5.8,8.2
                                                                                          C5.6,8.1,5.4,8,5.2,8C5,8,4.8,8.1,4.6,8.2L2.4,9.3l0.4-2.4C2.9,6.4,2.8,6,2.5,5.7L0.7,4l2.5-0.4c0.4-0.1,0.8-0.3,1-0.7L5.2,0.7
                                                                                          M5.2,0C5,0,4.8,0.1,4.7,0.4L3.5,2.6C3.4,2.8,3.3,2.9,3.1,3L0.5,3.3C0,3.4-0.2,4.1,0.2,4.4L2,6.2c0.2,0.1,0.2,0.4,0.2,0.6L1.8,9.3
                                                                                          C1.7,9.7,2,10,2.4,10c0.1,0,0.2,0,0.3-0.1l2.2-1.2c0.1,0,0.2-0.1,0.3-0.1c0.1,0,0.2,0,0.3,0.1l2.2,1.2C7.9,10,8,10,8.1,10
                                                                                          c0.4,0,0.7-0.3,0.6-0.7L8.3,6.8c0-0.2,0-0.4,0.2-0.6l1.8-1.8c0.4-0.4,0.2-1-0.4-1.1L7.4,3C7.2,2.9,7,2.8,6.9,2.6L5.8,0.4
                                                                                          C5.7,0.1,5.5,0,5.2,0L5.2,0z"/>
                                                                                    </svg>  
                                                                                </div>
                                                                            </li><li>
                                                                                <div class="aR">
                                                                                    <svg width="10.4px" height="10px" viewBox="0 0 10.4 10">
                                                                                    <path style="fill:#E99C48;" d="M5.2,0.7l1.1,2.2c0.2,0.4,0.6,0.7,1,0.7L9.8,4L8,5.7C7.7,6,7.5,6.4,7.6,6.9L8,9.3L5.8,8.2
                                                                                          C5.6,8.1,5.4,8,5.2,8C5,8,4.8,8.1,4.6,8.2L2.4,9.3l0.4-2.4C2.9,6.4,2.8,6,2.5,5.7L0.7,4l2.5-0.4c0.4-0.1,0.8-0.3,1-0.7L5.2,0.7
                                                                                          M5.2,0C5,0,4.8,0.1,4.7,0.4L3.5,2.6C3.4,2.8,3.3,2.9,3.1,3L0.5,3.3C0,3.4-0.2,4.1,0.2,4.4L2,6.2c0.2,0.1,0.2,0.4,0.2,0.6L1.8,9.3
                                                                                          C1.7,9.7,2,10,2.4,10c0.1,0,0.2,0,0.3-0.1l2.2-1.2c0.1,0,0.2-0.1,0.3-0.1c0.1,0,0.2,0,0.3,0.1l2.2,1.2C7.9,10,8,10,8.1,10
                                                                                          c0.4,0,0.7-0.3,0.6-0.7L8.3,6.8c0-0.2,0-0.4,0.2-0.6l1.8-1.8c0.4-0.4,0.2-1-0.4-1.1L7.4,3C7.2,2.9,7,2.8,6.9,2.6L5.8,0.4
                                                                                          C5.7,0.1,5.5,0,5.2,0L5.2,0z"/>
                                                                                    </svg>  
                                                                                </div>
                                                                            </li><li>
                                                                                <div class="aR">
                                                                                    <svg width="10.4px" height="10px" viewBox="0 0 10.4 10">
                                                                                    <path style="fill:#E99C48;" d="M5.2,0.7l1.1,2.2c0.2,0.4,0.6,0.7,1,0.7L9.8,4L8,5.7C7.7,6,7.5,6.4,7.6,6.9L8,9.3L5.8,8.2
                                                                                          C5.6,8.1,5.4,8,5.2,8C5,8,4.8,8.1,4.6,8.2L2.4,9.3l0.4-2.4C2.9,6.4,2.8,6,2.5,5.7L0.7,4l2.5-0.4c0.4-0.1,0.8-0.3,1-0.7L5.2,0.7
                                                                                          M5.2,0C5,0,4.8,0.1,4.7,0.4L3.5,2.6C3.4,2.8,3.3,2.9,3.1,3L0.5,3.3C0,3.4-0.2,4.1,0.2,4.4L2,6.2c0.2,0.1,0.2,0.4,0.2,0.6L1.8,9.3
                                                                                          C1.7,9.7,2,10,2.4,10c0.1,0,0.2,0,0.3-0.1l2.2-1.2c0.1,0,0.2-0.1,0.3-0.1c0.1,0,0.2,0,0.3,0.1l2.2,1.2C7.9,10,8,10,8.1,10
                                                                                          c0.4,0,0.7-0.3,0.6-0.7L8.3,6.8c0-0.2,0-0.4,0.2-0.6l1.8-1.8c0.4-0.4,0.2-1-0.4-1.1L7.4,3C7.2,2.9,7,2.8,6.9,2.6L5.8,0.4
                                                                                          C5.7,0.1,5.5,0,5.2,0L5.2,0z"/>
                                                                                    </svg>  
                                                                                </div>
                                                                            </li><li>
                                                                                <div class="aR">
                                                                                    <svg width="10.4px" height="10px" viewBox="0 0 10.4 10">
                                                                                    <path style="fill:#E99C48;" d="M5.2,0.7l1.1,2.2c0.2,0.4,0.6,0.7,1,0.7L9.8,4L8,5.7C7.7,6,7.5,6.4,7.6,6.9L8,9.3L5.8,8.2
                                                                                          C5.6,8.1,5.4,8,5.2,8C5,8,4.8,8.1,4.6,8.2L2.4,9.3l0.4-2.4C2.9,6.4,2.8,6,2.5,5.7L0.7,4l2.5-0.4c0.4-0.1,0.8-0.3,1-0.7L5.2,0.7
                                                                                          M5.2,0C5,0,4.8,0.1,4.7,0.4L3.5,2.6C3.4,2.8,3.3,2.9,3.1,3L0.5,3.3C0,3.4-0.2,4.1,0.2,4.4L2,6.2c0.2,0.1,0.2,0.4,0.2,0.6L1.8,9.3
                                                                                          C1.7,9.7,2,10,2.4,10c0.1,0,0.2,0,0.3-0.1l2.2-1.2c0.1,0,0.2-0.1,0.3-0.1c0.1,0,0.2,0,0.3,0.1l2.2,1.2C7.9,10,8,10,8.1,10
                                                                                          c0.4,0,0.7-0.3,0.6-0.7L8.3,6.8c0-0.2,0-0.4,0.2-0.6l1.8-1.8c0.4-0.4,0.2-1-0.4-1.1L7.4,3C7.2,2.9,7,2.8,6.9,2.6L5.8,0.4
                                                                                          C5.7,0.1,5.5,0,5.2,0L5.2,0z"/>
                                                                                    </svg>  
                                                                                </div>
                                                                            </li><li>
                                                                                <div class="aR">
                                                                                    <svg width="10.4px" height="10px" viewBox="0 0 10.4 10">
                                                                                    <path style="fill:#E99C48;" d="M5.2,0.7l1.1,2.2c0.2,0.4,0.6,0.7,1,0.7L9.8,4L8,5.7C7.7,6,7.5,6.4,7.6,6.9L8,9.3L5.8,8.2
                                                                                          C5.6,8.1,5.4,8,5.2,8C5,8,4.8,8.1,4.6,8.2L2.4,9.3l0.4-2.4C2.9,6.4,2.8,6,2.5,5.7L0.7,4l2.5-0.4c0.4-0.1,0.8-0.3,1-0.7L5.2,0.7
                                                                                          M5.2,0C5,0,4.8,0.1,4.7,0.4L3.5,2.6C3.4,2.8,3.3,2.9,3.1,3L0.5,3.3C0,3.4-0.2,4.1,0.2,4.4L2,6.2c0.2,0.1,0.2,0.4,0.2,0.6L1.8,9.3
                                                                                          C1.7,9.7,2,10,2.4,10c0.1,0,0.2,0,0.3-0.1l2.2-1.2c0.1,0,0.2-0.1,0.3-0.1c0.1,0,0.2,0,0.3,0.1l2.2,1.2C7.9,10,8,10,8.1,10
                                                                                          c0.4,0,0.7-0.3,0.6-0.7L8.3,6.8c0-0.2,0-0.4,0.2-0.6l1.8-1.8c0.4-0.4,0.2-1-0.4-1.1L7.4,3C7.2,2.9,7,2.8,6.9,2.6L5.8,0.4
                                                                                          C5.7,0.1,5.5,0,5.2,0L5.2,0z"/>
                                                                                    </svg>  
                                                                                </div>
                                                                            </li><li>
                                                                                <div class="noR">
                                                                                    (+10k)
                                                                                </div>
                                                                            </li>
                                                                        </ul>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </li><li>
                                                <div class="aP">
                                                    <div class="yt-pk-m">
                                                        <div class="pt">
                                                            <div class="bg"></div>
                                                            <div class="img">
                                                                <img src="https://cdn.youtripper.com/m-mockup/sample-package.jpg"/>
                                                            </div>
                                                            <div class="name">
                                                                Package Name 1<br/>
                                                                Line 2
                                                            </div>
                                                            <div class="loc">
                                                                <svg width="11.3px" height="16px" viewBox="0 0 11.3 16" >
                                                                <path style="fill: #FFFFFF" d="M11.3,5.6C11.3,2.5,8.7,0,5.6,0C2.5,0,0,2.5,0,5.6c0,0.1,0,0.2,0,0.3c0,0,0,3.9,3.7,9
                                                                      c0,0,0,0,0,0c0,0,0,0.1,0.1,0.1l0,0C4.2,15.6,4.9,16,5.6,16c0.8,0,1.5-0.4,1.9-1l0,0c3.7-5.2,3.7-9.2,3.7-9.2l0,0
                                                                      C11.3,5.8,11.3,5.7,11.3,5.6z M5.7,8C4.4,8,3.3,6.9,3.3,5.6c0-1.3,1.1-2.4,2.4-2.4c1.3,0,2.4,1.1,2.4,2.4C8.1,6.9,7,8,5.7,8z"/>
                                                                </svg>
                                                                Bangkok
                                                            </div>
                                                        </div>
                                                        <div class="info">
                                                            <div class="clearfix">

                                                                <div class="price">
                                                                    <ul>
                                                                        <li>
                                                                            <div class="aE">
                                                                                <div class="vertical-center-container">
                                                                                    <div class="center-content">
                                                                                        <div class="pricing">
                                                                                            <div class="currency semi-bold-txt">
                                                                                                ฿
                                                                                            </div>
                                                                                            <div class="number">
                                                                                                <div class="main semi-bold-txt">
                                                                                                    690.00
                                                                                                </div>
                                                                                                <div class="old">
                                                                                                    890.00
                                                                                                </div>
                                                                                            </div>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                            </div> 
                                                                        </li><li>
                                                                            <div class="aE">
                                                                                <div class="vertical-center-container">
                                                                                    <div class="center-content">
                                                                                        <div class="dcP semi-bold-txt">
                                                                                            -60%
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                            </div>
                                                                        </li>
                                                                    </ul>
                                                                </div>

                                                                <div class="book-info">
                                                                    <div class="no-s">
                                                                        24 Sold(s)
                                                                    </div>
                                                                    <div class="reviews">
                                                                        <ul>
                                                                            <li>
                                                                                <div class="aR">
                                                                                    <svg width="10.4px" height="10px" viewBox="0 0 10.4 10">
                                                                                    <path style="fill:#E99C48;" d="M5.2,0.7l1.1,2.2c0.2,0.4,0.6,0.7,1,0.7L9.8,4L8,5.7C7.7,6,7.5,6.4,7.6,6.9L8,9.3L5.8,8.2
                                                                                          C5.6,8.1,5.4,8,5.2,8C5,8,4.8,8.1,4.6,8.2L2.4,9.3l0.4-2.4C2.9,6.4,2.8,6,2.5,5.7L0.7,4l2.5-0.4c0.4-0.1,0.8-0.3,1-0.7L5.2,0.7
                                                                                          M5.2,0C5,0,4.8,0.1,4.7,0.4L3.5,2.6C3.4,2.8,3.3,2.9,3.1,3L0.5,3.3C0,3.4-0.2,4.1,0.2,4.4L2,6.2c0.2,0.1,0.2,0.4,0.2,0.6L1.8,9.3
                                                                                          C1.7,9.7,2,10,2.4,10c0.1,0,0.2,0,0.3-0.1l2.2-1.2c0.1,0,0.2-0.1,0.3-0.1c0.1,0,0.2,0,0.3,0.1l2.2,1.2C7.9,10,8,10,8.1,10
                                                                                          c0.4,0,0.7-0.3,0.6-0.7L8.3,6.8c0-0.2,0-0.4,0.2-0.6l1.8-1.8c0.4-0.4,0.2-1-0.4-1.1L7.4,3C7.2,2.9,7,2.8,6.9,2.6L5.8,0.4
                                                                                          C5.7,0.1,5.5,0,5.2,0L5.2,0z"/>
                                                                                    </svg>  
                                                                                </div>
                                                                            </li><li>
                                                                                <div class="aR">
                                                                                    <svg width="10.4px" height="10px" viewBox="0 0 10.4 10">
                                                                                    <path style="fill:#E99C48;" d="M5.2,0.7l1.1,2.2c0.2,0.4,0.6,0.7,1,0.7L9.8,4L8,5.7C7.7,6,7.5,6.4,7.6,6.9L8,9.3L5.8,8.2
                                                                                          C5.6,8.1,5.4,8,5.2,8C5,8,4.8,8.1,4.6,8.2L2.4,9.3l0.4-2.4C2.9,6.4,2.8,6,2.5,5.7L0.7,4l2.5-0.4c0.4-0.1,0.8-0.3,1-0.7L5.2,0.7
                                                                                          M5.2,0C5,0,4.8,0.1,4.7,0.4L3.5,2.6C3.4,2.8,3.3,2.9,3.1,3L0.5,3.3C0,3.4-0.2,4.1,0.2,4.4L2,6.2c0.2,0.1,0.2,0.4,0.2,0.6L1.8,9.3
                                                                                          C1.7,9.7,2,10,2.4,10c0.1,0,0.2,0,0.3-0.1l2.2-1.2c0.1,0,0.2-0.1,0.3-0.1c0.1,0,0.2,0,0.3,0.1l2.2,1.2C7.9,10,8,10,8.1,10
                                                                                          c0.4,0,0.7-0.3,0.6-0.7L8.3,6.8c0-0.2,0-0.4,0.2-0.6l1.8-1.8c0.4-0.4,0.2-1-0.4-1.1L7.4,3C7.2,2.9,7,2.8,6.9,2.6L5.8,0.4
                                                                                          C5.7,0.1,5.5,0,5.2,0L5.2,0z"/>
                                                                                    </svg>  
                                                                                </div>
                                                                            </li><li>
                                                                                <div class="aR">
                                                                                    <svg width="10.4px" height="10px" viewBox="0 0 10.4 10">
                                                                                    <path style="fill:#E99C48;" d="M5.2,0.7l1.1,2.2c0.2,0.4,0.6,0.7,1,0.7L9.8,4L8,5.7C7.7,6,7.5,6.4,7.6,6.9L8,9.3L5.8,8.2
                                                                                          C5.6,8.1,5.4,8,5.2,8C5,8,4.8,8.1,4.6,8.2L2.4,9.3l0.4-2.4C2.9,6.4,2.8,6,2.5,5.7L0.7,4l2.5-0.4c0.4-0.1,0.8-0.3,1-0.7L5.2,0.7
                                                                                          M5.2,0C5,0,4.8,0.1,4.7,0.4L3.5,2.6C3.4,2.8,3.3,2.9,3.1,3L0.5,3.3C0,3.4-0.2,4.1,0.2,4.4L2,6.2c0.2,0.1,0.2,0.4,0.2,0.6L1.8,9.3
                                                                                          C1.7,9.7,2,10,2.4,10c0.1,0,0.2,0,0.3-0.1l2.2-1.2c0.1,0,0.2-0.1,0.3-0.1c0.1,0,0.2,0,0.3,0.1l2.2,1.2C7.9,10,8,10,8.1,10
                                                                                          c0.4,0,0.7-0.3,0.6-0.7L8.3,6.8c0-0.2,0-0.4,0.2-0.6l1.8-1.8c0.4-0.4,0.2-1-0.4-1.1L7.4,3C7.2,2.9,7,2.8,6.9,2.6L5.8,0.4
                                                                                          C5.7,0.1,5.5,0,5.2,0L5.2,0z"/>
                                                                                    </svg>  
                                                                                </div>
                                                                            </li><li>
                                                                                <div class="aR">
                                                                                    <svg width="10.4px" height="10px" viewBox="0 0 10.4 10">
                                                                                    <path style="fill:#E99C48;" d="M5.2,0.7l1.1,2.2c0.2,0.4,0.6,0.7,1,0.7L9.8,4L8,5.7C7.7,6,7.5,6.4,7.6,6.9L8,9.3L5.8,8.2
                                                                                          C5.6,8.1,5.4,8,5.2,8C5,8,4.8,8.1,4.6,8.2L2.4,9.3l0.4-2.4C2.9,6.4,2.8,6,2.5,5.7L0.7,4l2.5-0.4c0.4-0.1,0.8-0.3,1-0.7L5.2,0.7
                                                                                          M5.2,0C5,0,4.8,0.1,4.7,0.4L3.5,2.6C3.4,2.8,3.3,2.9,3.1,3L0.5,3.3C0,3.4-0.2,4.1,0.2,4.4L2,6.2c0.2,0.1,0.2,0.4,0.2,0.6L1.8,9.3
                                                                                          C1.7,9.7,2,10,2.4,10c0.1,0,0.2,0,0.3-0.1l2.2-1.2c0.1,0,0.2-0.1,0.3-0.1c0.1,0,0.2,0,0.3,0.1l2.2,1.2C7.9,10,8,10,8.1,10
                                                                                          c0.4,0,0.7-0.3,0.6-0.7L8.3,6.8c0-0.2,0-0.4,0.2-0.6l1.8-1.8c0.4-0.4,0.2-1-0.4-1.1L7.4,3C7.2,2.9,7,2.8,6.9,2.6L5.8,0.4
                                                                                          C5.7,0.1,5.5,0,5.2,0L5.2,0z"/>
                                                                                    </svg>  
                                                                                </div>
                                                                            </li><li>
                                                                                <div class="aR">
                                                                                    <svg width="10.4px" height="10px" viewBox="0 0 10.4 10">
                                                                                    <path style="fill:#E99C48;" d="M5.2,0.7l1.1,2.2c0.2,0.4,0.6,0.7,1,0.7L9.8,4L8,5.7C7.7,6,7.5,6.4,7.6,6.9L8,9.3L5.8,8.2
                                                                                          C5.6,8.1,5.4,8,5.2,8C5,8,4.8,8.1,4.6,8.2L2.4,9.3l0.4-2.4C2.9,6.4,2.8,6,2.5,5.7L0.7,4l2.5-0.4c0.4-0.1,0.8-0.3,1-0.7L5.2,0.7
                                                                                          M5.2,0C5,0,4.8,0.1,4.7,0.4L3.5,2.6C3.4,2.8,3.3,2.9,3.1,3L0.5,3.3C0,3.4-0.2,4.1,0.2,4.4L2,6.2c0.2,0.1,0.2,0.4,0.2,0.6L1.8,9.3
                                                                                          C1.7,9.7,2,10,2.4,10c0.1,0,0.2,0,0.3-0.1l2.2-1.2c0.1,0,0.2-0.1,0.3-0.1c0.1,0,0.2,0,0.3,0.1l2.2,1.2C7.9,10,8,10,8.1,10
                                                                                          c0.4,0,0.7-0.3,0.6-0.7L8.3,6.8c0-0.2,0-0.4,0.2-0.6l1.8-1.8c0.4-0.4,0.2-1-0.4-1.1L7.4,3C7.2,2.9,7,2.8,6.9,2.6L5.8,0.4
                                                                                          C5.7,0.1,5.5,0,5.2,0L5.2,0z"/>
                                                                                    </svg>  
                                                                                </div>
                                                                            </li><li>
                                                                                <div class="noR">
                                                                                    (+10k)
                                                                                </div>
                                                                            </li>
                                                                        </ul>
                                                                    </div>
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
                        </div>
                    </div>

                    <div class="hp-m hi">
                        <div class="yt-container">
                            <div class="hp-m-con">
                                <div class="m-b">
                                    <div class="ad-m">
                                        <div class="bg"></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <%@include file="header.jsp" %>
    </body>

    <style>
        #hp-page .hp-cover {
            margin-bottom: 10px;
        }

        #hp-page .hp-cover .aCover .bg {
            background-color: #F8F7F9;
            width: 100%;
            padding-bottom: 50%;
        }

        #hp-page .hp-m {
            margin-bottom: 15px;
        }
        
        #hp-page .hp-m.hi {
            margin-bottom: 25px;
        }

        #hp-page .hp-m .m-h {
            color: #E94848;
        }

        #hp-page .hp-m .m-h.cen {
            text-align: center;
        }

        #hp-page .hp-m .m-h .yt-btn {
            display: inline-block;
            width: auto;
            padding: 0 10px;
            line-height: 25px;
            height: 27px;
        }

        #hp-page .hp-m .m-h button.yt-btn {
            height: 27px;
            line-height: auto;
        }

        #hp-page .hp-m .ad-m .bg {
            background-color: #9C9B9C;
            width: 100%;
            padding-bottom: 33.333%;
        }

        #cat-m .cates {
            overflow: hidden;
            padding: 5px 0 10px 0;
        }

        #cat-m .cates > ul {
            margin: 0 -5px;
            white-space: nowrap;
        }

        #cat-m .cates > ul > li {
            display: inline-block;
            padding: 0 5px;
        }

        #cat-m .cates .acat {
            width: 120px;
            -webkit-box-shadow: 3px 3px 5px 1px rgba(0,0,0,0.2);
            -moz-box-shadow: 3px 3px 5px 1px rgba(0,0,0,0.2);
            box-shadow: 3px 3px 5px 1px rgba(0,0,0,0.2);
        }

        #cat-m .cates .acat .bg {
            background-color: #4cbdc9;
            width: 100%;
            padding-bottom: 50%;
        }

        #cat-m .cates .acat .name {
            font-size: 11px;
            text-align: center;
        }

        #hp-page .hp-m .pk-m {
            padding-top: 5px;
            padding-bottom: 10px;
            overflow: hidden;
        }

        #hp-page .pk-m .pkgs {
            white-space: nowrap;
            margin: 0 -5px;
        }

        #hp-page .pk-m .pkgs > li {
            display: inline-block;
            padding: 0 5px;
        }

        #hp-page .pk-m .aP {
            width: 240px;
        }
    </style>
</html>
