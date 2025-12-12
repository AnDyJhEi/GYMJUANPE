package com.gymjuanpe_app.model;

import jakarta.persistence.*;

@Entity
public class Trainer {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable=false)
  private String name;

  private String specialty;

  public Long getId(){return id;}
  public String getName(){return name;}
  public void setName(String v){this.name=v;}
  public String getSpecialty(){return specialty;}
  public void setSpecialty(String v){this.specialty=v;}
}
