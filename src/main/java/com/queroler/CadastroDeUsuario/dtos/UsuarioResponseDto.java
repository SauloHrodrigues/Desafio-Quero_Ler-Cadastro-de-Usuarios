package com.queroler.CadastroDeUsuario.dtos;
import com.queroler.CadastroDeUsuario.enuns.UsuarioRole;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;

@Schema(name = "UsuarioResponse", description = "Dados retornados do usuário")
public record UsuarioResponseDto(

        @Schema(description = "ID do usuário", example = "1")
        Long id,

        @Schema(description = "Nome completo do usuário", example = "Saulo Rodrigues")
        String nome,

        @Schema(description = "Email do usuário", example = "saulo@email.com")
        String email,

        @Schema(description = "CPF do usuário", example = "12345678901")
        String cpf,

        @Schema(description = "Data de nascimento", example = "1995-08-21")
        LocalDate dataDeNascimento,

        @Schema(description = "Cidade do usuário", example = "Campinas")
        String cidade,

        @Schema(description = "Estado do usuário", example = "São Paulo")
        String estado,

        @Schema(description = "País do usuário", example = "Brasil")
        String pais,

        @Schema(description = "Perfil do usuário", example = "LEITOR")
        UsuarioRole role,

        @Schema(description = "Login do usuário", example = "saulo.rodrigues")
        String login
) {}