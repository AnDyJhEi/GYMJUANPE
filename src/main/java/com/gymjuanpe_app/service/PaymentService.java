package com.gymjuanpe_app.service;

import com.gymjuanpe_app.model.*;
import com.gymjuanpe_app.repository.PaymentRepository;
import com.gymjuanpe_app.repository.PlanRepository;
import com.gymjuanpe_app.repository.SubscriptionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class PaymentService {

  private final PaymentRepository paymentRepo;
  private final PlanRepository planRepo;
  private final SubscriptionRepository subRepo;

  public PaymentService(PaymentRepository paymentRepo, PlanRepository planRepo, SubscriptionRepository subRepo) {
    this.paymentRepo = paymentRepo;
    this.planRepo = planRepo;
    this.subRepo = subRepo;
  }

  @Transactional
  public Payment pay(Long userId, Long planId, Double amount) {
    Plan plan = planRepo.findById(planId)
        .orElseThrow(() -> new RuntimeException("Plan no existe"));

    if (amount == null || Double.compare(amount, plan.getPrice()) != 0) {
      Payment failed = new Payment();
      failed.setUserId(userId);
      failed.setPlanId(planId);
      failed.setAmount(amount == null ? 0.0 : amount);
      failed.setStatus(PaymentStatus.FAILED);
      failed.setCreatedAt(LocalDateTime.now());
      failed.setReceiptCode("FAIL-" + UUID.randomUUID().toString().substring(0,8));
      return paymentRepo.save(failed);
    }

    Payment p = new Payment();
    p.setUserId(userId);
    p.setPlanId(planId);
    p.setAmount(amount);
    p.setStatus(PaymentStatus.PAID);
    p.setCreatedAt(LocalDateTime.now());
    p.setReceiptCode("REC-" + UUID.randomUUID().toString().substring(0,8));
    paymentRepo.save(p);

    // Suscripción: crear o renovar
    LocalDate today = LocalDate.now();
    Subscription sub = subRepo.findTopByUserIdOrderByEndDateDesc(userId).orElse(null);

    if (sub == null) {
      sub = new Subscription();
      sub.setUserId(userId);
      sub.setPlanId(planId);
      sub.setStartDate(today);
      sub.setEndDate(today.plusDays(plan.getDurationDays()));
      sub.setStatus(SubscriptionStatus.ACTIVE);
    } else {
      LocalDate start = sub.getEndDate().isAfter(today) ? sub.getEndDate().plusDays(1) : today;
      sub.setPlanId(planId);
      sub.setStartDate(start);
      sub.setEndDate(start.plusDays(plan.getDurationDays()));
      sub.setStatus(SubscriptionStatus.ACTIVE);
    }
    subRepo.save(sub);

    return p;
  }
}
