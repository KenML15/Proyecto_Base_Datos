package com.telecom.link360.model;

import jakarta.persistence.*;
import java.sql.Date;
import java.time.LocalDateTime;

@Entity
@Table(name = "LINEA_MOVIL")
public class LineaMovil {

    @Id
    @Column(name = "NumTelefono", length = 15)
    private String numTelefono;

    @ManyToOne
    @JoinColumn(name = "Id_Cliente", nullable = false)
    private Customer cliente;

    @Column(name = "TipoLinea", nullable = false, length = 20)
    private String tipoLinea;

    @Column(name = "Tecnologia", nullable = false, length = 10)
    private String tecnologia;

    @Column(name = "FechaActivacion", nullable = false)
    private Date fechaActivacion;

    @Column(name = "EstadoLinea", nullable = false, length = 20)
    private String estadoLinea;

    @Column(name = "TipoSIM", nullable = false, length = 10)
    private String tipoSIM;

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

    public LineaMovil() {}

    // Getters y Setters
    public String getNumTelefono() { return numTelefono; }
    public void setNumTelefono(String numTelefono) { this.numTelefono = numTelefono; }

    public Customer getCliente() { return cliente; }
    public void setCliente(Customer cliente) { this.cliente = cliente; }

    public String getTipoLinea() { return tipoLinea; }
    public void setTipoLinea(String tipoLinea) { this.tipoLinea = tipoLinea; }

    public String getTecnologia() { return tecnologia; }
    public void setTecnologia(String tecnologia) { this.tecnologia = tecnologia; }

    public Date getFechaActivacion() { return fechaActivacion; }
    public void setFechaActivacion(Date fechaActivacion) { this.fechaActivacion = fechaActivacion; }

    public String getEstadoLinea() { return estadoLinea; }
    public void setEstadoLinea(String estadoLinea) { this.estadoLinea = estadoLinea; }

    public String getTipoSIM() { return tipoSIM; }
    public void setTipoSIM(String tipoSIM) { this.tipoSIM = tipoSIM; }

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