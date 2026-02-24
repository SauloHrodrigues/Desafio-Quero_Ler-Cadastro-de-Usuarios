package com.queroler.CadastroDeUsuario.service;

import com.queroler.CadastroDeUsuario.dtos.AtualizarSenhaDto;
import com.queroler.CadastroDeUsuario.dtos.UsuarioResponseDto;
import com.queroler.CadastroDeUsuario.dtos.administrador.AdministradorRequestDTO;
import com.queroler.CadastroDeUsuario.dtos.administrador.UsuarioAdministradorAtualizadoDto;

public interface AdministradorServiceI {
    UsuarioResponseDto criarAdministrador(AdministradorRequestDTO registroDto);
    void alterarSenha( AtualizarSenhaDto senhaDto);
    void atualizar(UsuarioAdministradorAtualizadoDto atualizacoes);
}
