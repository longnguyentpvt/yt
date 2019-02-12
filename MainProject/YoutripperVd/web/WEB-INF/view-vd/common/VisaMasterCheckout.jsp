<%-- 
    Document   : VisaMasterCheckout
    Created on : Jul 10, 2018, 5:37:37 PM
    Author     : nickn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="${pageContext.response.locale}" currency="${sessionScope.ytCurrency}" ng-app="youtripper" ng-controller="YoutripperController">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="commonhead.jsp" %>

        <link rel="stylesheet" href="${requestScope.cssURL}visamaster-checkout.css" >

        <script src="${requestScope.jsURL}visamaster-payment-checkout.js?93323229"></script>

        <title>Visa Master Payment - Youtripper</title>
    </head>
    <body>
        <div id="padding-header" ng-controller="VisaMasterPayment" ng-cloak>
            <div id="vm-pm">
                <div id="co-stt">
                    <div class="yt-smallest-container">
                        <div class="stts">
                            <ul class="clearfix">
                                <li>
                                    <div class="aS d">
                                        Selection
                                    </div>
                                </li>
                                <li>
                                    <div class="aS d">
                                        Review
                                    </div>
                                </li>
                                <li>
                                    <div class="aS c">
                                        Payment
                                    </div>
                                </li>
                                <li>
                                    <div class="aS">
                                        Confirmation
                                    </div>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>

                <div id="vm-bd">
                    <div class="yt-smallest-container">
                        <div class="bd-tt">
                            PAYMENT - Booking No. {{pm.vars.on}}
                        </div>
                        <div class="bd-f">
                            <ul class="opts">
                                <li>
                                    <div class="aO">
                                        <div class="o-slt">
                                            <div class="checkbox-ctn">
                                                <div class="yt-checkbox radio">
                                                    <input type="radio" value="ex" ng-model="pm.vars.op">
                                                    <span></span>
                                                </div>
                                                <div class="na">
                                                    Choose an existing card
                                                </div>
                                            </div>
                                        </div>
                                        <div class="o-f re-o" ng-if="pm.vars.op === 'ex'">
                                            <ul>
                                                <li>
                                                    <div class="aR">
                                                        <div class="lb">
                                                            Card Number
                                                        </div>
                                                        <div class="rin">
                                                            <div class="ddl-input">
                                                                <select class="yt-input">
                                                                    <option value="">XXXX-XXXX-XXXX-1234</option>
                                                                </select>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </li>
                                                <li>
                                                    <div class="aR">
                                                        <div class="lb">
                                                            Name on Card
                                                        </div>
                                                        <div class="rin">
                                                            <input type="text" class="yt-input ro" value="Long Nguyen" readonly/>
                                                        </div>
                                                    </div>
                                                </li>
                                                <li>
                                                    <div class="aR">
                                                        <div class="lb">
                                                            Expiry
                                                        </div>
                                                        <div class="rin">
                                                            <input type="text" class="yt-input ro" value="07 / 2020" readonly/>
                                                        </div>
                                                    </div>
                                                </li>
                                            </ul>
                                        </div>
                                    </div>
                                </li>
                                <li>
                                    <div class="aO">
                                        <div class="o-slt">
                                            <div class="yt-checkbox radio">
                                                <input type="radio" value="new" ng-model="pm.vars.op">
                                                <span></span>
                                            </div>
                                            <div class="na">
                                                Use a new card
                                                <img src="${requestScope.imageURL}9d4039df3eac30e6256d12413ac5fc8f.png"/>
                                                <img src="${requestScope.imageURL}a0e7b1e14592f3ea131c5ab8af96f8aa.png"/>
                                            </div>
                                        </div>
                                        <div class="o-f re-o"  ng-if="pm.vars.op === 'new'">
                                            <ul>
                                                <li>
                                                    <div class="aR">
                                                        <div class="lb">
                                                            Card Number
                                                        </div>
                                                        <div class="rin">
                                                            <input type="text" class="yt-input" placeholder="XXXX-XXXX-XXXX-XXXX" number-str-input
                                                                   ng-model="pm.vars.new.sno" ng-change="pm.funcs.snoc()"  ytmaxlength="19"/>
                                                        </div>
                                                    </div>
                                                </li>
                                                <li>
                                                    <div class="aR">
                                                        <div class="lb">
                                                            Name on Card
                                                        </div>
                                                        <div class="rin">
                                                            <input type="text" class="yt-input" placeholder="Card Holder Name"
                                                                   ng-model="pm.vars.new.hn" ytmaxlength="200"/>
                                                        </div>
                                                    </div>
                                                </li>
                                                <li>
                                                    <div class="aR e-r">
                                                        <div class="sec-i">
                                                            <div class="lb">
                                                                CVC / CVV
                                                            </div>
                                                            <div class="rin">
                                                                <input type="text" class="yt-input center-txt" placeholder="CVC / CVV"
                                                                       ng-model="pm.vars.new.sec" number-str-input ytmaxlength="3"/>
                                                            </div>
                                                        </div>
                                                        <div class="ed-i">
                                                            <ul>
                                                                <li>
                                                                    <div class="lb">
                                                                        Expiry
                                                                    </div>
                                                                    <div class="rin">
                                                                        <div class="ddl-input" ng-class="{'nov' : !pm.vars.new.em.length}">
                                                                            <select class="yt-input" ng-model="pm.vars.new.em">
                                                                                <option value="" disabled>
                                                                                    MM
                                                                                </option>
                                                                                <option value="01">01</option>
                                                                                <option value="02">02</option>
                                                                                <option value="03">03</option>
                                                                                <option value="04">04</option>
                                                                                <option value="05">05</option>
                                                                                <option value="06">06</option>
                                                                                <option value="07">07</option>
                                                                                <option value="08">08</option>
                                                                                <option value="09">09</option>
                                                                                <option value="10">10</option>
                                                                                <option value="11">11</option>
                                                                                <option value="12">12</option>
                                                                            </select>
                                                                        </div>
                                                                    </div>
                                                                </li><li>
                                                                    <div class="lb">
                                                                        &nbsp;
                                                                    </div>
                                                                    <div class="rin">
                                                                        <div class="ddl-input" ng-class="{'nov' : !pm.vars.new.ey.length}">
                                                                            <select class="yt-input" ng-model="pm.vars.new.ey">
                                                                                <option value="" disabled>
                                                                                    YYYY
                                                                                </option>
                                                                                <option value="2018">2018</option>
                                                                                <option value="2019">2019</option>
                                                                                <option value="2020">2020</option>
                                                                                <option value="2021">2021</option>
                                                                                <option value="2022">2022</option>
                                                                                <option value="2023">2023</option>
                                                                                <option value="2024">2024</option>
                                                                                <option value="2025">2025</option>
                                                                                <option value="2026">2026</option>
                                                                                <option value="2027">2027</option>
                                                                                <option value="2028">2028</option>
                                                                                <option value="2029">2029</option>
                                                                                <option value="2030">2030</option>
                                                                                <option value="2031">2031</option>
                                                                                <option value="2032">2032</option>
                                                                                <option value="2033">2033</option>
                                                                                <option value="2034">2034</option>
                                                                                <option value="2035">2035</option>
                                                                                <option value="2036">2036</option>
                                                                                <option value="2037">2037</option>
                                                                                <option value="2038">2038</option>
                                                                            </select>
                                                                        </div>
                                                                    </div>
                                                                </li>
                                                            </ul>
                                                        </div>
                                                    </div>
                                                </li>   
                                                <li>
                                                    <div class="aR">
                                                        <div class="rin">
                                                            <div class="checkbox-ctn">
                                                                <div class="yt-checkbox">
                                                                    <input type="checkbox" name="" value="ON">
                                                                    <span></span>
                                                                </div> 
                                                                Save this card for the future use
                                                            </div>
                                                        </div>
                                                    </div>
                                                </li>
                                            </ul>
                                        </div>
                                    </div>
                                </li>
                                <li>
                                    <div class="ctrls">
                                        <ul>
                                            <li class="pay-ctrl">
                                                <button class="yt-btn red-btn semi-bold-txt" ng-click="pm.funcs.pay()">
                                                    Pay {{pm.vars.tt|currency:"":2}} {{pm.vars.cc}}
                                                </button>
                                            </li><li>
                                                <button class="yt-btn grey-btn semi-bold-txt">
                                                    Cancel
                                                </button>
                                            </li>
                                        </ul>
                                    </div>
                                </li>
                            </ul>
                        </div>
                        <form id="2c2p-payment-form" action="/vm-payment-request" method="POST" style="display: none">
                            <input type="text" class="yt-input" data-encrypt="cardnumber" name="cardnumber" placeholder="Card Number" ng-model="pm.vars.new.rno"/>
                            <input type="text" class="yt-input" data-encrypt="month" name="month" placeholder="Month" ng-model="pm.vars.new.em"/>
                            <input type="text" class="yt-input" data-encrypt="year" name="year" placeholder="Year" ng-model="pm.vars.new.ey"/>
                            <input type="text" class="yt-input" placeholder="cvv" 
                                   autocomplete="off" data-encrypt="cvv" name="cvv"  ng-model="pm.vars.new.sec"/>
                            <input type="text" class="yt-input" name="on" ng-model="pm.vars.on"/>
                            <input type="text" class="yt-input" name="cardHolderName" ng-model="pm.vars.new.hn"/>
                            <input type="submit" value="Pay"/>
                        </form>
                    </div>
                </div>
            </div>

            <%@include file="header.jsp" %>
        </div>
    </body>
    <script type="text/javascript" src="<spring:message code="yt.payment.2c2p.jslink"/>"></script>
    <script>
        var rdt = ${requestScope.rp};
    </script>
</html>
