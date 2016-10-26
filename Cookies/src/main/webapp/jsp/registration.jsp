<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Registration</title>
    <meta http-equiv="Content-Type" content="text/html; charset=unicode"/>
    <meta content="CoffeeCup HTML Editor (www.coffeecup.com)" name="generator"/>
</head>
<body>
<h1>
    Окно регистрации
</h1>
<script src='js/fields.js'></script>
<form action="registration" method="post" name="Form" onsubmit="return validateForm()">
    Name: <textarea cols="30" rows="2" name="userName" id="a"></textarea>
    Login: <textarea cols="30" rows="2" name="userLogin" id="b"></textarea>
    Password: <textarea cols="30" rows="2" name="password" id="c"></textarea>
    <input type="submit" value="Registration" id="d" onclick="window.location='/login'">
    <span class="error">${error}</span>
</form>
</body>
</html>