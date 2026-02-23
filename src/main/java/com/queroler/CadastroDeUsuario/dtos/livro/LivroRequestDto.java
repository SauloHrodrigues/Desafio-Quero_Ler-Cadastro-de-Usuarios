package com.queroler.CadastroDeUsuario.dtos.livro;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;
@Schema(name = "LivroRequest", description = "Dados necessários para cadastro de um livro")
public record LivroRequestDto(

            @NotBlank(message = "Título é obrigatório")
            @Size(min = 2, max = 150, message = "Título deve ter entre 2 e 150 caracteres")
            @Schema(description = "Título do livro",example = "Clean Code")
            String titulo,

            @NotBlank(message = "ISBN é obrigatório")
            @Size(min = 10, max = 20, message = "ISBN deve ter entre 10 e 20 caracteres")
            @Schema(description = "Código ISBN único do livro",example = "9780132350884")
            String isbn,

            @NotBlank(message = "Autor é obrigatório")
            @Size(min = 2, max = 120, message = "Autor deve ter entre 2 e 120 caracteres")
            @Schema(description = "Nome do autor", example = "Robert C. Martin")
            String autor,

            @NotNull(message = "Data de publicação é obrigatória")
            @PastOrPresent(message = "Data de publicação não pode ser futura")
            @Schema(description = "Data de publicação do livro", example = "2008-08-01")
            LocalDate dataDePublicacao
    ) {}