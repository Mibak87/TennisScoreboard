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
  <p>${matchScore.finish}</p>
  <table class="table-center">
    <tr>
      <td>Имя</td>
      <td>Очки</td>
      <td>Гейм</td>
      <td>Сет</td>
    </tr>
    <tr>
      <td>${matchScore.player1Name}</td>
      <td>${matchScore.player1Score}</td>
      <td>${matchScore.player1Game}</td>
      <td>${matchScore.player1Set}</td>
      <td>
        <form name="MatchScore" action="match-score" method="post">
          <input type="hidden" name="player-id" value="1">
          <button type="submit" ${matchScore.buttonDisabled}>${matchScore.player1Name} выиграл</button>
        </form>
      </td>
    </tr>
    <tr>
      <td>${matchScore.player2Name}</td>
      <td>${matchScore.player2Score}</td>
      <td>${matchScore.player2Game}</td>
      <td>${matchScore.player2Set}</td>
      <td>
        <form name="MatchScore" action="match-score" method="post">
          <input type="hidden" name="player-id" value="2">
          <button type="submit" ${matchScore.buttonDisabled}>${matchScore.player2Name} выиграл</button>
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