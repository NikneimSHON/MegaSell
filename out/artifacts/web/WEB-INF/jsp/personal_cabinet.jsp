<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Личный кабинет</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
        }

        .container {
            width: 80%;
            margin: 50px auto;
            padding: 20px;
            background-color: white;
            box-shadow: 0 2px 8px rgba(0, 0, 0, 0.2);
            border-radius: 8px;
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
        }

        .field input[disabled] {
            background-color: #e9ecef;
            color: #6c757d;
        }

        .button-wrapper {
            text-align: center;
            margin-top: 20px;
        }

        .button-wrapper button {
            padding: 10px 20px;
            font-size: 16px;
            color: white;
            background-color: #28a745;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        .button-wrapper button:hover {
            background-color: #218838;
        }
    </style>
</head>
<body>

<div class="container">
    <h1>Личный кабинет</h1>

    <form action="/personal_cabinet" method="post">
        <div class="field">
            <label for="Имя">Имя:</label>
            <input type="text" id="Имя" name="Имя" value="Имя" required>
        </div>
        <div class="field">
            <label for="Фамилия">Фамилия:</label>
            <input type="text" id="Фамилия" name="Фамилия" value="Фамилия" required>
        </div>

        <div class="field">
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" value="mail" required>
        </div>

        <div class="field">
            <label for="phone">Номер телефона:</label>
            <input type="text" id="phone" name="phone" value="phone" required>
        </div>

        <div class="field">
            <label for="birthday">День рождения:</label>
            <input type="text" id="birthday" name="birthday" value="birthday" required>
        </div>

        <div class="button-wrapper">
            <button type="submit">Сохранить</button>
        </div>
    </form>
</div>

</body>
</html>