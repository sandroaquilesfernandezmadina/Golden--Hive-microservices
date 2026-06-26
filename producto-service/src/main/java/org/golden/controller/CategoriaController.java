package org.golden.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.golden.Dto.CategoriaRequest;
import org.golden.Dto.CategoriaResponse;
import org.golden.Service.CategoriaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categoria")
@RequiredArgsConstructor
public class CategoriaController {

    private final CategoriaService categoriaService;

    @GetMapping
    public List<CategoriaResponse> findAll(){
        return categoriaService.findAll();
    }

    @PostMapping
    @RequestMapping("/registrar")
    public CategoriaResponse save(
            @Valid @RequestBody CategoriaRequest request){
        return categoriaService.save(request);
    }


}
