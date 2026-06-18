package org.golden.service;

import org.golden.entity.Usuario;
import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {
    String generateToken(Usuario usuario);

    String extractUsername(String token);
    boolean isTokenValid(String token, UserDetails userDetails);
}
