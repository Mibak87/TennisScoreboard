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

    public Match getPlayer1WinOverScore(Match match) {
        byte scorePlayer1 = match.getPlayer1Score();
        byte scoreDifference = (byte) (scorePlayer1- match.getPlayer2Score());
        switch (scoreDifference) {
            case 1 -> {
                match.setPlayer1Game((byte) (match.getPlayer1Game() + 1));
                match.setZeroScores();
                match.setOverScore(false);
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
            }
            case -1 -> match.setZeroScores();
            case 0 -> match.setPlayer1Score((byte) (scorePlayer1 + 1));
        }
        return match;
    }

    public Match getPlayer1WinTieBreak(Match match) {
        byte gamePlayer1 = (byte) (match.getPlayer1Game() + 1);
        byte gamePlayer2 = match.getPlayer2Game();
        byte gameDifference = (byte) (gamePlayer1 - gamePlayer2);
        if (gameDifference >= 2 && gamePlayer1 == 7) {
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

    public Match getPlayer1WinScore(Match match) {
        switch (match.getPlayer1Score()) {
            case 15 -> match.setPlayer1Score((byte) 30);
            case 30 -> {
                if (match.getPlayer2Score() == 40) {
                    match.setZeroScores();
                    match.setOverScore(true);
                } else {
                    match.setPlayer1Score((byte) 40);
                }
            }
            case 40 -> {
                match.setZeroScores();
                byte gamePlayer1 = (byte) (match.getPlayer1Game() + 1);
                byte gamePlayer2 = match.getPlayer2Game();
                byte gameDifference = (byte) (gamePlayer1 - gamePlayer2);
                switch (gameDifference) {
                    case 1 -> {
                        if (gamePlayer1 < 5) {
                            match.setPlayer1Game((byte) (gamePlayer1 + 1));
                        } else {
                            match.setPlayer1Game((byte) 0);
                            match.setPlayer2Game((byte) 0);
                            match.setPlayer1Set((byte) (gamePlayer1 + 1));
                        }
                    }
                    case -1 -> {
                        if (gamePlayer2 < 6) {
                            match.setPlayer1Game((byte) (gamePlayer1 + 1));
                        } else {
                            match.setPlayer1Game((byte) 0);
                            match.setPlayer2Game((byte) 0);
                            match.setTieBreak(true);
                        }
                    }
                    default -> {
                        if (gamePlayer1 < 5) {
                            match.setPlayer1Game((byte) (gamePlayer1 + 1));
                        } else {
                            match.setPlayer1Set((byte) (match.getPlayer1Set() + 1));
                            match.setPlayer1Game((byte) 0);
                            match.setPlayer2Game((byte) 0);
                        }
                    }
                }

            }
            default -> {
                if (match.isOverScore()) {
                    byte scoreDifference = (byte) (match.getPlayer1Score()- match.getPlayer2Score());
                    byte scorePlayer1 = match.getPlayer1Score();
                    switch (scoreDifference) {
                        case 0 -> match.setPlayer1Score((byte) (scorePlayer1 + 1));
                        case 1 -> {
                            match.setPlayer1Score((byte) 0);
                            match.setPlayer2Score((byte) 0);
                            match.setOverScore(false);
                            match.setPlayer1Game((byte) (scorePlayer1 + 1));
                        }
                        case -1 -> {
                            match.setPlayer1Score((byte) 0);
                            match.setPlayer2Score((byte) 0);
                        }
                    }
                    match.setPlayer1Score((byte) 15);
                } else if (match.isTieBreak()) {
                    byte gameDifference = (byte) (match.getPlayer1Game() - match.getPlayer2Game());
                    byte player1Game = match.getPlayer1Game();
                    switch (gameDifference) {
                        case 0,-1 -> match.setPlayer1Game((byte) (player1Game + 1));
                        case 1 -> {
                            if (match.getPlayer2Game() == 5) {
                                match.setPlayer1Game((byte) 0);
                                match.setPlayer2Game((byte) 0);
                                match.setPlayer1Set((byte) (match.getPlayer1Set() + 1));
                                match.setTieBreak(false);
                                match.setPlayer1Game((byte) 0);
                                match.setPlayer2Game((byte) 0);
                            }
                        }
                        default -> {
                            if (match.getPlayer1Game() == 6 && gameDifference > 0) {
                                match.setPlayer1Game((byte) 0);
                                match.setPlayer2Game((byte) 0);
                                match.setPlayer1Set((byte) (match.getPlayer1Set() + 1));
                                match.setTieBreak(false);
                                match.setPlayer1Game((byte) 0);
                                match.setPlayer2Game((byte) 0);
                            } else {
                                match.setPlayer1Game((byte) (player1Game + 1));
                            }
                        }
                    }
                } else {
                    match.setPlayer1Score((byte) 15);
                }
            }
        }
        return match;
    }

    public Match getPlayer2WinOverScore(Match match) {
        byte scorePlayer2 = match.getPlayer2Score();
        byte scoreDifference = (byte) (scorePlayer2- match.getPlayer1Score());
        switch (scoreDifference) {
            case 1 -> {
                match.setPlayer2Game((byte) (match.getPlayer2Game() + 1));
                match.setZeroScores();
                match.setOverScore(false);
                byte player1Game = match.getPlayer1Game();
                byte player2Game = match.getPlayer2Game();
                if (player2Game == 7 || player2Game == 6 && player1Game <= 4) {
                    match.setZeroGames();
                    match.setPlayer2Set((byte) (match.getPlayer2Set() + 1));
                    if (match.getPlayer2Set() == 2) {
                        match.setFinished(true);
                        match.setPlayer2Win(true);
                    }
                } else if (player2Game == 6 && player1Game == 6) {
                    match.setZeroGames();
                    match.setTieBreak(true);
                }
            }
            case -1 -> match.setZeroScores();
            case 0 -> match.setPlayer2Score((byte) (scorePlayer2 + 1));
        }
        return match;
    }

    public Match getPlayer2WinTieBreak(Match match) {
        byte gamePlayer2 = (byte) (match.getPlayer2Game() + 1);
        byte gamePlayer1 = match.getPlayer1Game();
        byte gameDifference = (byte) (gamePlayer2 - gamePlayer1);
        if (gameDifference >= 2 && gamePlayer2 == 7) {
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

    public Match getPlayer2WinScore(Match match) {
        switch (match.getPlayer2Score()) {
            case 15 -> match.setPlayer2Score((byte) 30);
            case 30 -> {
                if (match.getPlayer1Score() == 40) {
                    match.setPlayer2Score((byte) 0);
                    match.setPlayer1Score((byte) 0);
                    match.setOverScore(true);
                } else {
                    match.setPlayer2Score((byte) 40);
                }
            }
            case 40 -> {
                match.setPlayer2Score((byte) 0);
                match.setPlayer1Score((byte) 0);
                match.setOverScore(false);
                byte gamePlayer1 = match.getPlayer1Game();
                byte gamePlayer2 = match.getPlayer2Game();
                byte gameDifference = (byte) (gamePlayer2 - gamePlayer1);
                switch (gameDifference) {
                    case 1 -> {
                        if (gamePlayer2 < 5) {
                            match.setPlayer2Game((byte) (gamePlayer2 + 1));
                        } else {
                            match.setPlayer1Game((byte) 0);
                            match.setPlayer2Game((byte) 0);
                            match.setPlayer2Set((byte) (gamePlayer2 + 1));
                        }
                    }
                    case -1 -> {
                        if (gamePlayer1 < 6) {
                            match.setPlayer2Game((byte) (gamePlayer2 + 1));
                        } else {
                            match.setPlayer1Game((byte) 0);
                            match.setPlayer2Game((byte) 0);
                            match.setTieBreak(true);
                        }
                    }
                    default -> {
                        if (gamePlayer2 < 5) {
                            match.setPlayer2Game((byte) (gamePlayer2 + 1));
                        } else {
                            match.setPlayer2Set((byte) (match.getPlayer2Set() + 1));
                            match.setPlayer1Game((byte) 0);
                            match.setPlayer2Game((byte) 0);
                        }
                    }
                }

            }
            default -> {
                if (match.isOverScore()) {
                    byte scoreDifference = (byte) (match.getPlayer2Score()- match.getPlayer1Score());
                    byte scorePlayer2 = match.getPlayer2Score();
                    switch (scoreDifference) {
                        case 0 -> match.setPlayer2Score((byte) (scorePlayer2 + 1));
                        case 1 -> {
                            match.setPlayer1Score((byte) 0);
                            match.setPlayer2Score((byte) 0);
                            match.setOverScore(false);
                            match.setPlayer2Game((byte) (scorePlayer2 + 1));
                        }
                        case -1 -> {
                            match.setPlayer1Score((byte) 0);
                            match.setPlayer2Score((byte) 0);
                        }
                    }
                    match.setPlayer2Score((byte) 15);
                } else if (match.isTieBreak()) {
                    byte gameDifference = (byte) (match.getPlayer2Game() - match.getPlayer1Game());
                    byte player2Game = match.getPlayer2Game();
                    switch (gameDifference) {
                        case 0,-1 -> match.setPlayer2Game((byte) (player2Game + 1));
                        case 1 -> {
                            if (match.getPlayer1Game() == 5) {
                                match.setPlayer1Game((byte) 0);
                                match.setPlayer2Game((byte) 0);
                                match.setPlayer2Set((byte) (match.getPlayer2Set() + 1));
                                match.setTieBreak(false);
                                match.setPlayer1Game((byte) 0);
                                match.setPlayer2Game((byte) 0);
                            }
                        }
                        default -> {
                            if (match.getPlayer2Game() == 6 && gameDifference > 0) {
                                match.setPlayer2Game((byte) 0);
                                match.setPlayer1Game((byte) 0);
                                match.setPlayer2Set((byte) (match.getPlayer2Set() + 1));
                                match.setTieBreak(false);
                                match.setPlayer1Game((byte) 0);
                                match.setPlayer2Game((byte) 0);
                            } else {
                                match.setPlayer2Game((byte) (player2Game + 1));
                            }
                        }
                    }
                } else {
                    match.setPlayer2Score((byte) 15);
                }
            }
        }
        return match;
    }

}
