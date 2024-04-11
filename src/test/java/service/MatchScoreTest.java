package service;

import entity.Players;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MatchScoreTest {
    Match testMatch;

    @BeforeEach
    void init() {
        Players player1 = new Players("Иванов");
        Players player2 = new Players("Петров");
        testMatch = new Match(player1,player2);
    }

    @Test
    void playerWinPointWithScoreThenWinGame() {
        testMatch.setPlayer1Score((byte) 40);
        byte player1Game = new MatchScore().getMatchScore(testMatch,1).getPlayer1Game();
        assertEquals((byte) 1,player1Game);
    }

    @Test
    void overScoreModeActivate() {
        testMatch.setPlayer1Score((byte) 30);
        testMatch.setPlayer2Score((byte) 40);
        boolean overScoreMode = new MatchScore().getMatchScore(testMatch,1).isDeuce();
        assertTrue(overScoreMode);
    }

    @Test
    void playerNotWinGameWhenWinOneTimeWithOverScore() {
        testMatch.setPlayer1Score((byte) 40);
        testMatch.setPlayer2Score((byte) 40);
        testMatch.setDeuce(true);
        byte player1Game = new MatchScore().getMatchScore(testMatch,1).getPlayer1Game();
        assertNotEquals(1,player1Game);
    }

    @Test
    void tieBreakActivate() {
        testMatch.setPlayer1Game((byte) 5);
        testMatch.setPlayer2Game((byte) 6);
        testMatch.setPlayer1Score((byte) 40);
        boolean tieBreak = new MatchScore().getMatchScore(testMatch,1).isTieBreak();
        assertTrue(tieBreak);
    }
}
