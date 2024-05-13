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
        <tr>
            <td>${matches.player1Name.get(0)}</td>
            <td>${matches.player2Name.get(0)}</td>
        </tr>
        <tr>
            <td>${matches.player1Name.get(1)}</td>
            <td>${matches.player2Name.get(1)}</td>
        </tr>
        <tr>
            <td>${matches.player1Name.get(2)}</td>
            <td>${matches.player2Name.get(2)}</td>
        </tr>
        <tr>
            <td>${matches.player1Name.get(3)}</td>
            <td>${matches.player2Name.get(3)}</td>
        </tr>
        <tr>
            <td>${matches.player1Name.get(4)}</td>
            <td>${matches.player2Name.get(4)}</td>
        </tr>
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