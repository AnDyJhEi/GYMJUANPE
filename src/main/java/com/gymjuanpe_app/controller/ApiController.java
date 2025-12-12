package com.gymjuanpe_app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class ApiController {

    @GetMapping("/api")
    public Map<String, Object> api() {
        return Map.of(
                "app", "Gym Juanpe",
                "status", "OK"
        );
    }
}
