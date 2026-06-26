package com.telecom.link360.controller;

import com.telecom.link360.model.Contrato;
import com.telecom.link360.repository.ContratoRepository;
import com.telecom.link360.repository.CustomerRepository;
import com.telecom.link360.repository.PlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/contratos")
public class ContratoController {

    @Autowired
    private ContratoRepository contratoRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PlanRepository planRepository;

    // LEER (Listar)
    @GetMapping
    public String listContratos(Model model) {
        model.addAttribute("contratos", contratoRepository.findAll());
        return "contratoList";
    }

    // CREAR (Formulario)
    @GetMapping("/new")
    public String showForm(Model model) {
        model.addAttribute("contrato", new Contrato());
        model.addAttribute("clientes", customerRepository.findAll());
        model.addAttribute("planes", planRepository.findAll());
        return "contratoForm";
    }

    // GUARDAR (POST)
    @PostMapping("/save")
    public String saveContrato(@ModelAttribute Contrato contrato) {
        // Generar ID manual si es nuevo
        if (contrato.getNumContrato() == null) {
            Integer maxId = contratoRepository.findAll()
                    .stream()
                    .map(Contrato::getNumContrato)
                    .max(Integer::compare)
                    .orElse(0);
            contrato.setNumContrato(maxId + 1);
        }

        // Asignar valores por defecto si es necesario
        if (contrato.getFechaFirma() == null) {
            contrato.setFechaFirma(LocalDate.now());
        }

        if (contrato.getCreatedBy() == null || contrato.getCreatedBy().isEmpty()) {
            contrato.setCreatedBy("admin");
        }

        if (contrato.getCreatedAt() == null) {
            contrato.setCreatedAt(LocalDateTime.now());
        }

        if (contrato.getStatus() == null || contrato.getStatus().isEmpty()) {
            contrato.setStatus("A");
        }

        contratoRepository.save(contrato);
        return "redirect:/contratos";
    }

    // EDITAR (Cargar datos)
    @GetMapping("/edit/{id}")
    public String editContrato(@PathVariable Integer id, Model model) {
        Contrato contrato = contratoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID de contrato inválido: " + id));
        model.addAttribute("contrato", contrato);
        model.addAttribute("clientes", customerRepository.findAll());
        model.addAttribute("planes", planRepository.findAll());
        return "contratoForm";
    }

    // ELIMINAR
    @GetMapping("/delete/{id}")
    public String deleteContrato(@PathVariable Integer id) {
        contratoRepository.deleteById(id);
        return "redirect:/contratos";
    }
}
