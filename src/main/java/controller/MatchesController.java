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
        int start = 5*pageNumber - 5;
        int limit = Math.min (5*pageNumber - 1, matchesList.size() - 1);
        for (int i = start; i <= limit; i++) {
            Matches match = matchesList.get(i);
            request.setAttribute("player1_" + (i - start),match.getPlayer1().getName());
            request.setAttribute("player2_" + (i - start),match.getPlayer2().getName());
            request.setAttribute("winner_" + (i - start),match.getWinner().getName());
        }
        if (pageNumber == 1) {
            request.setAttribute("button1_hidden","hidden");
        } else {
            request.setAttribute("button1_hidden","");
        }
        if (pageNumber == (matchesList.size() / 5) + 1) {
            request.setAttribute("button2_hidden","hidden");
        } else {
            request.setAttribute("button2_hidden","");
        }
        request.setAttribute("page_number_last",pageNumber-1);
        request.setAttribute("page_number_next",pageNumber+1);
        RequestDispatcher dispatcher = request.getRequestDispatcher("matches.jsp");
        dispatcher.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
