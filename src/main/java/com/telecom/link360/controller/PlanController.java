package com.telecom.link360.controller;

import com.telecom.link360.model.Plan;
import com.telecom.link360.repository.PlanRepository;
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

    @GetMapping
    public String listPlanes(Model model) {
        model.addAttribute("planes", planRepository.findAll());
        return "planList";
    }

    @GetMapping("/new")
    public String showForm(Model model) {
        model.addAttribute("plan", new Plan());
        return "planForm";
    }

    @PostMapping("/save")
    public String savePlan(@ModelAttribute Plan plan) {
        // 1. Generar ID manual si es nuevo
        if (plan.getId() == null) {
            Integer maxId = planRepository.findAll().stream()
                    .map(Plan::getId)
                    .filter(id -> id != null)
                    .max(Integer::compare)
                    .orElse(0);
            plan.setId(maxId + 1);
        }

        // 2. Asignar Categoría obligatoria (defecto: 1)
        if (plan.getCodCategoria() == null) {
            plan.setCodCategoria(1);
        }

        // 3. Auditoría - CreatedAt y CreatedBy (solo para nuevos)
        if (plan.getCreatedBy() == null || plan.getCreatedBy().isEmpty()) {
            plan.setCreatedBy("admin");
        }
        if (plan.getCreatedAt() == null) {
            plan.setCreatedAt(LocalDateTime.now());
        }

        // 4. Auditoría - ModifiedAt y ModifiedBy (para ediciones)
        if (plan.getId() != null && planRepository.existsById(plan.getId())) {
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

    @GetMapping("/edit/{id}")
    public String editPlan(@PathVariable("id") Integer id, Model model) {
        Plan plan = planRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID de plan inválido: " + id));
        model.addAttribute("plan", plan);
        return "planForm";
    }

    @GetMapping("/delete/{id}")
    public String deletePlan(@PathVariable("id") Integer id) {
        planRepository.deleteById(id);
        return "redirect:/planes";
    }
}
