package org.golden.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {
    @Email(message = "correo inválido")
    @NotBlank(message = "el correo es  obligatorio")
    private String correo;

    @NotBlank(message = "la contraseña es obligatoria")
    private String password;
}
