package com.queroler.CadastroDeUsuario.exceptions.especies;

public class UsuarioNaoEncontradoException extends RuntimeException{
    public UsuarioNaoEncontradoException(String mensagem) {
    super(mensagem);
    }
}