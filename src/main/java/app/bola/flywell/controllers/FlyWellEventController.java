package app.bola.flywell.controllers;

import app.bola.flywell.broker.pulsar.SimpleEventPublisher;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/producer")
@AllArgsConstructor
public class FlyWellEventController {

    final SimpleEventPublisher eventPublisher;

    @PostMapping("/publish-customer-event/{message}")
    public ResponseEntity<?> publishEvent(@PathVariable String message) {
        eventPublisher.publishPlainMessage(message);
        return ResponseEntity.ok("Message Published");
    }

    @GetMapping("/publish-text-event/{message}")
    public ResponseEntity<?> publishTextEvent(@PathVariable String message) {
        eventPublisher.publishPlainMessage(message);
        return ResponseEntity.ok("Message Published");
    }
}
