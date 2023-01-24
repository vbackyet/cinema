package edu.school.cinema.repositories;


import edu.school.cinema.models.Film;
import edu.school.cinema.repositories.interfaces.MovieRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class MessagesRepositoryEntityManagerImpl implements MovieRepository {
    @PersistenceContext
    private EntityManager entityManager;



    @Override
    public List<Film> findAll() {
        return entityManager
                .createQuery("from Message", Film.class).getResultList();
    }
    @Override
    @Transactional
    public void save(Message entity) {
        entityManager.persist(entity);
    }
}