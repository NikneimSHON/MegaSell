<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Личный кабинет</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
            display: flex;
            flex-direction: column;
        }

        .main-content {
            display: flex;
            flex: 1;
            height: 100vh;
            flex-direction: row;
        }


        .sidebar {
            width: 200px;
            background-color: #343a40;
            padding: 20px;
            color: white;
            display: flex;
            flex-direction: column;
            box-shadow: 2px 0px 5px rgba(0, 0, 0, 0.2);
            height: 820px;
        }

        .sidebar a {
            display: block;
            padding: 10px;
            color: white;
            text-decoration: none;
            border-bottom: 1px solid #6c757d;
            transition: background-color 0.3s;
        }

        .sidebar a:hover {
            background-color: #495057;
        }

        .container {
            flex: 1;
            max-width: 1400px;
            max-height: 1000px;
            padding: 20px;
            background-color: white;
            box-shadow: 0 2px 8px rgba(0, 0, 0, 0.2);
            border-radius: 8px;
            height: 820px;
        }


        h1 {
            text-align: center;
            color: #333;
        }

        .field {
            margin-bottom: 20px;
        }

        .field label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
        }

        .field input {
            width: 100%;
            padding: 10px;
            font-size: 14px;
            border: 1px solid #ccc;
            border-radius: 4px;
            background-color: #f0f0f0;
            color: #000000;
        }

        .field input[readonly] {
            background-color: #e9ecef;
        }

        .button-wrapper {
            text-align: center;
            margin-top: 20px;
        }

        .button-wrapper button {
            padding: 10px 20px;
            font-size: 16px;
            color: #af9999;
            background-color: #28a745;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        .button-wrapper button:hover {
            background-color: #218838;
        }

        .error-message {
            color: red;
            margin-top: 10px;
        }

        .header-wrapper {
            width: 100%;
        }


        .main-content {
            flex: 1;
        }
    </style>

</head>
<body>
<div class="header-wrapper">
    <%@ include file="header.jsp" %>
</div>
<div class="main-content">
    <div class="sidebar">
        <a href="/personal_cabinet">Личный кабинет</a>
        <a href="/my_orders">Мои заказы</a>
        <a href="/addresses">Мои адреса</a>
        <c:if test = "${sessionScope.employ.accessPermissionId == 2}" >
            <a href = "/adminpanel">Админ панель</a>
            <a href = "/createProduct">Создание нового товара</a>
        </c:if>
    </div>
    <div class="container">
        <h1>Личный кабинет</h1>
        <form action="/personal_cabinet" method="post">
            <div class="field">
                <label for="firstName">Имя:</label>
                <input type="text" id="firstName" name="firstName" value="${sessionScope.employ.firstName}"
                       required ${sessionScope.editMode ? '' : 'readonly'}>
            </div>
            <div class="field">
                <label for="lastName">Фамилия:</label>
                <input type="text" id="lastName" name="lastName" value="${sessionScope.employ.lastName}"
                       required ${sessionScope.editMode ? '' : 'readonly'}>
            </div>
            <div class="field">
                <label for="email">Email:</label>
                <input type="email" id="email" name="email" value="${sessionScope.employ.email}"
                <c:if test="${not empty sessionScope.employ.email}">
                       required readonly
                </c:if>

            </div>
            <div class="field">
                <label for="number">Номер телефона:</label>
                <input type="text" id="number" name="number" value="${sessionScope.employ.number}" required readonly>
            </div>
            <div class="field">
                <label for="birthDate">День рождения:</label>
                <input type="text" id="birthDate" name="birthDate" value="${sessionScope.employ.birthDate}" required
                       readonly>
            </div>
            <div class="button-wrapper">
                <button type="submit" name="action" value="save">Сохранить</button>
                <button type="submit" name="action" value="edit">Редактировать</button>
            </div>
            <c:if test="${not empty requestScope.errors}">
                <div class="error-message">
                    <c:forEach var="error" items="${requestScope.errors}">
                        <span>${error.message}</span>
                        <br>
                    </c:forEach>
                </div>
            </c:if>
        </form>
    </div>
</div>
</body>
</html>