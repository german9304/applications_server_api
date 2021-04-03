package com.application.server.health;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    private final String api = "/api/health";

    @GetMapping(api)
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok().body("success");
    }
}
