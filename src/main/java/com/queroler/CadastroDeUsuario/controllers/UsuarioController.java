package com.queroler.CadastroDeUsuario.controllers;

import com.queroler.CadastroDeUsuario.dtos.UsuarioExibirResponseDto;
import com.queroler.CadastroDeUsuario.dtos.UsuarioLeitorAtualizadoDto;
import com.queroler.CadastroDeUsuario.dtos.UsuarioRequestDTO;
import com.queroler.CadastroDeUsuario.dtos.UsuarioResponseDto;
import com.queroler.CadastroDeUsuario.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService service;

    @PostMapping()
    public ResponseEntity<UsuarioResponseDto> registrar(@RequestBody @Valid UsuarioRequestDTO requestDto) {
        return ResponseEntity.ok().body(service.criar(requestDto));
    }

    @GetMapping()
    public ResponseEntity<UsuarioExibirResponseDto> exibir() {
        return ResponseEntity.ok().body(service.exibir());
    }

    @PatchMapping
    public ResponseEntity<Void> atualizar(@RequestBody @Valid UsuarioLeitorAtualizadoDto atualizacoes) {
        service.atualizar(atualizacoes);
        return ResponseEntity.noContent().build();
    }
}