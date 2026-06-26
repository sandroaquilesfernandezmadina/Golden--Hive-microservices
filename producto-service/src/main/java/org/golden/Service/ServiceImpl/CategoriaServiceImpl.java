package org.golden.Service.ServiceImpl;

import lombok.RequiredArgsConstructor;
import org.golden.Dto.CategoriaRequest;
import org.golden.Dto.CategoriaResponse;
import org.golden.Service.CategoriaService;
import org.golden.entity.Categoria;
import org.golden.exception.BadRequestException;
import org.golden.exception.ResourceNotFoundException;
import org.golden.repository.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoriaServiceImpl implements CategoriaService {

    private final CategoriaRepository categoriaRepository;


    @Override
    public List<CategoriaResponse> findAll() {
        return categoriaRepository.findAll()
                .stream()
                .map(categoria -> new CategoriaResponse(
                        categoria.getCategoriaId(),
                        categoria.getNombreCategoria()
                ))
                .toList();
    }

    @Override
    public CategoriaResponse findById(Integer id) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("categoria no encontrado con el id: " + id));
        return new CategoriaResponse(
                categoria.getCategoriaId(),
                categoria.getNombreCategoria()
        );
    }

    @Override
    public CategoriaResponse save(CategoriaRequest request) {
        //validacion de ingreso de categorias duplicadas
        if(categoriaRepository.findBynombreCategoria(request.getNombreCategoria()).isPresent()) {
            throw new BadRequestException("El nombre de la categoria ya existe");
        }
        //crear la categoria
        Categoria categoria = new Categoria();
        categoria.setNombreCategoria(request.getNombreCategoria());
        Categoria categoriaGuardado = categoriaRepository.save(categoria);

        return new CategoriaResponse(
                categoriaGuardado.getCategoriaId(),
                categoria.getNombreCategoria()
        );
    }

    @Override
    public CategoriaResponse update(Integer id, CategoriaRequest request) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoria no encontrada con el id: " + id));

                //crear categoria
        categoria.setNombreCategoria(request.getNombreCategoria());

        Categoria categoriaActualizado =categoriaRepository.save(categoria);

        return new CategoriaResponse(
                categoria.getCategoriaId(),
                categoriaActualizado.getNombreCategoria()
        );
    }

    @Override
    public void delete(Integer id) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Categoria no encontrada con el id: " + id));

        categoriaRepository.delete(categoria);
    }
}
