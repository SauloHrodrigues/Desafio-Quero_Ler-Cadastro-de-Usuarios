package com.queroler.CadastroDeUsuario.service.implementacoes;

import com.queroler.CadastroDeUsuario.dtos.AtualizarSenhaDto;
import com.queroler.CadastroDeUsuario.dtos.UsuarioRequestDTO;
import com.queroler.CadastroDeUsuario.dtos.UsuarioResponseDto;
import com.queroler.CadastroDeUsuario.dtos.administrador.AdministradorRequestDTO;
import com.queroler.CadastroDeUsuario.enuns.UsuarioRole;
import com.queroler.CadastroDeUsuario.exceptions.especies.SenhaInvalidaException;
import com.queroler.CadastroDeUsuario.exceptions.especies.UsuarioNaoEncontradoException;
import com.queroler.CadastroDeUsuario.mappers.implementacoes.UsuarioMapperImpl;
import com.queroler.CadastroDeUsuario.model.Usuario;
import com.queroler.CadastroDeUsuario.repository.UsuarioRepository;
import com.queroler.CadastroDeUsuario.service.implementacoes.fixtures.UsuarioFixture;
import com.queroler.CadastroDeUsuario.utils.ValidadorUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceImplTest {
    @Mock
    private UsuarioRepository repository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UsuarioServiceImpl service;

    @Mock
    private ValidadorUtils validar;

    @Mock
    private UsuarioMapperImpl mapper;


    @BeforeEach
    void setUp() {
    }


    @Test
    @DisplayName("Deve criar usuário com sucesso e definir role LEITOR")
    void deveCriarUsuarioLeitorComSucesso() {

        UsuarioRequestDTO dto = UsuarioFixture.requestDTO();
        Usuario usuario = new Usuario();
        Usuario usuarioSalvo = UsuarioFixture.entity(UsuarioRole.LEITOR);
        UsuarioResponseDto response = UsuarioFixture.response(usuarioSalvo);

        when(mapper.toEntity(dto)).thenReturn(usuario);
        when(repository.save(usuario)).thenReturn(usuarioSalvo);
        when(mapper.toResponse(usuarioSalvo)).thenReturn(response);

        UsuarioResponseDto resultado = service.criar(dto);

        assertNotNull(resultado);
        assertEquals(response, resultado);

        verify(repository).save(usuario);
        verify(mapper).toEntity(dto);
        verify(mapper).toResponse(usuarioSalvo);

        assertEquals(UsuarioRole.LEITOR, usuario.getRole());
    }

    @Test
    @DisplayName("Deve criar usuário com sucesso e definir role ADMINISTRADOR")
    void deveCriarAdministradorComSucesso() {

        AdministradorRequestDTO dto = UsuarioFixture.requestAdmDTO();
        Usuario usuario = new Usuario();
        Usuario usuarioSalvo = UsuarioFixture.entity(UsuarioRole.ADMINISTRADOR);
        UsuarioResponseDto response = UsuarioFixture.response(usuarioSalvo);

        when(mapper.toEntity(dto)).thenReturn(usuario);
        when(repository.save(usuario)).thenReturn(usuarioSalvo);
        when(mapper.toResponse(usuarioSalvo)).thenReturn(response);

        UsuarioResponseDto resultado = service.criarAdministrador(dto);

        assertNotNull(resultado);
        assertEquals(response, resultado);

        verify(repository).save(usuario);
        verify(mapper).toEntity(dto);
        verify(mapper).toResponse(usuarioSalvo);

        assertEquals(UsuarioRole.ADMINISTRADOR, usuario.getRole());
    }


    @Test
    @DisplayName("deve lançar excessão por usuário não encontrado .")
    void deveLancarExcessaoPorUsuarioNaoEncontrado(){
        String login = "admin";

        Authentication authentication =
                new UsernamePasswordAuthenticationToken(login, null);

        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authentication);
        SecurityContextHolder.setContext(context);

        when(repository.findByLogin(login)).thenReturn(null);

        UsuarioNaoEncontradoException exception= assertThrows(UsuarioNaoEncontradoException.class,
                () -> service.alterarSenha(new AtualizarSenhaDto("a", "b")));

        assertEquals("Usuário não encontrado",exception.getMessage());
    }


    @Test
    @DisplayName("Deve alterar a senha com sucesso quando dados estiverem corretos")
    void deveAlterarSenhaComSucesso() {

        Usuario usuario = new Usuario();
        usuario.setSenha("senhaCriptografada");
        AtualizarSenhaDto dto = new AtualizarSenhaDto("SenhaAtual123@", "NovaSenha123@");

        Authentication authentication = mock(Authentication.class);
        SecurityContext securityContext = mock(SecurityContext.class);

        when(authentication.getName()).thenReturn("loginTeste");
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        when(repository.findByLogin("loginTeste")).thenReturn(usuario);
        when(passwordEncoder.matches(dto.senhaAtual(), usuario.getSenha())).thenReturn(true);
        when(passwordEncoder.matches(dto.novaSenha(), usuario.getSenha())).thenReturn(false);
        when(passwordEncoder.encode(dto.novaSenha())).thenReturn("novaSenhaCriptografada");

        service.alterarSenha(dto);

        verify(repository).save(usuario);
        assertEquals("novaSenhaCriptografada", usuario.getSenha());

        SecurityContextHolder.clearContext();
    }

    @Test
    @DisplayName("Deve lançar exceção quando usuário não for encontrado")
    void deveLancarExcecaoQuandoUsuarioNaoEncontrado() {

        AtualizarSenhaDto dto = new AtualizarSenhaDto("SenhaAtual123@", "NovaSenha123@");

        Authentication authentication = mock(Authentication.class);
        SecurityContext securityContext = mock(SecurityContext.class);

        when(authentication.getName()).thenReturn("loginTeste");
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        when(repository.findByLogin("loginTeste")).thenReturn(null);

        assertThrows(UsuarioNaoEncontradoException.class, () -> service.alterarSenha(dto));

        SecurityContextHolder.clearContext();
    }

    @Test
    @DisplayName("Deve lançar exceção quando senha atual estiver incorreta")
    void deveLancarExcecaoQuandoSenhaAtualIncorreta() {

        Usuario usuario = new Usuario();
        usuario.setSenha("senhaCriptografada");

        AtualizarSenhaDto dto =
                new AtualizarSenhaDto("SenhaErrada@", "NovaSenha123@");

        Authentication authentication = mock(Authentication.class);
        SecurityContext securityContext = mock(SecurityContext.class);

        when(authentication.getName()).thenReturn("loginTeste");
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        when(repository.findByLogin("loginTeste")).thenReturn(usuario);
        when(passwordEncoder.matches(dto.senhaAtual(), usuario.getSenha())).thenReturn(false);

        SenhaInvalidaException exception =assertThrows(SenhaInvalidaException.class,
                () -> service.alterarSenha(dto));

        SecurityContextHolder.clearContext();
        assertEquals("Senha atual incorreta",exception.getMessage());
    }

    @Test
    @DisplayName("Deve lançar exceção quando nova senha for igual à senha atual")
    void deveLancarExcecaoQuandoNovaSenhaIgualAtual() {

        Usuario usuario = new Usuario();
        usuario.setSenha("senhaCriptografada");

        AtualizarSenhaDto dto =
                new AtualizarSenhaDto("SenhaAtual123@", "SenhaAtual123@");

        Authentication authentication = mock(Authentication.class);
        SecurityContext securityContext = mock(SecurityContext.class);

        when(authentication.getName()).thenReturn("loginTeste");
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        when(repository.findByLogin("loginTeste")).thenReturn(usuario);
        when(passwordEncoder.matches(dto.senhaAtual(), usuario.getSenha())).thenReturn(true);
        when(passwordEncoder.matches(dto.novaSenha(), usuario.getSenha())).thenReturn(true);

        SenhaInvalidaException exception= assertThrows(SenhaInvalidaException.class,() -> service.alterarSenha(dto));

        SecurityContextHolder.clearContext();
        assertEquals("A nova senha não pode ser igual à senha atual.",exception.getMessage());
    }
}