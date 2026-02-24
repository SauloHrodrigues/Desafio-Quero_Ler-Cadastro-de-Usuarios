package com.queroler.CadastroDeUsuario.exceptions;

public class SenhaInvalidaException extends RuntimeException{
    public SenhaInvalidaException(String mensagem) {
    super(mensagem);
    }
}