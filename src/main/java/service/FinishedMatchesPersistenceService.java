package service;

import dto.MatchDto;
import entity.Matches;
import entity.Players;
import repository.MatchesRepository;
import repository.PlayersRepository;


public class FinishedMatchesPersistenceService {
    public void saveFinishedMatch(MatchDto match) {
        Players player1 = match.getPlayer1();
        Players player2 = match.getPlayer2();
        try {
            new PlayersRepository().save(player1);
        } catch (Exception e) {
            player1 = new PlayersRepository().findByName(player1.getName()).orElseThrow();
        }
        try {
            new PlayersRepository().save(player2);
        } catch (Exception e) {
            player2 = new PlayersRepository().findByName(player2.getName()).orElseThrow();
        }
        new MatchesRepository().save(new Matches(player1,player2,match.getPlayerWin()));

    }
}
