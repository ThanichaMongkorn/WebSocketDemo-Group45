package com.websocket.demo.config;
;
import com.websocket.demo.chat.ChatMessage;
import com.websocket.demo.chat.MessageType;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
@RequiredArgsConstructor
public class WebSocketEventListener {

    private final SimpMessageSendingOperations messagingTemplate;
    @EventListener
    public void disconnect(SessionDisconnectEvent event){
        SimpMessageHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        String username = (String) headerAccessor.getSessionAttributes().get("username");
        var chatMessage = ChatMessage.builder()
                .sender(username)
                .type(MessageType.LEAVE)
                .onlineUser(ChatMessage.leaveUser())
                .build();
        messagingTemplate.convertAndSend("/topic/public", chatMessage);
    }
}
