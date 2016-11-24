<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Chat List</title>
</head>
<body>
    <h1>
        Окно чатов
    </h1>
    <p>
        <c:forEach items="${chatList}" var="chatList">
        <tr>
            Chat Name: <td><c:out value="${chatList.chatName}" /></td>
            <input type="button" value="selectChat" onclick="window.location='/' + <c:out value="${chatList.chatId}" /> ">
        </tr>
        </c:forEach>
        <br>
    </p>
</body>
</html>
