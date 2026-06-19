package org.golden.service;

import org.golden.dto.auth.RegisterRequest;
import org.golden.dto.empleado.EmpleadoRequest;
import org.golden.dto.empleado.EmpleadoResponse;
import org.golden.entity.EstadoUsuario;

import java.util.List;

public interface EmpleadoService {
    //listar todos los empleados.
    List<EmpleadoResponse> findAll();
    //crear empleados y devuelve datos seguros.
    EmpleadoResponse register(EmpleadoRequest request);
    //ontener el empleado por id.
    EmpleadoResponse findById(Integer id);
    //axtualizar datos de los empleados.
    EmpleadoResponse update(Integer id, EmpleadoRequest request);
    // eliminar Empleados.
    public void delete(Integer id);

    //para cambiar estado de los Empleados
    void cambiarEstado(Integer empleadoId, EstadoUsuario estado);
}
