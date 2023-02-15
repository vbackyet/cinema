package edu.school.cinema.controllers;


import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.Gson;
import edu.school.cinema.filters.MessageDTO;
import edu.school.cinema.filters.MessageType;
import edu.school.cinema.models.*;
import edu.school.cinema.services.HallService;
import edu.school.cinema.services.MessageService;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller

@RequestMapping()
public class MessageController {
    private final MessageService MessageDAO;
    private final HallService HallDAO;


    @Value("${upload.path_avatar}")
    private String uploadPath;

    @Autowired
    public MessageController(MessageService messageDAO, HallService hallDAO) {

        MessageDAO = messageDAO;
        HallDAO = hallDAO;
    }

    @MessageMapping("/chat.send/{id}")
    @SendTo("/topic/public/{id}")
    public MessageDTO sendMessage(final MessageDTO message, SimpMessageHeaderAccessor headerAccessor,
    @DestinationVariable String id)
    {
        System.out.println(message  + " <---------- My message ");
//        Integer user_id = (Integer) headerAccessor.getSessionAttributes().get("user_id");
        Message myMessage = MessageDAO.save(message);
        message.setUser_name(MessageDAO.getUserById(message.getUser_id()).getUsername());
        message.setImage(MessageDAO.getUserById(message.getUser_id()).getUser_avatar());
        System.out.println(message  + " <---------- My message ");
        return message;
    }

    @MessageMapping("/chat.newUser/{id}")
    @SendTo("/topic/public/{id}")
    public MessageDTO newUser(final MessageDTO message,
                              SimpMessageHeaderAccessor headerAccessor,
                              @DestinationVariable("id") String film_id)
    {
        if (message.getUser_name() == null)
        {
            System.out.println("RECCCCCOONNNECTTTTT!!!!    " + film_id);
            System.out.println(message + " %%%%%%%%%%%%%%%%%%%%");
            message.setUser_name(MessageDAO.getUserById(message.getUser_id()).getUsername());
            message.setFilm_id(Long.valueOf(film_id));
            System.out.println(message + " %%%%%%%%%%%%%%%%%%%%");
            return message;
        }
        System.out.println(message + "<= message");
        System.out.println(film_id + "<= id");
        User newUser = new User();
        newUser.setUsername(message.getUser_name());
        int user_id = MessageDAO.saveUser(newUser);
        message.setUser_id(user_id);
        return message;
    }

    @ResponseBody
    @GetMapping(value = "/messages/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> search(@RequestParam("filmName") String filmName) {
//        List<Message> messages = MessageDAO.findAll();
        List<Message> messages = MessageDAO.findAllByFilmId(Integer.parseInt(filmName));
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }


    @PostMapping(value = "/chat/save/{user_id}/{film_id}")
    public String save(@PathVariable ("user_id") String id,
                       @PathVariable ("film_id") String film_id,
                       @RequestParam("file") MultipartFile multipartFile) throws IOException {
        if (multipartFile != null && !multipartFile.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + multipartFile.getOriginalFilename();

            multipartFile.transferTo(new File(uploadPath + "/" + resultFilename));


            User user  = MessageDAO.getUserById(Integer.parseInt(id));
            user.setUser_avatar(resultFilename);
            MessageDAO.merge(user);
//            message.setFilename(resultFilename);
        }
        return "redirect:/films/{film_id}/chat/";
    }

    @GetMapping("/films/{id}/chat/")
    public String show(Model model, @PathVariable("id") int id)
    {

        List<Message> messages = MessageDAO.findAll();
        Film film = new Film();
        film.setId(id);
        model.addAttribute("film", film);

//        JSONArray.toJSONString(messages);
        model.addAttribute("messages",  new JSONArray(messages));
        return "static/index";
    }

}
