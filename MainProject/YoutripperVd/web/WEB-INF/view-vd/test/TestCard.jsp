<%-- 
    Document   : TestCard
    Created on : Jul 9, 2018, 9:55:15 PM
    Author     : KyLong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="${pageContext.response.locale}" currency="${sessionScope.ytCurrency}" ng-app="youtripper" ng-controller="YoutripperController">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="../common/commonhead.jsp" %>
        <title>JSP Page</title>
    </head>
    <body>
        <form id="2c2p-payment-form" action="/ytpayment" method="POST" style="width: 200px">
            <input type="text" class="yt-input" data-encrypt="cardnumber" name="cardnumber" placeholder="Card Number"/>
            <input type="text" class="yt-input" data-encrypt="month" name="month" placeholder="Month"/>
            <input type="text" class="yt-input" data-encrypt="year" name="year" placeholder="Year"/>
            <input type="text" class="yt-input" placeholder="cvv" 
                   autocomplete="off" data-encrypt="cvv" name="cvv"/>
            <input type="text" class="yt-input" placeholder="Total" name="total"/>
            <input type="submit" value="Pay"/>
        </form>
    </body>
    <script type="text/javascript" src="<spring:message code="yt.payment.2c2p.jslink"/>"></script>
    <script>
            $(function () {
                My2c2p.onSubmitForm("2c2p-payment-form", function (errCode, errDesc) {
//                    var errorCode = null;
//                    if (errCode === 1 || errCode === 2) {
//                        errorCode = errors.INVALID_NUMBER;
//                    } else if (errCode === 3 || errCode === 4
//                            || errCode === 5 || errCode === 6 || errCode === 9) {
//                        errorCode = errors.INVALID_EXPIRATATION;
//                    } else if (errCode === 7 || errCode === 8) {
//                        errorCode = errors.EXPIRED;
//                    } else if (errCode === 10) {
//                        errorCode = errors.INVALID_CVV;
//                    }
                    alert(errDesc);
                });
            });
    </script>
</html>
