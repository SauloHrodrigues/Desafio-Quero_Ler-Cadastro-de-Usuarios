package com.queroler.CadastroDeUsuario.mappers;

import com.queroler.CadastroDeUsuario.dtos.administrador.AdministradorRequestDTO;
import com.queroler.CadastroDeUsuario.dtos.UsuarioAtualizadoDto;
import com.queroler.CadastroDeUsuario.dtos.UsuarioRequestDTO;
import com.queroler.CadastroDeUsuario.dtos.UsuarioResponseDto;
import com.queroler.CadastroDeUsuario.model.Usuario;

public interface UsuarioMapper {
    Usuario toEntity(UsuarioRequestDTO request);
    Usuario toEntity(AdministradorRequestDTO request);

    Usuario toUpdate(Usuario usuario, UsuarioAtualizadoDto atualizacoes);
    UsuarioResponseDto toResponse(Usuario usuario);
    String loginTratado(String login);
}
