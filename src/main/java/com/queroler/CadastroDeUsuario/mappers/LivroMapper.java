package com.queroler.CadastroDeUsuario.mappers;

import com.queroler.CadastroDeUsuario.dtos.livro.LivroRequestDto;
import com.queroler.CadastroDeUsuario.dtos.livro.LivroResponseDto;
import com.queroler.CadastroDeUsuario.model.Livro;

public interface LivroMapper {
    Livro toEntity(LivroRequestDto dto);
    LivroResponseDto toResponse(Livro livro);
}
