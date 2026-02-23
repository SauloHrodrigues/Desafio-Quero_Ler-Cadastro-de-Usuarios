package com.queroler.CadastroDeUsuario.mappers.implementacoes;

import com.queroler.CadastroDeUsuario.dtos.administrador.AdministradorRequestDTO;
import com.queroler.CadastroDeUsuario.dtos.UsuarioAtualizadoDto;
import com.queroler.CadastroDeUsuario.dtos.UsuarioRequestDTO;
import com.queroler.CadastroDeUsuario.dtos.UsuarioResponseDto;
import com.queroler.CadastroDeUsuario.enuns.UsuarioRole;
import com.queroler.CadastroDeUsuario.mappers.UsuarioMapper;
import com.queroler.CadastroDeUsuario.model.Usuario;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapperImpl implements UsuarioMapper {
    public Usuario toEntity(UsuarioRequestDTO request) {
        String senhaCriptografada = new BCryptPasswordEncoder().encode(request.senha());
        Usuario usuario = Usuario.builder()
                .nome(request.nome())
                .email(request.email())
                .cpf(request.cpf())
                .dataDeNascimento(request.dataDeNascimento())
                .aceitarTermos(request.aceitarTermos())
                .cidade(request.cidade())
                .estado(request.estado())
                .pais(request.pais())
                .role(UsuarioRole.LEITOR)
                .login(loginTratado(request.email()))
                .senha(senhaCriptografada)
                .build();
        return usuario;
    }

    public Usuario toEntity(AdministradorRequestDTO request) {
        String senhaCriptografada = new BCryptPasswordEncoder().encode(request.senha());
        Usuario usuario = Usuario.builder()
                .nome(request.nome())
                .email(request.email())
                .cpf(request.cpf())
                .dataDeNascimento(request.dataDeNascimento())
                .aceitarTermos(request.aceitarTermos())
                .cidade(request.cidade())
                .estado(request.estado())
                .pais(request.pais())
                .role(UsuarioRole.ADMINISTRADOR)
                .senha(senhaCriptografada)
                .build();
        usuario.gerarLogin(request.login());
        return usuario;
    }

    public Usuario toUpdate(Usuario usuario, UsuarioAtualizadoDto atualizacoes) {

        if (atualizacoes.nome() != null) {
            usuario.setNome(atualizacoes.nome());
        }

        if (atualizacoes.email() != null) {
            usuario.setEmail(atualizacoes.email());
        }

        if (atualizacoes.dataDeNascimento() != null) {
            usuario.setDataDeNascimento(atualizacoes.dataDeNascimento());
        }

        if (atualizacoes.cidade() != null) {
            usuario.setCidade(atualizacoes.cidade());
        }

        if (atualizacoes.estado() != null) {
            usuario.setEstado(atualizacoes.estado());
        }

        if (atualizacoes.pais() != null) {
            usuario.setPais(atualizacoes.pais());
        }

        if (atualizacoes.senha() != null) {
            usuario.setSenha(atualizacoes.senha());
        }

        if (atualizacoes.login() != null) {
            usuario.setLogin(atualizacoes.login());
        }
        return usuario;
    }


    public UsuarioResponseDto toResponse(Usuario usuario) {
        return new UsuarioResponseDto(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getCpf(),
                usuario.getDataDeNascimento(),
                usuario.getCidade(),
                usuario.getEstado(),
                usuario.getPais(),
                usuario.getRole(),
                usuario.getLogin()
        );
    }

    public String loginTratado(String login) {
        String loginGerado;
        if (login.contains("@")) {
            loginGerado = login.substring(0, login.indexOf("@"));
        } else {
            loginGerado = login;
        }
        return loginGerado.toLowerCase();
    }
}