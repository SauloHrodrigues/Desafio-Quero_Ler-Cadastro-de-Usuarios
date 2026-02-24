package com.queroler.CadastroDeUsuario.dtos.administrador;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;

public record UsuarioAdministradorAtualizadoDto(

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
) {
}