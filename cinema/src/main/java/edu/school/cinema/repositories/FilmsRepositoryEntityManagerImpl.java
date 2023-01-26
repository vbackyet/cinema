package edu.school.cinema.repositories;


import edu.school.cinema.models.Film;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class FilmsRepositoryEntityManagerImpl {
    @PersistenceContext
    private final EntityManager entityManager;


    @Autowired
    public FilmsRepositoryEntityManagerImpl(EntityManager sessionFactory) {
        this.entityManager = sessionFactory;
    }
//    @Override
    public List<Film> findAll() {
        return entityManager
                .createQuery("from Film", Film.class).getResultList();
    }

    public void save(Film film) {
        entityManager.persist(film);
    }

    public Film getById(int id) {
//        Session session = entityManager.
        return entityManager.find(Film.class, id);
    }

    public Film findById(int id) {
        return entityManager.find(Film.class, id);
    }

    public void delete(Film film) {
        entityManager.remove(film);
    }
}