package com.queroler.CadastroDeUsuario.dtos;

import com.queroler.CadastroDeUsuario.enuns.UsuarioRole;

public record RegistrarDto(
        String login,
        String password,
        UsuarioRole role
) {
}
