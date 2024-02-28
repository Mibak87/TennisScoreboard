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
      <td>${player1name}</td>
      <td>${player2name}</td>
    </tr>
    <tr>
      <td>Счет</td>
      <td>${score1}</td>
      <td>${score2}</td>
    </tr>
  </table>
  <p>
  <form name="MatchScore" action="match-score" method="post">
    <input type="text" name="player1id" value=${player1id} readonly>
    <button type="submit">Игрок1 выиграл</button>
  </form>
  <form name="MatchScore" action="match-score" method="post">
    <input type="text" name="player2id" value=${player2id} readonly>
    <button type="submit">Игрок2 выиграл</button>
  </form>
  </p>
</div>
</body>
</html>