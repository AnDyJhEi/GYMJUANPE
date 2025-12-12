package com.gymjuanpe_app.repository;

import com.gymjuanpe_app.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
  List<Reservation> findByClassId(Long classId);
}
