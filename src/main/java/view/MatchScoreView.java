package view;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import repository.PlayersRepository;
import service.Match;

public class MatchScoreView {

    public void renderingMatchScore(HttpServletRequest request, Match match) {
        String player1 = new PlayersRepository().findById(match.getPlayer1Id()).orElseThrow().getName();
        String player2 = new PlayersRepository().findById(match.getPlayer2Id()).orElseThrow().getName();
        String player1Score = String.valueOf(match.getPlayer1Score());
        String player2Score = String.valueOf(match.getPlayer2Score());
        if (match.isOverScore()) {
            if (match.getPlayer1Score() == 1) {
                player1Score = "40 больше";
                player2Score = "40 меньше";
            } else if (match.getPlayer2Score() == 1) {
                player1Score = "40 меньше";
                player2Score = "40 больше";
            } else {
                player1Score = "40 ровно";
                player2Score = "40 ровно";
            }
        }
        request.setAttribute("player1name",player1);
        request.setAttribute("player2name",player2);
        request.setAttribute("score1",player1Score);
        request.setAttribute("score2",player2Score);
        request.setAttribute("player1id",match.getPlayer1Id());
        request.setAttribute("player2id",match.getPlayer2Id());
        request.setAttribute("game1",match.getPlayer1Game());
        request.setAttribute("game2",match.getPlayer2Game());
        request.setAttribute("set1",match.getPlayer1Set());
        request.setAttribute("set2",match.getPlayer2Set());
        if (match.isFinished()) {
            request.setAttribute("button_disabled","disabled");
            String playerWin = player1;
            if (match.isPlayer2Win()) {
                playerWin = player2;
            }
            request.setAttribute("finish","Матч закончен! Победитель - " + playerWin + "!");
        } else if (match.isTieBreak()) {
            request.setAttribute("finish","Тай-брейк!");
        }  else {
            request.setAttribute("finish","");
        }
    }
}
