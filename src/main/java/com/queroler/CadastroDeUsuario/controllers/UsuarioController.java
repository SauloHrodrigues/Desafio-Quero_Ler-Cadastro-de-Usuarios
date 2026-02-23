package com.queroler.CadastroDeUsuario.controllers;

import com.queroler.CadastroDeUsuario.dtos.AtualizarSenhaDto;
import com.queroler.CadastroDeUsuario.dtos.UsuarioRequestDTO;
import com.queroler.CadastroDeUsuario.dtos.UsuarioResponseDto;
import com.queroler.CadastroDeUsuario.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService service;

    @PostMapping()
    public ResponseEntity<UsuarioResponseDto> registrar(@RequestBody @Valid UsuarioRequestDTO requestDto ){
        return ResponseEntity.ok().body(service.criar(requestDto));
    }
}
