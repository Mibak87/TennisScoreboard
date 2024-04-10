package controller;

import java.io.*;
import java.util.UUID;

import entity.Players;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.hibernate.HibernateException;
import repository.PlayersRepository;
import service.Match;
import service.MatchMap;

@WebServlet(name = "NewMatchController", value = "/new-match")
public class NewMatchController extends HttpServlet {



    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("new-match.jsp");
        dispatcher.forward(request,response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String player1Name = request.getParameter("player1");
        String player2Name = request.getParameter("player2");
            try {
                Players player1 = new Players(player1Name);
                Players player2 = new Players(player2Name);
                UUID matchId = UUID.randomUUID();
                Match currentMatch = new Match(player1, player2);
                MatchMap.updateCurrentMatch(matchId, currentMatch);
                response.sendRedirect("match-score?uuid=" + matchId);
            } catch (HibernateException e) {
                e.printStackTrace();
            }
    }

}