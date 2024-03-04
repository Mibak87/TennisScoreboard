package service;

import lombok.AllArgsConstructor;
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
    private boolean overScore;
    private boolean tieBreak;


    public Match(long player1Id, long player2Id) {
        this.player1Id = player1Id;
        this.player2Id = player2Id;
        player1Score = 0;
        player2Score = 0;
        player1Game = 0;
        player2Game = 0;
        player1Set = 0;
        player2Set = 0;
        overScore = false;
    }
}
