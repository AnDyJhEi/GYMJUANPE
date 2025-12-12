package com.gymjuanpe_app.controller;

import com.gymjuanpe_app.model.Subscription;
import com.gymjuanpe_app.model.SubscriptionStatus;
import com.gymjuanpe_app.repository.SubscriptionRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Map;

@RestController
@RequestMapping("/api/subscriptions")
public class SubscriptionController {

  private final SubscriptionRepository repo;
  public SubscriptionController(SubscriptionRepository repo){this.repo=repo;}

  @GetMapping("/user/{userId}")
  public Object byUser(@PathVariable Long userId){
    Subscription sub = repo.findTopByUserIdOrderByEndDateDesc(userId).orElse(null);
    if(sub==null) return Map.of("message","Sin suscripción");
    // auto-expire simple:
    if(sub.getEndDate().isBefore(LocalDate.now())) {
      sub.setStatus(SubscriptionStatus.EXPIRED);
      repo.save(sub);
    }
    return sub;
  }
}
