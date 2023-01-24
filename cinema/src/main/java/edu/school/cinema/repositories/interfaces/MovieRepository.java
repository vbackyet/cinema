package edu.school.cinema.repositories.interfaces;

public interface MovieRepository {
    List<Movie> findAll();

    @Transactional
    void save(Message entity);
}
