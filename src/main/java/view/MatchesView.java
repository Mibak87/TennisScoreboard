package view;

import entity.Matches;
import jakarta.servlet.http.HttpServletRequest;
import repository.MatchesRepository;

import java.util.List;

public class MatchesView {

    public void renderingMatches(HttpServletRequest request) {
        String page = request.getParameter("page");
        int pageNumber = 1;
        if (page != null) {
            pageNumber = Integer.parseInt(page);
        }
        String filterPlayer = request.getParameter("filter_by_player_name");
        List<Matches> matchesList;
        if (filterPlayer == null || filterPlayer.equals("")) {
            matchesList = new MatchesRepository().findFromRangeForPagination(5,pageNumber);
        } else {
            matchesList = new MatchesRepository().findByName(filterPlayer);
        }
        for (int i = 0; i <= matchesList.size() - 1; i++) {
            Matches matches = matchesList.get(i);
            request.setAttribute("player1_" + i,matches.getPlayer1().getName());
            request.setAttribute("player2_" + i,matches.getPlayer2().getName());
            request.setAttribute("winner_" + i,matches.getWinner().getName());
        }
        if (pageNumber == 1) {
            request.setAttribute("button1_hidden","disabled");
        } else {
            request.setAttribute("button1_hidden","");
        }
        if (pageNumber == (matchesList.size() / 5) + 1) {
            request.setAttribute("button2_hidden","disabled");
        } else {
            request.setAttribute("button2_hidden","");
        }
        request.setAttribute("page_number_last",pageNumber-1);
        request.setAttribute("page_number_next",pageNumber+1);
    }
}
