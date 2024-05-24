package controller;

import java.io.*;
import java.util.UUID;

import dto.MatchDto;
import entity.Player;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.MatchMap;

@WebServlet(name = "NewMatchController", value = "/new-match")
public class NewMatchController extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(NewMatchController.class);


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("new-match.jsp");
        dispatcher.forward(request,response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String player1Name = request.getParameter("player1");
        String player2Name = request.getParameter("player2");
        if (player1Name.equals(player2Name)) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
            dispatcher.forward(request,response);
        } else {
            Player player1 = new Player(player1Name);
            Player player2 = new Player(player2Name);
            UUID matchId = UUID.randomUUID();
            MatchDto currentMatch = new MatchDto(player1, player2);
            MatchMap.updateCurrentMatch(matchId, currentMatch);
            logger.info("The match has been created. Players: " + player1Name + " and " + player2Name + ".");
            response.sendRedirect("match-score?uuid=" + matchId);
        }
    }

}