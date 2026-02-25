package com.queroler.CadastroDeUsuario.mappers.implementacoes;

import com.queroler.CadastroDeUsuario.dtos.documento.DocumentoRequestDto;
import com.queroler.CadastroDeUsuario.dtos.documento.DocumentoResponseDto;
import com.queroler.CadastroDeUsuario.dtos.documento.DocumentoUpdateDto;
import com.queroler.CadastroDeUsuario.mappers.DocumentoMapper;
import com.queroler.CadastroDeUsuario.model.Documento;
import org.springframework.stereotype.Component;

@Component
public class DocumentoMapperImpl implements DocumentoMapper {
    @Override
    public Documento toEntity(DocumentoRequestDto dto) {
        Documento documento = new Documento();
        documento.setTitulo(dto.titulo());
        documento.setTipo(dto.tipo());
        documento.setConteudo(dto.conteudo());
        return documento;
    }

    @Override
    public DocumentoResponseDto toResponse(Documento documento) {
        return new DocumentoResponseDto(
                documento.getId(),
                documento.getTitulo(),
                documento.getTipo(),
                documento.getConteudo()
        );
    }

    @Override
    public void toUpdate(Documento documento, DocumentoUpdateDto dto) {
       if(dto.titulo()!=null){
           documento.setTitulo(dto.titulo());
       }
       if(dto.tipo()!= null){
           documento.setTipo(dto.tipo());
       }
       if(dto.conteudo()!=null){
           documento.setConteudo(dto.conteudo());
       }
    }
}