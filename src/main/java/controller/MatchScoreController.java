package controller;

import java.io.*;
import java.util.UUID;

import dto.MatchScoreDTO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import service.FinishedMatchesPersistenceService;
import service.Match;
import service.MatchMap;
import service.MatchScore;

@WebServlet(name = "MatchScoreController", value = "/match-score")
public class MatchScoreController extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException {
        UUID matchId = UUID.fromString(request.getParameter("uuid"));
        Match match = MatchMap.currentMatch.get(matchId);
        String player1Score = String.valueOf(match.getPlayer1Score());
        String player2Score = String.valueOf(match.getPlayer2Score());
        if (match.isDeuce()) {
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
        String finish = "";
        String buttonDisabled = "";
        if (match.isFinished()) {
            buttonDisabled = "disabled";
            String playerWin = match.getPlayer1().getName();
            if (match.isPlayer2Win()) {
                playerWin = match.getPlayer2().getName();
            }
            finish = "Матч закончен! Победитель - " + playerWin + "!";
        } else if (match.isTieBreak()) {
            finish = "Тай-брейк!";
        }
        MatchScoreDTO matchScoreDTO = MatchScoreDTO.builder()
                        .player1Name(match.getPlayer1().getName())
                        .player2Name(match.getPlayer2().getName())
                        .player1Score(player1Score)
                        .player2Score(player2Score)
                        .player1Game(match.getPlayer1Game())
                        .player2Game(match.getPlayer2Game())
                        .player1Set(match.getPlayer1Set())
                        .player2Set(match.getPlayer2Set())
                        .finish(finish)
                        .buttonDisabled(buttonDisabled)
                        .build();
        request.setAttribute("matchScore",matchScoreDTO);
        RequestDispatcher dispatcher = request.getRequestDispatcher("match-score.jsp");
        dispatcher.forward(request,response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException {
        UUID matchId = UUID.fromString(request.getParameter("uuid"));
        Match match = MatchMap.currentMatch.get(matchId);
        int playerId = Integer.parseInt(request.getParameter("player-id"));
        match = new MatchScore().getMatchScore(match,playerId);
        if (match.isFinished()) {
            new FinishedMatchesPersistenceService().saveFinishedMatch(match);
            MatchMap.deleteFinishedMatch(matchId);
        } else {
            MatchMap.updateCurrentMatch(matchId, match);
        }
        response.sendRedirect("match-score?uuid=" + matchId);
    }
}
