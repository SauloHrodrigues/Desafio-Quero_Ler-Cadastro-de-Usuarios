package com.queroler.CadastroDeUsuario.controllers;

import com.queroler.CadastroDeUsuario.dtos.AtualizarSenhaDto;
import com.queroler.CadastroDeUsuario.dtos.UsuarioResponseDto;
import com.queroler.CadastroDeUsuario.dtos.administrador.AdministradorRequestDTO;
import com.queroler.CadastroDeUsuario.dtos.administrador.UsuarioAdministradorAtualizadoDto;
import com.queroler.CadastroDeUsuario.service.AdministradorServiceI;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/administradores")
public class AdiministradorController {

    private final AdministradorServiceI service;

    @PostMapping()
    public ResponseEntity<UsuarioResponseDto> registrar(@RequestBody @Valid AdministradorRequestDTO requestDto ){
        System.out.println("Chegou na controller!!!!");
        return ResponseEntity.ok().body(service.criarAdministrador(requestDto));
    }
    @PatchMapping("/senha")
    public ResponseEntity<Void> alterarSenha(@RequestBody @Valid AtualizarSenhaDto dto){
        service.alterarSenha(dto);
        return ResponseEntity.ok().build();
    }

    @PatchMapping
    public ResponseEntity<Void> atualizar(@RequestBody @Valid UsuarioAdministradorAtualizadoDto atualizacoes) {
        service.atualizar(atualizacoes);
        return ResponseEntity.noContent().build();
    }
}