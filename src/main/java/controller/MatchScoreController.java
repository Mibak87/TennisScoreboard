package controller;

import java.io.*;
import java.util.UUID;

import dto.MatchDto;
import dto.MatchScoreDto;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import service.FinishedMatchesPersistenceService;
import service.MatchMap;
import service.MatchScore;
import utils.DtoUtil;

@WebServlet(name = "MatchScoreController", value = "/match-score")
public class MatchScoreController extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException {
        UUID matchId = UUID.fromString(request.getParameter("uuid"));
        MatchScoreDto matchScoreDTO = new DtoUtil().getMatchScoreDTO(matchId);
        request.setAttribute("matchScore",matchScoreDTO);
        RequestDispatcher dispatcher = request.getRequestDispatcher("match-score.jsp");
        dispatcher.forward(request,response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException {
        UUID matchId = UUID.fromString(request.getParameter("uuid"));
        MatchDto match = MatchMap.currentMatch.get(matchId);
        int playerId = Integer.parseInt(request.getParameter("player-id"));
        new MatchScore().getMatchScore(matchId,playerId);
        MatchScoreDto matchScoreDTO = new DtoUtil().getMatchScoreDTO(matchId);
        if (match.isFinished()) {
            new FinishedMatchesPersistenceService().saveFinishedMatch(match);
            MatchMap.deleteFinishedMatch(matchId);
        }
        request.setAttribute("matchScore",matchScoreDTO);
        RequestDispatcher dispatcher = request.getRequestDispatcher("match-score.jsp");
        dispatcher.forward(request,response);
    }
}
