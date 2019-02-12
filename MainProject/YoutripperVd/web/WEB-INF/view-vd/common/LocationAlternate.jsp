<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:forEach var="aalt" items="${requestScope.alternates}"><link rel="alternate"  hreflang="${aalt[0]}" href="${aalt[1]}" />
</c:forEach>