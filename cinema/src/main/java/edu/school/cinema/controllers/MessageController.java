package edu.school.cinema.controllers;


import edu.school.cinema.filters.MessageDTO;
import edu.school.cinema.models.Film;
import edu.school.cinema.models.Message;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping()
public class MessageController {
    @MessageMapping("/chat.send/{id}")
    @SendTo("/topic/public/{id}")
    public MessageDTO sendMessage(final MessageDTO message,
    @DestinationVariable String id)
    {
//        message.setFilm();
        System.out.println(id);
        System.out.println(message);
        return message;
    }

    @MessageMapping("/chat.newUser/{id}")
    @SendTo("/topic/public/{id}")
    public MessageDTO newUser(final MessageDTO message,
                              SimpMessageHeaderAccessor headerAccessor,
                              @DestinationVariable String id)
    {
        System.out.println(message);
        System.out.println(id);
        headerAccessor.getSessionAttributes().put("username", message.getUser_name());
        return message;
    }



    @GetMapping("/films/{id}/chat/")
    public String show(Model model, @PathVariable("id") int id)
    {
        Film film = new Film();
        film.setId(id);
        model.addAttribute("film", film);
        return "static/index";
    }

}
