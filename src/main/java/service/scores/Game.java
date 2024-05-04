package service.scores;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class Game {

    private static final Logger logger = LogManager.getLogger(Game.class);
    public Scores getScore(Scores scores,int playerWinId) {
        if (playerWinId == 0) {
            return getPlayer1WinGame(scores);
        }
        return getPlayer2WinGame(scores);
    }

    private Scores getPlayer1WinGame(Scores scores) {
        List<Integer> player1Set = scores.getSets().getPlayer1Set();
        List<Integer> player2Set = scores.getSets().getPlayer2Set();
        int player1Game = player1Set.get(player1Set.size()-1) + 1;
        int player2Game = player2Set.get(player2Set.size()-1);
        if (player1Game == 7 || player1Game == 6 && player2Game <= 4) {
            player1Set.set(player1Set.size()-1,player1Game);
            logger.info("Player1 wins this set.");
            if (player1Set.size() == 3) {
                scores.setFinished(true);
                scores.setPlayerWinId(1);
                logger.info("Match is finished.");
            } else {
                player1Set.add(0);
                player2Set.add(0);
            }
        } else if (player1Game == 6 && player2Game == 6) {
            scores.setTieBreak(true);
            logger.info("Playing tie-break.");
        }
        scores.setSets(new Sets(player1Set,player2Set));
        return scores;
    }

    private Scores getPlayer2WinGame(Scores scores) {
        List<Integer> player1Set = scores.getSets().getPlayer1Set();
        List<Integer> player2Set = scores.getSets().getPlayer2Set();
        int player2Game = player2Set.get(player1Set.size()-1) + 1;
        int player1Game = player1Set.get(player2Set.size()-1);
        if (player2Game == 7 || player2Game == 6 && player1Game <= 4) {
            player2Set.set(player2Set.size()-1,player2Game);
            logger.info("Player2 wins this set.");
            if (player2Set.size() == 2) {
                scores.setFinished(true);
                scores.setPlayerWinId(2);
                logger.info("Match is finished.");
            } else {
                player1Set.add(0);
                player2Set.add(0);
            }
        } else if (player1Game == 6 && player2Game == 6) {
            scores.setTieBreak(true);
            logger.info("Playing tie-break.");
        }
        scores.setSets(new Sets(player1Set,player2Set));
        return scores;
    }
}
