package com.telecom.link360.repository;

import com.telecom.link360.model.PromocionAmbito;
import com.telecom.link360.model.PromocionAmbitoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PromocionAmbitoRepository extends JpaRepository<PromocionAmbito, PromocionAmbitoId> {
}
