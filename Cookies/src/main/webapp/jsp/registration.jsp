<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<h1>
    Окно регистрации
</h1>
<form action="registration" method="post">
    Name: <input type="text" name="userName"> <br>
    login: <input type="text" name="userLogin"> <br>
    password: <input type="password" name="password"> <br>
    <input type="submit" value="Registration">
    <span class="error">${error}</span>
</form>
</body>
</html>