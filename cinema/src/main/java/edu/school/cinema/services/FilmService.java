package edu.school.cinema.services;


import edu.school.cinema.models.Film;
import edu.school.cinema.repositories.FilmsRepositoryEntityManagerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class FilmService {

    private final FilmsRepositoryEntityManagerImpl FilmDAO;


    @Autowired
    public FilmService(FilmsRepositoryEntityManagerImpl filmDAO) {
        FilmDAO = filmDAO;
    }
    public List<Film> findAll() {
        return FilmDAO.findAll();
    }

    public void save(Film film) {
        FilmDAO.save(film);
    }

    public Film getById(int id) {
       return FilmDAO.getById(id);
    }

    public void delete(int id) {
        Film film = FilmDAO.findById(id);
        FilmDAO.delete(film);

    }


    public void merge(Film film) {
        FilmDAO.merge(film);
    }
}

