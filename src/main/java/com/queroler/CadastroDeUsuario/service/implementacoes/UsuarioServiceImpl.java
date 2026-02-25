package com.queroler.CadastroDeUsuario.service.implementacoes;

import com.queroler.CadastroDeUsuario.dtos.*;
import com.queroler.CadastroDeUsuario.dtos.administrador.AdministradorRequestDTO;
import com.queroler.CadastroDeUsuario.dtos.administrador.UsuarioAdministradorAtualizadoDto;
import com.queroler.CadastroDeUsuario.enuns.UsuarioRole;
import com.queroler.CadastroDeUsuario.exceptions.especies.EmailInvalidoException;
import com.queroler.CadastroDeUsuario.exceptions.especies.SenhaInvalidaException;
import com.queroler.CadastroDeUsuario.exceptions.especies.UsuarioNaoEncontradoException;
import com.queroler.CadastroDeUsuario.mappers.UsuarioMapper;
import com.queroler.CadastroDeUsuario.model.Usuario;
import com.queroler.CadastroDeUsuario.repository.UsuarioRepository;
import com.queroler.CadastroDeUsuario.service.AdministradorServiceI;
import com.queroler.CadastroDeUsuario.service.UsuarioService;
import com.queroler.CadastroDeUsuario.utils.ValidadorUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UsuarioServiceImpl implements UsuarioService, AdministradorServiceI, UserDetailsService {
    private final UsuarioRepository repository;
    private final UsuarioMapper mapper;
    private final PasswordEncoder passwordEncoder;
    private final ValidadorUtils validar;

    @Override
    public UsuarioResponseDto criar(UsuarioRequestDTO registroDto) {
        validar.email(registroDto.email());
        validar.senha(registroDto.senha());
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
        validar.senha(senhaDto.novaSenha());
        String login = SecurityContextHolder.getContext().getAuthentication().getName();

        UserDetails userDetails = repository.findByLogin(login);

        if (userDetails == null) {
            throw new UsuarioNaoEncontradoException("Usuário não encontrado");
        }

        Usuario usuario = (Usuario) userDetails;

        if (!passwordEncoder.matches(senhaDto.senhaAtual(), usuario.getSenha())) {
            throw new SenhaInvalidaException( "Senha atual incorreta");
        }

        if (passwordEncoder.matches(senhaDto.novaSenha(), usuario.getSenha())) {
            throw new SenhaInvalidaException("A nova senha não pode ser igual à senha atual.");
        }

        usuario.setSenha(passwordEncoder.encode(senhaDto.novaSenha()));
        repository.save(usuario);
    }



    protected void validarRequestDto(String email) {
        if (repository.findByLogin(email.toLowerCase()) != null) {
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
    public void validaLogin(String loguin){
        loadUserByUsername(loguin);
    }

    @Override
    public UsuarioExibirResponseDto exibir(){
        Usuario usuario = getUsuarioLogado();
        return mapper.toResponseExibir(usuario);
    }

    @Override
    public void atualizar(UsuarioAdministradorAtualizadoDto atualizacoes){
        Usuario usuario = getUsuarioLogado();
        mapper.toUpdate(usuario,atualizacoes);
    }
    @Override
    public void atualizar(UsuarioLeitorAtualizadoDto atualizacoes){
        if(atualizacoes.email()!=null){
            Optional<Usuario> cadastrado = repository.findByEmailIgnoreCase(atualizacoes.email());
                if (!cadastrado.isEmpty()){
                    throw new EmailInvalidoException("Email já cadastrado no banco!");
                }
        }
        Usuario usuario = getUsuarioLogado();
        usuario = mapper.toUpdate(usuario,atualizacoes);
        repository.save(usuario);
    }

    @Override
    public void deletar(){
        Usuario usuario = getUsuarioLogado();
        repository.delete(usuario);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails usuario = repository.findByLogin(username.toLowerCase());

        if (usuario == null) {
            throw new UsuarioNaoEncontradoException("Usuário não encontrado");
        }

        return usuario;
    }
}