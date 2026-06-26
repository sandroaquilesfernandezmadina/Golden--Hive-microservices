package org.golden.Dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
//EL REQUES  NOS PERMITE REGISTRAR EN LA BASE DE DATOS
public class CategoriaRequest {
    @NotBlank(message = "El nombre de la categoria es obligatorio")
    private String nombreCategoria;
}
