package com.telecom.link360.repository;

import com.telecom.link360.model.DetalleFactura;
import com.telecom.link360.model.DetalleFacturaId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetalleFacturaRepository extends JpaRepository<DetalleFactura, DetalleFacturaId> {

    List<DetalleFactura> findByFacturaNumFactura(Integer numFactura);
}