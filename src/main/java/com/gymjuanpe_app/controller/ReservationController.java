package com.gymjuanpe_app.controller;

import com.gymjuanpe_app.model.Reservation;
import com.gymjuanpe_app.repository.ReservationRepository;
import com.gymjuanpe_app.service.ReservationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

  private final ReservationService service;
  private final ReservationRepository repo;

  public ReservationController(ReservationService service, ReservationRepository repo){
    this.service=service; this.repo=repo;
  }

  @PostMapping("/reserve")
  public Reservation reserve(@RequestBody Map<String,Object> body){
    Long classId = ((Number)body.get("classId")).longValue();
    Long userId = ((Number)body.get("userId")).longValue();
    return service.reserve(classId, userId);
  }

  @GetMapping("/class/{classId}")
  public List<Reservation> byClass(@PathVariable Long classId){
    return repo.findByClassId(classId);
  }
}
