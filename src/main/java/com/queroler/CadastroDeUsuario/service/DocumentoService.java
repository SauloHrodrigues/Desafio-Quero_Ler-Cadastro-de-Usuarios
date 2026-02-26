package com.queroler.CadastroDeUsuario.service;

import com.queroler.CadastroDeUsuario.dtos.documento.DocumentoRequestDto;
import com.queroler.CadastroDeUsuario.dtos.documento.DocumentoResponseDto;
import com.queroler.CadastroDeUsuario.dtos.documento.DocumentoUpdateDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DocumentoService {
    DocumentoResponseDto criar(DocumentoRequestDto dto);

    Page<DocumentoResponseDto> listar(Pageable pageable);

    void atualizar(Long id, DocumentoUpdateDto dto);
}