package utils;

import entity.Match;
import entity.Player;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import repository.MatchesRepository;
import repository.PlayersRepository;

public class HibernateUtil {
    private static SessionFactory sessionFactory;

    static {
        PlayersRepository playersRepository = new PlayersRepository();
        MatchesRepository matchesRepository = new MatchesRepository();
        Player player1 = new Player("Джокович");
        playersRepository.save(player1);
        Player player2 = new Player("Алькараз");
        playersRepository.save(player2);
        Match match1 = new Match(player1,player2,player1);
        matchesRepository.save(match1);

        Player player3 = new Player("Синнер");
        playersRepository.save(player3);
        Player player4 = new Player("Медведев");
        playersRepository.save(player4);
        Match match2 = new Match(player3,player4,player4);
        matchesRepository.save(match2);

        Player player5 = new Player("Рублёв");
        playersRepository.save(player5);
        Player player6 = new Player("Зверев");
        playersRepository.save(player6);
        Match match3 = new Match(player5,player6,player6);
        matchesRepository.save(match3);

        Player player7 = new Player("Руне");
        playersRepository.save(player7);
        Player player8 = new Player("Гуркач");
        playersRepository.save(player8);
        Match match4 = new Match(player7,player8,player7);
        matchesRepository.save(match4);

        Player player9 = new Player("Рууд");
        playersRepository.save(player9);
        Match match5 = new Match(player9,player6,player6);
        matchesRepository.save(match5);

        Player player10 = new Player("Умбер");
        playersRepository.save(player10);
        Match match6 = new Match(player5,player10,player5);
        matchesRepository.save(match6);
    }
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        }
        return sessionFactory;
    }
}
