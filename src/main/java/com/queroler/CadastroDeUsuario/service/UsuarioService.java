package com.queroler.CadastroDeUsuario.service;

import com.queroler.CadastroDeUsuario.dtos.UsuarioRequestDTO;

public interface UsuarioService {
    void criar(UsuarioRequestDTO usuarioRequestDTO);
}
