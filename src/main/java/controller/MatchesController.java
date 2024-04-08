package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import view.MatchesView;
import java.io.IOException;

@WebServlet(name = "MatchesController", value = "/matches")
public class MatchesController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        new MatchesView().renderingMatches(request);
        RequestDispatcher dispatcher = request.getRequestDispatcher("matches.jsp");
        dispatcher.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
