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

    public Match(long player1Id, long player2Id) {
        this.player1Id = player1Id;
        this.player2Id = player2Id;
        player1Score = 0;
        player2Score = 0;
    }
}
