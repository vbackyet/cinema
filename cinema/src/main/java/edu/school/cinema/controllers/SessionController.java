package edu.school.cinema.controllers;



//A page for working with movie shows. An administrator can create a session
//        for a certain movie in a certain movie hall at a required time. An administrator
//        should be able to indicate a ticket cost. You should implement loading of all
//        movie and movie hall data as attributes onto the page for subsequent selection
//        by an administrator.

import edu.school.cinema.models.Film;
import edu.school.cinema.models.Hall;
import edu.school.cinema.models.Session;
import edu.school.cinema.services.FilmService;
import edu.school.cinema.services.HallService;
import edu.school.cinema.services.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin/panel/sessions/")
public class SessionController {

    private final SessionService SessionDAO;
    private final HallService HallDAO;
    private final FilmService FilmDAO;

    @Autowired
    public SessionController(SessionService sessionDAO, HallService hallDAO, FilmService filmDAO) {
        SessionDAO = sessionDAO;
        HallDAO = hallDAO;
        FilmDAO = filmDAO;
    }

    @GetMapping()
    public String show(Model model)
    {
        System.out.println("here we go");
        System.out.println(SessionDAO.findAll().get(0).getCost());

        model.addAttribute("sessions" , SessionDAO.findAll());
        model.addAttribute("films" , FilmDAO.findAll());
        return "sessions/show_all";
    }



    @GetMapping("/new")
    public String new_session(@ModelAttribute("session") Session session,
                              @ModelAttribute("film") Film film,
                              @ModelAttribute("hall") Hall hall,
                              Model model)
    {
        model.addAttribute("films", FilmDAO.findAll());
        model.addAttribute("halls", HallDAO.findAll());
        return "sessions/new";
    }

    @PostMapping("/new")
    public String new_session(
            @ModelAttribute("session") Session session,
                                @ModelAttribute("film") Film film,
                                @ModelAttribute("hall") Hall hall
    ) throws ParseException {
        System.out.println(hall.getId() + " !!!!!!!!!!!!!!!!!!");
        System.out.println(film.getId() + " !!!!!!!!!!!!!!!!!!");
        SessionDAO.save(session, film.getId(), hall.getId());
        return "redirect:/admin/panel/sessions/";
    }


//
//
//    @GetMapping("/new")
//    public String create_new(@ModelAttribute("hall") Hall hall)
//    {
//        return "halls/new";
//    }
//
//    @PostMapping("/new")
//    public String create_new_hall(@ModelAttribute("hall") Hall hall)
//    {
//        SessionDAO.save(hall);
//        return "redirect:/admin/panel/halls/";
//    }
}
