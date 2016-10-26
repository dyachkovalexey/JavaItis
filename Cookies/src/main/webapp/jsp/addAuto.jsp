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
<script src="js/fields.js"></script>
<form action="addAuto" method="post" name="Form" onsubmit="return validateForm()">
    Name: <textarea cols="30" rows="2" name="autoName" id="a"></textarea>
    Number: <textarea cols="30" rows="2" name="autoNumber" id="b"></textarea>
    <input type="submit" value="AddAuto">
    <span class="error">${error}</span>
</form>
</body>
</html>