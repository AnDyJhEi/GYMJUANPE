package com.gymjuanpe_app.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Reservation {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable=false)
  private Long classId;

  @Column(nullable=false)
  private Long userId;

  @Column(nullable=false)
  private LocalDateTime createdAt;

  public Long getId(){return id;}
  public Long getClassId(){return classId;}
  public void setClassId(Long v){this.classId=v;}
  public Long getUserId(){return userId;}
  public void setUserId(Long v){this.userId=v;}
  public LocalDateTime getCreatedAt(){return createdAt;}
  public void setCreatedAt(LocalDateTime v){this.createdAt=v;}
}
