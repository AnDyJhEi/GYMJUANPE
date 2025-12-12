package com.gymjuanpe_app.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Subscription {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable=false)
  private Long userId;

  @Column(nullable=false)
  private Long planId;

  @Column(nullable=false)
  private LocalDate startDate;

  @Column(nullable=false)
  private LocalDate endDate;

  @Enumerated(EnumType.STRING)
  @Column(nullable=false)
  private SubscriptionStatus status;

  public Long getId(){return id;}
  public Long getUserId(){return userId;}
  public void setUserId(Long v){this.userId=v;}
  public Long getPlanId(){return planId;}
  public void setPlanId(Long v){this.planId=v;}
  public LocalDate getStartDate(){return startDate;}
  public void setStartDate(LocalDate v){this.startDate=v;}
  public LocalDate getEndDate(){return endDate;}
  public void setEndDate(LocalDate v){this.endDate=v;}
  public SubscriptionStatus getStatus(){return status;}
  public void setStatus(SubscriptionStatus v){this.status=v;}
}
