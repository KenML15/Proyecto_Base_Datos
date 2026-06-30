package com.telecom.link360.controller;

import com.telecom.link360.model.Factura;
import com.telecom.link360.repository.FacturaRepository;
import com.telecom.link360.repository.CustomerRepository;
import com.telecom.link360.repository.FranjaHorariaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/facturas")
public class FacturaController {

    @Autowired
    private FacturaRepository facturaRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private FranjaHorariaRepository franjaHorariaRepository;

    // LEER (Listar)
    @GetMapping
    public String listFacturas(Model model) {
        model.addAttribute("facturas", facturaRepository.findAll());
        return "facturaList";
    }

    // CREAR (Formulario)
    @GetMapping("/new")
    public String showForm(Model model) {
        model.addAttribute("factura", new Factura());
        model.addAttribute("clientes", customerRepository.findAll());
        model.addAttribute("franjas", franjaHorariaRepository.findAll());
        return "facturaForm";
    }

    // GUARDAR (POST)
    @PostMapping("/save")
    public String saveFactura(@ModelAttribute Factura factura) {
        // Auditoría - CreatedAt y CreatedBy (solo para nuevos)
        if (factura.getCreatedBy() == null || factura.getCreatedBy().isEmpty()) {
            factura.setCreatedBy("admin");
        }
        if (factura.getCreatedAt() == null) {
            factura.setCreatedAt(LocalDateTime.now());
        }

        // Auditoría - ModifiedAt y ModifiedBy (para ediciones)
        if (factura.getNumFactura() != null && facturaRepository.existsById(factura.getNumFactura())) {
            factura.setModifiedBy("admin");
            factura.setModifiedAt(LocalDateTime.now());
        }

        // Status por defecto
        if (factura.getStatus() == null || factura.getStatus().isEmpty()) {
            factura.setStatus("A");
        }

        facturaRepository.save(factura);
        return "redirect:/facturas";
    }

    // EDITAR (Cargar datos)
    @GetMapping("/edit/{id}")
    public String editFactura(@PathVariable Integer id, Model model) {
        Factura factura = facturaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID de factura inválido: " + id));
        model.addAttribute("factura", factura);
        model.addAttribute("clientes", customerRepository.findAll());
        model.addAttribute("franjas", franjaHorariaRepository.findAll());
        return "facturaForm";
    }

    // ELIMINAR
    @GetMapping("/delete/{id}")
    public String deleteFactura(@PathVariable Integer id) {
        facturaRepository.deleteById(id);
        return "redirect:/facturas";
    }
}