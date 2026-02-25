package com.queroler.CadastroDeUsuario.service.implementacoes.fixtures;

import com.queroler.CadastroDeUsuario.dtos.documento.DocumentoRequestDto;
import com.queroler.CadastroDeUsuario.dtos.documento.DocumentoResponseDto;
import com.queroler.CadastroDeUsuario.enuns.DocumentoTipo;
import com.queroler.CadastroDeUsuario.model.Documento;

public class DocumentoFixture {
    private static final Long ID = 1L;
    private static final String TITULO = "titulo teste";
    private static final DocumentoTipo TIPO = DocumentoTipo.TERMOS_GERAIS_DE_USO;
    private static final String CONTEUDO = "conteudo do termo a ser usado...";

    public static DocumentoRequestDto request(){
        return new DocumentoRequestDto(
                TITULO,TIPO,CONTEUDO
        );
    }

    public static Documento entity(){
        return new Documento(ID,TITULO,TIPO,CONTEUDO);
    }

    public static DocumentoResponseDto response(){
        return new DocumentoResponseDto(ID,TITULO,TIPO,CONTEUDO);
    }
}