package com.telecom.link360.controller;

import com.telecom.link360.model.Ambito;
import com.telecom.link360.repository.AmbitoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/ambitos")
public class AmbitoController {

    @Autowired
    private AmbitoRepository ambitoRepository;

    // LEER (Listar)
    @GetMapping
    public String listAmbitos(Model model) {
        model.addAttribute("ambitos", ambitoRepository.findAll());
        return "ambitoList";
    }

    // CREAR (Formulario)
    @GetMapping("/new")
    public String showForm(Model model) {
        model.addAttribute("ambito", new Ambito());
        return "ambitoForm";
    }

    // GUARDAR (POST)
    @PostMapping("/save")
    public String saveAmbito(@ModelAttribute Ambito ambito) {
        // Generar ID manual si es nuevo
        if (ambito.getCodAmbito() == null) {
            Integer maxId = ambitoRepository.findAll()
                    .stream()
                    .map(Ambito::getCodAmbito)
                    .max(Integer::compare)
                    .orElse(0);
            ambito.setCodAmbito(maxId + 1);
        }

        // Auditoría - CreatedAt y CreatedBy (solo para nuevos)
        if (ambito.getCreatedBy() == null || ambito.getCreatedBy().isEmpty()) {
            ambito.setCreatedBy("admin");
        }
        if (ambito.getCreatedAt() == null) {
            ambito.setCreatedAt(LocalDateTime.now());
        }

        // Auditoría - ModifiedAt y ModifiedBy (para ediciones)
        if (ambito.getCodAmbito() != null && ambitoRepository.existsById(ambito.getCodAmbito())) {
            ambito.setModifiedBy("admin");
            ambito.setModifiedAt(LocalDateTime.now());
        }

        // Status por defecto
        if (ambito.getStatus() == null || ambito.getStatus().isEmpty()) {
            ambito.setStatus("A");
        }

        ambitoRepository.save(ambito);
        return "redirect:/ambitos";
    }

    // EDITAR (Cargar datos)
    @GetMapping("/edit/{id}")
    public String editAmbito(@PathVariable Integer id, Model model) {
        Ambito ambito = ambitoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID de ámbito inválido: " + id));
        model.addAttribute("ambito", ambito);
        return "ambitoForm";
    }

    // ELIMINAR
    @GetMapping("/delete/{id}")
    public String deleteAmbito(@PathVariable Integer id) {
        ambitoRepository.deleteById(id);
        return "redirect:/ambitos";
    }
}
