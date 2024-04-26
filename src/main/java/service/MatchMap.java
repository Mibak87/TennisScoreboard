package service;

import dto.MatchDto;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class MatchMap {
    public static Map<UUID,MatchDto> currentMatch;

    public static void updateCurrentMatch(UUID matchId, MatchDto match) {
        if (currentMatch == null) {
            currentMatch = new ConcurrentHashMap<>();
        }
        currentMatch.put(matchId, match);
    }

    public static void deleteFinishedMatch(UUID matchId) {
        currentMatch.remove(matchId);
    }
}
