package com.queroler.CadastroDeUsuario.exceptions;

public class UsuarioNaoEncontradoException extends RuntimeException{
    public UsuarioNaoEncontradoException(String mensagem) {
    super(mensagem);
    }
}