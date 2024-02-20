package repository;

import entity.Matches;
import entity.Players;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateUtil;


import java.util.List;
import java.util.Optional;

public class PlayersRepository implements ScoreboardRepository<Players> {
    @Override
    public Optional<Players> findById(long id) throws HibernateException {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Players players = session.get(Players.class,id);
            return Optional.ofNullable(players);
        }
    }

    @Override
    public void save(Players players) throws HibernateException {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(players);
            transaction.commit();

        }
    }

    @Override
    public List<Players> findAll() throws HibernateException {
        return null;
    }

    @Override
    public Optional<Players> findByName(String name) throws HibernateException {
        return Optional.empty();
    }
}
