package com.punyadev.batch.unit.repository;

import com.punyadev.batch.unit.model.Unit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UnitRepository extends JpaRepository<Unit, Long> {
}
