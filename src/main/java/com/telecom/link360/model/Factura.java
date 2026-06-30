package com.telecom.link360.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDateTime;

@Entity
@Table(name = "FACTURA")
public class Factura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "NumFactura")
    private Integer numFactura;

    @ManyToOne
    @JoinColumn(name = "Id_Cliente", nullable = false)
    private Customer cliente;

    @Column(name = "FechaEmision", nullable = false)
    private Date fechaEmision;

    @Column(name = "FechaFin", nullable = false)
    private Date fechaFin;

    @Column(name = "Desc_epoca", nullable = false, precision = 10, scale = 2)
    private BigDecimal descEpoca;

    @Column(name = "CodRango", nullable = false)
    private Integer codRango;

    @ManyToOne
    @JoinColumn(name = "Id_Franja", nullable = false)
    private FranjaHoraria franjaHoraria;

    @Column(name = "Desc_FR_Ran", nullable = false, precision = 10, scale = 2)
    private BigDecimal descFRRan;

    @Column(name = "PtsRedimidos", nullable = false)
    private Integer ptsRedimidos;

    @Column(name = "ImpPtsRedimidos", nullable = false, precision = 12, scale = 2)
    private BigDecimal impPtsRedimidos;

    @Column(name = "MontoSubtotal", nullable = false, precision = 12, scale = 2)
    private BigDecimal montoSubtotal;

    @Column(name = "MontoImpuesto", nullable = false, precision = 12, scale = 2)
    private BigDecimal montoImpuesto;

    @Column(name = "MontoTotal", nullable = false, precision = 12, scale = 2)
    private BigDecimal montoTotal;

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

    public Factura() {}

    // Getters y Setters
    public Integer getNumFactura() { return numFactura; }
    public void setNumFactura(Integer numFactura) { this.numFactura = numFactura; }

    public Customer getCliente() { return cliente; }
    public void setCliente(Customer cliente) { this.cliente = cliente; }

    public Date getFechaEmision() { return fechaEmision; }
    public void setFechaEmision(Date fechaEmision) { this.fechaEmision = fechaEmision; }

    public Date getFechaFin() { return fechaFin; }
    public void setFechaFin(Date fechaFin) { this.fechaFin = fechaFin; }

    public BigDecimal getDescEpoca() { return descEpoca; }
    public void setDescEpoca(BigDecimal descEpoca) { this.descEpoca = descEpoca; }

    public Integer getCodRango() { return codRango; }
    public void setCodRango(Integer codRango) { this.codRango = codRango; }

    public FranjaHoraria getFranjaHoraria() { return franjaHoraria; }
    public void setFranjaHoraria(FranjaHoraria franjaHoraria) { this.franjaHoraria = franjaHoraria; }

    public BigDecimal getDescFRRan() { return descFRRan; }
    public void setDescFRRan(BigDecimal descFRRan) { this.descFRRan = descFRRan; }

    public Integer getPtsRedimidos() { return ptsRedimidos; }
    public void setPtsRedimidos(Integer ptsRedimidos) { this.ptsRedimidos = ptsRedimidos; }

    public BigDecimal getImpPtsRedimidos() { return impPtsRedimidos; }
    public void setImpPtsRedimidos(BigDecimal impPtsRedimidos) { this.impPtsRedimidos = impPtsRedimidos; }

    public BigDecimal getMontoSubtotal() { return montoSubtotal; }
    public void setMontoSubtotal(BigDecimal montoSubtotal) { this.montoSubtotal = montoSubtotal; }

    public BigDecimal getMontoImpuesto() { return montoImpuesto; }
    public void setMontoImpuesto(BigDecimal montoImpuesto) { this.montoImpuesto = montoImpuesto; }

    public BigDecimal getMontoTotal() { return montoTotal; }
    public void setMontoTotal(BigDecimal montoTotal) { this.montoTotal = montoTotal; }

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