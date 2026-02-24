package com.queroler.CadastroDeUsuario.exceptions;

public class EmailNaoCadastradoException extends RuntimeException{
    public EmailNaoCadastradoException(String mensagem) {
    super(mensagem);
    }
}