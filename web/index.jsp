<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="com.savko.i18n.text"/>
<html lang="${language}">
<head>
    <title>XML Parser</title>
</head>
<body>

<form>
    <label for="language">
        <fmt:message key="language"/>
    </label>
    <select id="language" name="language" onchange="submit()">
        <option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
        <option value="de" ${language == 'de' ? 'selected' : ''}>Dutch</option>
        <option value="es" ${language == 'es' ? 'selected' : ''}>Spain</option>
    </select>
</form>

<form action="${pageContext.request.contextPath}/parse" method="post" enctype="multipart/form-data">
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
    <br>
    <input type="file" name="file"/>
    <br>
    <input type="submit" value="Submit"/>
</form>


</body>
</html>
