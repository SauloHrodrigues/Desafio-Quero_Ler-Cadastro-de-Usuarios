package com.queroler.CadastroDeUsuario.exceptions;

public class EmailInvalidoException extends RuntimeException{
    public EmailInvalidoException(String mensagem) {
    super(mensagem);
    }
}