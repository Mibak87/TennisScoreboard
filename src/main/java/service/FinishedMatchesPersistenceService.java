package service;

import entity.Matches;
import entity.Players;
import repository.MatchesRepository;
import repository.PlayersRepository;

public class FinishedMatchesPersistenceService {
    public void saveFinishedMatch(Match match) {
        Players player1 = new PlayersRepository().findById(match.getPlayer1Id()).orElseThrow();
        Players player2 = new PlayersRepository().findById(match.getPlayer2Id()).orElseThrow();
        if (match.getPlayer1Set() > match.getPlayer2Set()) {
            new MatchesRepository().save(new Matches(player1,player2,player1));
        } else {
            new MatchesRepository().save(new Matches(player1,player2,player2));
        }
    }
}
