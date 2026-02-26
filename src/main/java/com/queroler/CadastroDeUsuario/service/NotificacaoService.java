package com.queroler.CadastroDeUsuario.service;

import com.queroler.CadastroDeUsuario.dtos.documento.DocumentoResponseDto;
import com.queroler.CadastroDeUsuario.dtos.notificacao.NotificacaoResponseDto;
import com.queroler.CadastroDeUsuario.model.Documento;

import java.util.List;

public interface NotificacaoService {
    void gerar(Documento documento);
    List<NotificacaoResponseDto>buscarNotificacoesNaoLidas();

    void marcarComoVisualizado(Long id);
}