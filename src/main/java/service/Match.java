package service;

import entity.Players;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Match {
    private Players player1;
    private Players player2;
    private byte player1Score;
    private byte player2Score;
    private byte player1Game;
    private byte player2Game;
    private byte player1Set;
    private byte player2Set;
    private boolean player2Win;
    private boolean deuce;
    private boolean tieBreak;
    private boolean finished;


    public Match(Players player1, Players player2) {
        this.player1 = player1;
        this.player2 = player2;
        player1Score = 0;
        player2Score = 0;
        player1Game = 0;
        player2Game = 0;
        player1Set = 0;
        player2Set = 0;
        player2Win = false;
        deuce = false;
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
