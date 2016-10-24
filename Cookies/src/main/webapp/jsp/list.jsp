<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>List</title>
</head>
<body>
<h1>
    Cписок владельцев авто:
</h1>
<p>
    <c:forEach items="${requestScope.Users}" var="currentUsers">
        <tr>
            <td><c:out value="${currentUsers}" /><td>
            <br>
        </tr>
    </c:forEach>
    <c:forEach items="${requestScope.Autos}" var="currentAutos">
        <tr>
            <td><c:out value="${currentAutos}" /><td>
            <br>
        </tr>
    </c:forEach>
</p>
</body>
</html>