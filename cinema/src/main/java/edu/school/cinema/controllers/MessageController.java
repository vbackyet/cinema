package edu.school.cinema.controllers;


import edu.school.cinema.models.Message;
import org.springframework.messaging.converter.SimpleMessageConverter;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping()
public class MessageController {
    @MessageMapping("/chat.send")
    @SendTo("topic/public")
    public Message sendMessage(final Message message)
    {
        return message;
    }

    @MessageMapping("/chat.newUser")
    @SendTo("topic/public")
    public Message newUser(final Message message,
                           SimpMessageHeaderAccessor headerAccessor)
    {
        headerAccessor.getSessionAttributes().put("username", message.getUsername());
        return message;
    }

}
