package edu.school.cinema.services;

import edu.school.cinema.filters.MessageDTO;
import edu.school.cinema.models.Message;
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

    public void save(MessageDTO message) {
        Message new_message = new Message();
        new_message.setFilm(FilmDAO.getById(Math.toIntExact(message.getFilm_id())));
        new_message.setContent(message.getMessage());
        new_message.setTime(message.getDate());
        new_message.setSender(message.getUser_name());

        MessageDAO.save(new_message);
    }

    public Message getById(int id) {
        return MessageDAO.findById(id);
    }

    public void delete(int id) {
        Message film = MessageDAO.findById(id);
        MessageDAO.delete(film);

    }


//    public void merge(Film film) {
//        FilmDAO.merge(film);
//    }
}

