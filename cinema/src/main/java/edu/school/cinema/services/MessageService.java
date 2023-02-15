package edu.school.cinema.services;

import edu.school.cinema.filters.MessageDTO;
import edu.school.cinema.models.Message;
import edu.school.cinema.models.User;
import edu.school.cinema.repositories.FilmsRepositoryEntityManagerImpl;
import edu.school.cinema.repositories.MessagesRepositoryEntityManagerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class MessageService {

    private final MessagesRepositoryEntityManagerImpl MessageDAO;
    private final FilmsRepositoryEntityManagerImpl FilmDAO;

    @Autowired
    public MessageService(MessagesRepositoryEntityManagerImpl messageDAO, FilmsRepositoryEntityManagerImpl filmDAO) {
        this.MessageDAO = messageDAO;
        FilmDAO = filmDAO;
    }

    public List<Message> findAll() {
        return MessageDAO.findAll();
    }

    public Message save(MessageDTO message) {
        Message new_message = new Message();
        new_message.setFilm(FilmDAO.getById(Math.toIntExact(message.getFilm_id())));
        new_message.setContent(message.getMessage());
        new_message.setTime(message.getDate());
        User my_user = MessageDAO.getUserById(message.getUser_id());
        new_message.setSender(my_user);
//        User new_user = new User(message.getUser_name());
//        new_message.setSender(new_user);

        MessageDAO.save(new_message);
        return new_message;

    }

    public Message getById(int id) {
        return MessageDAO.findById(id);
    }

    public User getUserById(int id) {
        return MessageDAO.getUserById(id);
    }

    public void delete(int id) {
        Message film = MessageDAO.findById(id);
        MessageDAO.delete(film);

    }

    public int saveUser(User newUser) {
        return MessageDAO.saveUser(newUser);

    }

    public void merge(User user) {
        MessageDAO.merge(user);

    }

    public List<Message> findAllByFilmId(int id) {
        return MessageDAO.findAllByFilmId(id);
    }


//    public void merge(Film film) {
//        FilmDAO.merge(film);
//    }
}

