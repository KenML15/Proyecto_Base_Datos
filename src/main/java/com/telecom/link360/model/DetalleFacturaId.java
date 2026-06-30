package com.telecom.link360.model;

import java.io.Serializable;
import java.util.Objects;

public class DetalleFacturaId implements Serializable {
    private Integer factura;
    private Integer idLineaDetalle;

    public DetalleFacturaId() {}

    public DetalleFacturaId(Integer factura, Integer idLineaDetalle) {
        this.factura = factura;
        this.idLineaDetalle = idLineaDetalle;
    }

    public Integer getFactura() {
        return factura;
    }

    public void setFactura(Integer factura) {
        this.factura = factura;
    }

    public Integer getIdLineaDetalle() {
        return idLineaDetalle;
    }

    public void setIdLineaDetalle(Integer idLineaDetalle) {
        this.idLineaDetalle = idLineaDetalle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DetalleFacturaId that = (DetalleFacturaId) o;
        return Objects.equals(factura, that.factura) &&
                Objects.equals(idLineaDetalle, that.idLineaDetalle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(factura, idLineaDetalle);
    }
}
