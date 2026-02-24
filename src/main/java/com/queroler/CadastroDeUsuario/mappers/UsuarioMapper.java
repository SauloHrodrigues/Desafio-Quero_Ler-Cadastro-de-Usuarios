package com.queroler.CadastroDeUsuario.mappers;

import com.queroler.CadastroDeUsuario.dtos.UsuarioExibirResponseDto;
import com.queroler.CadastroDeUsuario.dtos.administrador.AdministradorRequestDTO;
import com.queroler.CadastroDeUsuario.dtos.UsuarioLeitorAtualizadoDto;
import com.queroler.CadastroDeUsuario.dtos.UsuarioRequestDTO;
import com.queroler.CadastroDeUsuario.dtos.UsuarioResponseDto;
import com.queroler.CadastroDeUsuario.dtos.administrador.UsuarioAdministradorAtualizadoDto;
import com.queroler.CadastroDeUsuario.model.Usuario;

public interface UsuarioMapper {
    Usuario toEntity(UsuarioRequestDTO request);
    Usuario toEntity(AdministradorRequestDTO request);
    Usuario toUpdate(Usuario usuario, UsuarioLeitorAtualizadoDto atualizacoes);
    Usuario toUpdate(Usuario usuario, UsuarioAdministradorAtualizadoDto atualizacoes);
    UsuarioResponseDto toResponse(Usuario usuario);
    UsuarioExibirResponseDto toResponseExibir(Usuario usuario);
}