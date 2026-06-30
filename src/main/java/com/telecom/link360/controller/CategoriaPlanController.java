package com.telecom.link360.controller;

import com.telecom.link360.model.CategoriaPlan;
import com.telecom.link360.repository.CategoriaPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/categorias")
public class CategoriaPlanController {

    @Autowired
    private CategoriaPlanRepository categoriaPlanRepository;

    // LEER (Listar)
    @GetMapping
    public String listCategorias(Model model) {
        model.addAttribute("categorias", categoriaPlanRepository.findAll());
        return "categoriaList";
    }

    // CREAR (Formulario)
    @GetMapping("/new")
    public String showForm(Model model) {
        model.addAttribute("categoria", new CategoriaPlan());
        return "categoriaForm";
    }

    // GUARDAR (POST)
    @PostMapping("/save")
    public String saveCategoria(@ModelAttribute CategoriaPlan categoria) {
        // Auditoría - CreatedAt y CreatedBy (solo para nuevos)
        if (categoria.getCreatedBy() == null || categoria.getCreatedBy().isEmpty()) {
            categoria.setCreatedBy("admin");
        }
        if (categoria.getCreatedAt() == null) {
            categoria.setCreatedAt(LocalDateTime.now());
        }

        // Auditoría - ModifiedAt y ModifiedBy (para ediciones)
        if (categoria.getCodCategoria() != null && categoriaPlanRepository.existsById(categoria.getCodCategoria())) {
            categoria.setModifiedBy("admin");
            categoria.setModifiedAt(LocalDateTime.now());
        }

        // Status por defecto
        if (categoria.getStatus() == null || categoria.getStatus().isEmpty()) {
            categoria.setStatus("A");
        }

        categoriaPlanRepository.save(categoria);
        return "redirect:/categorias";
    }

    // EDITAR (Cargar datos)
    @GetMapping("/edit/{id}")
    public String editCategoria(@PathVariable Integer id, Model model) {
        CategoriaPlan categoria = categoriaPlanRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID de categoría inválido: " + id));
        model.addAttribute("categoria", categoria);
        return "categoriaForm";
    }

    // ELIMINAR
    @GetMapping("/delete/{id}")
    public String deleteCategoria(@PathVariable Integer id) {
        categoriaPlanRepository.deleteById(id);
        return "redirect:/categorias";
    }
}