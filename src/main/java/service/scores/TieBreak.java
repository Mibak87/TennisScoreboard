package service.scores;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class TieBreak {
    private static final Logger logger = LogManager.getLogger(TieBreak.class);
    public Scores getScore(Scores scores, int playerWinId) {
        if (playerWinId == 0) {
            return getPlayer1WinPoint(scores);
        }
        return getPlayer2WinPoint(scores);
    }

    private Scores getPlayer1WinPoint(Scores scores) {
        int player1Point = Integer.parseInt(scores.getPoints().getPlayer1Points()) + 1;
        int player2Point = Integer.parseInt(scores.getPoints().getPlayer2Points());
        int pointDifference = player1Point - player2Point;
        if (pointDifference >= 2 && player1Point >= 7) {
            scores.setPoints(new Points(PointStates.ZERO.getValue(),PointStates.ZERO.getValue()));
            scores.setTieBreak(false);
            List<Integer> player1Set = scores.getSets().getPlayer1Set();
            int player1Game = player1Set.get(player1Set.size()-1) + 1;
            List<Integer> player2Set = scores.getSets().getPlayer2Set();
            player1Set.set(player1Set.size()-1,player1Game);
            if (player1Set.size() == 3) {
                scores.setFinished(true);
                scores.setPlayerWinId(1);
                logger.info("Match is finished.");
            } else {
                player1Set.add(0);
                player2Set.add(0);
            }
            scores.setSets(new Sets(player1Set,player2Set));
        } else {
            scores.setPoints(new Points(String.valueOf(player1Point),String.valueOf(player2Point)));
        }
        return scores;
    }

    private Scores getPlayer2WinPoint(Scores scores) {
        int player2Point = Integer.parseInt(scores.getPoints().getPlayer2Points()) + 1;
        int player1Point = Integer.parseInt(scores.getPoints().getPlayer1Points());
        int pointDifference = player2Point - player1Point;
        if (pointDifference >= 2 && player2Point >= 7) {
            scores.setPoints(new Points(PointStates.ZERO.getValue(),PointStates.ZERO.getValue()));
            scores.setTieBreak(false);
            List<Integer> player2Set = scores.getSets().getPlayer2Set();
            int player2Game = player2Set.get(player2Set.size()-1) + 1;
            List<Integer> player1Set = scores.getSets().getPlayer1Set();
            player2Set.set(player2Set.size()-1,player2Game);
            if (player2Set.size() == 2) {
                scores.setFinished(true);
                scores.setPlayerWinId(2);
                logger.info("Match is finished.");
            } else {
                player1Set.add(0);
                player2Set.add(0);
            }
            scores.setSets(new Sets(player1Set,player2Set));
        } else {
            scores.setPoints(new Points(String.valueOf(player1Point),String.valueOf(player2Point)));
        }
        return scores;
    }
}
