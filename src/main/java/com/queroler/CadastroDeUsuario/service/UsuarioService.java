package com.queroler.CadastroDeUsuario.service;

import com.queroler.CadastroDeUsuario.dtos.UsuarioExibirResponseDto;
import com.queroler.CadastroDeUsuario.dtos.UsuarioLeitorAtualizadoDto;
import com.queroler.CadastroDeUsuario.dtos.UsuarioRequestDTO;
import com.queroler.CadastroDeUsuario.dtos.UsuarioResponseDto;
import com.queroler.CadastroDeUsuario.model.Usuario;

public interface UsuarioService {
    UsuarioResponseDto criar(UsuarioRequestDTO usuarioRequestDTO);
    Usuario getUsuarioLogado();

    void validaLogin(String loguin);
    UsuarioExibirResponseDto exibir();
    void atualizar(UsuarioLeitorAtualizadoDto atualizacoes);
    void deletar();
}
