<%-- 
    Document   : PackageModuleJS
    Created on : Aug 8, 2018, 11:04:40 AM
    Author     : nickn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="yt-pkg">
    <a ng-href="{{ap.siteURL}}">
        <div class="thumb" ng-style="{'background-image' : ('url(' + ap.thumb + ')')}">
            <div class="loc">
                <svg width="11.3px" height="16px" viewBox="0 0 11.3 16">
                <path style="fill:#FFFFFF;" d="M11.3,5.6C11.3,2.5,8.7,0,5.6,0C2.5,0,0,2.5,0,5.6c0,0.1,0,0.2,0,0.3c0,0,0,3.9,3.7,9
                      c0,0,0,0,0,0c0,0,0,0.1,0.1,0.1l0,0C4.2,15.6,4.9,16,5.6,16c0.8,0,1.5-0.4,1.9-1l0,0c3.7-5.2,3.7-9.2,3.7-9.2l0,0
                      C11.3,5.8,11.3,5.7,11.3,5.6z M5.7,8C4.4,8,3.3,6.9,3.3,5.6c0-1.3,1.1-2.4,2.4-2.4c1.3,0,2.4,1.1,2.4,2.4C8.1,6.9,7,8,5.7,8z"/>
                </svg> <span ng-bind="ap.loc"></span>
            </div>
            <div class="na" ng-bind="ap.name"></div>
        </div>
    </a>
    <div class="info">
        <div class="price" ng-class="{'dc' : ap.promotional}">
            <ul>
                <li>
                    <div class="sym" ng-bind="ap.sym"></div>
                </li><li>
                    <div class="nu">
                        <div class="rp" ng-bind='ap.price |currency:"":2'></div>
                        <div class="op" ng-bind='ap.oPrice |currency:"":2'></div>
                    </div>
                </li><li>
                    <div class="pc">
                        <span ng-bind="'-' + ap.dcPercent + '%'"></span>
                    </div>
                </li>
            </ul>
        </div>
        <div class="rv-i">
            <div class="rates">
                <ul>
                    <li  ng-repeat='i in [1, 2, 3, 4, 5]'>
                        <div class="ar" ng-class="{'at' : i <= ap.rate}">
                            <svg vidth="10.4px" height="10px" viewBox="0 0 10.4 10"> <path class="nat" style="fill: #f9b03c" d="M5.2,0.7l1.1,2.2c0.2,0.4,0.6,0.6,1,0.7L9.7,4L8,5.7C7.7,6,7.5,6.4,7.6,6.9L8,9.3L5.8,8.2 C5.6,8.1,5.4,8,5.2,8S4.8,8,4.6,8.2L2.4,9.3l0.4-2.4C2.9,6.4,2.7,6,2.5,5.7L0.7,4l2.4-0.4c0.4-0.1,0.8-0.3,1-0.7L5.2,0.7 M5.2,0 C5,0,4.8,0.1,4.7,0.4L3.5,2.6C3.4,2.8,3.2,3,3,3L0.5,3.3C0,3.4-0.2,4,0.2,4.4L2,6.2c0.2,0.2,0.2,0.4,0.2,0.6L1.7,9.2 C1.7,9.7,2,10,2.4,10c0.1,0,0.2,0,0.3-0.1l2.2-1.2c0.1,0,0.2-0.1,0.3-0.1c0.1,0,0.2,0,0.3,0.1l2.2,1.2C7.8,10,7.9,10,8,10 c0.4,0,0.7-0.3,0.6-0.8L8.2,6.8c0-0.2,0-0.4,0.2-0.6l1.8-1.8c0.4-0.4,0.2-1-0.4-1.1L7.3,3C7.1,3,6.9,2.8,6.8,2.6L5.7,0.4 C5.7,0.1,5.4,0,5.2,0L5.2,0z"></path> <path class="at" style="fill: #f9b03c" d="M5.2,0C5,0,4.8,0.1,4.7,0.4L3.5,2.6C3.5,2.8,3.3,3,3.1,3L0.5,3.4C0,3.4-0.2,4.1,0.2,4.4L2,6.2 c0.2,0.2,0.2,0.4,0.2,0.6L1.7,9.2C1.7,9.6,2,10,2.4,10c0.1,0,0.2,0,0.3-0.1l2.2-1.2c0.1,0,0.2-0.1,0.3-0.1c0.1,0,0.2,0,0.3,0.1 l2.2,1.2C7.8,10,7.9,10,8,10c0.4,0,0.7-0.3,0.6-0.8L8.2,6.8c0-0.2,0-0.4,0.2-0.6l1.8-1.8c0.4-0.4,0.2-1-0.4-1.1L7.3,3 C7.1,3,6.9,2.8,6.8,2.6L5.7,0.4C5.7,0.1,5.5,0,5.2,0L5.2,0z"></path> </svg>
                        </div>
                    </li><li>
                        <div class="n-r" ng-bind="'(' + ap.noComments + ')'"></div>
                    </li>
                </ul>
            </div>
            <div class="n-booked" ng-bind="ap.noSolds + ' Booked'"></div>
        </div>
    </div>
</div>
