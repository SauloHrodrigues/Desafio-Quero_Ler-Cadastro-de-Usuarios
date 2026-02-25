package com.queroler.CadastroDeUsuario.exceptions.especies;

public class EmailNaoCadastradoException extends RuntimeException{
    public EmailNaoCadastradoException(String mensagem) {
    super(mensagem);
    }
}