package service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class MatchMap {
    public static Map<UUID,Match> currentMatch = new HashMap<>();

    public Map<UUID, Match> getCurrentMatch() {
        return currentMatch;
    }

    public void setCurrentMatch(Map<UUID, Match> currentMatch) {
        this.currentMatch = currentMatch;
    }

    public static void updateCurrentMatch(UUID matchId, Match match) {
        if (currentMatch == null) {
            currentMatch = new HashMap<>();
        }
        currentMatch.put(matchId, match);
    }
}
