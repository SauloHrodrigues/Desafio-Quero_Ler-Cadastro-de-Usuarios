package com.queroler.CadastroDeUsuario.mappers;

import com.queroler.CadastroDeUsuario.dtos.notificacao.NotificacaoResponseDto;
import com.queroler.CadastroDeUsuario.model.Notificacao;

import java.util.List;

public interface NotificacaoMapper {
    NotificacaoResponseDto toResponse(Notificacao notificacao);
    List<NotificacaoResponseDto> toResponse(List<Notificacao> notificacoes);
}
