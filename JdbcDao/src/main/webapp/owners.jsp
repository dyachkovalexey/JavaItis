<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Hello, World!</title>
</head>
<body>
    <h1>
        <c:forEach items="${requestScope.owners}" var="currentOwner">
            <tr>
                <td><c:out value="${currentOwner}" /><td>
                <!--<td><c:out value="${currentOwner}" /><td>-->
            </tr>
        </c:forEach>
    </h1>
</body>
</html>
