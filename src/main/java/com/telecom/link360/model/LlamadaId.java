package com.telecom.link360.model;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class LlamadaId implements Serializable {

    private Integer idConsumo;

    public LlamadaId() {}

    public LlamadaId(Integer idConsumo) {
        this.idConsumo = idConsumo;
    }

    public Integer getIdConsumo() { return idConsumo; }
    public void setIdConsumo(Integer idConsumo) { this.idConsumo = idConsumo; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LlamadaId llamadaId = (LlamadaId) o;
        return Objects.equals(idConsumo, llamadaId.idConsumo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idConsumo);
    }

    @Override
    public String toString() {
        return "LlamadaId{" + "idConsumo=" + idConsumo + '}';
    }
}