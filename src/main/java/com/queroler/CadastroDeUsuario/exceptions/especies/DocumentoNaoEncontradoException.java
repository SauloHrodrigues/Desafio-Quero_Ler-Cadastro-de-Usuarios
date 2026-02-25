package com.queroler.CadastroDeUsuario.exceptions.especies;

public class DocumentoNaoEncontradoException extends RuntimeException{
    public DocumentoNaoEncontradoException(String mensagem) {
    super(mensagem);
    }
}