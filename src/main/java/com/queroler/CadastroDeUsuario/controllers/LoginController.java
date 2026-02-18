package com.queroler.CadastroDeUsuario.controllers;

import com.queroler.CadastroDeUsuario.dtos.LoginRequestDto;
import com.queroler.CadastroDeUsuario.service.LoginService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RequiredArgsConstructor
@RestController
@RequestMapping("login")
public class LoginController {

    private final LoginService service;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody @Valid LoginRequestDto requestDto){
        return ResponseEntity.ok(service);
    }
}
