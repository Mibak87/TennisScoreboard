package service;

import dto.MatchDto;
import entity.Players;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class MatchScoreTest {
    MatchDto testMatch;
    UUID matchId;

    @BeforeEach
    void init() {
        Players player1 = new Players("Иванов");
        Players player2 = new Players("Петров");
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
    void deuceAfterPlayer1WinWithPlayer2Advantage() {
        testMatch.setPlayer1Points("40");
        testMatch.setPlayer2Points("AD");
        MatchMap.updateCurrentMatch(matchId, testMatch);
        new MatchScore().getMatchScore(matchId,1);
        String player2Points = MatchMap.currentMatch.get(matchId).getPlayer2Points();
        assertEquals("40",player2Points);
    }

    @Test
    void playerNotWinGameWhenWinOneTimeWithDeuce() {
        testMatch.setPlayer1Points("40");
        testMatch.setPlayer2Points("40");
        testMatch.setDeuce(true);
        MatchMap.updateCurrentMatch(matchId, testMatch);
        int player1Game = MatchMap.currentMatch.get(matchId).getPlayer1Set().get(0);
        assertNotEquals(1,player1Game);
    }

    @Test
    void tieBreakActivate() {
        List<Integer> player1Set = new ArrayList<>();
        List<Integer> player2Set = new ArrayList<>();
        player1Set.add(5);
        player2Set.add(6);
        testMatch.setPlayer1Set(player1Set);
        testMatch.setPlayer2Set(player2Set);
        testMatch.setPlayer1Points("40");
        MatchMap.updateCurrentMatch(matchId, testMatch);
        boolean tieBreak = MatchMap.currentMatch.get(matchId).isTieBreak();
        assertTrue(tieBreak);
    }
}
