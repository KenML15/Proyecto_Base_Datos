package com.telecom.link360.controller;

import com.telecom.link360.model.DetalleFactura;
import com.telecom.link360.model.DetalleFacturaId;
import com.telecom.link360.repository.DetalleFacturaRepository;
import com.telecom.link360.repository.FacturaRepository;
import com.telecom.link360.repository.ConsumoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/detalles-facturas")
public class DetalleFacturaController {

    @Autowired
    private DetalleFacturaRepository detalleFacturaRepository;

    @Autowired
    private FacturaRepository facturaRepository;

    @Autowired
    private ConsumoRepository consumoRepository;

    // LEER (Listar)
    @GetMapping
    public String listDetalles(Model model) {
        model.addAttribute("detalles", detalleFacturaRepository.findAll());
        return "detalleFacturaList";
    }

    // CREAR (Formulario)
    @GetMapping("/new")
    public String showForm(Model model) {
        model.addAttribute("detalle", new DetalleFactura());
        model.addAttribute("facturas", facturaRepository.findAll());
        model.addAttribute("consumos", consumoRepository.findAll());
        return "detalleFacturaForm";
    }

    // GUARDAR (POST)
    @PostMapping("/save")
    public String saveDetalleFactura(@ModelAttribute DetalleFactura detalle) {
        // Asignar Id_LineaDetalle manualmente si es un registro nuevo
        if (detalle.getIdLineaDetalle() == null) {
            Integer maxId = detalleFacturaRepository.findMaxIdLineaDetalleByNumFactura(detalle.getNumFactura());
            detalle.setIdLineaDetalle(maxId == null ? 1 : maxId + 1);
        }

        // Auditoría - CreatedAt y CreatedBy (solo para nuevos)
        if (detalle.getCreatedBy() == null || detalle.getCreatedBy().isEmpty()) {
            detalle.setCreatedBy("admin");
        }
        if (detalle.getCreatedAt() == null) {
            detalle.setCreatedAt(LocalDateTime.now());
        }

        // Status por defecto
        if (detalle.getStatus() == null || detalle.getStatus().isEmpty()) {
            detalle.setStatus("A");
        }

        detalleFacturaRepository.save(detalle);
        return "redirect:/detalles-facturas";
    }

    // EDITAR (Cargar datos)
    @GetMapping("/edit/{numFactura}/{idLinea}")
    public String editDetalleFactura(@PathVariable Integer numFactura, @PathVariable Integer idLinea, Model model) {
        DetalleFacturaId id = new DetalleFacturaId(numFactura, idLinea);
        DetalleFactura detalle = detalleFacturaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID de detalle factura inválido"));
        model.addAttribute("detalle", detalle);
        model.addAttribute("facturas", facturaRepository.findAll());
        model.addAttribute("consumos", consumoRepository.findAll());
        return "detalleFacturaForm";
    }

    // ELIMINAR
    @GetMapping("/delete/{numFactura}/{idLinea}")
    public String deleteDetalleFactura(@PathVariable Integer numFactura, @PathVariable Integer idLinea) {
        DetalleFacturaId id = new DetalleFacturaId(numFactura, idLinea);
        detalleFacturaRepository.deleteById(id);
        return "redirect:/detalles-facturas";
    }
}