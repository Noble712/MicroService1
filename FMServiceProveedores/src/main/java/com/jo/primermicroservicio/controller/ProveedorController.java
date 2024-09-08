package com.jo.primermicroservicio.controller;

import org.springframework.web.bind.annotation.*;
import com.jo.primermicroservicio.entity.Proveedor;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/proveedores")
public class ProveedorController {
    private List<Proveedor> proveedores = new ArrayList<>();

    @PostMapping
    public Proveedor crearProveedor(@RequestBody Proveedor proveedor) {
        proveedor.setId((long) (proveedores.size() + 1));
        proveedores.add(proveedor);
        return proveedor;
    }

    @GetMapping
    public List<Proveedor> listarProveedores() {
        return proveedores;
    }

    @GetMapping("/{id}")
    public Proveedor obtenerProveedor(@PathVariable Long id) {
        return proveedores.stream().filter(p -> p.getId().equals(id)).findFirst().orElse(null);
    }

    @PutMapping("/{id}")
    public Proveedor actualizarProveedor(@PathVariable Long id, @RequestBody Proveedor proveedorActualizado) {
        Proveedor proveedor = obtenerProveedor(id);
        if (proveedor != null) {
            proveedor.setNombre(proveedorActualizado.getNombre());
            proveedor.setDireccion(proveedorActualizado.getDireccion());
            proveedor.setTelefono(proveedorActualizado.getTelefono());
        }
        return proveedor;
    }

    @DeleteMapping("/{id}")
    public void eliminarProveedor(@PathVariable Long id) {
        proveedores.removeIf(p -> p.getId().equals(id));
    }
}
