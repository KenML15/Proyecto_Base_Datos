package com.telecom.link360.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "[PLAN_TARIFARIO]")
public class Plan {

    @Id
    @Column(name = "CodPlan")
    private Integer id;

    @Column(name = "CodCategoria", nullable = false)
    private Integer codCategoria;

    @Column(name = "Nombre", nullable = false, length = 50)
    private String nombre;

    @Column(name = "Descripcion", length = 255)
    private String descripcion;

    @Column(name = "CuotaMensual", nullable = false, precision = 12, scale = 2)
    private BigDecimal cuotaMensual;

    @Column(name = "GigabytesIncluidos", nullable = false)
    private Integer gigabytes;

    @Column(name = "MinutosIncluidos", nullable = false)
    private Integer minutos;

    @Column(name = "MensajesIncluidos", nullable = false)
    private Integer mensajes;

    @Column(name = "CostoExceso", nullable = false, precision = 10, scale = 2)
    private BigDecimal costoExceso;

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
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCodCategoria() {
        return codCategoria;
    }

    public void setCodCategoria(Integer codCategoria) {
        this.codCategoria = codCategoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getCuotaMensual() {
        return cuotaMensual;
    }

    public void setCuotaMensual(BigDecimal cuotaMensual) {
        this.cuotaMensual = cuotaMensual;
    }

    public Integer getGigabytes() {
        return gigabytes;
    }

    public void setGigabytes(Integer gigabytes) {
        this.gigabytes = gigabytes;
    }

    public Integer getMinutos() {
        return minutos;
    }

    public void setMinutos(Integer minutos) {
        this.minutos = minutos;
    }

    public Integer getMensajes() {
        return mensajes;
    }

    public void setMensajes(Integer mensajes) {
        this.mensajes = mensajes;
    }

    public BigDecimal getCostoExceso() {
        return costoExceso;
    }

    public void setCostoExceso(BigDecimal costoExceso) {
        this.costoExceso = costoExceso;
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
