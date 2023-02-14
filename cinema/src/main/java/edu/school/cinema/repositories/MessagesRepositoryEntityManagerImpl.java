package edu.school.cinema.repositories;

import edu.school.cinema.models.Hall;
import edu.school.cinema.models.Message;
import edu.school.cinema.models.Session;
import edu.school.cinema.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class MessagesRepositoryEntityManagerImpl {

    @PersistenceContext
    private final EntityManager entityManager;

    @Autowired
    public MessagesRepositoryEntityManagerImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Autowired
    public List<Message> findAll() {
        return entityManager.createQuery("from Message", Message.class).getResultList();
    }

    public void save(Message message) {

//        entityManager.detach(session.getHall());
//        entityManager.detach(session.getFilm());

        entityManager.merge(message);

    }

    public Message findById(int id) {
        return entityManager.find(Message.class, id);
    }

    public void delete(Message message) {
        entityManager.remove(message);
    }

    public int saveUser(User newUser) {
        entityManager.persist(newUser);
//        System.out.println(newUser.getId() + " ---------------------");
        return newUser.getId();
    }



    public User getUserById(int user_id) {
       return  entityManager.find(User.class, user_id);
    }

    public void merge(User user) {
        entityManager.merge(user);
    }
}
