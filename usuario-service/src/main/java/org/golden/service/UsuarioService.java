package org.golden.service;


import org.golden.dto.auth.RegisterRequest;
import org.golden.dto.usuario.UsuarioResponse;

public interface UsuarioService {
    UsuarioResponse register(RegisterRequest request);
}
