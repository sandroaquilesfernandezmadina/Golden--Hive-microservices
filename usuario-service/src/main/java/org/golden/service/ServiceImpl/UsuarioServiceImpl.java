package org.golden.service.ServiceImpl;

import lombok.RequiredArgsConstructor;
import org.golden.dto.auth.RegisterRequest;
import org.golden.dto.usuario.UsuarioResponse;
import org.golden.entity.Usuario;
import org.golden.repository.UsuarioRepository;
import org.golden.service.UsuarioService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

 private final UsuarioRepository usuarioRepository;

    @Override
    public UsuarioResponse register(RegisterRequest request) {
        Usuario usuario = new Usuario();

        usuario.setUserName(request.getUserName());
        usuario.setCorreo(request.getCorreo());
        usuario.setPasswordHash(request.getPassword());

        Usuario guardado = usuarioRepository.save(usuario);

        UsuarioResponse response = new UsuarioResponse();
        response.setUsuarioId(guardado.getUsuarioId());
        response.setUserName(guardado.getUserName());
        response.setCorreo(guardado.getCorreo());

        return response;
    }
}
