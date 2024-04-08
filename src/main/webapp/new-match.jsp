<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html lang="ru">
<head>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8">
    <title>Новый матч</title>
    <link rel="stylesheet" href="css/styles.css" type="text/css">
</head>
<body>
<div id="wrapper">

        <p>Новый матч</p>
        <p>
        <form name="NewMatch" action="new-match" method="post">
            <input type="text" name="player1" placeholder="Имя игрока 1">
            <input type="text" name="player2" placeholder="Имя игрока 2">
            <button type="submit">Начать</button>
        </form>
        </p>
        <p>${validation_error}</p>

    <section></section>
</div>
</body>
</html>
