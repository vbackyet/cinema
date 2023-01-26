package edu.school.cinema.repositories.interfaces;

import edu.school.cinema.models.Film;

import java.util.List;

public interface MovieRepository {
    List<Film> findAll();
//    void save(Film entity);
}


