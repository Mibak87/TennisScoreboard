package service;

import entity.Matches;
import entity.Players;
import repository.MatchesRepository;
import repository.PlayersRepository;


public class FinishedMatchesPersistenceService {
    public void saveFinishedMatch(Match match) {
        Players player1 = match.getPlayer1();
        Players player2 = match.getPlayer2();
        new PlayersRepository().save(player1);
        new PlayersRepository().save(player2);
        if (match.getPlayer1Set() > match.getPlayer2Set()) {
            new MatchesRepository().save(new Matches(player1,player2,player1));
        } else {
            new MatchesRepository().save(new Matches(player1,player2,player2));
        }
    }
}
