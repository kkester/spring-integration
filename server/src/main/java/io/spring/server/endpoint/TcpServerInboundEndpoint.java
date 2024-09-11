package io.spring.server.endpoint;

import io.spring.server.service.TcpServerInboundService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.ip.IpHeaders;
import org.springframework.messaging.Message;

import java.nio.charset.StandardCharsets;

import static io.spring.server.config.TcpServerConfig.MESSAGE_CHANNEL;

@MessageEndpoint
@RequiredArgsConstructor
@Slf4j
public class TcpServerInboundEndpoint {

    private final TcpServerInboundService inboundService;

    @ServiceActivator(inputChannel = MESSAGE_CHANNEL, requiresReply = "true")
    public byte[] onMessage(Message<byte[]> message) {
        log.info("received message with connection id {}",
                message.getHeaders().get(IpHeaders.CONNECTION_ID));

        byte[] bytePayload = message.getPayload();
        String payloadString = new String(bytePayload, StandardCharsets.UTF_8);
        return inboundService.processMessage(payloadString).name().getBytes();
    }

}
