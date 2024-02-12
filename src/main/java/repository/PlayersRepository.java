package repository;

import entity.Players;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class PlayersRepository implements ScoreboardRepository<Players> {
    @Override
    public Optional<Players> findById(long id) throws SQLException {
        return Optional.empty();
    }

    @Override
    public void save(Players players) throws SQLException {

    }

    @Override
    public List<Players> findAll() throws SQLException {
        return null;
    }

    @Override
    public Optional<Players> findByName(String name) throws SQLException {
        return Optional.empty();
    }
}
