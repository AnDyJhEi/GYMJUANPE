package com.gymjuanpe_app.controller;

import com.gymjuanpe_app.model.User;
import com.gymjuanpe_app.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UsersController {
  private final UserRepository repo;
  public UsersController(UserRepository repo){this.repo=repo;}

  @GetMapping
  public List<User> all(){ return repo.findAll(); }
}
