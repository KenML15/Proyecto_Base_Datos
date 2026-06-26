package com.telecom.link360.model;

import jakarta.persistence.*;
import java.time.LocalTime;
import java.time.LocalDateTime;

@Entity
@Table(name = "FRANJA_HORARIA")
public class FranjaHoraria {

    @Id
    @Column(name = "Id_Franja")
    private Integer idFranja;

    @Column(name = "Descripcion", nullable = false, length = 100)
    private String descripcion;

    @Column(name = "HoraInicio", nullable = false)
    private LocalTime horaInicio;

    @Column(name = "HoraFin", nullable = false)
    private LocalTime horaFin;

    @Column(name = "DiasAplicacion", nullable = false, length = 50)
    private String diasAplicacion;

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
    public Integer getIdFranja() {
        return idFranja;
    }

    public void setIdFranja(Integer idFranja) {
        this.idFranja = idFranja;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalTime getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(LocalTime horaFin) {
        this.horaFin = horaFin;
    }

    public String getDiasAplicacion() {
        return diasAplicacion;
    }

    public void setDiasAplicacion(String diasAplicacion) {
        this.diasAplicacion = diasAplicacion;
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
