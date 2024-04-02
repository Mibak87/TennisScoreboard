package controller;

import java.io.*;
import java.util.UUID;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import repository.PlayersRepository;
import service.FinishedMatchesPersistenceService;
import service.Match;
import service.MatchMap;
import service.MatchScore;

@WebServlet(name = "MatchScoreController", value = "/match-score")
public class MatchScoreController extends HttpServlet {
    private UUID matchId;
    private Match match;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException {
        matchId = UUID.fromString(request.getParameter("uuid"));
        if (match == null) {
            match = MatchMap.currentMatch.get(matchId);
        }
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
            MatchMap.deleteFinishedMatch(matchId);
        } else if (match.isTieBreak()) {
            request.setAttribute("finish","Тай-брейк!");
        }  else {
            request.setAttribute("finish","");
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("match-score.jsp");
        dispatcher.forward(request,response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException {
        long playerId = Long.parseLong(request.getParameter("player-id"));
        match = new MatchScore().getMatchScore(match,playerId);
        if (match.isFinished()) {
            new FinishedMatchesPersistenceService().saveFinishedMatch(match);
        }
        MatchMap.updateCurrentMatch(matchId,match);
        response.sendRedirect("match-score?uuid=" + matchId);
    }
}
