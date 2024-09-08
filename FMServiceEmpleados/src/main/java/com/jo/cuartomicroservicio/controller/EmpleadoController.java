package com.jo.cuartomicroservicio.controller;

import org.springframework.web.bind.annotation.*;
import com.jo.cuartomicroservicio.entity.Empleado;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/empleados")
public class EmpleadoController {
    private List<Empleado> empleados = new ArrayList<>();

    @PostMapping
    public Empleado crearEmpleado(@RequestBody Empleado empleado) {
        empleado.setId((long) (empleados.size() + 1));
        empleados.add(empleado);
        return empleado;
    }

    @GetMapping
    public List<Empleado> listarEmpleados() {
        return empleados;
    }

    @GetMapping("/{id}")
    public Empleado obtenerEmpleado(@PathVariable Long id) {
        return empleados.stream().filter(e -> e.getId().equals(id)).findFirst().orElse(null);
    }

    @PutMapping("/{id}")
    public Empleado actualizarEmpleado(@PathVariable Long id, @RequestBody Empleado empleadoActualizado) {
        Empleado empleado = obtenerEmpleado(id);
        if (empleado != null) {
            empleado.setNombre(empleadoActualizado.getNombre());
            empleado.setPuesto(empleadoActualizado.getPuesto());
            empleado.setSalario(empleadoActualizado.getSalario());
        }
        return empleado;
    }

    @DeleteMapping("/{id}")
    public void eliminarEmpleado(@PathVariable Long id) {
        empleados.removeIf(e -> e.getId().equals(id));
    }
}
