package service.scores;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class Game {

    private static final Logger logger = LogManager.getLogger(Game.class);
    private int setNumber;

    public Scores getScore(Scores scores,int playerWinId) {
        setNumber = scores.getSets().getCurrentSetNumber();
        if (playerWinId == 1) {
            return getPlayer1WinGame(scores);
        }
        return getPlayer2WinGame(scores);
    }

    private Scores getPlayer1WinGame(Scores scores) {
        List<Integer> player1Set = scores.getSets().getPlayer1Set();
        List<Integer> player2Set = scores.getSets().getPlayer2Set();
        int player1Game = player1Set.get(setNumber-1) + 1;
        int player2Game = player2Set.get(setNumber-1);
        player1Set.set(setNumber-1,player1Game);
        if (player1Game == 7 || player1Game == 6 && player2Game <= 4) {
            logger.info("Player1 wins this set.");
            if (setNumber == 3) {
                scores.setFinished(true);
                logger.info("Match is finished.");
            } else {
                setNumber++;
            }
        } else if (player1Game == 6 && player2Game == 6) {
            scores.setTieBreak(true);
            logger.info("Playing tie-break.");
        }
        scores.setSets(new Sets(player1Set,player2Set,setNumber));
        return scores;
    }

    private Scores getPlayer2WinGame(Scores scores) {
        List<Integer> player1Set = scores.getSets().getPlayer1Set();
        List<Integer> player2Set = scores.getSets().getPlayer2Set();
        int setNumber = scores.getSets().getCurrentSetNumber();
        int player2Game = player2Set.get(setNumber-1) + 1;
        int player1Game = player1Set.get(setNumber-1);
        player2Set.set(setNumber-1,player2Game);
        if (player2Game == 7 || player2Game == 6 && player1Game <= 4) {
            logger.info("Player2 wins this set.");
            if (setNumber == 3) {
                scores.setFinished(true);
                logger.info("Match is finished.");
            } else {
                setNumber++;
            }
        } else if (player1Game == 6 && player2Game == 6) {
            scores.setTieBreak(true);
            logger.info("Playing tie-break.");
        }
        scores.setSets(new Sets(player1Set,player2Set,setNumber));
        return scores;
    }
}
