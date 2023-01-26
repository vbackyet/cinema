package edu.school.cinema.controllers;


import edu.school.cinema.models.Film;
import edu.school.cinema.models.Hall;
import edu.school.cinema.services.HallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


//The page for working with movie halls contains a list of all movie halls created
//        by an administrator. The administrator can create a movie hall with a certain
//        configuration. To each movie hall, a serial number and number of seats are
//        assigned.
@Controller
@RequestMapping("/admin/panel/halls/")
public class HallController {


    private final HallService HallDAO;

    @Autowired
    public HallController(HallService hallDAO) {
        HallDAO = hallDAO;
    }


    @GetMapping()
    public String show(Model model)
    {
        model.addAttribute("halls" , HallDAO.findAll());
        return "halls/show_all";
    }


    @GetMapping("/new")
    public String create_new(@ModelAttribute("hall") Hall hall)
    {
        return "halls/new";
    }

    @PostMapping("/new")
    public String create_new_hall(@ModelAttribute("hall") Hall hall)
    {
        HallDAO.save(hall);
        return "redirect:/admin/panel/halls/";
    }
}
