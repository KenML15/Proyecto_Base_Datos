package com.telecom.link360.repository;

import com.telecom.link360.model.Llamada;
import com.telecom.link360.model.LlamadaId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LlamadaRepository extends JpaRepository<Llamada, LlamadaId> {
    List<Llamada> findByFranjaHorariaIdFranja(Integer idFranja);
}