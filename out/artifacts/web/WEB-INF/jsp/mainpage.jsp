<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Главная страница</title>
    <style>
        body {
            font-family: sans-serif;
            margin: 0;
            padding: 0;
            display: flex;
            flex-direction: column;
            min-height: 100vh;
        }
        .search-container {
            padding: 20px; /* Уменьшил отступ для высоты */
            text-align: center;
            background-color: #f9f9f9;
            border-radius: 8px;
            box-shadow: 0 4px 10px rgba(0,0,0,0.1);
            max-width: 1400px; /* Установил максимальную ширину контейнера, если это нужно */
            margin: 20px auto; /* Отцентрировал и добавил отступ сверху/снизу */
        }

        .search-container input[type="text"] {
            padding: 12px;
            border: 2px solid #ddd;
            border-radius: 6px;
            font-size: 16px;
            transition: border-color 0.3s ease;
            margin-right: 10px;
            width: 500px; /* Увеличил ширину поля ввода */
        }

        .search-container input[type="text"]:focus {
            border-color: #4CAF50;
            outline: none;
        }

        .search-container button {
            padding: 12px 20px;
            background: linear-gradient(to right, #4CAF50, #388E3C);
            color: white;
            border: none;
            border-radius: 6px;
            cursor: pointer;
            font-size: 16px;
            transition: background 0.3s ease, transform 0.2s ease;
            box-shadow: 0 2px 5px rgba(0,0,0,0.2);
        }

        .search-container button:hover {
            background: linear-gradient(to right, #388E3C, #4CAF50);
            transform: scale(1.05);
        }

        .search-container button:active {
            transform: scale(1);
            box-shadow: 0 1px 3px rgba(0,0,0,0.2);
        }

        .main-content {
            display: flex;
            flex: 1; /* Растягивается, занимая оставшееся пространство */
            padding: 20px;

        }
        .filter-sidebar {
            width: 200px;
            padding: 10px;
            border-right: 1px solid #ccc;

        }
        .product-container {
            flex: 1; /* Занимает доступное пространство в центре */
            display: flex;
            flex-wrap: wrap;
            justify-content: center;
            padding: 20px;

        }

        .product-card {
            width: 200px;
            border: 1px solid #ddd;
            margin: 10px;
            padding: 10px;
            text-align: center;
            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
            display: flex;
            flex-direction: column;
        }
        .product-card img {
            max-width: 100%;
            height: 150px; /*или любое нужное вам значение*/
            object-fit: contain; /*сохраняет пропорции*/
            margin-bottom: 10px;
        }

        .product-card button {
            background-color: #007bff;
            color: white;
            border: none;
            padding: 8px 12px;
            border-radius: 4px;
            cursor: pointer;
            margin-top: auto; /* Переносит кнопку вниз */

        }
        .pagination {
            text-align: center;
            margin-top: 20px;
            padding-bottom: 20px;
        }
        .pagination button{
            padding: 8px 12px;
            margin: 0 5px;
            border: 1px solid #ccc;
            cursor: pointer;
        }
        .right-margin {
            margin-right: 20px;
        }
    </style>
</head>
<body>

<%@ include file="header.jsp" %>

<div class="search-container">
    <form action="/search" method="post">
        <input type="text" name="query" placeholder="Поиск товаров...">
        <button type="submit">Поиск</button>
    </form>
</div>

<div class="main-content">
    <div class="filter-sidebar">
        <h2>Фильтр</h2>
        <p>Здесь будут ваши фильтры</p>
    </div>
    <div class="product-container right-margin">

        <% for (int i = 0; i < 20; i++) { %>
        <div class="product-card">
            <img src="https://placehold.co/200x150" alt="Product <%= i + 1 %>">
            <h3>Товар <%= i + 1 %></h3>
            <button>В корзину</button>
        </div>
        <%}%>

    </div>
</div>


"${currentPage - 2 > 0 ? currentPage - 2 : 1}"
<c:set var="startPage" value="${currentPage - 2 > 0 ? currentPage - 2 : 1}" scope="request" />
<c:set var="endPage" value="${currentPage + 2 < totalPages ? currentPage + 2 : totalPages}" scope="request" />

<div class="pagination">
    <%-- Кнопка "Предыдущая" --%>
    <c:if test="${currentPage > 1}">
        <a href="?page=${currentPage - 1}">Предыдущая</a>
    </c:if>

    <%-- Кнопки страниц --%>
    <c:forEach var="i" begin="${startPage}" end="${endPage}">
        <c:choose>
            <c:when test="${i == currentPage}">
                <button class="active">${i}</button>
            </c:when>
            <c:otherwise>
                <a href="?page=${i}"><button>${i}</button></a>
            </c:otherwise>
        </c:choose>
    </c:forEach>

    <%-- Кнопка "Следующая" --%>
    <c:if test="${currentPage < totalPages}">
        <a href="?page=${currentPage + 1}">Следующая</a>
    </c:if>
</div>
<%@include file="footer.jsp" %>

</body>
</html>