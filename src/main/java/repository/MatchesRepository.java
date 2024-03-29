package repository;

import entity.Matches;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utils.HibernateUtil;


import java.util.List;
import java.util.Optional;

public class MatchesRepository implements ScoreboardRepository<Matches> {

    @Override
    public Optional<Matches> findById(long id) throws HibernateException {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Matches matches = session.get(Matches.class,id);
            return Optional.ofNullable(matches);
        }
    }
    public List<Matches> findByName(String name) throws HibernateException {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM Matches m JOIN m.player1 p JOIN m.player2 p1" +
                    " WHERE p.name = :playerName OR p1.name = :playerName";
            Query query = session.createQuery(hql);
            query.setParameter("playerName",name);
            return query.list();
        }
    }

    @Override
    public List<Matches> findAll() throws HibernateException {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Matches", Matches.class).getResultList();
        }
    }

    @Override
    public void save(Matches matches) throws HibernateException {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(matches);
            transaction.commit();
        }
    }
}
