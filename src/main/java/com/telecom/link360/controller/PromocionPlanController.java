package com.telecom.link360.controller;

import com.telecom.link360.model.PromocionPlan;
import com.telecom.link360.model.PromocionPlanId;
import com.telecom.link360.repository.PromocionPlanRepository;
import com.telecom.link360.repository.PromocionRepository;
import com.telecom.link360.repository.PlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/promociones-planes")
public class PromocionPlanController {

    @Autowired
    private PromocionPlanRepository promocionPlanRepository;

    @Autowired
    private PromocionRepository promocionRepository;

    @Autowired
    private PlanRepository planRepository;

    // LEER (Listar)
    @GetMapping
    public String listPromocionesPlanes(Model model) {
        model.addAttribute("promocionesPlanes", promocionPlanRepository.findAll());
        return "promocionPlanList";
    }

    // CREAR (Formulario)
    @GetMapping("/new")
    public String showForm(Model model) {
        model.addAttribute("promocionPlan", new PromocionPlan());
        model.addAttribute("promociones", promocionRepository.findAll());
        model.addAttribute("planes", planRepository.findAll());
        return "promocionPlanForm";
    }

    // GUARDAR (POST)
    @PostMapping("/save")
    public String savePromocionPlan(@ModelAttribute PromocionPlan promocionPlan) {
        // Auditoría - CreatedAt y CreatedBy (solo para nuevos)
        if (promocionPlan.getCreatedBy() == null || promocionPlan.getCreatedBy().isEmpty()) {
            promocionPlan.setCreatedBy("admin");
        }
        if (promocionPlan.getCreatedAt() == null) {
            promocionPlan.setCreatedAt(LocalDateTime.now());
        }

        // Status por defecto
        if (promocionPlan.getStatus() == null || promocionPlan.getStatus().isEmpty()) {
            promocionPlan.setStatus("A");
        }

        promocionPlanRepository.save(promocionPlan);
        return "redirect:/promociones-planes";
    }

    // EDITAR (Cargar datos)
    @GetMapping("/edit/{codPromocion}/{codPlan}")
    public String editPromocionPlan(@PathVariable Integer codPromocion, @PathVariable Integer codPlan, Model model) {
        PromocionPlanId id = new PromocionPlanId(codPromocion, codPlan);
        PromocionPlan promocionPlan = promocionPlanRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID de promocion plan inválido"));
        model.addAttribute("promocionPlan", promocionPlan);
        model.addAttribute("promociones", promocionRepository.findAll());
        model.addAttribute("planes", planRepository.findAll());
        return "promocionPlanForm";
    }

    // ELIMINAR
    @GetMapping("/delete/{codPromocion}/{codPlan}")
    public String deletePromocionPlan(@PathVariable Integer codPromocion, @PathVariable Integer codPlan) {
        PromocionPlanId id = new PromocionPlanId(codPromocion, codPlan);
        promocionPlanRepository.deleteById(id);
        return "redirect:/promociones-planes";
    }
}
