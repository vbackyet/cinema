package edu.school.cinema.services;


import edu.school.cinema.models.Hall;
import edu.school.cinema.models.Session;
import edu.school.cinema.repositories.SessionsRepositoryEntityManagerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class SessionService {

    private final SessionsRepositoryEntityManagerImpl SessionDAO;

    @Autowired
    public SessionService(SessionsRepositoryEntityManagerImpl sessionDAO) {
        SessionDAO = sessionDAO;
    }

    public List<Session> findAll() {
        return SessionDAO.findAll();
    }
}
