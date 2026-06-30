package com.telecom.link360.controller;

import com.telecom.link360.model.Llamada;
import com.telecom.link360.model.LlamadaId;
import com.telecom.link360.model.Consumo;
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

        // 1. Validar que el consumo existe
        if (llamada.getConsumo() == null || llamada.getConsumo().getIdConsumo() == null) {
            throw new IllegalArgumentException("Debe seleccionar un consumo válido");
        }

        Consumo consumo = consumoRepository.findById(llamada.getConsumo().getIdConsumo())
                .orElseThrow(() -> new IllegalArgumentException("Consumo no encontrado"));

        llamada.setConsumo(consumo);

        // 2. Auditoría - CreatedBy y CreatedAt (solo para nuevos)
        if (llamada.getCreatedBy() == null || llamada.getCreatedBy().isEmpty()) {
            llamada.setCreatedBy("admin");
        }
        if (llamada.getCreatedAt() == null) {
            llamada.setCreatedAt(LocalDateTime.now());
        }

        // 3. Auditoría - ModifiedAt y ModifiedBy (para ediciones)
        // ✅ CORREGIDO: Usar LlamadaId en lugar de Integer
        if (llamada.getIdConsumo() != null) {
            LlamadaId id = new LlamadaId(llamada.getIdConsumo());
            if (llamadaRepository.existsById(id)) {
                llamada.setModifiedBy("admin");
                llamada.setModifiedAt(LocalDateTime.now());
            }
        }

        // 4. Status por defecto
        if (llamada.getStatus() == null || llamada.getStatus().isEmpty()) {
            llamada.setStatus("A");
        }

        llamadaRepository.save(llamada);
        return "redirect:/llamadas";
    }

    // EDITAR (Cargar datos)
    @GetMapping("/edit/{id}")
    public String editLlamada(@PathVariable Integer id, Model model) {
        LlamadaId llamadaId = new LlamadaId(id);
        Llamada llamada = llamadaRepository.findById(llamadaId)
                .orElseThrow(() -> new IllegalArgumentException("Llamada no encontrada para el consumo: " + id));

        model.addAttribute("llamada", llamada);
        model.addAttribute("consumos", consumoRepository.findAll());
        model.addAttribute("franjas", franjaHorariaRepository.findAll());
        return "llamadaForm";
    }

    // ELIMINAR
    @GetMapping("/delete/{id}")
    public String deleteLlamada(@PathVariable Integer id) {
        LlamadaId llamadaId = new LlamadaId(id);
        llamadaRepository.deleteById(llamadaId);
        return "redirect:/llamadas";
    }
}