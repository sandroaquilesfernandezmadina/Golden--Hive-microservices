package org.golden.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.golden.dto.empleado.EmpleadoRequest;
import org.golden.dto.empleado.EmpleadoResponse;
import org.golden.dto.usuario.EstadoRequest;
import org.golden.service.EmpleadoService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/empleados")
@RequiredArgsConstructor
public class    EmpleadoController {

    private  final EmpleadoService empleadoService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<EmpleadoResponse> findAll(){
        return empleadoService.findAll();
    }

    //Crear empleado
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping("/registrar")
    public EmpleadoResponse register(
            @Valid @RequestBody EmpleadoRequest request){

        return empleadoService.register(request);
    }

    @PatchMapping("/{id}/estado")
    @PreAuthorize("hasRole('ADMIN')")
    public void cambiarEstado(@PathVariable Integer id,
                              @RequestBody EstadoRequest request){
        empleadoService.cambiarEstado(
                id,
                request.getEstado());
    }


    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public EmpleadoResponse findById(@PathVariable Integer id){
        return empleadoService.findById(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping("/actualizar")
    public EmpleadoResponse update(@PathVariable Integer id, @RequestBody EmpleadoRequest request){
        return empleadoService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(@PathVariable Integer id){
        empleadoService.delete(id);
    }


}
