package service;


import dto.MatchDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.scores.*;

import java.util.List;
import java.util.UUID;

public class MatchScore {

    private static final Logger logger = LogManager.getLogger(MatchScore.class);

    public void getMatchScore(UUID matchId,int playerWinId) {
        MatchDto match = MatchMap.currentMatch.get(matchId);
        Scores scores = getScores(match);
        Scores scoresAfterUpdate;
        if (match.isDeuce()) {
            logger.info("Playing deuce.");
            scoresAfterUpdate = new Deuce().getScore(scores,playerWinId);
        } else if (match.isTieBreak()) {
            logger.info("Playing TieBreak.");
            scoresAfterUpdate = new TieBreak().getScore(scores,playerWinId);
        } else {
            logger.info("Playing RegularGame.");
            scoresAfterUpdate = new RegularGame().getScore(scores,playerWinId);
            if (scoresAfterUpdate.getPoints().getPlayer1Points().equals("40") &&
                    scoresAfterUpdate.getPoints().getPlayer2Points().equals("40")) {
                scoresAfterUpdate.setDeuce(true);
            }
        }
        MatchDto matchUpdate = updateMatchDto(scoresAfterUpdate,match);
        MatchMap.updateCurrentMatch(matchId,matchUpdate);
    }

    private MatchDto updateMatchDto(Scores scores,MatchDto matchDto) {
        MatchDto matchUpdate = matchDto;
        matchUpdate.setPlayer1Points(scores.getPoints().getPlayer1Points());
        matchUpdate.setPlayer2Points(scores.getPoints().getPlayer2Points());
        matchUpdate.setPlayer1Set(scores.getSets().getPlayer1Set());
        matchUpdate.setPlayer2Set(scores.getSets().getPlayer2Set());
        matchUpdate.setCurrentSetNumber(scores.getSets().getCurrentSetNumber());
        matchUpdate.setDeuce(scores.isDeuce());
        matchUpdate.setTieBreak(scores.isTieBreak());
        matchUpdate.setFinished(scores.isFinished());
        if (scores.isFinished()) {
            int sum = player1WinSum(scores);
            if (sum >= 2) {
                matchUpdate.setPlayerWin(matchDto.getPlayer1());
            } else {
                matchUpdate.setPlayerWin(matchDto.getPlayer2());
            }
        }
        return matchUpdate;
    }

    private Scores getScores(MatchDto matchDto) {
        Points points = new Points(matchDto.getPlayer1Points(),matchDto.getPlayer2Points());
        Sets sets = new Sets(matchDto.getPlayer1Set(),matchDto.getPlayer2Set(), matchDto.getCurrentSetNumber());
        Scores scores = new Scores(points,sets);
        scores.setDeuce(matchDto.isDeuce());
        scores.setTieBreak(matchDto.isTieBreak());
        return scores;
    }

    private int player1WinSum(Scores scores) {
        int sum = 0;
        List<Integer> player1Set = scores.getSets().getPlayer1Set();
        List<Integer> player2Set = scores.getSets().getPlayer2Set();
        for (int i = 0; i < player1Set.size(); i++) {
            if (player1Set.get(i) > player2Set.get(i)) {
                sum++;
            }
        }
        return sum;
    }
}
