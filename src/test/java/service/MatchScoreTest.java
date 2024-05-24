package service;

import dto.MatchDto;
import entity.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class MatchScoreTest {
    MatchDto testMatch;
    UUID matchId;

    @BeforeEach
    void init() {
        Player player1 = new Player("Иванов");
        Player player2 = new Player("Петров");
        testMatch = new MatchDto(player1,player2);
        matchId = UUID.randomUUID();
    }

    @Test
    void playerWinPointWithScoreThenWinGame() {
        testMatch.setPlayer1Points("40");
        MatchMap.updateCurrentMatch(matchId, testMatch);
        new MatchScore().getMatchScore(matchId,1);
        MatchDto match = MatchMap.currentMatch.get(matchId);
        assertEquals(1,match.getPlayer1Set().get(0));
    }

    @Test
    void deuceModeActivate() {
        testMatch.setPlayer1Points("30");
        testMatch.setPlayer2Points("40");
        MatchMap.updateCurrentMatch(matchId, testMatch);
        new MatchScore().getMatchScore(matchId,1);
        boolean deuceMode = MatchMap.currentMatch.get(matchId).isDeuce();
        assertTrue(deuceMode);
    }

    @Test
    void playerNotWinGameWhenWinOneTimeWithDeuce() {
        testMatch.setPlayer1Points("40");
        testMatch.setPlayer2Points("40");
        testMatch.setDeuce(true);
        MatchMap.updateCurrentMatch(matchId, testMatch);
        new MatchScore().getMatchScore(matchId,1);
        int player1Game = MatchMap.currentMatch.get(matchId).getPlayer1Set().get(0);
        assertNotEquals(1,player1Game);
    }

    @Test
    void tieBreakActivate() {
        List<Integer> player1Set = testMatch.getPlayer1Set();
        List<Integer> player2Set = testMatch.getPlayer2Set();
        player1Set.set(0,5);
        player2Set.set(0,6);
        testMatch.setPlayer1Set(player1Set);
        testMatch.setPlayer2Set(player2Set);
        testMatch.setPlayer1Points("40");
        MatchMap.updateCurrentMatch(matchId, testMatch);
        new MatchScore().getMatchScore(matchId,1);
        boolean tieBreak = MatchMap.currentMatch.get(matchId).isTieBreak();
        assertTrue(tieBreak);
    }
}
