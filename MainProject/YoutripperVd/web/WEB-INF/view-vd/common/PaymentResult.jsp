<%-- 
    Document   : PaymentResult
    Created on : Jul 12, 2018, 4:42:06 PM
    Author     : nickn
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
        <c:choose>
            <c:when test="${requestScope.sc}">
                Success - ${requestScope.on}
            </c:when>
            <c:otherwise>
                Fail - ${requestScope.on}
            </c:otherwise>
        </c:choose>
    </body>
</html>
