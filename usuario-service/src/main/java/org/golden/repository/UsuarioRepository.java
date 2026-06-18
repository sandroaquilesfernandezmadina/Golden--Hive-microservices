package org.golden.repository;

import org.golden.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository <Usuario, Integer> {

    // Spring infiere: SELECT * FROM usuario WHERE correo = ?
    Optional<Usuario> findByCorreo(String correo);
    Optional<Usuario> findByAlias(String alias);
}
