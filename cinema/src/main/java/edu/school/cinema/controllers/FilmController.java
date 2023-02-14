package edu.school.cinema.controllers;


import edu.school.cinema.models.JsonResponse;
import edu.school.cinema.services.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.*;
import edu.school.cinema.models.Film;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.annotation.MultipartConfig;
import java.io.File;
import java.io.IOException;
import java.util.UUID;
//A movie page contains a list of all movies created by an administrator. An
//        administrator can add a movie. For each movie, the title, year of release,
//        age restrictions, and a description are specified. It is also possible to upload
//        a poster for a movie.



@Controller
@RequestMapping("/admin/panel/films/")
public class FilmController {
    private static final String UPLOAD_DIRECTORY ="/images";
    private final FilmService FilmDAO;

  @Autowired
  public FilmController(FilmService filmDAO) {
    FilmDAO = filmDAO;
}

    @Value("${upload.path}")
    private String uploadPath;
    @GetMapping()
    public String show(Model model)
    {
        model.addAttribute("films" , FilmDAO.findAll());
        return "films/show_all";
    }


//    @GetMapping()
//    public String show(Model model)
//    {
//        model.addAttribute("films" , FilmDAO.findAll());
//        return "AddUser";
//    }



    @PostMapping("/{id}/save_image")
    public String save_image(@PathVariable("id") int id, @RequestParam("file") MultipartFile multipartFile) throws IOException
    {


        if (multipartFile != null && !multipartFile.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + multipartFile.getOriginalFilename();

            multipartFile.transferTo(new File(uploadPath + "/" + resultFilename));
            Film film  = FilmDAO.getById(id);
            film.setPoster_for_a_movie(resultFilename);
            FilmDAO.merge(film);
//            message.setFilename(resultFilename);
        }
        return "redirect:/admin/panel/films/";
    }
//    films/11/save_image

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
    public String create_new_film (@ModelAttribute("film") Film film, @RequestParam("file") MultipartFile multipartFile) throws IOException {
        System.out.println(multipartFile);
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        film.setPoster_for_a_movie(fileName);

        FilmDAO.save(film);
        String uploadDir = "user-photos/" + film.getId();
        System.out.println(uploadDir);
        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
        return "redirect:/admin/panel/films/";
    }


    @DeleteMapping("/{id}/delete")
    public String delete_film(@PathVariable("id") int id)
    {
        FilmDAO.delete(id);
        return "redirect:/admin/panel/films/";
    }
}
