package com.gymjuanpe_app.controller;

import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/public")
public class PublicController {

  @GetMapping("/health")
  public Map<String, Object> health() {
    return Map.of(
      "ok", true,
      "service", "Gym Juanpe API",
      "time", new Date().toString()
    );
  }

  @GetMapping("/plans")
  public List<Map<String, Object>> plans() {
    return List.of(
      Map.of("id", 1, "name", "SMART", "price", 79.90, "durationDays", 30),
      Map.of("id", 2, "name", "BLACK", "price", 109.90, "durationDays", 30),
      Map.of("id", 3, "name", "FIT", "price", 69.90, "durationDays", 30)
    );
  }

  @GetMapping("/classes")
  public List<Map<String, Object>> classes() {
    return List.of(
      Map.of("id", 1, "title", "Full Body", "trainer", "Juan", "time", "07:00", "capacity", 20, "reserved", 6),
      Map.of("id", 2, "title", "Spinning", "trainer", "Pepe", "time", "18:00", "capacity", 25, "reserved", 12),
      Map.of("id", 3, "title", "Funcional", "trainer", "Ana", "time", "20:00", "capacity", 15, "reserved", 10)
    );
  }
}
