package service;

public class MatchScore {
    public void getMatchScore(Match match, long playerId) {
        if (match.getPlayer1Id() == playerId) {
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
                    match.setPlayer1Game((byte) 1);
                }
                default -> {
                    if (match.isOverScore()) {
                        byte scoreDifference = (byte) (match.getPlayer1Score()- match.getPlayer2Score());
                        switch (scoreDifference) {
                            case 0 -> match.setPlayer1Score((byte) (match.getPlayer1Score() + 1));
                            case 1 -> {
                                match.setPlayer1Score((byte) 0);
                                match.setPlayer2Score((byte) 0);
                                match.setOverScore(false);
                                match.setPlayer1Game((byte) 1);
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
    }


}
