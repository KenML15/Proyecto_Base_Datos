package com.telecom.link360.repository;

import com.telecom.link360.model.PromocionPlan;
import com.telecom.link360.model.PromocionPlanId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PromocionPlanRepository extends JpaRepository<PromocionPlan, PromocionPlanId> {
}
