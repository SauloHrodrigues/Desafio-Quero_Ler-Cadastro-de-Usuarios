package com.queroler.CadastroDeUsuario.exceptions.especies;

public class EmailInvalidoException extends RuntimeException{
    public EmailInvalidoException(String mensagem) {
    super(mensagem);
    }
}