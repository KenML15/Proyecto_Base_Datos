package com.telecom.link360.repository;

import com.telecom.link360.model.Llamada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LlamadaRepository extends JpaRepository<Llamada, Integer> {
}
