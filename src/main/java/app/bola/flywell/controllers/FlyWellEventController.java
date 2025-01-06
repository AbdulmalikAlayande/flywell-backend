package app.bola.flywell.controllers;

import app.bola.flywell.broker.FlyWellPublisher;
import app.bola.flywell.dto.response.CustomerResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/producer")
@AllArgsConstructor
public class FlyWellEventController {

    final FlyWellPublisher flyWellPublisher;

    @PostMapping("/publish-customer-event")
    public ResponseEntity<CustomerResponse> publishEvent() {
        return ResponseEntity.ok(flyWellPublisher.sendMessage());
    }
}
