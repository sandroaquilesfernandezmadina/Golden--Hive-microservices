package org.golden.dto.auth;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {
    private String userName;
    private String correo;
    private String password;

    private String nombres;
    private String apellidos;
    private String telefono;
    private String direccion;


}
