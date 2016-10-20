<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Hello, World!</title>
</head>
<body>
    <h1>
        Cписок владельцев:
    </h1>
    <p>
        <c:forEach items="${requestScope.CarOwners}" var="currentOwner">
            <tr>
                <td><c:out value="${currentOwner}" /><td>
                <br>
            </tr>
        </c:forEach>
    </p>
    <form action="owners" method="post">
        ID: <input type="id" name ="ownerID">
        Name: <input type="text" name="ownerName">
        Age: <input type="age" name="ownerAge">
        City: <input type="city" name="ownerCity">
        <input type="submit" value="Submit">
        <span class="error">${error}</span>
    </form>
</body>
</html>
