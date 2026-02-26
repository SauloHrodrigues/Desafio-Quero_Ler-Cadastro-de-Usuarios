package com.queroler.CadastroDeUsuario.controllers;

import com.queroler.CadastroDeUsuario.dtos.documento.DocumentoResponseDto;
import com.queroler.CadastroDeUsuario.dtos.notificacao.NotificacaoResponseDto;
import com.queroler.CadastroDeUsuario.service.NotificacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/notificacoes")
public class NotificacaoController {
    private final NotificacaoService service;

    @GetMapping
    public ResponseEntity<List<NotificacaoResponseDto>> notificacoes() {
        return ResponseEntity.ok(service.buscarNotificacoesNaoLidas());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> visualizar(@PathVariable Long id) {
        service.marcarComoVisualizado(id);
        return ResponseEntity.noContent().build();
    }
}