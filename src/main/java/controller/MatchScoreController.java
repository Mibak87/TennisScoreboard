package controller;

import java.io.*;
import java.util.UUID;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import repository.PlayersRepository;
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
        String player1 = new PlayersRepository().findById(match.getPlayer1Id()).get().getName();
        String player2 = new PlayersRepository().findById(match.getPlayer2Id()).get().getName();
        request.setAttribute("player1name",player1);
        request.setAttribute("player2name",player2);
        request.setAttribute("score1",match.getPlayer1Score());
        request.setAttribute("score2",match.getPlayer2Score());
        request.setAttribute("player1id",match.getPlayer1Id());
        request.setAttribute("player2id",match.getPlayer2Id());
        request.setAttribute("game1",match.getPlayer1Game());
        request.setAttribute("game2",match.getPlayer2Game());
        request.setAttribute("set1",match.getPlayer1Set());
        request.setAttribute("set2",match.getPlayer2Set());
        RequestDispatcher dispatcher = request.getRequestDispatcher("match-score.jsp");
        dispatcher.forward(request,response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException {
        long playerId = Long.parseLong(request.getParameter("player-id"));
        match = new MatchScore().getMatchScore(match,playerId);
        MatchMap.updateCurrentMatch(matchId,match);
        if (match.getPlayer1Set() == 2 || match.getPlayer1Set() == 2) {
            System.out.println("Конец!");

        }
        response.sendRedirect("match-score?uuid=" + matchId);
    }
}
