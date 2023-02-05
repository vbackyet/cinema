package edu.school.cinema.controllers;


import edu.school.cinema.filters.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping()
public class MessageController {
    @MessageMapping("/chat.send")
    @SendTo("/topic/public")
    public Message sendMessage(final Message message)
    {
        System.out.println(message.getContent());
        return message;
    }

    @MessageMapping("/chat.newUser")
    @SendTo("/topic/public")
    public Message newUser(final Message message,
                           SimpMessageHeaderAccessor headerAccessor)
    {
        System.out.println(message.getSender());
        headerAccessor.getSessionAttributes().put("username", message.getSender());
        return message;
    }

    @GetMapping("chat")
    public String show()
    {
        return "static/index";
    }

}
