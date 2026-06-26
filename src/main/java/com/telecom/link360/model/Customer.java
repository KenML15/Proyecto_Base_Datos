package com.telecom.link360.model;

import jakarta.persistence.*;
import java.sql.Date;
import java.time.LocalDate;

@Entity
@Table(name = "CLIENTE")
public class Customer {

    @Id
    @Column(name = "Id_Cliente")
    private Integer id; // Quitamos @GeneratedValue porque tu tabla no es IDENTITY

    @Column(name = "Nombre", nullable = false)
    private String firstName;

    @Column(name = "Apellido1", nullable = false)
    private String lastName1;

    @Column(name = "Apellido2")
    private String lastName2;

    @Column(name = "Direccion", nullable = false)
    private String direccion; // Campo obligatorio en tu SQL

    @Column(name = "Correo", nullable = false)
    private String email;

    @Column(name = "FechaIngreso", nullable = false)
    private Date fechaIngreso; // Campo obligatorio en tu SQL

    @Column(name = "TipoCliente", nullable = false)
    private String customerType;

    @Column(name = "Status", nullable = false, length = 1)
    private String status;

    @Column(name = "CreatedBy", nullable = false)
    private String createdBy = "admin"; // Valor por defecto

    public Customer() {}

    // Getters y Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName1() { return lastName1; }
    public void setLastName1(String lastName1) { this.lastName1 = lastName1; }
    public String getLastName2() { return lastName2; }
    public void setLastName2(String lastName2) { this.lastName2 = lastName2; }
    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public Date getFechaIngreso() { return fechaIngreso; }
    public void setFechaIngreso(Date fechaIngreso) { this.fechaIngreso = fechaIngreso; }
    public String getCustomerType() { return customerType; }
    public void setCustomerType(String customerType) { this.customerType = customerType; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getCreatedBy() { return createdBy; }
    public void setCreatedBy(String createdBy) { this.createdBy = createdBy; }
}