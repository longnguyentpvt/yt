<%-- 
    Document   : design
    Created on : May 23, 2018, 12:39:29 PM
    Author     : nickn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="${pageContext.response.locale}" currency="${sessionScope.ytCurrency}" ng-app="youtripper" ng-controller="YoutripperController">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="commonhead.jsp" %>

        <title>Youtripper - Common CSS</title>
    </head>
    <body style="background-color: #F8F7F9">
        <div id="padding-header">
            <div class="yt-container">
                <span class="light-txt">Light Text</span>
                <br/>
                <br/>
                <span>Normal Text</span>
                <br/>
                <br/>
                <span class="semi-bold-txt">Semi Bold Text</span>
                <br/>
                <br/>
                <span class="bold-txt">Bold Text</span>
                <br/>
                <br/>
                <span class="link">Non SEO Link</span>
                <br/>
                <br/>
                <a href="" class="link">SEO Link</a>
                <br/>
                <br/>
                <button class="yt-btn">Input Button</button>
                <br/>
                <a href="" class="yt-btn">Href Button</a>
                <br/>
                <button class="yt-btn red-btn">Red Button</button>
                <br/>
                <button class="yt-btn orange-btn">Orange Button</button>
                <br/>
                <button class="yt-btn yellow-btn">Yellow Button</button>
                <br/>
                <button class="yt-btn green-btn">Green Button</button>
                <br/>
                <button class="yt-btn grey-btn">Grey Button</button>
                <br/>
                <button class="yt-btn auto-width-btn">Auto Width Button</button>
                <br/>
                <br/>
                <a class="yt-btn auto-width-btn">Auto Width Href</a>
                <br/>
                <br/>
                <input type="text" class="yt-input" placeholder="Border Input"/>
                <br/>
                <input type="text" class="yt-input no-b" placeholder="No Border Input"/>
                <br/>
                <input type="text" class="yt-input grey-i" placeholder="Grey Input"/>
                <br/>
                <input type="text" placeholder="Readonly Input" readonly="readonly" class="yt-input readonly" />
                <br/>
                <div class="close-input">
                    <input type="text" placeholder="Text Input With Close Btn" class="yt-input"/>
                    <span class="close-btn"></span>
                </div>
                <br/>
                <div class="yt-input-ico">
                    <input type="text" placeholder="Text Input With Icon" class="yt-input grey-i"/>
                    <div class="ico">

                        <svg  width="18.8px" height="15px" viewBox="0 0 18.8 15">
                        <path style="fill: #9C9B9C" d="M16.9,0h-15C0.8,0,0,0.8,0,1.9l0,11.3c0,1,0.8,1.9,1.9,1.9h15c1,0,1.9-0.8,1.9-1.9V1.9
                              C18.8,0.8,17.9,0,16.9,0z M16.9,3.8L9.4,8.4L1.9,3.8V1.9l7.5,4.7l7.5-4.7V3.8z"/>
                        </svg>

                    </div>
                </div>
                <br/>
                <textarea class="yt-input" placeholder="Textarea"></textarea>
                <br/>
                <input type="text" name="" value="" placeholder="Center Text Input" class="yt-input center-txt" />
                <br/>
                <div class="ddl-input">
                    <select name="" class="yt-input">
                        <option selected="selected">Dropdown list</option>
                        <option>1</option>
                        <option>2</option>
                    </select>
                </div>
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
                <br/>
                <br/>
                <br/>
            </div>
        </div>

        <%@include file="header.jsp" %>
    </body>
</html>
