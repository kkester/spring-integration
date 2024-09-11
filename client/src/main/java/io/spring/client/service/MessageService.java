package io.spring.client.service;

import io.spring.client.config.TcpClientConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final TcpClientConfig.TcpClientGateway tcpClientGateway;

    public String sendMessage(String message) {
        byte[] response = tcpClientGateway.send(String.valueOf(message).getBytes());
        return new String(response);
    }
}
