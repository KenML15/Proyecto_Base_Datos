package com.telecom.link360.model;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class DetalleFacturaId implements Serializable {

    private Integer numFactura;
    private Integer idLineaDetalle;

    public DetalleFacturaId() {}

    public DetalleFacturaId(Integer numFactura, Integer idLineaDetalle) {
        this.numFactura = numFactura;
        this.idLineaDetalle = idLineaDetalle;
    }

    public Integer getNumFactura() { return numFactura; }
    public void setNumFactura(Integer numFactura) { this.numFactura = numFactura; }

    public Integer getIdLineaDetalle() { return idLineaDetalle; }
    public void setIdLineaDetalle(Integer idLineaDetalle) { this.idLineaDetalle = idLineaDetalle; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DetalleFacturaId that = (DetalleFacturaId) o;
        return Objects.equals(numFactura, that.numFactura) &&
                Objects.equals(idLineaDetalle, that.idLineaDetalle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numFactura, idLineaDetalle);
    }

    @Override
    public String toString() {
        return "DetalleFacturaId{" +
                "numFactura=" + numFactura +
                ", idLineaDetalle=" + idLineaDetalle +
                '}';
    }
}