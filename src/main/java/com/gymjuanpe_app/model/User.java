package com.gymjuanpe_app.model;

import jakarta.persistence.*;

@Entity
@Table(name="users")
public class User {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(unique = true, nullable = false)
  private String username;

  @Column(nullable = false)
  private String password;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private Role role;

  // getters/setters
  public Long getId(){return id;}
  public String getUsername(){return username;}
  public void setUsername(String u){this.username=u;}
  public String getPassword(){return password;}
  public void setPassword(String p){this.password=p;}
  public Role getRole(){return role;}
  public void setRole(Role r){this.role=r;}
}
