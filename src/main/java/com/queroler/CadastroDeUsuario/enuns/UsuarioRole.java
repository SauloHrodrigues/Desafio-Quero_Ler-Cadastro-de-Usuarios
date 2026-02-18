package com.queroler.CadastroDeUsuario.enuns;

public enum UsuarioRole {
    LEITOR("Leitor"),
    ADMINISTRADOR("Administrador"),
    MODERADOR("Moderador");

    private final String perfil;

    UsuarioRole(String perfil) {
        this.perfil = perfil;
    }
}
