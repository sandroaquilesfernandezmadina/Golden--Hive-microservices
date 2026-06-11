package org.golden.service;

import org.golden.entity.Usuario;

public interface JwtService {
    String generateToken(Usuario usuario);

    String extractUsername(String token);
    boolean isTokenValid(String token, Usuario usuario);
}
