package service;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Match {
    private long player1Id;
    private long player2Id;
    private byte player1Score;
    private byte player2Score;
    private byte player1Game;
    private byte player2Game;
    private byte player1Set;
    private byte player2Set;
    private boolean player2Win;
    private boolean overScore;
    private boolean tieBreak;
    private boolean finished;


    public Match(long player1Id, long player2Id) {
        this.player1Id = player1Id;
        this.player2Id = player2Id;
        player1Score = 0;
        player2Score = 0;
        player1Game = 0;
        player2Game = 0;
        player1Set = 0;
        player2Set = 0;
        player2Win = false;
        overScore = false;
        tieBreak = false;
        finished = false;
    }

    public void setZeroScores() {
        setPlayer1Score((byte) 0);
        setPlayer2Score((byte) 0);
    }

    public void setZeroGames() {
        setPlayer1Game((byte) 0);
        setPlayer2Game((byte) 0);
    }
}
