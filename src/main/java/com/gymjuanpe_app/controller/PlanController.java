package com.gymjuanpe_app.controller;

import com.gymjuanpe_app.model.Plan;
import com.gymjuanpe_app.repository.PlanRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/plans")
public class PlanController {

  private final PlanRepository repo;
  public PlanController(PlanRepository repo){this.repo=repo;}

  @PostMapping
  public Plan create(@RequestBody Plan p){ return repo.save(p); }

  @GetMapping
  public List<Plan> all(){ return repo.findAll(); }
}
