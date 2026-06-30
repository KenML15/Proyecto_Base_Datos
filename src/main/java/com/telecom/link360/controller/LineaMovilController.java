package com.telecom.link360.controller;

import com.telecom.link360.model.LineaMovil;
import com.telecom.link360.model.Customer;
import com.telecom.link360.repository.CustomerRepository;
import com.telecom.link360.repository.LineaMovilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/lineas")
public class LineaMovilController {

    @Autowired
    private LineaMovilRepository lineaRepository;

    @Autowired
    private CustomerRepository customerRepository;

    // LEER (Listar)
    @GetMapping
    public String listLineas(Model model) {
        model.addAttribute("lineas", lineaRepository.findAll());
        return "lineaList";
    }

    // CREAR (Formulario)
    @GetMapping("/new")
    public String showForm(Model model) {
        model.addAttribute("lineaMovil", new LineaMovil());
        model.addAttribute("clientes", customerRepository.findAll());
        return "lineaForm";
    }

    // GUARDAR (POST)
    @PostMapping("/save")
    public String saveLinea(@ModelAttribute LineaMovil lineaMovil) {

        // 1. Validar que el cliente existe
        if (lineaMovil.getCliente() == null || lineaMovil.getCliente().getIdCliente() == null) {
            throw new IllegalArgumentException("Debe seleccionar un cliente válido");
        }

        Customer cliente = customerRepository.findById(lineaMovil.getCliente().getIdCliente())
                .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado"));
        lineaMovil.setCliente(cliente);

        // 2. Asignar fecha de activación si no existe
        if (lineaMovil.getFechaActivacion() == null) {
            lineaMovil.setFechaActivacion(LocalDate.now());
        }

        // 3. Auditoría - CreatedBy y CreatedAt (solo para nuevos)
        if (lineaMovil.getCreatedBy() == null || lineaMovil.getCreatedBy().isEmpty()) {
            lineaMovil.setCreatedBy("admin");
        }
        if (lineaMovil.getCreatedAt() == null) {
            lineaMovil.setCreatedAt(LocalDateTime.now());
        }

        // 4. Auditoría - ModifiedAt y ModifiedBy (para ediciones)
        if (lineaMovil.getNumTelefono() != null && lineaRepository.existsById(lineaMovil.getNumTelefono())) {
            lineaMovil.setModifiedBy("admin");
            lineaMovil.setModifiedAt(LocalDateTime.now());
        }

        // 5. Status por defecto
        if (lineaMovil.getStatus() == null || lineaMovil.getStatus().isEmpty()) {
            lineaMovil.setStatus("A");
        }

        lineaRepository.save(lineaMovil);
        return "redirect:/lineas";
    }

    // EDITAR (Cargar datos)
    @GetMapping("/edit/{id}")
    public String editLinea(@PathVariable String id, Model model) {
        LineaMovil lineaMovil = lineaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Número de teléfono inválido: " + id));
        model.addAttribute("lineaMovil", lineaMovil);
        model.addAttribute("clientes", customerRepository.findAll());
        return "lineaForm";
    }

    // ELIMINAR
    @GetMapping("/delete/{id}")
    public String deleteLinea(@PathVariable String id) {
        lineaRepository.deleteById(id);
        return "redirect:/lineas";
    }
}