package com.queroler.CadastroDeUsuario.service.implementacoes;

import com.queroler.CadastroDeUsuario.dtos.documento.DocumentoRequestDto;
import com.queroler.CadastroDeUsuario.dtos.documento.DocumentoResponseDto;
import com.queroler.CadastroDeUsuario.dtos.documento.DocumentoUpdateDto;
import com.queroler.CadastroDeUsuario.exceptions.especies.DocumentoNaoEncontradoException;
import com.queroler.CadastroDeUsuario.mappers.DocumentoMapper;
import com.queroler.CadastroDeUsuario.model.Documento;
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
    private final DocumentoMapper mapper;

    @Override
    public DocumentoResponseDto criar(DocumentoRequestDto dto) {
        Documento documento = mapper.toEntity(dto);
        documento = repository.save(documento);
        return mapper.toResponse(documento);
    }

    @Override
    public Page<DocumentoResponseDto> listar(Pageable pageable) {
        return repository.findAll(pageable).map(mapper::toResponse);
    }

    @Override
    public void atualizar(Long id, DocumentoUpdateDto dto) {
        Documento documento = repository.findById(id).orElseThrow(
                () -> new DocumentoNaoEncontradoException("Não há documento com ID:'" + id + "'.")
        );
        mapper.toUpdate(documento, dto);
        repository.save(documento);
    }
}