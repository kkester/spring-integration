package io.spring.server.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TcpServerInboundService {

    private final TcpServerOutboundService outboundService;

    public ResultEnum processMessage(String payloadString) {
        int messagesDelivered = outboundService.emitMessage(payloadString);
        return messagesDelivered > 0 ? ResultEnum.DELIVERED : ResultEnum.ERROR;
    }
}
