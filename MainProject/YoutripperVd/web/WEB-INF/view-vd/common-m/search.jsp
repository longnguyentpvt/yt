<%-- 
    Document   : search
    Created on : May 25, 2018, 10:51:17 AM
    Author     : nickn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="${pageContext.response.locale}" currency="${sessionScope.ytCurrency}" ng-app="youtripper" ng-controller="YoutripperController">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="commonhead.jsp" %>
        <script src="${requestScope.jsURL}/search-module.js"></script>

        <title>Youtripper - Search Module</title>  
    </head>
    <body ng-controller="SearchModule">
        <div id="padding-header">
            <div id="search-m">
                <div class="search-con">
                    <div id="search-filter">
                        <div class="no">
                            Find 23 Activities In <span class="hl">Bangkok</span>
                        </div>
                        <div class="ops">
                            <ul class="clearfix">
                                <li>
                                    <div class="aO" ng-click="search.funcs.oFilter('pop')"
                                         ng-class="{'active' : search.vars.filter.t === 'pop'}">
                                        <div class="btn">
                                            <svg width="9.4px" height="5.4px" viewBox="0 0 9.4 5.4">
                                            <line style="fill:none;stroke:#9C9B9C;stroke-linecap:square;stroke-miterlimit:10;" x1="4.7" y1="4.7" x2="0.7" y2="0.7"/>
                                            <line style="fill:none;stroke:#9C9B9C;stroke-linecap:square;stroke-miterlimit:10;" x1="8.7" y1="0.7" x2="4.7" y2="4.7"/>
                                            </svg>
                                        </div>
                                        Popularity
                                    </div>
                                </li>
                                <li>
                                    <div class="aO" ng-click="search.funcs.oFilter('cat')"
                                         ng-class="{'active' : search.vars.filter.t === 'cat'}">
                                        <div class="btn">
                                            <svg width="9.4px" height="5.4px" viewBox="0 0 9.4 5.4">
                                            <line style="fill:none;stroke:#9C9B9C;stroke-linecap:square;stroke-miterlimit:10;" x1="4.7" y1="4.7" x2="0.7" y2="0.7"/>
                                            <line style="fill:none;stroke:#9C9B9C;stroke-linecap:square;stroke-miterlimit:10;" x1="8.7" y1="0.7" x2="4.7" y2="4.7"/>
                                            </svg>
                                        </div>
                                        Categories
                                    </div>
                                </li>
                                <li>
                                    <div class="aO" ng-click="search.funcs.oFilter('price')"
                                         ng-class="{'active' : search.vars.filter.t === 'price'}">
                                        <div class="btn">
                                            <svg width="9.4px" height="5.4px" viewBox="0 0 9.4 5.4">
                                            <line style="fill:none;stroke:#9C9B9C;stroke-linecap:square;stroke-miterlimit:10;" x1="4.7" y1="4.7" x2="0.7" y2="0.7"/>
                                            <line style="fill:none;stroke:#9C9B9C;stroke-linecap:square;stroke-miterlimit:10;" x1="8.7" y1="0.7" x2="4.7" y2="4.7"/>
                                            </svg>
                                        </div>
                                        Price
                                    </div>
                                </li>
                            </ul>
                        </div>

                        <div class="filter-menu-ops" ng-class="search.vars.filter.cl">
                            <div class="aM">
                                <ul>
                                    <li>
                                        <div class="aM-o">
                                            <div class="tick">
                                                <svg width="16.5px" height="12.5px" viewBox="0 0 16.5 12.5" >
                                                <line style="fill:none;stroke:#4cbdc9;stroke-width:2;stroke-linecap:round;stroke-linejoin:round;stroke-miterlimit:10;" x1="5" y1="11.5" x2="1" y2="7.5"/>
                                                <line style="fill:none;stroke:#4cbdc9;stroke-width:2;stroke-linecap:round;stroke-linejoin:round;stroke-miterlimit:10;" class="st0" x1="15.5" y1="1" x2="5" y2="11.5"/>
                                                </svg>
                                            </div>
                                            <div class="name">
                                                Recommended
                                            </div>
                                        </div>
                                    </li>
                                    <li>
                                        <div class="aM-o">
                                            <div class="name">
                                                The most-reviewed activities
                                            </div>
                                        </div>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>

                    <div id="search-result">
                        <div class="pkg-container">
                            <div class="pkgs">
                                <ul class="clearfix">
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
                                    </li>

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
                                    </li>

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
                                    </li>

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
                                    </li>

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
                                    </li>

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
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%@include file="header.jsp" %>
    </body>
    <style>
        #search-filter {
            position: fixed;
            top: 0;
            left: 0;
            right: 0;
            margin-top: 50px;
            z-index: 2;
        }

        #search-result {
            margin-top: 60px;
        }

        #search-filter .no {
            text-align: center;
            line-height: 30px;
            background-color: #D6D4D4;
            color: #FFF;
        }

        #search-filter .no .hl {
            color: #4cbdc9;
        }

        #search-filter .ops > ul > li {
            float: left;
            width: 33.33%;
            border-right: solid 1px #D6D4D4;
            border-bottom: solid 1px #D6D4D4;
        }

        #search-filter .ops > ul > li:last-of-type {
            border-right: 0;
        }

        #search-filter .ops .aO {
            text-align: center;
            line-height: 30px;
            position: relative;
            font-size: 12px;
            background-color: #fff;
        }

        #search-filter .ops .aO.active {
            background-color: #D6D4D4;
            color: #fff;
        }

        #search-filter .ops .aO.active svg line {
            stroke: #FFF !important;
        }

        #search-filter .ops .aO .btn {
            position: absolute;
            right: 5px;
        }

        #search-filter .filter-menu-ops {
            background-color: #fff;
            position: fixed;
            z-index: 2;
            left: 0;
            right: 0;
            top: 0;
            bottom: 0;
            margin-top: 111px;
            display: none;
        }

        #search-filter .filter-menu-ops.op {
            display: block;
        }

        #search-filter .aM {
            height: 100%;
            overflow: auto;
        }

        #search-filter .aM .aM-o {
            padding: 0 10px;
            font-size: 12px;
            line-height: 30px;
        }

        #search-filter .aM .aM-o .tick {
            float: right;
        }

        #search-filter .aM > ul > li {
            border-bottom: solid 1px #D6D4D4;
        }

        #search-result .pkgs {
            padding: 20px;
        }

        #search-result .pkgs > ul {
            margin: -10px;
        }

        #search-result .pkgs > ul > li {
            float: left;
            padding: 10px;
            width: 100%;
        }

        @media (min-width: 540px) { 
            #search-result .pkgs > ul > li {
                width: 50%;
            }
        }

        @media (min-width: 800px) { 
            #search-result .pkgs > ul > li {
                width: 33.33%;
            }
        }
    </style>

</html>
