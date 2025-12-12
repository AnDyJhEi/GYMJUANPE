package com.gymjuanpe_app.controller;

import com.gymjuanpe_app.model.GymClass;
import com.gymjuanpe_app.repository.ClassRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/classes")
public class ClassController {
  private final ClassRepository repo;
  public ClassController(ClassRepository repo){this.repo=repo;}

  @PostMapping
  public GymClass create(@RequestBody GymClass c){ return repo.save(c); }

  @GetMapping
  public List<GymClass> all(){ return repo.findAll(); }
}
