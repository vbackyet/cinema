package edu.school.cinema.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

//A movie page contains a list of all movies created by an administrator. An
//        administrator can add a movie. For each movie, the title, year of release,
//        age restrictions, and a description are specified. It is also possible to upload
//        a poster for a movie.



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
