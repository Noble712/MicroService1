package com.jo.tercermicroservicio.controller;

import org.springframework.web.bind.annotation.*;
import com.jo.tercermicroservicio.entity.Factura;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/facturas")
public class FacturaController {
    private List<Factura> facturas = new ArrayList<>();

    @PostMapping
    public Factura crearFactura(@RequestBody Factura factura) {
        factura.setId((long) (facturas.size() + 1));
        facturas.add(factura);
        return factura;
    }

    @GetMapping
    public List<Factura> listarFacturas() {
        return facturas;
    }

    @GetMapping("/{id}")
    public Factura obtenerFactura(@PathVariable Long id) {
        return facturas.stream().filter(f -> f.getId().equals(id)).findFirst().orElse(null);
    }

    @PutMapping("/{id}")
    public Factura actualizarFactura(@PathVariable Long id, @RequestBody Factura facturaActualizada) {
        Factura factura = obtenerFactura(id);
        if (factura != null) {
            factura.setClienteId(facturaActualizada.getClienteId());
            factura.setMonto(facturaActualizada.getMonto());
            factura.setFecha(facturaActualizada.getFecha());
        }
        return factura;
    }

    @DeleteMapping("/{id}")
    public void eliminarFactura(@PathVariable Long id) {
        facturas.removeIf(f -> f.getId().equals(id));
    }
}
