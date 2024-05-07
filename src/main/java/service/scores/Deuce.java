package service.scores;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Deuce {
    private static final Logger logger = LogManager.getLogger(Deuce.class);
    public Scores getScore(Scores scores, int playerWinId) {
        logger.info("Playing deuce.");
        if (playerWinId == 1) {
            return getPlayer1WinDeuce(scores);
        }
        return getPlayer2WinDeuce(scores);
    }

    private Scores getPlayer1WinDeuce(Scores scores) {
        Points points = scores.getPoints();
        String player1Points = points.getPlayer1Points();
        String player2Points = points.getPlayer2Points();
        if (player1Points.equals("AD")) {
            scores.setDeuce(false);
            points.setPlayer1Points(PointStates.ZERO.getValue());
            points.setPlayer2Points(PointStates.ZERO.getValue());
            scores.setPoints(points);
            logger.info("Player1 win deuce.");
            return new Game().getScore(scores,1);
        }
        if (player2Points.equals("AD")) {
            points.setPlayer2Points(PointStates.FORTY.getValue());
            logger.info("Players have equals scores.");
        } else {
            points.setPlayer1Points(PointStates.ADVANTAGE.getValue());
            logger.info("Player1 is on advantage.");
        }
        scores.setPoints(points);
        return scores;
    }

    private Scores getPlayer2WinDeuce(Scores scores) {
        Points points = scores.getPoints();
        String player1Points = points.getPlayer1Points();
        String player2Points = points.getPlayer2Points();
        if (player2Points.equals("AD")) {
            scores.setDeuce(false);
            points.setPlayer1Points(PointStates.ZERO.getValue());
            points.setPlayer2Points(PointStates.ZERO.getValue());
            scores.setPoints(points);
            logger.info("Player2 win deuce.");
            return new Game().getScore(scores,2);
        }
        if (player1Points.equals("AD")) {
            points.setPlayer1Points(PointStates.FORTY.getValue());
            logger.info("Players have equals scores.");
        } else {
            points.setPlayer2Points(PointStates.ADVANTAGE.getValue());
            logger.info("Player2 is on advantage.");
        }
        scores.setPoints(points);
        return scores;
    }
}
