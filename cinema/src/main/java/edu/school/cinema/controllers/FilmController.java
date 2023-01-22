package edu.school.cinema.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/panel/films/")
public class FilmController {

    @GetMapping()
    public String show()
    {
        System.out.println("puk");
        return "films/show_all";
    }
}
