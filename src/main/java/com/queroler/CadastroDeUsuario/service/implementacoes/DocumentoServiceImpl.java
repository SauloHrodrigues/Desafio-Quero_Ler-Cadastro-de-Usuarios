package com.queroler.CadastroDeUsuario.service.implementacoes;

import com.queroler.CadastroDeUsuario.dtos.documento.DocumentoRequestDto;
import com.queroler.CadastroDeUsuario.dtos.documento.DocumentoResponseDto;
import com.queroler.CadastroDeUsuario.dtos.documento.DocumentoUpdateDto;
import com.queroler.CadastroDeUsuario.repository.DocumentoRepository;
import com.queroler.CadastroDeUsuario.service.DocumentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DocumentoServiceImpl implements DocumentoService {
    private final DocumentoRepository repository;


    @Override
    public DocumentoResponseDto criar(DocumentoRequestDto dto) {
        return null;
    }

    @Override
    public Page<DocumentoResponseDto> listar(Pageable pageable) {
        return null;
    }

    @Override
    public void atualizar(Long id, DocumentoUpdateDto dto) {

    }
}
