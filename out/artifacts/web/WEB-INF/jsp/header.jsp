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
            background-color: #333;
            color: white;
            padding: 15px;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }
        .header img {
            height: 50px;
            margin-right: 20px;
        }
        .header h1 {
            margin: 0;
            font-size: 24px;
        }
        .header .logout-btn, .header .cabinet-btn {
            background-color: #008CBA;
            color: white;
            border: none;
            padding: 10px 15px;
            border-radius: 5px;
            cursor: pointer;
        }
        .header .logout-btn:hover, .header .cabinet-btn:hover {
            background-color: #005f73;
        }
    </style>
</head>
<body>

<div class="header">
    <img src="images/ded.png" alt="Logo" />
    <h1>MegaSell</h1>
    <div>
        <c:if test="${not empty sessionScope.employ}">
            <form action="${pageContext.request.contextPath}/logout" method="post" style="display:inline;">
                <button class="logout-btn" type="submit">Logout</button>
            </form>
            <a href="${pageContext.request.contextPath}/personal_cabinet" class="cabinet-btn">Личный кабинет</a>
        </c:if>

        <c:if test="${empty sessionScope.employ}">
            <p style="color: white;">Пользователь не авторизован.</p>
        </c:if>
    </div>
</div>

</body>
</html>