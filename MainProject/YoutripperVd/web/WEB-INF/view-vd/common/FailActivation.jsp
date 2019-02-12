<%-- 
    Document   : FailActivation
    Created on : Jan 17, 2018, 12:14:34 PM
    Author     : nickn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="${pageContext.response.locale}" currency="${sessionScope.ytCurrency}" ng-app="youtripper" ng-controller="YoutripperController">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="commonhead.jsp" %>
        <title>Youtripper - Invalid Activation</title>
    </head>
    <body>
        <div class="yt-smallest-container">
            <div class="txt med-txt" style="text-align: center; padding: 200px 0">
                Your account is already activated or activation token is invalid!
            </div>
        </div>
    </body>
</html>
