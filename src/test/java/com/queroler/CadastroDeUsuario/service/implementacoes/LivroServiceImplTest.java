package com.queroler.CadastroDeUsuario.service.implementacoes;


import com.queroler.CadastroDeUsuario.dtos.livro.LivroRequestDto;
import com.queroler.CadastroDeUsuario.dtos.livro.LivroResponseDto;
import com.queroler.CadastroDeUsuario.mappers.LivroMapper;
import com.queroler.CadastroDeUsuario.model.Livro;
import com.queroler.CadastroDeUsuario.model.Usuario;
import com.queroler.CadastroDeUsuario.repository.LivroRepository;
import com.queroler.CadastroDeUsuario.service.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LivroServiceImplTest {



        @Mock
        private LivroRepository repository;

        @Mock
        private LivroMapper mapper;

        @Mock
        private UsuarioService usuarioService;

        @InjectMocks
        private LivroServiceImpl service;

        private Usuario usuarioMock;
        private LivroRequestDto requestDto;
        private Livro livroEntity;
        private LivroResponseDto responseDto;

        @BeforeEach
        void setUp() {
            MockitoAnnotations.openMocks(this);

            usuarioMock = new Usuario();
            usuarioMock.setId(1L);
            usuarioMock.setNome("Saulo");

            requestDto = new LivroRequestDto(
                    "Clean Code",
                    "9780132350884",
                    "Robert C. Martin",
                    LocalDate.of(2008, 8, 1)
            );

            livroEntity = new Livro();
            livroEntity.setId(1L);
            livroEntity.setTitulo(requestDto.titulo());
            livroEntity.setIsbn(requestDto.isbn());
            livroEntity.setAutor(requestDto.autor());
            livroEntity.setDataDePublicacao(requestDto.dataDePublicacao());

            responseDto = new LivroResponseDto(
                    1L,
                    requestDto.titulo(),
                    requestDto.isbn(),
                    requestDto.autor(),
                    requestDto.dataDePublicacao(),
                    usuarioMock.getNome()
            );
        }

        @Test
        void deveCadastrarLivroComSucesso() {
            when(repository.existsByTituloIgnoreCase(requestDto.titulo())).thenReturn(false);
            when(usuarioService.getUsuarioLogado()).thenReturn(usuarioMock);
            when(mapper.toEntity(requestDto)).thenReturn(livroEntity);
            when(repository.save(livroEntity)).thenReturn(livroEntity);
            when(mapper.toResponse(livroEntity)).thenReturn(responseDto);

            LivroResponseDto resultado = service.cadastrar(requestDto);

            assertNotNull(resultado);
            assertEquals("Clean Code", resultado.titulo());
            assertEquals("Saulo", resultado.cadstradoPor());

            ArgumentCaptor<Livro> captor = ArgumentCaptor.forClass(Livro.class);
            verify(repository).save(captor.capture());
            assertEquals(usuarioMock, captor.getValue().getUsuario());
        }

        @Test
        void deveLancarExcecaoAoCadastrarLivroJaExistente() {
            when(repository.existsByTituloIgnoreCase(requestDto.titulo())).thenReturn(true);

            RuntimeException exception = assertThrows(RuntimeException.class, () -> {
                service.cadastrar(requestDto);
            });

            assertEquals("Livro já cadastrado", exception.getMessage());
            verify(repository, never()).save(any());
        }

        @Test
        void deveBuscarLivroPorIdComSucesso() {
            when(repository.findById(1L)).thenReturn(Optional.of(livroEntity));
            when(mapper.toResponse(livroEntity)).thenReturn(responseDto);

            LivroResponseDto resultado = service.buscarLivro(1L);

            assertNotNull(resultado);
            assertEquals(1L, resultado.id());
        }

        @Test
        void deveLancarExcecaoAoBuscarLivroInexistente() {
            when(repository.findById(1L)).thenReturn(Optional.empty());

            RuntimeException exception = assertThrows(RuntimeException.class, () -> {
                service.buscarLivro(1L);
            });

            assertEquals("Livro não encontrado", exception.getMessage());
        }
}