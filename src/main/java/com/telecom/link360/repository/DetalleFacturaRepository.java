package com.telecom.link360.repository;

import com.telecom.link360.model.DetalleFactura;
import com.telecom.link360.model.DetalleFacturaId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetalleFacturaRepository extends JpaRepository<DetalleFactura, DetalleFacturaId> {

    List<DetalleFactura> findByFacturaNumFactura(Integer numFactura);

    @Query("SELECT MAX(d.idLineaDetalle) FROM DetalleFactura d WHERE d.numFactura = :numFactura")
    Integer findMaxIdLineaDetalleByNumFactura(@Param("numFactura") Integer numFactura);
}