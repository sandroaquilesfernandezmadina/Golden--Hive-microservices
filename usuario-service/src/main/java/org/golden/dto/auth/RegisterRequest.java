package org.golden.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {

    @NotBlank(message = "El usuario es obliagotorio")
    private String alias;

    @Email(message = "correo inválido")
    @NotBlank(message = "el correo es obligatorio")
    private String correo;

    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 8, message = "la contraseña debe ser minimo de 8 caracteres")
    private String password;

    @NotBlank(message = "EL nombre es obligatorio")
    private String nombres;
    @NotBlank(message = "los Apellidos son obligatorios")
    private String apellidos;
    private String telefono;
    private String direccion;


}
