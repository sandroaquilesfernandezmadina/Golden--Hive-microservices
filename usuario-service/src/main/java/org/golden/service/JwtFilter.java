package org.golden.service;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.golden.entity.Usuario;
import org.golden.repository.UsuarioRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor

public class JwtFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UsuarioRepository usuarioRepository;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
    throws ServletException, IOException {

        //lee la Authorization: Bearer eyJhbGc...
        String authHeader = request.getHeader("Authorization");

        if(authHeader == null || !authHeader.startsWith("Bearer ")){

            filterChain.doFilter(request, response);
        return;
        }
        //quita el Bearer
        String token = authHeader.substring(7);

        //Extrae el correo
        String correo = jwtService.extractUsername(token);

        //busca el usuario en la base de datos
        Usuario usuario = usuarioRepository.findByCorreo(correo)
        .orElse(null);

        if(usuario != null &&
        jwtService.isTokenValid(token, usuario)){

            UsernamePasswordAuthenticationToken authToken =
                    new UsernamePasswordAuthenticationToken(
                            usuario,
                            null,
                            null
                    );

            //le dice a spring que este usuario ya está autenticado
            SecurityContextHolder.getContext()
                    .setAuthentication(authToken);
        }
        filterChain.doFilter(request, response);
    }

}
