package com.queroler.CadastroDeUsuario.dtos.notificacao;

import com.queroler.CadastroDeUsuario.dtos.documento.DocumentoResponseDto;
import com.queroler.CadastroDeUsuario.enuns.DocumentoTipo;

import java.time.LocalDateTime;

public record NotificacaoResponseDto(
        Long id,
        LocalDateTime dataInicio,
        String titulo,
        DocumentoTipo tipo,
        String conteudo
) {}