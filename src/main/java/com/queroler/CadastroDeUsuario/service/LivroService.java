package com.queroler.CadastroDeUsuario.service;

import com.queroler.CadastroDeUsuario.dtos.livro.LivroRequestDto;
import com.queroler.CadastroDeUsuario.dtos.livro.LivroResponseDto;

public interface LivroService {
    LivroResponseDto cadastrar(LivroRequestDto requestDto);
    LivroResponseDto buscarLivro(Long id);
}