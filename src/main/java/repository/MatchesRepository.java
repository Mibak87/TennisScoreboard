package repository;

import entity.Matches;
import org.hibernate.HibernateException;
import org.hibernate.Session;
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

    @Override
    public Optional<Matches> findByName(String name) throws HibernateException {

        return Optional.empty();
    }

    @Override
    public List<Matches> findAll() throws HibernateException {
        return null;
    }

    @Override
    public void save(Matches matches) throws HibernateException {

    }
}
