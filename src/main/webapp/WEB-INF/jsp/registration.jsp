<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Регистрация</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-image: url("${pageContext.request.contextPath}/images/ded.png");
            background-size: cover;
            background-position: center;
            color: white;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
            text-align: center;
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
            text-align: center;
            margin-bottom: 20px;
            font-size: 24px;
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
            font-size: 16px;
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
            width: 100%;
            transition: background-color 0.3s;
        }
        button:hover {
            background-color: #C70039;
        }
        a {
            display: block;
            text-align: center;
            margin-top: 20px;
            color: #FF5733;
            text-decoration: none;
        }
        a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
<div class="overlay"></div>
<div class="container">
    <h1>Регистрация</h1>
    <form action="/registration" method="post">
        <div class="form-row">
            <label for="number">Номер:</label>
            <input type="text" name="number" id="number" required>
        </div>
        <div class="form-row">
            <label for="password">Пароль:</label>
            <input type="text" name="password" id="password" required>
        </div>
        <div class="form-row">
            <label for="repeat_password">Повтор пароля:</label>
            <input type="text" name="repeat_password" id="repeat_password" required>
        </div>
        <div class="form-row">
            <label for="birth_date">Дата рождения:</label>
            <input type="date" name="birth_date" id="birth_date" required>
        </div>
        <button type="submit">Зарегистрироваться</button>
        <c:if test="${not empty requestScope.errors}">
            <div style="color: red">
                <c:forEach var="error" items="${requestScope.errors}">
                    <span>${error.message}</span>
                    <br>
                </c:forEach>
            </div>
        </c:if>
    </form>
    <a href="${pageContext.request.contextPath}/authorization">Вернуться назад</a>
</div>
</body>
</html>