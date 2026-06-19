package org.golden.dto.usuario;

import lombok.Getter;
import lombok.Setter;
import org.golden.entity.EstadoUsuario;

@Getter
@Setter
public class EstadoRequest {
    private EstadoUsuario estado;
}
