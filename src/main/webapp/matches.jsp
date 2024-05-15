<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Завершенные матчи</title>
    <link rel="stylesheet" href="css/styles.css" type="text/css">
</head>
<body>

<div id="wrapper">
    <h1><%= "Завершенные матчи" %></h1>
    <p>
        <a href="new-match" class="href-button">Новый матч</a>
    </p>
    <p>
    <form name="NewMatch" action="matches" method="get">
        <input type="text" name="filter_by_player_name" placeholder="Имя игрока">
        <button type="submit">Поиск</button>
    </form>
    </p>
    <table class="table-center">
        <tr>
            <td>Игрок 1</td>
            <td>Игрок 2</td>
        </tr>
        <c:forEach var="playerName" items="${matches.matchesList}">
            <c:set var="player1" value="${playerName.getPlayer1().getName()}"/>
            <c:set var="player2" value="${playerName.getPlayer2().getName()}"/>
            <c:set var="winner" value="${playerName.getWinner().getName()}"/>
            <c:if test="${player1 == winner}">
                <c:set var="player1" value="${player1}🏆"/>
            </c:if>
            <c:if test="${player2 == winner}">
                <c:set var="player2" value="${player2}🏆"/>
            </c:if>
        <tr>
            <td>${player1}</td>
            <td>${player2}</td>
        </tr>
        </c:forEach>
    </table>
    <p>
    <form action="matches">
        <input type="hidden" name="page" value=${matches.pageNumberLast}>
        <button type="submit" class="page_button" ${matches.button1Hidden}>Предыдущая страница</button>
    </form>
    <form action="matches">
        <input type="hidden" name="page" value=${matches.pageNumberNext}>
        <button type="submit" class="page_button" ${matches.button2Hidden}>Следующая страница</button>
    </form>
    </p>
</div>
</body>
</html>