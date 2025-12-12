package com.gymjuanpe_app.repository;

import com.gymjuanpe_app.model.GymClass;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassRepository extends JpaRepository<GymClass, Long> {}
