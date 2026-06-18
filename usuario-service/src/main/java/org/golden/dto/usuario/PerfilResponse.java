package org.golden.dto.usuario;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PerfilResponse {
    private Integer usuarioId;
    private String alias;
    private String correo;
    private String rol;
}
