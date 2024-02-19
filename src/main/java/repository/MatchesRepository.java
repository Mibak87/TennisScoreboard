package repository;

import entity.Matches;
import org.hibernate.Session;
import utils.HibernateUtil;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class MatchesRepository implements ScoreboardRepository<Matches> {

    @Override
    public Optional<Matches> findById(long id) throws SQLException {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Matches matches = session.get(Matches.class,id);
            return Optional.ofNullable(matches);
        }
    }

    @Override
    public Optional<Matches> findByName(String name) throws SQLException {

        return Optional.empty();
    }

    @Override
    public List<Matches> findAll() throws SQLException {
        return null;
    }

    @Override
    public void save(Matches matches) throws SQLException {

    }
}
