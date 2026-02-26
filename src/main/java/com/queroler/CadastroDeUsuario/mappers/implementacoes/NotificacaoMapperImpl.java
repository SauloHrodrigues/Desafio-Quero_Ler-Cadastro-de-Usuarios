package com.queroler.CadastroDeUsuario.mappers.implementacoes;

import com.queroler.CadastroDeUsuario.dtos.notificacao.NotificacaoResponseDto;
import com.queroler.CadastroDeUsuario.mappers.NotificacaoMapper;
import com.queroler.CadastroDeUsuario.model.Notificacao;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class NotificacaoMapperImpl implements NotificacaoMapper {
    @Override
    public NotificacaoResponseDto toResponse(Notificacao notificacao) {
        return new NotificacaoResponseDto(
                notificacao.getId(),
                notificacao.getDataInicio(),
                notificacao.getDocumento().getTitulo(),
                notificacao.getDocumento().getTipo(),
                notificacao.getDocumento().getConteudo()
        );
    }

    @Override
    public List<NotificacaoResponseDto> toResponse(List<Notificacao> notificacoes){
        List<NotificacaoResponseDto> resposta= new ArrayList<>();
        for (Notificacao notificacao:notificacoes){
            resposta.add(toResponse(notificacao));
        }
        return resposta;
    }
}
