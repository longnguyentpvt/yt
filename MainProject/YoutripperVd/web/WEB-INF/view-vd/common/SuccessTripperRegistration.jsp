<%-- 
    Document   : SuccessPartnerRegistraiton
    Created on : Jan 15, 2018, 3:56:21 PM
    Author     : nickn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="${pageContext.response.locale}" currency="${sessionScope.ytCurrency}" ng-app="youtripper" ng-controller="YoutripperController">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="commonhead.jsp" %>
        <title>Youtripper - Tripper Registration Successfully</title>
        <style>
            #activation-success {
                text-align: center;
            }

            #resend {
                text-align: center;
                padding: 20px 0;
            }

            #resend .yt-btn {
                margin-top: 10px;
            }
        </style>
    </head>
    <body>
        <div class="yt-small-container">
            <div id="activation-success">
                <img src="${pageScope.imageURL}mail.gif" alt="Tripper Registration Mail"/>
                <h1 class="biggest-txt">
                    Thank You!
                </h1>
                <p class="med-txt">
                    <spring:message code="yt.partner.register.success.thankyou"/>
                </p>
            </div>
            <c:if test="${sessionScope.resend != null}">
                <div id="resend">
                    <p>
                        <spring:message code="yt.partner.register.resend.txt"/>
                    </p>
                    <form method="POST" action="/tripper-registration/success/resend" 
                          class="form-horizontal" name="signupForm" id="resend-form"
                          autocomplete="off">
                        <button type="submit" class="yt-btn center-btn" style="width: 200px">
                            <spring:message code="yt.partner.register.resend.btn"/>
                        </button>     
                    </form>
                </div>
            </c:if>
        </div>
    </body>
</html>
