package com.queroler.CadastroDeUsuario.mappers.implementacoes;

import com.queroler.CadastroDeUsuario.dtos.documento.DocumentoRequestDto;
import com.queroler.CadastroDeUsuario.dtos.documento.DocumentoResponseDto;
import com.queroler.CadastroDeUsuario.dtos.documento.DocumentoUpdateDto;
import com.queroler.CadastroDeUsuario.mappers.DocumentoMapper;
import com.queroler.CadastroDeUsuario.model.Documento;
import org.springframework.stereotype.Component;

@Component
public class DocumentoMapperImpl implements DocumentoMapper {
    @Override
    public Documento toEntity(DocumentoRequestDto dto) {
        Documento documento = new Documento();
        documento.setTitulo(dto.titulo());
        documento.setTipo(dto.tipo());
        documento.setConteudo(dto.conteudo());
        return documento;
    }

    @Override
    public DocumentoResponseDto toResponse(Documento documento) {
        return new DocumentoResponseDto(
                documento.getId(),
                documento.getTitulo(),
                documento.getTipo(),
                documento.getConteudo()
        );
    }

    @Override
    public Documento toUpdate(Documento documento, DocumentoUpdateDto dto) {
        return new Documento(
                documento.getId(),
                dto.titulo() != null ? dto.titulo() : documento.getTitulo(),
                dto.tipo() != null ? dto.tipo() : documento.getTipo(),
                dto.conteudo() != null ? dto.conteudo() : documento.getConteudo()
        );
    }
}