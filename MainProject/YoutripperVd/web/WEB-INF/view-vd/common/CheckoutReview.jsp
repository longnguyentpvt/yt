<%-- 
    Document   : CheckoutReview
    Created on : Jul 10, 2018, 5:49:27 PM
    Author     : nickn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="${pageContext.response.locale}" currency="${sessionScope.ytCurrency}" ng-app="youtripper" ng-controller="YoutripperController">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="commonhead.jsp" %>

        <script src="${requestScope.jsURL}checkout-review.js?9212232529332445"></script>

        <title>Checkout Review - Youtripper</title>
    </head>
    <body ng-cloak>
        <div id="padding-header" ng-controller="CheckoutReview">
            <div class="yt-normal-container">
                <div>
                    Pkg ID : {{cr.vars.if.pid}}
                </div>
                <div>
                    Pkg Price : {{cr.vars.if.ppr}}
                </div>
                <div>
                    QTY : {{cr.vars.if.qty}}
                </div>
                <div>
                    Sub Total : {{cr.vars.if.stt}}
                </div>
                <c:if test="${sessionScope.ytAccount != null && sessionScope.ytAccount.tripper}">
                    <div>
                        Discount Code<br/>
                        <input type="text" placeholder="Discount Code" class="yt-input" style="width: 200px"
                               ng-model="cr.vars.dc.code" ytmaxlength="30"/>
                    </div>
                </c:if>
                <div>
                    Total : {{cr.vars.if.tt}}
                </div>
                <div>
                    Currency : {{cr.vars.if.cc}}
                </div>
                <br/>
                <div>
                    Email<br/>
                    <input type="text" placeholder="Tripper Email" class="yt-input" style="width: 200px"
                           ng-model="cr.vars.tem" ytmaxlength="30"/>
                </div>
                <div>
                    Billig First Name<br/>
                    <input type="text" placeholder="Billing First Name" class="yt-input" style="width: 200px"
                           ng-model="cr.vars.bl.fn" ytmaxlength="30"/>
                </div>
                <div>
                    Billig Last Name<br/>
                    <input type="text" placeholder="Billing Last Name" class="yt-input" style="width: 200px"
                           ng-model="cr.vars.bl.ln" ytmaxlength="30"/>
                </div>
                <div>
                    Method<br/>
                    <div class="ddl-input nod" style="width: 200px">
                        <select class="yt-input" ng-model="cr.vars.mt">
                            <option value="vm">
                                Visa Master
                            </option>
                            <option value="pp">
                                Paypal
                            </option>
                            <option value="lp">
                                Linepay
                            </option>
                        </select>
                    </div>
                </div>
                <br/>
                <div>
                    <button class="yt-btn red-btn"  style="width: 200px" ng-click="cr.funcs.co()">Pay</button>
                </div>
                <form id="co-f" method="POST" action="/vm-checkout" style="display: none">
                    <input type="text" ng-model="cr.vars.on" name="orderNo"/>
                    <input type="submit" name="btnsm" value="Submit"/>
                </form>
            </div>
            <%@include file="header.jsp" %>
        </div>
    </body>
    <script>
        var tid = '${param.tid}';
    </script>
</html>
