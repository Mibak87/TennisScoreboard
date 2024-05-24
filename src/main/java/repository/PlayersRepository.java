package repository;

import entity.Player;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateUtil;


import java.util.List;
import java.util.Optional;

public class PlayersRepository implements ScoreboardRepository<Player> {
    @Override
    public Optional<Player> findById(long id) throws HibernateException {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Player player = session.get(Player.class,id);
            return Optional.ofNullable(player);
        }
    }

    @Override
    public void save(Player players) throws HibernateException {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(players);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public List<Player> findAll() throws HibernateException {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Player", Player.class).getResultList();
        }
    }

    public Optional<Player> findByName(String name) throws HibernateException {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String query = "FROM Player WHERE name = :name";
            Player player = session.createQuery(query, Player.class)
                    .setParameter("name", name)
                    .getSingleResult();
            return Optional.ofNullable(player);
        }
    }
}
