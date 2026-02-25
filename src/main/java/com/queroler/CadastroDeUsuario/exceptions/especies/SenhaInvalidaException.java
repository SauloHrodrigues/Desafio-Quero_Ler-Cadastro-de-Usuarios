package com.queroler.CadastroDeUsuario.exceptions.especies;

public class SenhaInvalidaException extends RuntimeException{
    public SenhaInvalidaException(String mensagem) {
    super(mensagem);
    }
}