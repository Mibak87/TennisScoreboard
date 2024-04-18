package utils;

import dto.MatchScoreDTO;
import service.Match;
import service.MatchMap;

import java.util.UUID;

public class DtoUtil {
    public MatchScoreDTO getMatchScoreDTO(UUID matchId) {
        Match match = MatchMap.currentMatch.get(matchId);
        String player1Score = String.valueOf(match.getPlayer1Score());
        String player2Score = String.valueOf(match.getPlayer2Score());
        if (match.isDeuce()) {
            if (match.getPlayer1Score() == 1) {
                player1Score = "40 больше";
                player2Score = "40 меньше";
            } else if (match.getPlayer2Score() == 1) {
                player1Score = "40 меньше";
                player2Score = "40 больше";
            } else {
                player1Score = "40 ровно";
                player2Score = "40 ровно";
            }
        }
        String finish = "";
        String buttonDisabled = "";
        if (match.isFinished()) {
            buttonDisabled = "disabled";
            String playerWin = match.getPlayer1().getName();
            if (match.isPlayer2Win()) {
                playerWin = match.getPlayer2().getName();
            }
            finish = "Матч закончен! Победитель - " + playerWin + "!";
        } else if (match.isTieBreak()) {
            finish = "Тай-брейк!";
        }
        MatchScoreDTO matchScoreDTO = MatchScoreDTO.builder()
                .player1Name(match.getPlayer1().getName())
                .player2Name(match.getPlayer2().getName())
                .player1Score(player1Score)
                .player2Score(player2Score)
                .player1Game(match.getPlayer1Game())
                .player2Game(match.getPlayer2Game())
                .player1Set(match.getPlayer1Set())
                .player2Set(match.getPlayer2Set())
                .finish(finish)
                .buttonDisabled(buttonDisabled)
                .uuid(matchId.toString())
                .build();
        return matchScoreDTO;
    }
}
