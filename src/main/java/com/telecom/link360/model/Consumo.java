package com.telecom.link360.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "CONSUMO")
public class Consumo {

    @Id
    @Column(name = "Id_Consumo")
    private Integer idConsumo;

    @ManyToOne
    @JoinColumn(name = "NumTelefono", nullable = false)
    private LineaMovil lineaMovil;

    @ManyToOne
    @JoinColumn(name = "CodAmbito", nullable = false)
    private Ambito ambito;

    @ManyToOne
    @JoinColumn(name = "Id_Franja", nullable = false)
    private FranjaHoraria franjaHoraria;

    @Column(name = "FechaHoraInicio", nullable = false)
    private LocalDateTime fechaHoraInicio;

    @Column(name = "FechaHoraFin", nullable = false)
    private LocalDateTime fechaHoraFin;

    @Column(name = "TipoConsumo", nullable = false, length = 30)
    private String tipoConsumo;

    @Column(name = "CantidadConsumida", nullable = false, precision = 10, scale = 2)
    private BigDecimal cantidadConsumida;

    @Column(name = "Costo", nullable = false, precision = 12, scale = 2)
    private BigDecimal costo;

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
    public Integer getIdConsumo() {
        return idConsumo;
    }

    public void setIdConsumo(Integer idConsumo) {
        this.idConsumo = idConsumo;
    }

    public LineaMovil getLineaMovil() {
        return lineaMovil;
    }

    public void setLineaMovil(LineaMovil lineaMovil) {
        this.lineaMovil = lineaMovil;
    }

    public Ambito getAmbito() {
        return ambito;
    }

    public void setAmbito(Ambito ambito) {
        this.ambito = ambito;
    }

    public FranjaHoraria getFranjaHoraria() {
        return franjaHoraria;
    }

    public void setFranjaHoraria(FranjaHoraria franjaHoraria) {
        this.franjaHoraria = franjaHoraria;
    }

    public LocalDateTime getFechaHoraInicio() {
        return fechaHoraInicio;
    }

    public void setFechaHoraInicio(LocalDateTime fechaHoraInicio) {
        this.fechaHoraInicio = fechaHoraInicio;
    }

    public LocalDateTime getFechaHoraFin() {
        return fechaHoraFin;
    }

    public void setFechaHoraFin(LocalDateTime fechaHoraFin) {
        this.fechaHoraFin = fechaHoraFin;
    }

    public String getTipoConsumo() {
        return tipoConsumo;
    }

    public void setTipoConsumo(String tipoConsumo) {
        this.tipoConsumo = tipoConsumo;
    }

    public BigDecimal getCantidadConsumida() {
        return cantidadConsumida;
    }

    public void setCantidadConsumida(BigDecimal cantidadConsumida) {
        this.cantidadConsumida = cantidadConsumida;
    }

    public BigDecimal getCosto() {
        return costo;
    }

    public void setCosto(BigDecimal costo) {
        this.costo = costo;
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
