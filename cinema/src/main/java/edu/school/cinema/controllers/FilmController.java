package edu.school.cinema.controllers;


import edu.school.cinema.services.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import edu.school.cinema.models.Film;
//A movie page contains a list of all movies created by an administrator. An
//        administrator can add a movie. For each movie, the title, year of release,
//        age restrictions, and a description are specified. It is also possible to upload
//        a poster for a movie.



@Controller
@RequestMapping("/admin/panel/films/")
public class FilmController {

      private final FilmService FilmDAO;

      @Autowired
      public FilmController(FilmService filmDAO) {
        FilmDAO = filmDAO;
    }

    @GetMapping()
    public String show(Model model)
    {
        model.addAttribute("films" , FilmDAO.findAll());
        return "films/show_all";
    }


    @GetMapping("/new")
    public String create_new(@ModelAttribute("film") Film film)
    {
        return "films/new";
    }
    @GetMapping("/{id}")
    public String show_one(Model model, @PathVariable("id") int id)
    {

        Film film = FilmDAO.getById(id);
        model.addAttribute("film", film);

        return "films/show_one";
    }

    @PostMapping("/new")
    public String create_new_film(@ModelAttribute("film") Film film)
    {
        FilmDAO.save(film);
        return "redirect:/admin/panel/films/";
    }

    @DeleteMapping("/{id}/delete")
    public String delete_film(@PathVariable("id") int id)
    {
        FilmDAO.delete(id);
        return "redirect:/admin/panel/films/";
    }
}
