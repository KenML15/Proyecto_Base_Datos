package com.telecom.link360.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDateTime;

@Entity
@Table(name = "CONTRATO")
public class Contrato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "NumContrato")
    private Integer numContrato;

    @ManyToOne
    @JoinColumn(name = "Id_Cliente", nullable = false)
    private Customer cliente;

    @ManyToOne
    @JoinColumn(name = "CodPlan", nullable = false)
    private Plan plan;

    @Column(name = "FechaFirma", nullable = false)
    private Date fechaFirma;

    @Column(name = "PlazoMeses", nullable = false)
    private Integer plazoMeses;

    @Column(name = "MontoPenalizacion", nullable = false, precision = 12, scale = 2)
    private BigDecimal montoPenalizacion;

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

    public Contrato() {}

    // Getters y Setters
    public Integer getNumContrato() { return numContrato; }
    public void setNumContrato(Integer numContrato) { this.numContrato = numContrato; }

    public Customer getCliente() { return cliente; }
    public void setCliente(Customer cliente) { this.cliente = cliente; }

    public Plan getPlan() { return plan; }
    public void setPlan(Plan plan) { this.plan = plan; }

    public Date getFechaFirma() { return fechaFirma; }
    public void setFechaFirma(Date fechaFirma) { this.fechaFirma = fechaFirma; }

    public Integer getPlazoMeses() { return plazoMeses; }
    public void setPlazoMeses(Integer plazoMeses) { this.plazoMeses = plazoMeses; }

    public BigDecimal getMontoPenalizacion() { return montoPenalizacion; }
    public void setMontoPenalizacion(BigDecimal montoPenalizacion) { this.montoPenalizacion = montoPenalizacion; }

    public String getCreatedBy() { return createdBy; }
    public void setCreatedBy(String createdBy) { this.createdBy = createdBy; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public String getModifiedBy() { return modifiedBy; }
    public void setModifiedBy(String modifiedBy) { this.modifiedBy = modifiedBy; }

    public LocalDateTime getModifiedAt() { return modifiedAt; }
    public void setModifiedAt(LocalDateTime modifiedAt) { this.modifiedAt = modifiedAt; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}