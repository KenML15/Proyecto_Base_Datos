package com.telecom.link360.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "DETALLE_FACTURA")
@IdClass(DetalleFacturaId.class)
public class DetalleFactura {

    @Id
    @ManyToOne
    @JoinColumn(name = "NumFactura", nullable = false)
    private Factura factura;

    @Id
    @Column(name = "Id_LineaDetalle")
    private Integer idLineaDetalle;

    @ManyToOne
    @JoinColumn(name = "Id_Consumo", nullable = false)
    private Consumo consumo;

    @Column(name = "MontoCobrado", nullable = false, precision = 12, scale = 2)
    private BigDecimal montoCobrado;

    @Column(name = "CreatedBy", nullable = false, length = 50)
    private String createdBy = "admin";

    @Column(name = "CreatedAt", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "ModifiedBy", length = 50)
    private String modifiedBy;

    @Column(name = "ModifiedAt")
    private LocalDateTime modifiedAt;

    @Column(name = "Status", nullable = false, length = 1)
    private String status = "A";

    // Getters y Setters
    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public Integer getIdLineaDetalle() {
        return idLineaDetalle;
    }

    public void setIdLineaDetalle(Integer idLineaDetalle) {
        this.idLineaDetalle = idLineaDetalle;
    }

    public Consumo getConsumo() {
        return consumo;
    }

    public void setConsumo(Consumo consumo) {
        this.consumo = consumo;
    }

    public BigDecimal getMontoCobrado() {
        return montoCobrado;
    }

    public void setMontoCobrado(BigDecimal montoCobrado) {
        this.montoCobrado = montoCobrado;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(LocalDateTime modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
