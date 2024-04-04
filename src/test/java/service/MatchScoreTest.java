package service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MatchScoreTest {
    @Test
    void playerWinPointWithScoreThenWinGame() {
        Match testMatch = new Match(3,5);
        testMatch.setPlayer1Score((byte) 40);
        byte player1Game = new MatchScore().getMatchScore(testMatch,3).getPlayer1Game();
        assertEquals((byte) 1,player1Game);
    }
}
