package repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface ScoreboardRepository<T> {
    Optional<T> findById(long id) throws SQLException;
    void save (T t) throws SQLException;
    List<T> findAll() throws SQLException;
    Optional<T> findByName(String name) throws SQLException;
}
