package com.queroler.CadastroDeUsuario.service;

import com.queroler.CadastroDeUsuario.dtos.UsuarioRequestDTO;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioService {
    void criar(UsuarioRequestDTO usuarioRequestDTO);
    UserDetails loadUserByUsername(String username);
}
