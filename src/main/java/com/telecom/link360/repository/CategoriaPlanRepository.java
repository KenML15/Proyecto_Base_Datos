package com.telecom.link360.repository;

import com.telecom.link360.model.CategoriaPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaPlanRepository extends JpaRepository<CategoriaPlan, Integer> {
}
