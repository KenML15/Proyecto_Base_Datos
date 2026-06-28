package com.telecom.link360.controller;

import com.telecom.link360.model.Llamada;
import com.telecom.link360.repository.LlamadaRepository;
import com.telecom.link360.repository.ConsumoRepository;
import com.telecom.link360.repository.FranjaHorariaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/llamadas")
public class LlamadaController {

    @Autowired
    private LlamadaRepository llamadaRepository;

    @Autowired
    private ConsumoRepository consumoRepository;

    @Autowired
    private FranjaHorariaRepository franjaHorariaRepository;

    // LEER (Listar)
    @GetMapping
    public String listLlamadas(Model model) {
        model.addAttribute("llamadas", llamadaRepository.findAll());
        return "llamadaList";
    }

    // CREAR (Formulario)
    @GetMapping("/new")
    public String showForm(Model model) {
        model.addAttribute("llamada", new Llamada());
        model.addAttribute("consumos", consumoRepository.findAll());
        model.addAttribute("franjas", franjaHorariaRepository.findAll());
        return "llamadaForm";
    }

    // GUARDAR (POST)
    @PostMapping("/save")
    public String saveLlamada(@ModelAttribute Llamada llamada) {
        // Auditoría - CreatedAt y CreatedBy (solo para nuevos)
        if (llamada.getCreatedBy() == null || llamada.getCreatedBy().isEmpty()) {
            llamada.setCreatedBy("admin");
        }
        if (llamada.getCreatedAt() == null) {
            llamada.setCreatedAt(LocalDateTime.now());
        }

        // Auditoría - ModifiedAt y ModifiedBy (para ediciones)
        if (llamada.getIdConsumo() != null && llamadaRepository.existsById(llamada.getIdConsumo())) {
            llamada.setModifiedBy("admin");
            llamada.setModifiedAt(LocalDateTime.now());
        }

        // Status por defecto
        if (llamada.getStatus() == null || llamada.getStatus().isEmpty()) {
            llamada.setStatus("A");
        }

        llamadaRepository.save(llamada);
        return "redirect:/llamadas";
    }

    // EDITAR (Cargar datos)
    @GetMapping("/edit/{id}")
    public String editLlamada(@PathVariable Integer id, Model model) {
        Llamada llamada = llamadaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID de llamada inválido: " + id));
        model.addAttribute("llamada", llamada);
        model.addAttribute("consumos", consumoRepository.findAll());
        model.addAttribute("franjas", franjaHorariaRepository.findAll());
        return "llamadaForm";
    }

    // ELIMINAR
    @GetMapping("/delete/{id}")
    public String deleteLlamada(@PathVariable Integer id) {
        llamadaRepository.deleteById(id);
        return "redirect:/llamadas";
    }
}
