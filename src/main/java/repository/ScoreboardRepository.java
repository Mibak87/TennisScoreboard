package repository;

import org.hibernate.HibernateException;


import java.util.List;
import java.util.Optional;

public interface ScoreboardRepository<T> {
    Optional<T> findById(long id) throws HibernateException;
    void save (T t) throws HibernateException;
    List<T> findAll() throws HibernateException;
    Optional<T> findByName(String name) throws HibernateException;
}
