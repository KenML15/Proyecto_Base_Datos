package com.telecom.link360.controller;

import com.telecom.link360.model.Consumo;
import com.telecom.link360.repository.ConsumoRepository;
import com.telecom.link360.repository.LineaMovilRepository;
import com.telecom.link360.repository.AmbitoRepository;
import com.telecom.link360.repository.FranjaHorariaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/consumos")
public class ConsumoController {

    @Autowired
    private ConsumoRepository consumoRepository;

    @Autowired
    private LineaMovilRepository lineaMovilRepository;

    @Autowired
    private AmbitoRepository ambitoRepository;

    @Autowired
    private FranjaHorariaRepository franjaHorariaRepository;

    // LEER (Listar)
    @GetMapping
    public String listConsumos(Model model) {
        model.addAttribute("consumos", consumoRepository.findAll());
        return "consumoList";
    }

    // CREAR (Formulario)
    @GetMapping("/new")
    public String showForm(Model model) {
        model.addAttribute("consumo", new Consumo());
        model.addAttribute("lineas", lineaMovilRepository.findAll());
        model.addAttribute("ambitos", ambitoRepository.findAll());
        model.addAttribute("franjas", franjaHorariaRepository.findAll());
        return "consumoForm";
    }

    // GUARDAR (POST)
    @PostMapping("/save")
    public String saveConsumo(@ModelAttribute Consumo consumo) {
        // Generar ID manual si es nuevo
        if (consumo.getIdConsumo() == null) {
            Integer maxId = consumoRepository.findAll()
                    .stream()
                    .map(Consumo::getIdConsumo)
                    .max(Integer::compare)
                    .orElse(0);
            consumo.setIdConsumo(maxId + 1);
        }

        // Auditoría - CreatedAt y CreatedBy (solo para nuevos)
        if (consumo.getCreatedBy() == null || consumo.getCreatedBy().isEmpty()) {
            consumo.setCreatedBy("admin");
        }
        if (consumo.getCreatedAt() == null) {
            consumo.setCreatedAt(LocalDateTime.now());
        }

        // Auditoría - ModifiedAt y ModifiedBy (para ediciones)
        if (consumo.getIdConsumo() != null && consumoRepository.existsById(consumo.getIdConsumo())) {
            consumo.setModifiedBy("admin");
            consumo.setModifiedAt(LocalDateTime.now());
        }

        // Status por defecto
        if (consumo.getStatus() == null || consumo.getStatus().isEmpty()) {
            consumo.setStatus("A");
        }

        consumoRepository.save(consumo);
        return "redirect:/consumos";
    }

    // EDITAR (Cargar datos)
    @GetMapping("/edit/{id}")
    public String editConsumo(@PathVariable Integer id, Model model) {
        Consumo consumo = consumoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID de consumo inválido: " + id));
        model.addAttribute("consumo", consumo);
        model.addAttribute("lineas", lineaMovilRepository.findAll());
        model.addAttribute("ambitos", ambitoRepository.findAll());
        model.addAttribute("franjas", franjaHorariaRepository.findAll());
        return "consumoForm";
    }

    // ELIMINAR
    @GetMapping("/delete/{id}")
    public String deleteConsumo(@PathVariable Integer id) {
        consumoRepository.deleteById(id);
        return "redirect:/consumos";
    }
}
