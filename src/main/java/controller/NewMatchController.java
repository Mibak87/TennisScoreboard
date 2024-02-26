package controller;

import java.io.*;
import java.util.UUID;

import entity.Players;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.hibernate.HibernateException;
import repository.PlayersRepository;
import service.Match;
import service.MatchMap;

@WebServlet(name = "NewMatchController", value = "/new-match")
public class NewMatchController extends HttpServlet {



    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            Players player1 = new Players(request.getParameter("player1"));
            Players player2 = new Players(request.getParameter("player2"));
            new PlayersRepository().save(player1);
            new PlayersRepository().save(player2);
            UUID matchId = UUID.randomUUID();
            Match currentMatch = new Match(player1.getId(), player2.getId());
            MatchMap.updateCurrentMatch(matchId,currentMatch);
            response.sendRedirect("match-score.html?uuid=" + matchId);
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

}