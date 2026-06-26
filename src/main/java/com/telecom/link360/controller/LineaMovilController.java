package com.telecom.link360.controller;

import com.telecom.link360.model.LineaMovil;
import com.telecom.link360.repository.CustomerRepository;
import com.telecom.link360.repository.LineaMovilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/lineas")
public class LineaMovilController {

    @Autowired
    private LineaMovilRepository lineaRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping
    public String listLineas(Model model) {
        model.addAttribute("lineas", lineaRepository.findAll());
        return "lineaList";
    }

    @GetMapping("/new")
    public String showForm(Model model) {
        model.addAttribute("linea", new LineaMovil());
        model.addAttribute("customers", customerRepository.findAll()); // Lista de clientes
        return "lineaForm";
    }

    @PostMapping("/save")
    public String saveLinea(@ModelAttribute LineaMovil linea) {
        if (linea.getStatus() == null || linea.getStatus().isEmpty()) {
            linea.setStatus("A");
        }

        if (linea.getFechaActivacion() == null) {
            linea.setFechaActivacion(LocalDate.now());
        }

        lineaRepository.save(linea);
        return "redirect:/lineas";
    }

    // Método para borrar
    @GetMapping("/delete/{id}")
    public String deleteLinea(@PathVariable String id) {
        lineaRepository.deleteById(id);
        return "redirect:/lineas";
    }

    // Método para editar (cargar datos al formulario)
    @GetMapping("/edit/{id}")
    public String editLinea(@PathVariable String id, Model model) {
        model.addAttribute("linea", lineaRepository.findById(id).get());
        model.addAttribute("customers", customerRepository.findAll());
        return "lineaForm";
    }
}