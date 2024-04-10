<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html lang="ru">
<head>
  <meta http-equiv="Content-type" content="text/html; charset=utf-8">
    <title>Счет матча</title>
  <link rel="stylesheet" href="css/styles.css" type="text/css">
</head>
<body>
<div id="wrapper">
  <p>Счет матча</p>
  <p>${finish}</p>
  <table class="table-center">
    <tr>
      <td>Имя</td>
      <td>Очки</td>
      <td>Гейм</td>
      <td>Сет</td>
    </tr>
    <tr>
      <td>${player1name}</td>
      <td>${score1}</td>
      <td>${game1}</td>
      <td>${set1}</td>
      <td>
        <form name="MatchScore" action="match-score" method="post">
          <input type="hidden" name="player-id" value="1">
          <button type="submit" ${button_disabled}>${player1name} выиграл</button>
        </form>
      </td>
    </tr>
    <tr>
      <td>${player2name}</td>
      <td>${score2}</td>
      <td>${game2}</td>
      <td>${set2}</td>
      <td>
        <form name="MatchScore" action="match-score" method="post">
          <input type="hidden" name="player-id" value="2">
          <button type="submit" ${button_disabled}>${player2name} выиграл</button>
        </form>
      </td>
    </tr>
  </table>
  <p>
    <a href="matches" class="href-button">Завершенные матчи</a>
  </p>
</div>
</body>
</html>