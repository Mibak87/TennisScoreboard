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
    <table>
        <tr>
            <td>Игрок 1</td>
            <td>Игрок 2</td>
            <td>Победитель</td>
        </tr>
        <tr>
            <td>${player1_0}</td>
            <td>${player2_0}</td>
            <td>${winner_0}</td>
        </tr>
        <tr>
            <td>${player1_1}</td>
            <td>${player2_1}</td>
            <td>${winner_1}</td>
        </tr>
        <tr>
            <td>${player1_2}</td>
            <td>${player2_2}</td>
            <td>${winner_2}</td>
        </tr>
        <tr>
            <td>${player1_3}</td>
            <td>${player2_3}</td>
            <td>${winner_3}</td>
        </tr>
        <tr>
            <td>${player1_4}</td>
            <td>${player2_4}</td>
            <td>${winner_4}</td>
        </tr>
    </table>
</div>
</body>
</html>