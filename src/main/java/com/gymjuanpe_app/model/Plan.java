package com.gymjuanpe_app.model;

import jakarta.persistence.*;

@Entity
public class Plan {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable=false)
  private String name;

  @Column(nullable=false)
  private Integer durationDays;

  @Column(nullable=false)
  private Double price;

  public Long getId(){return id;}
  public String getName(){return name;}
  public void setName(String n){this.name=n;}
  public Integer getDurationDays(){return durationDays;}
  public void setDurationDays(Integer d){this.durationDays=d;}
  public Double getPrice(){return price;}
  public void setPrice(Double p){this.price=p;}
}
