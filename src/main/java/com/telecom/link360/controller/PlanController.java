package com.telecom.link360.controller;

import com.telecom.link360.model.Plan;
import com.telecom.link360.model.CategoriaPlan;
import com.telecom.link360.repository.PlanRepository;
import com.telecom.link360.repository.CategoriaPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/planes")
public class PlanController {

    @Autowired
    private PlanRepository planRepository;

    @Autowired
    private CategoriaPlanRepository categoriaPlanRepository;

    // LEER (Listar)
    @GetMapping
    public String listPlanes(Model model) {
        model.addAttribute("planes", planRepository.findAll());
        return "planList";
    }

    // CREAR (Formulario)
    @GetMapping("/new")
    public String showForm(Model model) {
        model.addAttribute("plan", new Plan());
        model.addAttribute("categorias", categoriaPlanRepository.findAll());
        return "planForm";
    }

    // GUARDAR (POST)
    @PostMapping("/save")
    public String savePlan(@ModelAttribute Plan plan) {

        // 1. NO generar ID manualmente - SQL Server lo hará automáticamente
        // Solo si es edición (ID existe), mantenerlo
        // Si es nuevo (ID es null), dejarlo null para que se auto-genere

        // 2. Validar que la categoría existe
        if (plan.getCodCategoria() == null || plan.getCodCategoria() <= 0) {
            throw new IllegalArgumentException("Debe seleccionar una categoría válida");
        }

        CategoriaPlan categoria = categoriaPlanRepository.findById(plan.getCodCategoria())
                .orElseThrow(() -> new IllegalArgumentException("Categoría no encontrada: " + plan.getCodCategoria()));

        // 3. Auditoría - CreatedBy y CreatedAt (solo para nuevos)
        if (plan.getCreatedBy() == null || plan.getCreatedBy().isEmpty()) {
            plan.setCreatedBy("admin");
        }
        if (plan.getCreatedAt() == null) {
            plan.setCreatedAt(LocalDateTime.now());
        }

        // 4. Auditoría - ModifiedAt y ModifiedBy (para ediciones)
        if (plan.getCodPlan() != null && planRepository.existsById(plan.getCodPlan())) {
            plan.setModifiedBy("admin");
            plan.setModifiedAt(LocalDateTime.now());
        }

        // 5. Status por defecto
        if (plan.getStatus() == null || plan.getStatus().isEmpty()) {
            plan.setStatus("A");
        }

        planRepository.save(plan);
        return "redirect:/planes";
    }

    // EDITAR (Cargar datos)
    @GetMapping("/edit/{id}")
    public String editPlan(@PathVariable("id") Integer id, Model model) {
        Plan plan = planRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID de plan inválido: " + id));
        model.addAttribute("plan", plan);
        model.addAttribute("categorias", categoriaPlanRepository.findAll());
        return "planForm";
    }

    // ELIMINAR
    @GetMapping("/delete/{id}")
    public String deletePlan(@PathVariable("id") Integer id) {
        planRepository.deleteById(id);
        return "redirect:/planes";
    }
}