<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Авторизация</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-image: url("${pageContext.request.contextPath}/images/фон.webp");
            background-size: cover;
            background-position: center;
            color: white;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
            text-align: center;
            position: relative;
        }
        .overlay {
            position: absolute;
            top: 0;
            left: 0;
            right: 0;
            bottom: 0;
            background-color: rgba(0, 0, 0, 0.5);
        }
        .container {
            position: relative;
            z-index: 1;
            padding: 20px;
            border-radius: 8px;
            background-color: rgba(0, 0, 0, 0.7);
            width: 90%;
            max-width: 400px;
        }
        h1 {
            margin-bottom: 20px;
            font-size: 36px;
            text-transform: uppercase;
            text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.7);
        }
        .form-row {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 15px;
        }
        .form-row label {
            font-size: 18px;
            margin-right: 10px;
            flex: 1;
            text-align: left;
        }
        .form-row input {
            flex: 2;
            padding: 10px;
            border: none;
            border-radius: 5px;
            max-width: 200px;
        }
        button {
            background-color: #FF5733;
            color: white;
            border: none;
            border-radius: 5px;
            padding: 10px 20px;
            font-size: 16px;
            cursor: pointer;
            transition: background-color 0.3s;
        }
        button:hover {
            background-color: #C70039;
        }
        .error-message {
            color: red;
            margin-top: 10px;
        }
        a {
            color: #FF5733;
            text-decoration: none;
            margin-top: 20px;
            display: inline-block;
        }
    </style>
</head>
<body>
<div class="overlay"></div>
<div class="container">
    <h1>Авторизация</h1>
    <form action="${pageContext.request.contextPath}/login" method="post">
        <div class="form-row">
            <label for="number">Номер:</label>
            <input type="text" name="number" id="number" value="${param.number}" required>
        </div>
        <div class="form-row">
            <label for="password">Пароль:</label>
            <input type="text" name="password" id="password" required>
        </div>
        <button type="submit">Войти</button>
        <c:if test="${param.error != null}">
            <div class="error-message">
                <span>Номер или пароль неверны</span>
            </div>
        </c:if>
    </form>
    <a href="${pageContext.request.contextPath}/authorization">Вернуться назад</a>
</div>
</body>
</html>
