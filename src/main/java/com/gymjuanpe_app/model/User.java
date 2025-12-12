package com.gymjuanpe_app.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank
  @Size(max = 50)
  @Column(unique = true, nullable = false)
  private String username;

  @NotBlank
  @Size(max = 100)
  @Column(nullable = false)
  private String password;

  @NotBlank
  @Size(max = 30)
  @Column(nullable = false)
  private String role; // ADMIN, CLIENT, RECEPTIONIST

  public User() {}

  public Long getId() { return id; }
  public String getUsername() { return username; }
  public String getPassword() { return password; }
  public String getRole() { return role; }

  public void setId(Long id) { this.id = id; }
  public void setUsername(String username) { this.username = username; }
  public void setPassword(String password) { this.password = password; }
  public void setRole(String role) { this.role = role; }
}
