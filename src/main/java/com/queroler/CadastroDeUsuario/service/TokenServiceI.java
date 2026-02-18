package com.queroler.CadastroDeUsuario.service;

import com.queroler.CadastroDeUsuario.model.Usuario;

public interface TokenServiceI {
    String gerarToken(Usuario usuarios);
    String validaToken(String token);
}
