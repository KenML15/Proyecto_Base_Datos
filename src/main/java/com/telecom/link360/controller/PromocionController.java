package com.telecom.link360.controller;

import com.telecom.link360.model.Promocion;
import com.telecom.link360.repository.PromocionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/promociones")
public class PromocionController {

    @Autowired
    private PromocionRepository promocionRepository;

    // LEER (Listar)
    @GetMapping
    public String listPromociones(Model model) {
        model.addAttribute("promociones", promocionRepository.findAll());
        return "promocionList";
    }

    // CREAR (Formulario)
    @GetMapping("/new")
    public String showForm(Model model) {
        model.addAttribute("promocion", new Promocion());
        return "promocionForm";
    }

    // GUARDAR (POST)
    @PostMapping("/save")
    public String savePromocion(@ModelAttribute Promocion promocion) {
        // Generar ID manual si es nuevo
        if (promocion.getCodPromocion() == null) {
            Integer maxId = promocionRepository.findAll()
                    .stream()
                    .map(Promocion::getCodPromocion)
                    .max(Integer::compare)
                    .orElse(0);
            promocion.setCodPromocion(maxId + 1);
        }

        // Auditoría - CreatedAt y CreatedBy (solo para nuevos)
        if (promocion.getCreatedBy() == null || promocion.getCreatedBy().isEmpty()) {
            promocion.setCreatedBy("admin");
        }
        if (promocion.getCreatedAt() == null) {
            promocion.setCreatedAt(LocalDateTime.now());
        }

        // Auditoría - ModifiedAt y ModifiedBy (para ediciones)
        if (promocion.getCodPromocion() != null && promocionRepository.existsById(promocion.getCodPromocion())) {
            promocion.setModifiedBy("admin");
            promocion.setModifiedAt(LocalDateTime.now());
        }

        // Status por defecto
        if (promocion.getStatus() == null || promocion.getStatus().isEmpty()) {
            promocion.setStatus("A");
        }

        promocionRepository.save(promocion);
        return "redirect:/promociones";
    }

    // EDITAR (Cargar datos)
    @GetMapping("/edit/{id}")
    public String editPromocion(@PathVariable Integer id, Model model) {
        Promocion promocion = promocionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID de promoción inválido: " + id));
        model.addAttribute("promocion", promocion);
        return "promocionForm";
    }

    // ELIMINAR
    @GetMapping("/delete/{id}")
    public String deletePromocion(@PathVariable Integer id) {
        promocionRepository.deleteById(id);
        return "redirect:/promociones";
    }
}
