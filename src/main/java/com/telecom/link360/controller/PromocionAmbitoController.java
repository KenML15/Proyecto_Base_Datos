package com.telecom.link360.controller;

import com.telecom.link360.model.PromocionAmbito;
import com.telecom.link360.model.PromocionAmbitoId;
import com.telecom.link360.repository.PromocionAmbitoRepository;
import com.telecom.link360.repository.PromocionRepository;
import com.telecom.link360.repository.AmbitoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/promociones-ambitos")
public class PromocionAmbitoController {

    @Autowired
    private PromocionAmbitoRepository promocionAmbitoRepository;

    @Autowired
    private PromocionRepository promocionRepository;

    @Autowired
    private AmbitoRepository ambitoRepository;

    // LEER (Listar)
    @GetMapping
    public String listPromocionesAmbitos(Model model) {
        model.addAttribute("promocionesAmbitos", promocionAmbitoRepository.findAll());
        return "promocionAmbitoList";
    }

    // CREAR (Formulario)
    @GetMapping("/new")
    public String showForm(Model model) {
        model.addAttribute("promocionAmbito", new PromocionAmbito());
        model.addAttribute("promociones", promocionRepository.findAll());
        model.addAttribute("ambitos", ambitoRepository.findAll());
        return "promocionAmbitoForm";
    }

    // GUARDAR (POST)
    @PostMapping("/save")
    public String savePromocionAmbito(@ModelAttribute PromocionAmbito promocionAmbito) {
        // Auditoría - CreatedAt y CreatedBy (solo para nuevos)
        if (promocionAmbito.getCreatedBy() == null || promocionAmbito.getCreatedBy().isEmpty()) {
            promocionAmbito.setCreatedBy("admin");
        }
        if (promocionAmbito.getCreatedAt() == null) {
            promocionAmbito.setCreatedAt(LocalDateTime.now());
        }

        // Status por defecto
        if (promocionAmbito.getStatus() == null || promocionAmbito.getStatus().isEmpty()) {
            promocionAmbito.setStatus("A");
        }

        promocionAmbitoRepository.save(promocionAmbito);
        return "redirect:/promociones-ambitos";
    }

    // EDITAR (Cargar datos)
    @GetMapping("/edit/{codPromocion}/{codAmbito}")
    public String editPromocionAmbito(@PathVariable Integer codPromocion, @PathVariable Integer codAmbito, Model model) {
        PromocionAmbitoId id = new PromocionAmbitoId(codPromocion, codAmbito);
        PromocionAmbito promocionAmbito = promocionAmbitoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID de promocion ambito inválido"));
        model.addAttribute("promocionAmbito", promocionAmbito);
        model.addAttribute("promociones", promocionRepository.findAll());
        model.addAttribute("ambitos", ambitoRepository.findAll());
        return "promocionAmbitoForm";
    }

    // ELIMINAR
    @GetMapping("/delete/{codPromocion}/{codAmbito}")
    public String deletePromocionAmbito(@PathVariable Integer codPromocion, @PathVariable Integer codAmbito) {
        PromocionAmbitoId id = new PromocionAmbitoId(codPromocion, codAmbito);
        promocionAmbitoRepository.deleteById(id);
        return "redirect:/promociones-ambitos";
    }
}
