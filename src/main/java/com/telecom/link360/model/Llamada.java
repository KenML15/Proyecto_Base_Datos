package com.telecom.link360.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "LLAMADA")
@IdClass(LlamadaId.class)
public class Llamada {

    @Id
    @Column(name = "Id_Consumo")
    private Integer idConsumo;

    @ManyToOne
    @JoinColumn(name = "Id_Consumo", insertable = false, updatable = false)
    private Consumo consumo;

    @Column(name = "NumDestino", nullable = false, length = 15)
    private String numDestino;

    @Column(name = "TipoLlamada", nullable = false, length = 30)
    private String tipoLlamada;

    @Column(name = "Costo", nullable = false, precision = 12, scale = 2)
    private BigDecimal costo;

    @Column(name = "FechaInicial", nullable = false)
    private LocalDateTime fechaInicial;

    @ManyToOne
    @JoinColumn(name = "Id_Franja", nullable = false)
    private FranjaHoraria franjaHoraria;

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

    public Llamada() {}

    // Getters y Setters
    public Integer getIdConsumo() { return idConsumo; }
    public void setIdConsumo(Integer idConsumo) { this.idConsumo = idConsumo; }

    public Consumo getConsumo() { return consumo; }
    public void setConsumo(Consumo consumo) {
        this.consumo = consumo;
        if (consumo != null) {
            this.idConsumo = consumo.getIdConsumo();
        }
    }

    public String getNumDestino() { return numDestino; }
    public void setNumDestino(String numDestino) { this.numDestino = numDestino; }

    public String getTipoLlamada() { return tipoLlamada; }
    public void setTipoLlamada(String tipoLlamada) { this.tipoLlamada = tipoLlamada; }

    public BigDecimal getCosto() { return costo; }
    public void setCosto(BigDecimal costo) { this.costo = costo; }

    public LocalDateTime getFechaInicial() { return fechaInicial; }
    public void setFechaInicial(LocalDateTime fechaInicial) { this.fechaInicial = fechaInicial; }

    public FranjaHoraria getFranjaHoraria() { return franjaHoraria; }
    public void setFranjaHoraria(FranjaHoraria franjaHoraria) { this.franjaHoraria = franjaHoraria; }

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