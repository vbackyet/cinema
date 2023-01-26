package edu.school.cinema.controllers;



//A page for working with movie shows. An administrator can create a session
//        for a certain movie in a certain movie hall at a required time. An administrator
//        should be able to indicate a ticket cost. You should implement loading of all
//        movie and movie hall data as attributes onto the page for subsequent selection
//        by an administrator.

import edu.school.cinema.models.Hall;
import edu.school.cinema.repositories.SessionsRepositoryEntityManagerImpl;
import edu.school.cinema.services.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/panel/sessions/")
public class SessionController {

    private final SessionService SessionDAO;

    @Autowired
    public SessionController(SessionService sessionDAO) {
        SessionDAO = sessionDAO;
    }

    @GetMapping()
    public String show(Model model)
    {
        System.out.println("here we go");
        model.addAttribute("sessions" , SessionDAO.findAll());
        return "sessions/show_all";
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
