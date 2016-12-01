<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="com.savko.i18n.text"/>
<html>
<head>
    <title>Title</title>
</head>
<body>

<c:if test="${not empty type}">
    <p><fmt:message key="parser"/> - ${type}</p>
</c:if>

<table border="2">
    <tr>
        <th><fmt:message key="deposit.depositId"/></th>
        <th><fmt:message key="deposit.bankName"/></th>
        <th><fmt:message key="deposit.country"/></th>
        <th><fmt:message key="deposit.depositType"/></th>
        <th><fmt:message key="deposit.depositor"/></th>
        <th><fmt:message key="deposit.accountId"/></th>
        <th><fmt:message key="deposit.amount"/>, $</th>
        <th><fmt:message key="deposit.profitability"/>, %</th>
        <th><fmt:message key="deposit.timeConstraints"/>, months</th>
    </tr>
    <c:if test="${not empty type}">
    <c:forEach items="${bank.deposits}" var="deposit">
        <tr>
            <th>${deposit.depositId}</th>
            <td>${deposit.bankName}</td>
            <td>${deposit.country}</td>
            <td>${deposit.depositType}</td>
            <td>${deposit.depositor}</td>
            <td>${deposit.accountId}</td>
            <td>${deposit.amount}</td>
            <td>${deposit.profitability}</td>
            <td>${deposit.timeConstraints}</td>
        </tr>
    </c:forEach>
</table>
</c:if>

</body>
</html>
