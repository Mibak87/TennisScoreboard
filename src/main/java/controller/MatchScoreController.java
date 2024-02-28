package controller;

import java.io.*;
import java.util.UUID;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import repository.PlayersRepository;
import service.Match;
import service.MatchMap;

@WebServlet(name = "MatchScoreController", value = "/match-score")
public class MatchScoreController extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        UUID matchId = UUID.fromString(request.getParameter("uuid"));
        Match match = MatchMap.currentMatch.get(matchId);
        String player1 = new PlayersRepository().findById(match.getPlayer1Id()).get().getName();
        String player2 = new PlayersRepository().findById(match.getPlayer2Id()).get().getName();
        Byte player1Score = match.getPlayer1Score();
        Byte player2Score = match.getPlayer2Score();
        request.setAttribute("player1",player1);
        request.setAttribute("player2",player2);
        request.setAttribute("score1",player1Score);
        request.setAttribute("score2",player2Score);
        RequestDispatcher dispatcher = request.getRequestDispatcher("match-score.jsp");
        dispatcher.forward(request,response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

    }
}
