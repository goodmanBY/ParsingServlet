<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isErrorPage="true" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="com.savko.i18n.text"/>
<html>
<head>
    <title><fmt:message key="error"/></title>
</head>
<body>
<h1>Oops...</h1>
<p><fmt:message key="error.text"/></p>
<table width="100%" border="1">
    <tr>
        <td width="40%"><b><fmt:message key="error"/>:</b></td>
        <td>${pageContext.exception}</td>
    </tr>
    <tr>
        <td><b><fmt:message key="uri"/>:</b></td>
        <td>${pageContext.errorData.requestURI}</td>
    </tr>
    <tr>
        <td><b><fmt:message key="status.code"/>:</b></td>
        <td>${pageContext.errorData.statusCode}</td>
    </tr>
    <tr>
        <td><b><fmt:message key="stack.trace"/>:</b></td>
        <td>
            <c:forEach var="trace"
                       items="${pageContext.exception.stackTrace}">
                <p>${trace}</p>
            </c:forEach>
        </td>
    </tr>
</table>
</body>
</html>
