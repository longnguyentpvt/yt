<%-- 
    Document   : TrippDash
    Created on : Jul 5, 2018, 10:27:58 PM
    Author     : KyLong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="${pageContext.response.locale}" currency="${sessionScope.ytCurrency}" ng-app="youtripper" ng-controller="YoutripperController">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="../common/commonhead.jsp" %>

        <link rel="stylesheet" href="${requestScope.cssURL}partner-trippdash-menu.css" >
        <link rel="stylesheet" href="${requestScope.cssURL}partner-payment.css" >

        <script src="${requestScope.jsURL}partner-payment.js"></script>

        <title>Payment</title>
    </head>
    <body>
        <div id="padding-header" ng-cloak ng-controller="Payment">
            <!--MENU-->
            <%@include file="TrippDashMenu.jsp" %>
            <!--END MENU-->

            <div id="trippdash-body" ng-init="trippdashSection = 'payment'">
                <div class="yt-normal-container">
                    <div id="payment-manager">
                        <div class="payment-filter">
                            <ul class="clearfix">
                                <li class="filter-year">
                                    <div class="ddl-input">
                                        <select class="yt-input" ng-model="payment.vars.filter.sly"
                                                ng-change="payment.funcs.cy()"
                                                > 
                                            <option ng-repeat="aYear in payment.vars.DATA.YEARS"
                                                    ng-value="aYear">Y-{{aYear}}</option>
                                        </select>
                                    </div>
                                </li>
                                <li class="filter-month">
                                    <div class="ddl-input">
                                        <select name="" class="yt-input" ng-model="payment.vars.filter.slm"
                                                ng-change="payment.funcs.cm()">
                                            <option ng-value="null"><spring:message code="yt.partner.payment.filter.allMonth"/></option>
                                            <option ng-value="1"><spring:message code="yt.partner.payment.filter.month1"/></option>
                                            <option ng-value="2"><spring:message code="yt.partner.payment.filter.month2"/></option>
                                            <option ng-value="3"><spring:message code="yt.partner.payment.filter.month3"/></option>
                                            <option ng-value="4"><spring:message code="yt.partner.payment.filter.month4"/></option>
                                            <option ng-value="5"><spring:message code="yt.partner.payment.filter.month5"/></option>
                                            <option ng-value="6"><spring:message code="yt.partner.payment.filter.month6"/></option>
                                            <option ng-value="7"><spring:message code="yt.partner.payment.filter.month7"/></option>
                                            <option ng-value="8"><spring:message code="yt.partner.payment.filter.month8"/></option>
                                            <option ng-value="9"><spring:message code="yt.partner.payment.filter.month9"/></option>
                                            <option ng-value="10"><spring:message code="yt.partner.payment.filter.month10"/></option>
                                            <option ng-value="11"><spring:message code="yt.partner.payment.filter.month11"/></option>
                                            <option ng-value="12"><spring:message code="yt.partner.payment.filter.month12"/></option>
                                        </select>
                                    </div>
                                </li>
                                <li class="filter-status">
                                    <div class="ddl-input">
                                        <select name="" class="yt-input" ng-model="payment.vars.filter.status"
                                                ng-change="payment.funcs.cstt()">
                                            <option ng-value="null"><spring:message code="yt.partner.payment.filter.allBill"/></option>
                                            <option ng-value="payment.vars.DATA.BILL_STATUS.paid"><spring:message code="yt.partner.payment.filter.paidBill"/></option>
                                            <option ng-value="payment.vars.DATA.BILL_STATUS.processing"><spring:message code="yt.partner.payment.filter.processingBill"/></option>
                                            <option ng-value="payment.vars.DATA.BILL_STATUS.payable"><spring:message code="yt.partner.payment.filter.payableBill"/></option>
                                            <option ng-value="payment.vars.DATA.BILL_STATUS.pending"><spring:message code="yt.partner.payment.filter.pendingBill"/></option>
                                        </select>
                                    </div>
                                </li>
                                <li class="bucket">
                                    <button class="yt-btn red-btn"><spring:message code="yt.partner.payment.filter.viewWithdraw"/></button>
                                </li>
                            </ul>
                        </div>
                        <div id="payment-tb" >
                            <div class="tbl">
                                <div class="row h-row">
                                    <div class="cols">
                                        <ul class="clearfix">
                                            <li class="aCol bo-col">
                                                <div class="col-t">
                                                    <div class="vertical-center-container">
                                                        <div class="center-content">
                                                            <spring:message code="yt.partner.payment.col.bookingNo"/>
                                                        </div>
                                                    </div>
                                                </div>
                                            </li>
                                            <li class="aCol bd-col">
                                                <div class="col-t">
                                                    <div class="vertical-center-container">
                                                        <div class="center-content">
                                                            <spring:message code="yt.partner.payment.col.bookingDate"/>
                                                        </div>
                                                    </div>
                                                </div>
                                            </li>
                                            <li class="aCol pki-col">
                                                <div class="col-t">
                                                    <div class="vertical-center-container">
                                                        <div class="center-content">
                                                            <spring:message code="yt.partner.payment.col.packageID"/>
                                                        </div>
                                                    </div>
                                                </div>
                                            </li>
                                            <li class="aCol pkn-col">
                                                <div class="col-t">
                                                    <div class="vertical-center-container">
                                                        <div class="center-content">
                                                            <spring:message code="yt.partner.payment.col.packageName"/>
                                                        </div>
                                                    </div>
                                                </div>
                                            </li>
                                            <li class="aCol p-col">
                                                <div class="col-t">
                                                    <div class="vertical-center-container">
                                                        <div class="center-content">
                                                            <spring:message code="yt.partner.payment.col.price"/>
                                                        </div>
                                                    </div>
                                                </div>
                                            </li>
                                            <li class="aCol disc-col">
                                                <div class="col-t">
                                                    <div class="vertical-center-container">
                                                        <div class="center-content">
                                                            <spring:message code="yt.partner.payment.col.disc"/>
                                                        </div>
                                                    </div>
                                                </div>
                                            </li>
                                            <li class="aCol pb-col">
                                                <div class="col-t">
                                                    <div class="vertical-center-container">
                                                        <div class="center-content">
                                                            <spring:message code="yt.partner.payment.col.payBack"/>
                                                        </div>
                                                    </div>
                                                </div>
                                            </li>
                                            <li class="aCol stt-col">
                                                <div class="col-t">
                                                    <div class="vertical-center-container">
                                                        <div class="center-content">
                                                            <spring:message code="yt.partner.payment.col.subTotal"/>
                                                        </div>
                                                    </div>
                                                </div>
                                            </li>
                                            <li class="aCol exR-col">
                                                <div class="col-t">
                                                    <div class="vertical-center-container">
                                                        <div class="center-content">
                                                            <spring:message code="yt.partner.payment.col.exRate"/>
                                                        </div>
                                                    </div>
                                                </div>
                                            </li>
                                            <li class="aCol tt-col">
                                                <div class="col-t">
                                                    <div class="vertical-center-container">
                                                        <div class="center-content">
                                                            <spring:message code="yt.partner.payment.col.exRate"/>
                                                        </div>
                                                    </div>
                                                </div>
                                            </li>
                                            <li class="aCol com-col">
                                                <div class="col-t">
                                                    <div class="vertical-center-container">
                                                        <div class="center-content">
                                                            <spring:message code="yt.partner.payment.col.total"/>
                                                        </div>
                                                    </div>
                                                </div>
                                            </li>
                                            <li class="aCol pable-col">
                                                <div class="col-t">
                                                    <div class="vertical-center-container">
                                                        <div class="center-content">
                                                            <spring:message code="yt.partner.payment.col.payable"/>
                                                        </div>
                                                    </div>
                                                </div>
                                            </li>
                                            <li class="aCol pkgS-col">
                                                <div class="col-t">
                                                    <div class="vertical-center-container">
                                                        <div class="center-content">
                                                            <spring:message code="yt.partner.payment.col.pkgStatus"/>
                                                        </div>
                                                    </div>
                                                </div>
                                            </li>
                                            <li class="aCol paymentW-col">
                                                <div class="col-t">
                                                    <div class="vertical-center-container">
                                                        <div class="center-content">
                                                            <spring:message code="yt.partner.payment.col.paymentWithdraw"/>
                                                        </div>
                                                    </div>
                                                </div>
                                            </li>
                                        </ul>
                                    </div>
                                </div>

                                <div class="row o-row" ng-repeat="p in payment.vars.dt">
                                    <div class="cols">
                                        <ul class="clearfix">
                                            <li class="aCol bo-col">
                                                <div class="col-t">
                                                    <div>
                                                        <span class="link">{{p.bookingCode}}</span>
                                                    </div>
                                                </div>
                                            </li>
                                            <li class="aCol bd-col">
                                                <div class="col-t">
                                                    {{p.bookingDateStr}}
                                                </div>
                                            </li>
                                            <li class="aCol pki-col">
                                                <div class="col-t">
                                                    {{p.packageID}}
                                                </div>
                                            </li>
                                            <li class="aCol pkn-col">
                                                <div class="col-t">
                                                    <div class="vertical-center-container">
                                                        <div class="center-content">
                                                            <div class="center-content">
                                                                {{p.packageName}}
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </li>
                                            <li class="aCol p-col">
                                                <div class="col-t">
                                                    {{p.totalExDc| currency:"":2}}<br/>
                                                    {{p.currencyCode}}
                                                </div>
                                            </li>
                                            <li class="aCol disc-col">
                                                <div class="col-t">
                                                    {{0| currency:"":2}}<br/>
                                                    {{p.currencyCode}}
                                                </div>
                                            </li>
                                            <li class="aCol pb-col">
                                                <div class="col-t">
                                                    <div class="vertical-center-container">
                                                        <div class="center-content">
                                                            {{0| currency:"":2}}<br/>
                                                            {{p.currencyCode}}
                                                        </div>
                                                    </div>
                                                </div>
                                            </li>
                                            <li class="aCol stt-col">
                                                <div class="col-t">
                                                    <div class="vertical-center-container">
                                                        <div class="center-content">
                                                            {{p.total| currency:"":2}}<br/>
                                                            {{p.currencyCode}}

                                                        </div>
                                                    </div>
                                                </div>
                                            </li>
                                            <li class="aCol exR-col">
                                                <div class="col-t">
                                                    <div class="vertical-center-container">
                                                        <div class="center-content">
                                                            {{p.exRate}}
                                                        </div>
                                                    </div>
                                                </div>
                                            </li>
                                            <li class="aCol tt-col">
                                                <div class="col-t">
                                                    <div class="vertical-center-container">
                                                        <div class="center-content">
                                                            {{p.total| currency:"":2}}<br/>
                                                            {{p.currencyCode}}
                                                        </div>
                                                    </div>
                                                </div>
                                            </li>
                                            <li class="aCol com-col">
                                                <div class="col-t">
                                                    <div class="vertical-center-container">
                                                        <div class="center-content">
                                                            {{p.commission| currency:"":2}}<br/>
                                                            {{p.currencyCode}}
                                                        </div>
                                                    </div>
                                                </div>
                                            </li>
                                            <li class="aCol pable-col">
                                                <div class="col-t">
                                                    <div class="vertical-center-container">
                                                        <div class="center-content">
                                                            {{p.partnerTotal| currency:"":2}}<br/>
                                                            {{p.currencyCode}}
                                                        </div>
                                                    </div>
                                                </div>
                                            </li>
                                            <li class="aCol pkgS-col">
                                                <div class="col-t" ng-class="{
                                                        'done':p.pkgDone}">
                                                    <div class="vertical-center-container">
                                                        <div class="center-content">
                                                            <span class="s-w">
                                                                Waiting 
                                                            </span>
                                                            <span class="s-d">
                                                                Done
                                                            </span>
                                                            {{p.doneDate}}
                                                        </div>
                                                    </div>
                                                </div>
                                            </li>
                                            <li class="aCol paymentW-col">
                                                <div class="col-t"
                                                     ng-class="{
                                                             'c-pen' : p.status === payment.vars.DATA.BILL_STATUS.pending,
                                                                     'c-wd' : p.status === payment.vars.DATA.BILL_STATUS.payable,
                                                                     'c-paid' : p.status === payment.vars.DATA.BILL_STATUS.paid,
                                                                     'c-pro' : p.status === payment.vars.DATA.BILL_STATUS.processing,
                                                                     'c-rq' : p.status === payment.vars.DATA.BILL_STATUS.requesting}"
                                                     >
                                                    <div class="vertical-center-container">
                                                        <div class="center-content">
                                                            <div class="pending aCtrl">
                                                                Pending<br/>{{p.pendingDays}} Days
                                                            </div>
                                                            <div class="withdraw aCtrl" 
                                                                 >
                                                                <a href="">Withdraw</a>
                                                            </div>
                                                            <div class="paid aCtrl">
                                                                Paid
                                                            </div>
                                                            <div class="aCtrl requesting">
                                                                Requesting Payment
                                                            </div>
                                                            <div class="processing aCtrl">
                                                                Processing Payment
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                                <div class="center-spinner-block loading" ng-show="payment.vars.lding">
                                    <div class="vertical-center-container">
                                        <div class="center-content">
                                            <span class="yt-spinner center"></span>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="no-rows" ng-if="!payment.vars.dt.length">
                                No result
                            </div>

                            <div class="paging" ng-if="payment.vars.dt.length">
                                <ul>
                                    <li>
                                        <div class="page">
                                            {{payment.vars.paging.pageRange}} <spring:message code="yt.partner.bookinghistory.tbl.upcoming.paging.of.text"/> {{payment.vars.paging.total}}
                                        </div>
                                    </li>
                                    <li>
                                        <div class="ctrls">
                                            <ul class="clearfix">
                                                <li>
                                                    <div class="aCtrl">
                                                        <span ng-class="{
                                                                'dat':payment.vars.filter.currentPage === 1}" 
                                                              class="btn prv" ng-click="payment.funcs.prevU()"></span>
                                                    </div>
                                                </li>
                                                <li>
                                                    <div class="aCtrl">
                                                        <span  ng-class="{
                                                                'dat':payment.vars.filter.currentPage === payment.vars.paging.totalPage}"
                                                               class="btn nxt" ng-click="payment.funcs.nextU()"></span>
                                                    </div>
                                                </li>
                                            </ul>
                                        </div>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <%@include file="../common/header.jsp" %>
        </div>
        <script>

        </script>
    </body>
</html>
