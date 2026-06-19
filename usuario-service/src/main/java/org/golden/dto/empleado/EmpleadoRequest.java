package org.golden.dto.empleado;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;


@Setter
@Getter
public class EmpleadoRequest {

    @NotBlank(message = "El usuario es obliagotorio")
    private String alias;
    @Email(message = "correo inválido")
    @NotBlank(message = "el correo es obligatorio")
    private String correo;
    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 8, message = "la contraseña debe ser minimo de 8 caracteres")
    private String password;

    @NotBlank(message = "El nombre es obligatorio")
    private String nombres;
    @NotBlank(message = "El apellido es obligatorio")
    private String apellidos;
    @NotBlank(message = "El numero de telefono es obligatorio")
    private String telefono;
    @NotBlank(message = "El cargo es obligatorio")
    private String cargo;
    @NotNull(message = "El salario es obligatorio")
    private BigDecimal salario;
}
