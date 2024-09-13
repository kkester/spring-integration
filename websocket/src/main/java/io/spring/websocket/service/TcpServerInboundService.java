package io.spring.websocket.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TcpServerInboundService {

    private final MessagePublisher messagePublisher;

    public ResultEnum processMessage(String payloadString) {
        boolean messagesDelivered = messagePublisher.sendMessage(payloadString);
        return messagesDelivered ? ResultEnum.DELIVERED : ResultEnum.ERROR;
    }
}
