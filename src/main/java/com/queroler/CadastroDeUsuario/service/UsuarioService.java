package com.queroler.CadastroDeUsuario.service;

import com.queroler.CadastroDeUsuario.dtos.AtualizarSenhaDto;
import com.queroler.CadastroDeUsuario.dtos.UsuarioRequestDTO;
import com.queroler.CadastroDeUsuario.dtos.UsuarioResponseDto;
import com.queroler.CadastroDeUsuario.model.Usuario;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioService {
    UsuarioResponseDto criar(UsuarioRequestDTO usuarioRequestDTO);
    Usuario getUsuarioLogado();
}
