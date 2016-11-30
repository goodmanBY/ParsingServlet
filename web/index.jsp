<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>XML Parser</title>
</head>
<body>

<form action="${pageContext.request.contextPath}/parse" method="post"  enctype="multipart/form-data">
    <label>
        DOM
        <input type="radio" name="parserType" value="dom" checked="checked"/>
    </label>
    <label>
        SAX
        <input type="radio" name="parserType" value="sax"/>
    </label>
    <label>
        StAX
        <input type="radio" name="parserType" value="stax"/>
    </label>
    <input type="file" name="file" />
    <input type="submit" value="Submit"/>
    <c:out value="${name}"/>
</form>

<c:if test="${not empty type}">
    <p>Parser - ${type}</p>
    <table border="2">
        <tr>
            <th>Deposit ID</th>
            <th>Bank name</th>
            <th>Country of registration</th>
            <th>Deposit type</th>
            <th>Depositor</th>
            <th>Account ID</th>
            <th>Amount, $</th>
            <th>Profitability, %</th>
            <th>Time Constraints, months</th>
        </tr>

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
