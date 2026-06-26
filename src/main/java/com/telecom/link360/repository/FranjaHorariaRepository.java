package com.telecom.link360.repository;

import com.telecom.link360.model.FranjaHoraria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FranjaHorariaRepository extends JpaRepository<FranjaHoraria, Integer> {
}
