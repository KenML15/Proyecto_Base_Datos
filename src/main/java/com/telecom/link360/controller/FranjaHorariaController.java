package com.telecom.link360.controller;

import com.telecom.link360.model.FranjaHoraria;
import com.telecom.link360.repository.FranjaHorariaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/franjas")
public class FranjaHorariaController {

    @Autowired
    private FranjaHorariaRepository franjaHorariaRepository;

    // LEER (Listar)
    @GetMapping
    public String listFranjas(Model model) {
        model.addAttribute("franjas", franjaHorariaRepository.findAll());
        return "franjaList";
    }

    // CREAR (Formulario)
    @GetMapping("/new")
    public String showForm(Model model) {
        model.addAttribute("franja", new FranjaHoraria());
        return "franjaForm";
    }

    // GUARDAR (POST)
    @PostMapping("/save")
    public String savefranja(@ModelAttribute FranjaHoraria franja) {
        // Auditoría - CreatedAt y CreatedBy (solo para nuevos)
        if (franja.getCreatedBy() == null || franja.getCreatedBy().isEmpty()) {
            franja.setCreatedBy("admin");
        }
        if (franja.getCreatedAt() == null) {
            franja.setCreatedAt(LocalDateTime.now());
        }

        // Auditoría - ModifiedAt y ModifiedBy (para ediciones)
        if (franja.getIdFranja() != null && franjaHorariaRepository.existsById(franja.getIdFranja())) {
            franja.setModifiedBy("admin");
            franja.setModifiedAt(LocalDateTime.now());
        }

        // Status por defecto
        if (franja.getStatus() == null || franja.getStatus().isEmpty()) {
            franja.setStatus("A");
        }

        franjaHorariaRepository.save(franja);
        return "redirect:/franjas";
    }

    // EDITAR (Cargar datos)
    @GetMapping("/edit/{id}")
    public String editFranja(@PathVariable Integer id, Model model) {
        FranjaHoraria franja = franjaHorariaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID de franja inválido: " + id));
        model.addAttribute("franja", franja);
        return "franjaForm";
    }

    // ELIMINAR
    @GetMapping("/delete/{id}")
    public String deleteFranja(@PathVariable Integer id) {
        franjaHorariaRepository.deleteById(id);
        return "redirect:/franjas";
    }
}