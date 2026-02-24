package com.queroler.CadastroDeUsuario.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.queroler.CadastroDeUsuario.enuns.UsuarioRole;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

@Schema(name = "UsuarioExibirResponse", description = "Dados para serem retornados ao usuário")
public record UsuarioExibirResponseDto(


        @Schema(description = "Nome completo do usuário", example = "Saulo Rodrigues")
        String nome,

        @Schema(description = "Email do usuário", example = "saulo@email.com")
        String email,

        @Schema(description = "CPF do usuário", example = "12345678901")
        String cpf,

        @JsonFormat(pattern = "dd/MM/yyyy")
        @Schema(description = "Data de nascimento", example = "02/08/2001")
        LocalDate dataDeNascimento,

        @Schema(description = "Cidade do usuário", example = "Campinas")
        String cidade,

        @Schema(description = "Estado do usuário", example = "São Paulo")
        String estado,

        @Schema(description = "País do usuário", example = "Brasil")
        String pais
) {}