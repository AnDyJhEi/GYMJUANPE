package com.gymjuanpe_app.controller;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/public")
public class PublicController {

  @GetMapping("/health")
  public Map<String, Object> health() {
    return Map.of("status", "OK", "service", "Gym Juanpe API");
  }

  @GetMapping("/plans")
  public List<PlanDto> plans() {
    return List.of(
      new PlanDto(1L, "Basic", 60.0, 30),
      new PlanDto(2L, "Premium", 120.0, 30),
      new PlanDto(3L, "Annual", 999.0, 365)
    );
  }

  @GetMapping("/classes")
  public List<ClassDto> classes() {
    return List.of(
      new ClassDto(1L, "Functional Training", "Juan Perez", "08:00 AM", 20, 7),
      new ClassDto(2L, "Spinning", "Maria Lopez", "06:00 PM", 15, 10),
      new ClassDto(3L, "Cross Training", "Carlos Ruiz", "07:00 PM", 18, 18)
    );
  }

  // ==== DTOs (para que el JSON tenga EXACTO los campos que tu HTML usa) ====
  public record PlanDto(Long id, String name, Double price, Integer durationDays) {}
  public record ClassDto(Long id, String title, String trainer, String time, Integer capacity, Integer reserved) {}
}
