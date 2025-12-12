package com.gymjuanpe_app.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Payment {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable=false)
  private Long userId;

  @Column(nullable=false)
  private Long planId;

  @Column(nullable=false)
  private Double amount;

  @Enumerated(EnumType.STRING)
  @Column(nullable=false)
  private PaymentStatus status;

  @Column(nullable=false)
  private LocalDateTime createdAt;

  private String receiptCode;

  public Long getId(){return id;}
  public Long getUserId(){return userId;}
  public void setUserId(Long v){this.userId=v;}
  public Long getPlanId(){return planId;}
  public void setPlanId(Long v){this.planId=v;}
  public Double getAmount(){return amount;}
  public void setAmount(Double v){this.amount=v;}
  public PaymentStatus getStatus(){return status;}
  public void setStatus(PaymentStatus v){this.status=v;}
  public LocalDateTime getCreatedAt(){return createdAt;}
  public void setCreatedAt(LocalDateTime v){this.createdAt=v;}
  public String getReceiptCode(){return receiptCode;}
  public void setReceiptCode(String v){this.receiptCode=v;}
}
