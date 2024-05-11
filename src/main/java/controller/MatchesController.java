package controller;

import dto.MatchesDto;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import utils.DtoUtil;
import java.io.IOException;

@WebServlet(name = "MatchesController", value = "/matches")
public class MatchesController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //new MatchesView().renderingMatches(request);
        String page = request.getParameter("page");
        int pageNumber = 1;
        if (page != null) {
            pageNumber = Integer.parseInt(page);
        }
        String filterPlayer = request.getParameter("filter_by_player_name");
        MatchesDto matchesDto = new DtoUtil().getMatchesDto(pageNumber,filterPlayer);
        request.setAttribute("matches",matchesDto);
        RequestDispatcher dispatcher = request.getRequestDispatcher("matches.jsp");
        dispatcher.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
