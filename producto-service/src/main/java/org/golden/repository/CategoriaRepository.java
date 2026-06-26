package org.golden.repository;

import org.golden.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer>{
    //spring infiere: SELECT* FROM categoria WHERE nombreCategoria = ?
    Optional<Categoria> findBynombreCategoria(String nombreCategoria);
}
