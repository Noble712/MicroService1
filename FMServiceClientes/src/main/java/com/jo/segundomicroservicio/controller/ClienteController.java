package com.jo.segundomicroservicio.controller;

import org.springframework.web.bind.annotation.*;
import com.jo.segundomicroservicio.entity.Cliente;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    private List<Cliente> clientes = new ArrayList<>();

    @PostMapping
    public Cliente crearCliente(@RequestBody Cliente cliente) {
        cliente.setId((long) (clientes.size() + 1));
        clientes.add(cliente);
        return cliente;
    }

    @GetMapping
    public List<Cliente> listarClientes() {
        return clientes;
    }

    @GetMapping("/{id}")
    public Cliente obtenerCliente(@PathVariable Long id) {
        return clientes.stream().filter(c -> c.getId().equals(id)).findFirst().orElse(null);
    }

    @PutMapping("/{id}")
    public Cliente actualizarCliente(@PathVariable Long id, @RequestBody Cliente clienteActualizado) {
        Cliente cliente = obtenerCliente(id);
        if (cliente != null) {
            cliente.setNombre(clienteActualizado.getNombre());
            cliente.setCorreo(clienteActualizado.getCorreo());
            cliente.setTelefono(clienteActualizado.getTelefono());
        }
        return cliente;
    }

    @DeleteMapping("/{id}")
    public void eliminarCliente(@PathVariable Long id) {
        clientes.removeIf(c -> c.getId().equals(id));
    }
}
