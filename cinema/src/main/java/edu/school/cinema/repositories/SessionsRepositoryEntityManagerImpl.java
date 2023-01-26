package edu.school.cinema.repositories;


import edu.school.cinema.models.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class SessionsRepositoryEntityManagerImpl {

    @PersistenceContext
    private final EntityManager entityManager;

    @Autowired
    public SessionsRepositoryEntityManagerImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

   @Autowired
    public List<Session> findAll() {
        return entityManager.createQuery("from Sessions").getResultList();
    }
}
