package service;

public class MatchScore {
    public void getMatchScore(Match match, long playerId) {
        if (match.getPlayer1Id() == playerId) {
            getPlayer1WinScore(match);
        } else {
            getPlayer2WinScore(match);
        }
    }

    public void getPlayer1WinScore(Match match) {
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
                        if (gamePlayer2 < 5) {
                            match.setPlayer1Game((byte) (gamePlayer1 + 1));
                        } else {
                            match.setPlayer1Game((byte) 0);
                            match.setPlayer2Game((byte) 0);
                            match.setPlayer1Set((byte) (match.getPlayer1Set() + 1));
                        }
                    }
                    default -> {
                        if (gamePlayer1 < 5) {
                            match.setPlayer1Game((byte) (gamePlayer1 + 1));
                        } else {
                            match.setPlayer1Set((byte) (match.getPlayer1Set() + 1));
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
                }
                match.setPlayer1Score((byte) 15);
            }
        }
    }

    public void getPlayer2WinScore(Match match) {

    }

}
