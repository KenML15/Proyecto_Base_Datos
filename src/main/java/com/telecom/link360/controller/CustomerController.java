package com.telecom.link360.controller;

import com.telecom.link360.model.Customer;
import com.telecom.link360.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    // LEER (Listar)
    @GetMapping
    public String listCustomers(Model model) {
        model.addAttribute("customers", customerRepository.findAll());
        return "customerList";
    }

    // CREAR (Formulario)
    @GetMapping("/new")
    public String showForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "customerForm";
    }

    // GUARDAR (POST)
    @PostMapping("/save")
    public String saveCustomer(@ModelAttribute Customer customer) {

        if (customer.getNombre() == null || customer.getNombre().isEmpty()) {
            throw new IllegalArgumentException("El nombre es obligatorio");
        }
        if (customer.getApellido1() == null || customer.getApellido1().isEmpty()) {
            throw new IllegalArgumentException("El primer apellido es obligatorio");
        }
        if (customer.getCorreo() == null || customer.getCorreo().isEmpty()) {
            throw new IllegalArgumentException("El correo es obligatorio");
        }

        if (customer.getFechaIngreso() == null) {
            customer.setFechaIngreso(LocalDate.now());
        }

        if (customer.getCreatedBy() == null || customer.getCreatedBy().isEmpty()) {
            customer.setCreatedBy("admin");
        }
        if (customer.getCreatedAt() == null) {
            customer.setCreatedAt(LocalDateTime.now());
        }

        if (customer.getIdCliente() != null && customerRepository.existsById(customer.getIdCliente())) {
            customer.setModifiedBy("admin");
            customer.setModifiedAt(LocalDateTime.now());
        }

        if (customer.getStatus() == null || customer.getStatus().isEmpty()) {
            customer.setStatus("A");
        }

        if (customer.getTipoCliente() == null || customer.getTipoCliente().isEmpty()) {
            customer.setTipoCliente("Regular");
        }

        customerRepository.save(customer);
        return "redirect:/customers";
    }

    // EDITAR (Cargar datos)
    @GetMapping("/edit/{id}")
    public String editCustomer(@PathVariable("id") Integer id, Model model) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID de cliente inválido: " + id));
        model.addAttribute("customer", customer);
        return "customerForm";
    }

    // ELIMINAR
    @GetMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable("id") Integer id) {
        customerRepository.deleteById(id);
        return "redirect:/customers";
    }
}