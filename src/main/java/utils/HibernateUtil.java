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
        Players player1 = new Players("Джокович");
        new PlayersRepository().save(player1);
        Players player2 = new Players("Алькараз");
        new PlayersRepository().save(player2);
        Matches match1 = new Matches(player1,player2,player1);
        new MatchesRepository().save(match1);

        Players player3 = new Players("Синнер");
        new PlayersRepository().save(player3);
        Players player4 = new Players("Медведев");
        new PlayersRepository().save(player4);
        Matches match2 = new Matches(player3,player4,player4);
        new MatchesRepository().save(match2);

        Players player5 = new Players("Рублёв");
        new PlayersRepository().save(player5);
        Players player6 = new Players("Зверев");
        new PlayersRepository().save(player6);
        Matches match3 = new Matches(player5,player6,player6);
        new MatchesRepository().save(match3);

        Players player7 = new Players("Руне");
        new PlayersRepository().save(player7);
        Players player8 = new Players("Гуркач");
        new PlayersRepository().save(player8);
        Matches match4 = new Matches(player7,player8,player7);
        new MatchesRepository().save(match4);

        Players player9 = new Players("Рууд");
        new PlayersRepository().save(player9);
        Matches match5 = new Matches(player9,player6,player6);
        new MatchesRepository().save(match5);

        Players player10 = new Players("Умбер");
        new PlayersRepository().save(player10);
        Matches match6 = new Matches(player5,player10,player5);
        new MatchesRepository().save(match6);
    }
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        }
        return sessionFactory;
    }
}
