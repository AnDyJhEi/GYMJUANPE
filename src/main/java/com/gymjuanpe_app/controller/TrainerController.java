package com.gymjuanpe_app.controller;

import com.gymjuanpe_app.model.Trainer;
import com.gymjuanpe_app.repository.TrainerRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trainers")
public class TrainerController {
  private final TrainerRepository repo;
  public TrainerController(TrainerRepository repo){this.repo=repo;}

  @PostMapping
  public Trainer create(@RequestBody Trainer t){ return repo.save(t); }

  @GetMapping
  public List<Trainer> all(){ return repo.findAll(); }
}
