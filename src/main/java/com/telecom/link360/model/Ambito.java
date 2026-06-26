package com.telecom.link360.model;

import jakarta.persistence.*;

@Entity
@Table(name = "AMBITO")
public class Ambito {
    @Id
    @Column(name = "CodAmbito")
    private String code;

    @Column(name = "Nombre")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}