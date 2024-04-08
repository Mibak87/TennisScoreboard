package controller;

import java.io.*;
import java.util.UUID;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import service.FinishedMatchesPersistenceService;
import service.Match;
import service.MatchMap;
import service.MatchScore;
import view.MatchScoreView;

@WebServlet(name = "MatchScoreController", value = "/match-score")
public class MatchScoreController extends HttpServlet {
    private UUID matchId;
    private Match match;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException {
        matchId = UUID.fromString(request.getParameter("uuid"));
        if (match == null) {
            match = MatchMap.currentMatch.get(matchId);
        }
        new MatchScoreView().renderingMatchScore(request,match);
        RequestDispatcher dispatcher = request.getRequestDispatcher("match-score.jsp");
        dispatcher.forward(request,response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException {
        long playerId = Long.parseLong(request.getParameter("player-id"));
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
