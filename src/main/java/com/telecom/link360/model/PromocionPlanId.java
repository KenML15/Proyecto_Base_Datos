package com.telecom.link360.model;

import java.io.Serializable;
import java.util.Objects;

public class PromocionPlanId implements Serializable {
    private Integer promocion;
    private Integer plan;

    public PromocionPlanId() {}

    public PromocionPlanId(Integer promocion, Integer plan) {
        this.promocion = promocion;
        this.plan = plan;
    }

    public Integer getPromocion() {
        return promocion;
    }

    public void setPromocion(Integer promocion) {
        this.promocion = promocion;
    }

    public Integer getPlan() {
        return plan;
    }

    public void setPlan(Integer plan) {
        this.plan = plan;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PromocionPlanId that = (PromocionPlanId) o;
        return Objects.equals(promocion, that.promocion) &&
                Objects.equals(plan, that.plan);
    }

    @Override
    public int hashCode() {
        return Objects.hash(promocion, plan);
    }
}
