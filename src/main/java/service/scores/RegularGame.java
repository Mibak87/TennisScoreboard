package service.scores;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RegularGame {
    private static final Logger logger = LogManager.getLogger(RegularGame.class);
    public Scores getScore(Scores scores, int playerWinId) {
        if (playerWinId == 1) {
            return getPlayer1WinPoints(scores);
        }
        return getPlayer2WinPoints(scores);
    }

    private Scores getPlayer1WinPoints(Scores scores) {
        Points points = scores.getPoints();
        Points pointsAfterUpdate = points;
        switch (points.getPlayer1Points()) {
            case "0" -> {
                pointsAfterUpdate.setPlayer1Points(PointStates.FIFTEEN.getValue());
                logger.info("Player1 gets 15 points.");
            }
            case "15" -> {
                pointsAfterUpdate.setPlayer1Points(PointStates.THIRTY.getValue());
                logger.info("Player1 gets 30 points.");
            }
            case "30" -> {
                logger.info("Player1 gets 40 points.");
                pointsAfterUpdate.setPlayer1Points(PointStates.FORTY.getValue());
            }
            case "40" -> {
                pointsAfterUpdate.setPlayer1Points(PointStates.ZERO.getValue());
                pointsAfterUpdate.setPlayer2Points(PointStates.ZERO.getValue());
                scores = new Game().getScore(scores,1);
            }
        }
        scores.setPoints(pointsAfterUpdate);
        return scores;
    }

    private Scores getPlayer2WinPoints(Scores scores) {
        Points points = scores.getPoints();
        Points pointsAfterUpdate = points;
        switch (points.getPlayer2Points()) {
            case "0" -> {
                pointsAfterUpdate.setPlayer2Points(PointStates.FIFTEEN.getValue());
                logger.info("Player2 gets 15 points.");
            }
            case "15" -> {
                pointsAfterUpdate.setPlayer2Points(PointStates.THIRTY.getValue());
                logger.info("Player2 gets 30 points.");
            }
            case "30" -> {
                logger.info("Player2 gets 40 points.");
                pointsAfterUpdate.setPlayer2Points(PointStates.FORTY.getValue());
            }
            case "40" -> {
                pointsAfterUpdate.setPlayer1Points(PointStates.ZERO.getValue());
                pointsAfterUpdate.setPlayer2Points(PointStates.ZERO.getValue());
                scores = new Game().getScore(scores,2);
            }
        }
        scores.setPoints(pointsAfterUpdate);
        return scores;
    }
}
