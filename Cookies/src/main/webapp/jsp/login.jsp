<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<h1>
    Авторизация:
    <%
        response.addCookie(new Cookie("user-name", "user"));
        response.addCookie(new Cookie("user-password", "user"));
    %>
</h1>
<form action="login" method="post">
    Login: <input type="login" name="userLogin">
    password: <input type="password" name="password">
    <input type="submit" value="Check">
    <span class="error">${error}</span>
</form>
</body>
</html>