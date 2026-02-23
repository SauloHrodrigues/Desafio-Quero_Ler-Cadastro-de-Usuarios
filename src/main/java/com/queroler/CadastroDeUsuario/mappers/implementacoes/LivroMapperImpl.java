package com.queroler.CadastroDeUsuario.mappers.implementacoes;

import com.queroler.CadastroDeUsuario.dtos.livro.LivroRequestDto;
import com.queroler.CadastroDeUsuario.dtos.livro.LivroResponseDto;
import com.queroler.CadastroDeUsuario.mappers.LivroMapper;
import com.queroler.CadastroDeUsuario.model.Livro;
import org.springframework.stereotype.Component;

@Component
public class LivroMapperImpl implements LivroMapper {
    @Override
    public Livro toEntity(LivroRequestDto dto) {
        Livro livro = new Livro();
        livro.setTitulo(dto.titulo());
        livro.setIsbn(dto.isbn());
        livro.setAutor(dto.autor());
        livro.setDataDePublicacao(dto.dataDePublicacao());
        return livro;
    }

    @Override
    public LivroResponseDto toResponse(Livro livro) {
        return new LivroResponseDto(
                livro.getId(),
                livro.getTitulo(),
                livro.getIsbn(),
                livro.getAutor(),
                livro.getDataDePublicacao(),
                livro.getNomeUsuarioCadastro()
        );
    }
}
