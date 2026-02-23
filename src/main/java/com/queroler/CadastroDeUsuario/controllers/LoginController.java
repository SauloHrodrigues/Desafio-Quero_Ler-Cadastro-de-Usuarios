package com.queroler.CadastroDeUsuario.controllers;

import com.queroler.CadastroDeUsuario.dtos.login.LoginRequestDto;
import com.queroler.CadastroDeUsuario.service.LoginService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/login")
public class LoginController {

    private final LoginService service;

    @PostMapping
    public ResponseEntity<Object> login(@RequestBody @Valid LoginRequestDto autenticacaoDto) {
        var token = service.login(autenticacaoDto);
        return ResponseEntity.ok(token);
    }
}