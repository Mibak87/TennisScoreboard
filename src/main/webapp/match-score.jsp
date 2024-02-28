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
  <table>
    <tr>
      <td>Имя</td>
      <td>${player1}</td>
      <td>${player2}</td>
    </tr>
    <tr>
      <td>Счет</td>
      <td>${score1}</td>
      <td>${score2}</td>
    </tr>
  </table>
  <p>
  <form name="MatchScore" action="match-score" method="post">
    <input type="hidden" name="player1" value="player1" disabled>
    <button type="submit">Игрок1 выиграл</button>
  </form>
  <form name="MatchScore" action="match-score" method="post">
    <input type="hidden" name="player2" value="player2" disabled>
    <button type="submit">Игрок2 выиграл</button>
  </form>
  </p>
</div>
</body>
</html>