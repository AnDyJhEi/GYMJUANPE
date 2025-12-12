package com.gymjuanpe_app.model;

import jakarta.persistence.*;

@Entity
public class GymClass {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable=false)
  private String title;

  @Column(nullable=false)
  private String schedule; // "Lun 7pm"

  @Column(nullable=false)
  private Integer capacity;

  @Column(nullable=false)
  private Integer reservedCount = 0;

  @Column(nullable=false)
  private Long trainerId;

  public Long getId(){return id;}
  public String getTitle(){return title;}
  public void setTitle(String v){this.title=v;}
  public String getSchedule(){return schedule;}
  public void setSchedule(String v){this.schedule=v;}
  public Integer getCapacity(){return capacity;}
  public void setCapacity(Integer v){this.capacity=v;}
  public Integer getReservedCount(){return reservedCount;}
  public void setReservedCount(Integer v){this.reservedCount=v;}
  public Long getTrainerId(){return trainerId;}
  public void setTrainerId(Long v){this.trainerId=v;}
}
