<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Выберите тип входа</title>
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
        }
        h1 {
            margin-bottom: 20px;
            font-size: 48px;
            text-transform: uppercase;
            text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.7);
        }
        button {
            background-color: #FF5733;
            color: white;
            border: none;
            border-radius: 5px;
            padding: 10px 20px;
            font-size: 16px;
            cursor: pointer;
            margin: 10px;
            transition: background-color 0.3s;
        }
        button:hover {
            background-color: #C70039;
        }
        .button-container {
            display: flex;
            justify-content: center;
        }
    </style>
</head>
<body>
<div class="overlay"></div>
<div class="container">
    <h1>Anime Store</h1>
    <div class="button-container">
        <form action="/authorization" method="post">
            <button name="action" value="login" type="submit">Вход</button>
            <button name="action" value="registration" type="submit">Регистрация</button>
        </form>
    </div>
</div>
</body>
</html>