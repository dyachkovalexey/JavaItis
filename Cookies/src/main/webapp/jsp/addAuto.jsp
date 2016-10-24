<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>addAuto</title>
</head>
<body>
<h1>
    Добавление автомобиля
</h1>
<form action="addAuto" method="post">
    Name: <input type="name" name="autoName"> <br>
    Number: <input type="text" name="autoNumber"> <br>
    <input type="submit" value="Add">
    <span class="error">${error}</span>
</form>
</body>
</html>