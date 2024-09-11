package io.spring.server.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Slf4j
@Service
public class TcpServerOutboundService {

    private final List<SseEmitter> emitters = new CopyOnWriteArrayList<>();

    public void addEmitter(SseEmitter sseEmitter) {
        emitters.add(sseEmitter);
        sseEmitter.onError((e) -> emitters.remove(sseEmitter));
        sseEmitter.onTimeout(() -> emitters.remove(sseEmitter));
    }

    public int emitMessage(String payloadString) {
        int emitterSize = emitters.size();
        log.info("Emitting message to {} listeners", emitterSize);
        List<SseEmitter> failedEmitters = new ArrayList<>();
        emitters.forEach(sseEmitter -> {
            try {
                sseEmitter.send(payloadString);
            } catch (IOException e) {
                failedEmitters.add(sseEmitter);
            }
        });
        return emitterSize - failedEmitters.size();
    }
}
