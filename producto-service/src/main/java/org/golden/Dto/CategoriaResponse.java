package org.golden.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
//EL RESPONSE NOS PERMITE PARA LISTAR LISTARNOS O MOSTRARNOS LOS
// REGISTROS DE LA BASE DE DATOS
public class CategoriaResponse {
    private Integer categoriaId;
    private String nombreCategoria;
}
