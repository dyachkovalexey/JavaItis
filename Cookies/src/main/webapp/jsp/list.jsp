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
    <c:forEach items="${usersList}" var="currentUsers">
        <tr>
            ID: <td><c:out value="${currentUsers.userId}" /></td>
            Name: <td><c:out value="${currentUsers.userName}" /></td>
            <c:forEach items="${autosList}" var="currentAutos">
                <c:if test="${currentUsers.userId == currentAutos.userId}">
                    Mark: <td><c:out value="${currentAutos.autoName}" /></td>
                    Number: <td><c:out value="${currentAutos.autoNumber}" /></td>
                    </tr>
                </c:if>
            </c:forEach>
            <br>
    </c:forEach>
</p>
<form>
    <input type="button" value="addAuto" onclick="window.location='/addAuto'">
</form>
</body>
</html>