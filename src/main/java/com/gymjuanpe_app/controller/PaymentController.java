package com.gymjuanpe_app.controller;

import com.gymjuanpe_app.model.Payment;
import com.gymjuanpe_app.repository.PaymentRepository;
import com.gymjuanpe_app.service.PaymentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

  private final PaymentService service;
  private final PaymentRepository repo;

  public PaymentController(PaymentService service, PaymentRepository repo){
    this.service=service; this.repo=repo;
  }

  @PostMapping("/pay")
  public Payment pay(@RequestBody Map<String,Object> body){
    Long userId = ((Number)body.get("userId")).longValue();
    Long planId = ((Number)body.get("planId")).longValue();
    Double amount = ((Number)body.get("amount")).doubleValue();
    return service.pay(userId, planId, amount);
  }

  @GetMapping("/user/{userId}")
  public List<Payment> byUser(@PathVariable Long userId){
    return repo.findByUserId(userId);
  }
}
