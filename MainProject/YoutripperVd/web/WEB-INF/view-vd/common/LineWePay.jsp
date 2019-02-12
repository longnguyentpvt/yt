<%-- 
    Document   : LineWePay
    Created on : Aug 7, 2018, 10:29:17 AM
    Author     : IDD_LENOVO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
<!--        <a href="https://access.line.me/oauth2/v2.1/authorize?response_type=code&client_id=1599264332&redirect_uri=http://localhost:8084/lp-login&state=12345abcde&scope=openid%20profile%20email">
            <input type="button" value="line login">
        </a>-->
        <a href="https://access.line.me/oauth2/v2.1/authorize?response_type=code&client_id=1599264332&redirect_uri=http://www.localhost:8084/lp-login&state=12345abcde&scope=openid%20profile%20email">
            <input type="button" value="line login">
        </a>
    </body>
</html>
