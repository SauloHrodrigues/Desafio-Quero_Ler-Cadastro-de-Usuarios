package com.queroler.CadastroDeUsuario.service.implementacoes;

import com.queroler.CadastroDeUsuario.dtos.AtualizarSenhaDto;
import com.queroler.CadastroDeUsuario.model.Usuario;
import com.queroler.CadastroDeUsuario.repository.UsuarioRepository;
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
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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


    @BeforeEach
    void setUp() {
    }

    @Test
    void criar() {
    }

    @Test
    void criarAdministrador() {
    }

    @Test
    @DisplayName("Deve alterar senha do usuário com sucesso.")
    void deveAlterarSenhaComSucesso() {

        String login = "admin";

        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setLogin(login);
        usuario.setSenha("senhaCriptografada");

        AtualizarSenhaDto dto = new AtualizarSenhaDto("senhaAtual", "novaSenha");

        // simula usuário autenticado
        Authentication authentication = new UsernamePasswordAuthenticationToken(login, null);

        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authentication);
        SecurityContextHolder.setContext(context);

        when(repository.findByLogin(login)).thenReturn(usuario);
        when(passwordEncoder.matches("senhaAtual", "senhaCriptografada"))
                .thenReturn(true);
        when(passwordEncoder.encode("novaSenha")).thenReturn("novaSenhaCriptografada");

        service.alterarSenha(dto);

        assertEquals("novaSenhaCriptografada", usuario.getSenha());
        verify(repository).save(usuario);
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

        UsernameNotFoundException exception= assertThrows(UsernameNotFoundException.class,
                () -> service.alterarSenha(new AtualizarSenhaDto("a", "b")));

        assertEquals("Usuário não encontrado",exception.getMessage());
    }

    @Test
    void deveLancarExcecaoQuandoSenhaAtualIncorreta() {

        String login = "admin";

        Usuario usuario = new Usuario();
        usuario.setLogin(login);
        usuario.setSenha("senhaCriptografada");

        AtualizarSenhaDto dto = new AtualizarSenhaDto("errada", "nova");

        Authentication authentication =
                new UsernamePasswordAuthenticationToken(login, null);

        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authentication);
        SecurityContextHolder.setContext(context);

        when(repository.findByLogin(login)).thenReturn(usuario);
        when(passwordEncoder.matches("errada", "senhaCriptografada"))
                .thenReturn(false);

        IllegalArgumentException exception= assertThrows(IllegalArgumentException.class,
                () -> service.alterarSenha(dto));

        assertEquals("Senha atual incorreta",exception.getMessage());
        verify(repository, never()).save(any());
    }

    @Test
    void validarRequestDto() {
    }

    @Test
    void loadUserByUsername() {
    }
}