package com.queroler.CadastroDeUsuario.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record UsuarioLeitorAtualizadoDto(

        @Size(max = 80)
        @Pattern(regexp = "^[\\p{L} ]+$", message = "O nome deve conter apenas letras e espaços")
        @Schema(description = "Nome completo do usuário", example = "Saulo Rodrigues", maxLength = 80)
        String nome,

        @Email
        @Size(max = 150)
        @Schema(description = "Email do usuário", example = "saulo@email.com", maxLength = 150)
        String email,

        @Past
        @JsonFormat(pattern = "dd/MM/yyyy")
        @Schema(description = "Data de nascimento", example = "02/08/2001")
        LocalDate dataDeNascimento,

        @Size(max = 80)
        @Schema(description = "Cidade do usuário", example = "Campinas", maxLength = 80)
        String cidade,

        @Size(max = 100)
        @Schema(description = "Estado do usuário", example = "São Paulo", maxLength = 100)
        String estado,

        @Size(max = 100)
        @Schema(description = "País do usuário", example = "Brasil", maxLength = 100)
        String pais,

        @Size(min = 6)
        @Schema(description = "Senha do usuário", example = "123456", minLength = 6)
        String senha
) {}