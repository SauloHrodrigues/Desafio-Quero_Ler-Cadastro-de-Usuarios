package com.queroler.CadastroDeUsuario.service.implementacoes;

import com.queroler.CadastroDeUsuario.dtos.documento.DocumentoRequestDto;
import com.queroler.CadastroDeUsuario.dtos.documento.DocumentoResponseDto;
import com.queroler.CadastroDeUsuario.dtos.documento.DocumentoUpdateDto;
import com.queroler.CadastroDeUsuario.exceptions.especies.DocumentoNaoEncontradoException;
import com.queroler.CadastroDeUsuario.mappers.implementacoes.DocumentoMapperImpl;
import com.queroler.CadastroDeUsuario.model.Documento;
import com.queroler.CadastroDeUsuario.service.implementacoes.fixtures.DocumentoFixture;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DocumentoServiceImplTest {
    @Mock
    private DocumentoRepository repository;

    @Mock
    private DocumentoMapperImpl mapper;

    @InjectMocks
    private DocumentoServiceImpl service;

    @Test
    @DisplayName("Deve criar documento com sucesso")
    void deveCriarDocumentoComSucesso() {
        DocumentoRequestDto request = DocumentoFixture.request();
        Documento documento = new Documento();
        Documento documentoSalvo =DocumentoFixture.entity();
        DocumentoResponseDto response = DocumentoFixture.response();

        when(mapper.toEntity(request)).thenReturn(documento);
        when(repository.save(documento)).thenReturn(documentoSalvo);
        when(mapper.toResponse(documentoSalvo)).thenReturn(response);

        DocumentoResponseDto resultado = service.criar(request);

        assertNotNull(resultado.id());
        assertEquals(request.titulo(),resultado.titulo());
        assertEquals(request.tipo(),resultado.tipo());
        assertEquals(request.conteudo(),resultado.conteudo());

        verify(mapper).toEntity(request);
        verify(repository).save(documento);
        verify(mapper).toResponse(documentoSalvo);
    }

    @Test
    @DisplayName("Deve listar documentos paginados")
    void deveListarDocumentos() {
        Pageable pageable = PageRequest.of(0, 10);

        Documento documento = new Documento();
        DocumentoResponseDto response = DocumentoFixture.response();

        Page<Documento> page = new PageImpl<>(List.of(documento));

        when(repository.findAll(pageable)).thenReturn(page);
        when(mapper.toResponse(documento)).thenReturn(response);

        Page<DocumentoResponseDto> resultado = service.listar(pageable);

        assertEquals(1, resultado.getTotalElements());
        assertNotNull(resultado.getContent().get(0).id());

        verify(repository).findAll(pageable);
        verify(mapper).toResponse(documento);
    }

    @Test
    @DisplayName("Deve atualizar documento com sucesso")
    void deveAtualizarDocumentoComSucesso() {
        Documento documento = DocumentoFixture.entity();
        Long id = 1L;
        DocumentoUpdateDto dto = new DocumentoUpdateDto("Novo titulo",null,"Novo conteudo");

        when(repository.findById(id)).thenReturn(Optional.of(documento));

        service.atualizar(id, dto);

        verify(repository).findById(id);
        verify(mapper).toUpdate(documento, dto);
        verify(repository).save(documento);
    }

    @Test
    @DisplayName("Deve lançar exceção quando documento não existir ao atualizar")
    void deveLancarExcecaoQuandoDocumentoNaoExistir() {
        Long id = 1L;
        DocumentoUpdateDto dto = mock(DocumentoUpdateDto.class);

        when(repository.findById(id)).thenReturn(Optional.empty());

        DocumentoNaoEncontradoException exception= assertThrows(
                DocumentoNaoEncontradoException.class,
                () -> service.atualizar(id, dto)
        );

        assertEquals("Não há documento com ID:'" + id + "'.",exception.getMessage());

        verify(repository).findById(id);
        verify(repository, never()).save(any());
        verify(mapper, never()).toUpdate(any(), any());
    }
}