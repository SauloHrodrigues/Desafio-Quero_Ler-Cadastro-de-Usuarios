package com.queroler.CadastroDeUsuario.controllers;

import com.queroler.CadastroDeUsuario.dtos.UsuarioRequestDTO;
import com.queroler.CadastroDeUsuario.dtos.UsuarioResponseDto;
import com.queroler.CadastroDeUsuario.dtos.livro.LivroRequestDto;
import com.queroler.CadastroDeUsuario.dtos.livro.LivroResponseDto;
import com.queroler.CadastroDeUsuario.service.LivroService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/livros")
public class LivroController {

    private final LivroService service;

    @PostMapping()
    public ResponseEntity<LivroResponseDto> cadastrar(@RequestBody @Valid LivroRequestDto requestDto ){
        return ResponseEntity.ok().body(service.cadastrar(requestDto));
    }
}
