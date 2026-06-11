package org.golden.service.ServiceImpl;

import org.golden.entity.Usuario;

public interface JwtService {
    String generateToken(Usuario usuario);
}
