package repository;

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
            Players player = session.get(Players.class,id);
            return Optional.ofNullable(player);
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
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Players", Players.class).getResultList();
        }
    }

    @Override
    public Optional<Players> findByName(String name) throws HibernateException {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String query = "FROM Players WHERE name = :name";
            Players player = session.createQuery(query, Players.class)
                    .setParameter("name", "Сафин")
                    .getSingleResult();
            return Optional.ofNullable(player);
        }
    }
}
