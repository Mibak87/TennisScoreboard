package service;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class MatchMap {
    public static Map<UUID,Match> currentMatch;

    public static void updateCurrentMatch(UUID matchId, Match match) {
        if (currentMatch == null) {
            currentMatch = new ConcurrentHashMap<>();
        }
        currentMatch.put(matchId, match);
    }

    public static void deleteFinishedMatch(UUID matchId) {
        currentMatch.remove(matchId);
    }
}
