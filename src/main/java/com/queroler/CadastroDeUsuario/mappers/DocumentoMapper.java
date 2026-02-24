package com.queroler.CadastroDeUsuario.mappers;

import com.queroler.CadastroDeUsuario.dtos.documento.DocumentoRequestDto;
import com.queroler.CadastroDeUsuario.dtos.documento.DocumentoResponseDto;
import com.queroler.CadastroDeUsuario.dtos.documento.DocumentoUpdateDto;
import com.queroler.CadastroDeUsuario.model.Documento;

public interface DocumentoMapper {
    Documento toEntity(DocumentoRequestDto dto);

    DocumentoResponseDto toResponse(Documento documento);

    Documento toUpdate(Documento documento, DocumentoUpdateDto dto);
}