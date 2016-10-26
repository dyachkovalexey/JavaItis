<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Login</title>
    <meta http-equiv="Content-Type" content="text/html; charset=unicode"/>
    <meta content="CoffeeCup HTML Editor (www.coffeecup.com)" name="generator"/>
</head>
<body>
<h1>
    Авторизация:
    <%
        response.addCookie(new Cookie("user-name", "user"));
        response.addCookie(new Cookie("user-password", "user"));
    %>
</h1>
<script src='js/fields.js'></script>
<form action="login" method="post" name="Form" onsubmit="return validateForm()">
    Login: <textarea cols="30" rows="2" name="userLogin" id="b"></textarea>
    password: <textarea cols="30" rows="2" name="password" id="c"></textarea>
    <input type="submit" value="Check" onclick="window.location='/list'">
    <span class="error">${error}</span>
</form>
</body>
</html>