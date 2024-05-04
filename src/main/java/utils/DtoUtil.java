package utils;

import dto.MatchDto;
import dto.MatchScoreDto;
import service.MatchMap;

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
}
