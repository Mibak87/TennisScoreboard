package controller;

import entity.Matches;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import repository.MatchesRepository;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "MatchesController", value = "/matches")
public class MatchesController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int pageNumber =Integer.parseInt(request.getParameter("page"));
        List<Matches> matchesList = new MatchesRepository().findAll();
        for (int i = 5*pageNumber - 5; i <= 5*pageNumber - 1; i++) {
            Matches match = matchesList.get(i);
            request.setAttribute("player1_" + i,match.getPlayer1().getName());
            request.setAttribute("player2_" + i,match.getPlayer2().getName());
            request.setAttribute("winner_" + i,match.getWinner().getName());
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("matches.jsp");
        dispatcher.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
