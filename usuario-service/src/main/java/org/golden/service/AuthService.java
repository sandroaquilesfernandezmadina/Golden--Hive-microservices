package org.golden.service;


import org.golden.dto.auth.LoginRequest;
import org.golden.dto.auth.LoginResponse;
import org.golden.dto.auth.RegisterRequest;
import org.golden.dto.usuario.UsuarioResponse;

public interface AuthService {
    //crea usuario y cliente y devuelve datos seguros
    UsuarioResponse register(RegisterRequest request);

    //valida credenciales genera JWT devuelve tocken y una informacion breve
    LoginResponse login(LoginRequest request);
}
