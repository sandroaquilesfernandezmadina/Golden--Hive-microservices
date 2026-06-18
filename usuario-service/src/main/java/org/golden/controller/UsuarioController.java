package org.golden.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class UsuarioController {

    @GetMapping("/perfil")
    public String perfil() {
        return "Acceso permitido";
    }
}