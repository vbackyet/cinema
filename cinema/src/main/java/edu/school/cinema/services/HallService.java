package edu.school.cinema.services;


import edu.school.cinema.models.Hall;
import edu.school.cinema.repositories.HallsRepositoryEntityManagerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class HallService {

    private final HallsRepositoryEntityManagerImpl HALLDAO;

    @Autowired
    public HallService(HallsRepositoryEntityManagerImpl halldao) {
        HALLDAO = halldao;
    }

    public List<Hall> findAll() {
        System.out.println(HALLDAO.findAll());
        return HALLDAO.findAll();
    }

    public void save(Hall hall) {
        HALLDAO.save(hall);
    }
}
