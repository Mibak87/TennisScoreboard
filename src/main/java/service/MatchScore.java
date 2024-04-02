package service;

public class MatchScore {
    public Match getMatchScore(Match match, long playerId) {
        if (match.getPlayer1Id() == playerId) {
            if (match.isOverScore()) {
                return getPlayer1WinOverScore(match);
            } else if (match.isTieBreak()) {
                return getPlayer1WinTieBreak(match);
            } else {
                return getPlayer1WinScore(match);
            }
        } else {
            if (match.isOverScore()) {
                return getPlayer2WinOverScore(match);
            } else if (match.isTieBreak()) {
                return getPlayer2WinTieBreak(match);
            } else {
                return getPlayer2WinScore(match);
            }
        }
    }

    private Match getPlayer1WinOverScore(Match match) {
        byte scorePlayer1 = match.getPlayer1Score();
        byte scoreDifference = (byte) (scorePlayer1- match.getPlayer2Score());
        switch (scoreDifference) {
            case 1 -> {
                match.setOverScore(false);
                return getPlayer1GameScore(match);
            }
            case -1 -> match.setZeroScores();
            case 0 -> {
                match.setZeroScores();
                match.setPlayer1Score((byte) 1);
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
            case 0 -> match.setPlayer1Score((byte) 15);
            case 15 -> match.setPlayer1Score((byte) 30);
            case 30 -> {
                if (match.getPlayer2Score() == 40) {
                    match.setPlayer1Score((byte) 40);
                    match.setOverScore(true);
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
        match.setZeroScores();
        match.setPlayer1Game((byte) (match.getPlayer1Game() + 1));
        byte player1Game = match.getPlayer1Game();
        byte player2Game = match.getPlayer2Game();
        if (player1Game == 7 || player1Game == 6 && player2Game <= 4) {
            match.setZeroGames();
            match.setPlayer1Set((byte) (match.getPlayer1Set() + 1));
            if (match.getPlayer1Set() == 2) {
                match.setFinished(true);
            }
        } else if (player1Game == 6 && player2Game == 6) {
            match.setZeroGames();
            match.setTieBreak(true);
        }
        return match;
    }

    private Match getPlayer2WinOverScore(Match match) {
        byte scorePlayer2 = match.getPlayer2Score();
        byte scoreDifference = (byte) (scorePlayer2- match.getPlayer1Score());
        switch (scoreDifference) {
            case 1 -> {
                match.setOverScore(false);
                return getPlayer2GameScore(match);
            }
            case -1 -> match.setZeroScores();
            case 0 -> {
                match.setZeroScores();
                match.setPlayer2Score((byte) 1);
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
            }
        } else {
            match.setPlayer2Game(gamePlayer2);
        }
        return match;
    }

    private Match getPlayer2WinScore(Match match) {
        switch (match.getPlayer2Score()) {
            case 0 -> match.setPlayer2Score((byte) 15);
            case 15 -> match.setPlayer2Score((byte) 30);
            case 30 -> {
                if (match.getPlayer1Score() == 40) {
                    match.setPlayer2Score((byte) 40);
                    match.setOverScore(true);
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
        match.setZeroScores();
        match.setPlayer2Game((byte) (match.getPlayer2Game() + 1));
        byte player1Game = match.getPlayer1Game();
        byte player2Game = match.getPlayer2Game();
        if (player2Game == 7 || player2Game == 6 && player1Game <= 4) {
            match.setZeroGames();
            match.setPlayer2Set((byte) (match.getPlayer2Set() + 1));
            if (match.getPlayer2Set() == 2) {
                match.setFinished(true);
            }
        } else if (player2Game == 6 && player1Game == 6) {
            match.setZeroGames();
            match.setTieBreak(true);
        }
        return match;
    }
}
