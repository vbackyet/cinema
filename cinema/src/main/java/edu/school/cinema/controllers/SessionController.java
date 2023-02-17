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
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

//    @GetMapping()
//    public String show(Model model)
//    {
//        model.addAttribute("films" , FilmDAO.findAll());
//        return "sessions/AddUser";
//    }

//    @PostMapping(produces={"application/json"})
//    @ResponseStatus(HttpStatus.OK)
//    @ResponseBody
//    public List<Session> listWithMarshalling()
//    {
////        model.addAttribute("films" , FilmDAO.findAll());
//        System.out.println(SessionDAO.findAll());
//        System.out.println("-----------------------------------------------");
//        return SessionDAO.findAll();
//    }

    @ResponseBody
    @GetMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> search(@RequestParam("filmName") String filmName) {
        System.out.println("BUGAGA");
        List<Session> sessions = SessionDAO.findAll();
        System.out.println(sessions);
        return new ResponseEntity<>(sessions, HttpStatus.OK);
    }

    @GetMapping("/for_user")
    public String listWithView(Model model) {
        // Call RESTful method to avoid repeating account lookup logic
        model.addAttribute("films" , FilmDAO.findAll());

        // Return the view to use for rendering the response
        return "sessions/AddUser";
    }
//    @RequestMapping(value="/accounts", produces={"application/xml", "application/json"})
//    @ResponseStatus(HttpStatus.OK)
//    public @ResponseBody List<Account> listWithMarshalling(Principal principal) {
//        return accountManager.getAccounts(principal);
//    }

    // View-based method
//    @RequestMapping("/accounts")
//    public String listWithView(Model model, Principal principal) {
//        // Call RESTful method to avoid repeating account lookup logic
//        model.addAttribute( listWithMarshalling(principal) );
//
//        // Return the view to use for rendering the response
//        return ¨accounts/list¨;
//    }


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
        System.out.println(session);
        SessionDAO.save(session, session.getFilm().getId(), session.getHall().getId());
        return "redirect:/admin/panel/sessions/";
    }
//    https://www.youtube.com/watch?v=U4lqTmFmbAM

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
