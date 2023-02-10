package edu.school.cinema.listeners;


import edu.school.cinema.models.Message;
import edu.school.cinema.filters.MessageType;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;



@Component
public class WebSocketEventListener {


    private static  final Logger LOGGER = LoggerFactory.getLogger(WebSocketEventListener.class);
//

    private final SimpMessageSendingOperations sendingOperations;


    @Autowired
    public WebSocketEventListener(SimpMessageSendingOperations sendingOperations) {
        this.sendingOperations = sendingOperations;
    }
//
//

    @EventListener
    public void handleWebSocketConnectListener(final SessionConnectedEvent event)
    {
        LOGGER.info("Пук пук - появился коннект");
    }

    @EventListener
    public void handleWebSocketDisConnectListener(final SessionConnectedEvent event)
    {
        final StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        final String username = (String) headerAccessor.getSessionAttributes().get("username");
        final Message message = new Message(MessageType.DISCONNECT, username);
//        LOGGER.info("Пук пук - коннект пропал");
        sendingOperations.convertAndSend("/topic/public", message);
    }
}
