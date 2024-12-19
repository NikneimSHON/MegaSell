<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Header Example</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
        }
        .header {
            background-color: #444;
            color: white;
            padding: 15px;
            display: flex;
            justify-content: center;
            align-items: center;
            position: relative;
        }
        .header img {
            height: 50px;
            margin-right: 10px;
        }
        .header h1 {
            margin: 0;
            font-size: 28px;
            text-align: center;
            color: #e3d2d2;
        }
        .header .logout-btn, .header .cabinet-btn {
            background-color: #28a745;
            color: white;
            border: none;
            padding: 10px 15px;
            border-radius: 5px;
            cursor: pointer;
            margin-left: 10px;
        }
        .header .logout-btn:hover, .header .cabinet-btn:hover {
            background-color: #218838;
        }
        .header .auth-info {
            position: absolute;
            right: 20px;
        }
    </style>
</head>
<body>

<div class="header">
    <a href="${pageContext.request.contextPath}/mainpage" style="display: flex; align-items: center;">
        <img src="images/ded.png" alt="Logo" />
        <h1>MegaSell</h1>
    </a>
    <div class="auth-info">
        <c:if test="${not empty sessionScope.employ}">
            <form action="${pageContext.request.contextPath}/logout" method="post" style="display:inline;">
                <button class="logout-btn" type="submit">Logout</button>
            </form>
            <a href="${pageContext.request.contextPath}/personal_cabinet" class="cabinet-btn">Личный кабинет</a>
        </c:if>

        <c:if test="${empty sessionScope.employ}">
            <a href="${pageContext.request.contextPath}/authorization">Войти</a>
        </c:if>
    </div>
</div>

</body>
</html>