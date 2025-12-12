package com.gymjuanpe_app.service;

import com.gymjuanpe_app.model.GymClass;
import com.gymjuanpe_app.model.Reservation;
import com.gymjuanpe_app.repository.ClassRepository;
import com.gymjuanpe_app.repository.ReservationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class ReservationService {

  private final ClassRepository classRepo;
  private final ReservationRepository reservationRepo;

  public ReservationService(ClassRepository classRepo, ReservationRepository reservationRepo) {
    this.classRepo = classRepo;
    this.reservationRepo = reservationRepo;
  }

  @Transactional
  public Reservation reserve(Long classId, Long userId) {
    GymClass gc = classRepo.findById(classId)
        .orElseThrow(() -> new RuntimeException("Clase no existe"));

    if (gc.getReservedCount() >= gc.getCapacity()) {
      throw new RuntimeException("No hay cupos disponibles");
    }

    gc.setReservedCount(gc.getReservedCount() + 1);
    classRepo.save(gc);

    Reservation r = new Reservation();
    r.setClassId(classId);
    r.setUserId(userId);
    r.setCreatedAt(LocalDateTime.now());
    return reservationRepo.save(r);
  }
}
