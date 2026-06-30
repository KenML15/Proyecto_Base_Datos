package com.telecom.link360.model;

import java.io.Serializable;
import java.util.Objects;

public class PromocionAmbitoId implements Serializable {
    private Integer promocion;
    private Integer ambito;

    public PromocionAmbitoId() {}

    public PromocionAmbitoId(Integer promocion, Integer ambito) {
        this.promocion = promocion;
        this.ambito = ambito;
    }

    public Integer getPromocion() {
        return promocion;
    }

    public void setPromocion(Integer promocion) {
        this.promocion = promocion;
    }

    public Integer getAmbito() {
        return ambito;
    }

    public void setAmbito(Integer ambito) {
        this.ambito = ambito;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PromocionAmbitoId that = (PromocionAmbitoId) o;
        return Objects.equals(promocion, that.promocion) &&
                Objects.equals(ambito, that.ambito);
    }

    @Override
    public int hashCode() {
        return Objects.hash(promocion, ambito);
    }
}
