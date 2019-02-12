<%-- 
    Document   : PaymentAuthentication
    Created on : Jul 9, 2018, 10:38:51 PM
    Author     : KyLong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="../common/commonhead.jsp" %>
        <title>JSP Page</title>
    </head>
    <body onload="document.getElementById('paymentForm').submit();" style="display: none">
        <form id="paymentForm" action='<spring:message code="yt.payment.2c2p.authlink"/>' method='POST' name='paymentRequestForm'>
            Processing payment request, Do not close the browser, press back or refresh the page.
            <input type='text' name="paymentRequest" value="${requestScope.paymentrequest}">
            <input type="submit" value="Submit">
        </form>
    </body>
</html>
