package com.queroler.CadastroDeUsuario.service.implementacoes;

import com.queroler.CadastroDeUsuario.dtos.AtualizarSenhaDto;
import com.queroler.CadastroDeUsuario.dtos.UsuarioRequestDTO;
import com.queroler.CadastroDeUsuario.dtos.UsuarioResponseDto;
import com.queroler.CadastroDeUsuario.dtos.administrador.AdministradorRequestDTO;
import com.queroler.CadastroDeUsuario.enuns.UsuarioRole;
import com.queroler.CadastroDeUsuario.mappers.UsuarioMapper;
import com.queroler.CadastroDeUsuario.model.Usuario;
import com.queroler.CadastroDeUsuario.repository.UsuarioRepository;
import com.queroler.CadastroDeUsuario.service.AdministradorServiceI;
import com.queroler.CadastroDeUsuario.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UsuarioServiceImpl implements UsuarioService, AdministradorServiceI, UserDetailsService {
    private final UsuarioRepository repository;
    private final UsuarioMapper mapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UsuarioResponseDto criar(UsuarioRequestDTO registroDto) {
        validarRequestDto(registroDto.email());
        Usuario usuario = mapper.toEntity(registroDto);
        usuario.setRole(UsuarioRole.LEITOR);
        Usuario usuarioSalvo = repository.save(usuario);
        return mapper.toResponse(usuarioSalvo);
    }

    @Override
    public UsuarioResponseDto criarAdministrador(AdministradorRequestDTO registroDto) {
        validarRequestDto(registroDto.email());
        Usuario usuario = mapper.toEntity(registroDto);
        usuario.setRole(UsuarioRole.ADMINISTRADOR);
        Usuario usuarioSalvo = repository.save(usuario);
        return mapper.toResponse(usuarioSalvo);
    }

    @Override
    public void alterarSenha(AtualizarSenhaDto senhaDto) {

        String login = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        UserDetails userDetails = repository.findByLogin(login);

        if (userDetails == null) {
            throw new UsernameNotFoundException("Usuário não encontrado");
        }

        Usuario usuario = (Usuario) userDetails;

        if (!passwordEncoder.matches(senhaDto.senhaAtual(), usuario.getSenha())) {
            throw new IllegalArgumentException("Senha atual incorreta");
        }

        usuario.setSenha(passwordEncoder.encode(senhaDto.novaSenha()));
        repository.save(usuario);
    }

    protected void validarRequestDto(String email) {
        if (repository.findByLogin(mapper.loginTratado(email)) != null) {
            throw new RuntimeException("Usuario ja cadastrado ");
        }
    }

    @Override
    public Usuario getUsuarioLogado() {

        Authentication authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new RuntimeException("Usuário não autenticado");
        }

        Object principal = authentication.getPrincipal();

        if (principal instanceof Usuario usuario) {
            return usuario;
        }

        throw new RuntimeException("Principal inválido");
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails usuario = repository.findByLogin(username);

        if (usuario == null) {
            throw new UsernameNotFoundException("Usuário não encontrado");
        }

        return usuario;
    }
}