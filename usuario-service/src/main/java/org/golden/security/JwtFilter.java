package org.golden.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.golden.entity.Usuario;
import org.golden.repository.UsuarioRepository;
import org.golden.service.JwtService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor

public class JwtFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UsuarioRepository usuarioRepository;
    private final CustomUserDetailsService  customUserDetailsService;
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
        UserDetails userDetails =
                customUserDetailsService.loadUserByUsername(correo);

        if(jwtService.isTokenValid(token, userDetails)){

            UsernamePasswordAuthenticationToken authToken =
                    new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities()
                    );

            //le dice a spring que este usuario ya está autenticado
            SecurityContextHolder.getContext()
                    .setAuthentication(authToken);
        }
        filterChain.doFilter(request, response);
    }

}
