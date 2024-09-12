package io.spring.client.api;

import io.spring.client.service.MessageService;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @PostMapping("/messages")
    public String sendMessage(@RequestParam("message") String message) {
        return messageService.sendMessage(message);
    }

    @GetMapping("/")
    @Hidden
    public ResponseEntity<String> redirectToHtml() {
        return ResponseEntity.status(HttpStatus.FOUND)
                .header("Location", "swagger-ui.html")
                .build();
    }
}
