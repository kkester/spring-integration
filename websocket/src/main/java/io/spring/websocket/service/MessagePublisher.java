package io.spring.websocket.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class MessagePublisher {

    private final RabbitTemplate rabbitTemplate;
    private final SimpMessageSendingOperations messagingTemplate;

    public boolean sendMessage(String message) {
        try {
//            rabbitTemplate.convertAndSend("your-exchange", "your-routing-key", message);
            messagingTemplate.convertAndSend("/topic/public", message);
        } catch (Exception e) {
            log.error("Error occurred sending message", e);
            return false;
        }
        return true;
    }
}
