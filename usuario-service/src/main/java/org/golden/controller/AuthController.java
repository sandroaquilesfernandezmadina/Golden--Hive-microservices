package org.golden.controller;

import lombok.RequiredArgsConstructor;
import org.golden.dto.auth.LoginRequest;
import org.golden.dto.auth.LoginResponse;
import org.golden.dto.auth.RegisterRequest;
import org.golden.dto.usuario.UsuarioResponse;
import org.golden.service.AuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public UsuarioResponse register(@RequestBody RegisterRequest request){
        return authService.register(request);
    }

    @PostMapping("/login")
    public LoginResponse login (@RequestBody LoginRequest request){
        return authService.login(request);
    }
}
