package utils;

import dto.MatchDto;
import dto.MatchScoreDto;
import dto.MatchesDto;
import entity.Matches;
import repository.MatchesRepository;
import service.MatchMap;

import java.util.List;
import java.util.UUID;

public class DtoUtil {
    public MatchScoreDto getMatchScoreDTO(UUID matchId) {
        MatchDto match = MatchMap.currentMatch.get(matchId);
        String player1Points = String.valueOf(match.getPlayer1Points());
        String player2Points = String.valueOf(match.getPlayer2Points());
        String finish = "";
        String buttonDisabled = "";
        if (match.isFinished()) {
            buttonDisabled = "disabled";
            String playerWin = match.getPlayerWin().getName();
            finish = "Матч закончен! Победитель - " + playerWin + "!";
        } else if (match.isTieBreak()) {
            finish = "Тай-брейк!";
        }
        return MatchScoreDto.builder()
                .player1Name(match.getPlayer1().getName())
                .player2Name(match.getPlayer2().getName())
                .player1Points(player1Points)
                .player2Points(player2Points)
                .player1Set(match.getPlayer1Set())
                .player2Set(match.getPlayer2Set())
                .finish(finish)
                .buttonDisabled(buttonDisabled)
                .uuid(matchId.toString())
                .build();
    }

    public MatchesDto getMatchesDto(int page,String filterPlayer) {
        long matchesCountNumber = new MatchesRepository().findCountNumber();
        int pageNumber = (int) (matchesCountNumber / 5) + 1;
        List<Matches> matchesList;
        if (filterPlayer == null || filterPlayer.isEmpty()) {
            matchesList = new MatchesRepository().findFromRangeForPagination(5,page);
        } else {
            matchesList = new MatchesRepository().findByName(filterPlayer);
            pageNumber = matchesList.size() / 5 + 1;
        }
        String button1Hidden = "";
        if (page == 1) {
            button1Hidden = "disabled";
        }
        String button2Hidden = "";
        if (page == pageNumber) {
            button2Hidden = "disabled";
        }
        String pageNumberLast = String.valueOf(page - 1);
        String pageNumberNext = String.valueOf(page + 1);
        return MatchesDto.builder()
                .page(page)
                .pageNumber(pageNumber)
                .filterPlayer(filterPlayer)
                .matchesList(matchesList)
                .button1Hidden(button1Hidden)
                .button2Hidden(button2Hidden)
                .pageNumberLast(pageNumberLast)
                .pageNumberNext(pageNumberNext)
                .build();
    }
}
