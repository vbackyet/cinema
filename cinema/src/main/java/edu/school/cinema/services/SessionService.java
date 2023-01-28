package edu.school.cinema.services;

import java.sql.Timestamp;
import edu.school.cinema.models.Film;
import edu.school.cinema.models.Hall;
import edu.school.cinema.models.Session;
import edu.school.cinema.repositories.FilmsRepositoryEntityManagerImpl;
import edu.school.cinema.repositories.HallsRepositoryEntityManagerImpl;
import edu.school.cinema.repositories.SessionsRepositoryEntityManagerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class SessionService {

    private final SessionsRepositoryEntityManagerImpl SessionDAO;
    private final FilmsRepositoryEntityManagerImpl FilmDAO;
    private final HallsRepositoryEntityManagerImpl HALLDAO;
    @Autowired
    public SessionService(SessionsRepositoryEntityManagerImpl sessionDAO, FilmsRepositoryEntityManagerImpl filmDAO, HallsRepositoryEntityManagerImpl halldao) {
        SessionDAO = sessionDAO;
        FilmDAO = filmDAO;
        HALLDAO = halldao;
    }

    public List<Session> findAll() {
        return SessionDAO.findAll();
    }

    public void save(Session session, int filmid, int hallId) throws ParseException {

//        DateFormat formatter = new SimpleDateFormat("yyyy-MM-ddThh-mm");
//        Date date = formatter.parse(session.getStartAt());
//        Timestamp timeStampDate = new Timestamp(date.getTime());


//        2023-01-11T16:10

//        session.setStartAt(timestamp);
        System.out.println(HALLDAO.getById(hallId));
        session.setStartAt(session.getStartAt());
        session.setFilm(FilmDAO.findById(filmid));
        session.setHall(HALLDAO.getById(hallId));
        SessionDAO.save(session);
        System.out.println(session);


    }
}
