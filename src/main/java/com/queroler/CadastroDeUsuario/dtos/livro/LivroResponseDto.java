package com.queroler.CadastroDeUsuario.dtos.livro;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

@Schema(name = "LivroResponse", description = "Dados retornados de um livro")
public record LivroResponseDto(

        @Schema(description = "ID do livro", example = "1")
        Long id,

        @Schema(description = "Título do livro", example = "Clean Code")
        String titulo,

        @Schema(description = "Código ISBN único", example = "9780132350884")
        String isbn,

        @Schema(description = "Autor do livro",example = "Robert C. Martin")
        String autor,

        @Schema(description = "Data de publicação", example = "2008-08-01")
        LocalDate dataDePublicacao,

        @Schema(description = "Cadastrado por ", example = "José da Silva")
        String cadstradoPor
) {}