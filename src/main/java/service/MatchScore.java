package service;

public class MatchScore {
    public Match getMatchScore(Match match, long playerId) {
        if (match.getPlayer1Id() == playerId) {
            return getPlayer1WinScore(match);
        } else {
            return getPlayer2WinScore(match);
        }
    }

    public Match getPlayer1WinScore(Match match) {
        switch (match.getPlayer1Score()) {
            case 15 -> match.setPlayer1Score((byte) 30);
            case 30 -> {
                if (match.getPlayer2Score() == 40) {
                    match.setPlayer1Score((byte) 0);
                    match.setPlayer2Score((byte) 0);
                    match.setOverScore(true);
                } else {
                    match.setPlayer1Score((byte) 40);
                }
            }
            case 40 -> {
                match.setPlayer1Score((byte) 0);
                match.setPlayer2Score((byte) 0);
                match.setOverScore(false);
                byte gamePlayer1 = match.getPlayer1Game();
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

    public Match getPlayer2WinScore(Match match) {
        return match;
    }

}
