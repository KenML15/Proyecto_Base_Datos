package com.telecom.link360.model;

import jakarta.persistence.Id;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "LINEA_MOVIL")
public class LineaMovil {

    @Id // Esta es la anotación correcta de jakarta.persistence
    @Column(name = "NumTelefono")
    private String numeroTelefono;

    @Column(name = "EstadoLinea", nullable = false, length = 20)
    private String estado;

    @Column(name = "Status", nullable = false, length = 1)
    private String status = "A";

    @Column(name = "TipoLinea", nullable = false, length = 20)
    private String tipoLinea;

    @Column(name = "Tecnologia", nullable = false, length = 10)
    private String tecnologia;

    @Column(name = "FechaActivacion", nullable = false)
    private LocalDate fechaActivacion;

    @Column(name = "TipoSIM", nullable = false, length = 10)
    private String tipoSIM;

    @Column(name = "CreatedBy", nullable = false, length = 50)
    private String createdBy = "admin";

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @ManyToOne
    @JoinColumn(name = "Id_Cliente", nullable = false)
    private Customer customer;

    public String getNumeroTelefono() {
        return numeroTelefono;
    }

    public void setNumeroTelefono(String numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTipoLinea() {
        return tipoLinea;
    }

    public void setTipoLinea(String tipoLinea) {
        this.tipoLinea = tipoLinea;
    }

    public String getTecnologia() {
        return tecnologia;
    }

    public void setTecnologia(String tecnologia) {
        this.tecnologia = tecnologia;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getTipoSIM() {
        return tipoSIM;
    }

    public void setTipoSIM(String tipoSIM) {
        this.tipoSIM = tipoSIM;
    }

    public LocalDate getFechaActivacion() {
        return fechaActivacion;
    }

    public void setFechaActivacion(LocalDate fechaActivacion) {
        this.fechaActivacion = fechaActivacion;
    }
}
