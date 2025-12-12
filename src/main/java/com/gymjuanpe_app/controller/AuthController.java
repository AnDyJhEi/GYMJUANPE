package com.gymjuanpe_app.controller;

import com.gymjuanpe_app.model.User;
import com.gymjuanpe_app.repository.UserRepository;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

  private final UserRepository repo;
  public AuthController(UserRepository repo){ this.repo = repo; }

  @PostMapping("/register")
  public ResponseEntity<?> register(@RequestBody User user){
    if(user.getUsername()==null || user.getUsername().trim().isEmpty())
      return ResponseEntity.badRequest().body(Map.of("message","Username obligatorio"));
    if(user.getPassword()==null || user.getPassword().trim().isEmpty())
      return ResponseEntity.badRequest().body(Map.of("message","Password obligatorio"));
    if(user.getRole()==null)
      return ResponseEntity.badRequest().body(Map.of("message","Role obligatorio"));

    if(repo.findByUsername(user.getUsername()).isPresent())
      return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("message","Username ya existe"));

    return ResponseEntity.ok(repo.save(user));
  }

  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody Map<String,String> body){
    String u = body.get("username");
    String p = body.get("password");
    User user = repo.findByUsername(u).orElse(null);
    if(user==null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message","Usuario no existe"));
    if(!user.getPassword().equals(p)) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message","Password incorrecto"));
    return ResponseEntity.ok(Map.of("message","Login OK","userId",user.getId(),"role",user.getRole()));
  }
}
