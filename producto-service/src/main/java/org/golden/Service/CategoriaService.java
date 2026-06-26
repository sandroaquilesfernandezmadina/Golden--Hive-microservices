package org.golden.Service;

import org.golden.Dto.CategoriaRequest;
import org.golden.Dto.CategoriaResponse;

import java.util.List;

public interface CategoriaService {

    //listar todas las categorias.
    List<CategoriaResponse> findAll();
    //listar categorias por id
    CategoriaResponse findById (Integer id);
    //guardar una nueva categoria
    CategoriaResponse save(CategoriaRequest request);
    //actualizar una categoria obteniendo por su id
    CategoriaResponse update(Integer id, CategoriaRequest request);
    //eliminar categoria
    void delete(Integer id);
}
