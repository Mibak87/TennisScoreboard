package service;


import dto.MatchDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.scores.*;

import java.util.UUID;

public class MatchScore {

    private static final Logger logger = LogManager.getLogger(MatchScore.class);

    public void getScore(UUID matchId,int playerWinId) {
        MatchDto match = MatchMap.currentMatch.get(matchId);
        Points points = new Points(match.getPlayer1Points(),match.getPlayer2Points());
        Sets sets = new Sets(match.getPlayer1Set(),match.getPlayer2Set());
        Scores scores = new Scores(points,sets);
        Points pointsAfterUpdate;
        if (match.isDeuce()) {
            new Deuce().getScore(scores,playerWinId);
        } else if (match.isTieBreak()) {
            new TieBreak().getScore(scores,playerWinId);
        } else {
            pointsAfterUpdate = new RegularGame().getScore(points,playerWinId);
            if (pointsAfterUpdate.getPlayer1Points().equals("40") &&
                pointsAfterUpdate.getPlayer2Points().equals("40")) {
                match.setDeuce(true);
                logger.info("Playing deuce.");
            }
        }
        MatchMap.updateCurrentMatch(matchId,match);
    }
    public Match getMatchScore(Match match, int playerId) {
        if (playerId == 0) {
            if (match.isDeuce()) {
                return getPlayer1WinDeuce(match);
            } else if (match.isTieBreak()) {
                return getPlayer1WinTieBreak(match);
            } else {
                return getPlayer1WinScore(match);
            }
        } else {
            if (match.isDeuce()) {
                return getPlayer2WinDeuce(match);
            } else if (match.isTieBreak()) {
                return getPlayer2WinTieBreak(match);
            } else {
                return getPlayer2WinScore(match);
            }
        }
    }

    private Match getPlayer1WinDeuce(Match match) {
        byte scorePlayer1 = match.getPlayer1Score();
        byte scoreDifference = (byte) (scorePlayer1- match.getPlayer2Score());
        switch (scoreDifference) {
            case 1 -> {
                match.setDeuce(false);
                return getPlayer1GameScore(match);
            }
            case -1 -> {
                match.setZeroScores();
                logger.info("Players have equals scores.");
            }
            case 0 -> {
                match.setZeroScores();
                match.setPlayer1Score((byte) 1);
                logger.info("Player1 is on advantage.");
            }
        }
        return match;
    }

    private Match getPlayer1WinTieBreak(Match match) {
        byte gamePlayer1 = (byte) (match.getPlayer1Game() + 1);
        byte gamePlayer2 = match.getPlayer2Game();
        byte gameDifference = (byte) (gamePlayer1 - gamePlayer2);
        if (gameDifference >= 2 && gamePlayer1 >= 7) {
            match.setZeroGames();
            match.setTieBreak(false);
            match.setPlayer1Set((byte) (match.getPlayer1Set() + 1));
            if (match.getPlayer1Set() == 2) {
                match.setFinished(true);
            }
        } else {
            match.setPlayer1Game(gamePlayer1);
        }
        return match;
    }

    private Match getPlayer1WinScore(Match match) {
        switch (match.getPlayer1Score()) {
            case 0 -> {
                match.setPlayer1Score((byte) 15);
                logger.info("Player1 gets 15 points.");
            }
            case 15 -> {
                match.setPlayer1Score((byte) 30);
                logger.info("Player1 gets 30 points.");
            }
            case 30 -> {
                logger.info("Player1 gets 40 points.");
                if (match.getPlayer2Score() == 40) {
                    match.setPlayer1Score((byte) 40);
                    match.setDeuce(true);
                    logger.info("Playing deuce.");
                } else {
                    match.setPlayer1Score((byte) 40);
                }
            }
            case 40 -> {
                return getPlayer1GameScore(match);
            }
        }
        return match;
    }

    private Match getPlayer1GameScore(Match match) {
        logger.info("Player1 wins this game.");
        match.setZeroScores();
        match.setPlayer1Game((byte) (match.getPlayer1Game() + 1));
        byte player1Game = match.getPlayer1Game();
        byte player2Game = match.getPlayer2Game();
        if (player1Game == 7 || player1Game == 6 && player2Game <= 4) {
            match.setZeroGames();
            match.setPlayer1Set((byte) (match.getPlayer1Set() + 1));
            logger.info("Player1 wins this set.");
            if (match.getPlayer1Set() == 2) {
                match.setFinished(true);
                logger.info("Match is finished.");
            }
        } else if (player1Game == 6 && player2Game == 6) {
            match.setZeroGames();
            match.setTieBreak(true);
            logger.info("Playing tie-break.");
        }

        return match;
    }

    private Match getPlayer2WinDeuce(Match match) {
        byte scorePlayer2 = match.getPlayer2Score();
        byte scoreDifference = (byte) (scorePlayer2- match.getPlayer1Score());
        switch (scoreDifference) {
            case 1 -> {
                match.setDeuce(false);
                return getPlayer2GameScore(match);
            }
            case -1 -> {
                match.setZeroScores();
                logger.info("Players have equals scores.");
            }
            case 0 -> {
                match.setZeroScores();
                match.setPlayer2Score((byte) 1);
                logger.info("Player2 is on advantage.");
            }
        }
        return match;
    }

    private Match getPlayer2WinTieBreak(Match match) {
        byte gamePlayer2 = (byte) (match.getPlayer2Game() + 1);
        byte gamePlayer1 = match.getPlayer1Game();
        byte gameDifference = (byte) (gamePlayer2 - gamePlayer1);
        if (gameDifference >= 2 && gamePlayer2 >= 7) {
            match.setZeroGames();
            match.setTieBreak(false);
            match.setPlayer2Set((byte) (match.getPlayer2Set() + 1));
            if (match.getPlayer2Set() == 2) {
                match.setFinished(true);
                logger.info("Match is finished.");
            }
        } else {
            match.setPlayer2Game(gamePlayer2);
        }
        return match;
    }

    private Match getPlayer2WinScore(Match match) {
        switch (match.getPlayer2Score()) {
            case 0 -> {
                match.setPlayer2Score((byte) 15);
                logger.info("Player2 gets 15 points.");
            }
            case 15 -> {
                match.setPlayer2Score((byte) 30);
                logger.info("Player2 gets 30 points.");
            }
            case 30 -> {
                logger.info("Player2 gets 40 points.");
                if (match.getPlayer1Score() == 40) {
                    match.setPlayer2Score((byte) 40);
                    match.setDeuce(true);
                    logger.info("Playing deuce.");
                } else {
                    match.setPlayer2Score((byte) 40);
                }
            }
            case 40 -> {
                return getPlayer2GameScore(match);
            }
        }
        return match;
    }

    private Match getPlayer2GameScore(Match match) {
        logger.info("Player2 wins this game.");
        match.setZeroScores();
        match.setPlayer2Game((byte) (match.getPlayer2Game() + 1));
        byte player1Game = match.getPlayer1Game();
        byte player2Game = match.getPlayer2Game();
        if (player2Game == 7 || player2Game == 6 && player1Game <= 4) {
            match.setZeroGames();
            match.setPlayer2Set((byte) (match.getPlayer2Set() + 1));
            logger.info("Player1 wins this set.");
            if (match.getPlayer2Set() == 2) {
                match.setFinished(true);
                match.setPlayer2Win(true);
                logger.info("Match is finished.");
            }
        } else if (player2Game == 6 && player1Game == 6) {
            match.setZeroGames();
            match.setTieBreak(true);
            logger.info("Playing tie-break.");
        }
        return match;
    }
}
