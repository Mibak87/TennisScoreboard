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
      <td>Сет1</td>
      <td>Сет2</td>
      <td>Сет3</td>
    </tr>
    <tr>
      <td>${matchScore.player1Name}</td>
      <td>${matchScore.player1Points}</td>
      <td>${matchScore.player1Set.get(0)}</td>
      <td>${matchScore.player1Set.get(1)}</td>
      <td>${matchScore.player1Set.get(2)}</td>
      <td>
        <form name="MatchScore" action="match-score?uuid=${matchScore.uuid}" method="post">
          <input type="hidden" name="player-id" value="1">
          <button type="submit" ${matchScore.buttonDisabled}>${matchScore.player1Name} выиграл</button>
        </form>
      </td>
    </tr>
    <tr>
      <td>${matchScore.player2Name}</td>
      <td>${matchScore.player2Points}</td>
      <td>${matchScore.player2Set.get(0)}</td>
      <td>${matchScore.player2Set.get(1)}</td>
      <td>${matchScore.player2Set.get(2)}</td>
      <td>
        <form name="MatchScore" action="match-score?uuid=${matchScore.uuid}" method="post">
          <input type="hidden" name="player-id" value="2">
          <button type="submit" ${matchScore.buttonDisabled}>${matchScore.player2Name} выиграл</button>
        </form>
      </td>
    </tr>
  </table>
  <div id="player1Name">
    ${matchScore.player1Name}
  </div>
  <p>
    <a href="matches" class="href-button">Завершенные матчи</a>
  </p>
</div>
</body>
</html>