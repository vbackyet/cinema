package edu.school.cinema.repositories;


import edu.school.cinema.models.Film;
import edu.school.cinema.models.Hall;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class HallsRepositoryEntityManagerImpl {

    @PersistenceContext
    private final EntityManager entityManager;

    @Autowired
    public HallsRepositoryEntityManagerImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Hall> findAll() {
        return entityManager
                .createQuery("from Hall", Hall.class).getResultList();
    }

    public void save(Hall hall) {
        entityManager.persist(hall);
    }
}
