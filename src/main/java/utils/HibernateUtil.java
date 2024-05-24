package utils;

import entity.Matches;
import entity.Players;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import repository.MatchesRepository;
import repository.PlayersRepository;

public class HibernateUtil {
    private static SessionFactory sessionFactory;

    static {
        PlayersRepository playersRepository = new PlayersRepository();
        MatchesRepository matchesRepository = new MatchesRepository();
        Players player1 = new Players("Джокович");
        playersRepository.save(player1);
        Players player2 = new Players("Алькараз");
        playersRepository.save(player2);
        Matches match1 = new Matches(player1,player2,player1);
        matchesRepository.save(match1);

        Players player3 = new Players("Синнер");
        playersRepository.save(player3);
        Players player4 = new Players("Медведев");
        playersRepository.save(player4);
        Matches match2 = new Matches(player3,player4,player4);
        matchesRepository.save(match2);

        Players player5 = new Players("Рублёв");
        playersRepository.save(player5);
        Players player6 = new Players("Зверев");
        playersRepository.save(player6);
        Matches match3 = new Matches(player5,player6,player6);
        matchesRepository.save(match3);

        Players player7 = new Players("Руне");
        playersRepository.save(player7);
        Players player8 = new Players("Гуркач");
        playersRepository.save(player8);
        Matches match4 = new Matches(player7,player8,player7);
        matchesRepository.save(match4);

        Players player9 = new Players("Рууд");
        playersRepository.save(player9);
        Matches match5 = new Matches(player9,player6,player6);
        matchesRepository.save(match5);

        Players player10 = new Players("Умбер");
        playersRepository.save(player10);
        Matches match6 = new Matches(player5,player10,player5);
        matchesRepository.save(match6);
    }
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        }
        return sessionFactory;
    }
}
