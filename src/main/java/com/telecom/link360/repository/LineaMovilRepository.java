package com.telecom.link360.repository;

import com.telecom.link360.model.LineaMovil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LineaMovilRepository extends JpaRepository<LineaMovil, String> {
}