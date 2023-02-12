package edu.school.cinema.controllers;


import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.Gson;
import edu.school.cinema.filters.MessageDTO;
import edu.school.cinema.models.Film;
import edu.school.cinema.models.Hall;
import edu.school.cinema.models.Message;
import edu.school.cinema.models.Session;
import edu.school.cinema.services.HallService;
import edu.school.cinema.services.MessageService;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
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

import java.util.List;

@Controller

@RequestMapping()
public class MessageController {
    private final MessageService MessageDAO;
    private final HallService HallDAO;

    @Autowired
    public MessageController(MessageService messageDAO, HallService hallDAO) {

        MessageDAO = messageDAO;
        HallDAO = hallDAO;
    }

    @MessageMapping("/chat.send/{id}")
    @SendTo("/topic/public/{id}")
    public MessageDTO sendMessage(final MessageDTO message,
    @DestinationVariable String id)
    {
        System.out.println(message  + " <---------- My message ");
        MessageDAO.save(message);
        return message;
    }

    @MessageMapping("/chat.newUser/{id}")
    @SendTo("/topic/public/{id}")
    public MessageDTO newUser(final MessageDTO message,
                              SimpMessageHeaderAccessor headerAccessor,
                              @DestinationVariable String id)
    {
        System.out.println(message + "<= message");
        System.out.println(id + "<= id");
        headerAccessor.getSessionAttributes().put("username", message.getUser_name());
        return message;
    }

    @ResponseBody
    @GetMapping(value = "/messages/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> search(@RequestParam("filmName") String filmName) {
        System.out.println("BUGAGA");
        List<Message> sessions = MessageDAO.findAll();
        System.out.println(sessions);
        return new ResponseEntity<>(sessions, HttpStatus.OK);
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
