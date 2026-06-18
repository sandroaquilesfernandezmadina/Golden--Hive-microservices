package org.golden.controller;

import jakarta.validation.Valid;
import lombok.Generated;
import lombok.RequiredArgsConstructor;
import org.golden.dto.auth.LoginRequest;
import org.golden.dto.auth.LoginResponse;
import org.golden.dto.auth.RegisterRequest;
import org.golden.dto.usuario.PerfilResponse;
import org.golden.dto.usuario.UsuarioResponse;
import org.golden.entity.Usuario;
import org.golden.service.AuthService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public UsuarioResponse register(@Valid @RequestBody RegisterRequest request){
        return authService.register(request);
    }

    @PostMapping("/login")
    public LoginResponse login (@Valid @RequestBody LoginRequest request){
        return authService.login(request);
    }

    @GetMapping("/perfil")
    public PerfilResponse perfil(Authentication authentication) {

        Usuario usuario = (Usuario) authentication.getPrincipal();

        return new PerfilResponse(
                usuario.getUsuarioId(),
                usuario.getAlias(),
                usuario.getCorreo(),
                usuario.getRol().getNombreRol()
        );
    }


    //Registros de prueba
    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String admin(){
        return "Bienvenido Administrador";
    }


    @GetMapping("/cliente")
    @PreAuthorize("hasRole('CLIENTE')")
    public String cliente(){
        return  "Bienvenido Cliente";
    }

}
