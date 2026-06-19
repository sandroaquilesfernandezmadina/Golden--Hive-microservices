package org.golden.dto.empleado;

import lombok.AllArgsConstructor;
import lombok.Getter;


import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class EmpleadoResponse {
    private Integer empleadoId;
    private String nombres;
    private String apellidos;
    private String telefono;
    private String cargo;
    private LocalDateTime fechaContratacion;
    private BigDecimal salario;

    //pertenece a usuario
    private String estado;
}
